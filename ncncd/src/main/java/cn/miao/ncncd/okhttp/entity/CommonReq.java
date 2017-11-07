package cn.miao.ncncd.okhttp.entity;

/**
 * Created by zhangzhuang on 17/11/7.
 */

public class CommonReq {

    /**
     * appkey
     */
    private String appKey;

    /**
     * 时间戳
     */
    private int timestamp;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 业务参数
     */
    private String data;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
