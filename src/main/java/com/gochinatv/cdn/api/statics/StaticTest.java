package com.gochinatv.cdn.api.statics;

import java.util.HashSet;
import java.util.Set;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2018-11-25 下午12:04
 */
public class StaticTest {

    private  int age=10;
    private static int name=10;

    public static void main(String[] args) {
        StaticRole sr = new StaticRole();
        StaticStudent ss = new StaticStudent();
        ss.setId(1);

        StaticStudent ss1 = new StaticStudent();
        ss1.setId(2);

        Set<StaticStudent> sets = new HashSet<>();
        sets.add(ss);
        sets.add(ss1);
        System.out.println(sets.size());
    }


    static class StaticStudent{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static void print(){}
    }


    class StaticMemeber {

        void test(){}

    }

}


final class StaticRole{
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}



class StaticUser {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
