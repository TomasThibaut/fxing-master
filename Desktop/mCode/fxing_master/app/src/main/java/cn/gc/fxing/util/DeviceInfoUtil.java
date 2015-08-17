package cn.gc.fxing.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 
 * @author yanggf
 * 
 */

public class DeviceInfoUtil {
	private final static String TAG = "DeviceInfoUtil";
	
	private final static String UNKNOWN_MAC_ADDRESS = "00:00:00:00:00:00";
	private final static String CPUINFO_PATH = "/proc/cpuinfo";
	private final static int FILE_SIZE = 8192;
	private final static int BASE_SIZE = 1024;
	private final static String TYPE_APHONE = "aphone";
	private final static String TYPE_APAD = "apad";
	private final static int TYPE_TOTAL = 1;
	private final static int TYPE_AVAIABLE = 2;
	private final static String MAK_KEK = "mac_key";
	private final static String MAK_VALUE = "mac_value";
	
	public final static  String UNKNOWN_VERSION = "versionUnknown";
	
	private static String mAppVersion = "versionUnknown";
	private static Context mContext = null;
	private static String mCpuInfo = "";
	private static String []mDeviceInfo = null; 
	private static String mOSVersion = "";
	private static String mDeviceType = TYPE_APHONE;
	private static String mDeviceModel = "";
	private static String mMacAddress = UNKNOWN_MAC_ADDRESS;
	private static String mIpAddress = "";
	private static String mDeviceManufacturer = "";
	private static String mUploadDeviceInfo = "";
	private static String mReportDeviceInfo;
	private static float mDensity = 0;
	
	public final static int DPI_DIVIDER = 320;
	
	public final static int DEVICE_WIDTH_240X320 = 240;
	public final static int DEVICE_WIDTH_320X480 = 320;
	public final static int DEVICE_WIDTH_480X800 = 480;
	public final static int DEVICE_WIDTH_640X960 = 640;
	public final static int DEVICE_WIDTH_720X1280 = 720;
	public final static int DEVICE_WIDTH_800X1280 = 800;

//	private static int mCpuArch = PlayerConstant.CPU_ARCHITECTURE_ARMV6;
//	private static int mCpuType = PlayerConstant.CPU_FEATURES_ARMV6_VFP;
	private static long mFirstInstallTime = -1;
	
//	public static void initCPUInfo() {
////		String cpuInfo = getCPUInfos();
//		
//		if (cpuInfo != null) {/*
//			switch (mCpuArch) {
//			case PlayerConstant.CPU_ARCHITECTURE_ARMV5:
//				mCpuType = PlayerConstant.CPU_FEATURES_ARMV5;
//				break;
//			case PlayerConstant.CPU_ARCHITECTURE_ARMV6:
//				if (cpuInfo.contains("vfp")) {
//					mCpuType = PlayerConstant.CPU_FEATURES_ARMV6_VFP;
//				} else {
//					mCpuType = PlayerConstant.CPU_FEATURES_ARMV6;
//				}
//				break;
//			case PlayerConstant.CPU_ARCHITECTURE_ARMV7:
//				if (cpuInfo.contains("neon")) {
//					mCpuType = PlayerConstant.CPU_FEATURES_ARMV7_NEON;
//				} else if (cpuInfo.contains("vfpv3")) {
//					mCpuType = PlayerConstant.CPU_FEATURES_ARMV7_VFP3;
//				} else {
//					mCpuType = PlayerConstant.CPU_FEATURES_ARMV7_VFP;
//				}
//				break;
//			case PlayerConstant.CPU_ARCHITECTURE_OTHERS:
//				mCpuType = PlayerConstant.CPU_FEATURES_OTHERS;
//				break;
//			default:
//				mCpuType = PlayerConstant.CPU_FEATURES_ARMV6_VFP;
//				break;
//			}
//		*/} else {
////			mCpuType = PlayerConstant.CPU_FEATURES_ARMV6_VFP;
//		}
//	}

//	public static int getCpuType(){
//		return mCpuType;
//	}
	
//	private static String getCPUInfos() {
//		if(StringUtil.isEmpty(mCpuInfo)){
//			String str = "";
//			StringBuilder stringBuilder = new StringBuilder();
//			try {
//				FileReader fr = new FileReader(CPUINFO_PATH);
//				BufferedReader reader = new BufferedReader(fr,FILE_SIZE);
//				while ((str = reader.readLine()) != null) {
//					if( str != null && str.contains("CPU architecture")){
//						String[] strArray=str.split(":",2);
//						
//						LogUtil.i(TAG, "CPU architecture is : " + strArray[1]);
//						
//						if(strArray[1].contains("5")){
//							mCpuArch = PlayerConstant.CPU_ARCHITECTURE_ARMV5;
//						}else if(strArray[1].contains("6")){
//							mCpuArch = PlayerConstant.CPU_ARCHITECTURE_ARMV6;
//						}else if(strArray[1].contains("7")){
//							mCpuArch = PlayerConstant.CPU_ARCHITECTURE_ARMV7;
//						}else{
//							mCpuArch = PlayerConstant.CPU_ARCHITECTURE_OTHERS;
//						}
//					}
//					
//					stringBuilder.append(str);
//				}
//				if (reader != null) {
//					reader.close();
//				}
//				if (stringBuilder != null) {
//					mCpuInfo = stringBuilder.toString();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return mCpuInfo;
//	}
	
