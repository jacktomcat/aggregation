package com.gochinatv.cdn.api.jdk.jdk7;

import java.io.Closeable;
import java.io.IOException;

public class CloseableTest {

    public static void main(String[] args) {
        try (Resource resource = new Resource()){
            resource.readResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



class Resource implements Closeable{

    @Override
    public void close() throws IOException {
        System.out.println("invoke close method");
    }

    public void readResource() {
        System.out.println("read resource");
    }
}