package cn.gc.fxing.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {	
	public static final String TAG = "Utils";
	
	public static long mAPPstatTime = 0;
	
	public static final String CLIENT = "aphone";
	public static final String PLAY_TYPE = "playtype";
	public static final String LIVEFLAG = "liveflag";
	public static final String DEMANDFSPS = "demandata";
	public static final String FORCE_UPDATE = "forceupdate";
	public static final String CACHE_UPDATE = "cacheupdate";
	
	public static int NUM = 0;// 表示精选页面图片更新的数量
	
	public static boolean isLogined = false;
	public static boolean isPlayerCrashSystem = false;
	public static boolean isPlayerCrashVLC = false;
	public static boolean isUploadBfSuccess = false;//是否已经上报过首次缓冲失败的先关信息 
	public static boolean isTipFilter = false;//是否弹出请求不到筛选的Dialog
	public static boolean isGetData = false;//是否取到了频道页的数据

	public static final String CODE_HTTP_FAIL = "-1";
	public static final String LIVEPOSITION = "liveposition";
	public static final String CODE_ERROR_RIGHT = "0";// No problem of errCode

	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String NULL = "null";

	public final static String MEDIA_KEY = "media_key";
	public final static String MEDIA_ITEM = "media_item";
	public final static String DOWNLOAD_KEY = "download_key";
	public final static String LIVE_DATA = "livedata";
	public final static String VIDEONAME = "videoname";
	public final static String IS_ENTERTAINMENT = "entertainment";
	public final static String ENTERTAINMEN_DRDATA = "entertainment_drdata";//娱乐页容灾相关的数据
	public final static String FILTER_KEY = "filter_key";
	public final static String MEDIA_CHANNEL_KEY = "media_channel_key";
	public final static String MEDIA_OPERATION = "MEDIA_OPERATION";
	public final static String PLAY_HISTORY_KEY = "play_history_info_key";
	public final static String PLAY_LOADING_KEY = "play_loading_key";
	public final static String BY_PLAY_HISTORY_KEY = "by_play_histoty_key";
	public static final String KEY_ERROR_MESSAGE = "errorMessage";//errorMessage
	public static final String THE_LIVE_SHOW_FSPS_INFO = "liveshowfspsinfo";
	public final static String MICROVIDEO_KEY = "MICROVIDEO_KEY";

	public static final byte HANDLER_SESSION_EXPIRED = 1;
	public static final byte HANDLER_DISMISS_PROGRESSDIALOG = 2;
	public static final byte HANDLER_LOGO_HTTP_FAILED = 3;
	public static final byte HANDLER_SHOW_ERRORMESSAGE = 4;
	public static final byte HANDLER_SHOW_ERRORMESSAGE_GOTO = 5;
	public static final byte HANDLER_SHOW_START_ERROR_MESSAGE = 6;
	public static final byte HANDLER_SHOW_ERROR_DATA_TOAST = 7;

	/**
	 * 启动界面广告
	 */
	public static final String GET_LAUNCH_AD_URL = "http://pub.funshion.com/interface/deliver?ap=ape_lp_v1&deliver_ver=v1";
	
	public static final String FIRST_FILTER_DATA_CONFIG = "first_filter_data_config";
	/**sharePreferences中预设值的标志*/
	public final static String PRESET_VAULE_FLAG = "preset_value";

	/** Used to identify the upgrade request path */
	public static final String UPGRADE_URL = "http://update.funshion.com/update/check_android.php?uid=";
	
	/**
	 * 用于动态配置push消息需要用到的url add by zhangshuo
	 */
	public static final String PUSH_ORIGIN_URL = "http://update.funshion.com/app/pushconfig/?client=";

	public static boolean isNormalLogin = true;//判断是正常登陆还是其他登陆，默认正常登陆
	
	public static String DIR = "Directory";

	/**
	 * 判断是否是第一次下载 add by jiyx at 2012-9-17 11:05:15
	 * @param context
	 * @param
	 * @return
	 */
//	public static boolean isFirstDownload(Context context, String fName) {
//		DownloadDao dao = new DownloadDao(context);
//		String fileName = fName;
//		return dao.isFirst(fileName);
//	}

	
	public static boolean  getFirstStartForceUpdateVaule(Context context) {
		   if (null == context)
			   return false;
		   SharedPreferences sharedPreferences = context.getSharedPreferences(FORCE_UPDATE, context.MODE_PRIVATE);
		   return sharedPreferences.getBoolean(CACHE_UPDATE, false);
	   }
	   
		 public static void setIsFirstStartForceUpdate (boolean flag ,Context context) {
			   if (null == context)
				   return;
			   SharedPreferences  sharedPreferences = context.getSharedPreferences(FORCE_UPDATE, context.MODE_PRIVATE );
			   Editor edit = sharedPreferences.edit();
			   edit.putBoolean(CACHE_UPDATE, flag);
			   edit.commit();
		   } 
		 
			
		 /**
		  * 判断用户输入的内容是是否为数字
		  * add by zhangshuo
		  * 2013-5-16下午2:11:02
		  * @param str
		  * @return
		  */
	public static boolean isNumber(String str) {
		try {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher match = pattern.matcher(str);
			return match.matches();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断用户输入是否为邮箱帐号
	 * @param str
	 * @return
	 */
	public static boolean isMailAddress (String str) {
		try {
			Pattern pattern = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
	        Matcher matcher = pattern.matcher(str);
	        return matcher.matches();
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断是否是qq号
	 * @param str
	 * @return
	 */
	public static boolean isQQNumber (String str) {
		try {
			int length = str.length();
			return ((!str.startsWith("0"))&&isNumber(str) && (length >= 5 && length < 11));
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断是否是电话号码
	 * @param str
	 * @return
	 */
	public static boolean isPhoneNumber (String str) {
		try {
			Pattern pattern = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
			Matcher matcher = pattern.matcher(str);
			return (isNumber(str) &&str.length() == 11 &&  matcher.matches());
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @author donggx
	 * 
	 * @param view
	 * 
	 * http://stackoverflow.com/questions/4611822/java-lang-outofmemoryerror-bitmap-size-exceeds-vm-budget
	 */
	public static void unbindDrawables(View view) {
		try {
			if (null == view) {
				return;
			}
			if (view.getBackground() != null) {
				view.getBackground().setCallback(null);
			}
			if (view instanceof ViewGroup) {
				for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
					unbindDrawables(((ViewGroup) view).getChildAt(i));
				}
				((ViewGroup) view).removeAllViews();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getExternalStoragePath() {
		boolean bExists = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		String path = null;
		if (bExists) {
			path = Environment.getExternalStorageDirectory().getAbsolutePath();

			if (path == null) {
				return "/";
			}

			if ("/mnt/flash".equalsIgnoreCase(path)) {
				path = "/mnt/sdcard";
				File file = new File(path);
				if (!file.exists()) {
					path = "/sdcard";
					file = new File(path);
					if (!file.exists()) {
						path = "/";
					}
				}
			}

			return path;
		} else {
			return "/";
		}
	}

	public static String getMntPath() {
		String mntPath;
		boolean bExists = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		if (bExists) {
			mntPath= Environment.getExternalStorageDirectory().getAbsolutePath();
		}else{
			mntPath = "/";
		}
		
		if (!mntPath.endsWith(File.separator)) {   
			mntPath = mntPath + File.separator;   
	    }

		if(!"/".equals(mntPath) && mntPath.lastIndexOf("/") != -1){
			mntPath = mntPath.substring(0, mntPath.lastIndexOf("/")+1);
		}
		
		return mntPath;
	}
	
    public static void sendMsg(Handler handler, int what) {
        Message message = new Message();
        message.what = what;
        handler.sendMessage(message);
    }

    public static void sendMsg(Handler handler, int what, int arg1) {
        Message message = new Message();
        message.what = what;
        message.arg1 = arg1;
        handler.sendMessage(message);
    }

    public static void sendMsg(Handler handler, int what, int arg1, int arg2) {
        Message message = new Message();
        message.what = what;
        message.arg1 = arg1;
        message.arg2 = arg2;
        handler.sendMessage(message);
    }

    public static void sendMsgDelay(Handler handler, int what, Object obj, long delay) {
        Message message = new Message();
        message.what = what;
        message.obj = obj;
        handler.sendMessageDelayed(message, delay);
    }

    public static void sendMsg(Handler handler, int what, Object obj) {
        Message message = new Message();
        message.what = what;
        message.obj = obj;
        handler.sendMessage(message);
    }

    public static void sendMsg(Handler handler, int what, int arg1, int arg2, Object obj) {
        Message message = new Message();
        message.what = what;
        message.arg1 = arg1;
        message.arg2 = arg2;
        message.obj = obj;
        handler.sendMessage(message);
    }

    public static void sendMsg(Handler handler, int what, int arg1, Object obj) {
        Message message = new Message();
        message.what = what;
        message.arg1 = arg1;
        message.obj = obj;
        handler.sendMessage(message);
    }
    
    /**
     * 实现震动效果  add by zhangshuo
     * @param context  需要获取系统服务的上下文对象
     * @param pattern  震动重复的参数 时间间隔 OFF/ON/OFF/ON
     * @param repeatCount 重复的次数 -1为不重复 0为一直重复震动
     */
    public static void vibrationEffect (Context context ,long[]pattern,int repeatCount) {
    	//获取震动服务对象
    	Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
    	if (null != vibrator) {
    		vibrator.vibrate(pattern, repeatCount);
    	}
    }
}
