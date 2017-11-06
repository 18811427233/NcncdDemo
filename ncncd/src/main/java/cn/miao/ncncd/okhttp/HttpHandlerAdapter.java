package cn.miao.ncncd.okhttp;

/**
 * Default Implements for HttpCallBack
 *
 * @see HttpCallBack
 */
public class HttpHandlerAdapter implements HttpCallBack {

    @Override
    public void onStart() {
        // Initiated the request
    }

    @Override
    public void onSuccess(String response) {
        // Successfully got a response
    }

    @Override
    public void onFailure(int statusCode, String response, Throwable error) {
        // Response failed :(
    }

    @Override
    public void onNetError() {
        // internet error
    }

    @Override
    public void onFinish() {
        // Completed the request (either success or failure)
    }
}
