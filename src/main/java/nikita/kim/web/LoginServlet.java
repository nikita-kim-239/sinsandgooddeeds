/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nikita.kim.config.SpringConfig;
import nikita.kim.repository.UserRepository;

import nikita.kim.util.SecurityUtil;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;




@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    
    private static final Logger log = Logger.getLogger(LoginServlet.class);
    private static final String JDBC_LOGIN="postgres";
    private static final String JDBC_PASSWORD="postgres";
    private static final String JDBC_URL="jdbc:postgresql://localhost:5432/sinsandgooddeeds";
    private static final String SELECT_USERS_QUERY="select * from users";
    
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
              RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/index.jsp");         
              requestDispatcher.forward(req, resp);          
        }
    
    
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
        {
            req.setCharacterEncoding("UTF-8");
            String login=req.getParameter("login").trim();
            String password=req.getParameter("password").trim();
            
            try {
            Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            }
            Connection connection=null;
            
            Map<String,String> users=userRepository.getLoginsAndPasswords();
            Map<String,Integer> userIds=userRepository.getLoginsAndIds();

            
            if ((users.containsKey(login))&&(users.get(login).equals(password)))
                {
                    SecurityUtil.setCurrentUser(userIds.get(login));
                    log.info("redirect to userPage");
                    resp.sendRedirect(req.getContextPath()+"/userPage");
                }
            else
                {
                    System.out.println("Bad credentials");
                    resp.sendRedirect(req.getContextPath()+"/login");
                }
                    
                    
        }
    
}
