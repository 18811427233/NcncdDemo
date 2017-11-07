package cn.miao.ncncd.api;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import cn.miao.ncncd.okhttp.HttpCallBack;
import cn.miao.ncncd.okhttp.HttpConstant;
import cn.miao.ncncd.okhttp.HttpSession;
import cn.miao.ncncd.okhttp.entity.BloodSugar;
import cn.miao.ncncd.okhttp.entity.CommonReq;
import cn.miao.ncncd.util.HmacSha256Util;

/**
 * 血糖Api
 * Created by zhangzhuang on 17/11/7.
 */

public class BloodSugarApi {

    private static final String TAG = "BloodSugarApi";

    /**
     * 上传血糖数据
     *
     * @param telephone         电话号
     * @param bloodSugarList    业务参数
     * @param callBackInterface
     */
    public static void bloodSugar(String telephone, List<BloodSugar> bloodSugarList, final CallBackInterface callBackInterface) {

//                String appKey = MetaDataUtil.getAppMetaData(MainActivity.this, "appKey");
//                String appSecret = MetaDataUtil.getAppMetaData(MainActivity.this, "appSecret");
        String appKey = "1";
        String appSecret = "1";

        Log.e(TAG, "======appKey=====" + appKey);
        Log.e(TAG, "======appSecret=====" + appSecret);

        int timestamp = (int) (System.currentTimeMillis() / 1000);
        List<BloodSugar> bloodSugars = new ArrayList<BloodSugar>();
        bloodSugars.addAll(bloodSugarList);

        String s1 = JSON.toJSONString(bloodSugars);
        Log.e(TAG, "======s=====" + s1);

        String s = appKey + timestamp + telephone + s1;
        String sign = HmacSha256Util.createSign(s.getBytes(), appSecret.getBytes());

        CommonReq commonReq = new CommonReq();
        commonReq.setAppKey(appKey);
        commonReq.setData(s1);
        commonReq.setTelephone(telephone);
        commonReq.setSign(sign);
        commonReq.setTimestamp(timestamp);

        HttpSession.sendPost(HttpConstant.PATH_DATA_UPLOAD_BLOOD_SUGAR, commonReq, new HttpCallBack() {
            @Override
            public void onStart() {

                callBackInterface.onStart();
            }

            @Override
            public void onSuccess(String response) {

                callBackInterface.onSuccess();
            }

            @Override
            public void onFailure(int statusCode, String response, Throwable error) {

                callBackInterface.onFailure(response);
            }

            @Override
            public void onNetError() {
                callBackInterface.onNetError();
            }

            @Override
            public void onFinish() {

                callBackInterface.onFinish();
            }
        });
    }
}
