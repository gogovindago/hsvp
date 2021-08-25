package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SubjectCombinationResponse {

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

    public static  class Datum {

        @SerializedName("subjectCombination_Id")
        @Expose
        private Integer subjectCombinationId;
        @SerializedName("subjectCombination")
        @Expose
        private String subjectCombination;

        public Integer getSubjectCombinationId() {
            return subjectCombinationId;
        }

        public void setSubjectCombinationId(Integer subjectCombinationId) {
            this.subjectCombinationId = subjectCombinationId;
        }

        public String getSubjectCombination() {
            return subjectCombination;
        }

        public void setSubjectCombination(String subjectCombination) {
            this.subjectCombination = subjectCombination;
        }

    }

}
