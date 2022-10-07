package com.example.myapplication;

import android.os.SystemClock;
import android.widget.TextView;

public class Updatetask extends Thread{
    private MainActivity activity;
    private int cnt;

    public Updatetask(MainActivity act,int cnt) {
        this.cnt=cnt;
        attach(act);
    }
    public void attach(MainActivity act){activity=act;}
    public void detach(){activity=null;}

    @Override
    public void run() {
        super.run();
        SystemClock.sleep(2000);

        //now update the textbox in mainactivity
        if (activity != null)
            //activity=null;    //simulates Activity being detached after above line but before next
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    //will this crash? No, this thread holds a ref to the activity
                    //so its not garbage collected, but the below change happens
                    // to the old activity, not the new one
                    TextView tv=activity.findViewById(R.id.tv);
                    tv.setText("Finished Thread "+Integer.toString(cnt));
                }
            });
    }
}
