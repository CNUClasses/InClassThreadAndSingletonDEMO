package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button b;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get refs to UI widgets
        b=findViewById(R.id.button);
        tv=findViewById(R.id.tv);
    }

    public void doClick(View view) {
        Mysingleton.getInstance().start(this);

        manipUI(Mysingleton.getInstance().bEnable);
    }

    public void manipUI(boolean bEnable){
        //disable button so we can only launch 1 thread
        b.setEnabled(bEnable);

        if(bEnable)
            tv.setText("Waiting to run thread");
        else
            tv.setText("Running Thread");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Mysingleton.getInstance().attach(this);
        manipUI(Mysingleton.getInstance().bEnable);
    }

    @Override
    protected void onStop() {
        super.onStop();

        //where to save a ref to this thread?
        //singleton
        Mysingleton.getInstance().detach();
    }
}