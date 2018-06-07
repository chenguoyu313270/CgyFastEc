package com.flj.latte.ui.recycler;

import com.google.auto.value.AutoValue;

/** 存储颜色值 红 绿蓝 色值，
 * Created by Administrator on 2018\6\7 0007.
 */
@AutoValue
public abstract class RgbValue {
    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
