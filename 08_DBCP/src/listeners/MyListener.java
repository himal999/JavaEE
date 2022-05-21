package listeners;/*
author :Himal
version : 0.0.1
*/

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BasicDataSource bds  =  new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUsername("root");
        bds.setPassword("");
        bds.setUrl("jdbc:mysql://localhost:3306/JavaEE");
        bds.setMaxTotal(5);
        bds.setInitialSize(5);
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("bds",bds);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        BasicDataSource bds = (BasicDataSource) servletContext.getAttribute("bds");
        try {
            bds.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
