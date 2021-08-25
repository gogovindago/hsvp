
package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HomePageResponse {

    @SerializedName("response")
    @Expose
    private int response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private Datum data;

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

    public Datum getData() {
        return data;
    }

    public void setData(Datum data) {
        this.data = data;
    }

public static class Datum {


    public static class ResponeData1 {

        @SerializedName("srNo")
        @Expose
        private int srNo;
        @SerializedName("imagePath")
        @Expose
        private String imagePath;
        @SerializedName("description")
        @Expose
        private String description;

        public int getSrNo() {
            return srNo;
        }

        public void setSrNo(int srNo) {
            this.srNo = srNo;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }


    public static class ResponeDatum {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("fileName")
        @Expose
        private String fileName;
        @SerializedName("filePath")
        @Expose
        private String filePath;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("unverified")
        @Expose
        private String unverified;
        @SerializedName("verified")
        @Expose
        private String verified;
        @SerializedName("schemeRequest")
        @Expose
        private String schemeRequest;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getSchemeRequest() {
            return schemeRequest;
        }

        public void setSchemeRequest(String schemeRequest) {
            this.schemeRequest = schemeRequest;
        }

    }


    @SerializedName("responeData")
    @Expose
    private List<ResponeDatum> responeData = new ArrayList<ResponeDatum>();
    @SerializedName("responeData1")
    @Expose
    private List<ResponeData1> responeData1 = new ArrayList<ResponeData1>();

    public List<ResponeDatum> getResponeData() {
        return responeData;
    }

    public void setResponeData(List<ResponeDatum> responeData) {
        this.responeData = responeData;
    }

    public List<ResponeData1> getResponeData1() {
        return responeData1;
    }

    public void setResponeData1(List<ResponeData1> responeData1) {
        this.responeData1 = responeData1;
    }

}


  /*  @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("filePath")
    @Expose
    private String filePath;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("unverified")
    @Expose
    private String unverified;



    @SerializedName("verified")
    @Expose
    private String verified;



    @SerializedName("schemeRequest")
    @Expose
    private String schemeRequest;


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

    public String getSchemeRequest() {
        return schemeRequest;
    }

    public void setSchemeRequest(String schemeRequest) {
        this.schemeRequest = schemeRequest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
*/



//}




}
