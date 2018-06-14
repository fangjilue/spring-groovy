package me.robbie.spring.grovvy.model;

import java.io.Serializable;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/6/14 下午1:34
 * @since [产品/模块版本]
 */
public class RuleTemplate implements Serializable {

    private StoreType storeType;

    /**
     * StoreType#NET 为url
     * StoreType#LOCAL 为本地file路径
     * StoreType#DB 为数据库中template id
     */
    private String template;


    public RuleTemplate() {
    }

    public RuleTemplate(StoreType storeType, String template) {
        this.storeType = storeType;
        this.template = template;
    }

    public StoreType getStoreType() {
        return storeType;
    }

    public void setStoreType(StoreType storeType) {
        this.storeType = storeType;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
