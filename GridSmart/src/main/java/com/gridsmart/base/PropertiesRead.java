package com.gridsmart.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesRead {
	
	   Properties prop;
	    
	    public PropertiesRead(String Path)
	    {
	        File file = new File(Path);
	        
	        FileInputStream fileInput = null;
	        try {
	            fileInput = new FileInputStream(file);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	         prop = new Properties();
	        
	        //load properties file
	        try {
	            prop.load(fileInput);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	public String getProperties(String key)
	{
	   return prop.getProperty(key);
	    
	}

}
