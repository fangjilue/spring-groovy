import me.robbie.spring.grovvy.model.ValidateAgeParam
import me.robbie.spring.grovvy.script.RuleEngine
import me.robbie.spring.grovvy.model.RuleParam
import me.robbie.spring.grovvy.model.RuleResult
import org.springframework.context.ApplicationContext

class AgeRule implements RuleEngine<ValidateAgeParam> {

    @Override
    void setApplication(ApplicationContext application) {
        //不需要bean;
    }


    @Override
    RuleResult execute(RuleParam<ValidateAgeParam> param) {
        println("AgeRule....111")

        if(param.getParam() == null){
            return RuleResult.getError("param is null by local")
        }

        ValidateAgeParam ageParam = param.getParam()

        ageParam.getAge()< 18 ? RuleResult.getError("age < 18 by local") : RuleResult.getSuccess("age >= 18 by local")
    }

}
