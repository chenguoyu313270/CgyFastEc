package com.flj.latte.net;

import android.content.Context;
import android.support.v4.math.MathUtils;

import com.flj.latte.net.callback.IError;
import com.flj.latte.net.callback.IFailure;
import com.flj.latte.net.callback.IRequest;
import com.flj.latte.net.callback.ISuccess;
import com.flj.latte.net.callback.RequestCallbacks;
import com.flj.latte.net.download.DownloadHandler;
import com.flj.latte.ui.LatteLoader;
import com.flj.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Multipart;

/**
 * Created by Administrator on 2018\5\24 0024.
 */

public class RestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    public RestClient(String URL, Map<String, Object> params,
                      String downloadDir,
                      String extension,
                      String name, IRequest request, ISuccess success, IFailure failure, IError error,
                      RequestBody body, File file, Context context, LoaderStyle loaderStyle) {
        this.URL = URL;
        PARAMS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;

        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;


    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    public void resuest(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);

                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UOLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);


                break;
            default:
                break;

        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYLE);

    }

    public final void get() {
        resuest(HttpMethod.GET);

    }

    public final void post() {
        if (BODY == null) {
            resuest(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            resuest(HttpMethod.POST_RAW);
        }


    }

    public final void put() {
//        resuest(HttpMethod.PUT);

        if (BODY == null) {
            resuest(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            resuest(HttpMethod.PUT_RAW);
        }

    }

    public final void delete() {
        resuest(HttpMethod.DELETE);

    }

    public void upload() {
        resuest(HttpMethod.UPLOAD);

    }

    public void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR,
                EXTENSION, NAME, SUCCESS,
                FAILURE, ERROR).handleDownload();

    }


}
