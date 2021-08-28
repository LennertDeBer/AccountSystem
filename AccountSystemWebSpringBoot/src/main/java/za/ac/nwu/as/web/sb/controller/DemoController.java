package za.ac.nwu.as.web.sb.controller;
/*Swagger advance setting*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.ac.nwu.as.domain.service.GeneralResponse;

@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("/ping")

   public GeneralResponse<String> ping(@RequestParam(value = "echo",defaultValue = "pong") String echo){
        return new GeneralResponse<String> (true, echo);
    }

        }


