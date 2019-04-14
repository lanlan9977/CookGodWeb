package android.com.chefOdDetail.controller;
import java.io.*;

public class Send {

  public void sendMessage(String[] tel , String message)  {

  try {
      String server  = "203.66.172.131"; //Socket to Air Gateway IP
      int port	     = 8000;            //Socket to Air Gateway Port

      String user    = "85559671"; //甯宠櫉
      String passwd  = "2irioiai"; //瀵嗙⒓
      String messageBig5 = new String(message.getBytes(),"big5"); //绨¤▕鍏у

      //----寤虹珛閫ｇ窔 and 妾㈡煡甯宠櫉瀵嗙⒓鏄惁閷
      sock2air mysms = new sock2air();
      int ret_code = mysms.create_conn(server,port,user,passwd) ;
      if( ret_code == 0 ) {
           System.out.println("甯宠櫉瀵嗙⒓Login OK!");
      } else {
      	   System.out.println("甯宠櫉瀵嗙⒓Login Fail!");
           System.out.println("ret_code="+ret_code + ",ret_content=" + mysms.get_message());
           //绲愭潫閫ｇ窔
           mysms.close_conn();
           return ;
      }

      //鍌抽�佹枃瀛楃啊瑷�
      //濡傞渶鍚屾檪鍌抽�佸绛嗙啊瑷婏紝璜嬪娆″懠鍙玸end_message()鍗冲彲銆�
    for(int i=0 ; i<tel.length ; i++){  
      ret_code=mysms.send_message(tel[i],messageBig5);
      if( ret_code == 0 ) {
           System.out.println("绨¤▕宸查�佸埌绨¤▕涓績!");
           System.out.println("MessageID="+mysms.get_message()); //鍙栧緱MessageID
      } else {
           System.out.println("绨¤▕鍌抽�佺櫦鐢熼尟瑾�!");
           System.out.print("ret_code="+ret_code+",");
           System.out.println("ret_content="+mysms.get_message());//鍙栧緱閷鐨勮▕鎭�
           //绲愭潫閫ｇ窔
           mysms.close_conn();
           return ;
      }
    }

      //绲愭潫閫ｇ窔
      mysms.close_conn();

  }catch (Exception e)  {

      System.out.println("I/O Exception : " + e);
   }
 }

 public static void main(String[] args) {
 	Send se = new Send();
 	String[] tel ={"0921514217"};
 	String message = "鎺掔▼瑷婃伅娓│";
 	se.sendMessage(tel , message);
 }	

}//end of class
