package com.nic.get_current_loc;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;


public class StatusBarColor {

    Context context;

    public StatusBarColor(Context context) {
        this.context=context;
    }

    public void Status_Color(Activity activity) {
        Window window=activity.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        window.setStatusBarColor(ContextCompat.getColor(activity, R.color.statusbarcolor));


    }
}