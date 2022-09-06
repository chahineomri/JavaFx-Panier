package Services;

import Interfaces.IAdmin;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Models.User;
import Utils.BCrypt; 

/**
 *
 * @author sarawahada
 */
public class AdminService extends UserService implements IAdmin {
    //addUser

    @Override
    public void AddAdmin(User u, String PasswordUser) {

        String Req = "INSERT INTO `user`(`NameUser`, `LastNameUser`, `EmailUser`, `ProfilePicUser`,`PasswordUser`,`UserRole`,`UserStatus`) VALUES (?,?,?,?,?,?,?)";
        try {
            String hashedpw = BCrypt.hashpw(PasswordUser, BCrypt.gensalt(12));
            PreparedStatement su = cnx.prepareStatement(Req);
            su.setString(1, u.getNameUser());
            su.setString(2, u.getLastNameUser());
            su.setString(3, u.getEmailUser());
            su.setString(4, u.getProfilePicUser());
            su.setString(5, hashedpw);
            su.setString(6, u.getUserRole());
            su.setInt(7, u.getUserStatus());
            su.execute();
            System.out.println("admin added!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    //update user

    @Override
    public boolean UpdateUser(User u, int PasswordUser) {

        try {
            String cpass = BCrypt.hashpw(u.getPasswordUser(), BCrypt.gensalt(12));
            String sql = "UPDATE user SET NameUser =?, LastNameUser=?, EmailUser=?, ProfilePicUser=?, PasswordUser=?, UserRole=?,UserStatus=? WHERE IdUser=?";

            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setString(1, u.getNameUser());
            statement.setString(2, u.getLastNameUser());
            statement.setString(3, u.getEmailUser());
            statement.setString(4, u.getProfilePicUser());
            statement.setString(5, cpass);
            statement.setString(6, u.getUserRole());
            statement.setInt(7, u.getUserStatus());
            statement.setInt(8, u.getIdUser());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return true;
    }
    //update user role

    @Override
    public void UpdateRole(String EmailUser, String UserRole) throws SQLException {
        String sql = "UPDATE user SET UserRole='" + UserRole + "'" + "WHERE EmailUser='" + EmailUser + "'";
        PreparedStatement statement = cnx.prepareStatement(sql);
        statement.executeUpdate(sql);
    }
    //update user status

    @Override
    public void UpdateUserStatus(int IDUser, int UserStatus) throws SQLException {
        System.out.println(UserStatus);
        String sql = "UPDATE user SET UserStatus='" + UserStatus + "'" + "WHERE IDUser='" + IDUser + "'";
        PreparedStatement statement = cnx.prepareStatement(sql);
        statement.executeUpdate(sql);
    }

    @Override
    public void AddUser(User u, String PasswordUse) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     

}
