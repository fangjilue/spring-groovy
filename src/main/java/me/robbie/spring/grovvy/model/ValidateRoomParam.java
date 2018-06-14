package me.robbie.spring.grovvy.model;

import java.io.Serializable;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/6/13 下午6:14
 * @since [产品/模块版本]
 */
public class ValidateRoomParam implements Serializable {
    private static final long serialVersionUID = 7123407613131418475L;

    private int status;

    private int count;

    public ValidateRoomParam() {
    }

    public ValidateRoomParam(int status, int count) {
        this.status = status;
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
