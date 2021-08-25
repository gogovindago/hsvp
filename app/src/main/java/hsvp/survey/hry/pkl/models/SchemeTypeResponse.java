package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SchemeTypeResponse {


    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private List<LeaveTypeData> data = new ArrayList<LeaveTypeData>();

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

    public List<LeaveTypeData> getData() {
        return data;
    }

    public void setData(List<LeaveTypeData> data) {
        this.data = data;
    }

    public class LeaveTypeData {


        @SerializedName("leave_type_id")
        @Expose
        private Integer leaveTypeId;
        @SerializedName("leave_type")
        @Expose
        private String leaveType;

        public Integer getLeaveTypeId() {
            return leaveTypeId;
        }

        public void setLeaveTypeId(Integer leaveTypeId) {
            this.leaveTypeId = leaveTypeId;
        }

        public String getLeaveType() {
            return leaveType;
        }

        public void setLeaveType(String leaveType) {
            this.leaveType = leaveType;
        }

        
    }



}
