package cn.gc.fxing.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 宫成 on 15/8/10.
 */
public class CacheUtils {

    private static final String APP = "app";
    private static SharedPreferences sp;


    public static void putBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    public static void putString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }
}
