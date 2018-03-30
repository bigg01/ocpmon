package ch.oliverg.Hello.services;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

@Service
public class OcpServicePOJO implements OcpService {


    @Override
    public ArrayList<String> getOCPs() {
        ArrayList<String> ocpitems = new ArrayList<String>();

        ocpitems.add("oli");
        ocpitems.add("vanessa");
        return ocpitems;
    }
}
