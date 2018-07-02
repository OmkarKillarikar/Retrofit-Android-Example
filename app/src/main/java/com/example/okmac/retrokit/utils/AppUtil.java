package com.example.okmac.retrokit.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.okmac.retrokit.R;

public class AppUtil {
    private static Logger logger = new Logger(AppUtil.class.getSimpleName());

    public static String getApplicationVersionName(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            logger.debug("Exception while fetching version name");
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
        return context.getString(R.string.not_available);
    }

    /**
     * Retrieve Application Version code for sending in the header of api calls
     *
     * @param context
     * @return App Code
     */
    public static int getApplicationVersionCode(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            logger.debug("Exception while fetching version name");
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
        return -1;
    }
}
