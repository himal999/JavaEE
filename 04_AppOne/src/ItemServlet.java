import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JavaEE", "root", "");
            ResultSet rst = con.prepareStatement("SELECT * FROM `item`").executeQuery();
            String allItems="";
            while (rst.next()){

                String item = "{\"id\":\""+rst.getString(1)+"\",\"name\":\""+rst.getString(2)+"\",\"qty\":"+rst.getString(3)+",\"unitPrice\":"+rst.getString(4)+"},";
                allItems+=item;
            }

             String json = "["+allItems.substring(0,allItems.length()-1)+"]";

            PrintWriter writer = resp.getWriter();
            writer.write(json);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
