package com.example.mindmap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 개발자 : 20191583 나민형
 * 마지막 수정일 : 2020-11-20
 * 기능 : 템플릿 선택 액티비티
 * 추가 설명 : 아이디어 구상에 선택할 템플릿을 선택하는 액티비티
 */

public class TemplateActivity extends AppCompatActivity {

    public static Activity activity;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Window window = getWindow();
        window.setStatusBarColor(Color.BLACK);
        setContentView(R.layout.activity_template);

        activity = TemplateActivity.this;

        Button backButton;
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ArrayList<IdeaTemplate> templates = new ArrayList<>();
        templates.add(new IdeaTemplate("마인드맵", "지도를 그리듯 자유로운 편집이 가능한 템플릿"));

        final RecyclerView recyclerView = findViewById(R.id.templateRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final TemplateAdapter adapter = new TemplateAdapter(templates);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new TemplateAdapter.OnItemClickListener() {
            @Override
            public void OnItemCLick(View v, int pos) {
            }
        });

        Button selectTemplate;
        selectTemplate = findViewById(R.id.selectTemplateButton);
        selectTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.getSelected() != -1) {
                    Intent intent = new Intent(TemplateActivity.this, CreateActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(TemplateActivity.this, "템플릿을 선택하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}