package com.example.user.microelectronic;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;


public class progressBar_annimation extends Animation {

    private Context context;
    private ProgressBar progressBar;
    private TextView textView;
    private float from;
    private float to;


    public progressBar_annimation(Context context, ProgressBar progressBar, TextView textView, float from, float to) {

        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
        this.from = from;
        this.to = to;

    }

    public void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
        textView.setText((int) value + "%");

        if (value == to) {
            context.startActivity(new Intent(context, Main2Activity.class));
        }


    }
}
