package com.example.okmac.retrokit.retrofit;

import com.example.okmac.retrokit.utils.Constants;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServices {

    @GET(Constants.UrlPath.EMERGENCY_SERVICES)
    Call<ResponseBody> getSomething(@Path("service") String service);

    @GET(Constants.UrlPath.GET_SOMETHING_ELSE)
    Call<ResponseBody> getSomethingElse();

}
