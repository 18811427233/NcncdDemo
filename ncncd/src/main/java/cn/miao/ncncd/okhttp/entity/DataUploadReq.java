package cn.miao.ncncd.okhttp.entity;

import com.alibaba.fastjson.JSON;

/**
 *  API签名
 * Created by zhangzhuang on 17/11/3.
 */

public class DataUploadReq {

    /**
     * appkey
     */
    private String appKey;

    /**
     * 时间戳
     */
    private int timestamp;

    /**
     * 业务参数
     */
    private JSON data;

    /**
     * 签名参数
     */
    private String  sign;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public JSON getData() {
        return data;
    }

    public void setData(JSON data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
