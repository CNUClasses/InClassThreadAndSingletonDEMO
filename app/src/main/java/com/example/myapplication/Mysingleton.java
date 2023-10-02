package com.example.myapplication;

public class Mysingleton {
    private static Mysingleton INSTANCE;
//    private Updatetask_with_weakref mt;
    private UpdateTask mt;

    //UI enabled?
    public boolean bEnable=true;

    //dont let anyone create this
    private Mysingleton() {
    }

    public static Mysingleton getInstance(){
        if(INSTANCE == null)
            INSTANCE = new Mysingleton();
        return INSTANCE;
    }

    public void start(MainActivity act){
        bEnable=false;
//        mt=new Updatetask_with_weakref(act);
        mt=new UpdateTask(act);
        mt.start();
    }

    public void attach(MainActivity act){
        if(mt != null)
            mt.attach(act);
    }

    public void detach(){
        if(mt != null)
            mt.detach();
    }
}
