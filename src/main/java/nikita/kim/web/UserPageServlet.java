/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.web;

import java.io.IOException;
import java.util.List;
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
import nikita.kim.util.SecurityUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Никита
 */

@WebServlet("/userPage")
public class UserPageServlet extends HttpServlet{
    
    
    
    
    
    private ActRepository actRepository;
    private  AnnotationConfigApplicationContext context;
    
    @Override
    public void init()
        {
            context=new AnnotationConfigApplicationContext(JpaConfig.class);
            actRepository= context.getBean(ActRepository.class);
            
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
            
            List <Act> acts = actRepository.getAll(SecurityUtil.getCurrentUser());
           
            req.setAttribute("acts",acts);
            req.setAttribute("votesToHeaven",SecurityUtil.getVotesToHeaven());
            req.setAttribute("votesToHell",SecurityUtil.getVotesToHell());
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/userPage.jsp");         
            requestDispatcher.forward(req, resp);
                }    
        }
    
}
