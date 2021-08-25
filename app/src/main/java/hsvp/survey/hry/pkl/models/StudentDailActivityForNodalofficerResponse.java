package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StudentDailActivityForNodalofficerResponse {
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

        @SerializedName("studentName")
        @Expose
        private String studentName;


        @SerializedName("studentId")
        @Expose
        private String studentId;
        @SerializedName("nodalOfficerId")
        @Expose
        private String nodalOfficerId;
        @SerializedName("problemFacedByPerson")
        @Expose
        private String problemFacedByPerson;
        @SerializedName("solutionProvidedByStudent")
        @Expose
        private String solutionProvidedByStudent;
        @SerializedName("rquirmentOfPerson")
        @Expose
        private String rquirmentOfPerson;
        @SerializedName("dateOfVisit")
        @Expose
        private String dateOfVisit;
        @SerializedName("contactByPhone")
        @Expose
        private String contactByPhone;
        @SerializedName("filePath")
        @Expose
        private String filePath;
        @SerializedName("fileName")
        @Expose
        private String fileName;
        @SerializedName("otherObservation")
        @Expose
        private String otherObservation;
        @SerializedName("typeOfVisit")
        @Expose
        private String typeOfVisit;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("long")
        @Expose
        private String _long;
        @SerializedName("accuracy")
        @Expose
        private String accuracy;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("createdOn")
        @Expose
        private String createdOn;


        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
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

        public String getNodalOfficerId() {
            return nodalOfficerId;
        }

        public void setNodalOfficerId(String nodalOfficerId) {
            this.nodalOfficerId = nodalOfficerId;
        }

        public String getProblemFacedByPerson() {
            return problemFacedByPerson;
        }

        public void setProblemFacedByPerson(String problemFacedByPerson) {
            this.problemFacedByPerson = problemFacedByPerson;
        }

        public String getSolutionProvidedByStudent() {
            return solutionProvidedByStudent;
        }

        public void setSolutionProvidedByStudent(String solutionProvidedByStudent) {
            this.solutionProvidedByStudent = solutionProvidedByStudent;
        }

        public String getRquirmentOfPerson() {
            return rquirmentOfPerson;
        }

        public void setRquirmentOfPerson(String rquirmentOfPerson) {
            this.rquirmentOfPerson = rquirmentOfPerson;
        }

        public String getDateOfVisit() {
            return dateOfVisit;
        }

        public void setDateOfVisit(String dateOfVisit) {
            this.dateOfVisit = dateOfVisit;
        }

        public String getContactByPhone() {
            return contactByPhone;
        }

        public void setContactByPhone(String contactByPhone) {
            this.contactByPhone = contactByPhone;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getOtherObservation() {
            return otherObservation;
        }

        public void setOtherObservation(String otherObservation) {
            this.otherObservation = otherObservation;
        }

        public String getTypeOfVisit() {
            return typeOfVisit;
        }

        public void setTypeOfVisit(String typeOfVisit) {
            this.typeOfVisit = typeOfVisit;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLong() {
            return _long;
        }

        public void setLong(String _long) {
            this._long = _long;
        }

        public String getAccuracy() {
            return accuracy;
        }

        public void setAccuracy(String accuracy) {
            this.accuracy = accuracy;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

    }
}
