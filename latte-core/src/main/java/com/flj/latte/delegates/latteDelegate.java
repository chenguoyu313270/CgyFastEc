package com.flj.latte.delegates;

/**
 * Created by Administrator on 2018\5\24 0024.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
