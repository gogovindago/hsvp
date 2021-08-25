package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SchemeOfCollegeResponse {
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
        private int id;
        @SerializedName("collegeId")
        @Expose
        private int collegeId;
        @SerializedName("takeCare")
        @Expose
        private boolean takeCare;
        @SerializedName("adoptionOfLibrary")
        @Expose
        private boolean adoptionOfLibrary;
        @SerializedName("takeCareInRural")
        @Expose
        private boolean takeCareInRural;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCollegeId() {
            return collegeId;
        }

        public void setCollegeId(int collegeId) {
            this.collegeId = collegeId;
        }

        public boolean isTakeCare() {
            return takeCare;
        }

        public void setTakeCare(boolean takeCare) {
            this.takeCare = takeCare;
        }

        public boolean isAdoptionOfLibrary() {
            return adoptionOfLibrary;
        }

        public void setAdoptionOfLibrary(boolean adoptionOfLibrary) {
            this.adoptionOfLibrary = adoptionOfLibrary;
        }

        public boolean isTakeCareInRural() {
            return takeCareInRural;
        }

        public void setTakeCareInRural(boolean takeCareInRural) {
            this.takeCareInRural = takeCareInRural;
        }

    }

}