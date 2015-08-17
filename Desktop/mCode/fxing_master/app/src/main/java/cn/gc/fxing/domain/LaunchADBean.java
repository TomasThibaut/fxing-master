package cn.gc.fxing.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 启动界面广告数据
 * Created by 宫成 on 2015/8/10.
 */
public class LaunchADBean implements Serializable {

    /**
     * ad_list : [{"ad_type":"funshion","material":"http://adm.funshion.com/mat/20150529190430-1934619.jpg","open_type":"inner_web","checksum":"35f50f67d1f4b085ee409c95cb57e964","format":"jpg","link":"http://www.totole.com.cn/","monitor":{"view":[{"provider":"funshion","point":0,"url":"http://pub.funshion.com/interface/monitor?uid=&mac=&fck=&mick=&ap=ape_lp_v1&matid=9030&ad=55189&mid=&impid=738e8610-17c3-11e5-8f99-c370dcc366f00001&tmatid=&order=1&client=&reqId=738e8610-17c3-11e5-8f99-c370dcc366f0"},{"provider":"funshion","point":-1,"url":"http://pub.funshion.com/interface/monitor?uid=&mac=&fck=&mick=&ap=ape_lp_v1&matid=9030&ad=55189&mid=&impid=738e8610-17c3-11e5-8f99-c370dcc366f00001&tmatid=&order=1&client=&reqId=738e8610-17c3-11e5-8f99-c370dcc366f0"}],"click":[{"provider":"funshion","url":"http://pub.funshion.com/interface/click?uid=&mac=&fck=&mick=&ap=ape_lp_v1&matid=9030&ad=55189&mid=&impid=738e8610-17c3-11e5-8f99-c370dcc366f00001&tmatid=&order=1&client=&reqId=738e8610-17c3-11e5-8f99-c370dcc366f0"}]},"position":"0,0,1,1","time":5,"title":""}]
     * ap : ape_lp_v1
     */
    public ArrayList<Ad_listEntity> ad_list;
    public String ap;

    public class Ad_listEntity {
        /**
         * ad_type : funshion
         * material : http://adm.funshion.com/mat/20150529190430-1934619.jpg
         * open_type : inner_web
         * checksum : 35f50f67d1f4b085ee409c95cb57e964
         * format : jpg
         * link : http://www.totole.com.cn/
         * monitor : {"view":[{"provider":"funshion","point":0,"url":"http://pub.funshion.com/interface/monitor?uid=&mac=&fck=&mick=&ap=ape_lp_v1&matid=9030&ad=55189&mid=&impid=738e8610-17c3-11e5-8f99-c370dcc366f00001&tmatid=&order=1&client=&reqId=738e8610-17c3-11e5-8f99-c370dcc366f0"},{"provider":"funshion","point":-1,"url":"http://pub.funshion.com/interface/monitor?uid=&mac=&fck=&mick=&ap=ape_lp_v1&matid=9030&ad=55189&mid=&impid=738e8610-17c3-11e5-8f99-c370dcc366f00001&tmatid=&order=1&client=&reqId=738e8610-17c3-11e5-8f99-c370dcc366f0"}],"click":[{"provider":"funshion","url":"http://pub.funshion.com/interface/click?uid=&mac=&fck=&mick=&ap=ape_lp_v1&matid=9030&ad=55189&mid=&impid=738e8610-17c3-11e5-8f99-c370dcc366f00001&tmatid=&order=1&client=&reqId=738e8610-17c3-11e5-8f99-c370dcc366f0"}]}
         * position : 0,0,1,1
         * time : 5
         * title :
         */
        public String ad_type;
        public String material;
        public String open_type;
        public String checksum;
        public String format;
        public String link;
        public MonitorEntity monitor;
        public String position;
        public int time;
        public String title;

        public class MonitorEntity {
            /**
             * view : [{"provider":"funshion","point":0,"url":"http://pub.funshion.com/interface/monitor?uid=&mac=&fck=&mick=&ap=ape_lp_v1&matid=9030&ad=55189&mid=&impid=738e8610-17c3-11e5-8f99-c370dcc366f00001&tmatid=&order=1&client=&reqId=738e8610-17c3-11e5-8f99-c370dcc366f0"},{"provider":"funshion","point":-1,"url":"http://pub.funshion.com/interface/monitor?uid=&mac=&fck=&mick=&ap=ape_lp_v1&matid=9030&ad=55189&mid=&impid=738e8610-17c3-11e5-8f99-c370dcc366f00001&tmatid=&order=1&client=&reqId=738e8610-17c3-11e5-8f99-c370dcc366f0"}]
             * click : [{"provider":"funshion","url":"http://pub.funshion.com/interface/click?uid=&mac=&fck=&mick=&ap=ape_lp_v1&matid=9030&ad=55189&mid=&impid=738e8610-17c3-11e5-8f99-c370dcc366f00001&tmatid=&order=1&client=&reqId=738e8610-17c3-11e5-8f99-c370dcc366f0"}]
             */
            public ArrayList<ViewEntity> view;
            public ArrayList<ClickEntity> click;

            public class ViewEntity {
                /**
                 * provider : funshion
                 * point : 0
                 * url : http://pub.funshion.com/interface/monitor?uid=&mac=&fck=&mick=&ap=ape_lp_v1&matid=9030&ad=55189&mid=&impid=738e8610-17c3-11e5-8f99-c370dcc366f00001&tmatid=&order=1&client=&reqId=738e8610-17c3-11e5-8f99-c370dcc366f0
                 */
                public String provider;
                public int point;
                public String url;
            }

            public class ClickEntity {
                /**
                 * provider : funshion
                 * url : http://pub.funshion.com/interface/click?uid=&mac=&fck=&mick=&ap=ape_lp_v1&matid=9030&ad=55189&mid=&impid=738e8610-17c3-11e5-8f99-c370dcc366f00001&tmatid=&order=1&client=&reqId=738e8610-17c3-11e5-8f99-c370dcc366f0
                 */
                public String provider;
                public String url;
            }
        }
    }
}
