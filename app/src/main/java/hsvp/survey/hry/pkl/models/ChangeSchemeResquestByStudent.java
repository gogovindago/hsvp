package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeSchemeResquestByStudent {

    @SerializedName("StudentId")
    @Expose
    private String studentId;
    @SerializedName("CollegeId")
    @Expose
    private String collegeId;
    @SerializedName("TakeCare")
    @Expose
    private String takeCare;
    @SerializedName("AdoptionOfLibrary")
    @Expose
    private String adoptionOfLibrary;
    @SerializedName("TakeCareRural")
    @Expose
    private String takeCareRural;
    @SerializedName("Remarks")
    @Expose
    private String remarks;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
