package ncu.im3069.demo.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import ncu.im3069.demo.util.Arith;

public class Coupon {


 private int id;
 
 private String sn;
 
 private int price;
 
 private int id_OrderGet;
 
 private int id_Member;
 
 private int id_OrderUsed;
 
  private ArrayList<Coupon> list = new ArrayList<Coupon>();
 
 private CouponHelper ch =  CouponHelper.getHelper();
 
 public Coupon(String sn) {
	 this.sn =sn;
 }
 
 public Coupon(int id, String sn, int price, int id_OrderGet, int id_Member) {
  this.id = id;
  this.sn =sn;
  this.price = price;
  this.id_OrderGet = id_OrderGet;
  this.id_Member = id_Member;
  
 }
 public Coupon(String sn, int price, int id_OrderGet, int id_Member, int id_OrderUsed) {
  
  this.sn =sn;
  this.price = price;
  this.id_OrderGet = id_OrderGet;
  this.id_Member = id_Member;
  this.id_OrderUsed = id_OrderUsed;
  
 }
 public Coupon (String sn, int price, int id_OrderGet, int id_Member) {
 
  this.sn =sn;
  this.price = price;
  this.id_OrderGet = id_OrderGet;
  this.id_Member = id_Member;
  
 }
 
 public Coupon(int id, int id_OrderUsed) {
  this.id =id;
  this.id_OrderUsed = id_OrderUsed;
 }
 
 public Coupon(int id, String sn, int price, int id_OrderGet, int id_Member, int id_OrderUsed) {
  this.id = id;
  this.sn =sn;
  this.price = price;
  this.id_OrderGet = id_OrderGet;
  this.id_Member = id_Member;
  this.id_OrderUsed = id_OrderUsed;
 }
 
 public Coupon(int price, int id_OrderGet, int id_Member) {
  
  this.price = price;
  this.id_OrderGet = id_OrderGet;
  this.id_Member = id_Member;
  
 }
 
 
 public String getSN() {
  
  String content="ABCDEFGHIJKLMNOPQRSTUVWHYZ";//建立23個大寫字母的字串
  content+=content.toLowerCase();//把大寫字母轉換成小寫字母，相連線
  content+="0123456789";//連線0~9的數字。
  Random r=new Random();//建立一個隨機數物件
  StringBuilder b=new StringBuilder(5);//建立空間大小為5的StringBuilder物件
  for (int i = 0; i <5; i++) {
  char n=content.charAt(r.nextInt(content.length()));//擷取一個從0到content.length()之間的字元，迴圈輸出5個不同的字元，追加到一起
  b.append(n);
  }
  return b.toString();
 }
 
 
 public String getsn() {
	  return this.sn;
	 }
 
 public int getId() {
  return this.id;
 }
 
 
 
 public int getPrice() {
  return this.price;
 }
 
 public int getId_OrderGet() {
  return this.id_OrderGet;
 }
 
 public int getId_Member() {
  return this.id_Member;
 }
 
 public int getId_OrderUsed() {
  return this.id_OrderUsed;
 }
 
 /**
     * 取得該名會員所有資料
     *
     * @return the data 取得該名會員之所有資料並封裝於JSONObject物件內
     */
    public ArrayList<Coupon> getMemberCoupon() {
        return this.list;
    }

    
 
  public JSONObject getCouponData() {
         JSONObject data = new JSONObject();
         data.put("id", getId());
         data.put("sn", getsn());
         data.put("price", getPrice());
         data.put("id_OrderGet", getId_OrderGet());
         data.put("id_Member", getId_Member());
         data.put("id_OrderUsed", getId_OrderUsed());
       
         return data;
     }
  

  public JSONObject update() {
         /** 新建一個JSONObject用以儲存更新後之資料 */
         JSONObject data = new JSONObject();
         
         /** 檢查該筆優惠券是否已經在資料庫 */
         if(this.id != 0) {
           
             /** 透過MemberHelper物件，更新目前之優惠券資料置資料庫中 */
             data = ch.update(this);
         }
         
         return data;
     }
  
  
 

}