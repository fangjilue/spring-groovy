package me.robbie.spring.grovvy.dao;

import me.robbie.spring.grovvy.model.RuleResult;
import org.springframework.stereotype.Repository;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/7/25 上午10:28
 * @since [产品/模块版本]
 */
@Repository
public class TestDao {

    public RuleResult get(int id){
        System.out.println("dao id=" + id);
        return id % 2 ==0 ?  RuleResult.getSuccess("ok") : RuleResult.getError("error");
    }
}
