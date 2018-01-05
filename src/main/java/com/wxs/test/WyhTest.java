package com.wxs.test;

import com.wxs.enu.EnuDynamicTypeCode;
import com.wxs.enu.EnumClassworkCompletion;
import org.wxs.core.util.BaseUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/19.
 */
public class WyhTest {

    public static void main(String[] args) {

        int a = -1;
        byte b = 0;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));

//        int arr[][] = {{1,2,3},{4,5,6,7},{9}};
//        boolean found = false;
//        for(int i=0;i<arr.length && !found;i++){
//            for(int j=0;j<arr[i].length;j++){
//                System.out.println("i=" + i + ",j=" + j);
//                if(arr[i][j] == 5) {
//                    found = true;
//                    break;
//                }
//            }
//        }
//        int  temp = 0x31 & 0x0f;
        System.out.println(EnumClassworkCompletion.NOT_HAND_IN.getTypeCode());

//        Calendar c = Calendar.getInstance();
//        c.setTime(BaseUtil.toDate("2017-12-17 00:00:00"));
//        System.out.println(c.get(Calendar.DAY_OF_WEEK));
//        c.setTime(BaseUtil.toDate("2017-12-16 00:00:00"));
//        System.out.println(c.get(Calendar.DAY_OF_WEEK));

//        System.out.println(BaseUtil.getDayOfWeek(BaseUtil.toDate("2017-12-17 00:00:00")));
//        System.out.println(BaseUtil.getDayOfWeek( new Date() ));
    }
}
