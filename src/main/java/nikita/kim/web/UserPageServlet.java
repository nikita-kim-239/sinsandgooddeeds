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
import nikita.kim.util.SecurityUtil;

/**
 *
 * @author Никита
 */

@WebServlet("/userPage")
public class UserPageServlet extends HttpServlet{
    
    
    
    private static final String JDBC_LOGIN="postgres";
    private static final String JDBC_PASSWORD="postgres";
    private static final String JDBC_URL="jdbc:postgresql://localhost:5432/sinsandgooddeeds";
    private static final String SELECT_SINS_QUERY="select * from acts where userid=?";
    
    
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
        {
            Connection connection=null;
            
            req.setCharacterEncoding("UTF-8");
            List <Act> acts = new ArrayList<>();
                    try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        PreparedStatement pstmt = connection.prepareStatement(SELECT_SINS_QUERY);
                        pstmt.setInt(1,SecurityUtil.getCurrentUser());
                        ResultSet rs=pstmt.executeQuery();
                        while(rs.next())
                            {                        
                                Act act=new Act();
                                act.setDate(rs.getObject(3,LocalDate.class));
                                act.setSin(rs.getBoolean("sin"));
                                act.setDescription(rs.getString("description"));
                                acts.add(act);
                                
                            }
                        }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }
                    req.setAttribute("acts",acts);
                    ServletContext servletContext = getServletContext();
                    RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/userPage.jsp");         
                    requestDispatcher.forward(req, resp);
        }
    
}
