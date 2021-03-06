/**
 * Copyright 2001-2013 CryptoHeaven Corp. All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of CryptoHeaven Corp. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with CryptoHeaven Corp.
 */
package com.tools;

import java.io.File;
import java.security.MessageDigest;

/**
 * Copyright 2001-2013 CryptoHeaven Corp. All Rights Reserved.
 *
 * @author  Marcin Kurzawa
 */
public class DigestFile extends Object {

  public static void main(String[] args) {
    try {
      if (args.length < 2) {
        usageExit(-1);
      }
      String algorithm = args[0].toUpperCase();
      MessageDigest digest = com.CH_co.util.Digester.getDigest(algorithm);

      for (int fileNum=1; fileNum<args.length; fileNum++) {
        String filename = args[fileNum];

        File file = new File(filename);
        byte[] hexDigest = com.CH_co.util.Digester.digestFile(file, digest);
        System.out.print(algorithm + " : ");
        for (int i=0; i<hexDigest.length; i++) {
          int bi = hexDigest[i];
          int hexDigit1 = ((bi & 0x00F0) >> 4);
          int hexDigit2 =  (bi & 0x000F);
          System.out.print(Character.forDigit(hexDigit1, 16));
          System.out.print(Character.forDigit(hexDigit2, 16));
        }
        System.out.println("   " + file.getName());
      }
    } catch (Throwable t) {
      System.out.println("Exception: " + t.getMessage());
      System.out.println();
      usageExit(-2);
    }

  }

  private static void usageExit(int code) {
    System.out.println("Usage: <algorithm> <file-list>");
    System.out.println("Eaxmple: sha256 filename1 filename2 filename3");
    System.exit(code);
  }

}