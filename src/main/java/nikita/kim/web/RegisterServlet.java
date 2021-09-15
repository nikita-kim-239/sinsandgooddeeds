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
import nikita.kim.config.SpringConfig;
import nikita.kim.model.User;
import nikita.kim.repository.UserRepository;
import nikita.kim.util.SecurityUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
    

    
    
    
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
            
            List<String> logins=userRepository.getLogins();
            List<String> names=userRepository.getNames();
 
            if ((!logins.contains(login))&&(!names.contains(name)))
                {
                    
                    User user=new User();
                    user.setName(name);
                    user.setLogin(login);
                    user.setPassword(password);
                    userRepository.save(user);
                    resp.sendRedirect(req.getContextPath()+"/login");
                    
                }
            else
                {
                    System.out.println("Bad credentials");
                    resp.sendRedirect(req.getContextPath()+"/register");
                }
                    
                    
        }
    
}
