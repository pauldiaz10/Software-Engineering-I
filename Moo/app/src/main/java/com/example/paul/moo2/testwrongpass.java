package com.example.paul.moo2;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class testwrongpass extends Activity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testwrongpass);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));
        setFinishOnTouchOutside(true);
    }
}
