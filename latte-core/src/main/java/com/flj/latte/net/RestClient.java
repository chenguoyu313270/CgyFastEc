package com.flj.latte.net;

import com.flj.latte.net.callback.IError;
import com.flj.latte.net.callback.IFailure;
import com.flj.latte.net.callback.IRequest;
import com.flj.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018\5\24 0024.
 */

public class RestClient {
   private final String URL;
   private static final WeakHashMap<String ,Object> PARAMS=RestCreator.getParams();
   private final IRequest request;
   private final ISuccess success;
   private final IFailure failure;
   private final IError error;
    private final RequestBody body;

    public RestClient(String URL, Map<String, Object> params, IRequest REQUEST, ISuccess SUCCESS, IFailure FAILURE, IError ERROR, RequestBody BODY) {
        this.URL = URL;
        PARAMS.putAll(params);

        this.request = REQUEST;
        this.success = SUCCESS;
        this.failure = FAILURE;
        this.error = ERROR;
        this.body = BODY;
    }
    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }





}
