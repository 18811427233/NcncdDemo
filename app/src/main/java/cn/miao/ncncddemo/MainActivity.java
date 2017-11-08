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
import cn.miao.ncncd.api.HealthApi;
import cn.miao.ncncd.okhttp.entity.BloodSugar;
import cn.miao.ncncd.okhttp.entity.Health;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    /*签名调试*/
    private Button btnBloodSugar;
    private Button btnHealth;

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
        btnBloodSugar = findViewById(R.id.btn_blood_sugar);
        btnHealth = findViewById(R.id.btn_health);
    }

    public void initData() {

    }

    public void initEvent() {

        /*上传血糖数据*/
        btnBloodSugar.setOnClickListener(new View.OnClickListener() {
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

        /*上传健康数据*/
        btnHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Health> healthList = new ArrayList<Health>();
                for (int i = 0; i < 1; i++) {
                    /*业务参数*/
                    Health health = new Health();
                    health.setBmd(0);
                    health.setBmi(0);
                    health.setBmr(0);
                    health.setBodyFat(0);
                    health.setMoistureRate(0);
                    health.setMuscleRate(0);
                    health.setSampleTime(0);
                    healthList.add(health);
                }

                HealthApi.health("18811427233", healthList, new CallBackInterface() {

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
