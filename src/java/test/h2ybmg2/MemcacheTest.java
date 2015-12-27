package h2ybmg2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import com.h2y.bmg.util.SysBaseUtil;
import com.h2y.memcached.MemcachedUtil;
import com.h2y.util.DateUtil;

public class MemcacheTest {

	
	public static void main(String[] args) {
		
		
		
		MemcachedUtil memcachedUtil = MemcachedUtil.getInstance();
		String key = "mysql1";
		memcachedUtil.add(key, "1111",1);
		
		System.out.println(memcachedUtil.get(key));
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(memcachedUtil.get(key));
		
//		String test = DateUtil.toStr(new Date(1000), DateUtil.YYYY_MM_DD_HH_MM_SS);
//		System.out.println(test);
	}
	
	public static String getExceptionAllinformation(Exception ex){
        String sOut = "";
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
	}
	
	
	public static String getExceptionAllinformation_01(Exception ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
             out.close();
        } catch (Exception e) {
        }
        return ret;
	}
	
	
	private static String toString_02(Throwable e){   
        StringWriter sw = new StringWriter();   
        PrintWriter pw = new PrintWriter(sw, true);   
        e.printStackTrace(pw);   
        pw.flush();   
        sw.flush();   
        return sw.toString();   
	} 
	
	
	
	/** 
     * 获取exception详情信息 
     *  
     * @param e 
     *            Excetipn type 
     * @return String type 
     */  
    public static String getExceptionDetail(Exception e) {  
  
        StringBuffer msg = new StringBuffer("null");  
  
        if (e != null) {  
            msg = new StringBuffer("");  
  
            String message = e.toString();  
  
            int length = e.getStackTrace().length;  
  
            if (length > 0) {  
  
                msg.append(message + "\n");  
  
                for (int i = 0; i < length; i++) {  
  
                    msg.append("\t" + e.getStackTrace()[i] + "\n");  
  
                }  
            } else {  
  
                msg.append(message);  
            }  
  
        }  
        return msg.toString();  
  
    }  
}
