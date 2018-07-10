package com.flj.latte.ec.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.flj.latte.delegates.BaseDelegate;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.ec.R;
import com.flj.latte.ec.R2;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.ISuccess;
import com.flj.latte.ui.banner.HolderCreator;
import com.flj.latte.ui.widget.CircleTextView;
import com.flj.latte.util.TestUrlData;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018\6\7 0007.
 */

public class GoodsDetailDelegate extends LatteDelegate implements   AppBarLayout.OnOffsetChangedListener {

    @BindView(R2.id.goods_detail_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager = null;
    @BindView(R2.id.detail_banner)
    ConvenientBanner<String> mBanner = null;
    @BindView(R2.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolbarLayout = null;
    @BindView(R2.id.app_bar_detail)
    AppBarLayout mAppBar = null;

    //底部
    @BindView(R2.id.icon_favor)
    IconTextView mIconFavor = null;
    @BindView(R2.id.tv_shopping_cart_amount)
    CircleTextView mCircleTextView = null;
    @BindView(R2.id.rl_add_shop_cart)
    RelativeLayout mRlAddShopCart = null;
    @BindView(R2.id.icon_shop_cart)
    IconTextView mIconShopCart = null;

    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";

    private int mGoodsId = -1;

    public static GoodsDetailDelegate create(int goodsId) {

        final Bundle args=new Bundle();
        args.putInt(ARG_GOODS_ID,goodsId);
        final GoodsDetailDelegate delegate=new GoodsDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mCollapsingToolbarLayout.setContentScrimColor(Color.WHITE);//设置折叠布局 的变换颜色。
        mAppBar.addOnOffsetChangedListener(this);
        initData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle=getArguments();
        if (bundle!=null){
            mGoodsId= bundle.getInt(ARG_GOODS_ID);
            Toast.makeText(getContext(),"商品id="+mGoodsId,Toast.LENGTH_SHORT).show();
        }



    }
//  AppBarLayout.OnOffsetChangedListener
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }
    private void initData() {
        RestClient.builder()
                .url("http://news.baidu.com/")
//                .params("goods_id", mGoodsId)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String StrResponse= TestUrlData.goodsDetail;
                        final JSONObject data =
                                JSON.parseObject(StrResponse).getJSONObject("data");
                        initBanner(data);
//                        initGoodsInfo(data);
//                        initPager(data);
//                        setShopCartCount(data);
                    }
                })
                .build()
                .get();
    }
    private void initBanner(JSONObject data) {
        final JSONArray array = data.getJSONArray("banners");
        final List<String> images = new ArrayList<>();
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            images.add(array.getString(i));
        }
        mBanner
                .setPages(new HolderCreator(), images)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }
}
