package com.example.mindmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DrawView extends View {
    private Paint paint = new Paint();

    private MindMapEditorActivity activity;

    public DrawView(MindMapEditorActivity context) {
        super(context);

        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(10);

        activity = context;
    }

    private static int getRelativeLeft(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getLeft();
        else
            return myView.getLeft() + getRelativeLeft((View) myView.getParent());
    }

    private static int getRelativeTop(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getTop();
        else
            return myView.getTop() + getRelativeTop((View) myView.getParent());
    }

    private void drawConnections(Canvas canvas, NodeFragment fragment)
    {
        /*if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return;
        }*/

        int barsHeight = activity.getBarHseight();

        View parentView = fragment.getView().findViewById(R.id.node_img);
        int[] parentLocation = new int[] {getRelativeLeft(parentView), getRelativeTop(parentView)};
        //parentView.getLocationInSurface(parentLocation);
        parentLocation[0] += parentView.getWidth() / 2;
        parentLocation[1] += parentView.getHeight() / 2;

        for (Node child : fragment.node.children)
        {
            View childView = child.fragment.getView();
            if (childView == null)
            {
                continue;
            }

            childView = childView.findViewById(R.id.node_img);
            if (childView == null)
            {
                continue;
            }

            int[] childLocation = new int[] {getRelativeLeft(childView), getRelativeTop(childView)};
            //childView.getLocationInSurface(childLocation);
            childLocation[0] += childView.getWidth() / 2;
            childLocation[1] += childView.getHeight() / 2;

            canvas.drawLine(parentLocation[0], parentLocation[1] - barsHeight, childLocation[0], childLocation[1] - barsHeight, paint);

            drawConnections(canvas, child.fragment);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        drawConnections(canvas, activity.getNodeFragments().get(0));
        invalidate();
    }

}