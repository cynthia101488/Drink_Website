package ncu.im3069.demo.app;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

import org.json.*;

public class Order {

    /** id，訂單編號 */
    private int id;

    private Timestamp dateOforder;

    private String stateOforder;

    private int id_Member;
    
    private String phoneNumber_Member;
    
    private String name_Member;
    
    private String sn_Coupon;
    /** list，訂單列表 */
    private ArrayList<OrderDetail> list = new ArrayList<OrderDetail>();

    /** oph，OrderItemHelper 之物件與 Order 相關之資料庫方法（Sigleton） */
    private OrderDetailHelper oph = OrderDetailHelper.getHelper();
    
    private OrderHelper oh = OrderHelper.getHelper();

    /**
     * 實例化（Instantiates）一個新的（new）Order 物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立訂單資料時，產生一個新的訂單
     *
     * @param phoneNumber_Member
     * @param name_Member
     * @param paymentMethod
     * @param stateOforder
     */
    
    public Order(String phoneNumber_Member,String stateOforder) {
        this.phoneNumber_Member = phoneNumber_Member;
        //this.name_Member = name_Member;
        this.stateOforder = stateOforder;
        this.dateOforder = dateOforder;
    }
    
    public Order(int id, String stateOforder) {
        this.id = id;
        //this.name_Member = name_Member;
        this.stateOforder = stateOforder;
        this.dateOforder = dateOforder;
    }
    
    public Order(int id_Member, String phoneNumber_Member,String stateOforder, String sn_Coupon) {
    	this.id_Member = id_Member;
    	this.phoneNumber_Member = phoneNumber_Member;
        //this.name_Member = name_Member;
        this.stateOforder = stateOforder;
        this.dateOforder = dateOforder;
        this.sn_Coupon = sn_Coupon;
    }    
    
    public Order(String stateOforder, int id_Member, String phoneNumber_Member, String name_Member,Timestamp dateOforder, String sn_Coupon) {
        this.stateOforder = stateOforder;
        this.id_Member = id_Member;
        this.phoneNumber_Member = phoneNumber_Member;
        this.name_Member = name_Member;
        this.sn_Coupon = sn_Coupon;
        this.dateOforder = dateOforder;
    }
    /**
     * 實例化（Instantiates）一個新的（new）Order 物件<br>
     * 採用多載（overload）方法進行，此建構子用於修改訂單資料時，新改資料庫已存在的訂單
     *
     * @param phoneNumber_Member
     * @param name_Member
     * @param paymentMethod
     * @param dateOforder 
     * @param stateOforder
     */
    public Order(int id, int id_Member, String phoneNumber_Member,Timestamp dateOforder,String stateOforder, String sn_Coupon) {
        this.id = id;
        this.id_Member = id_Member;
        this.phoneNumber_Member = phoneNumber_Member;
        this.sn_Coupon = sn_Coupon;
        this.dateOforder = dateOforder;
        this.stateOforder = stateOforder;
    }
    public Order(int id, String stateOforder, int id_Member, String phoneNumber_Member, String name_Member, String sn_Coupon) {
        this.id = id;
        this.stateOforder = stateOforder;
        this.id_Member = id_Member;
        this.phoneNumber_Member = phoneNumber_Member;
        this.name_Member = name_Member;
        this.sn_Coupon = sn_Coupon;
        //getOrderProductFromDB();
    }
    public Order(int id, String phoneNumber_Member, Timestamp dateOforder, String stateOforder) {
        this.id = id;
        this.phoneNumber_Member = phoneNumber_Member;
        //this.name_Member = name_Member;
        this.stateOforder = stateOforder;
        this.dateOforder = dateOforder;
        getOrderProductFromDB();
    }

    //新增一個訂單產品及其數量
    public void addOrderProduct(Product pd, int quantity) {
        this.list.add(new OrderDetail(pd, quantity));
    }

    //新增一個訂單產品
    public void addOrderProduct(OrderDetail op) {
        this.list.add(op);
    }
    public void setId(int id) {
        this.id = id;
    }
    /**
     * 
     * 取得訂單編號
     *
     * @return int 回傳訂單編號
     */
    public int getId() {
        return this.id;
    }

    /**
     * 取得訂單會員的姓名
     *
     * @return String 回傳訂單會員的姓名
     */
    public String getName() {
        return this.name_Member;
    }
    
    /**
     * 取得訂單電話
     *
     * @return String 回傳訂單電話
     */
    public String getPhone() {
        return this.phoneNumber_Member;
    }
    public int getId_Member() {
        return this.id_Member;
    }
    public String getPhoneNumber_Member() {
        return this.phoneNumber_Member;
    }
    
    
    public String getName_Member() {
    	return this.name_Member;
    }
    
    public String getSn_Coupon() {
    	return this.sn_Coupon;
    }
    /**
     * 取得訂單狀態
     *
     * @return String 回傳訂單追蹤
     */
    public String getStateOforder() {
        return this.stateOforder;
    }

    /**
     * 取得訂單創建時間
     *
     * @return String 回傳訂單創建時間
     */
    public Timestamp getCreateTime() {
        return this.dateOforder;
    }
    
    public Timestamp getTime() {
    	return Timestamp.valueOf(LocalDateTime.now());
    }

    /**
     * 取得該名會員所有資料
     *
     * @return the data 取得該名會員之所有資料並封裝於JSONObject物件內
     */
    public ArrayList<OrderDetail> getOrderProduct() {
        return this.list;
    }

    /**
     * 從 DB 中取得訂單產品
     */
    private void getOrderProductFromDB() {
        ArrayList<OrderDetail> data = oph.getOrderProductByOrderId(this.id);
        this.list = data;
    }

    /**
     * 取得訂單基本資料
     *
     * @return JSONObject 取得訂單基本資料
     */
    public JSONObject getOrderData() {
        JSONObject jso = new JSONObject();
        jso.put("id", getId());
        jso.put("id_Member", getId_Member());
        jso.put("phoneNumber_Member", getPhoneNumber_Member());  
        jso.put("sn_Coupon", getSn_Coupon());
        jso.put("stateOforder", getStateOforder());
        jso.put("dateOforder", getCreateTime());

        return jso;
    }

    /**
     * 取得訂單產品資料
     *
     * @return JSONArray 取得訂單產品資料
     */
    public JSONArray getOrderProductData() {
        JSONArray result = new JSONArray();

        for(int i=0 ; i < this.list.size() ; i++) {
            result.put(this.list.get(i).getData());
        }

        return result;
    }

    /**
     * 取得訂單所有資訊
     *
     * @return JSONObject 取得訂單所有資訊
     */
    public JSONObject getOrderAllInfo() {
        JSONObject jso = new JSONObject();
        jso.put("order_info", getOrderData());
        jso.put("product_info", getOrderProductData());

        return jso;
    }

    /**
     * 設定訂單產品編號
     */
    public void setOrderProductId(JSONArray data) {
        for(int i=0 ; i < this.list.size() ; i++) {
            this.list.get(i).setId((int) data.getLong(i));
        }
    }
    
    public JSONObject update() {
        /** 新建一個JSONObject用以儲存更新後之資料 */
        JSONObject data = new JSONObject();
        /** 取得更新資料時間（即現在之時間）之分鐘數 */
       
        /** 檢查該名會員是否已經在資料庫 */
        if(this.id != 0) {
           
            data = oh.update(this);
        }
        
        return data;
    }
}