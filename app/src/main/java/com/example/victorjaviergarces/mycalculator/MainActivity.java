package com.example.victorjaviergarces.mycalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.imageView).setOnClickListener(this);
        findViewById(R.id.my_text_imageView).setOnClickListener(this);

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.rel);
        rl.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,DataActivity.class);
        startActivity(intent);
    }


    }

