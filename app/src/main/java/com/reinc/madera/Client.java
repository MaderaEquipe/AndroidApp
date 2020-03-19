package com.reinc.madera;

public class Client {
    private String userName;
    private String userSurname;
    private String userAdress;
    private String userCP;
    private String userTown;
    private String userEmail;
    private String userTel;
    private String userId;

    public Client(String userName, String userSurname, String userAdress, String userCP, String userTown, String userEmail, String userTel, String userId) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAdress = userAdress;
        this.userCP = userCP;
        this.userTown = userTown;
        this.userEmail = userEmail;
        this.userTel = userTel;
        this.userId = userId;
    }

    public String getClientId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(String userAdress) {
        this.userAdress = userAdress;
    }

    public String getUserCP() {
        return userCP;
    }

    public void setUserCP(String userCP) {
        this.userCP = userCP;
    }

    public String getUserTown() {
        return userTown;
    }

    public void setUserTown(String userTown) {
        this.userTown = userTown;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
}