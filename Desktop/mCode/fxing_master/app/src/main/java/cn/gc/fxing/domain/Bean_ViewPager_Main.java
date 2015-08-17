package cn.gc.fxing.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by 宫成 on 15/8/11.
 * 首页轮播图的bean
 */
public class Bean_ViewPager_Main {

    public ArrayList<DataEntity> data;
    @SerializedName("return")
    public String returnX;

    public static class DataEntity {
        /**
         * adword : 火爆 、玩命、孤胆豪杰 ，范•迪塞尔狂拽酷炫大破惊天危机
         * program_type : 0
         * timelength :
         * mpurls : {}
         * picurl : http://img1.funshion.com/pictures/pocket/201507/0dfd9b0915d.jpg
         * vtype :
         * type : mobile_media
         * durl :
         * type_id : 931
         * upinfo :
         * title : 极限特工
         * purl :
         * mid : 931
         */
        public String adword;
        public String program_type;
        public String timelength;
        public MpurlsEntity mpurls;
        public String picurl;
        public String vtype;
        public String type;
        public String durl;
        public String type_id;
        public String upinfo;
        public String title;
        public String purl;
        public String mid;

        public static class MpurlsEntity {
        }
    }
}
