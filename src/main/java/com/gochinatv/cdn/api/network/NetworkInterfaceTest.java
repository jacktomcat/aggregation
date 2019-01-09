package com.gochinatv.cdn.api.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


public class NetworkInterfaceTest {

	public static void main(String[] args) {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while(networkInterfaces.hasMoreElements()){
				NetworkInterface next = networkInterfaces.nextElement();
				System.out.println("本机网卡信息为："+ next.getName());
			}
			NetworkInterface address = network.getByInetAddress(ip);
			
			String hostName = ip.getHostName();
			String hostAddress = ip.getHostAddress();
			System.out.println(hostName);
			System.out.println(hostAddress);
			
			byte[] mac = network.getHardwareAddress();
            System.out.print("Current MAC address : ");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
            }
            System.out.println(sb.toString());
			
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
