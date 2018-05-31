package com.flj.latte.net.download;

import android.os.AsyncTask;

import com.flj.latte.net.RestCreator;
import com.flj.latte.net.callback.IError;
import com.flj.latte.net.callback.IFailure;
import com.flj.latte.net.callback.IRequest;
import com.flj.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018\5\30 0030.
 */

public class DownloadHandler {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(String URL, IRequest REQUEST, String DOWNLOAD_DIR,
                           String EXTENSION, String NAME, ISuccess SUCCESS,
                           IFailure FAILURE, IError ERROR) {
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
    }

    public final void handleDownload(){
        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }
RestCreator.getRestService().download(URL,PARAMS)
        .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()){
                    final ResponseBody responseBody = response.body();
                    final SaveFileTask saveFileTask=new SaveFileTask(REQUEST,SUCCESS);
                    saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR
                            ,EXTENSION,NAME, responseBody, NAME);
                    if (saveFileTask.isCancelled()){
                        if (REQUEST != null) {
                            REQUEST.onRequestEnd();
                        }

                    }
                }else{
                    if (ERROR != null) {
                        ERROR.onError(response.code(), response.message());
                    }

                }
                RestCreator.getParams().clear();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (FAILURE != null) {
                    FAILURE.onFailure();
                    RestCreator.getParams().clear();
                }
            }
        });
    }

}
