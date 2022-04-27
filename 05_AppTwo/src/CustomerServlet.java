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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            resp.setContentType("application/json");//(methanik resp type ek menstion kroth front end eke kiynn onee nh);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaEE","root","");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM `customer`");
            ResultSet rst = pst.executeQuery();
            String allCustomer="";
            while (rst.next()){

                String customer = "{\"id\":\""+rst.getString(1)+"\",\"name\":\""+rst.getString(2)+"\",\"address\":\""+rst.getString(3)+"\",\"salary\":"+rst.getString(4)+"},";
                allCustomer+=customer;
            }
            //meken out put eke enne awsnet , ekth ekka ethokt ek json object ekk wenne nehe e nisa e ',' eka ain krgnna onee.
            //eka ain krnna mn use kre java special charactors   ...thwa allCustomer.subString(0,allCustomer.lenght-1); mekenuth puluwn eka ain krnna
            String json = "["+allCustomer.substring(0,allCustomer.length()-1)+"]";

            PrintWriter writer = resp.getWriter();
            writer.write(json);
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
        System.out.println(customerId+" "+customerName+" "+customerAddress+" "+customerSalary);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaEE","root","");
            PreparedStatement pst = con.prepareStatement("INSERT  INTO `customer` VALUES(?,?,?,?)");
            pst.setObject(1,customerId);
            pst.setObject(2,customerName);
            pst.setObject(3,customerAddress);
            pst.setObject(4,customerSalary);



            PrintWriter writer = resp.getWriter();
            if(pst.executeUpdate()>0){
                writer.write("Customer Added Success");
                return;
            }else{
                writer.write("Try Again");
                return;
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String customerId = req.getParameter("customerId");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaEE","root","");
            PreparedStatement pst = con.prepareStatement("DELETE FROM `customer` WHERE id=?");
            pst.setObject(1,customerId);
            PrintWriter writer = resp.getWriter();
            if(pst.executeUpdate()>0){
                writer.write("Customer Delete Success");
            }else{
                writer.write("Try Again");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String customerId = req.getParameter("customerId");
        String customerName = req.getParameter("customerName");
        String customerAddress = req.getParameter("customerAddress");
        String customerSalary = req.getParameter("customerSalary");
        System.out.println(customerId+" "+customerName+" "+customerAddress+" "+customerSalary);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaEE","root","");
            PreparedStatement pst = con.prepareStatement("UPDATE `customer` SET  `name`=? ,address=? ,salary=? WHERE id=?");

            pst.setObject(1,customerName);
            pst.setObject(2,customerAddress);
            pst.setObject(3,customerSalary);
            pst.setObject(4,customerId);



            PrintWriter writer = resp.getWriter();
            if(pst.executeUpdate()>0){
                writer.write("Customer Update Success");
                return;
            }else{
                writer.write("Try Again");
                return;
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
