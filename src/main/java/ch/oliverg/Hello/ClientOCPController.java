package ch.oliverg.Hello;


import ch.oliverg.Hello.services.OcpService;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceList;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.openshift.api.model.ImageStream;
import io.fabric8.openshift.api.model.ImageStreamList;
import io.fabric8.openshift.api.model.Project;
import io.fabric8.openshift.api.model.ProjectList;
import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftAPIGroups;
import io.fabric8.openshift.client.OpenShiftClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class ClientOCPController {



    @RequestMapping(value = "/ocp", method = RequestMethod.GET)
    public String welcome(Map<String, Object> model) {
        return "ocp";
    }

    @Autowired
    OcpService ocpService;


    @RequestMapping("/test")
    public String home(Map<String, Object> model) {
        model.put("message", ocpService.getOCPs());
        System.out.println(ocpService.getOCPs());
        return "ocp";
    }


}