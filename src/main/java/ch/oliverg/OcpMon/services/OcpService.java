package ch.oliverg.OcpMon.services;


import java.util.ArrayList;

public interface OcpService {

    ArrayList<String> getOCPNamespaces();
    ArrayList<String> getOCPServices();
}
