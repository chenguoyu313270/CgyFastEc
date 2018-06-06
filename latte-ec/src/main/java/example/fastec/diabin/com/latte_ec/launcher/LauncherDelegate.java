//package example.fastec.diabin.com.latte_ec.launcher;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.View;
//
//import android.support.v7.widget.AppCompatTextView;
//
//
//import com.flj.latte.app.AccountManager;
//import com.flj.latte.app.IUserChecker;
//import com.flj.latte.delegates.LatteDelegate;
//import com.flj.latte.ui.launcher.ILauncherListener;
//import com.flj.latte.ui.launcher.OnLauncherFinishTag;
//import com.flj.latte.ui.launcher.ScrollLauncherTag;
//import com.flj.latte.util.storage.LattePreference;
//import com.flj.latte.util.time.BaseTimeTask;
//import com.flj.latte.util.time.ITimerListener;
//
//import java.text.MessageFormat;
//import java.util.Timer;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import example.fastec.diabin.com.latte_ec.R;
//import example.fastec.diabin.com.latte_ec.R2;
//import example.fastec.diabin.com.latte_ec.sign.SignUpDelegate;
//
///**
// * Created by cguyu on 2018/6/2.
// */
//
//public class LauncherDelegate extends LatteDelegate implements ITimerListener {
//
//    @BindView(R2.id.tv_launcher_timer)
//    AppCompatTextView mTvTimer = null;
//
//    private Timer mTimer = null;
//    private int mCount = 5;
//
//    private ILauncherListener mILauncherListener = null;//
//
//    @OnClick(R2.id.tv_launcher_timer)
//    void onClickTimerView() {
//        if (mTimer != null) {
//            mTimer.cancel();
//            mTimer = null;
//            checkIsShowScroll();
//        }
//
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        if (activity instanceof ILauncherListener) {
//            mILauncherListener = (ILauncherListener) activity;
//        }
//    }
//
//
//
//    private void initTimer() {
//        mTimer = new Timer();
//        final BaseTimeTask task = new BaseTimeTask(this);
//        mTimer.schedule(task, 0, 1000);
//    }
//
//    @Override
//    public Object setLayout() {
//        return R.layout.delegate_launcher;
//    }
//
//    @Override
//    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
//        initTimer();
//    }
//
//    //判断是否显示滑动启动页
//    private void checkIsShowScroll() {
//        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
//            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
//        } else {
//            //检查用户是否登录了APP
//            AccountManager.checkAccount(new IUserChecker() {
//                @Override
//                public void onSignIn() {
//                    if (mILauncherListener != null) {
//                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
//                    }
//                }
//
//                @Override
//                public void onNotSignIn() {
//                    if (mILauncherListener != null) {
//                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
//                    }
//                }
//            });
//        }
//    }
//
//    @Override
//    public void onTimer() {
//        getProxyActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (mTvTimer != null) {
//                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
//                    mCount--;
//                    if (mCount < 0) {//倒计时结束后
//                        if (mTimer != null) {
//                            mTimer.cancel();
//                            mTimer = null;
//                            checkIsShowScroll();
//                        }
//                    }
//                }
//            }
//        });
//
//    }
//}