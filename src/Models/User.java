/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;





public class User {

    
    //var

    private int IdUser;
    private String NameUser;
    private String LastNameUser;
    private String EmailUser;
    private String ProfilePicUser;
    private String PasswordUser;
    private String UserRole;
    private int UserStatus;
   
    
    //Constructor
    public User() {
    }

    public User(int IdUser, String NameUser, String LastNameUser, String EmailUser, String ProfilePicUser, String PasswordUser, String UserRole, int UserStatus) {
        this.IdUser = IdUser;
        this.NameUser = NameUser;
        this.LastNameUser = LastNameUser;
        this.EmailUser = EmailUser;
        this.ProfilePicUser = ProfilePicUser;
        this.PasswordUser = PasswordUser;
        this.UserRole = UserRole;
        this.UserStatus = UserStatus;
    }

    public User(String NameUser, String LastNameUser, String EmailUser, String ProfilePicUser, String PasswordUser, String UserRole, int UserStatus) {
        this.NameUser = NameUser;
        this.LastNameUser = LastNameUser;
        this.EmailUser = EmailUser;
        this.ProfilePicUser = ProfilePicUser;
        this.PasswordUser = PasswordUser;
        this.UserRole = UserRole;
        this.UserStatus = UserStatus;
    }

    public User(String NameUser, String EmailUser) {
        this.NameUser = NameUser;
        this.EmailUser = EmailUser;
    }
    
    //Getters & Setters
    public int getIdUser() {
        return IdUser;
    }

    public String getNameUser() {
        return NameUser;
    }

    public String getLastNameUser() { 
        return LastNameUser;
    }

    public String getEmailUser() {
        return EmailUser;
    }

    public String getProfilePicUser() {
        return ProfilePicUser;
    }

    public String getPasswordUser() {
        return PasswordUser;
    }

    public String getUserRole() {
        return UserRole;
    }

  
    public int getUserStatus() {
        return UserStatus;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public void setNameUser(String NameUser) {
        this.NameUser = NameUser;
    }

    public void setLastNameUser(String LastNameUser) {
        this.LastNameUser = LastNameUser;
    }

    public void setEmailUser(String EmailUser) {
        this.EmailUser = EmailUser;
    }

    public void setProfilePicUser(String ProfilePicUser) {
        this.ProfilePicUser = ProfilePicUser;
    }

    public void setPasswordUser(String PasswordUser) {
        this.PasswordUser = PasswordUser;
    }

    public void setUserRole(String UserRole) {
        this.UserRole = UserRole;
    }

    public void setUserStatus(int UserStatus) {
        this.UserStatus = UserStatus;
    }
    

    //Affichage
    @Override
    public String toString() {
        return "User{" + "IdUser=" + IdUser + ", NameUser=" + NameUser + ", LastNameUser=" + LastNameUser + ", EmailUser=" + EmailUser + ", ProfilePicUser=" + ProfilePicUser +",PasswordUser=" + PasswordUser +",UserRole=" +UserRole +",UserStatus=" + UserStatus + '}';
    }

   
}
   