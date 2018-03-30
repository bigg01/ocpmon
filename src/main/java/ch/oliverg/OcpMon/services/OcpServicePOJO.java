package ch.oliverg.OcpMon.services;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.ServiceList;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.openshift.api.model.ImageStream;
import io.fabric8.openshift.api.model.ImageStreamList;
import io.fabric8.openshift.api.model.Project;
import io.fabric8.openshift.api.model.ProjectList;
import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftAPIGroups;
import io.fabric8.openshift.client.OpenShiftClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OcpServicePOJO implements OcpService {


    @Override
    public ArrayList<String> getOCPNamespaces() {
        ArrayList<String> ocpitems = new ArrayList<String>();


        try {
            OpenShiftClient client = new DefaultOpenShiftClient();
            if (!client.supportsOpenShiftAPIGroup(OpenShiftAPIGroups.IMAGE)) {
                System.out.println("WARNING this cluster does not support the API Group " + OpenShiftAPIGroups.IMAGE);
                //return;
            }

            NamespaceList nlist = client.namespaces().list();
            if (nlist == null) {
                System.out.println("ERROR no list returned!");
                //return;
            }
            List<Namespace> namespaces = nlist.getItems();
            for (Namespace np : namespaces) {
                System.out.println("Namespace " +
                        np.getMetadata().getName() +
                        " has version: " +
                        np.getMetadata().getAdditionalProperties());

                ocpitems.add(np.getMetadata().getName());

            }
            System.out.println("Found " + namespaces.size() + " Project(s)");


        } catch (KubernetesClientException e) {
            System.out.println("Failed: " + e);
            //e.printStackTrace();
            ocpitems.add("ERROR");
        }
        return ocpitems;
    }

}
