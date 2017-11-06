package cn.miao.ncncd.okhttp.entity;

/**
 * 首页轮播请求
 * Created by lihaotian on 2017/2/9.
 */

public class CarouselListReq {

    /**
     * 城市
     */
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
