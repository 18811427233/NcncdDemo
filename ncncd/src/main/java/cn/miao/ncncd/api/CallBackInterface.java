package cn.miao.ncncd.api;

/**
 *
 * Created by zhangzhuang on 17/11/7.
 */

public interface CallBackInterface {

     void onStart();

     void onSuccess();

     void onFailure(String response);

     void onNetError();

     void onFinish();
}
