package org.raptor;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class IpAndInternetCheck extends TimerTask{

  private  boolean connectivity;
  private  String ipAddress;
  private  String interfaceName;
  private  Logger logger;

  public IpAndInternetCheck(){

    logger=Logger.getLogger(IpAndInternetCheck.class.getName()); //getting the logger instance

  }

  /**
   * Getter method
   */

  public boolean isConnectivity() {
    return connectivity;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  /**
   * Main method
   */
  public static void main(String[] args){

    int interval=5;      // iterate after every 5 seconds
    Timer timer=new Timer();
    timer.schedule(new IpAndInternetCheck(),0,interval*1000);

  }
  @Override public void run() {

    checkConnectivity();    //check connectivity
    try {
      ipAddress();         //check ipaddress
    } catch (SocketException e) {
      logger.log(Level.WARNING,e.getMessage());
    }

    logger.log(Level.INFO,interfaceName);
    logger.log(Level.INFO,"Ipv4 address is "+ipAddress);

  }

  /**
   * This method check for internet connectivity in system
   * @return
   */
  public void checkConnectivity(){

    try{

      URL url=new URL("http://www.google.com");
      URLConnection connection=url.openConnection();
      connection.connect();
      connectivity=true;
      logger.log(Level.INFO,"Internet Available");

    }catch(IOException e){
      connectivity=false;
      logger.log(Level.WARNING,e.getMessage());

    }

  }

  /**
   * This method find the ipaddress of system
   */
  public void ipAddress() throws SocketException{

    /*
      Getting list of network interface
     */
    Enumeration<NetworkInterface>networkInterfaceList=NetworkInterface.getNetworkInterfaces();
    while (networkInterfaceList.hasMoreElements()){             //iterate over network list

      NetworkInterface networkInterface=networkInterfaceList.nextElement();
      interfaceName=networkInterface.getDisplayName();          //getting interface name

      if(Pattern.matches("enp3s[0-9]",interfaceName)||Pattern.matches("wlp1s[0-9]",interfaceName)){

        Enumeration<InetAddress> inetAddress=networkInterface.getInetAddresses();
        while (inetAddress.hasMoreElements()){                  //iterate over inet address

          InetAddress address=inetAddress.nextElement();
          if(address instanceof Inet4Address){

            ipAddress=address.getHostAddress();

          }

        }

      }else{

        interfaceName="localhost";
        ipAddress="127.0.0.1";

      }

      break;

    }

  }

}
