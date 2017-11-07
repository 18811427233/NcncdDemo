package cn.miao.ncncddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.miao.ncncd.api.BloodSugarApi;
import cn.miao.ncncd.okhttp.HttpHandler;

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

                BloodSugarApi.bloodSugar("18811427233",1,new HttpHandler(){

                    @Override
                    public void onStart() {
                        super.onStart();

                        Toast.makeText(MainActivity.this, "asfsd", Toast.LENGTH_SHORT).show();

                        Log.e(TAG,"====onStart=====");
                    }

                    @Override
                    public void onSuccess(String response) {
                        super.onSuccess(response);
                        Log.e(TAG,"====onSuccess=====");
                    }

                    @Override
                    public void onFailure(int statusCode, String response, Throwable error) {
                        super.onFailure(statusCode, response, error);
                        Log.e(TAG,"====onFailure=====");

                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        Log.e(TAG,"====onFinish=====");
                    }
                });
            }
        });
    }
}
