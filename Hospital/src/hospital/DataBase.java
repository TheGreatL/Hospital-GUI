/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author Ken-Carlon
 */
public class DataBase {
        
    private static  String url = "jdbc:sqlserver://KEN-CARLON;databaseName=HospitalDb;encrypt=false;trustServerCertificate=false";
    private static    String userName="kencarlon";
    private static String password= "Skuubydoo123";
    private static PreparedStatement preparedStatement;
   
    
    protected static void writeStaffData(LinkedList<Object> info)
    {
        try(Connection con = DriverManager.getConnection(url, userName, password)){
              String query =  "INSERT INTO dbo.HospitalStaffTbl (Name,Password,Employee_ID, Address,Guardian,Contact,Age,Position) Values (?,?,?,?,?,?,?,?)";
              
          
           preparedStatement = con.prepareStatement(query);
           
           preparedStatement.setString(1, info.removeFirst().toString());
           preparedStatement.setString(2, info.removeFirst().toString());
               int emplyeeID =Integer.parseInt(info.removeFirst().toString());
           preparedStatement.setInt(3, emplyeeID);
           preparedStatement.setString(4, info.removeFirst().toString());
           preparedStatement.setString(5, info.removeFirst().toString());
         
           preparedStatement.setString(6, info.removeFirst().toString());
             preparedStatement.setInt(7, Integer.parseInt(info.removeFirst().toString()));
           preparedStatement.setString(8, info.removeFirst().toString());
           
           int rows= preparedStatement.executeUpdate();
           if(rows>0){
               
                JOptionPane.showMessageDialog(null, "Submit Successful");
                   JOptionPane.showMessageDialog(null, "Your Employee ID is: " +emplyeeID+"\n Dont Forget It.");
           }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
       protected static void writePatientData(LinkedList<Object> info)
       {
       try(Connection con = DriverManager.getConnection(url, userName,password)){
           String query =  "INSERT INTO dbo.PatientTbl (Name,PatientID, Address,Age,Guardian,Concern) Values (?,?,?,?,?,?)";
           preparedStatement = con.prepareStatement(query);
           
           preparedStatement.setString(1, info.removeFirst().toString());
           preparedStatement.setInt(2, Integer.parseInt(info.removeFirst().toString()));
           preparedStatement.setString(3, info.removeFirst().toString());
           preparedStatement.setInt(4, Integer.parseInt(info.removeFirst().toString()));
           preparedStatement.setString(5, info.removeFirst().toString());
           preparedStatement.setString(6, info.removeFirst().toString());
           
           int rows= preparedStatement.executeUpdate();
           if(rows>0){
                JOptionPane.showMessageDialog(null, "Submit Successful");
           }
       }
       
       catch(SQLException e){
           e.printStackTrace();
       }
    }

    static boolean isInput(JTextField EmplyeeIDField, String ipassword) 
    {
     boolean condition = false;
     try(Connection con = DriverManager.getConnection(url, userName, password)){
         String query= "SELECT  Employee_ID, Password FROM HospitalStaffTbl";
         Statement statement = con.createStatement();
         ResultSet resultSet = statement.executeQuery(query);
         
         while(resultSet.next())
         {
         if(resultSet.getString(1).equals(EmplyeeIDField.getText()) &&resultSet.getString(2).equals(ipassword)){
             return condition = true;
         }    
         }
     }  catch (SQLException ex) {
            ex.printStackTrace();
        }
     
     return condition;
    }
}
