/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nikita.kim;

import java.util.Scanner;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Никита
 */
public class PasswordEncoder {
    
    public static void main(String[] args)
        {
        
            Scanner sc = new Scanner(System.in);
            String password=sc.next();
            String md5Hex = DigestUtils.md5Hex(password);
            System.out.println(md5Hex);
        }   
}
