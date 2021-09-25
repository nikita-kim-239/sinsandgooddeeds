/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nikita.kim.util.SecurityUtil;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
        {
            
              SecurityUtil.setCurrentUser(null);
              SecurityUtil.setVotesToHeaven(null);
              SecurityUtil.setVotesToHell(null);              
              resp.sendRedirect(req.getContextPath()+"/login");         
        }
    
    
}
