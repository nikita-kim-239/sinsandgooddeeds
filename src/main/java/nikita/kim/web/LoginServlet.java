/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.web;

import java.io.IOException;
import static java.nio.charset.StandardCharsets.UTF_8;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nikita.kim.config.SpringConfig;
import nikita.kim.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;




@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    
    private static final Logger log = Logger.getLogger(LoginServlet.class);
    
    
    @Autowired
    private UserService userService;
    private  AnnotationConfigApplicationContext context;
    private static DriverManagerDataSource dataSource;
    
    @Override
    public void init()
        {
            context=new AnnotationConfigApplicationContext(SpringConfig.class);
            userService= context.getBean(UserService.class);
            dataSource=context.getBean(DriverManagerDataSource.class);
            ResourceDatabasePopulator tables = new ResourceDatabasePopulator();
            tables.setSqlScriptEncoding(UTF_8.name());
            tables.addScript(new ClassPathResource("db/initDB.sql"));
            tables.addScript(new ClassPathResource("db/populateDB.sql"));
            DatabasePopulatorUtils.execute(tables, dataSource);
            
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
          
            if (userService.autorize(login, password))
                {                   
                    log.info(login+" autorized succesfully");
                    resp.sendRedirect(req.getContextPath()+"/userPage");
                }
            else
                {
                    log.info(login+" "+password+" are bad credentials");
                    resp.sendRedirect(req.getContextPath()+"/login");
                }
                    
                    
        }
    
}
