package com.oncecloud.helper;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 自定义消息类，用于国际化
 */

public final class MessageSourceHelper { 	    
	
    public String getMessage(String code){    	 
    	 ResourceBundle rb = ResourceBundle.getBundle("com/oncecloud/config/message", Locale.CHINA);
    	 return rb.getString(code);
    }   
}
