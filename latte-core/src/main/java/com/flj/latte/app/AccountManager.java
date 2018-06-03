package com.flj.latte.app;

import com.flj.latte.util.storage.LattePreference;

/**
 * Created by cguyu on 2018/6/3.
 */

public class AccountManager {

//    public static void checkAccount(IUserChecker checker) {
//        if (isSignIn()) {
//            checker.onSignIn();
//        } else {
//            checker.onNotSignIn();
//        }
//    }
    //枚举保存状态。
    private enum SingTag {
        SIGN_TAG
    }

    //方法设置登录状态 state
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SingTag.SIGN_TAG.name(), state);
    }

    //    获取登录状态
    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SingTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        }else{
            checker.onNotSignIn();
        }

    }

}
