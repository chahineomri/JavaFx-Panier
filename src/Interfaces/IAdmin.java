/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.sql.SQLException;
import Models.User;

/**
 *
 * @author sarawahada
 */
public interface IAdmin extends IUser {

    //addAdmin
    public void AddAdmin(User u, String PasswordUser);
    //ban user
    //public boolean Banned( int UserStatus);
    //update user role

    public void UpdateRole(String EmailUser, String UserRole) throws SQLException;

    //update user status (banned or allowed)
    public void UpdateUserStatus(int IDUser, int UserStatus) throws SQLException;
    
    public boolean UpdateUser(User u, int PasswordUser);

}
