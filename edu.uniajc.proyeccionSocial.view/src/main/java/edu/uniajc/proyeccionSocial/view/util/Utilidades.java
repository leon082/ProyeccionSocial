/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.proyeccionSocial.view.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author luis.leon
 */
public class Utilidades {
    
    public static String generateHash(String password) throws RuntimeException, NoSuchAlgorithmException{

		if (password==null && password.length() < 0) {
			System.err.println("String to MD5 digest should be first and only parameter");
			throw new RuntimeException();
		}
		String original = password;
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}

		return sb.toString();
	}
    
}
