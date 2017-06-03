package com.gochinatv.cdn.api.elasticjob;


public class DataProcessFactory {
   
	 private static DataProcess dataProcess = new DataProcess();
     
     public static DataProcess getDataProcess() {
         return dataProcess;
     }
     
}
