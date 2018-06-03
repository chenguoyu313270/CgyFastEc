package example.fastec.diabin.com.latte_ec.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.ui.launcher.LauncherHolderCreator;

import com.flj.latte.ui.launcher.ScrollLauncherTag;
import com.flj.latte.util.storage.LattePreference;

import java.util.ArrayList;

import example.fastec.diabin.com.latte_ec.R;

/**
 * Created by cguyu on 2018/6/2.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private void initBanner() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            INTEGERS.add(R.mipmap.launcher_01);
            INTEGERS.add(R.mipmap.launcher_02);
            INTEGERS.add(R.mipmap.launcher_04);
            INTEGERS.add(R.mipmap.launcher_03);
            INTEGERS.add(R.mipmap.launcher_05);
        }

        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//水平居中
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }
    @Override
    public Object setLayout() {
//        mConvenientBanner=new ConvenientBanner<Integer>(getContext());
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "position"+position, Toast.LENGTH_SHORT).show();
        if (position==INTEGERS.size()-1){
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
        }
    }
}
