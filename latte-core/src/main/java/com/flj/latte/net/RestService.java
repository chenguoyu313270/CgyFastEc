package com.flj.latte.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
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

public interface RestService {
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String ,Object> Params);

    @FormUrlEncoded
    @POST
    Call<String> psot(@Url String url, @FieldMap Map<String ,Object> Params);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String ,Object> Params);

    @DELETE
    Call<String> delete(@Url String url, @FieldMap Map<String ,Object> Params);

    @Streaming //边下载边存储
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String ,Object> Params);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);

}
