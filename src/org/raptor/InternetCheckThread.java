package org.raptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class InternetCheckThread implements Runnable{

	private int interval;
	File file=null;
	public InternetCheckThread(int interval)
	{
		this.interval=interval;
		file=new File("output");
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("file not created");
		}
	}
	
	
	@Override
	public void run() {
		while(true)
		{
			
			//check Internet connectivity
			
			/* here we try to connect with remote host if
			 * connection established then there will be 
			 * Internet connection present.
			*/
			try 
			{
				URL url=new URL("http://www.google.com");
				URLConnection connection=url.openConnection();
				connection.connect();
			
				System.out.println("Internet available");
				
				ipAddress();
				
			}
			catch(IOException e)
			{
				System.out.println("No network connectivity");
				
				InetAddress address=null;
				try {
					address = InetAddress.getLocalHost();
					String hostAddress=address.getHostAddress();
					
					
						System.out.println("localhost "+hostAddress);
					
					
				}
				catch (UnknownHostException eq) {
					System.out.println("problem to connect with system");
				}
				
			}
			
			try {
				Thread.sleep(interval*100*60);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
				
		}
		
	}
	
	public void ipAddress()
	{
		Process proc=null;
		try {
			proc = Runtime.getRuntime().exec("ifconfig");
			// Read the output

	        BufferedReader reader =  
	              new BufferedReader(new InputStreamReader(proc.getInputStream()));
	        
	        PrintWriter out=new PrintWriter(file);
	        String line = "";
	        while((line = reader.readLine()) != null) {
	            out.write(line);
	            out.flush();
	        }   
	            
	            proc.waitFor();
	        	out.close();
	                
	        	reader=new BufferedReader(new FileReader(file));
	        	String input=reader.readLine();
	        	int index=input.indexOf("wlp1s0");
	        	int index1=input.indexOf("inet addr",index);
	        	
	        	for(int i=index1;i<index1+21;i++)
	                System.out.print(input.charAt(i));
	        	
	        	reader.close();	
	        

		} 
		catch(FileNotFoundException |InterruptedException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			
			System.out.println(e.getMessage());
		}
		System.out.println();
	}

}
