package org.wxs.core.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.awt.*;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUtil {

    public static Boolean reductionFlag = false;

    private static String description = "fft BaseUtil:";

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 给定范围获得随机颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    public static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    // 不四舍五入 传入浮点数 和 小数点后保留几位
    public static double noRound(Double round, int bit) {
        try {
            String fbefore = round.toString().substring(0,
                    round.toString().indexOf("."));
            String fafter = round.toString().substring(
                    round.toString().indexOf(".") + 1,
                    round.toString().indexOf(".") + 1 + bit);
            String hut = fbefore + "." + fafter;
            return Double.parseDouble(hut);
        } catch (Exception e) {
            return round;
        }
    }

    // 不四舍五入 传入浮点数 和 小数点后保留几位
    public static String noRound(String round, int bit) {
        try {
            String fbefore = round.substring(0, round.indexOf("."));
            String fafter = round.substring(round.indexOf(".") + 1,
                    round.indexOf(".") + 1 + bit);
            String hut = fbefore + "." + fafter;
            return hut;
        } catch (Exception e) {
            return round;
        }
    }

    /**
     * 如果s = null 返回 ""; 如果s的长度大于length，则返回 前length-3长度的字串并加上\uFFFD?...\uFFFD?;
     * 否则返回 s 本身\uFFFD?
     *
     * @param s      String
     * @param length int
     * @return String
     */
    public static String toString(String s, int length) {
        if (s == null) {
            return "";
        } else if (s.length() > length) {
            return s.substring(1, length - 3) + "...";
        } else {
            return s;
        }
    }

    public static String toString(Date date, String formate) {
        DateFormat df = new SimpleDateFormat(formate);
        return (null == date) ? "" : df.format(date);
    }

    /**
     * 如果s = null return ""\uFFFD? 否则返回转换后的代码 例子：iso = "UTF-8"\uFFFD?
     *
     * @param s String iso String
     * @return String
     */
    public static String toString(String s, String iso) {
        if (s == null) {
            return "";
        } else {
            byte[] b = new byte[0];
            String out = "";
            try {
                // 中间用ISO-8859-1过渡
                b = s.getBytes("8859_1");
                out = new String(b, iso);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException(description, e);
            }
            return out;
        }
    }

    /**
     * 格式化为“yyyy-MM-dd”的字符\uFFFD?
     *
     * @param date Date
     * @return String
     */
    public static String toShortDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return (null == date) ? "" : df.format(date);
    }

    /**
     * 格式化为“yyyy-MM-dd HH:mm:ss”的字符\uFFFD?
     *
     * @param date Date
     * @return String
     */
    public static String toLongDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return (null == date) ? "" : df.format(date);
    }

    public static String toChinaDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        return (null == date) ? "" : df.format(date);
    }

    /**
     * 按字符串内容构\uFFFD?\uFFFD日期： <br>
     * 格式\uFFFD? “yyyy-mm-dd hh:mm:ss.fffffffff”，“yyyy-mm-dd hh:mm:ss\uFFFD?
     * 或\uFFFD?\uFFFDyyyy-mm-dd”有效\uFFFD??<br>
     * <br>
     * 如果 s ＝null 或\uFFFD?\uFFFD\uFFFD?\uFFFD，则返回null\uFFFD?<br>
     *
     * @param s String
     * @return Date
     */
    public static Date toDate(String s) {
        if (null == s || "".equals(s)) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = new Date();
            try {
                d = sdf.parse(s);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                throw new RuntimeException(description, e1);
            }
            return new Date(d.getTime());
        }
    }

    /**
     * 按字符串内容构\uFFFD?\uFFFD日期： <br>
     * 格式\uFFFD? “yyyy-mm-dd hh:mm:ss.fffffffff”，“yyyy-mm-dd hh:mm:ss\uFFFD?
     * 或\uFFFD?\uFFFDyyyy-mm-dd”有效\uFFFD??<br>
     * <br>
     * 如果 s ＝null 或\uFFFD?\uFFFD\uFFFD?\uFFFD，则返回null\uFFFD?<br>
     *
     * @param s String
     * @return Date
     */
    public static Date toDate(String s, String format) {
        if (null == s || "".equals(s)) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date d = new Date();
            try {
                d = sdf.parse(s);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                throw new RuntimeException(description, e1);
            }
            return new Date(d.getTime());

        }
    }

    /**
     * 取得enddate 之间 startdate的秒\uFFFD?
     *
     * @param startdate Date
     * @param enddate   Date
     * @return int
     */
    public static long getSeconds(Date startdate,
                                  Date enddate) {
        long time = enddate.getTime() - startdate.getTime();
        long totalS = new Long(time / 1000);
        return totalS;
    }

    /**
     * 取得一个传入日期days天后的日\uFFFD?
     *
     * @param d    Date
     * @param days int
     * @return Date
     */
    public static Date getAfterDateByDays(Date d, int days) {
        // SimpleDateFormat sdf = new SimpleDateFormat(patten); //格式化日期
        Date d2 = new Date();
        d2.setTime(d.getTime() + days * 24 * 60 * 60 * 1000);
        return d2;
    }

    /**
     * 取得一个传入日期days天前的日\uFFFD?
     *
     * @param d    Date
     * @param days int
     * @return Date
     */
    public static Date getBeforeDateByDays(Date d, int days) {
        // SimpleDateFormat sdf = new SimpleDateFormat(patten); //格式化日期
        Date d2 = new Date();
        d2.setTime(d.getTime() - days * 24 * 60 * 60 * 1000);
        return d2;
    }

    /**
     * 取得一个日期days天后的日\uFFFD?
     *
     * @param days int
     * @return Date
     */
    public static Date getAfterDateByDays(int days) {
        Calendar calValue = Calendar.getInstance();
        calValue.add(Calendar.DATE, days);
        return new Date(calValue.getTime().getTime());
    }

    /**
     * 取随机整数
     */
    public static int RandomInt(int range) {
        Random r = new Random();
        return r.nextInt(range);
    }

    /**
     * @param n 产生数字的位数
     * @return
     * @Enclosing_Method : randomByNum
     * @Written by : chenzh
     * @Creation Date : 2014-9-8 下午3:18:24
     * @version : v1.00
     * @Description : 随机产生n个数字
     **/
    public static String randomByNum(int n) {
        String s = "";
        while (s.length() < n)
            s += (int) (Math.random() * 10);
        return s;
    }

    public static String randomNum(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double process = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenString = String.format("%.0f", process);

        // 返回固定的长度的随机数
        return fixLenString.substring(1, strLength + 1);
    }

    /**
     * 去除字符串数组的空白元素
     *
     * @param arr
     */
    public static String[] deleteBlankStringArray(String[] arr) {
        if (arr != null && arr.length > 0) {
            List<String> tmp = new ArrayList<String>();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != null && !"".equals(arr[i].trim()))
                    tmp.add(arr[i]);
            }
            if (tmp.size() > 0) {
                String[] strings = new String[tmp.size()];
                return tmp.toArray(strings);
            } else
                return null;
        } else
            return null;
    }

    /**
     * 字符串数组转换整形数组
     *
     * @param arr
     * @return
     */
    public static Integer[] ConverterToIntegerArray(String[] arr) {
        if (arr != null && arr.length > 0) {
            Integer[] arrout = new Integer[arr.length];
            for (int i = 0; i < arr.length; i++) {
                arrout[i] = Integer.parseInt(arr[i]);
            }
            return arrout;
        } else {
            return null;
        }
    }

    /**
     * 字符串数组转换长整形数组
     *
     * @param arr
     * @return
     */
    public static Long[] ConverterToLongArray(String[] arr) {
        if (arr != null && arr.length > 0) {
            Long[] arrout = new Long[arr.length];
            for (int i = 0; i < arr.length; i++) {
                arrout[i] = Long.parseLong(arr[i]);
            }
            return arrout;
        } else {
            return null;
        }
    }

    /**
     * 字符串数组转换长整形数组
     *
     * @param arr
     * @return
     */
    public static Long[] ConverterToLongArray(Object[] arr) {
        if (arr != null && arr.length > 0) {
            Long[] arrout = new Long[arr.length];
            for (int i = 0; i < arr.length; i++) {
                arrout[i] = Long.parseLong(arr[i].toString());
            }
            return arrout;
        } else {
            return null;
        }
    }

    // 判断字符串是否是整数 正则
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    // 过滤HTML标签
    public static String Html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            throw new RuntimeException(description, e);
        }

        return textStr;// 返回文本字符串
    }

    /**
     * 传入查询条件，返回是否含有敏感词结果
     *
     * @param str
     * @return
     */
    public static boolean isBadwords(String str) {
        String badwords = "select|update|delete|count|*|sum|master|script|'|declare|execute|exec|alter|statement|executeQuery|count|executeUpdate";
        badwords = badwords.toLowerCase();
        str = str.toLowerCase();
        // System.out.println(str);
        String[] data = StringUtils.split(badwords, "|");
        for (int i = 0; i < data.length; i++) {
            if (str.indexOf(data[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

    // 保存文件
    public static void savefile(String path, StringBuffer content)
            throws IOException {
        FileOutputStream outFile = null;
        try {
            outFile = new FileOutputStream(path);
            outFile.write(content.toString().getBytes());
        } catch (Exception e) {
            throw new RuntimeException(description, e);
        } finally {
            if (outFile != null) {
                outFile.close();
            }
        }
    }

    public static String readFile(String fullPath, String iso) {
        File f0 = new File(fullPath);
        if (f0.exists()) {
            BufferedReader reader = null;
            FileInputStream fr = null;
            InputStreamReader isr = null;
            StringBuilder outs = new StringBuilder("");
            try {
                fr = new FileInputStream(f0);
                isr = new InputStreamReader(fr, iso);
                reader = new BufferedReader(isr);// 得到流对象
                String line = null;

                // 组装字符串
                while ((line = reader.readLine()) != null) {
                    outs.append(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(description, e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
                if (isr != null) {
                    try {
                        isr.close();
                    } catch (IOException e1) {
                    }
                }
                if (fr != null) {
                    try {
                        fr.close();
                    } catch (IOException e1) {
                    }
                }
            }
            return outs.toString();
        } else {
            return null;
        }
    }

    public static boolean SequenceSearch(String[] src, String str) {
        if (src != null && src.length > 0) {
            boolean flag = false;
            for (int i = 0; i < src.length; i++) {
                if (src[i].equals(str)) {
                    flag = true;
                    break;
                }
            }
            return flag;
        } else {
            return false;
        }
    }

    /**
     * html转义字符转中文
     *
     * @param htmlchar
     * @return
     */
    public static String htmlChar2CNString(String htmlchar) {
        String[] str = htmlchar.split(";");

        String sResult = "";
        for (int i = 0; i < str.length; i++) {
            if (!str[i].startsWith("&#x")) {
                String aa = str[i].substring(0, str[i].indexOf("&#x"));
                sResult += aa;
                int str2 = Integer.parseInt(str[i].replace(aa + "&#x", ""), 16);
                sResult += "" + (char) str2;
            } else {
                int str2 = Integer.parseInt(str[i].replace("&#x", ""), 16);
                sResult += "" + (char) str2;
            }
        }
        return sResult;
    }

    /**
     * 获取exception详情信息
     *
     * @param e Excetipn type
     * @return String type
     */
    public static String getExceptionDetail(Exception e) {

        return ExceptionUtils.getFullStackTrace(e);

    }

    public static XMLGregorianCalendar convertToXMLGregorianCalendar(
            Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return gc;
    }

    public static Date convertToDate(XMLGregorianCalendar cal)
            throws Exception {
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }

    /**
     * 获取Exception的栈信息，返回字符串
     *
     * @param e
     * @return
     */
    public static String getExceptionStackTrace(Exception e) {
        return ExceptionUtils.getFullStackTrace(e);
    }

    /**
     * @param identity 身份证
     * @return true 合法 , false 不合法
     * @Enclosing_Method : checkIdentity
     * @Written by : chenzh
     * @Creation Date : 2014-9-8 下午1:50:43
     * @version : v1.00
     * @Description : 校验身份证
     **/
    public static boolean checkIdentity(String identity) {
        // String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        if (identity == null) {
            return false;
        }
        // ================ 号码的长度 15位或18位 ================
        if (identity.length() != 15 && identity.length() != 18) {
            // errorInfo = "身份证号码长度应该为15位或18位。";
            return false;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (identity.length() == 18) {
            Ai = identity.substring(0, 17);
        } else if (identity.length() == 15) {
            Ai = identity.substring(0, 6) + "19" + identity.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            // errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return false;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            // errorInfo = "身份证生日无效。";
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                // errorInfo = "身份证生日不在有效范围。";
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            // errorInfo = "身份证月份无效";
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            // errorInfo = "身份证日期无效";
            return false;
        }
        // =====================(end)=====================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (identity.length() == 18) {
            if (!Ai.equals(identity.toLowerCase())) {
                // errorInfo = "身份证无效，不是合法的身份证号码";
                return false;
            }
        }
        // =====================(end)=====================
        return true;

    }

    /**
     * @param phoneNum 手机号
     * @return false 不合法 true 合法
     * @Enclosing_Method : checkMobilePhone
     * @Written by : chenzh
     * @Creation Date : 2014-9-14 下午12:46:34
     * @version : v1.00
     * @Description : 检查手机号是否合法
     **/
    public static boolean checkMobilePhone(String phoneNum) {
        return phoneNum.matches("^(13|15|17|18)\\d{9}$");
    }

    /**
     * 验证邮箱地址是否正确
     *
     * @param email
     * @return true 合法 false 不合法
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^[a-z0-9A-Z]([a-z0-9A-Z_]+[-|\\.]?)+[a-z0-9A-Z]@[a-z0-9A-Z]([a-z0-9A-Z_]+(-[a-z0-9A-Z_]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }

    /**
     * 验证手机号码
     *
     * @param mobiles
     * @return true 合法 false 不合法
     */
    public static boolean isMobileNO(String mobiles) {
        boolean flag = false;
        try {
            Pattern p = Pattern
                    .compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    public static boolean checkPasswordLevel(String passwordStr) {
        int flag = 0;
        String str = "[0-9]";       //不超过20位的数字组合
        String str2 = "[a-zA-Z]";         //由字母不超过20位

        for (int i = 0; i < passwordStr.length(); i++) {
            char chr = passwordStr.charAt(i);
            if (String.valueOf(chr).matches(str)) {
                flag++;
                break;
            }
        }
        for (int i = 0; i < passwordStr.length(); i++) {
            char chr = passwordStr.charAt(i);
            if (String.valueOf(chr).matches(str2)) {
                flag++;
                break;
            }
        }
        if (flag != 2) {
            return false;
        } else {
            return true;
        }

    }

    public static Set<Class<?>> getClasses(String pack) {

        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(
                    packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    System.err.println("file类型的扫描");
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath,
                            recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    System.err.println("jar类型的扫描");
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection())
                                .getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        // 同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx)
                                            .replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class")
                                            && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名
                                        String className = name.substring(
                                                packageName.length() + 1, name
                                                        .length() - 6);
                                        try {
                                            // 添加到classes
                                            classes.add(Class
                                                    .forName(packageName + '.'
                                                            + className));
                                        } catch (ClassNotFoundException e) {
                                            // log
                                            // .error("添加用户自定义视图类错误 找不到此类的.class文件");
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        // log.error("在扫描用户定义视图时从jar包获取文件出错");
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    public static void findAndAddClassesInPackageByFile(String packageName,
                                                        String packagePath, final boolean recursive, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory())
                        || (file.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "."
                                + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0,
                        file.getName().length() - 6);
                try {
                    // 添加到集合中去
                    //classes.add(Class.forName(packageName + '.' + className));
                    //经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    // log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                    e.printStackTrace();
                }
            }
        }
    }

    // 去除 所有 制表符 空格 回车
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    public static String JAXBMarshal(Object obj) throws IOException, JAXBException {
        String xml = "";
        try (StringWriter sw = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(obj, sw);
            xml = sw.toString();
        }
        return xml;
    }

    public static <T> T JAXBUnMarshal(String xml, Class<T> cls) throws JAXBException {
        try (StringReader sr = new StringReader(xml)) {
            JAXBContext context = JAXBContext.newInstance(cls);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(sr);
        }
    }

    public static XMLGregorianCalendar getXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND),
                DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
        return xmlDate;
    }

    public static String createTmpTable(String tmpTableName, Map<String, Object> keys) {

        String createSql = "";
        createSql += "create temporary table `" + tmpTableName + "` (\n";
        int index = 1;
        for (Map.Entry<String, Object> entry : keys.entrySet()) {
            String key = entry.getKey();
            Object keyLength = entry.getValue();
            if (keyLength instanceof String) {
                createSql += "`" + key + "` varchar(" + keyLength + ") not null";
            } else if (keyLength instanceof Integer) {
                createSql += "`" + key + "` int(" + keyLength + ") not null";
            } else if (keyLength instanceof Long) {
                createSql += "`" + key + "` bigint(" + keyLength + ") not null";
            } else if (keyLength instanceof Float) {
                String[] sFloat = keyLength.toString().split("\\.");
                createSql += "`" + key + "` float(" + sFloat[0] + ", " + sFloat[1] + ") not null";
            } else if (keyLength instanceof java.sql.Timestamp) {
                createSql += "`" + key + "` datetime not null";
            } else if (keyLength instanceof Date) {
                createSql += "`" + key + "` date not null";
            }
            if (index == keys.size()) {
                createSql += " \n";
            } else {
                createSql += ", \n";
            }
            index++;
        }
        createSql += ") \n";
        createSql += "engine=InnoDB \n";
        createSql += "default character set=utf8 collate=utf8_unicode_ci \n";
        return createSql;
    }

    public static String createTmpTable2(String tmpTableName, Map<String, Object> keys) {

        String createSql = "";
        createSql += "create temporary table `" + tmpTableName + "` (\n";
        int index = 1;
        for (Map.Entry<String, Object> entry : keys.entrySet()) {
            String key = entry.getKey();
            Object keyLength = entry.getValue();
            if (keyLength instanceof String) {
                createSql += "`" + key + "` varchar(" + keyLength + ") ";
            } else if (keyLength instanceof Integer) {
                createSql += "`" + key + "` int(" + keyLength + ") ";
            } else if (keyLength instanceof Long) {
                createSql += "`" + key + "` bigint(" + keyLength + ") ";
            } else if (keyLength instanceof Float) {
                String[] sFloat = keyLength.toString().split("\\.");
                createSql += "`" + key + "` float(" + sFloat[0] + ", " + sFloat[1] + ") ";
            } else if (keyLength instanceof java.sql.Timestamp) {
                createSql += "`" + key + "` datetime ";
            } else if (keyLength instanceof Date) {
                createSql += "`" + key + "` date";
            }
            if (index == keys.size()) {
                createSql += " \n";
            } else {
                createSql += ", \n";
            }
            index++;
        }
        createSql += ") \n";
        createSql += "engine=InnoDB \n";
        createSql += "default character set=utf8 collate=utf8_unicode_ci \n";
        return createSql;
    }

    public static String createInsertSelect(String intoTableName, String selectTableName, String keys) {
        String createSql = "insert into " + intoTableName + "(" + keys + ") select " + keys + " from " + selectTableName;
        return createSql;
    }

    public static String createInsertValues(String intoTableName, Map<String, Object> keyValues) {

        String columns = "";
        String values = "";
        int index = 1;
        for (Map.Entry entry : keyValues.entrySet()) {
            String column = entry.getKey().toString();
            Object value = entry.getValue();
            columns += column;
            if (value instanceof String) {
                values += "'" + value + "'";
            } else {
                values += value;
            }
            if (index != keyValues.size()) {
                columns += ", ";
                values += ", ";
            }
            index++;
        }
        String createSql = "insert into " + intoTableName + "(\n";
        createSql += columns;
        createSql += ") \n";
        createSql += "values (\n";
        createSql += values;
        createSql += ")\n";
        return createSql;
    }

    /**
     * 传入一个表名字的list返回drop table 语句;
     *
     * @param tablesList
     * @return
     */
    public static String createDropTablesSQL(List<String> tablesList) {
        if (tablesList == null || tablesList.isEmpty()) {
            return "";
        }
        String out = "drop table";
        for (String table : tablesList) {
            out += " `" + table + "`, ";
        }
        return out.substring(0, out.length() - 2);
    }

    /**
     * bean 转 map
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Map convertBeanToMap(Object bean) throws Exception {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    public static String toJson(Object obj)  {
        try{
            return objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
            return null;
    }

    public static <T> T parseJson(String json, Class<T> clazz)  {
        try{
            return objectMapper.readValue(json, clazz);
        } catch ( IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public static String objectFieldToString(Object object) {
        if (object == null) {
            return null;
        }
        try {
            Field[] f = object.getClass().getDeclaredFields();
            MoreObjects.ToStringHelper toStringHelper = MoreObjects.toStringHelper(object);
            for (Field field : f) {
                field.setAccessible(true);
                Object fildObj = field.get(object);
                if (fildObj != null) {
                    if (fildObj instanceof Date)
                        toStringHelper.add(field.getName(), BaseUtil.toLongDate((Date) fildObj));
                    else
                        toStringHelper.add(field.getName(), fildObj);
                }
            }
            return toStringHelper.toString();
        } catch (Exception e) {
        }
        return object.toString();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //获取传入时间是星期几
    public static String getDayOfWeek(Date date){
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"日");
        map.put(2,"一");
        map.put(3,"二");
        map.put(4,"三");
        map.put(5,"四");
        map.put(6,"五");
        map.put(7,"六");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Integer dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return "星期"+ map.get(dayOfWeek);
    }

}
