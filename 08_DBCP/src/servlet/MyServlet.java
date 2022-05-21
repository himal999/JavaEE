package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/Life_cycle")
public class MyServlet extends HttpServlet {

    //Servlet Life Cycle
     public  MyServlet(){
         System.out.println("Constructor");
     }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Servlet ekk Kra");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get");
    }

    @Override
    public void destroy() {
        System.out.println("Close");
    }

    // constructor
    //servelet eekk kraa
    //get
}
