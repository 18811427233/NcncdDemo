package cn.miao.ncncddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;

import cn.miao.ncncd.okhttp.HttpCallBack;
import cn.miao.ncncd.okhttp.HttpConstant;
import cn.miao.ncncd.okhttp.HttpUtil;
import cn.miao.ncncd.okhttp.entity.DataUploadReq;
import cn.miao.ncncd.util.HmacSha256Util;
import cn.miao.ncncd.util.MetaDataUtil;

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

                String appKey = MetaDataUtil.getAppMetaData(MainActivity.this, "appKey");
                String appSecret = MetaDataUtil.getAppMetaData(MainActivity.this, "appSecret");

                Log.e(TAG, "======appKey=====" + appKey);
                Log.e(TAG, "======appSecret=====" + appSecret);

                String s = "10[{\"beginTime\":\"2017-10-10 00:00:00\",\"endTime\":\"2017-10-10 23:59:59\"}]";
                String sign = HmacSha256Util.createSign(s.getBytes(), appSecret.getBytes());

                Log.e(TAG, "======sign=====" + sign);

                String jsonString = "";
                JSON json = JSON.parseObject(jsonString);

                DataUploadReq dataUploadReq = new DataUploadReq();
                dataUploadReq.setAppKey("");
                dataUploadReq.setData(json);
                dataUploadReq.setSign(sign);
                dataUploadReq.setTimestamp((int) System.currentTimeMillis());

                HttpUtil.sendPost(HttpConstant.PATH_DATA_UPLOAD_TEST, dataUploadReq, new HttpCallBack() {
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


}
