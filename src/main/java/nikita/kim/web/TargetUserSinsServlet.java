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
import java.time.LocalDateTime;
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



@WebServlet("/usersins")
public class TargetUserSinsServlet extends HttpServlet{
    
     private static final String JDBC_LOGIN="postgres";
    private static final String JDBC_PASSWORD="postgres";
    private static final String JDBC_URL="jdbc:postgresql://localhost:5432/sinsandgooddeeds";
    private static final String INSERT_VOTE_QUERY="insert into votes (timeofvote,toheaven,userid,targetuserid) values(?,?,?,?)";
    private static final String SELECT_ACTS_QUERY="select * from acts where userid=?";
    
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
            Integer targetUserId=Integer.parseInt(req.getParameter("targetuser"));
            req.setCharacterEncoding("UTF-8");
            List <Act> acts = new ArrayList<>();
            try{
                connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ACTS_QUERY);
                pstmt.setInt(1,targetUserId);
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
            req.setAttribute("targeruser", targetUserId);
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/targetuser.jsp");         
            requestDispatcher.forward(req, resp);
                }    
        }
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
        {
            
            
            if (SecurityUtil.getCurrentUser()==null)
            {
                resp.sendRedirect(req.getContextPath()+"/login"); 
            }
                
            else
            {   
            Connection connection=null;
            String vote=req.getParameter("vote");
            Integer targetuser=Integer.parseInt(req.getParameter("targetuser"));
            LocalDateTime timeofvote=LocalDateTime.now();
            boolean heaven=true;
            if (vote.equals("hell")) 
                heaven=false;
            try{
                connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(INSERT_VOTE_QUERY);
                
                pstmt.setInt(3,SecurityUtil.getCurrentUser());
                pstmt.setInt(4,targetuser);
                pstmt.setBoolean(2,heaven);
                pstmt.setObject(1,timeofvote);
                pstmt.executeUpdate();
                    
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            
            resp.sendRedirect(req.getContextPath()+"/userPage");
                }    
        }
    
}
