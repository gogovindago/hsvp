package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordRequest {




    /*//"PhoneNo":"7018401817",
//"Password":"1234"*/

    @SerializedName("Id")
    @Expose
    private String Id;


    @SerializedName("PhoneNo")
    @Expose
    private String PhoneNo;


    @SerializedName("Mobile")
    @Expose
    private String Mobile;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("Password")
    @Expose
    private String password;

    @SerializedName("FCMToken")
    @Expose
    private String fCMToken;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getfCMToken() {
        return fCMToken;
    }

    public void setfCMToken(String fCMToken) {
        this.fCMToken = fCMToken;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFCMToken() {
        return fCMToken;
    }

    public void setFCMToken(String fCMToken) {
        this.fCMToken = fCMToken;
    }

}
