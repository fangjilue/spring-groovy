package me.robbie.spring.grovvy.service;

import me.robbie.spring.grovvy.model.RuleParam;
import me.robbie.spring.grovvy.model.RuleResult;
import me.robbie.spring.grovvy.script.RuleEngine;
import me.robbie.spring.grovvy.script.RuleEngineFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/6/13 下午6:23
 * @since [产品/模块版本]
 */
@Service
public class ValidateService {

    @Autowired
    private RuleEngineFactory factory;

    public <T> RuleResult validate(RuleParam<T> param) {

        try {
            RuleEngine ruleEngine = factory.getEngine(param.getRuleTemplate());

            if(ruleEngine == null){

                return RuleResult.getSuccess("无规则");
            }

            return ruleEngine.execute(param);
        } catch (Exception e) {
            e.printStackTrace();
            return RuleResult.getError("执行规则异常");
        }
    }

    @PreDestroy
    public void destroy(){
        System.out.println("ValidateService destroy");
    }
}
