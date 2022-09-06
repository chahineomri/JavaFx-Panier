/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.IUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Models.User;
import Utils.maConnexion;
import Utils.BCrypt;

public class UserService implements IUser {

    //var
    Connection cnx = maConnexion.getInstance().getCnx();

    @Override
    public boolean Login(String EmailUser, String PasswordUser) throws Exception {
        if (!EmailUser.isEmpty() && !PasswordUser.isEmpty()) {
            String requete = "SELECT PasswordUser, UserStatus FROM user WHERE EmailUser = '" + EmailUser + "'";
            Statement s = maConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()) {
                if (BCrypt.checkpw(PasswordUser, rs.getString("PasswordUser"))) {
                    if (rs.getInt("UserStatus") == 1) {
                        System.out.println("login success");
                        return true;
                    } else {
                        System.out.println("you were banned from using this app check your email for more information");
                        return false;
                    }
                } else {
                    System.out.println("Please enter correct Email or Password");
                    return false;
                }
            } else {
                System.out.println("Please enter correct Email or Password");
                return false;
            }
        } else {
            System.out.println("fill missing infos!");
            return false;

        }

    }

    //addUser
    @Override
    public void AddUser(User u, String PasswordUser) {

        String Req = "INSERT INTO `user`(`NameUser`, `LastNameUser`, `EmailUser`, `ProfilePicUser`,`PasswordUser`,`UserRole`) VALUES (?,?,?,?,?,?)";
        try {
            String hashedpw = BCrypt.hashpw(PasswordUser, BCrypt.gensalt(12));
            PreparedStatement su = cnx.prepareStatement(Req);
            su.setString(1, u.getNameUser());
            su.setString(2, u.getLastNameUser());
            su.setString(3, u.getEmailUser());
            su.setString(4, u.getProfilePicUser());
            su.setString(5, hashedpw);
            su.setString(6, u.getUserRole());
            su.execute();
            System.out.println("User added!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
//ShowUser

    @Override
    public List<User> ShowUser() {
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM user";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

//UpdateUser
    @Override
    public boolean UpdateUser(User u, int IdUser) {

        try {
            String cpass = BCrypt.hashpw(u.getPasswordUser(), BCrypt.gensalt(12));
            String sql = "UPDATE user SET NameUser =?, LastNameUser=?, EmailUser=?, ProfilePicUser=?, PasswordUser=? WHERE IdUser=?";

            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setString(1, u.getNameUser());
            statement.setString(2, u.getLastNameUser());
            statement.setString(3, u.getEmailUser());
            statement.setString(4, u.getProfilePicUser());
            statement.setString(5, cpass);
            statement.setInt(6, IdUser);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return true;
    }
    //Update UserName  

    @Override
    public void UpdateUsername(String EmailUser, String NameUser) throws SQLException {
        String sql = "UPDATE user SET NameUser='" + NameUser + "'" + "WHERE EmailUser='" + EmailUser + "'";
        PreparedStatement statement = cnx.prepareStatement(sql);
        int rowsUpdated = statement.executeUpdate(sql);
        if (rowsUpdated > 0) {
            System.out.println("username updated successfully!");
        }
    }

    //Update Password
    @Override
    public void UpdatePassword(String EmailUser, String PasswordUser) throws SQLException {
        String cpass = BCrypt.hashpw(PasswordUser, BCrypt.gensalt(12));
        String sql = "UPDATE user SET PasswordUser='" + cpass + "'" + "WHERE EmailUser='" + EmailUser + "'";
        PreparedStatement statement = cnx.prepareStatement(sql);
        int rowsUpdated = statement.executeUpdate(sql);
        if (rowsUpdated > 0) {
            System.out.println("password updated successfully!");
        }
    }

    //update profile picture
    @Override
    public void UpdateProfilePicUser(String EmailUser, String ProfilePicUser) throws SQLException {
        String sql = "UPDATE user SET ProfilePicUser='" + ProfilePicUser + "'" + "WHERE EmailUser='" + EmailUser + "'";
        PreparedStatement statement = cnx.prepareStatement(sql);
        int rowsUpdated = statement.executeUpdate(sql);
        if (rowsUpdated > 0) {
            System.out.println("profile picture updated successfully!");
        }
    }

//DeleteUser
    @Override
    public void DeleteUser(int IdUser) {

        try {
            String sql = "Delete FROM user WHERE IdUser=?";

            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setInt(1, IdUser);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User deleted!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    //search user by username
    @Override
    public User getUserByNameUser(String NameUser) throws SQLException {
        String sql = "SELECT * FROM user WHERE NameUser='" + NameUser + "'";
        Statement statement = cnx.prepareStatement(sql);
        //statement.executeUpdate(sql);
        ResultSet rs = statement.executeQuery(sql);
        User u = new User();
        while (rs.next()) {
            u.setIdUser(rs.getInt("IDUser"));
            u.setNameUser(rs.getString("NameUser"));
            u.setLastNameUser(rs.getString("LastNameUser"));
            u.setEmailUser(rs.getString("EmailUser"));
            u.setProfilePicUser(rs.getString("ProfilePicUser"));
            u.setUserRole(rs.getString("UserRole"));
            u.setUserStatus(rs.getInt("UserStatus"));

        }
        return u;
    }

    //search user by mail 
    @Override
    public User getUserByMail(String EmailUser) throws SQLException {
        String sql = "SELECT * FROM user WHERE EmailUser='" + EmailUser + "'";
        Statement statement = cnx.prepareStatement(sql);
        //statement.executeUpdate(sql);
        ResultSet rs = statement.executeQuery(sql);
        User u = new User();
        while (rs.next()) {
            u.setIdUser(rs.getInt("IDUser"));
            u.setNameUser(rs.getString("NameUser"));
            u.setLastNameUser(rs.getString("LastNameUser"));
            u.setEmailUser(rs.getString("EmailUser"));
            u.setProfilePicUser(rs.getString("ProfilePicUser"));
            u.setUserRole(rs.getString("UserRole"));
            u.setUserStatus(rs.getInt("UserStatus"));

        }
        return u;
    }
    //search  id by mail used in SendMail to verify that the user has an account

    @Override
    public int getIdbyMail(String EmailUser) throws SQLException {
        PreparedStatement st = cnx.prepareStatement("select IdUser from user where EmailUser=?");
        st.setString(1, EmailUser);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getInt("IdUser");
        }
        return 0;
    }

    @Override
    public int getId() throws SQLException {
        String sql = "SELECT IdUser FROM user ";
        Statement statement = cnx.prepareStatement(sql);
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("IdUser");
            return id;
        }
        return 0;
    }
    //search role by id used to determine role when logging in to determine dashboard to show

    @Override
    public String getRolebyId(int IdUser) throws SQLException {

        PreparedStatement st = cnx.prepareStatement("select UserRole from user where IdUser=?");
        st.setInt(1, IdUser);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getString("UserRole");

        }
        return "";
    }

    //search  mail by id used in SendMail to verify that the user has an account
    @Override
    public String getMailbyId(int IdUser) throws SQLException {

        PreparedStatement st = cnx.prepareStatement("select EmailUser from user where IdUser=?");
        st.setInt(1, IdUser);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getString("EmailUser");
        }
        return "";
    }

    @Override
    public String getNamebyId(int IdUser) throws SQLException {
        PreparedStatement st = cnx.prepareStatement("select NameUser from user where IdUser=?");
        st.setInt(1, IdUser);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getString("NameUser");
        }
        return "";

    }

    @Override
    public String getLastNamebyId(int IdUser) throws SQLException {
        PreparedStatement st = cnx.prepareStatement("select LastNameUser from user where IdUser=?");
        st.setInt(1, IdUser);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getString("LastNameUser");
        }
        return "";

    }

    @Override
    public String getStatusbyId(int IdUser) throws SQLException {
        PreparedStatement st = cnx.prepareStatement("select UserStatus from user where IdUser=?");
        st.setInt(1, IdUser);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            if ("1".equals(rs.getString("UserStatus"))) {
                return "allowed";
            }
        } else {
            return "blocked";
        }
        return "";

    }

    @Override
    public String getProfilePicbyId(int IdUser) throws SQLException {
        PreparedStatement st = cnx.prepareStatement("select ProfilePicUser from user where IdUser=?");
        st.setInt(1, IdUser);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getString("ProfilePicUser");
        }
        return "";

    }

    @Override
    public String getPasswordbyMail(String EmailUser) throws SQLException {
        PreparedStatement st = cnx.prepareStatement("select PasswordUser from user where EmailUser=?");
        st.setString(1, EmailUser);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getString("PasswordUser");
        }
        return "";

    }

}
