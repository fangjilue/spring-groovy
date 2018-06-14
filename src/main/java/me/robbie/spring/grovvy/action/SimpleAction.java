package me.robbie.spring.grovvy.action;

import me.robbie.spring.grovvy.model.*;
import me.robbie.spring.grovvy.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
public class SimpleAction {

    @Autowired
    private ValidateService validateService;

    @RequestMapping("/")
    String index() {
        return "Hello World!";
    }

    @RequestMapping("/net")
    RuleResult doNet(String param){
        if("age".equals(param)){
            RuleParam<ValidateAgeParam> ruleParam = new RuleParam<>();

            ruleParam.setParam(new ValidateAgeParam(18,"abc"));
            ruleParam.setRuleTemplate(new RuleTemplate(StoreType.NET,"http://localhost/AgeRule.groovy"));

            return  validateService.validate(ruleParam);

        }else {
            RuleParam<ValidateRoomParam> ruleParam = new RuleParam<>();

            ruleParam.setParam(new ValidateRoomParam(0,19));
            ruleParam.setRuleTemplate(new RuleTemplate(StoreType.NET,"http://localhost/RoomRule.groovy"));

            return  validateService.validate(ruleParam);
        }
    }

    @RequestMapping("/local")
    RuleResult doLocal(String param){
        if("age".equals(param)){
            RuleParam<ValidateAgeParam> ruleParam = new RuleParam<>();

            ruleParam.setParam(new ValidateAgeParam(18,"abc"));
            ruleParam.setRuleTemplate(new RuleTemplate(StoreType.LOCAL,"script/AgeRule.groovy"));

            return  validateService.validate(ruleParam);

        }else {
            RuleParam<ValidateRoomParam> ruleParam = new RuleParam<>();

            ruleParam.setParam(new ValidateRoomParam(0,19));
            ruleParam.setRuleTemplate(new RuleTemplate(StoreType.LOCAL,"script/RoomRule.groovy"));

            return  validateService.validate(ruleParam);
        }
    }


    @RequestMapping("/db")
    RuleResult doDb(String param){
        if("age".equals(param)){
            RuleParam<ValidateAgeParam> ruleParam = new RuleParam<>();

            ruleParam.setParam(new ValidateAgeParam(18,"abc"));
            ruleParam.setRuleTemplate(new RuleTemplate(StoreType.DB,"age"));

            return  validateService.validate(ruleParam);

        }else {
            RuleParam<ValidateRoomParam> ruleParam = new RuleParam<>();

            ruleParam.setParam(new ValidateRoomParam(0,19));
            ruleParam.setRuleTemplate(new RuleTemplate(StoreType.DB,"room"));

            return  validateService.validate(ruleParam);
        }
    }

}
