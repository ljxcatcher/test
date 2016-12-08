package com.hawk.commentcenter;

import com.hawk.commentcenter.service.CommentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author junxiong.lang
 * @date 2016/11/14 17:20
 */
public class BaseTest {
    private long beginTime;
    private long endTime;
    private static ClassPathXmlApplicationContext ctx;
    private static CommentService commentService;

    static {
        ctx = new ClassPathXmlApplicationContext("consumer.xml");
        ctx.start();
        commentService = ctx.getBean(CommentService.class);
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public CommentService produceService(Class clazz) {
        if(clazz == CommentService.class) {
            return commentService;
        }

        return null;
    }

    @Before
    public void testBegin(){
        this.setBeginTime(System.currentTimeMillis());
    }

    @After
    public void testEnd(){
        this.setEndTime(System.currentTimeMillis());
        System.out.println("#######################################################");
        System.out.println("elapsed time : "+(this.getEndTime()-this.getBeginTime())+"ms");
        System.out.println("#######################################################");
    }


}
