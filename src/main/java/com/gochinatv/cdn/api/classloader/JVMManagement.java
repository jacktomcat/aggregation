package com.gochinatv.cdn.api.classloader;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @auhtor jacktomcat
 * @create 2018-12-22 下午11:04
 */
public class JVMManagement {


    public static void main(String[] args) {

        List<Test> l = new ArrayList<Test>();
        int count = 0;
        while(true) {
            try {

                MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
                System.out.println("jvm.heap.init is before " + (heapMemoryUsage.getInit()));
                System.out.println("jvm.heap.used is before " + (heapMemoryUsage.getUsed()));
                System.out.println("jvm.heap.committed is before " + (heapMemoryUsage.getCommitted()));
                System.out.println("jvm.heap.max is before " + (heapMemoryUsage.getMax()));
                System.out.println("------------------------------");
                Thread.sleep(1);
                l.add(new Test());
                count++;
                /*heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
                System.out.println("jvm.heap.init is after " + (heapMemoryUsage.getInit()));
                System.out.println("jvm.heap.used is after " + (heapMemoryUsage.getUsed()));
                System.out.println("jvm.heap.committed is after " + (heapMemoryUsage.getCommitted()));
                System.out.println("jvm.heap.max is after " + (heapMemoryUsage.getMax()));

                System.out.println("=========================================================================");*/
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(":::::::::::"+count);
            }
        }



        //System.out.println("----------------------------------------------------------");

        /*MemoryUsage nonHeapMemoryUsage = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        System.out.println("jvm.nonheap.init is " + (nonHeapMemoryUsage.getInit()));
        System.out.println("jvm.nonheap.used is " + (nonHeapMemoryUsage.getUsed()));
        System.out.println("jvm.nonheap.committed is " + (nonHeapMemoryUsage.getCommitted()));
        System.out.println("jvm.nonheap.max is " + (nonHeapMemoryUsage.getMax()));

        System.out.println("----------------------------------------------------------");

        for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            final String kind = pool.getType() == MemoryType.HEAP ? "heap" : "nonheap";
            final MemoryUsage usage = pool.getUsage();
            System.out.println("kind is " + kind + ", pool name is " + pool.getName() + ", jvm." + pool.getName() + ".init is " + usage.getInit());
            System.out.println("kind is " + kind + ", pool name is " + pool.getName() + ", jvm." + pool.getName() + ".used is " + usage.getUsed());
            System.out.println("kind is " + kind + ", pool name is " + pool.getName() + ", jvm." + pool.getName()+ ".committed is " + usage.getCommitted());
            System.out.println("kind is " + kind + ", pool name is " + pool.getName() + ", jvm." + pool.getName() + ".max is " + usage.getMax());
        }*/

    }

}


class Test {
    int[] a = new int[2560];
}

