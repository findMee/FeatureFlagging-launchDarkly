package com.example.demolaunchdarkly;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/features")
public class FeaturesController {

    @Autowired
    private FeatureService featureService;

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Boolean> getFeatures(@RequestParam(name = "userName")String userName) {
        return featureService.getFeatures(userName);
    }
}
