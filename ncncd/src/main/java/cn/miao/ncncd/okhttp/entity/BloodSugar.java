package cn.miao.ncncd.okhttp.entity;


import com.alibaba.fastjson.JSONObject;

/**
 *  血糖实体类
 * Created by zhangzhuang on 17/11/7.
 */

public class BloodSugar extends JSONObject {

    /**
     * 血糖类型
     */
    private int type;

    /**
     * 测量数值 1空腹 2餐后
     */
    private float value;

    /**
     * 时间戳（秒）
     */
    private int sampleTime;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(int sampleTime) {
        this.sampleTime = sampleTime;
    }
}
