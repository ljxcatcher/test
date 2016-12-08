package com.hawk.commentcenter.service.impl;

import com.hawk.commentcenter.service.GreetService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @author junxiong.lang
 * @date 2016/11/29 10:12
 */
@Service
public class GreetServiceImpl implements GreetService{
    public void sayHi() {
        System.out.println("hi");
    }

    public void sayBye() {
        System.out.println("bye");
    }

    public void sayAll() {
//        ((GreetService)AopContext.currentProxy()).sayHi();
        sayHi();
        sayBye();
    }
}
