import me.robbie.spring.grovvy.model.RuleParam
import me.robbie.spring.grovvy.model.RuleResult
import me.robbie.spring.grovvy.model.ValidateRoomParam
import me.robbie.spring.grovvy.script.RuleEngine
import me.robbie.spring.grovvy.service.RoomService
import org.springframework.context.ApplicationContext

class RoomRule implements RuleEngine<ValidateRoomParam> {

    RoomService roomService

    @Override
    void setApplication(ApplicationContext application) {
        roomService = application.getBean("roomService")
    }


    @Override
    RuleResult execute(RuleParam<ValidateRoomParam> param) {
        println("RoomRule roomService =" + roomService)

        if(param.getParam() == null){
            return RuleResult.getError("param is null")
        }

        ValidateRoomParam roomParam = param.getParam()

        roomService.exist(roomParam.getCount()) ? RuleResult.getError("room count error") : RuleResult.getSuccess()
    }

}
