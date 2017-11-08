package cn.miao.ncncd.okhttp.entity;

import org.json.JSONObject;

/**
 * 健康实体类
 * Created by zhangzhuang on 17/11/8.
 */

public class Health extends JSONObject {

    /**
     * bmi指数
     */
   private float bmi;

    /**
     * 体脂率
     */
    private float bodyFat;

    /**
     * 肌肉率
     */
    private float muscleRate;

    /**
     * 水分
     */
    private float moistureRate;

    /**
     * 骨密度
     */
    private float bmd;

    /**
     * 基础代谢率
     */
    private float bmr;

    /**
     * 时间戳（秒）
     */
    private int sampleTime;

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public float getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(float bodyFat) {
        this.bodyFat = bodyFat;
    }

    public float getMuscleRate() {
        return muscleRate;
    }

    public void setMuscleRate(float muscleRate) {
        this.muscleRate = muscleRate;
    }

    public float getMoistureRate() {
        return moistureRate;
    }

    public void setMoistureRate(float moistureRate) {
        this.moistureRate = moistureRate;
    }

    public float getBmd() {
        return bmd;
    }

    public void setBmd(float bmd) {
        this.bmd = bmd;
    }

    public float getBmr() {
        return bmr;
    }

    public void setBmr(float bmr) {
        this.bmr = bmr;
    }

    public int getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(int sampleTime) {
        this.sampleTime = sampleTime;
    }
}
