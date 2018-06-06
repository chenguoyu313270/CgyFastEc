package com.flj.latte.delegates.bottom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.flj.latte.app.Latte;
import com.flj.latte.delegates.LatteDelegate;

/**
 * Created by Administrator on 2018\6\5 0005.
 */

public class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {
    @Override
    public void onResume() {
        super.onResume();
        final View rootView=getView();
        if (rootView!=null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();//h
            rootView.setOnKeyListener(this);

        }
    }

    @Override
    public Object setLayout() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }


    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-TOUCH_TIME<WAIT_TIME){
                _mActivity.finish();
            }else{
                TOUCH_TIME=System.currentTimeMillis();
                Toast.makeText(_mActivity,"双击退出"+ Latte.getApplicationContext().getString(com.wang.avi.R.string.app_name),Toast.LENGTH_LONG).show();

            }
        }
        return true;
    }
}
