package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doClick(View view) {
        Mysingleton.getInstance().start(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Mysingleton.getInstance().attach(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        //where to save a ref to this thread?
        //singleton
        Mysingleton.getInstance().detach();
    }
}