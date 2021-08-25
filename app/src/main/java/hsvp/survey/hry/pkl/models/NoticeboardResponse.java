package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NoticeboardResponse {

    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private List<NoticeBoard> data = new ArrayList<NoticeBoard>();

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

    public List<NoticeBoard> getData() {
        return data;
    }

    public void setData(List<NoticeBoard> data) {
        this.data = data;
    }

    public static class NoticeBoard {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("banner")
        @Expose
        private String banner;
        @SerializedName("description")
        @Expose
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }
    }
}
