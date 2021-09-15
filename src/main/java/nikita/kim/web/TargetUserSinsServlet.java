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
import nikita.kim.config.SpringConfig;
import nikita.kim.model.Act;
import nikita.kim.model.User;
import nikita.kim.model.Vote;
import nikita.kim.repository.ActRepository;
import nikita.kim.repository.VoteRepository;
import nikita.kim.util.SecurityUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



@WebServlet("/usersins")
public class TargetUserSinsServlet extends HttpServlet{
    
    
    
    private VoteRepository voteRepository;
    private ActRepository actRepository;
    private  AnnotationConfigApplicationContext context;
    
    @Override
    public void init()
        {
            context=new AnnotationConfigApplicationContext(SpringConfig.class);
            voteRepository= context.getBean(VoteRepository.class);
            actRepository=context.getBean(ActRepository.class);
            
        }
    
    @Override 
    public void destroy()
        {
            context.close();
            super.destroy();
        }
    
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
            List <Act> acts =actRepository.getAllByUserId(targetUserId);
            
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
            Integer targetUser=Integer.parseInt(req.getParameter("targetuser"));
            LocalDateTime timeOfVote=LocalDateTime.now();
            boolean heaven=true;
            if (vote.equals("hell")) 
                heaven=false;
            voteRepository.save(new Vote(timeOfVote,heaven,SecurityUtil.getCurrentUser(),targetUser));
            
            resp.sendRedirect(req.getContextPath()+"/userPage");
                }    
        }
    
}
