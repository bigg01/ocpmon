/**
 * Copyright (C) 2015 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.oliverg.OcpMon.services;

import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.openshift.api.model.*;
import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftClient;

public class OcpWatchRouter {
  public static void main(String[] args) {
    try {
      OpenShiftClient client = new DefaultOpenShiftClient();
      String namespace = client.getNamespace();
      System.out.println("Watching Route in namespace " + namespace);
      //try (Watch watchable = client.routes().inAnyNamespace().withLabel("network-zone=v12").watch(new Watcher<Route>() {
      try (Watch watchable = client.routes().inAnyNamespace().watch(new Watcher<Route>() {
        @Override
        public void eventReceived(Action action, Route resource) {
          System.out.println(">> Action: " + action + " on Route " + resource.getMetadata().getName()
                  + " Host: " + resource.getSpec().getHost());
        }

        @Override
        public void onClose(KubernetesClientException cause) {
          System.out.println("Watch Closed: " + cause);
          if (cause != null) {
            cause.printStackTrace();
          }
        }
      })) {
        System.out.println("Created watchable " + watchable);
      }
    } catch (KubernetesClientException e) {
      System.out.println("Failed: " + e);
      e.printStackTrace();
    }
  }

}