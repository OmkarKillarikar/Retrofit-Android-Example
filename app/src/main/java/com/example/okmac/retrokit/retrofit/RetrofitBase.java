package com.example.okmac.retrokit.retrofit;

import android.content.Context;

import com.example.okmac.retrokit.BuildConfig;
import com.example.okmac.retrokit.listeners.RetrofitListener;
import com.example.okmac.retrokit.models.ErrorPoJo;
import com.example.okmac.retrokit.utils.AppUtil;
import com.example.okmac.retrokit.utils.ConfigUtils;
import com.example.okmac.retrokit.utils.Constants;
import com.example.okmac.retrokit.utils.HttpUtil;
import com.example.okmac.retrokit.utils.Logger;
import com.example.okmac.retrokit.utils.ProfilePreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBase {
    protected Retrofit retrofit;
    protected ProfilePreferences profilePreference;
    protected Context context;
    private Logger logger;


    public RetrofitBase(Context context, boolean addTimeout) {
        this.context = context;


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder().addInterceptor(interceptor);
        if (addTimeout) {
            httpClientBuilder.readTimeout(Constants.TimeOut.SOCKET_TIME_OUT, TimeUnit.SECONDS);
            httpClientBuilder.connectTimeout(Constants.TimeOut.CONNECTION_TIME_OUT, TimeUnit.SECONDS);
        } else {
            httpClientBuilder.readTimeout(Constants.TimeOut.IMAGE_UPLOAD_SOCKET_TIMEOUT, TimeUnit.SECONDS);
            httpClientBuilder.connectTimeout(Constants.TimeOut.IMAGE_UPLOAD_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        }
        addVersioningHeaders(httpClientBuilder, context);
        OkHttpClient httpClient = httpClientBuilder.build();

        logger = new Logger(RetrofitBase.class.getSimpleName());

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUtils.SERVER_URL + ":"
                        + ConfigUtils.SERVER_PORT + "/"
                        + ConfigUtils.APPLICATION_BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        profilePreference = new ProfilePreferences(context);
    }

    private void addVersioningHeaders(OkHttpClient.Builder builder, Context context) {
        final String appVersion = "v.1.0.1";
        final int version = AppUtil.getApplicationVersionCode(context);
        final String appName = "RetroKit";
        final String name = "RetroKit";
        builder.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader(appVersion, String.valueOf(version))
                        .addHeader(appName, name)
                        .build();
                return chain.proceed(request);
            }
        });
    }


    void validateResponse(Response response, RetrofitListener retrofitListener, int apiFlag) {
        if (response.code() == 200) {
            ResponseBody responseBody = (ResponseBody) response.body();
            try {
                retrofitListener.onResponseSuccess(responseBody.string(), apiFlag);
            } catch (IOException e) {
                error(response, retrofitListener, apiFlag);
            }
        } else {
            error(response, retrofitListener, apiFlag);
        }
    }

    private void error(Response response, RetrofitListener retrofitListener, int apiFlag) {
        Gson gson = new Gson();
        ErrorPoJo errorPojo;
        try {
            errorPojo = gson.fromJson((response.errorBody()).string(), ErrorPoJo.class);
            if (errorPojo == null) {
                errorPojo = HttpUtil.getServerErrorPojo(context);
            }
            retrofitListener.onResponseError(errorPojo, null, apiFlag);
        } catch (Exception e) {
            retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), null, apiFlag);
        }
    }

    /*protected void checkVersioningHeader(final RetrofitListener retrofitListener, Call call, Response response, int apiFlag) {
        //Getting headers from response object
        Headers headers = response.headers();
        //Storing it in a Map for evaluation
        Map<String, List<String>> headersMultiMap = headers.toMultimap();
        //Checking the headers for FORCE_UPDATE and its value to be true
        if (headersMultiMap.containsKey(AppConstants.ApiHeader.FORCE_UPDATE)
                && headersMultiMap.get(AppConstants.ApiHeader.FORCE_UPDATE).get(0).equalsIgnoreCase("true")) {
            ApplicationPreference applicationPreference = new ApplicationPreference(context);
            applicationPreference.setShowForceUpdateScreen(true);
            applicationPreference.setPreviousApplicationVersion(AppUtil.getApplicationVersionCode(context));
            //If true then calling the Update Screen to display to the user
            if (this.activity != null) {
                AppUtil.callForceUpdateScreen(activity);
            }
        } else {
            validateResponse(retrofitListener,call, response, apiFlag);
        }
    }*/

    /*protected void validateResponse(final RetrofitListener retrofitListener, Call call, Response response, int apiFlag) {
        if (response.code() == 200) {
            ResponseBody responseBody = (ResponseBody) response.body();
            String responseBodyString = null;
            try {
                responseBodyString = responseBody.string();
            } catch (Exception e) {
                logger.error(e);
            }
            if (responseBodyString != null) {
                retrofitListener.onResponseSuccess(responseBodyString, apiFlag);
            } else {
                retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), null, apiFlag);
            }
        } else if (response.code() == 401) {
            if (activity != null) {
                AppUtil.callForceLoginScreen(activity);
            }
        } else {
            Gson gson = new Gson();
            ErrorPoJo errorPojo;
            try {
                errorPojo = gson.fromJson((response.errorBody()).string(), ErrorPoJo.class);
                if (errorPojo == null) {
                    errorPojo = HttpUtil.getServerErrorPojo(context);
                }
                retrofitListener.onResponseError(errorPojo, null, apiFlag);
            } catch (Exception e) {
                retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), null, apiFlag);
            }
        }
    }*/
}
