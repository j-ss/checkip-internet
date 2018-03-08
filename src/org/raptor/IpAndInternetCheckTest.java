package org.raptor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;

public class IpAndInternetCheckTest {

  private IpAndInternetCheck internetCheck;
  @Before
  public void setUp() throws Exception {

     internetCheck=new IpAndInternetCheck();
     internetCheck.run();

  }

  @Test
  public void checkConnectivity() {

    boolean connectivity=internetCheck.isConnectivity();
    assertEquals(true,connectivity);
  }

  @Test
  public void ipAddress() {

    String ipAddress=internetCheck.getIpAddress();
    assertEquals("192.168.1.115",ipAddress);           // here expected ip we found through command line
  }
}