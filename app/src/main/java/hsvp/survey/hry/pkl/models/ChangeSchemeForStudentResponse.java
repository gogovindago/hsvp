package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChangeSchemeForStudentResponse {
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

        @SerializedName("collegeTakeCare")
        @Expose
        private boolean collegeTakeCare;
        @SerializedName("collegeAdoptionofLibrary")
        @Expose
        private boolean collegeAdoptionofLibrary;
        @SerializedName("collegeRural")
        @Expose
        private boolean collegeRural;
        @SerializedName("studentTakecare")
        @Expose
        private boolean studentTakecare;
        @SerializedName("studentAdoptionOfLibrary")
        @Expose
        private boolean studentAdoptionOfLibrary;
        @SerializedName("studentRural")
        @Expose
        private boolean studentRural;

        public boolean isCollegeTakeCare() {
            return collegeTakeCare;
        }

        public void setCollegeTakeCare(boolean collegeTakeCare) {
            this.collegeTakeCare = collegeTakeCare;
        }

        public boolean isCollegeAdoptionofLibrary() {
            return collegeAdoptionofLibrary;
        }

        public void setCollegeAdoptionofLibrary(boolean collegeAdoptionofLibrary) {
            this.collegeAdoptionofLibrary = collegeAdoptionofLibrary;
        }

        public boolean isCollegeRural() {
            return collegeRural;
        }

        public void setCollegeRural(boolean collegeRural) {
            this.collegeRural = collegeRural;
        }

        public boolean isStudentTakecare() {
            return studentTakecare;
        }

        public void setStudentTakecare(boolean studentTakecare) {
            this.studentTakecare = studentTakecare;
        }

        public boolean isStudentAdoptionOfLibrary() {
            return studentAdoptionOfLibrary;
        }

        public void setStudentAdoptionOfLibrary(boolean studentAdoptionOfLibrary) {
            this.studentAdoptionOfLibrary = studentAdoptionOfLibrary;
        }

        public boolean isStudentRural() {
            return studentRural;
        }

        public void setStudentRural(boolean studentRural) {
            this.studentRural = studentRural;
        }
    }

}
