package com.tradelexi.util;

import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class FunctionUtil {

    public TranslateAnimation shakeError() {
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }

    public void displaySnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public void displayToast(View view, String msg) {
        Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
