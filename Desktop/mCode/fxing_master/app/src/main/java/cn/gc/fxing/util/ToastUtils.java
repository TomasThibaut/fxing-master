package cn.gc.fxing.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 宫成 on 15/8/13 下午8:21.
 */
public class ToastUtils {
    public static void showToast(Context context, String value) {
        Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
    }
}
