package com.example.demolaunchdarkly;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.launchdarkly.sdk.server.LDClient;

@Service
public class FeatureService {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(FeatureService.class);

    public Map<String, Boolean> getFeatures(String userName) {
        LDClient ldClient = FeatureMain.getLdClient();

        logger.info("Query {}", System.currentTimeMillis());
        boolean publishFeature = ldClient.boolVariation(FeatureKeyConstants.PUBLISH_FEATURE, userService.getUser(userName), true);
        logger.info("Query {}", System.currentTimeMillis());
        if (publishFeature) {
            logger.info("Publish feature is enabled for {}",userName);
        } else {
            logger.info("Publish feature is not enabled for {}", userName);
        }

        boolean vcsFeature = ldClient.boolVariation(FeatureKeyConstants.VCS_FEATURE, userService.getUser(userName), false);

        if (vcsFeature) {
            logger.info("Vcs feature is enabled for {}",userName);
        } else {
            logger.info("Vcs feature is not enabled for {}",userName);
        }

        boolean widgetsFeature = ldClient.boolVariation(FeatureKeyConstants.WIDGETS_DEVELOPMENT, userService.getUser(userName), false);
        if (widgetsFeature) {
            logger.info("Widgets development feature is enabled for {}",userName);
        } else {
            logger.info("Widgets development feature is not enabled for {}",userName);
        }

        Map<String, Boolean> featuresMap = new HashMap<>();
        featuresMap.put(FeatureKeyConstants.PUBLISH_FEATURE, publishFeature);
        featuresMap.put(FeatureKeyConstants.VCS_FEATURE, vcsFeature);
        featuresMap.put(FeatureKeyConstants.WIDGETS_DEVELOPMENT, widgetsFeature);

        return featuresMap;
    }
}
