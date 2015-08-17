package cn.gc.fxing.util;

import android.content.Context;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 
 * @author yanggf
 * 得到软件设置的渠道ID
 */
public class SIDConstant {

	private static Context mContext = null;

	public static final String UNKNOWN_SID = "sidUnknown";

	private static String mSid = "sidUnknown";
	
	/**同步工具*/
	private final static String SYNCHRONOUS_TOOL_SID = "1167";

	/**
	 * 动态获取应用程序的渠道ID
	 */
	public static String getSID(Context context) {
		mContext = context;
		setSidFromAsset();
		return mSid;
	}

	private static boolean isNeedToSetSid() {
		return ((UNKNOWN_SID.equals(mSid)) && (mContext != null));
	}

	private static void setSidFromAsset() {

		if (!isNeedToSetSid()) {
			return;
		}
		ByteArrayOutputStream byteout = null;
		InputStream mInputStream = null;
		try {
			mInputStream = mContext.getAssets().open("config");
			byteout = new ByteArrayOutputStream();
			byte bytes[] = new byte[10];
			int lenght = 0;
			while ((lenght = mInputStream.read(bytes)) != -1) {
				byteout.write(bytes, 0, lenght);
			}
			bytes = byteout.toByteArray();
			String str = new String(bytes);
			String strs[] = str.split("\r\n");
			String sid = str.substring(strs[0].indexOf("=") + 1,
					strs[0].length());
			if (!TextUtils.isEmpty(sid)) {
				mSid = sid;
			}
			bytes = null;
			str = null;
			strs = null;
			byteout.close();
			mInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			mSid = UNKNOWN_SID;
		}
	}
	
	/**
	 * 判断当前的sid是否为指定的渠道包
	 * @param context
	 * @return
	 */
	public static boolean isTargetSID(Context context){
		boolean result = false;
		String currentSid = SIDConstant.getSID(context);
		if(!TextUtils.isEmpty(currentSid)){
			if(SYNCHRONOUS_TOOL_SID.equals(currentSid)){
				result = true;
			}
		}
		return result;
	}
	
}