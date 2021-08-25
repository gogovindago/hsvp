package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChangeSchemeResquestListResponse {
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
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

        /*:[{"studentName":"santosh verma","mobile":8888888888,
        "filePath":"http://oldage.highereduhry.com/UploadedDocument/download (1).jpg",
        "course_name":"Bachelor of Arts (B.A.)","takeCare":false,"adoptionOfLibrary":false,
        "takeCareRural":true,"remarks":"","requestDate":"Jan 22 2021  3:55PM"}]}*/

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("studentId")
        @Expose
        private String studentId;
        @SerializedName("collegeId")
        @Expose
        private String collegeId;

        /*collegeTakeCare":true,"collegeAdoptionLibrary":true,"collegeTakeCareRural":true,*/


        @SerializedName("collegeTakeCare")
        @Expose
        private Boolean collegeTakeCare;



        @SerializedName("collegeAdoptionLibrary")
        @Expose
        private Boolean collegeAdoptionLibrary;



        @SerializedName("collegeTakeCareRural")
        @Expose
        private Boolean collegeTakeCareRural;


        @SerializedName("previuosTakeCare")
        @Expose
        private Boolean previuosTakeCare;
        @SerializedName("previuosTakeCareAdoption")
        @Expose
        private Boolean previuosTakeCareAdoption;
        @SerializedName("previuosTakeCareRural")
        @Expose
        private Boolean previuosTakeCareRural;
        @SerializedName("newTakeCare")
        @Expose
        private Boolean newTakeCare;
        @SerializedName("newAdoptionLibrary")
        @Expose
        private Boolean newAdoptionLibrary;
        @SerializedName("newTakeCareRural")
        @Expose
        private Boolean newTakeCareRural;
        @SerializedName("remarks")
        @Expose
        private String remarks;

        @SerializedName("requestDate")
        @Expose
        private String requestDate;

        @SerializedName("studentName")
        @Expose
        private String studentName;

        @SerializedName("mobile")
        @Expose
        private String mobile;

        @SerializedName("filePath")
        @Expose
        private String filePath;

        @SerializedName("course_name")
        @Expose
        private String course_name;


        public Boolean getCollegeTakeCare() {
            return collegeTakeCare;
        }

        public void setCollegeTakeCare(Boolean collegeTakeCare) {
            this.collegeTakeCare = collegeTakeCare;
        }

        public Boolean getCollegeAdoptionLibrary() {
            return collegeAdoptionLibrary;
        }

        public void setCollegeAdoptionLibrary(Boolean collegeAdoptionLibrary) {
            this.collegeAdoptionLibrary = collegeAdoptionLibrary;
        }

        public Boolean getCollegeTakeCareRural() {
            return collegeTakeCareRural;
        }

        public void setCollegeTakeCareRural(Boolean collegeTakeCareRural) {
            this.collegeTakeCareRural = collegeTakeCareRural;
        }

        public Boolean getPreviuosTakeCare() {
            return previuosTakeCare;
        }

        public void setPreviuosTakeCare(Boolean previuosTakeCare) {
            this.previuosTakeCare = previuosTakeCare;
        }

        public Boolean getPreviuosTakeCareAdoption() {
            return previuosTakeCareAdoption;
        }

        public void setPreviuosTakeCareAdoption(Boolean previuosTakeCareAdoption) {
            this.previuosTakeCareAdoption = previuosTakeCareAdoption;
        }

        public Boolean getPreviuosTakeCareRural() {
            return previuosTakeCareRural;
        }

        public void setPreviuosTakeCareRural(Boolean previuosTakeCareRural) {
            this.previuosTakeCareRural = previuosTakeCareRural;
        }

        public Boolean getNewTakeCare() {
            return newTakeCare;
        }

        public void setNewTakeCare(Boolean newTakeCare) {
            this.newTakeCare = newTakeCare;
        }

        public Boolean getNewAdoptionLibrary() {
            return newAdoptionLibrary;
        }

        public void setNewAdoptionLibrary(Boolean newAdoptionLibrary) {
            this.newAdoptionLibrary = newAdoptionLibrary;
        }

        public Boolean getNewTakeCareRural() {
            return newTakeCareRural;
        }

        public void setNewTakeCareRural(Boolean newTakeCareRural) {
            this.newTakeCareRural = newTakeCareRural;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getCollegeId() {
            return collegeId;
        }

        public void setCollegeId(String collegeId) {
            this.collegeId = collegeId;
        }


        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getRequestDate() {
            return requestDate;
        }

        public void setRequestDate(String requestDate) {
            this.requestDate = requestDate;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }
    }

}
