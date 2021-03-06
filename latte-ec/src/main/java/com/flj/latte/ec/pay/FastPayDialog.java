package com.flj.latte.ec.pay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flj.latte.app.ConfigKeys;
import com.flj.latte.app.Latte;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.ec.R;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.ISuccess;
import com.flj.latte.ui.LatteLoader;
import com.flj.latte.util.log.LatteLogger;
import com.flj.latte.wechat.LatteWeChat;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 * Created by Administrator on 2018\6\19 0019.
 */

public class FastPayDialog implements View.OnClickListener {
    //设置支付监听回调
    private IAlPayResultListener mIAlPayResultListener = null;

    private Activity mActivity = null;
    private AlertDialog mDialog = null;
    private int mOrderID = -1;//订单id

    private FastPayDialog(LatteDelegate delegate) {

        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();

    }

    public static FastPayDialog create(LatteDelegate delegate) {

        return new FastPayDialog(delegate);

    }

    //
    public void beginPayDialog() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_dialog_up_from_bottom2);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置透明的
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;

            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;//背景全部变暗的效果
            window.setAttributes(params);

            window.findViewById(R.id.btn_dialog_pay_alpay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);

        }
    }

    //设置回调
    public FastPayDialog setPayResultListener(IAlPayResultListener listener) {
        this.mIAlPayResultListener = listener;
        return this;
    }

    //设置订单id
    public FastPayDialog setOrderId(int orderId) {
        this.mOrderID = orderId;
        return this;
    }

    private void alPay(int orderId) {
//        final String singUrl = "你的服务端支付地址" + orderId;//创建支付订单
        final String singUrl = "http://app.api.zanzuanshi.com/api/v1/alipay/a=" + orderId;//创建支付订单
        //获取签名字符串
        RestClient.builder()
                .url(singUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String paySign = JSON.parseObject(response).getString("result");
                        LatteLogger.d("PAY_SIGN", paySign);
                        //必须是异步的调用客户端支付接口 去调用
                        final PayAsyncTask payAsyncTask = new PayAsyncTask(mActivity, mIAlPayResultListener);
                        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);
                    }
                })
                .build()
                .post();
    }

    private void weChatPay(int orderId) {
        LatteLoader.stopLoading();
        final String weChatPrePayUrl = "你的服务端微信预支付地址" + orderId;
        LatteLogger.d("WX_PAY", weChatPrePayUrl);

        final IWXAPI iwxapi = LatteWeChat.getInstance().getWXAPI();
        final String appId = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
        iwxapi.registerApp(appId);
        RestClient.builder()
                .url(weChatPrePayUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject result =
                                JSON.parseObject(response).getJSONObject("result");
                        final String prepayId = result.getString("prepayid");
                        final String partnerId = result.getString("partnerid");
                        final String packageValue = result.getString("package");
                        final String timestamp = result.getString("timestamp");
                        final String nonceStr = result.getString("noncestr");
                        final String paySign = result.getString("sign");

                        final PayReq payReq = new PayReq();
                        payReq.appId = appId;
                        payReq.prepayId = prepayId;
                        payReq.partnerId = partnerId;
                        payReq.packageValue = packageValue;
                        payReq.timeStamp = timestamp;
                        payReq.nonceStr = nonceStr;
                        payReq.sign = paySign;

                        iwxapi.sendReq(payReq);
                    }
                })
                .build()
                .post();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_dialog_pay_alpay) {//支付宝
            Toast.makeText(mActivity, "点击支付宝支付！", Toast.LENGTH_SHORT).show();
            alPay(mOrderID);
            mDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_wechat) {//微信支付。
            Toast.makeText(mActivity, "点击微信支付！", Toast.LENGTH_SHORT).show();

            weChatPay(mOrderID);
            mDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_cancel) {
            mDialog.cancel();
        }


    }
}
