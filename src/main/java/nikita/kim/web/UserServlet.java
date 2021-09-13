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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nikita.kim.model.Act;
import nikita.kim.model.User;
import nikita.kim.util.SecurityUtil;



@WebServlet("/users")
public class UserServlet extends HttpServlet{
    
    
    private static final String JDBC_LOGIN="postgres";
    private static final String JDBC_PASSWORD="postgres";
    private static final String JDBC_URL="jdbc:postgresql://localhost:5432/sinsandgooddeeds";
    private static final String SELECT_USERS_QUERY="select * from users where id!=?";
    
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
        {
            
            
            if (SecurityUtil.getCurrentUser()==null)
            {
                resp.sendRedirect(req.getContextPath()+"/login"); 
            }
                
            else
            {   
            Connection connection=null;
            
            req.setCharacterEncoding("UTF-8");
            List <User> users = new ArrayList<>();
            try{
                connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(SELECT_USERS_QUERY);
                pstmt.setInt(1,SecurityUtil.getCurrentUser());
                ResultSet rs=pstmt.executeQuery();
                    while(rs.next())
                        {                        
                            User user=new User();
                            user.setId(rs.getInt("id"));
                            user.setName(rs.getString("name"));
                            user.setLogin(rs.getString("login"));
                            user.setPassword(rs.getString("password"));
                            users.add(user);
                                
                        }
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            req.setAttribute("users",users);
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/users.jsp");         
            requestDispatcher.forward(req, resp);
                }    
        }
    
}
