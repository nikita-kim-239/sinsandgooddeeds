/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nikita.kim.config.SpringConfig;
import nikita.kim.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
    

    private static final Logger log = Logger.getLogger(LoginServlet.class); 
    
    
    private UserService userService;
    private  AnnotationConfigApplicationContext context;
    
    @Override
    public void init()
        {
            context=new AnnotationConfigApplicationContext(SpringConfig.class);
            userService= context.getBean(UserService.class);
            
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
            
            
 
            if (userService.register(name,login,password))
                {   
                    log.info("user with name "+name+" has been created succesfully");
                    resp.sendRedirect(req.getContextPath()+"/login");                    
                }
            else
                {
                    log.info(name+" "+login+" "+password+" are bad credentials");
                    resp.sendRedirect(req.getContextPath()+"/register");
                }
                    
                    
        }
    
}
