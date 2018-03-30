package ch.oliverg.OcpMon;


import ch.oliverg.OcpMon.services.OcpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@Controller
public class ClientOCPController {

    @Autowired
    OcpService ocpService;

    @RequestMapping(value = "/ocp", method = RequestMethod.GET)
    public String home(Map<String, Object> model) {
        model.put("message", ocpService.getOCPNamespaces());
        System.out.println(ocpService.getOCPNamespaces());
        return "ocp";
    }

}