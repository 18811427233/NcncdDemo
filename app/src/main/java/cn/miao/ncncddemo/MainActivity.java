package cn.miao.ncncddemo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import cn.miao.ncncd.okhttp.HttpCallBack;
import cn.miao.ncncd.okhttp.HttpConstant;
import cn.miao.ncncd.okhttp.OkHttpUtil;
import cn.miao.ncncd.okhttp.entity.DataUploadReq;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    /*签名调试*/
    private Button btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBoot();
        initView();
        initData();
        initEvent();

    }

    public void initBoot() {
    }

    public void initView() {
        btnSign = findViewById(R.id.btn_sign);
    }

    public void initData() {
    }

    public void initEvent() {

        /*签名*/
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String appKey = getAppMetaData(MainActivity.this, "appKey");
                String appSecret = getAppMetaData(MainActivity.this, "appSecret");

                Log.e(TAG, "======appKey=====" + appKey);
                Log.e(TAG, "======appSecret=====" + appSecret);

                String s = "10[{\"beginTime\":\"2017-10-10 00:00:00\",\"endTime\":\"2017-10-10 23:59:59\"}]";
                String sign = HMACSHA256(s.getBytes(), appSecret.getBytes());

                Log.e(TAG, "======sign=====" + sign);

                String jsonString = "";
                JSON json = JSON.parseObject(jsonString);

                DataUploadReq dataUploadReq = new DataUploadReq();
                dataUploadReq.setAppKey("");
                dataUploadReq.setData(json);
                dataUploadReq.setSign(sign);
                dataUploadReq.setTimestamp((int) System.currentTimeMillis());

                OkHttpUtil okHttpUtil = new OkHttpUtil();
                okHttpUtil.postOkHttp(HttpConstant.PATH_DATA_UPLOAD_TEST, dataUploadReq, new HttpCallBack() {
                    @Override
                    public void onStart() {
                        Log.e(TAG, "=======onStart====");
                    }

                    @Override
                    public void onSuccess(String response) {
                        Log.e(TAG, "=======onSuccess====");
                    }

                    @Override
                    public void onFailure(int statusCode, String response, Throwable error) {
                        Log.e(TAG, "=======onFailure====");
                    }

                    @Override
                    public void onNetError() {
                        Log.e(TAG, "=======onNetError====");
                    }

                    @Override
                    public void onFinish() {
                        Log.e(TAG, "=======onFinish====");
                    }
                });
            }
        });
    }

    /**
     * 获取application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值, 或者异常), 则返回值为空
     */
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }

    /**
     * 生成签名
     *
     * @param data
     * @param key
     * @return
     */
    public static String HMACSHA256(byte[] data, byte[] key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            return byte2hex(mac.doFinal(data));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }


}
