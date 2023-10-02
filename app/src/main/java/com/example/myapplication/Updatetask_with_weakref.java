package com.example.myapplication;

import android.os.SystemClock;

import java.lang.ref.WeakReference;

public class Updatetask_with_weakref extends Thread{
    private WeakReference<MainActivity>  act;

    public Updatetask_with_weakref(MainActivity act) {
        attach(act);
    }
    public void attach(MainActivity act){this.act=new WeakReference<>(act);}
    public void detach(){act=null;}

    @Override
    public void run() {
        super.run();
        SystemClock.sleep(4000);

        //rotate phone now, activity destroyed and GCed
        //next line may crash with with deref null pointer
        //unless reattach happen first in main thread

        //now update the textbox in mainactivity
        if (act.get() != null)
            //activity=null;    //simulates Activity being detached after above line but before next

            act.get().runOnUiThread(new Runnable() {
                public void run() {
                    //will this crash? No, this thread holds a ref to the activity
                    //so its not garbage collected, but the below change happens
                    // to the old activity, not the new one

                    act.get().manipUI(true);
                }
            });
    }
}
