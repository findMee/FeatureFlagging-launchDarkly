package com.example.demolaunchdarkly;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.launchdarkly.sdk.LDUser;
import com.launchdarkly.sdk.LDValue;

@Service
public class UserService {
    private Map<String, LDUser> userMap = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostConstruct
    private void init() {
        logger.info("Building LDUserMap");

        userMap.put("rohith", buildLDUser("a001", "rohith", "rohith@fisd.com", "fisd"));
        userMap.put("rahul", buildLDUser("a002", "rahul", "rahul@fisd.com", "fisd"));
        userMap.put("paran", buildLDUser("a010", "paran", "paran@freen.com", "freen"));
        userMap.put("jyoth", buildLDUser("a011", "jyoth", "jyoth@freen.com", "freen"));
        userMap.put("shrel", buildLDUser("a020", "shrel", "shrel@4iTECH.com", "4iTECH"));
        userMap.put("Tom", buildLDUser("a021", "Tom", "Tom@4iTECH.com", "4iTECH"));

        logger.debug("Building LDUserMap done....!");
    }

    private LDUser buildLDUser(String key, String name, String email, String group) {
        return new LDUser.Builder(key)
                .name(name)
                .email(email)
                .custom("groups", LDValue.buildArray().add(group).build())
                .build();
    }

    public LDUser getUser(String userName) {
        return userMap.getOrDefault(userName, userMap.get("user1"));
    }
}
