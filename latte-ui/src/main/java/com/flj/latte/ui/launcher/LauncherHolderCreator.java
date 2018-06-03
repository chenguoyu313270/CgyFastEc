package com.flj.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by cguyu on 2018/6/2.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder>{


    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
