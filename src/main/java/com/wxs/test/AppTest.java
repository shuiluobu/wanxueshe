package com.wxs.test;
import com.wxs.service.course.ITCourseCategoryService;
import com.wxs.service.organ.ITOrganizationService;
import com.wxs.util.Result;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxs.core.util.BaseUtil;

/**
 * Created by Administrator on 2017/9/23 0023.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {
    @Autowired
    private ITOrganizationService organizationService;
    @Autowired
    private ITCourseCategoryService courseCategoryService;
    @Test
    public void test1(){
        Long organId = 1L;
       //System.out.println(BaseUtil.toJson(Result.of(organizationService.selectById(organId))));
       System.out.println("==" +BaseUtil.toJson(Result.of(courseCategoryService.getAllCategoryOfOrgan(organId))));
    }
}
