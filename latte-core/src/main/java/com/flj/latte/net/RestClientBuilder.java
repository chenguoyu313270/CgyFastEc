package com.flj.latte.net;

import android.content.Context;

import com.flj.latte.net.callback.IError;
import com.flj.latte.net.callback.IFailure;
import com.flj.latte.net.callback.IRequest;
import com.flj.latte.net.callback.ISuccess;
import com.flj.latte.ui.LoaderStyle;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018\5\24 0024.
 */

public class RestClientBuilder {

    private String mURL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private File mFile;
    private  Context mContext = null;
    private  LoaderStyle mLoaderStyle = null;


    public RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mURL = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {

        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object values) {

        PARAMS.put(key, values);

        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);

        return this;
    }

    public final RestClientBuilder onRequest(IRequest iResquest) {
        this.mIRequest = iResquest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String  file) {
        this.mFile  =new File(file);
        return this;
    }
    //    private Map<String ,Object> checkParams(){
//        if (mParams==null){
//            return new WeakHashMap<>();
//        }
//
//        return PARAMS;
//    }
    public final RestClientBuilder loader(Context context,LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle=loaderStyle;
        return this;
    }
    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle=LoaderStyle.BallClipRotateIndicator;
        return this;
    }

    public final RestClient builder() {
        return new RestClient(mURL, PARAMS, mIRequest, mISuccess
                , mIFailure, mIError, mBody,mFile, mContext, mLoaderStyle);
    }


}
