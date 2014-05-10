package tr.edu.boun.swe574.fsn.web.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class Encrypter {
	
	private static Logger logger = Logger.getLogger(Encrypter.class);
	
	public static String generateMD5(String str) {
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
	        m.update(str.getBytes(),0,str.length());
	        return new BigInteger(1,m.digest()).toString(16);
	        
		} catch (NoSuchAlgorithmException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return null;
	}
	
}

