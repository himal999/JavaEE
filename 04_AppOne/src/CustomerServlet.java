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
}
