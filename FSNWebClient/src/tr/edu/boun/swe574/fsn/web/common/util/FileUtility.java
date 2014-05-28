package tr.edu.boun.swe574.fsn.web.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FileUtility {
	
	public static List<String> imgExtensions = Arrays.asList("jpg","gif","jpeg","png","bmp");
	
    public static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
        	return fileName.substring(fileName.lastIndexOf(".")+1);
        } else return "";
    }
    
    public static boolean isImageFile(String fileName) {
    	String fileExtension = getFileExtension(fileName);
    	return imgExtensions.contains(fileExtension.toLowerCase(Locale.US));
    }
    
}
