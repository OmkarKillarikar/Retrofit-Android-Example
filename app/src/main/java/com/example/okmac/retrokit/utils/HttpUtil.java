package com.example.okmac.retrokit.utils;

import android.content.Context;

import com.example.okmac.retrokit.R;
import com.example.okmac.retrokit.models.ErrorPoJo;
import com.google.gson.Gson;

import org.json.JSONObject;

public class HttpUtil {
    private static Logger logger = new Logger(HttpUtil.class.getSimpleName());

    /**
     * This method returns a Json object for handling Force update error
     *
     * @return
     */
    public static JSONObject getServerErrorJsonObject(Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.ErrorClass.STATUS, 505);
            jsonObject.put(Constants.ErrorClass.CODE, 3000);
            jsonObject.put(Constants.ErrorClass.MESSAGE, context.getString(R.string.server_not_available));
            jsonObject.put(Constants.ErrorClass.DEVELOPER_MESSAGE, context.getString(R.string.server_not_available));
        } catch (Exception e) {
            logger.error(e);
        }
        return jsonObject;
    }

    /**
     * This method returns a Json object for handling Force update error
     *
     * @return
     */
    public static ErrorPoJo getServerErrorPojo(Context context) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(getServerErrorJsonObject(context).toString(), ErrorPoJo.class);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}
