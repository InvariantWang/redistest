package com.odianyun.internship.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.odianyun.internship.mapper.UUserIdentityMapper;
import com.odianyun.internship.model.DTO.UUserIdentityDTO;
import com.odianyun.internship.model.PO.UUserIdentityPO;
import com.odianyun.internship.service.UUserIdentityService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wxj
 * @date 2021/7/22 - 16:54
 */
@Service
public class UUserIdentityServiceImpl implements UUserIdentityService {
    @Resource
    private UUserIdentityMapper uUserIdentityMapper;
    @Resource
    private RedisTemplate redisTemplate;
    public static final String CACHE_USER_LABEL="user:label:";


    @Override
    public List<String> listUserLabelById(Long userId) {
        String key=CACHE_USER_LABEL + userId;
        ListOperations<String,String> operations=redisTemplate.opsForList();
        List<String> result=operations.range(key,0,-1);
        if (!CollectionUtils.isEmpty(result)) {
            return result;
        }
        String userLabel=uUserIdentityMapper.getUserLabel(userId);
        if (StringUtils.isEmpty(userLabel)) {
            result = JSONObject.parseArray(userLabel, String.class);
            operations.leftPushAll(key, result);
        }


        return result;
    }

    @Override
    public void updateUserLabel1(UUserIdentityDTO dto) {
        UUserIdentityPO po=new UUserIdentityPO();
        po.setUserId(dto.getUserId());
        po.getextField1list(JSONObject.toJSONString(dto.getExtField1List()));
        int row=uUserIdentityMapper.updateUserLabel(po);
        if (row>0){
            String key=CACHE_USER_LABEL + dto.getUserId();
            ListOperations<String,String>operations=redisTemplate.opsForList();
            redisTemplate.delete(key);
            operations.leftPushAll(key,dto.getExtField1List());
        }
    }
}
