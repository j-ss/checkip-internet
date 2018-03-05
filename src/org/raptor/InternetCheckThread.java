package org.raptor;


import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class InternetCheckThread implements Runnable{
	private final Logger logger;
	private long interval;

	public InternetCheckThread(long interval)
	{
		this.interval=interval;
		logger=Logger.getLogger(InternetCheckThread.class.getName());
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
				logger.log(Level.INFO,"Internet available");
				
			}
			catch(IOException e)
			{
				logger.log(Level.INFO,"No netwok connectivity");
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
				logger.log(Level.INFO,"network interface is "+name);
				//This method print the ip address of system
				printInetAddress(networkInterface);
				break;
			}
			else {
				logger.log(Level.INFO,"localhost");
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
					logger.log(Level.INFO,"ipv4 address is "+address.getHostAddress());

			}
	}

}
