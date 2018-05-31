package com.flj.latte.net.rx;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018\5\24 0024.
 */

public interface RxRestService {
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> Params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> Params);

    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> Params);

    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody body);

    @DELETE
    Call<String> delete(@Url String url, @FieldMap Map<String, Object> Params);

    @Streaming //@Streaming 边下载边存储 异步
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> Params);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);

}
