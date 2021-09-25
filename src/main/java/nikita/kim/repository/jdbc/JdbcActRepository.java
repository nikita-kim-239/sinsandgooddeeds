/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim.repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import nikita.kim.model.Act;
import nikita.kim.model.User;
import nikita.kim.repository.ActRepository;
import org.springframework.stereotype.Repository;




@Repository
public class JdbcActRepository implements ActRepository{

    
    private static final String JDBC_LOGIN="postgres";
    private static final String JDBC_PASSWORD="postgres";
    private static final String JDBC_URL="jdbc:postgresql://localhost:5432/sagd?characterEncoding=UTF-8";
    private static final String SELECT_ACTS_BY_USER_ID_QUERY="select * from acts where user_id=?";
    private static final String SELECT_ACT_BY_USER_ID_QUERY="select * from acts where id=? and user_id=?";    
    private static final String INSERT_QUERY="insert into acts (sin,acted,description,user_id) values (?,?,?,?)";
    private static final String UPDATE_ACTS_QUERY="update acts set sin=?,acted=?,description=? where id=?";
    private static final String DELETE_QUERY="delete from acts where id=? and user_id=?";
    
    @Override
    public boolean create(Act act,Integer userId) {
        int rows=0;
        Connection connection=null;
            try{
                    connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                    PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);
                    pstmt.setBoolean(1,act.getSin());
                    pstmt.setObject(2,act.getActed());
                    pstmt.setString(3,act.getDescription());                    
                    pstmt.setInt(4,userId);
                    rows=pstmt.executeUpdate();
                    
                    }
                    catch(SQLException ex)
                    {
                        ex.printStackTrace();
                    } 
            
            return rows!=0?true:false;
        
    }
    
    
    @Override
    public boolean update(Act act,Integer userId) {
        int rows=0;
        Connection connection=null;
            try{
                    connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                    PreparedStatement pstmt = connection.prepareStatement(UPDATE_ACTS_QUERY);
                    pstmt.setBoolean(1,act.getSin());
                    pstmt.setObject(2,act.getActed());
                    pstmt.setString(3,act.getDescription());                    
                    pstmt.setInt(4,act.getId());
                    rows=pstmt.executeUpdate();
                    
                    }
                    catch(SQLException ex)
                    {
                        ex.printStackTrace();
                    } 
            
            return rows!=0?true:false;
        
    }
    

    @Override
    public boolean delete(int id,int userId) {
        
        Connection connection=null;
        int rows=0;
            try{
                    connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                    PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);
                    pstmt.setInt(1,id);
                    pstmt.setInt(2,userId);
                    
                    rows=pstmt.executeUpdate();
                    
                       
                    
                    }
                    catch(SQLException ex)
                    {
                        ex.printStackTrace();
                    } 
            
            return rows!=0?true:false;
        
    }

    @Override
    public Act get(Integer id,Integer userId) {
        
        Connection connection=null;
        Act act = new Act();
        try{
            connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(SELECT_ACT_BY_USER_ID_QUERY);
            pstmt.setInt(1,id);
            pstmt.setInt(2,userId);
            ResultSet rs=pstmt.executeQuery();
                if(rs.next())
                    {                        
                        
                        act.setId(rs.getInt("id"));
                        act.setActed(rs.getObject(3,LocalDate.class));
                        act.setSin(rs.getBoolean("sin"));
                        act.setDescription(rs.getString("description"));
                        User user = new User();
                        user.setId(userId);
                        act.setUser(user);
                        
                                
                    }
                else
                    {
                        act=null;
                    }
            }
            catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
        
        return act;
        
    }

    @Override
    public List<Act> getAll(int userId) {
        Connection connection=null;
        List <Act> acts = new ArrayList<>();
        try{
            connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(SELECT_ACTS_BY_USER_ID_QUERY);
            pstmt.setInt(1,userId);
            ResultSet rs=pstmt.executeQuery();
                while(rs.next())
                    {                        
                        Act act=new Act();
                        act.setId(rs.getInt("id"));
                        act.setActed(rs.getObject(3,LocalDate.class));
                        act.setSin(rs.getBoolean("sin"));
                        act.setDescription(rs.getString("description"));
                        acts.add(act);
                                
                    }
            }
            catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
        return acts;
    }
   
}
