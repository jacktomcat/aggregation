package com.gochinatv.cdn.api.basic;


import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * http://www.cnblogs.com/XL-Liang/articles/2852998.html
 * @author jacktomcat
 *
 */
public class FileLockTest {

    @Test
    public void testFileLock() throws IOException {
        File file = new File("/Users/zhuhuihui/study.md");

        FileLock lock = null;
        try(FileOutputStream fos = new FileOutputStream(file)){
            FileChannel channel = fos.getChannel();
            lock = channel.lock();

            //testConcurrentVisitFile();

            Thread.sleep(5000L);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(lock.isValid());
            lock.release();
        }

    }





}
