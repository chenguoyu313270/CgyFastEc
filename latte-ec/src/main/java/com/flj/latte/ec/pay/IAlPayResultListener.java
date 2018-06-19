package com.flj.latte.ec.pay;

/** 阿里支付宝 支付流程回调
 * Created by 傅令杰
 */

public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
