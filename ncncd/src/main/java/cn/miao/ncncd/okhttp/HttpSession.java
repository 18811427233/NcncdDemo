package cn.miao.ncncd.okhttp;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 网络请封装类
 * Created by zhangzhuang on 17/10/17.
 */

public class HttpSession {

    /**
     * 无参数get请求
     * @param url
     * @param httpCallBack
     */
    public static void sendGet(String url, HttpCallBack httpCallBack) {

        BaseHttp.okHttpGet(url, null, httpCallBack);
    }

    /**
     * 有参数get请求
     * @param url
     * @param paramers
     * @param httpCallBack
     */
    public static void sendGet(String url, Object paramers, HttpCallBack httpCallBack) {

        Map map = ConvertObjToMap(paramers);
        BaseHttp.okHttpGet(url, map, httpCallBack);
    }

    /**
     * post请求
     * @param url
     * @param paramers
     * @param httpCallBack
     */
    public static void sendPost(String url, Object paramers, HttpCallBack httpCallBack) {

        BaseHttp.okHttpPost(url, paramers, httpCallBack);
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
