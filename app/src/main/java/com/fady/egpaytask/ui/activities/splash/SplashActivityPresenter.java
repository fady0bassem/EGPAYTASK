package com.fady.egpaytask.ui.activities.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.fady.egpaytask.ui.activities.main.MainActivity;

class SplashActivityPresenter {

    private Context context;
    private static int splash_time = 3000;

    SplashActivityPresenter(Context context) {
        this.context = context;
    }

    void navigate() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }, splash_time);
    }
}
