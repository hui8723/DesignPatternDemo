package com.example.tangminghui.designpatterndemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tangminghui.designpatterndemo.R;

public class MainActivity extends AppCompatActivity {

    private static  final String TAG = "MainActivity";

    private Button btnMvc,btnMvp,btnMvvm,btnBest;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnMvc = (Button) findViewById(R.id.btn_mvc);
        btnMvp = (Button) findViewById(R.id.btn_mvp);
        btnMvvm = (Button) findViewById(R.id.btn_mvvm);
        btnBest = (Button) findViewById(R.id.btn_best);

        btnMvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MvcDesignActivity.class);
                startActivity(intent);
            }
        });
        btnMvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MvpDesignActivity.class);
                startActivity(intent);
            }
        });
        btnMvvm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MvvmDesignActivity.class);
                startActivity(intent);
            }
        });
        btnBest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, BestDesignActivity.class);
                startActivity(intent);
            }
        });
    }
}
