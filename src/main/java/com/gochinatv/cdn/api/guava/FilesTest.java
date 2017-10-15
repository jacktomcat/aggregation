package com.gochinatv.cdn.api.guava;

import com.google.common.io.Files;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by jacktomcat on 17/10/15.
 */
public class FilesTest {


    @Test
    public void filesTest() {
        String fullPath = "/Users/zhuhuihui/WorkBook/Markdown/zookeeper/zookeeper.md";
        String extension = Files.getFileExtension(fullPath);
        System.out.println(extension);

        String fileName = Files.getNameWithoutExtension(fullPath);
        System.out.println(fileName);

        try {
            List<String> rows = Files.readLines(new File(fullPath), Charset.forName("UTF-8"));

            rows.forEach(line->{
                System.out.println(line);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        String newFile = "/Users/zhuhuihui/WorkBook/Markdown/zookeeper/zookeeper-temp.md";
        try(BufferedWriter bufferedWriter = Files.newWriter(new File(newFile), Charset.forName("UTF-8"))){

            bufferedWriter.write("#<center>Zookeeper</center>");
            bufferedWriter.newLine();
            bufferedWriter.write("#<center>Zookeeper</center>");
            bufferedWriter.newLine();
            bufferedWriter.write("#<center>Zookeeper</center>");
        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
