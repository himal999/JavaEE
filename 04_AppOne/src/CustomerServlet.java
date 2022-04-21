import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            while (rst.next()){
                System.out.print(rst.getString(1)+" ");
                System.out.print(rst.getString(2)+" ");
                System.out.print(rst.getString(3)+" ");
                System.out.println(rst.getString(4));

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
