package com.vzwcoders.util;

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
}
