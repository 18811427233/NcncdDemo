package cn.miao.ncncddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.miao.ncncd.api.BloodSugarApi;
import cn.miao.ncncd.api.CallBackInterface;
import cn.miao.ncncd.okhttp.entity.BloodSugar;

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

                List<BloodSugar> bloodSugars = new ArrayList<BloodSugar>();
                for (int i = 0; i < 1; i++) {
                    /*业务参数*/
                    BloodSugar bloodSugar = new BloodSugar();
                    bloodSugar.setType(1);
                    bloodSugar.setValue(1);
                    bloodSugar.setSampleTime(1);
                    bloodSugars.add(bloodSugar);
                }

                BloodSugarApi.bloodSugar("18811427233", bloodSugars, new CallBackInterface() {

                    @Override
                    public void onStart() {
                        Log.e(TAG, "====onStart=====");
                    }

                    @Override
                    public void onSuccess() {
                        Log.e(TAG, "====onSuccess=====");
                    }

                    @Override
                    public void onFailure(String response) {
                        Log.e(TAG, "====onFailure=====");
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNetError() {
                        Log.e(TAG, "====onNetError=====");
                    }

                    @Override
                    public void onFinish() {

                        Log.e(TAG, "====onFinish=====");
                    }
                });
            }
        });
    }
}
