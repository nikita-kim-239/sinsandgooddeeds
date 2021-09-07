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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nikita.kim.util.SecurityUtil;



@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
    
    private static final String JDBC_LOGIN="postgres";
    private static final String JDBC_PASSWORD="postgres";
    private static final String JDBC_URL="jdbc:postgresql://localhost:5432/sinsandgooddeeds";
    private static final String SELECT_USERS_QUERY="select * from users";
    private static final String INSERT_USERS_QUERY="insert into users (name,login,password) values (?,?,?)";
    
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
        {
              
              ServletContext servletContext = getServletContext();
              RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/register.jsp");         
              requestDispatcher.forward(req, resp);          
        }
    
    
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
        {
            req.setCharacterEncoding("UTF-8");
            String name=req.getParameter("name").trim();
            String login=req.getParameter("login").trim();
            String password=req.getParameter("password").trim();
            Connection connection=null;
            
            List<String> logins=new ArrayList<>();
            List<String> names=new ArrayList<>();
            
                    try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        Statement stmt = connection.createStatement();
                        ResultSet rs=stmt.executeQuery(SELECT_USERS_QUERY);
                        while(rs.next())
                            {                        
                                logins.add(rs.getString("login"));
                                
                                names.add(rs.getString("name"));
                            }
                        }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }
            
            if ((!logins.contains(login))&&(!names.contains(name)))
                {
                    
                    try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        PreparedStatement pstmt = connection.prepareStatement(INSERT_USERS_QUERY);
                        pstmt.setString(1,name);
                        pstmt.setString(2,login);
                        pstmt.setString(3,password);
                        pstmt.executeUpdate();
                        
                        }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }
                    
                    resp.sendRedirect(req.getContextPath()+"/login");
                    
                }
            else
                {
                    System.out.println("Bad credentials");
                    resp.sendRedirect(req.getContextPath()+"/register");
                }
                    
                    
        }
    
}
