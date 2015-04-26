package com.vzwcoders.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;
import java.util.Set;

public class FileUtil {

	public static Set loadProps(String fileName){
		try {
			Properties p=new Properties();
			p.load(new FileReader(fileName));
			return p.keySet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println("properties "+FileUtil.loadProps("c:\\prop.txt"));
	}
public static void fileWrite(String fileName,int noOfLines){
	DataOutputStream fos = null;
	try {
	    fos =new DataOutputStream(new FileOutputStream(fileName+"_lock"));
	   fos.writeInt(noOfLines);
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
	   try {fos.close();} catch (Exception ex) {
		   ex.printStackTrace();
	   }
	}
}
public static int fileRead(String fileName){
	DataInputStream fis = null;
	int i=0;
	try {
		fis =new DataInputStream(new FileInputStream(fileName+"_lock"));
		  if(fis!=null && fis.available()>0)
			  i= fis.readInt();
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
	   try {fis.close();} catch (Exception ex) {
		   ex.printStackTrace();
	   }
	}
	return i;
}
}
