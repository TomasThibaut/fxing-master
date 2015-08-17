package cn.gc.fxing.util;

import android.content.Context;

/**
 * Created by 宫成 on 15/8/10.
 * 常量类;
 * 包括接口数据
 */
public class Constants {

    public static Context context;
    public static final String BAST_URL = "http://jsonfe.funshion.com/";
    public static final int MIRCOVIDEOPAGENUM = 88;

    /**精选请求焦点图链接*/
    public static final String GET_GALLERY_FLOW_URL = BAST_URL + "?cli="
            + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context)+"&src=focus";
    //  http://jsonfe.funshion.com/?cli=aphone&ver=1.5.5.1&sid=sidUnknown&src=focus

    /**运营图片请求链接*/
    public static final String GET_SPREAD_GALLERY_FLOW_URL = BAST_URL + "?cli="
            + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context)+"&src=promote";

    /**热门应用请求链接*/
    public static final String GET_HOTAPP_URL = BAST_URL+"aconf/?cli=" + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context);

    /** 请求内容块媒体链接 */
    public static final String GET_FEATURED_CONTENT_URL = BAST_URL + "?cli="
            + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context)+"&src=media";

    /** 请求媒体页数据 */

    public static final String GET_MEDIA_DATA_URL = BAST_URL + "media/?cli="
            + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context)+"&mid=";

    /** 请求相关视频页数据 */

    public static final String GET_RELATIVE_VIDEO_URL = BAST_URL + "relate/media/?cli="
            + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context)+"&page=1&pagesize=16&mid=";

    /** 请求直播秀节目详情页数据 */

    public static final String GET_PROGRRAM_DETAIL_URL = BAST_URL + "liveshow/?cli="
            + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context)+"&src=detail"+"&programid=";

    /** 媒体分类链接 */

    public static final String GET_MEDIA_INDEX_DATA_URL = BAST_URL
            + "list/tags?cli=" + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context)
            + "&sid="+SIDConstant.getSID(context)+"&type=";

    /**长视频请求数据url*/
    public static String longMediaUrl = Constants.BAST_URL + "list/?cli=" + Utils.CLIENT
            + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context) + "&pagesize=16&page=1&udate=week&type=";

    /**小视频请求数据url*/
    public static String shortMediaUrl = Constants.BAST_URL + "video/?cli="+ Utils.CLIENT
            + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context) + "&pagesize=20&page=1&date=day&videotype=";

    /**小视频专题请求数据url*/
    public static String shortMediaSpecialUrl = Constants.BAST_URL + "video/special?cli="+ Utils.CLIENT
            + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&ta="+"&sid="+SIDConstant.getSID(context) + "&page=1&pagesize="+MIRCOVIDEOPAGENUM+"&videoid=";

    /**直播节目数据请求url*/
    public static String liveMediaUrl = BAST_URL + "liveshow/?" + "cli="
            + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) + "&sid="
            + SIDConstant.getSID(context)+"&src=index&type=program&page=1&pagesize=10";

    /** 请求频道页数据 */
    public static  String GET_CONTENT_DATA_URL = BAST_URL + "list/?cli="
            + Utils.CLIENT + "&ver="+ DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context)+"&type=";

    /**
     * 娱乐请求数据
     */
    public static final String GET_ENTERTAINMENT_DATA_URL = BAST_URL+"video/?cli="+
            Utils.CLIENT+"&ver="+DeviceInfoUtil.getAppVersionName(context)+"&sid="+SIDConstant.getSID(context);

    public static final String GET_ENTERTAINMENT_INDEX_DATA_URL = BAST_URL+"video/tags?cli="+
            Utils.CLIENT+"&ver="+DeviceInfoUtil.getAppVersionName(context)+"&sid="+SIDConstant.getSID(context);

    /**请求精彩评论数据*/
    public static final String GET_MEDIA_COMMENT_DATA_URL = BAST_URL+"vote/?cli="+
            Utils.CLIENT+"&ver="+DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context)+"&votetype=" + "mediawonder";

    /** Used to identify search the contents of the request path */

    public static final String GET_SEARCH_CONTENT_URL = BAST_URL
            + "search/?cli=" + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context)+"&type=all"+"&key=";

    public static final String GET_SEARCH_VIDEO_CONTENT_URL = BAST_URL
            + "search/video?";

    public static final String GET_RELATIVE_VIDEO_CONTENT_URL = BAST_URL
            + "relate/video/?";

    /**
     * 搜索微视频
     */
    public static final String GET_MICRO_VIDEO_URL = GET_SEARCH_VIDEO_CONTENT_URL+"pagesize=16&page=1"+"&cli="+ Utils.CLIENT+"&ta="
            +"&ver="+ DeviceInfoUtil.getAppVersionName(context)+"&key=";

    /** Used to identify the search for heat request path */
    // http://192.168.130.8/search/tip?cli=android&ver=1.0.1&key=

    public static final String GET_SEARCH_KEY_URL = BAST_URL
            + "search/tip?cli=" + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context)
            + "&sid="+SIDConstant.getSID(context)+"&key=";

    /** Used to identify the media layer of the request path */

    public static final String GET_RANK_DATA_URL = BAST_URL + "rank?cli="
            + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context)+ "&mtype=$1&rank=$2";

    public static final String GET_MEDIA_PICTURES_DATA_URL = BAST_URL + "picture/?cli="
            + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context)+ "&pictype=" + "still";
    //http://jsonfe.funshion.com/user/platform?cli=$cli&ver=$ver&ta=地域信息&[sid=$sid|jk=$jk]
    public static final String GET_LOGINABLE_LIST_URL = BAST_URL + "user/platform?cli=" + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) + "&ta="+"&sid="+SIDConstant.getSID(context);

    //http://jsonfe.funshion.com/user/login?cli=$cli&ver=$ver&ta=地域信息&[sid=$sid|jk=$jk]&username=$username&userpwd=$userpwd&plat=$plat
    public static final String LOGIN_REQUEST_URL = BAST_URL + "user/login?cli=" + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&ta="+"&sid="+SIDConstant.getSID(context);
    //http://jsonfe.funshion.com/user/tiedlist?cli=$cli&ver=$ver&ta=地域信息&[sid=$sid|jk=$jk]&sso_token=$sso_token
    public static final String GET_LOGIN_THIRD_BOUND_LIST_URL = BAST_URL + "user/tiedlist?cli=" + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&ta="+"&sid="+SIDConstant.getSID(context);

    /**
     * 请求节目看点标签数据  add by donggx
     */
    public static final String GET_PROGRAM_WATCH_FOCUSTAG_URL = BAST_URL + "liveshow/?"
            +"cli=" + Utils.CLIENT
            +"&ver=" + DeviceInfoUtil.getAppVersionName(context)
            +"&sid="+ SIDConstant.getSID(context)
            +"&src=focustag"
            +"&programid=";


    /**
     * 请求节目看点数据  add by donggx  
     */
    public static final String GET_PROGRAM_WATCH_FOCUS_URL = BAST_URL + "liveshow/?"
            +"cli=" + Utils.CLIENT
            +"&ver=" + DeviceInfoUtil.getAppVersionName(context)
            +"&sid="+SIDConstant.getSID(context)
            +"&src=focus"
            +"&programid=";

    /**
     * 请求直播秀节目页的url
     */
    public static final String GET_PRGRAM_PAGE_URL = BAST_URL + "liveshow/?"
            + "cli=" + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) + "&sid="
            + SIDConstant.getSID(context) + "&src=program";

    /**
     * Get the live program's url
     */
    public static final String GET_PRGRAM_URL= BAST_URL + "liveshow/?"+ "cli=" + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) + "&sid="
            + SIDConstant.getSID(context)+"&src=index&type=program&";

    /**
     * Get the tv list url
     */
    public static final String GET_TV_URL = BAST_URL + "liveshow/?"+ "cli=" + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) + "&sid="
            + SIDConstant.getSID(context)+"&src=index&type=tvstation&page=1&pagesize=50";

    /**
     * Get user comment list
     */
    public static final String GET_USER_COMMENT_URL = BAST_URL + "liveshow/?"+ "cli=" + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) + "&sid="
            + SIDConstant.getSID(context)+"&src=comment&";

    /**精选请求焦点图链接*
     *
     * add by xj
     */
    public static final String GET_BUSSINISS_GALLERY_FLOW_URL = BAST_URL + "?cli="
            + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&sid="+SIDConstant.getSID(context)+"&src=promote";

    /**
     * Get the meaid live url
     * add by donggx
     */
    public static final String GET_MEDIAL_LIVE_URL = BAST_URL +"tool/medialive"+ "?cli="
            + Utils.CLIENT + "&ver=" + DeviceInfoUtil.getAppVersionName(context) +"&mediaid=";


    /**
     * 请求手机精选接口       
     * Get the phonemedia url
     * add by donggx
     *
     */
    public static final String GET_PHONEMEDIA_URL = BAST_URL + "?cli=" + Utils.CLIENT + "&ver="
            + DeviceInfoUtil.getAppVersionName(context) +"&sid="
            + SIDConstant.getSID(context) +"&src=phonemedia";

    public static final String GET_USER_POINT_URL = BAST_URL + "score/get_media_score";


    public static final String GET_PLAYINFO_LISTE_URL = BAST_URL +"playinfo?"+ "cli="
            + Utils.CLIENT+ "&ver=" + DeviceInfoUtil.getAppVersionName(context)+"&ta=&sid="+SIDConstant.getSID(context) +"&mid=";

    /**
     * 电影，电视剧，综艺，动漫大类接口
     */
    public static final String[] Movie_DEFAULT_URL_ARRAY = {
            "http://jsonfe.funshion.com/list/?cli=aphone&ver=1.5.5.1&sid=0002&pagesize=16&page=1&udate=week&type=movie&order=pl",
            "http://jsonfe.funshion.com/list/?cli=aphone&ver=1.5.5.1&sid=0002&pagesize=16&page=1&udate=week&type=tv&order=pl",
            "http://jsonfe.funshion.com/list/?cli=aphone&ver=1.5.5.1&sid=0002&pagesize=16&page=1&udate=week&type=variety&order=pl",
            "http://jsonfe.funshion.com/list/?cli=aphone&ver=1.5.5.1&sid=0002&pagesize=16&page=1&udate=week&type=cartoon&order=pl"
    };

    //目录接口
    public static final String[] MOVIE_CATEGORY_URL_ARRAY = {
            "http://jsonfe.funshion.com/list/tags?cli=aphone&ver=1.5.5.1&sid=0002&type=movie",
            "http://jsonfe.funshion.com/list/tags?cli=aphone&ver=1.5.5.1&sid=0002&type=tv",
            "http://jsonfe.funshion.com/list/tags?cli=aphone&ver=1.5.5.1&sid=0002&type=variety",
            "http://jsonfe.funshion.com/list/tags?cli=aphone&ver=1.5.5.1&sid=0002&type=cartoon"
    };

    //娱乐和体育默认url
    public static final String[] ENTERTAIN_SPORT_DEFAULT_ARR = {
            "http://jsonfe.funshion.com/video/?cli=aphone&ver=1.5.5.1&sid=0002&pagesize=20&videotype=entertainments&cate=all&date=month&page=1&order=pl",
            "http://jsonfe.funshion.com/video/?cli=aphone&ver=1.5.5.1&sid=0002&pagesize=20&videotype=sport&cate=all&date=month&page=1&order=pl"
    };

    //娱乐和体育目录url
    public static final String[] ENTERTAINMENT_OR_SPORTS_CATEGORY = {
            "http://jsonfe.funshion.com/video/tags?cli=aphone&ver=1.5.5.1&sid=0002&videotype=entertainments",
            "http://jsonfe.funshion.com/video/tags?cli=aphone&ver=1.5.5.1&sid=0002&videotype=sport"
    };



    public static final String MOVIE_ORDER_BASE = "http://jsonfe.funshion.com/list/?cli=aphone&ver=1.5.5.1&sid=0002&pagesize=16&page=1&udate=week";

    public static final String CATEGORY_BASE = "http://jsonfe.funshion.com/list/?cli=aphone&ver=1.5.5.1&sid=0002&";

    String dd = "http://jsonfe.funshion.com/list/?cli=aphone&ver=1.5.5.1&sid=0002" +
            "&type=movie&pagesize=16&cate=all&region=all&rdate=all&karma=all&udate=month&page=1&order=pl";
    String ff = "http://jsonfe.funshion.com/video/?cli=aphone&ver=1.5.5.1&sid=0002&pagesize=20&videotype=entertainments&cate=all&date=month&page=1&order=pl";
}
