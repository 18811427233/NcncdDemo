package cn.miao.ncncd.okhttp.entity;

/**
 * Created by zhangzhuang on 17/11/6.
 */

public class CommonResp {

    /**
     * 错误代码 0为成功
     */
   private int errNo;

    /**
     * 错误描述 errno为0时，errmsg为空
     */
   private String errMsg;

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
