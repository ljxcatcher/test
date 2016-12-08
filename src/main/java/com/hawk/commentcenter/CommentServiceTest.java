package com.hawk.commentcenter;

import com.hawk.commentcenter.model.Comment;
import com.hawk.commentcenter.model.CommentCms;
import com.hawk.commentcenter.model.CommentCount;
import com.hawk.commentcenter.model.StarsInfo;
import com.hawk.commentcenter.pagination.CommentPagination4Api;
import com.hawk.commentcenter.pagination.CommentPagination4Cms;
import com.hawk.commentcenter.pagination.CommentPaginationResult;
import com.hawk.commentcenter.service.CommentService;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author junxiong.lang
 * @date 2016/11/15 10:04
 */
public class CommentServiceTest extends BaseTest{
    private CommentService commentService = produceService(CommentService.class);
    @Test
    public void testFindPage4Cms() throws Exception {
        CommentPagination4Cms commentPagination4Cms = new CommentPagination4Cms();
        commentPagination4Cms.setPageNo(1);
        commentPagination4Cms.setPageSize(10);

        CommentPaginationResult<CommentCms> result = commentService.findPage4Cms(commentPagination4Cms);
        System.out.println("记录总数：" + result.getTotalCount());
        for (CommentCms commentCms : result.getItems()) {
            System.out.println(commentCms);
        }
    }

    @Test
    public void testFind4Cms() throws Exception {
        CommentPagination4Cms commentPagination4Cms = new CommentPagination4Cms();
        commentPagination4Cms.setPageNo(5);
        commentPagination4Cms.setPageSize(10);

        List<CommentCms> commentCmses = commentService.find4Cms(commentPagination4Cms);
        for (CommentCms commentCms : commentCmses) {
            System.out.println(commentCms);
        }
    }

    @Test
    public void testFindCount4Cms() throws Exception {
        CommentPagination4Cms commentPagination4Cms = new CommentPagination4Cms();
        commentPagination4Cms.setPageNo(5);
        commentPagination4Cms.setPageSize(10);

        Integer totalCount = commentService.findCount4Cms(commentPagination4Cms);
        System.out.println("记录总数：" + totalCount);
    }

    @Test
    public void testFindUnauditedCount4Cms() throws Exception {
        Integer unauditedCount = commentService.findUnauditedCount4Cms();

        System.out.println("评论表未审核数量：" + unauditedCount);
    }

    @Test
    public void testDelete4Cms() throws Exception {
        String ids = "23,";
        String appIds = "22,";

        commentService.delete4Cms(ids, appIds);
    }

    @Test
    public void testVerify4Cms() throws Exception {
        String ids = "4,6";
        String appIds = "22,22";
        Integer verifyStatus = 1;

        commentService.verify4Cms(ids, appIds, verifyStatus);
    }

    @Test
    public void testFindPage4Api() throws Exception {
        Integer appId = 1002;
        Integer pageNo = 1;
        Integer pageSize = 10;
        CommentPagination4Api pagination = new CommentPagination4Api();
        pagination.setAppId(appId);
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);

        CommentPaginationResult<Comment> result = commentService.findPage4Api(pagination);
        Integer totalCount = result.getTotalCount();
        List<Comment> items = result.getItems();

        System.out.println("总记录数：" + totalCount);
        for (Comment comment : items) {
            System.out.println(comment.toString());
        }
    }

    @Test
    public void testFind4Api() throws Exception {
        Integer appId = 1002;
        Integer pageNo = 1;
        Integer pageSize = 10;
        CommentPagination4Api pagination = new CommentPagination4Api();
        pagination.setAppId(appId);
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);

        for (int i = 1; i < 6; i++) {
            pagination.setPageNo(i);
            List<Comment> comments = commentService.find4Api(pagination);
            for (Comment comment : comments) {
                System.out.println(comment.toString());
            }
        }

    }

    @Test
    public void testFindCount4Api() throws Exception {
        Integer appId = 1003;
        Integer pageNo = 1;
        Integer pageSize = 10;
        CommentPagination4Api pagination = new CommentPagination4Api();
        pagination.setAppId(appId);
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);

        Integer totalCount = commentService.findCount4Api(pagination);
        System.out.println("总记录数：" + totalCount);
    }

    @Test
    public void testFindAllStarsInfo4Api() throws Exception {
        Integer appId = null;
        List<StarsInfo> allStarsInfo = commentService.findAllStarsInfo4Api(appId);
        for (StarsInfo starsInfo : allStarsInfo) {
            System.out.println(starsInfo);
        }
    }

    @Test
    public void testFindStarsInfo4Api() throws Exception {
        Integer appId = 22;
        List<StarsInfo> starsInfo = commentService.findStarsInfo4Api(appId);
        for (StarsInfo info : starsInfo) {
            System.out.println(info);
        }
    }

    @Test
    public void testSave4Api() throws Exception {
        Comment comment = new Comment();
        comment.setAppId(1002);
        comment.setUsername("逻辑学");
        comment.setVerifyStatus(0);
        comment.setMessage("这个应用还不错哦".getBytes());
        comment.setStars(3);
        comment.setCommitTime(new Date());

        commentService.save4Api(comment);
    }

    @Test
    public void testFind4WebMob() throws Exception {
        Integer appId = 22;
        List<Comment> comments = commentService.find4WebMob(appId);

        for (Comment comment : comments) {
            System.out.println(comment.toString());
        }
    }

    @Test
    public void testFind4OpenApi() throws Exception {
        Date startTime = new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
        List<CommentCount> commentCounts = commentService.find4OpenApi(startTime);
        for (CommentCount commentCount : commentCounts) {
            System.out.println(commentCount.toString());
        }
    }
}