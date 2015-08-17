package cn.gc.fxing.util;

import android.util.Log;

/**
 * Created by 宫成 on 15/8/12.
 */
public class LogUtils {
    public static void Log(String tag, Object obj) {
        Log.e(tag, ("" + obj + " = ") + obj);
    }
}
