package ch.oliverg.OcpMon;


import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.openshift.api.model.*;
import io.fabric8.openshift.api.model.ClusterRoleBindingList;

import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftAPIGroups;
import io.fabric8.openshift.client.OpenShiftClient;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientOCP {

    private static final Logger logger = LoggerFactory.getLogger(ClientOCP.class);

    private static void log(String action, Object obj) {
        logger.info("{}: {}", action, obj);
    }

    private static void log(String action) {
        logger.info(action);
    }

    /*private static OpenShiftClient connect(){
        OpenShiftClient client = new DefaultOpenShiftClient();
        if (!client.supportsOpenShiftAPIGroup(OpenShiftAPIGroups.IMAGE)) {
            System.out.println("WARNING this cluster does not support the API Group " + OpenShiftAPIGroups.IMAGE);
            return client
        }

    }*/


    public static void main(String[] args) {
            try {
                OpenShiftClient client = new DefaultOpenShiftClient();
                if (!client.supportsOpenShiftAPIGroup(OpenShiftAPIGroups.IMAGE)) {
                    System.out.println("WARNING this cluster does not support the API Group " + OpenShiftAPIGroups.IMAGE);
                    return;
                }



                ImageStreamList list = client.imageStreams().list();
                if (list == null) {
                    System.out.println("ERROR no list returned!");
                    return;
                }
                List<ImageStream> items = list.getItems();
                for (ImageStream item : items) {
                    System.out.println("ImageStream " + item.getMetadata().getName() + " has version: " + item.getApiVersion());
                }
                System.out.println("Found " + items.size() + " ImageStream(s)");



                ProjectList plist = client.projects().list();
                if (plist == null) {
                    System.out.println("ERROR no list returned!");
                    return;
                }
                List<Project> pitems = plist.getItems();
                for (Project pitem : pitems) {
                    System.out.println("Project " + pitem.getMetadata().getName() + " has version: " + pitem.getMetadata().getLabels());
                }
                System.out.println("Found " + items.size() + " Project(s)");


                ServiceList slist = client.services().list();
                if (slist == null) {
                    System.out.println("ERROR no list returned!");
                    return;
                }
                List<Service> sslists = slist.getItems();
                for (Service svc : sslists) {
                    System.out.println("Service " + svc.getMetadata().getName() + " has version: " + svc.getMetadata().getName());
                }
                System.out.println("Found " + items.size() + " Project(s)");



                NamespaceList nlist = client.namespaces().list();
                if (plist == null) {
                    System.out.println("ERROR no list returned!");
                    return;
                }
                List<Namespace> namespaces = nlist.getItems();
                for (Namespace np : namespaces) {
                    System.out.println("Namespace " +
                            np.getMetadata().getName() +
                            " has version: " +
                            np.getMetadata().getLabels() +
                            "netnamespace" + np.getSpec());
                }
                System.out.println("Found " + items.size() + " Project(s)");


                /*
                ResourceQuota quota = new ResourceQuotaBuilder().withNewMetadata().withName("pod-quota").endMetadata().withNewSpec().addToHard("pods", new Quantity("10")).endSpec().build();
                log("Create resource quota", client.resourceQuotas().inNamespace("demo").create(quota));
                */

                ResourceQuotaList  qlist = client.resourceQuotas().list();
                if (qlist == null) {
                    System.out.println("ERROR no list returned!");
                    return;
                }
                List<ResourceQuota> quotas = qlist.getItems();
                for (ResourceQuota rquota : quotas) {
                    System.out.println("Quota " +
                            rquota.getMetadata().getName() +
                            " has version: " +
                            rquota.getMetadata().getAdditionalProperties());
                }
                System.out.println("Found " + items.size() + " Quotas(s)");


                GroupList  glist = client.groups().list();
                if (glist == null) {
                    System.out.println("ERROR no list returned!");
                    return;
                }
                List<Group> groups = glist.getItems();
                for (Group group : groups) {
                    System.out.println("Group " +
                            group.getMetadata().getName() +
                            " has version: " +
                            group.getUsers());
                    log("Group", group.getMetadata().getName());
                }
                System.out.println("Found " + items.size() + " Group(s)");

                /* ClusterRoleBinding */


                ClusterRoleBindingList  rlist = client.clusterRoleBindings().list();
                if (rlist == null) {
                    System.out.println("ERROR no list returned!");
                    return;
                }


                List<ClusterRoleBinding> roles = rlist.getItems();
                for (ClusterRoleBinding role : roles) {
                    System.out.println("ClusterRoleBinding " +
                            role.getMetadata().getName() +
                            " has version: " +
                            role.getUserNames());
                    log("ClusterRoleBinding", role.getMetadata().getName());
                }
                System.out.println("Found " + items.size() + " ClusterRoleBinding(s)");



            } catch (KubernetesClientException e) {
                System.out.println("Failed: " + e);
                e.printStackTrace();
            }
        }

    }