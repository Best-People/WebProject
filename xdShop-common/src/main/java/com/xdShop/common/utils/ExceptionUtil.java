package com.xdShop.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by pro on 16/11/13.
 */
public class ExceptionUtil {

    public static String getStackTrace(Throwable t){
        StringWriter sw=new StringWriter();
        PrintWriter pw=new PrintWriter(sw);
        try{
            t.printStackTrace(pw);
            return sw.toString();
        }catch (Exception e){
            pw.close();
            return null;
        }
    }
}
