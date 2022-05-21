import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicDataSource bds  =  new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUsername("root");
        bds.setPassword("");
        bds.setUrl("jdbc:mysql://localhost:3306/JavaEE");
        bds.setMaxTotal(5);
        bds.setInitialSize(5);

        Connection connection = null;
        try {
            connection = bds.getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from `customer`");
            ResultSet rst = pst.executeQuery();
            while (rst.next()){
                System.out.println(rst.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
