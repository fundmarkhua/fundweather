package com.example.congbai.fundweather.model.network.api;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fundmarkhua on 2017/3/9
 * Email:57525101@qq.com
 */

public interface RemoteDataApi {
    @GET("bing_pic")
    Observable<String> getBingPicRx();

    @GET("bing_pic")
    Call<String> getBingPic();
}
