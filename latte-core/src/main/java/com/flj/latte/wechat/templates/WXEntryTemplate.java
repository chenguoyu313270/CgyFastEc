package com.flj.latte.wechat.templates;

import com.flj.latte.activities.ProxyActivity;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.wechat.BaseWXEntryActivity;
import com.flj.latte.wechat.LatteWeChat;

/**
 * Created by Administrator on 2018\6\4 0004.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {
    @Override
    protected void onResume() {
        super.onResume();

        finish();
        overridePendingTransition(0, 0);//动画方法
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }

}
