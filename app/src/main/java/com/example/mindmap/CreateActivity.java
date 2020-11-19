package com.example.mindmap;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Window window = getWindow();
        window.setStatusBarColor(Color.BLACK);
        setContentView(R.layout.activity_create);

        Button backToTemplate;
        backToTemplate = findViewById(R.id.backToTemplateButton);
        backToTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final EditText startingWord;
        startingWord = findViewById(R.id.startingWordEdit);

        Button createIdea;
        createIdea = findViewById(R.id.createIdeaButton);
        createIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startingWord.getText().toString().length() == 0){
                    Toast.makeText(CreateActivity.this, "주제를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(CreateActivity.this, MindMapEditorActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }

        return super.dispatchTouchEvent(ev);
    }
}
