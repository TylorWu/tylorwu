package com.learn.tylorwu.mybaitsplus.application;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.learn.tylorwu.mybaitsplus.dao.ResourceTypeMapper;
import com.learn.tylorwu.mybaitsplus.entity.ResourceTypeDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResourceTypeQueryService {
    @Resource
    private ResourceTypeMapper resourceTypeMapper;

    public List<ResourceTypeDO> getResourceType(ResourceTypeDO resourceType) {
        return resourceTypeMapper.selectList(Wrappers.lambdaQuery(ResourceTypeDO.class)
                .eq(ResourceTypeDO::getResourceTypeLevel, resourceType.getResourceTypeLevel()));
    }
}
