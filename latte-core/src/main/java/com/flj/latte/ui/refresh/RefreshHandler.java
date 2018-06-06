package com.flj.latte.ui.refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.flj.latte.app.Latte;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.ISuccess;

/**
 * Created by Administrator on 2018\6\6 0006.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {
        private final SwipeRefreshLayout REFRESH_LAYOUT;
    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout) {
        this.REFRESH_LAYOUT=swipeRefreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        REFRESH_LAYOUT.setRefreshing(true);//开始下拉
      Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);//结束下拉
//                firstPage("http://news.baidu.com/");
            }
        },2000);

    }
    public void firstPage(String url) {
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latte.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                }).build().get();
    }
}
