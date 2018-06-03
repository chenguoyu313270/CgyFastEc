package com.flj.latte.util.time;

import java.util.TimerTask;

/**
 * Created by cguyu on 2018/6/2.
 */

public class BaseTimeTask extends TimerTask{

   private ITimerListener mITimerListener=null;

    public BaseTimeTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener!=null){
            mITimerListener.onTimer();
        }

    }
}
