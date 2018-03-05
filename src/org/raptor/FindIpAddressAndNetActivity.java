package org.raptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindIpAddressAndNetActivity {
	
	public static void main(String[] args){		
		
		int interval=0;
		System.out.println("enter time interval(minute) to check connectivity");
		try (BufferedReader reader=new BufferedReader(new InputStreamReader(System.in)))
		{
			 interval=Integer.parseInt(reader.readLine());
			 
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		Thread thread=new Thread(new InternetCheckThread(interval));
			thread.start();


	}

}
