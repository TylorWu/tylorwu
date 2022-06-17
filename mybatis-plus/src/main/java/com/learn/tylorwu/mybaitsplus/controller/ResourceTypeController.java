package com.learn.tylorwu.mybaitsplus.controller;

import com.learn.tylorwu.mybaitsplus.application.ResourceTypeQueryService;
import com.learn.tylorwu.mybaitsplus.entity.ResourceTypeDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class ResourceTypeController {
    @Resource
    private ResourceTypeQueryService resourceTypeQueryService;

    @PostMapping("/getResourceType")
    public List<ResourceTypeDO> getResourceType() {
       return resourceTypeQueryService.getResourceType(ResourceTypeDO.builder().resourceTypeLevel("2").build());
    }
}
