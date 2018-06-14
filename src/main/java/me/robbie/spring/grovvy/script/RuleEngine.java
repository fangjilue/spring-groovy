package me.robbie.spring.grovvy.script;

import me.robbie.spring.grovvy.model.RuleParam;
import me.robbie.spring.grovvy.model.RuleResult;
import org.springframework.context.ApplicationContext;

/**
 * <p>TODO:类定义</p>
 *
 * @author Robbie
 * @version $Id$
 */
public interface RuleEngine<T> {

    /**
     * 注入 ApplicationContext
     * @param application
     */
    void setApplication(ApplicationContext application);

    /**
     * 执行规则引擎
     * @param param
     * @return
     */
    RuleResult execute(RuleParam<T> param);

}
