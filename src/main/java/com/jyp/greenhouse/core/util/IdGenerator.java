package com.jyp.greenhouse.core.util;

import java.util.UUID;

public final class IdGenerator {

  private IdGenerator(){

  }

  private static String digits(long val, int digits){
    long hi = 1L << (digits * 4);
    return NumberUtils.toString(hi | (val & (hi - 1)), NumberUtils.MAX_RADIX).substring(1);
  }

  /**
   * 以62进制（字母加数字）生成19位UUID，最短的UUID
   * 
   * @return
   */
  public static String uuid19(){
    UUID uuid = UUID.randomUUID();
    StringBuilder sb = new StringBuilder();
    sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));
    sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));
    sb.append(digits(uuid.getMostSignificantBits(), 4));
    sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));
    sb.append(digits(uuid.getLeastSignificantBits(), 12));
    return sb.toString();
  }

  public static String uuid32(){
    UUID uuid = UUID.randomUUID();
    return uuid.toString().replaceAll("-", "");
  }

  public static void main(String[] args){
    // creating UUID
    UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
    UUID uuid = UUID.randomUUID();
    // checking time stamp value
    System.out.println("Time stamp value: "+uid.timestamp());
    System.out.println(uid);
    System.out.println(uuid);
    System.out.println(uuid.version());
    System.out.println(IdGenerator.uuid19());
  }


}
