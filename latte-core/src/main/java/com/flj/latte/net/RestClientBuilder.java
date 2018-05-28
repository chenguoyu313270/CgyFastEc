package com.flj.latte.net;

import com.flj.latte.net.callback.IError;
import com.flj.latte.net.callback.IFailure;
import com.flj.latte.net.callback.IRequest;
import com.flj.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018\5\24 0024.
 */

public class RestClientBuilder {

    private String mURL;
    private static final Map<String, Object> PARAMS=RestCreator.getParams();
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;

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
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);

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
//    private Map<String ,Object> checkParams(){
//        if (mParams==null){
//            return new WeakHashMap<>();
//        }
//
//        return PARAMS;
//    }
public final RestClient builder(){
        return new RestClient(mURL,PARAMS,mIRequest,mISuccess
                ,mIFailure,mIError,mBody);
}





}
