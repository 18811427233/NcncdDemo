package cn.miao.ncncd.okhttp;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangzhuang on 17/10/17.
 */

public class OkHttpUtil {

    public static void sendGet(String url, HttpCallBack httpCallBack) {

        BaseHttpUtil.okHttpGet(url, null, httpCallBack);
    }

    public static void sendGet(String url, Object paramers, HttpCallBack httpCallBack) {

        Map map = ConvertObjToMap(paramers);
        BaseHttpUtil.okHttpGet(url, map, httpCallBack);
    }

    public static void sendPost(String url, Object paramers, HttpCallBack httpCallBack) {

        BaseHttpUtil.okHttpPost(url, paramers, httpCallBack);
    }

    /**
     * 将对象转换成Map集合
     * @param obj
     * @return
     */
    public static Map ConvertObjToMap(Object obj){
        Map<String,Object> reMap = new HashMap<String,Object>();
        if (obj == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for(int i=0;i<fields.length;i++){
                try {
                    Field f = obj.getClass().getDeclaredField(fields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    reMap.put(fields[i].getName(), o);
                } catch (NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reMap;
    }
}
