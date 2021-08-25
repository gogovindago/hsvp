package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Govind Kumar on 1/8/2021.
 * * PhpExpert
 * *  govind224556@gmail.com
 **/
public class VerifiedStudentDataResponse {

    @SerializedName("response")
    @Expose
    private int response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public String getSysMessage() {
        return sysMessage;
    }

    public void setSysMessage(String sysMessage) {
        this.sysMessage = sysMessage;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("district")
        @Expose
        private String district;
        @SerializedName("college")
        @Expose
        private String college;
        @SerializedName("course_name")
        @Expose
        private String courseName;
        @SerializedName("section_name")
        @Expose
        private String sectionName;
        @SerializedName("semester")
        @Expose
        private String semester;
        @SerializedName("filePath")
        @Expose
        private String filePath;
        @SerializedName("createdOn")
        @Expose
        private String createdOn;
        @SerializedName("oldAgeTakingCare")
        @Expose
        private boolean oldAgeTakingCare;
        @SerializedName("isVerified")
        @Expose
        private String isVerified;
        @SerializedName("verifiedBy")
        @Expose
        private String verifiedBy;
        @SerializedName("verifiedDate")
        @Expose
        private String verifiedDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getCollege() {
            return college;
        }

        public void setCollege(String college) {
            this.college = college;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getSectionName() {
            return sectionName;
        }

        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

        public boolean isOldAgeTakingCare() {
            return oldAgeTakingCare;
        }

        public void setOldAgeTakingCare(boolean oldAgeTakingCare) {
            this.oldAgeTakingCare = oldAgeTakingCare;
        }

        public String getIsVerified() {
            return isVerified;
        }

        public void setIsVerified(String isVerified) {
            this.isVerified = isVerified;
        }

        public String getVerifiedBy() {
            return verifiedBy;
        }

        public void setVerifiedBy(String verifiedBy) {
            this.verifiedBy = verifiedBy;
        }

        public String getVerifiedDate() {
            return verifiedDate;
        }

        public void setVerifiedDate(String verifiedDate) {
            this.verifiedDate = verifiedDate;
        }

    }

}
