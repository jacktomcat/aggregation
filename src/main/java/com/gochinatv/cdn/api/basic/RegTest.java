package com.gochinatv.cdn.api.basic;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2018-12-23 下午8:28
 */
public class RegTest {

    public static void main(String[] args) {
        getString();

    }


    /**
     * 正则表达式校验是否匹配
     */
    public static void validateMatch(){

        //匹配是否是手机
        String tel = "13419459630";
        boolean match = tel.matches("1[358][0-9]{9}");
        System.out.println(match);


        match = tel.matches("1[358]\\d{9}");
        System.out.println(match);

        //以上两种方式都可以
    }


    /**
     * 正则表达式切割string
     */
    public static void splitString(){

        //按空格分隔
        String names = "zhangsan      lisi         wangwu";

        //这里不能只写空格 " ",需要加上 + ,因为字符串中空格有很多个
        String[] result  = names.split(" +");
        Arrays.stream(result).forEach(System.out::println);
        /**
         * zhangsan
         * lisi
         * wangwu
         */


         //下面是针对叠词进行切割,下面表示任意字符串第一组出现1词以上的进行分隔
         names = "zhangsantttlisizzzzzwangwu";
         result = names.split("(.)\\1+");
         Arrays.stream(result).forEach(System.out::println);
        /**
         * wangwu
         *zhangsan
         *lisi
         *wangwu
         */
    }



    /**
     * 正则表达式替换string
     */
    public static void replaceString(){
        //叠词替换成#
        String names = "zhangsantttlisizzzzzwangwu";

        names = names.replaceAll("(.)\\1+","#");
        System.out.println(names);
        /**
         * zhangsan#lisi#wangwu
         */

        //把很多叠词替换成一个字符，用$1,表示第一组的字符
        String names1 = "zhangsantttlisizzzzzwangwu";
        names1 = names1.replaceAll("(.)\\1+","$1");
        System.out.println(names1);
        /**
         * zhangsantlisizwangwu
         */

        /**
         * 把手机号的中间4位用*替换，用到分组的思想
         */
        String phone  = "13423459821";
        phone  = phone.replaceAll("(\\d{3})(\\d{4})(\\d{4})","$1****$3");
        System.out.println(phone);
    }



    /**
     * 正则表达式获取string
     */
    public static void getString(){
        //获取字符串
        String names = "da jia hao, ming tian bu shang ban";

        /**
         * 这里\b 表示的是单词边界,如果不加\b 则把单词进行了切割
         */
        Pattern pattern = Pattern.compile("\\b[a-z]{3}\\b");
        Matcher matcher = pattern.matcher(names);

        while(matcher.find()){//必须先find
            System.out.println(matcher.group());
        }

        /**
         * jia
         * hao
         * ban
         */
    }

}
