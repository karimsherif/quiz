/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksh.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import com.ksh.core.util.Util;

/*
 * @author Karim Sherif
 */
public class FileDirectory implements Serializable {

    private static final long serialVersionUID = 4957821269949828615L;

    public FileDirectory() {
    }

    public static boolean makeDirectory(String path, String directoryName) {
        try {
            File directory = new File(path + "/" + directoryName);

            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean deleteFileDir(String path) {
    		File file = new File(path);
    		 
    		if (file.isDirectory()) {
    	        String[] subDirs = file.list();
    	        for (int i=0; i<subDirs.length; i++) {
    	            boolean success = deleteFileDir(new File(file, subDirs[i]));
    	            if (!success) {
    	                return false;
    	            }
    	        }
    	    }

    	    // The directory is now empty so delete it
    	    return file.delete();
    }
    public static boolean deleteFileDir(File fileDir) {
	    if (fileDir.isDirectory()) {
	        String[] subDirs = fileDir.list();
	        for (int i=0; i<subDirs.length; i++) {
	            boolean success = deleteFileDir(new File(fileDir, subDirs[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    // The directory is now empty so delete it
	    return fileDir.delete();
	}
    
    public static boolean upoladFile(String path,String fileName, InputStream inputStream) throws IOException {

        int BUFFER_SIZE = Constant.BUFFER_SIZE;
        File result = new File(path  + Util.fileSeparator+ fileName);
        FileOutputStream fileOutputStream;
        fileOutputStream = new FileOutputStream(result);
        byte[] buffer = new byte[BUFFER_SIZE];
        int bulk;
        while (true) {
            bulk = inputStream.read(buffer);
            if (bulk < 0) {
                break;
            }
            fileOutputStream.write(buffer, 0, bulk);
            fileOutputStream.flush();
        }
        fileOutputStream.close();
        inputStream.close();

        return true;
    }
}
