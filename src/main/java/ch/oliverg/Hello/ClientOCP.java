package ch.oliverg.Hello;


import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.openshift.api.model.ImageStream;
import io.fabric8.openshift.api.model.ImageStreamList;

import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.Namespace;

import io.fabric8.kubernetes.api.model.ServiceList;

import io.fabric8.openshift.api.model.Project;
import io.fabric8.openshift.api.model.ProjectList;

import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftAPIGroups;
import io.fabric8.openshift.client.OpenShiftClient;

import java.util.List;

public class ClientOCP {
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
                            np.getMetadata().getAdditionalProperties());
                }
                System.out.println("Found " + items.size() + " Project(s)");



            } catch (KubernetesClientException e) {
                System.out.println("Failed: " + e);
                e.printStackTrace();
            }
        }

    }