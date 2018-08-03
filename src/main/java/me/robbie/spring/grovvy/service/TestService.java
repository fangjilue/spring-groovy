package me.robbie.spring.grovvy.service;

import me.robbie.spring.grovvy.dao.RuleTemplateDao;
import me.robbie.spring.grovvy.dao.TestDao;
import me.robbie.spring.grovvy.model.RuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/7/25 上午10:25
 * @since [产品/模块版本]
 */
@Service
public class TestService {

    @Autowired
    private TestDao testDao;

    @Autowired
    private RuleTemplateDao ruleTemplateDao;

    public RuleResult get(int id){
        System.out.println("service id=" + id);

        System.out.println("ruleTemplateDao ="+ ruleTemplateDao);

        return testDao.get(id);
    }

    public String save(RuleResult result){
        System.out.println("service save=" + result.getCode());

        System.out.println("ruleTemplateDao ="+ ruleTemplateDao);

        return getSaveId(result.getCode());
    }

    public String update(RuleResult result){
        System.out.println("service update=" + result.getCode());

        System.out.println("ruleTemplateDao ="+ ruleTemplateDao);

        return TestUtil.getAbc(result.getCode());
    }

    private String getSaveId(String code){
        System.out.println("getSaveId....");

        return "private:"+code;
    }
}
