package com.example.myapplication;

import android.os.SystemClock;

import java.lang.ref.WeakReference;

public class UpdateTask extends Thread{
    private MainActivity  act;

    public UpdateTask(MainActivity act) {
        attach(act);
    }
    public void attach(MainActivity act){this.act=act;}
    public void detach(){act=null;}

    @Override
    public void run() {
        super.run();
        SystemClock.sleep(4000);
        //rotate phone now, activity destroyed and GCed

        if (act != null)

            //next line may crash with with deref null pointer
            //unless reattach happens first in main thread
            //activity=null;    //simulates Activity being detached after above line but before next
            act.runOnUiThread(new Runnable() {
                public void run() {
                    //will this crash? No, this thread holds a ref to the activity
                    //so its not garbage collected, but the below change happens
                    // to the old activity, not the new one

                    act.manipUI(true);
                }
            });
    }
}
