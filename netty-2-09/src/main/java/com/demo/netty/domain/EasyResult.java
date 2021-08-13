package com.demo.netty.domain;

/**
 * @author wangjianhua
 * @Description 返回值封装 用于接口反馈
 * @date 2021/8/13 10:49
 */
public class EasyResult {

    private boolean success;

    private String title;

    private String msg;

    public static EasyResult buildSuccessResult(){
        EasyResult result = new EasyResult();
        result.setSuccess(true);
        result.setMsg("ok");
        return result;
    }

    public static EasyResult buildErrResult(Exception e){
        EasyResult easyResult = new EasyResult();
        easyResult.setSuccess(false);
        easyResult.setMsg(e.getMessage());
        return easyResult;
    }

    public static EasyResult buildErrResult(String msg){
        EasyResult easyResult = new EasyResult();
        easyResult.setSuccess(false);
        easyResult.setMsg(msg);
        return easyResult;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
