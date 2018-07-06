package me.robbie.spring.grovvy.service;

import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/6/13 下午6:19
 * @since [产品/模块版本]
 */
@Service
public class RoomService {


    public boolean exist(int count) {
        return count%2 ==0;
    }


    @PreDestroy
    public void destroy(){
        System.out.println("RoomService destroy");
    }
}
