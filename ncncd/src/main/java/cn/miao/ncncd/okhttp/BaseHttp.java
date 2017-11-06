package cn.miao.ncncd.okhttp;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *  网络请求基础类
 * Created by zhangzhuang on 17/10/17.
 */

public class BaseHttp {

    /**
     * GET有参数请求
     *
     * @param url
     * @param paramsMap
     * @param httpCallBack
     */
    public static void okHttpGet(String url, Map<String, String> paramsMap, final HttpCallBack httpCallBack) {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        Request request = new Request.Builder()//创建Request 对象。
                .url(setGetParams(url, paramsMap))
                .build();

        httpCallBack.onStart();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpCallBack.onNetError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    httpCallBack.onSuccess(response.body().string());
                } else {
                    httpCallBack.onFailure(response.code(), response.message(), null);
                }

                httpCallBack.onFinish();
            }
        });
    }

    /**
     * POST有参数请求
     *
     * @param url
     * @param object
     * @param httpCallBack
     */
    public static void okHttpPost(String url, Object object, final HttpCallBack httpCallBack) {

        MediaType JSON1 = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        RequestBody body = RequestBody.create(JSON1, JSON.toJSONString(object));

        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        Request request = new Request.Builder()//创建Request 对象。
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpCallBack.onNetError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    httpCallBack.onSuccess(response.body().string());
                } else {
                    httpCallBack.onFailure(response.code(), response.message(), null);
                }

                httpCallBack.onFinish();

            }
        });
    }

    /**
     *  get请求，只有键值对参数，进行url拼接
     *
     * @param mUrl
     * @param mParamsMap
     * @return
     */
    private static String setGetParams(String mUrl, Map<String, String> mParamsMap) {
        if (mParamsMap != null) {
            mUrl = mUrl + "?";
            for (String key : mParamsMap.keySet()) {
                mUrl = mUrl + key + "=" + mParamsMap.get(key) + "&";
            }
            return mUrl.substring(0, mUrl.length() - 1);
        }
        return mUrl;
    }
}
