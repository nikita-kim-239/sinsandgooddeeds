/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.web;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nikita.kim.config.JpaConfig;
import nikita.kim.config.SpringConfig;
import nikita.kim.model.Act;
import nikita.kim.repository.ActRepository;
import nikita.kim.service.ActService;
import nikita.kim.util.SecurityUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



@WebServlet("/create")
public class CreateServlet extends HttpServlet{
    
    
    private ActService actService;
    private  AnnotationConfigApplicationContext context;
    
    @Override
    public void init()
        {
            context=new AnnotationConfigApplicationContext(JpaConfig.class);
            actService= context.getBean(ActService.class);
            
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
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/createForm.jsp");         
            requestDispatcher.forward(req, resp);  
                }
        }
    
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
        {
            
            req.setCharacterEncoding("UTF-8");
            String description=req.getParameter("description");
            LocalDate date =LocalDate.parse(req.getParameter("date"));
            Boolean isSin=(req.getParameter("sin").equals("true"))?true:false;
            
            actService.create(new Act(isSin,date,description),SecurityUtil.getCurrentUser());
            resp.sendRedirect(req.getContextPath()+"/userPage");
        }
 
}
