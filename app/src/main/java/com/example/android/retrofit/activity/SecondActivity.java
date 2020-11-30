package com.example.android.retrofit.activity;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.retrofit.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String message = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.comments);
        textView.setText(message);
    }
}
