package com.example.demolaunchdarkly;

import java.io.IOException;

import com.launchdarkly.sdk.LDUser;
import com.launchdarkly.sdk.LDValue;
import com.launchdarkly.sdk.server.LDClient;

public class FeatureMain {

    private static final LDClient ldClient = new LDClient("");      //Add LDClient value here.
    public static void main(String[] args) throws IOException {
        LDUser user = new LDUser.Builder("UNIQUE IDENTIFIER")
                .firstName("Bob")
                .lastName("Loblaw")
                .custom("groups", LDValue.buildArray().add("beta_testers").build())
                .build();

        boolean showFeature = ldClient.boolVariation("publish-flow-feature", user, true);

        if (showFeature) {
            System.out.println("Showing your feature");
        } else {
            System.out.println("Not showing your feature");
        }

        ldClient.close();
    }

    public static LDClient getLdClient() {
        return ldClient;
    }
}
