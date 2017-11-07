package cn.miao.ncncd.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.Map;

import cn.miao.ncncd.okhttp.entity.CommonResp;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络请求基础类
 * Created by zhangzhuang on 17/10/17.
 */

public class BaseHttp {

    private static final String TAG = "BaseHttp";

    /**
     * GET有参数请求
     *
     * @param url
     * @param paramsMap
     * @param httpCallBack
     */
    public static void okHttpGet(String url, Map<String, String> paramsMap, final HttpCallBack httpCallBack) {

        final Handler handler = new Handler(Looper.getMainLooper());

        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        Request request = new Request.Builder()//创建Request 对象。
                .url(setGetParams(url, paramsMap))
                .build();

        handler.post(new Runnable() {
            @Override
            public void run() {
                httpCallBack.onStart();
            }
        });

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpCallBack.onNetError();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                //跳转到主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        if (response.isSuccessful()) {

                            try {
                                httpCallBack.onSuccess(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {

                            httpCallBack.onFailure(response.code(), response.message(), null);
                        }

                        httpCallBack.onFinish();
                    }
                });
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

        final Handler handler = new Handler(Looper.getMainLooper());

        MediaType JSON1 = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        RequestBody body = RequestBody.create(JSON1, JSON.toJSONString(object));

        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        Request request = new Request.Builder()//创建Request 对象。
                .url(url)
                .post(body)
                .build();

        handler.post(new Runnable() {
            @Override
            public void run() {
                httpCallBack.onStart();
            }
        });

        /*打印请求url、参数*/
        Log.e(TAG, "url:" + url);
        Log.e(TAG, "ReqBody:" + JSON.toJSONString(object));

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpCallBack.onNetError();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                //跳转到主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String body = null;
                        try {
                            body = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.e(TAG, "RespBody:" + body);

                        CommonResp commonResp = JSON.parseObject(body, CommonResp.class);

                        if (commonResp.getErrNo() == 0) {

                            httpCallBack.onSuccess(body);

                        } else {

                            httpCallBack.onFailure(commonResp.getErrNo(), commonResp.getErrMsg(), null);

                        }

                        httpCallBack.onFinish();

                    }
                });
            }
        });
    }

    /**
     * get请求，只有键值对参数，进行url拼接
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
