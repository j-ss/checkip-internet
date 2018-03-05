package org.raptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FindIpAddressAndNetActivity {
	private final static Logger logger=Logger.getLogger(FindIpAddressAndNetActivity.class.getName());
	public static void main(String[] args){		
		
		int interval=0;
		System.out.println("enter time interval(minute) to check connectivity");
		try (BufferedReader reader=new BufferedReader(new InputStreamReader(System.in)))
		{
			 interval=Integer.parseInt(reader.readLine());
			 
		}
		catch(IOException e)
		{
			logger.log(Level.INFO," exception ",e);

		}
		
		Thread thread=new Thread(new InternetCheckThread(interval));
			thread.start();


	}

}
