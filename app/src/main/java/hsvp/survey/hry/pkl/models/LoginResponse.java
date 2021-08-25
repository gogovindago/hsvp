package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse {



    @SerializedName("response")
    @Expose
    private int response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private LoginResponse.Data data;

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

    public LoginResponse.Data getData() {
        return data;
    }

    public void setData(LoginResponse.Data data) {
        this.data = data;
    }
    public class Data {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("accountType")
        @Expose
        private String accountType;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("collegeId")
        @Expose
        private String collegeId;

        @SerializedName("isVerified")
        @Expose
        private String isVerified;
        @SerializedName("filePath")
        @Expose
        private String filePath;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("expiration")
        @Expose
        private String expiration;
        @SerializedName("oldAgeTakingCare")
        @Expose
        private String oldAgeTakingCare;
        @SerializedName("oldAgeAdoptionOfLibrary")
        @Expose
        private String oldAgeAdoptionOfLibrary;
        @SerializedName("oldAgePeopleInRuralArea")
        @Expose
        private String oldAgePeopleInRuralArea;
        @SerializedName("takeCareStep1")
        @Expose
        private String takeCareStep1;
        @SerializedName("librariesAdoptionStep1")
        @Expose
        private String librariesAdoptionStep1;
        @SerializedName("ruralTakeCareStep1")
        @Expose
        private String ruralTakeCareStep1;
        @SerializedName("nodalOfficers")
        @Expose
        private List<RegistrationRespone.NodalOfficer> nodalOfficers = new ArrayList<RegistrationRespone.NodalOfficer>();
        @SerializedName("collegeSchemes")
        @Expose
        private List<RegistrationRespone.CollegeScheme> collegeSchemes = new ArrayList<RegistrationRespone.CollegeScheme>();
        @SerializedName("nodalOfficersSchemes")
        @Expose
        private List<RegistrationRespone.NodalOfficersScheme> nodalOfficersSchemes = new ArrayList<RegistrationRespone.NodalOfficersScheme>();


        public String getIsVerified() {
            return isVerified;
        }

        public void setIsVerified(String isVerified) {
            this.isVerified = isVerified;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCollegeId() {
            return collegeId;
        }

        public void setCollegeId(String collegeId) {
            this.collegeId = collegeId;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExpiration() {
            return expiration;
        }

        public void setExpiration(String expiration) {
            this.expiration = expiration;
        }

        public String getOldAgeTakingCare() {
            return oldAgeTakingCare;
        }

        public void setOldAgeTakingCare(String oldAgeTakingCare) {
            this.oldAgeTakingCare = oldAgeTakingCare;
        }

        public String getOldAgeAdoptionOfLibrary() {
            return oldAgeAdoptionOfLibrary;
        }

        public void setOldAgeAdoptionOfLibrary(String oldAgeAdoptionOfLibrary) {
            this.oldAgeAdoptionOfLibrary = oldAgeAdoptionOfLibrary;
        }

        public String getOldAgePeopleInRuralArea() {
            return oldAgePeopleInRuralArea;
        }

        public void setOldAgePeopleInRuralArea(String oldAgePeopleInRuralArea) {
            this.oldAgePeopleInRuralArea = oldAgePeopleInRuralArea;
        }

        public String getTakeCareStep1() {
            return takeCareStep1;
        }

        public void setTakeCareStep1(String takeCareStep1) {
            this.takeCareStep1 = takeCareStep1;
        }

        public String getLibrariesAdoptionStep1() {
            return librariesAdoptionStep1;
        }

        public void setLibrariesAdoptionStep1(String librariesAdoptionStep1) {
            this.librariesAdoptionStep1 = librariesAdoptionStep1;
        }

        public String getRuralTakeCareStep1() {
            return ruralTakeCareStep1;
        }

        public void setRuralTakeCareStep1(String ruralTakeCareStep1) {
            this.ruralTakeCareStep1 = ruralTakeCareStep1;
        }

        public List<RegistrationRespone.NodalOfficer> getNodalOfficers() {
            return nodalOfficers;
        }

        public void setNodalOfficers(List<RegistrationRespone.NodalOfficer> nodalOfficers) {
            this.nodalOfficers = nodalOfficers;
        }

        public List<RegistrationRespone.CollegeScheme> getCollegeSchemes() {
            return collegeSchemes;
        }

        public void setCollegeSchemes(List<RegistrationRespone.CollegeScheme> collegeSchemes) {
            this.collegeSchemes = collegeSchemes;
        }

        public List<RegistrationRespone.NodalOfficersScheme> getNodalOfficersSchemes() {
            return nodalOfficersSchemes;
        }

        public void setNodalOfficersSchemes(List<RegistrationRespone.NodalOfficersScheme> nodalOfficersSchemes) {
            this.nodalOfficersSchemes = nodalOfficersSchemes;
        }

    }

    public class CollegeScheme {

        @SerializedName("collegeId")
        @Expose
        private String collegeId;
        @SerializedName("takeCare")
        @Expose
        private String takeCare;
        @SerializedName("adoptionOfLibrary")
        @Expose
        private String adoptionOfLibrary;
        @SerializedName("takeCareInRural")
        @Expose
        private String takeCareInRural;

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

        public String getTakeCareInRural() {
            return takeCareInRural;
        }

        public void setTakeCareInRural(String takeCareInRural) {
            this.takeCareInRural = takeCareInRural;
        }

    }

    public class NodalOfficer {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private Object lastName;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("email")
        @Expose
        private String email;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public Object getLastName() {
            return lastName;
        }

        public void setLastName(Object lastName) {
            this.lastName = lastName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }
    public class NodalOfficersScheme {

        @SerializedName("nodalOfficerId")
        @Expose
        private String nodalOfficerId;
        @SerializedName("collegeId")
        @Expose
        private String collegeId;
        @SerializedName("takeCare")
        @Expose
        private String takeCare;
        @SerializedName("adoptionOfLibrary")
        @Expose
        private String adoptionOfLibrary;
        @SerializedName("takeCareInRural")
        @Expose
        private String takeCareInRural;

        public String getNodalOfficerId() {
            return nodalOfficerId;
        }

        public void setNodalOfficerId(String nodalOfficerId) {
            this.nodalOfficerId = nodalOfficerId;
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

        public String getTakeCareInRural() {
            return takeCareInRural;
        }

        public void setTakeCareInRural(String takeCareInRural) {
            this.takeCareInRural = takeCareInRural;
        }

    }


}




  /*  @SerializedName("response")
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

        @SerializedName("accountType")
        @Expose
        private String accountType;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("fileName")
        @Expose
        private String fileName;
        @SerializedName("filePath")
        @Expose
        private String filePath;
        @SerializedName("token")
        @Expose
        private String token;

        @SerializedName("expiration")
        @Expose
        private String expiration;


        @SerializedName("takeCareStep1")
        @Expose
        private String takeCareStep1;
        @SerializedName("librariesAdoptionStep1")
        @Expose
        private String librariesAdoptionStep1;

        @SerializedName("ruralTakeCareStep1")
        @Expose
        private String ruralTakeCareStep1;



        @SerializedName("oldAgeTakingCare")
        @Expose
        private String oldAgeTakingCare;
        @SerializedName("oldAgeAdoptionOfLibrary")
        @Expose
        private String oldAgeAdoptionOfLibrary;

        @SerializedName("oldAgePeopleInRuralArea")
        @Expose
        private String oldAgePeopleInRuralArea;




        *//*"oldAgeTakingCare": "True",
        "oldAgeAdoptionOfLibrary": "",
        "oldAgePeopleInRuralArea": "",*//*


        public String getOldAgeTakingCare() {
            return oldAgeTakingCare;
        }

        public void setOldAgeTakingCare(String oldAgeTakingCare) {
            this.oldAgeTakingCare = oldAgeTakingCare;
        }

        public String getOldAgeAdoptionOfLibrary() {
            return oldAgeAdoptionOfLibrary;
        }

        public void setOldAgeAdoptionOfLibrary(String oldAgeAdoptionOfLibrary) {
            this.oldAgeAdoptionOfLibrary = oldAgeAdoptionOfLibrary;
        }

        public String getOldAgePeopleInRuralArea() {
            return oldAgePeopleInRuralArea;
        }

        public void setOldAgePeopleInRuralArea(String oldAgePeopleInRuralArea) {
            this.oldAgePeopleInRuralArea = oldAgePeopleInRuralArea;
        }











        public String getTakeCareStep1() {
            return takeCareStep1;
        }

        public void setTakeCareStep1(String takeCareStep1) {
            this.takeCareStep1 = takeCareStep1;
        }

        public String getLibrariesAdoptionStep1() {
            return librariesAdoptionStep1;
        }

        public void setLibrariesAdoptionStep1(String librariesAdoptionStep1) {
            this.librariesAdoptionStep1 = librariesAdoptionStep1;
        }

        public String getRuralTakeCareStep1() {
            return ruralTakeCareStep1;
        }

        public void setRuralTakeCareStep1(String ruralTakeCareStep1) {
            this.ruralTakeCareStep1 = ruralTakeCareStep1;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExpiration() {
            return expiration;
        }

        public void setExpiration(String expiration) {
            this.expiration = expiration;
        }

    }
}*/