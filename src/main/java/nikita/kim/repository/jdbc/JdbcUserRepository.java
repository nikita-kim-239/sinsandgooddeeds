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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nikita.kim.model.User;
import nikita.kim.repository.UserRepository;
import nikita.kim.web.LoginServlet;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;



@Repository
public class JdbcUserRepository implements UserRepository{

    private static final Logger log = Logger.getLogger(LoginServlet.class);
    private static final String JDBC_LOGIN="postgres";
    private static final String JDBC_PASSWORD="postgres";
    private static final String JDBC_URL="jdbc:postgresql://localhost:5432/sagd?characterEncoding=UTF-8";
    private static final String SELECT_USERS_QUERY="select distinct U.id,U.nick,U.login,U.password,(Select count(*) from votes where target_user_id=U.id and toheaven=true and actual=true group by target_user_id) as HeavenCount,(Select count(*) from votes where target_user_id=U.id and toheaven=false and actual=true group by target_user_id) as HellCount from users as U left join votes as V on U.id=V.target_user_id";
    private static final String INSERT_USERS_QUERY="insert into users (name,login,password) values (?,?,?)";
    private static final String DELETE_USER_QUERY="delete from users where id=?";
    private static final String GET_BY_ID_QUERY="select * from users where id=?";
    private static final String SELECT_PASSORD_AND_LOGIN_QUERY="select login,password from users";
    private static final String SELECT_ID_AND_LOGIN_QUERY="select login,id from users";
    private static final String SELECT_NAMES_QUERY="select name from users";
    private static final String SELECT_LOGINS_QUERY="select login from users";
    
    @Override
    public User save(User user) {
        
        
        Connection connection=null;
        
        try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        PreparedStatement pstmt = connection.prepareStatement(INSERT_USERS_QUERY);
                        pstmt.setString(1,user.getName());
                        pstmt.setString(2,user.getLogin());
                        pstmt.setString(3,user.getPassword());
                        pstmt.executeUpdate();
                        
                        }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }
        return user;
        
    }

    @Override
    public boolean delete(int id) {
        int rows=0;
        Connection connection=null;
        try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        PreparedStatement pstmt = connection.prepareStatement(DELETE_USER_QUERY);
                        pstmt.setInt(1,id);
                        rows=pstmt.executeUpdate();
           }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }  

        return rows!=0;
    }

    @Override
    public List<User> getAll() {
        
        
        List <User> users=new ArrayList<>();
        Connection connection=null;
        try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        Statement stmt = connection.createStatement();
                        ResultSet rs=stmt.executeQuery(SELECT_USERS_QUERY);
                        while(rs.next())
                            {                        
                                User user= new User();
                                user.setId(rs.getInt("id"));
                                user.setName(rs.getString("nick"));
                                user.setLogin(rs.getString("login"));
                                user.setPassword(rs.getString("password"));
                                user.setVotesToHeaven(rs.getInt("heavencount"));
                                user.setVotesToHell(rs.getInt("hellcount"));
                                users.add(user);
                                        
                            }
                        }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }
            
        return users;
    }

    @Override
    public User getUserById(int id) {
        Connection connection=null;
        User user= new User();
        
        try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        PreparedStatement pstmt = connection.prepareStatement(GET_BY_ID_QUERY);
                        pstmt.setInt(1,id);
                        ResultSet rs=pstmt.executeQuery();
                        
                        if(rs.next())
                            {                        
                                
                                user.setId(rs.getInt("id"));
                                user.setName(rs.getString("nick"));
                                user.setLogin(rs.getString("login"));
                                user.setPassword(rs.getString("password"));
                               
                                        
                            }
                        else
                            {
                                
                                user=null; 
                               
                            }
                        
        }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }  
        
        return user;
    }

    @Override
    public Map<String, String> getLoginsAndPasswords() {
        Map <String,String> logins = new HashMap<>();
        
        Connection connection=null;
        try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        Statement stmt = connection.createStatement();
                        ResultSet rs=stmt.executeQuery(SELECT_PASSORD_AND_LOGIN_QUERY);
                        while(rs.next())
                            {                        
                                String login=rs.getString("login");
                                String password=rs.getString("password");
                                logins.put(login,password);
                                        
                            }
                        }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }
            
        return logins;
    }

    @Override
    public Map<String, Integer> getLoginsAndIds() {
         Map <String,Integer> ids = new HashMap<>();
        
        Connection connection=null;
        try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        Statement stmt = connection.createStatement();
                        ResultSet rs=stmt.executeQuery(SELECT_ID_AND_LOGIN_QUERY);
                        while(rs.next())
                            {                        
                                String login=rs.getString("login");
                                Integer id=rs.getInt("id");
                                ids.put(login, id);
                                        
                            }
                        }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }
            
        return ids;
    }

    @Override
    public List<String> getNames() {
        List <String> names = new ArrayList<>();
        
        Connection connection=null;
        try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        Statement stmt = connection.createStatement();
                        ResultSet rs=stmt.executeQuery(SELECT_NAMES_QUERY);
                        while(rs.next())
                            {                        
                                names.add(rs.getString("nick"));
                                        
                            }
                        }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }
            
        return names;
    }

    @Override
    public List<String> getLogins() {
        List <String> logins = new ArrayList<>();
        
        Connection connection=null;
        try{
                        connection=DriverManager.getConnection(JDBC_URL,JDBC_LOGIN,JDBC_PASSWORD);
                        Statement stmt = connection.createStatement();
                        ResultSet rs=stmt.executeQuery(SELECT_LOGINS_QUERY);
                        while(rs.next())
                            {                        
                                logins.add(rs.getString("login"));
                                        
                            }
                        }
                        catch(SQLException ex)
                        {
                            ex.printStackTrace();
                        }
            
        return logins;
    }
    
}