	private static String[] getDeviceInfo() {
		if(null == mDeviceInfo || mDeviceInfo.length<=0/*||StringUtil.isEmpty(mDeviceInfo[0])*/){
			mDeviceInfo = new String[]{"",""};
			String str = "";
			try{
				FileReader fr = new FileReader(CPUINFO_PATH);
				BufferedReader bufferedReader = new BufferedReader(fr,FILE_SIZE);
				str = bufferedReader.readLine();
				String []array = str.split("\\s+");
				for(int i = 2;i < array.length;i++){
					mDeviceInfo[0] += array[i]+" ";
				}
				str = bufferedReader.readLine();
				mDeviceInfo[1] += array[2];
				bufferedReader.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return mDeviceInfo;
	}

	public static String getDeviceCPUInfo() {
		String cpuType = "";
		String[] cpuInfo = getDeviceInfo();
		if (cpuInfo != null && cpuInfo.length > 0) {
			cpuType = cpuInfo[0];
		}
		return cpuType;
	}

	/**
	 * Get android system version number
	 * @param context
	 * @return
	 */
	public static String getOSVersion() {
//		if(StringUtil.isEmpty(mOSVersion)){
			mOSVersion = Build.VERSION.RELEASE;
//		}
		return mOSVersion;
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	public static int getOSVersionSDKINT(Context context) {
		int currentapiVersion = Build.VERSION.SDK_INT;
		return currentapiVersion;
	}
	
	public static String getDeviceType(Context context) {
		if(context != null && context instanceof Activity){
			DisplayMetrics dm = new DisplayMetrics();
			((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
			double x = Math.pow(dm.widthPixels, 2);
			double y = Math.pow(dm.heightPixels, 2);
			double screenInches = Math.sqrt(x + y) / (160 * dm.density);
			if (2 < screenInches && 5 > screenInches) {
				mDeviceType = TYPE_APHONE;
			} else {
				mDeviceType = TYPE_APAD;
			}
		}
		return mDeviceType;
	}
	
	public static String getDeviceModel() {
		if(TextUtils.isEmpty(mDeviceModel)){
			try {
				mDeviceModel = URLEncoder.encode(Build.MODEL,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mDeviceModel;
	}
	
	private static void setCacheMacAddress(Context context, String macAddress) {
		if (context != null&&!TextUtils.isEmpty(macAddress)) {
			SharedPreferences preference = context.getSharedPreferences(MAK_KEK, Context.MODE_PRIVATE);
			Editor editor = preference.edit();
			editor.putString(MAK_VALUE, macAddress);
			editor.commit();
		}
	}
	
	private static String getCacheMacAddress(Context context) {
		if (context == null) {
			return null;
		}
		SharedPreferences sharedPreferences = context.getSharedPreferences(MAK_KEK, Context.MODE_PRIVATE);
			return sharedPreferences.getString(MAK_VALUE, null);
	}
	public static String getMacAddress(Context context) {

//		if (context == null) {
//			context = FunshionAndroidApp.getInstance();
//		}
		if ((!UNKNOWN_MAC_ADDRESS.equals(mMacAddress)) || (context == null)) {
			return mMacAddress;
		}
		try {
			WifiManager wifi = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wifi.getConnectionInfo();
			if (info != null && !TextUtils.isEmpty(info.getMacAddress())) {
				setCacheMacAddress(context, info.getMacAddress());
				return (mMacAddress = info.getMacAddress());
			} else {
				String mac = getCacheMacAddress(context);
				if (!TextUtils.isEmpty(mac)) {
					return (mMacAddress = mac);
				}else{
					// "AF"标识来自android客户端的mac地址
					String randomMacAddress = "";//TextUtils.substringAndAddPrefix(TextUtils.getMD5Str(String.valueOf(System.currentTimeMillis())), 10, "AF");
					if(!TextUtils.isEmpty(randomMacAddress)){
						 setCacheMacAddress(context, randomMacAddress);
						return randomMacAddress;
					}else{
						return mMacAddress;
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return mMacAddress;

	}
//	public static String getIpAddress() {
//		if(StringUtil.isEmpty(mIpAddress)){
//			try {
//				for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
//					NetworkInterface intf = en.nextElement();
//					for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
//						InetAddress inetAddress = enumIpAddr.nextElement();
//						if (!inetAddress.isLoopbackAddress()) {
//							mIpAddress = inetAddress.getHostAddress().toString();
//						}
//					}
//				}
//			} catch (SocketException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return mIpAddress;
//	}

	/**
	 * Get total memory of the device (including mobile phone memory and theSDcard Memory)
	 */
	public static double getDeviceMemory() {
		double totalDeviceMemory = 0;
		try {
			double mTotalInternalMemory = getTotalInternalMemory();
			double mTotalExternaMemory = getTotalExternaMemory();
			if (mTotalInternalMemory == mTotalExternaMemory) {
				totalDeviceMemory = mTotalInternalMemory;
			} else {
				totalDeviceMemory = (mTotalInternalMemory + mTotalExternaMemory);
			}
			return totalDeviceMemory;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalDeviceMemory;

	}

	/**
	 * Get equipment available total memory (including mobile phone memory andthe SDcard Memory)
	 */
	public static double getDeviceAvailableMemory() {
		double totalDeviceAvailableMemory = 0;
		try {
			double mAvailableInternalMemory = getAvailableInternalMemory();
			double mAvailableExternalMemory = getAvailableExternalMemory();
			if (mAvailableInternalMemory == mAvailableExternalMemory) {
				totalDeviceAvailableMemory = mAvailableInternalMemory;
			} else {
				totalDeviceAvailableMemory = (mAvailableInternalMemory + mAvailableExternalMemory);
			}
			return totalDeviceAvailableMemory;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalDeviceAvailableMemory;
	}
	
	private static double getInternalMemory(int type){
		double memorySize = 0;
		try {
			File path = Environment.getDataDirectory();
			StatFs internalStat = new StatFs(path.getPath());
			long blockSize = internalStat.getBlockSize();
			long internalBlocks = (TYPE_TOTAL == type)?internalStat.getBlockCount():internalStat.getAvailableBlocks();
			memorySize = (blockSize * internalBlocks)/ (BASE_SIZE * BASE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memorySize;
	}
	
	/**
	 * Get a total cell phone memory
	 */
	public static double getTotalInternalMemory() {
		return getInternalMemory(TYPE_TOTAL);
	}

	/**
	 * Get the available phone memory
	 */
	public static double getAvailableInternalMemory() {
		return getInternalMemory(TYPE_AVAIABLE);
	}

	private static double getExternalMemory(int type){
		double memorySize = 0;
		try{
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				File path = Environment.getExternalStorageDirectory();
				StatFs externaStat = new StatFs(path.getPath());
				long blockSize = externaStat.getBlockSize();
				long totalBlocks  = (TYPE_TOTAL == type)?externaStat.getBlockCount():externaStat.getAvailableBlocks();			
				memorySize = (blockSize * totalBlocks)/ (BASE_SIZE * BASE_SIZE);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return memorySize;
	}
	
	private static double getUsedMedmory () {
		double usedSize = 0;
		try {
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) { 
				String path = Environment.getExternalStorageDirectory().getPath();
				StatFs statFs = new StatFs(path);
				long blockSize = statFs.getBlockSize();
				long totalBlocks = statFs.getBlockCount();
				long availableBlocks = statFs.getAvailableBlocks();
				long usedBlocks = totalBlocks-availableBlocks;
				usedSize = (blockSize*usedBlocks)/(BASE_SIZE*BASE_SIZE);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
        return usedSize;
	}
	
	
	
	/**
	 * The total memory of the mobile phone SDcard external
	 */
	public static double getTotalExternaMemory() {
		return getExternalMemory(TYPE_TOTAL);
	}
	
	public static  double getUsedMemory () {
		return getUsedMedmory();
	}

	/**
	 * Phone the SDcard external available memory (free space)
	 */
	public static double getAvailableExternalMemory() {
		return getExternalMemory(TYPE_AVAIABLE);
	}
	
	/**
	 * 方法二：动态获取应用程序的版本名称
	 */
	public static String getAppVersionName(Context context) {
		mContext = context;
		setVersionNameFromPackage();
		return mAppVersion;
	}
	
	private static void setVersionNameFromPackage() {
		if (!isNeedToSetVersionNumber()) {
			return;
		}
		try {
			PackageManager manager = mContext.getPackageManager();
			PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
			if (info != null && !TextUtils.isEmpty(info.versionName)) {
				mAppVersion = info.versionName;
			}
		} catch (Exception e) {
			e.printStackTrace();
			mAppVersion = UNKNOWN_VERSION;
		}
	}
	
	private static boolean isNeedToSetVersionNumber() {
		return ((UNKNOWN_VERSION.equals(mAppVersion)) && (mContext != null) );
	}
	
	public static String getDeviceManufacturer() {
		if(TextUtils.isEmpty(mDeviceManufacturer)){
			try {
				mDeviceManufacturer = URLEncoder.encode(Build.MANUFACTURER, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mDeviceManufacturer;
	}
	
	/**
	 * 获得设备屏幕密度
	 */
	public static float getDeviceScreenDensity(Context context){
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.density;
	}
	/**
	 * According to the resolution width of the phone
	 */
	public static int getWidthPixels(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 获得设备的横向dpi
	 */
	public static float getWidthDpi(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getApplicationContext().getResources().getDisplayMetrics();
		return dm.densityDpi;
	}
	
	/**
	 * According to phone resolution height
	 */
	public static int getHeightPixels(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
	
	/**
	 * According to the resolution of the phone from the dp unit will become a
	 * px (pixels)
	 */
	public static int dip2px(Context context, float dpValue) {		
		if(Math.abs(mDensity-0)<0.0001){
			mDensity = context.getResources().getDisplayMetrics().density;
		}
		return (int)(dpValue*mDensity + 0.5f);
	}

	public static int dipToPx(Context context, float dpValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	/**
	 * Turn from the units of px (pixels) become dp according to phone
	 * resolution
	 */
	public static int px2dip(Context context, float pxValue) {
		if(Math.abs(mDensity-0)<0.0001){
			mDensity = context.getResources().getDisplayMetrics().density;
		}
		return (int)(pxValue/mDensity + 0.5f);
	}
	
	/**
	 * 获取设备详细信息
	 * @return
	 */
	public static String getDeviceInfo(Context context) {
		// 获取用户设备类型详细信息当前操作系统版本设备型号上报
		if(TextUtils.isEmpty(mUploadDeviceInfo) && null != context){
			String device = DeviceInfoUtil.getDeviceType(context) + "_"
					+ DeviceInfoUtil.getOSVersion() + "_" + DeviceInfoUtil.getDeviceModel();
			mUploadDeviceInfo = device + "&mac=" + DeviceInfoUtil.getMacAddress(context)
					+ "&ver=" + DeviceInfoUtil.getAppVersionName(context) + "&nt="
					/*+ NetworkUtil.reportNetType(context)*/;
		}
		return mUploadDeviceInfo;
	}
	
	/**
	 * 涓婃姤鐨勮澶囦俊鎭	 * @param context
	 */
	public static String getReportDeviceInfo(Context context){
		if(TextUtils.isEmpty(mReportDeviceInfo)){
			mReportDeviceInfo = DeviceInfoUtil.getDeviceType(context)
				+ "_" + DeviceInfoUtil.getOSVersion()
				+ "_" + DeviceInfoUtil.getDeviceModel();
		}
		return mReportDeviceInfo;
	}
	
	public static String getPhoneImei(Context context){
		String phoneImei = "";
		String UNKNOWN_IMEI = "ImeiUnknown";
		try { 
			TelephonyManager telephonyManager = null;
			telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			Log.i(TAG, "after get system service.");
			if( null != telephonyManager ) {
				phoneImei = telephonyManager.getDeviceId();
				Log.i(TAG, "mPhoneImei = " + phoneImei);
				if(phoneImei == null){
					phoneImei = UNKNOWN_IMEI;
				}
			}
		} catch (Exception e) { 
			e.printStackTrace(); 
			phoneImei = UNKNOWN_IMEI; 
			Log.i(TAG, "in the catch.");
		} 
		return phoneImei;
	}
	
	public static int getDifferentScreenOffsetX(Context ctx) {
		if(null == ctx){
			return 0;
		}
		int deviceWidthPixels = getHeightPixels(ctx);
		if ( deviceWidthPixels > 0 && deviceWidthPixels <= DeviceInfoUtil.DEVICE_WIDTH_320X480) {
			return -4;
		}else if (deviceWidthPixels > DeviceInfoUtil.DEVICE_WIDTH_320X480 && deviceWidthPixels <= DeviceInfoUtil.DEVICE_WIDTH_480X800) {
			return 12;
		} else if (deviceWidthPixels > DeviceInfoUtil.DEVICE_WIDTH_480X800 && deviceWidthPixels <= DeviceInfoUtil.DEVICE_WIDTH_720X1280) {
			return 10;
		} else if (deviceWidthPixels > DeviceInfoUtil.DEVICE_WIDTH_720X1280 && deviceWidthPixels <= DeviceInfoUtil.DEVICE_WIDTH_800X1280) {
			return 8;
		} else {
			return 8;
		}
	}
	
	public static long getFirstInstallTime(){
		try {
			if(mFirstInstallTime <= 0 && Build.VERSION.SDK_INT >= 9){
//				PackageInfo pi =context.getPackageManager().
//						getPackageInfo(context.getPackageName(), 0);	
//				mFirstInstallTime = pi.firstInstallTime/1000;				 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mFirstInstallTime;
	}
}
