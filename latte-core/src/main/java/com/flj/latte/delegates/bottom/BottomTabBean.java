package com.flj.latte.delegates.bottom;

/**
 * Created by Administrator on 2018\6\5 0005.
 */

public class BottomTabBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence ICON, CharSequence TITLE) {
        this.ICON = ICON;
        this.TITLE = TITLE;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
