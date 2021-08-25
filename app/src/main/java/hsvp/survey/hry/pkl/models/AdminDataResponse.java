package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Govind Kumar on 1/5/2021.
 * * PhpExpert
 * *  govind224556@gmail.com
 **/
public class AdminDataResponse {
    @SerializedName("response")
    @Expose
    private int response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("responeData")
        @Expose
        private List<ResponeDatum> responeData = new ArrayList<ResponeDatum>();

        public List<ResponeDatum> getResponeData() {
            return responeData;
        }

        public void setResponeData(List<ResponeDatum> responeData) {
            this.responeData = responeData;
        }

    }

    public class ResponeDatum {

        @SerializedName("totalStudent")
        @Expose
        private String totalStudent;
        @SerializedName("takeCareStudnet")
        @Expose
        private String takeCareStudnet;
        @SerializedName("adoptionLibraryStudent")
        @Expose
        private String adoptionLibraryStudent;
        @SerializedName("takeCareRuralStudent")
        @Expose
        private String takeCareRuralStudent;
        @SerializedName("unverified")
        @Expose
        private String unverified;
        @SerializedName("verified")
        @Expose
        private String verified;
        @SerializedName("takeCareTotalActivity")
        @Expose
        private String takeCareTotalActivity;
        @SerializedName("adoptionLibraryTotalActivity")
        @Expose
        private String adoptionLibraryTotalActivity;
        @SerializedName("takeCareRuralTotalActivity")
        @Expose
        private String takeCareRuralTotalActivity;

        public String getTotalStudent() {
            return totalStudent;
        }

        public void setTotalStudent(String totalStudent) {
            this.totalStudent = totalStudent;
        }

        public String getTakeCareStudnet() {
            return takeCareStudnet;
        }

        public void setTakeCareStudnet(String takeCareStudnet) {
            this.takeCareStudnet = takeCareStudnet;
        }

        public String getAdoptionLibraryStudent() {
            return adoptionLibraryStudent;
        }

        public void setAdoptionLibraryStudent(String adoptionLibraryStudent) {
            this.adoptionLibraryStudent = adoptionLibraryStudent;
        }

        public String getTakeCareRuralStudent() {
            return takeCareRuralStudent;
        }

        public void setTakeCareRuralStudent(String takeCareRuralStudent) {
            this.takeCareRuralStudent = takeCareRuralStudent;
        }

        public String getUnverified() {
            return unverified;
        }

        public void setUnverified(String unverified) {
            this.unverified = unverified;
        }

        public String getVerified() {
            return verified;
        }

        public void setVerified(String verified) {
            this.verified = verified;
        }

        public String getTakeCareTotalActivity() {
            return takeCareTotalActivity;
        }

        public void setTakeCareTotalActivity(String takeCareTotalActivity) {
            this.takeCareTotalActivity = takeCareTotalActivity;
        }

        public String getAdoptionLibraryTotalActivity() {
            return adoptionLibraryTotalActivity;
        }

        public void setAdoptionLibraryTotalActivity(String adoptionLibraryTotalActivity) {
            this.adoptionLibraryTotalActivity = adoptionLibraryTotalActivity;
        }

        public String getTakeCareRuralTotalActivity() {
            return takeCareRuralTotalActivity;
        }

        public void setTakeCareRuralTotalActivity(String takeCareRuralTotalActivity) {
            this.takeCareRuralTotalActivity = takeCareRuralTotalActivity;
        }
    }
}
