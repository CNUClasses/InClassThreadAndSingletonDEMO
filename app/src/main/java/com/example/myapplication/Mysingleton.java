package com.example.myapplication;

public class Mysingleton {
    private static Mysingleton INSTANCE;
    private static Updatetask mt;
    private static int cnt=0;

    //dont let anyone create this
    private Mysingleton() {
    }

    public static Mysingleton getInstance(){
        if(INSTANCE == null)
            INSTANCE = new Mysingleton();
        return INSTANCE;
    }

    public static void start(MainActivity act){
        mt=new Updatetask(act,cnt);
        mt.start();
        cnt++;
    }

    public static void attach(MainActivity act){
        if(mt != null)
            mt.attach(act);
    }

    public static void detach(){
        if(mt != null)
            mt.detach();
    }


}
