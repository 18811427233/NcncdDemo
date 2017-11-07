package cn.miao.ncncd.okhttp;

/**
 * 网络请求常量
 * Created by lihaotian on 2016/11/25.
 */

public class HttpConstant {

  /*卫计委接口文档*/
//    http://101.200.162.216:84/index.php?s=/5&page_id=779
//    cast_app
//    111111

    private static final String BASE_PATH = "http://118.190.93.145:8026/api/";

    /**
     * API签名调试
     */
    public static final String PATH_DATA_UPLOAD_TEST = BASE_PATH + "data/upload/test";
    /**
     * 上传血糖数据
     */
    public static final String PATH_DATA_UPLOAD_BLOOD_SUGAR = BASE_PATH + "data/upload/blood/sugar";
}
