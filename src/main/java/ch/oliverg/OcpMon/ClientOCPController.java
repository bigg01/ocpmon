package ch.oliverg.OcpMon;


import ch.oliverg.OcpMon.services.OcpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public class ClientOCPController {

    @Autowired
    OcpService ocpService;

    @RequestMapping(value = "/ocp", method = RequestMethod.GET)
    public String home(Map<String, Object> model) {
        model.put("namespaces", ocpService.getOCPNamespaces());
        model.put("services", ocpService.getOCPServices());

        return "ocp";
    }

    @ResponseBody
    @RequestMapping(value ="/txt" )
    public String txtResponse(HttpServletResponse response){
        //String fileName = "a.txt";
        //response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("Content-Type", "text/plain");
        String content = "This is txt content";
        return content;
    }


    @ResponseBody
    @RequestMapping(value ="/yaml" )
    public String yamlResponse(HttpServletResponse response){
        //String fileName = "a.txt";
        //response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("Content-Type", "application/yaml");
        String content = "This is txt content";
        return content;
    }

}