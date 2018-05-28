package com.diabin.fastec.example;

import android.app.Application;

import com.flj.latte.app.latte;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import example.fastec.diabin.com.latte_ec.icon.FontEcModel;

/**
 * Created by Administrator on 2018\5\23 0023.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        latte.init(this)
                .withIcon(new FontAwesomeModule())
        .withIcon(new FontEcModel())
        .withApiHost("")
        .configure();


    }
}
