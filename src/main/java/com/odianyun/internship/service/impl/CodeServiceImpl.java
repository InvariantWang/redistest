package com.odianyun.internship.service.impl;

import com.odianyun.internship.mapper.CodeMapper;
import com.odianyun.internship.model.VO.CodeVO;
import com.odianyun.internship.service.CodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CodeServiceImpl implements CodeService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CodeMapper codeMapper;

    public static final String CACHE_KEY_CATEGORY_STRING = "category:string";
    public static final String CACHE_KEY_CATEGORY_HASH = "category:hash";
    public static final String COLON = ":";

    private HashOperations<String, String, String> hashOperations;
    private ValueOperations<String, List<CodeVO>> valueOperations;
    @Override
    public String getNameByCode(String pool, String category, String code) {
        String hashKey = buildHashKey(pool, category);
        String name = getHashOperations().get(hashKey, code);
        if (StringUtils.isBlank(name)) {
            List<CodeVO> codeList = cacheCategory(pool, category, hashKey, buildStringKey(pool, category));
            if (!CollectionUtils.isEmpty(codeList)) {
                name = getHashOperations().get(hashKey, code);
            }
        }
        return name;
    }

    @Override
    public List<CodeVO> listByCategory(String pool,String category) {
        ValueOperations<String, List<CodeVO>> operations = redisTemplate.opsForValue();
        String key = "cache:category:" + pool + ":" + category;

        List<CodeVO> list = operations.get(key);
        if(!CollectionUtils.isEmpty(list)){
            return list;
        }

        list = codeMapper.listByCategory(pool, category);
        if(!CollectionUtils.isEmpty(list)) {
            operations.set(key, list);
        }

        return list;
    }

    /**
     * 缓存Category
     * @param pool
     * @param category
     * @return
     */
    private List<CodeVO> cacheCategory(String pool,String category,String hashKey,String stringKey) {
        List<CodeVO> codeList = codeMapper.listByCategory(pool, category);
        if (CollectionUtils.isEmpty(codeList)) {
            return codeList;
        }

        codeList.forEach(item -> getHashOperations().putIfAbsent(hashKey, item.getCode(), item.getName()));
        getValueOperations().set(stringKey, codeList);
        return codeList;
    }

    private String buildHashKey(String pool, String category) {
        return CACHE_KEY_CATEGORY_HASH + pool + COLON + category;
    }

    private String buildStringKey(String pool, String category) {
        return CACHE_KEY_CATEGORY_STRING + pool + COLON + category;
    }

    public HashOperations<String, String, String> getHashOperations() {
        if (null == hashOperations) {
            return redisTemplate.opsForHash();
        }
        return hashOperations;
    }

    public ValueOperations<String, List<CodeVO>> getValueOperations() {
        if (null == valueOperations) {
            return redisTemplate.opsForValue();
        }
        return valueOperations;
    }

}
