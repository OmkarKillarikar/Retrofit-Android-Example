package com.example.okmac.retrokit.activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.okmac.retrokit.R;
import com.example.okmac.retrokit.listeners.RetrofitListener;
import com.example.okmac.retrokit.models.ErrorObject;
import com.example.okmac.retrokit.retrofit.ApiServiceProvider;
import com.example.okmac.retrokit.utils.Constants;

public class MainActivity extends Activity implements RetrofitListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiServiceProvider apiServiceProvider = ApiServiceProvider.getInstance(this);

        //1st api call
        apiServiceProvider.getSomething("your parameter", this);
        //2nd api call
        apiServiceProvider.getSomethingElse(this);
    }

    @Override
    public void onResponseSuccess(String responseBodyString, int apiFlag) {
        switch (apiFlag) {
            case Constants.ApiFlags.GET_SOMETHING:
                //handle response
                //parse response json using gson or keep you response object as return type in ApiServices.java itself
                break;
            case Constants.ApiFlags.GET_SOMETHING_ELSE:
                //handle response of 2nd api
                break;
        }
    }

    @Override
    public void onResponseError(ErrorObject errorObject, Throwable throwable, int apiFlag) {
        switch (apiFlag) {
            case Constants.ApiFlags.GET_SOMETHING:
                //handle error
                break;
            case Constants.ApiFlags.GET_SOMETHING_ELSE:
                //handle error of 2nd api
                break;

        }
    }
}
