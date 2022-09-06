/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.User;
import java.sql.SQLException;
import java.util.List; 

public interface IUser {
    //Add
    public void AddUser(User u,String PasswordUse);
    //List
    public List<User> ShowUser();
    //Update user except status and role
    public boolean UpdateUser(User u,int IdUser);
   //Delete 
    public void DeleteUser( int IdUser);
    //login 
    public boolean Login(String NameUser, String PasswordUser)throws Exception;
    //BanUser
    //public boolean Banned( int UserStatus);
    //Update username
    public void UpdateUsername(String EmailUser, String NameUser) throws SQLException;
    //Update password
    public void UpdatePassword(String EmailUser, String pass) throws SQLException;
    //Update profile picture
     public void UpdateProfilePicUser(String EmailUser, String ProfilePicUser) throws SQLException;
    //Mail
    //search user by username
     public User getUserByNameUser(String NameUser) throws SQLException;
    //search user by mail
     public User getUserByMail(String EmailUser)throws SQLException;
     //search role by mail
     public int getIdbyMail(String EmailUser) throws SQLException;
      //search role by Id
     public String getRolebyId(int IdUser) throws SQLException;
     //search mail by id
     public String getMailbyId(int IdUser) throws SQLException;
     //search name using id
     public String getNamebyId(int IdUser) throws SQLException;
     //search last name using id
     public String getLastNamebyId(int IdUser) throws SQLException;
      //search status using id
     public String getStatusbyId(int IdUser) throws SQLException;
     //search status using id
     public String getProfilePicbyId(int IdUser) throws SQLException;
     //search password using mail
     public String getPasswordbyMail(String EmailUser) throws SQLException; 
      //display all users id
     public int getId() throws SQLException;
}
