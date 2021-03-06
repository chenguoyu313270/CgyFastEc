package com.flj.latte.delegates;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flj.latte.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by cguyu on 2018/5/13.
 */

public abstract class BaseDelegate extends SwipeBackFragment {

    public abstract Object setLayout();
    @SuppressWarnings("sp")
    private Unbinder mUnbinder = null;

    public abstract void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView);
//   private Unb


    @Nullable
//    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView=null;
     if(setLayout()instanceof Integer){
         rootView=inflater.inflate((Integer) setLayout(),container, false);
     }else if (setLayout()instanceof View){
         rootView=(View)setLayout();
     }
   if(rootView!=null){
       mUnbinder = ButterKnife.bind(this, rootView);
       onBindView(savedInstanceState,rootView);
   }

        return rootView;
    }
    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder!=null){
            mUnbinder.unbind();
        }

    }
}
