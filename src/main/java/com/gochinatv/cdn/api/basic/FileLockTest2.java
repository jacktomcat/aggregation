package com.gochinatv.cdn.api.basic;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhuhuihui on 17/10/12.
 */
public class FileLockTest2 {


    @Test
    public void testConcurrentVisitFile(){
        new Thread(()->{
            File file1 = new File("/Users/zhuhuihui/study.md");

            try(FileOutputStream fos = new FileOutputStream(file1)){

                fos.write(234);

                fos.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }
}
