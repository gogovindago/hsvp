package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileDataResponse {

    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
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





        @SerializedName("oldAgeTakingCare")
        @Expose
        private boolean oldAgeTakingCare;
        @SerializedName("oldAgeAdoptionOfLibrary")
        @Expose
        private boolean oldAgeAdoptionOfLibrary;
        @SerializedName("oldAgePeopleInRuralArea")
        @Expose
        private boolean oldAgePeopleInRuralArea;
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
        private int gender;
        @SerializedName("filePath")
        @Expose
        private String filePath;

        public boolean isOldAgeTakingCare() {
            return oldAgeTakingCare;
        }

        public void setOldAgeTakingCare(boolean oldAgeTakingCare) {
            this.oldAgeTakingCare = oldAgeTakingCare;
        }

        public boolean getOldAgeAdoptionOfLibrary() {
            return oldAgeAdoptionOfLibrary;
        }

        public void setOldAgeAdoptionOfLibrary(boolean oldAgeAdoptionOfLibrary) {
            this.oldAgeAdoptionOfLibrary = oldAgeAdoptionOfLibrary;
        }

        public boolean getOldAgePeopleInRuralArea() {
            return oldAgePeopleInRuralArea;
        }

        public void setOldAgePeopleInRuralArea(boolean oldAgePeopleInRuralArea) {
            this.oldAgePeopleInRuralArea = oldAgePeopleInRuralArea;
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

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }

}
