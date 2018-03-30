package ch.oliverg.OcpMon;


import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "OcpMon World";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }





}