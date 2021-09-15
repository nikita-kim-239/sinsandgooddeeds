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
import java.util.stream.Collectors;
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
import nikita.kim.repository.UserRepository;
import nikita.kim.util.SecurityUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



@WebServlet("/users")
public class UserServlet extends HttpServlet{
    
    
    
    
    private UserRepository userRepository;
    private  AnnotationConfigApplicationContext context;
    
    @Override
    public void init()
        {
            context=new AnnotationConfigApplicationContext(SpringConfig.class);
            userRepository= context.getBean(UserRepository.class);
            
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
            
            
            req.setCharacterEncoding("UTF-8");
            List <User> users = userRepository.getAll().stream().filter(u->u.getId()!=SecurityUtil.getCurrentUser()).collect(Collectors.toList());
            
            req.setAttribute("users",users);
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/users.jsp");         
            requestDispatcher.forward(req, resp);
                }    
        }
    
}
