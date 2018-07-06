package me.robbie.spring.grovvy.dao;

import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/6/14 下午2:20
 * @since [产品/模块版本]
 */
@Repository
public class RuleTemplateDao {

    public static final String ROOM =
            "import me.robbie.spring.grovvy.model.RuleParam\n" +
            "import me.robbie.spring.grovvy.model.RuleResult\n" +
            "import me.robbie.spring.grovvy.model.ValidateRoomParam\n" +
            "import me.robbie.spring.grovvy.script.RuleEngine\n" +
            "import me.robbie.spring.grovvy.service.RoomService\n" +
            "import org.springframework.context.ApplicationContext\n" +
            "\n" +
            "class RoomRule implements RuleEngine<ValidateRoomParam> {\n" +
            "\n" +
            "    RoomService roomService\n" +
            "\n" +
            "    @Override\n" +
            "    void setApplication(ApplicationContext application) {\n" +
            "        roomService = application.getBean(\"roomService\")\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    @Override\n" +
            "    RuleResult execute(RuleParam<ValidateRoomParam> param) {\n" +
            "        println(param)\n" +
            "\n" +
            "        if(param.getParam() == null){\n" +
            "            return RuleResult.getError(\"param is null by string\")\n" +
            "        }\n" +
            "\n" +
            "        ValidateRoomParam roomParam = param.getParam()\n" +
            "\n" +
            "        roomService.exist(roomParam.getCount()) ? RuleResult.getError(\"room count error\") : RuleResult.getSuccess(\"by string\")\n" +
            "    }\n" +
            "\n" +
            "}\n";

    public static final String AGE =
            "import me.robbie.spring.grovvy.model.ValidateAgeParam\n" +
            "import me.robbie.spring.grovvy.script.RuleEngine\n" +
            "import me.robbie.spring.grovvy.model.RuleParam\n" +
            "import me.robbie.spring.grovvy.model.RuleResult\n" +
            "import org.springframework.context.ApplicationContext\n" +
            "\n" +
            "class AgeRule implements RuleEngine<ValidateAgeParam> {\n" +
            "\n" +
            "    @Override\n" +
            "    void setApplication(ApplicationContext application) {\n" +
            "        //不需要bean;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    RuleResult execute(RuleParam<ValidateAgeParam> param) {\n" +
            "        println(param)\n" +
            "\n" +
            "        if(param.getParam() == null){\n" +
            "            return RuleResult.getError(\"param is null by string\")\n" +
            "        }\n" +
            "\n" +
            "        ValidateAgeParam ageParam = param.getParam()\n" +
            "\n" +
            "        ageParam.getAge()< 18 ? RuleResult.getError(\"age < 18 by string\") : RuleResult.getSuccess(\"by string\")\n" +
            "    }\n" +
            "\n" +
            "}\n";

    public String getTemplate(String id){
        //查询数据库获取模板

        return "age".equals(id) ? AGE : ROOM;
    }

    @PreDestroy
    public void destroy(){
        System.out.println("RuleTemplateDao destroy");
    }
}
