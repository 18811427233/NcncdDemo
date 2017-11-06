package cn.miao.ncncd.okhttp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangzhuang on 17/10/17.
 */

public class OkHttpUtil {

    public static void sendGet(String url , HttpCallBack httpCallBack){

        BaseHttpUtil.okHttpGet(url , null, httpCallBack);
    }

    public static void sendGet (String url , Object paramers , HttpCallBack httpCallBack){

//        paramers to map

        Map map = new HashMap();

        BaseHttpUtil.okHttpGet(url , map , httpCallBack);
    }
}
