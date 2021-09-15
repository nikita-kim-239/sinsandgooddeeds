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
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import nikita.kim.model.Act;
import nikita.kim.repository.ActRepository;
import nikita.kim.util.SecurityUtil;

/**
 *
 * @author Никита
 */
public class JdbcActRepository implements ActRepository{

    
    private static final String JDBC_LOGIN="postgres";
    private static final String JDBC_PASSWORD="postgres";
    private static final String JDBC_URL="jdbc:postgresql://localhost:5432/sinsandgooddeeds";
    private static final String SELECT_ACTS_BY_USER_ID_QUERY="select * from acts where userid=?";
    private static final String SELECT_ALL_SINS_QUERY="select * from acts";
    private static final String INSERT_QUERY="insert into acts (sin,acted,description,userid) values (?,?,?,?)";
    
    @Override
    public Act save(Act act) {
       
        Connection connection=null;
            try{
                    connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                    PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);
                    pstmt.setBoolean(1,act.getSin());
                    pstmt.setObject(2,act.getDate());
                    pstmt.setString(3,act.getDescription());                    
                    pstmt.setInt(4,SecurityUtil.getCurrentUser());
                    pstmt.executeUpdate();
                    
                    }
                    catch(SQLException ex)
                    {
                        ex.printStackTrace();
                    } 
            
            return act;
        
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Act get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Act> getAll() {
        Connection connection=null;
        List <Act> acts = new ArrayList<>();
        try{
            connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
            Statement pstmt = connection.createStatement();
                        
            ResultSet rs=pstmt.executeQuery(SELECT_ALL_SINS_QUERY);
                while(rs.next())
                    {                        
                        Act act=new Act();
                        act.setId(rs.getInt("id"));
                        act.setDate(rs.getObject(3,LocalDate.class));
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

    

    @Override
    public List<Act> getAllByUserId(Integer id) {
       Connection connection=null;
        List <Act> acts = new ArrayList<>();
        try{
            connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
            PreparedStatement pstmt = connection.prepareStatement(SELECT_ACTS_BY_USER_ID_QUERY);
            pstmt.setInt(1,id);
            ResultSet rs=pstmt.executeQuery();
                while(rs.next())
                    {                        
                        Act act=new Act();
                        act.setId(rs.getInt("id"));
                        act.setDate(rs.getObject(3,LocalDate.class));
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
