package ncu.im3069.demo.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.Product;
import ncu.im3069.demo.app.ProductHelper;
import ncu.im3069.tools.JsonReader;

@WebServlet("/api/product.do")
public class ProductController extends HttpServlet {
 private static final long serialVersionUID = 1L;

 private ProductHelper ph =  ProductHelper.getHelper();

    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
             JsonReader jsr = new JsonReader(request);
             /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
             String id = jsr.getParameter("id");

             JSONObject resp = new JSONObject();
             /** 判斷該字串是否存在，若存在代表要取回購物車內產品之資料，否則代表要取回全部資料庫內產品之資料 */
             if (!id.isEmpty()) {
               JSONObject query = ph.getById(id);
               resp.put("status", "200");
               resp.put("message", "所有購物車之商品資料取得成功");
               resp.put("response", query);
             }
             else {
               JSONObject query = ph.getAll();

               resp.put("status", "200");
               resp.put("message", "所有商品資料取得成功");
               resp.put("response", query);
             }

             jsr.response(resp, response);
      }

 public void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
         /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
         JsonReader jsr = new JsonReader(request);
         JSONObject jso = jsr.getObject();
         
         /** 取出經解析到JSONObject之Request參數 */
         String name = jso.getString("name");
         int price = jso.getInt("price");
         String image = jso.getString("image");
         String description = jso.getString("description");
         
         /** 建立一個新的商品物件 */
         Product p = new Product(name, price, image, description);
         
         /** 後端檢查是否有欄位為空值，若有則回傳錯誤訊息 */
         if(name.isEmpty() || image.isEmpty() || description.isEmpty()) {
             /** 以字串組出JSON格式之資料 */
             String resp = "{\"status\": \'400\', \"message\": \'欄位不能有空值\', \'response\': \'\'}";
             /** 透過JsonReader物件回傳到前端（以字串方式） */
             jsr.response(resp, response);
         }
         /** 透過PemberHelper物件的checkDuplicate()檢查該商品是否有重複 */
         else if (!ph.checkDuplicate(p)) {
             /** 透過MemberHelper物件的create()方法新建一個會員至資料庫 */
             JSONObject data = ph.create(p);
             
             /** 新建一個JSONObject用於將回傳之資料進行封裝 */
             JSONObject resp = new JSONObject();
             resp.put("status", "200");
             resp.put("message", "成功! ");
             resp.put("response", data);
             
             /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
             jsr.response(resp, response);
         }
         else {
             /** 以字串組出JSON格式之資料 */
             String resp = "{\"status\": \'400\', \"message\": \'新增帳號失敗，此E-Mail帳號重複！\', \'response\': \'\'}";
             /** 透過JsonReader物件回傳到前端（以字串方式） */
             jsr.response(resp, response);
         }
     }
 
 public void doPut(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
         /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
         JsonReader jsr = new JsonReader(request);
         JSONObject jso = jsr.getObject();
         
         /** 取出經解析到JSONObject之Request參數 */
         int id = jso.getInt("id");
            String name = jso.getString("name");
            int price = jso.getInt("price");
            String image =jso.getString("image");
            String description =jso.getString("description");

         /** 透過傳入之參數，新建一個以這些參數之會cup物件 */
         Product p = new Product(id, name, price, image,  description);
         
         /** 透過Member物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
         JSONObject data = p.update();
         
         /** 新建一個JSONObject用於將回傳之資料進行封裝 */
         JSONObject resp = new JSONObject();
         resp.put("status", "200");
         resp.put("message", "成功更新商品資訊!");
         resp.put("response", data);
         
         /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
         jsr.response(resp, response);
     }
 
 public void doDelete(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
         /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
         JsonReader jsr = new JsonReader(request);
         JSONObject jso = jsr.getObject();
         
         /** 取出經解析到JSONObject之Request參數 */
         int id = jso.getInt("id");
         System.out.println("OK");
         /** 透過MemberHelper物件的deleteByID()方法至資料庫刪除該名會員，回傳之資料為JSONObject物件 */
         JSONObject query = ph.deleteByID(id);
         
         /** 新建一個JSONObject用於將回傳之資料進行封裝 */
         JSONObject resp = new JSONObject();
         resp.put("status", "200");
         resp.put("message", "商品移除成功！");
         resp.put("response", query);

         /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
         jsr.response(resp, response);
     }


}