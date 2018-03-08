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
    boolean expected=true;                                       //here expected value is true and false check on both alternatively
    assertEquals(expected,connectivity);
  }

  @Test
  public void ipAddress() {

    String ipAddress=internetCheck.getIpAddress();
    String expected="192.168.1.115";                            // here expected ip we found through command line
    assertEquals(expected,ipAddress);
  }
}