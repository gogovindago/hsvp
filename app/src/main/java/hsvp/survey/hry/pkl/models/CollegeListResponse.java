package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CollegeListResponse {

    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

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



    public static class Datum {

        @SerializedName("college_Id")
        @Expose
        private Integer collegeId;
        @SerializedName("college")
        @Expose
        private String college;

        public Integer getCollegeId() {
            return collegeId;
        }

        public void setCollegeId(Integer collegeId) {
            this.collegeId = collegeId;
        }

        public String getCollege() {
            return college;
        }

        public void setCollege(String college) {
            this.college = college;
        }

    }

}