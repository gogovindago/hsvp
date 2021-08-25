package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeSchemeByNodalRequest {

    @SerializedName("StudentId")
    @Expose
    private String studentId;
    @SerializedName("NodalOfficerId")
    @Expose
    private String nodalOfficerId;
    @SerializedName("TakeCare")
    @Expose
    private String takeCare;
    @SerializedName("AdoptionOfLibrary")
    @Expose
    private String adoptionOfLibrary;
    @SerializedName("TakeCareRural")
    @Expose
    private String takeCareRural;
    @SerializedName("Action")
    @Expose
    private String action;
    @SerializedName("NodalOfficerRemarks")
    @Expose
    private String nodalOfficerRemarks;

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

    public String getTakeCare() {
        return takeCare;
    }

    public void setTakeCare(String takeCare) {
        this.takeCare = takeCare;
    }

    public String getAdoptionOfLibrary() {
        return adoptionOfLibrary;
    }

    public void setAdoptionOfLibrary(String adoptionOfLibrary) {
        this.adoptionOfLibrary = adoptionOfLibrary;
    }

    public String getTakeCareRural() {
        return takeCareRural;
    }

    public void setTakeCareRural(String takeCareRural) {
        this.takeCareRural = takeCareRural;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNodalOfficerRemarks() {
        return nodalOfficerRemarks;
    }

    public void setNodalOfficerRemarks(String nodalOfficerRemarks) {
        this.nodalOfficerRemarks = nodalOfficerRemarks;
    }


}
