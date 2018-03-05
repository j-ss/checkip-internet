package org.raptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class InternetCheckThread implements Runnable{

	private long interval;

	public InternetCheckThread(long interval)
	{
		this.interval=interval;
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
				
			}
			catch(IOException e)
			{
				System.out.println("No network connectivity");

			}
			finally {

				try
				{
					//This method check for network interface present in system
						ipAddress();
				}
				catch (SocketException e)
				{

				}
			}
	long currentTime=System.currentTimeMillis();
			long time=currentTime;
		while(time<=currentTime+interval*60000)

			time=System.currentTimeMillis();
		}
		
	}
	
	public void ipAddress() throws SocketException
	{
		//Taking list of network interface
		Enumeration<NetworkInterface> networkInterfaceList = NetworkInterface.getNetworkInterfaces();

		while (networkInterfaceList.hasMoreElements()) {
			NetworkInterface networkInterface = networkInterfaceList.nextElement();
			String name = networkInterface.getDisplayName();

			if (Pattern.matches("eth[0-9]", name) || Pattern.matches("wlp1s[0-9]", name)) {
				System.out.println(name);
				//This method print the ip address of system
				printInetAddress(networkInterface);
				break;
			}
			else {
				System.out.println("localhost");
				printInetAddress(networkInterface);

			}
		}
	}
	public void printInetAddress(NetworkInterface networkInterface)
	{
			Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
			while (inetAddresses.hasMoreElements())
			{

				InetAddress address = inetAddresses.nextElement();
				if(address instanceof Inet4Address)
					System.out.println("ipv4 address is "+address.getHostAddress());

			}
	}

}
