package hsvp.survey.hry.pkl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NodalofficerOfCollegeResponse {

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

    public class ResponeDatum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("collegeId")
        @Expose
        private String collegeId;
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

        public String getCollegeId() {
            return collegeId;
        }

        public void setCollegeId(String collegeId) {
            this.collegeId = collegeId;
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

    public class ResponeData1 {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("studentId")
        @Expose
        private String studentId;
        @SerializedName("nodalOfficerId")
        @Expose
        private String nodalOfficerId;
        @SerializedName("oldAge_OrphanName")
        @Expose
        private String oldAgeOrphanName;
        @SerializedName("oldAGe_OrphanAddress")
        @Expose
        private String oldAGeOrphanAddress;
        @SerializedName("oldAge_OrphanInchargeName")
        @Expose
        private String oldAgeOrphanInchargeName;
        @SerializedName("adoptedPerson")
        @Expose
        private String adoptedPerson;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("healthStatus")
        @Expose
        private String healthStatus;


        @SerializedName("villageName")
        @Expose
        private String villageName;

        @SerializedName("block")
        @Expose
        private String block;


        @SerializedName("villagePopulation")
        @Expose
        private String villagePopulation;


        @SerializedName("villageMainProblem")
        @Expose
        private String villageMainProblem;


        @SerializedName("above60YearsPersons")
        @Expose
        private String above60YearsPersons;
  @SerializedName("districtId")
        @Expose
        private String districtId;







/*

        @SerializedName("oldAge_OrphanName")
        @Expose
        private String oldAge_OrphanName;
*/


     /*   @SerializedName("oldAGe_OrphanAddress")
        @Expose
        private String oldAGe_OrphanAddress;


        @SerializedName("oldAge_OrphanInchargeName")
        @Expose
        private String oldAge_OrphanInchargeName;

*/


        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        @SerializedName("libraryInchargeName")
        @Expose
        private String libraryInchargeName;


        @SerializedName("libraryAlreadyExist")
        @Expose
        private String libraryAlreadyExist;


        @SerializedName("libraryFacility")
        @Expose
        private String libraryFacility;






        /*{"id":"2","studentId":"219","nodalOfficerId":"98","oldAge_OrphanName":"ee","oldAGe_OrphanAddress":"dd","oldAge_OrphanInchargeName":"dff",
        "libraryInchargeName":"ddf","libraryAlreadyExist":"fff","libraryFacility":"dff"}*/

      /*  public String getOldAge_OrphanName() {
            return oldAge_OrphanName;
        }

        public void setOldAge_OrphanName(String oldAge_OrphanName) {
            this.oldAge_OrphanName = oldAge_OrphanName;
        }

        public String getOldAGe_OrphanAddress() {
            return oldAGe_OrphanAddress;
        }

        public void setOldAGe_OrphanAddress(String oldAGe_OrphanAddress) {
            this.oldAGe_OrphanAddress = oldAGe_OrphanAddress;
        }

        public String getOldAge_OrphanInchargeName() {
            return oldAge_OrphanInchargeName;
        }

        public void setOldAge_OrphanInchargeName(String oldAge_OrphanInchargeName) {
            this.oldAge_OrphanInchargeName = oldAge_OrphanInchargeName;
        }*/

        public String getLibraryInchargeName() {
            return libraryInchargeName;
        }

        public void setLibraryInchargeName(String libraryInchargeName) {
            this.libraryInchargeName = libraryInchargeName;
        }

        public String getLibraryAlreadyExist() {
            return libraryAlreadyExist;
        }

        public void setLibraryAlreadyExist(String libraryAlreadyExist) {
            this.libraryAlreadyExist = libraryAlreadyExist;
        }

        public String getLibraryFacility() {
            return libraryFacility;
        }

        public void setLibraryFacility(String libraryFacility) {
            this.libraryFacility = libraryFacility;
        }


        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public String getBlock() {
            return block;
        }

        public void setBlock(String block) {
            this.block = block;
        }

        public String getVillagePopulation() {
            return villagePopulation;
        }

        public void setVillagePopulation(String villagePopulation) {
            this.villagePopulation = villagePopulation;
        }

        public String getVillageMainProblem() {
            return villageMainProblem;
        }

        public void setVillageMainProblem(String villageMainProblem) {
            this.villageMainProblem = villageMainProblem;
        }

        public String getAbove60YearsPersons() {
            return above60YearsPersons;
        }

        public void setAbove60YearsPersons(String above60YearsPersons) {
            this.above60YearsPersons = above60YearsPersons;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getOldAgeOrphanName() {
            return oldAgeOrphanName;
        }

        public void setOldAgeOrphanName(String oldAgeOrphanName) {
            this.oldAgeOrphanName = oldAgeOrphanName;
        }

        public String getOldAGeOrphanAddress() {
            return oldAGeOrphanAddress;
        }

        public void setOldAGeOrphanAddress(String oldAGeOrphanAddress) {
            this.oldAGeOrphanAddress = oldAGeOrphanAddress;
        }

        public String getOldAgeOrphanInchargeName() {
            return oldAgeOrphanInchargeName;
        }

        public void setOldAgeOrphanInchargeName(String oldAgeOrphanInchargeName) {
            this.oldAgeOrphanInchargeName = oldAgeOrphanInchargeName;
        }

        public String getAdoptedPerson() {
            return adoptedPerson;
        }

        public void setAdoptedPerson(String adoptedPerson) {
            this.adoptedPerson = adoptedPerson;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHealthStatus() {
            return healthStatus;
        }

        public void setHealthStatus(String healthStatus) {
            this.healthStatus = healthStatus;
        }
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("collegeId")
        @Expose
        private String collegeId;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCollegeId() {
            return collegeId;
        }

        public void setCollegeId(String collegeId) {
            this.collegeId = collegeId;
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

    }


}

