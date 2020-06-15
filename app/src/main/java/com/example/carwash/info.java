package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        this.setTitle(R.string.titulo_info);
    }
}
