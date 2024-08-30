package org.spring.demo.goitteamproject;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class Base62Encoder {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();
    private static final int SHORT_URL_LENGTH = 7;

    public String encode(long id) {
        StringBuilder shortURL = new StringBuilder();
        while (id > 0) {
            shortURL.append(ALPHABET.charAt((int) (id % BASE)));
            id /= BASE;
        }
        while (shortURL.length() < SHORT_URL_LENGTH) {
            shortURL.append(ALPHABET.charAt(new Random().nextInt(BASE)));
        }
        return shortURL.reverse().toString();
    }


//   MD5 hash

//    public static String encode(long id)  {
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("SHA-256");
//        } catch (NoSuchAlgorithmException e) {
//            logger.severe(e.getMessage());
//        }
//
//        // Hash the numeric ID
//        byte[] hash = md.digest(Long.toString(id).getBytes());
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 8; i++) {
//            sb.append(String.format("%02x", hash[i]));
//        }
//        return sb.toString();
//    }
}
