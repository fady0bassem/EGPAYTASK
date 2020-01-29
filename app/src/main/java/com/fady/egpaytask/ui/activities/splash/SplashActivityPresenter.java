package com.fady.egpaytask.ui.activities.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.fady.egpaytask.ui.activities.main.MainActivity;

class SplashActivityPresenter {

    private Context context;
    private SplashActivityInterface anInterface;
    private static int splash_time = 1000;

    SplashActivityPresenter(Context context, SplashActivityInterface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    void navigate() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
            anInterface.openMainActivity();
        }, splash_time);
    }
}