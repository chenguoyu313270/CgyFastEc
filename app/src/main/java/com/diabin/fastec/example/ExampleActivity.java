package com.diabin.fastec.example;

import com.flj.latte.activities.ProxyActivity;
import com.flj.latte.delegates.latteDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public latteDelegate setRootDelegare() {
        return new ExampleDelagate();
    }
}
