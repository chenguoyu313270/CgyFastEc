package com.diabin.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.TextView;
import android.widget.Toast;

import com.flj.latte.activities.ProxyActivity;
import com.flj.latte.app.Latte;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.ui.launcher.ILauncherListener;
import com.flj.latte.ui.launcher.OnLauncherFinishTag;

import example.fastec.diabin.com.latte_ec.launcher.LauncherDelegate;
import example.fastec.diabin.com.latte_ec.launcher.LauncherScrollDelegate;
import example.fastec.diabin.com.latte_ec.sign.ISignListener;
import example.fastec.diabin.com.latte_ec.sign.SignInDelegate;
import example.fastec.diabin.com.latte_ec.sign.SignUpDelegate;

public class ExampleActivity extends ProxyActivity implements ISignListener,ILauncherListener{
    private TextView mTextview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
    }

    @Override
    public LatteDelegate setRootDelegare() {


//        return new ExampleDelagate();//测试
        return new LauncherDelegate();//启动测试图
//        return  new LauncherScrollDelegate();//侧滑5张广告
//        return new SignInDelegate();//sing up zhuce

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"denglu",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"zhucechenggong",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();

                startWithPop(new ExampleDelagate());

//                getSupportDelegate().startWithPop(new ExampleDelagate());
//                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                start(new SignInDelegate());
//                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }

    }
}
