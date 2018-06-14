package me.robbie.spring.grovvy.model;

import java.io.Serializable;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/6/13 下午6:03
 * @since [产品/模块版本]
 */
public class RuleResult implements Serializable {

    private static final long serialVersionUID = -7336845966607744319L;

    private boolean success;

    private String code;

    private String msg;


    public RuleResult() {
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public static RuleResult getError(String msg){
        RuleResult result = new RuleResult();
        result.setCode("1");
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }

    public static RuleResult getSuccess(String msg){
        RuleResult result = new RuleResult();
        result.setCode("0");
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }
}
