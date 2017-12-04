package com.wxs.test;
import com.wxs.cache.IGeoRedisService;
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
       System.out.println("==" +BaseUtil.toJson(courseCategoryService.getAllCategoryOfOrgan(organId)));
    }

    @Test
    public void test3(){
        System.out.println(BaseUtil.toJson(organizationService.getOrganOutline(1L,1L)));
    }

    @Autowired
    private IGeoRedisService geoRedisService;
    @Test
    public void test2(){
        geoRedisService.addGeo("wanxueshe_zhengzhou_organ","001",34.7668210000,113.7765420000); //绿地之窗
        geoRedisService.addGeo("wanxueshe_zhengzhou_organ","002",34.7457240000,113.7551150000); //五洲小区
        geoRedisService.addGeo("wanxueshe_zhengzhou_organ","003",34.7652430000,113.7855630000); //高铁站
        geoRedisService.addGeo("wanxueshe_zhengzhou_organ","004",34.7029350000,113.6918770000);//南三环

        System.out.println("高铁站与绿地之窗距离为："+ geoRedisService.geodist("wanxueshe_zhengzhou_organ","001","002"));


    }
}
