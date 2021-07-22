package com.odianyun.internship;
import com.odianyun.internship.startup.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author wxj
 * @date 2021/7/22 - 16:59
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

    @Resource
    private RedisTemplate<String,Serializable> redisTemplate;


    @Test
    public void testHostSearch() {
        ZSetOperations<String,Serializable> operations =redisTemplate.opsForZSet();
        String key = "weibo:hotSearch";
        operations.add(key, "河南洪涝致33人遇难，8人失踪", 99);
        operations.add(key, "河南暴雨互助", 95);
        operations.add(key, "暴雨之下的平凡英雄", 90);
        operations.add(key, "河南急需这些物资", 85);
        operations.add(key, "郑州地铁12人遇难 事故原因公布", 80);
        operations.add(key, "中国法庭", 75);
        operations.add(key, "河南加油河南挺住", 70);
        operations.add(key, "郑州高铁站希岸酒店涨价到2888", 65);
        operations.add(key, "河南为啥三天下了一整年雨", 60);
        operations.add(key, "河南暴雨下的中国人", 50);

        System.out.println("从高到低 == " + operations.reverseRange(key, 0 ,9));
        System.out.println("从低到高 == " + operations.range(key, 0 ,9));

        operations.remove(key, "新疆百万个馕驰援河南");

        System.out.println("从高到低 == " + operations.reverseRange(key, 0 ,9));

        operations.incrementScore(key, "暴雨汛情最新辟谣", 30);

        System.out.println("从高到低 == " + operations.reverseRange(key, 0 ,9));
    }

}

