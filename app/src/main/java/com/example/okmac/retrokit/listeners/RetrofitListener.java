package com.example.okmac.retrokit.listeners;

import com.example.okmac.retrokit.models.ErrorPoJo;

public interface RetrofitListener {
    void onResponseSuccess(String responseBodyString, int flag);

    void onResponseError(ErrorPoJo errorPoJo, Throwable throwable, int apiFlag);

}
