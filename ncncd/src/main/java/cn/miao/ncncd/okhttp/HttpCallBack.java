package cn.miao.ncncd.okhttp;

/**
 *
 */
public interface HttpCallBack {
    public void onStart();

    public void onSuccess(String response);

    public void onFailure(int statusCode, String response, Throwable error);

    public void onNetError();

    public void onFinish();
}
