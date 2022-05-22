package servlet;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    String data;
    String message;
    String status;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

             resp.setContentType("application/json");
             resp.addHeader("Access-Control-Allow-Origin","*");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaEE","root","");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM `customer`");
            ResultSet rst = pst.executeQuery();
            JsonArrayBuilder customerArray = Json.createArrayBuilder();
            while (rst.next()){
                JsonObjectBuilder customer = Json.createObjectBuilder();
                customer.add("id",rst.getString(1));
                customer.add("name",rst.getString(2));
                customer.add("address",rst.getString(3));
                customer.add("salary",rst.getString(4));
                customerArray.add(customer.build());
            }
            JsonObjectBuilder respJson = Json.createObjectBuilder();
            PrintWriter writer = resp.getWriter();
            message = "Done";
            status = "200";
            respJson.add("data",customerArray.build());
            respJson.add("message",message);
            respJson.add("status",status);
            writer.print(respJson.build());



      

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("customerId");
        String customerName = req.getParameter("customerName");
        String customerAddress = req.getParameter("customerAddress");
        String customerSalary = req.getParameter("customerSalary");

        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaEE","root","");
            PreparedStatement pst = con.prepareStatement("INSERT  INTO `customer` VALUES(?,?,?,?)");
            pst.setObject(1,customerId);
            pst.setObject(2,customerName);
            pst.setObject(3,customerAddress);
            pst.setObject(4,customerSalary);


            JsonObjectBuilder respJson = Json.createObjectBuilder();


            PrintWriter writer = resp.getWriter();

            if(pst.executeUpdate()>0){
                  data = "";
                  message = "Added Success !";
                  status = "200";

            }else{
                data = "";
                message = "Try Again !";
                status = "";

            }
            respJson.add("data",data);
            respJson.add("message",message);
            respJson.add("status",status);

            writer.print(respJson.build());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Access-Control-Allow-Methods","DELETE");
        try {
            String customerId = req.getParameter("customerId");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaEE","root","");
            PreparedStatement pst = con.prepareStatement("DELETE FROM `customer` WHERE id=?");
            pst.setObject(1,customerId);

            JsonObjectBuilder respJson = Json.createObjectBuilder();


            PrintWriter writer = resp.getWriter();

            if(pst.executeUpdate()>0){
                data = "";
                message = "Delete Success !";
                status = "200";
            }else{
                data = "";
                message = "Try Again !";
                status = "";
            }
            respJson.add("data",data);
            respJson.add("message",message);
            respJson.add("status",status);

            writer.print(respJson.build());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin","*");

        JsonReader reader = Json.createReader(req.getReader());

        JsonObject customer = reader.readObject();
        String customerName = customer.getString("customerName");
        String customerAddress = customer.getString("customerAddress");
        String customerSalary =  customer.getString("customerSalary");
        String customerId = customer.getString("customerId");





        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaEE","root","");
            PreparedStatement pst = con.prepareStatement("UPDATE `customer` SET  `name`=? ,address=? ,salary=? WHERE id=?");

            pst.setObject(1,customerName);
            pst.setObject(2,customerAddress);
            pst.setObject(3,customerSalary);
            pst.setObject(4,customerId);



            JsonObjectBuilder respJson = Json.createObjectBuilder();


            PrintWriter writer = resp.getWriter();


            resp.setStatus(200);
            if(pst.executeUpdate()>0){
                data = "";
                message = "Update Success !";
                status = "200";
            }else{
                data = "";
                message = "Try Again !";
                status = "400";
            }

            respJson.add("data",data);
            respJson.add("message",message);
            respJson.add("status",status);

            writer.print(respJson.build());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.addHeader("Access-Control-Allow-Methods","DELETE,PUT");
        resp.addHeader("Access-Control-Allow-Headers","Content-Type");
    }
}
