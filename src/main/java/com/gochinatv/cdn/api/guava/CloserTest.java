package com.gochinatv.cdn.api.guava;

import com.google.common.io.Closer;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jacktomcat on 17/10/15.
 */
public class CloserTest {


    /**
     *  JDK7有了新的措施来解决这类问题，那就是try-with-resources 语句
     *
     *
     * 那JDK7以前的版本怎么办呢，guava给我们提供了方法。我们可以把Closeable 的对象注册到Closer对象上，资源使用完毕后，
     * 调用closer的close方法，就可以把所有注册了的资源安全的close掉。这个方法虽然没有try-with-resources好用，
     * 但是比起传统的jdk做法，要好很多了。
     *
     */
    @Test
    public void closerTest() {
        Closer closer = Closer.create();

        try {
            FileInputStream inputStreamRegister = closer.register(new FileInputStream(""));

            FileOutputStream outputStreamRegister = closer.register(new FileOutputStream(""));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                closer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
