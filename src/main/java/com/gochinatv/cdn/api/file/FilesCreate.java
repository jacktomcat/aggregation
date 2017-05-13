package com.gochinatv.cdn.api.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesCreate {
   
	public static void main(String[] args) {
		Path path = Paths.get("/Users/zhuhuihui/baseline2/export");
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
