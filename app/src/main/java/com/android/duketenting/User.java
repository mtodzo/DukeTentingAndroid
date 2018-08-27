package com.android.duketenting;

public class User {
    private String myUserName;
    private String myPassword;
    private String myUniqueID;

    /*
    * must have either public instance variables or public getter methods in order
    * for the object to be serializable
     */
    public User(String username, String password) {
        myUserName = username;
        myPassword = password;
    }

    public String getUserName(){
        return myUserName;
    }

    public String getPassword(){
        return myPassword;
    }

    public void setID(String id){
        myUniqueID = id;
    }

    public String getUniqueID(){
        return myUniqueID;
    }

}
