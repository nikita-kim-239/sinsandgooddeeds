/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nikita.kim.util.SecurityUtil;



@WebServlet("/sinForm")
public class NewSinServlet extends HttpServlet{
    
    
    private static final String JDBC_LOGIN="postgres";
    private static final String JDBC_PASSWORD="postgres";
    private static final String JDBC_URL="jdbc:postgresql://localhost:5432/sinsandgooddeeds";
    private static final String INSERT_QUERY="insert into sins (dateofsin,description,userid) values (?,?,?)";
    
    
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
        {
            
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/sinForm.jsp");         
            requestDispatcher.forward(req, resp);   
        }
    
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
        {
            
            req.setCharacterEncoding("UTF-8");
            String description=req.getParameter("description");
            LocalDate date =LocalDate.parse(req.getParameter("date"));
            try {
		Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e) {
		System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
		e.printStackTrace();
		return;
            }  
            Connection connection=null;
            try{
                    connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                    PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);
                    pstmt.setObject(1,date);
                    pstmt.setString(2,description);
                    pstmt.setInt(3,SecurityUtil.getCurrentUser());
                    pstmt.executeUpdate();
                    
                    }
                    catch(SQLException ex)
                    {
                        ex.printStackTrace();
                    } 
            resp.sendRedirect(req.getContextPath()+"/userPage");
        }
 
}
