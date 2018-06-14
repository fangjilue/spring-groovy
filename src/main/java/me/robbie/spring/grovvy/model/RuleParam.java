package me.robbie.spring.grovvy.model;


import java.io.Serializable;

/**
 * <p>
 * TODO:类定义
 * </p>
 *
 * @author Robbie
 * @version $Id$
 */
public class RuleParam<T> implements Serializable {

    private static final long serialVersionUID = -2403736370068297162L;

    private RuleTemplate ruleTemplate;

    private T param;


    public RuleParam() {

    }

    public RuleTemplate getRuleTemplate() {
        return ruleTemplate;
    }

    public void setRuleTemplate(RuleTemplate ruleTemplate) {
        this.ruleTemplate = ruleTemplate;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
