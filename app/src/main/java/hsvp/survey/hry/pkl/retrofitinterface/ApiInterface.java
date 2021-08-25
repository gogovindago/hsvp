package hsvp.survey.hry.pkl.retrofitinterface;

import hsvp.survey.hry.pkl.models.AdminDataResponse;
import hsvp.survey.hry.pkl.models.AdoptionStep1Response;
import hsvp.survey.hry.pkl.models.AdoptionStep2Response;
import hsvp.survey.hry.pkl.models.ChangeSchemeByNodalRequest;
import hsvp.survey.hry.pkl.models.ChangeSchemeByNodalResponse;
import hsvp.survey.hry.pkl.models.ChangeSchemeForStudentResponse;
import hsvp.survey.hry.pkl.models.ChangeSchemeResquestByStudent;
import hsvp.survey.hry.pkl.models.ChangeSchemeResquestByStudentResponse;
import hsvp.survey.hry.pkl.models.ChangeSchemeResquestListResponse;
import hsvp.survey.hry.pkl.models.CollegeListResponse;
import hsvp.survey.hry.pkl.models.CourseByCollegeIDResponse;
import hsvp.survey.hry.pkl.models.ForgotPasswordRequest;
import hsvp.survey.hry.pkl.models.ForgotPasswordResponse;
import hsvp.survey.hry.pkl.models.GetDistrictResponse;
import hsvp.survey.hry.pkl.models.HomePageResponse;
import hsvp.survey.hry.pkl.models.LoginRequest;
import hsvp.survey.hry.pkl.models.LoginResponse;
import hsvp.survey.hry.pkl.models.NodalofficerOfCollegeResponse;
import hsvp.survey.hry.pkl.models.NssGalleryResponse;
import hsvp.survey.hry.pkl.models.OldagetakecarStep1Response;
import hsvp.survey.hry.pkl.models.OldagetakecarStep2Response;
import hsvp.survey.hry.pkl.models.ProfileDataResponse;
import hsvp.survey.hry.pkl.models.RegistrationRespone;
import hsvp.survey.hry.pkl.models.ResetPasswordRequest;
import hsvp.survey.hry.pkl.models.ResetPasswordResponse;
import hsvp.survey.hry.pkl.models.RuraltakecarStep2Response;
import hsvp.survey.hry.pkl.models.SchemeOfCollegeResponse;
import hsvp.survey.hry.pkl.models.SectionResponse;
import hsvp.survey.hry.pkl.models.SignupRequest;
import hsvp.survey.hry.pkl.models.SignupResponse;
import hsvp.survey.hry.pkl.models.StudentDailActivityForNodalofficerResponse;
import hsvp.survey.hry.pkl.models.StudentListUnderNodalOfficerResponse;
import hsvp.survey.hry.pkl.models.SubjectCombinationResponse;
import hsvp.survey.hry.pkl.models.TakecareRuralStep1Response;
import hsvp.survey.hry.pkl.models.VerificationStudentListResponse;
import hsvp.survey.hry.pkl.models.VerifiedStudentDataResponse;
import hsvp.survey.hry.pkl.models.VerifiedStudentResponse;
import hsvp.survey.hry.pkl.models.VerifyOtpRequest;
import hsvp.survey.hry.pkl.models.VerifyOtpResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface ApiInterface {


    @POST("ChangePassword")
    Call<ResetPasswordResponse> ResetforgotPassword(@Header("Authorization") String token, @Body ResetPasswordRequest request);

    @POST("ProcessSchemeChangeRequest")
    Call<ChangeSchemeByNodalResponse> ProcessSchemeChangeRequestApi(@Header("Authorization") String token, @Body ChangeSchemeByNodalRequest request);

    @POST("RequestToChangeScheme")
    Call<ChangeSchemeResquestByStudentResponse> RequestToChangeSchemeByStudentApi(@Header("Authorization") String token, @Body ChangeSchemeResquestByStudent request);

    @POST("UserRegister")
    Call<SignupResponse> signupUser(@Body SignupRequest request);

    //http://112.196.99.107:81/api/commonapi/GetProfile/9499486861
    @GET("GetProfile/{MobileNo}")
    Call<ProfileDataResponse> getProfileDataAPi(@Path("MobileNo") String s);

    //GetStudentSchemeChangeRequest/4430
    @GET("GetStudentSchemeChangeRequest/{COllegeID}")
    Call<ChangeSchemeResquestListResponse> StudentSchemeChangeRequestListDataAPi(@Header("Authorization") String token, @Path("COllegeID") String s);

    @GET("GetCollege/{DistrictID}")
    Call<CollegeListResponse> CollegeDataAPi(@Path("DistrictID") String s);

    @GET("GetSchemeByCollegeId/{CollegeID}")
    Call<SchemeOfCollegeResponse> CollegeSchemeDataAPi(@Path("CollegeID") String s);

    @GET("GetStudentForVerification/{SchemeID}/{CollegeID}")
    Call<VerificationStudentListResponse> verificationstudentlistDataAPi(@Header("Authorization") String token, @Path("SchemeID") String SchemeID, @Path("CollegeID") String CollegeID);

    @GET("VerifiedStudentList/{SchemeID}/{CollegeID}")
    Call<VerifiedStudentDataResponse> verifiedtudentlistDataAPi(@Header("Authorization") String token, @Path("SchemeID") String SchemeID, @Path("CollegeID") String CollegeID);

    @FormUrlEncoded
    @POST("VerifyStudent")
    Call<VerifiedStudentResponse> studentverificationApi(@Header("Authorization") String token,
                                                         @Field("StudentId") String StudentId,
                                                         @Field("NodalOfficerId") String NodalOfficerId,
                                                         @Field("remarks") String remarks,
                                                         @Field("actionStatus") String actionStatus


    );


    @GET("OldAge_GetStudentActivitiesList/{StudentID}/{SchemeID}")
    Call<StudentDailActivityForNodalofficerResponse> StudentDailyActivityForNodalDataAPi(@Header("Authorization") String token, @Path("StudentID") String UserID, @Path("SchemeID") String SchemeID);

    @GET("Dashboard/")
    Call<AdminDataResponse> adminDataAPi(@Header("Authorization") String token);


    @GET("OldAge_GetNodalOfficerStudent/{UserID}/{SchemeID}")
    Call<StudentListUnderNodalOfficerResponse> NodalOfficerStudentDataAPi(@Header("Authorization") String token, @Path("UserID") String UserID, @Path("SchemeID") String SchemeID);


    @FormUrlEncoded
    @POST("AdoptionLibraryStep1")
    Call<AdoptionStep1Response> AdoptionStep1Api(@Header("Authorization") String token,
                                                 @Field("StudentId") String StudentId,
                                                 @Field("NodalOfficerId") String NodalOfficerId,

                                                 @Field("LibraryInchargeName") String LibraryInchargeName,
                                                 @Field("LibraryAlreadyExist") String LibraryAlreadyExist,
                                                 @Field("LibraryFacility") String LibraryFacility,

                                                 @Field("OldAge_OrphanName") String OldAge_OrphanName,
                                                 @Field("OldAGe_OrphanAddress") String OldAGe_OrphanAddress,
                                                 @Field("OldAge_OrphanInchargeName") String OldAge_OrphanInchargeName

    );


    @Multipart
    @POST("AdoptionLibraryStep2")
    Call<AdoptionStep2Response> AdoptionStep2Api(@Header("Authorization") String token,
                                                 @Part("StudentId") RequestBody StudentId,
                                                 @Part("NodalOfficerId") RequestBody NodalOfficerId,

                                                 @Part("ProblemInLibrary") RequestBody ProblemFacedByPerson,
                                                 @Part("DateOfVisit") RequestBody DateOfVisit,
                                                 @Part("ContactByPhone") RequestBody ContactByPhone,
                                                 @Part("OtherObservation") RequestBody OtherObservation,
                                                 @Part("AwarenessProgrammesOrg") RequestBody AwarenessProgrammesOrg,
                                                 @Part("TypeOfVisit") RequestBody TypeOfVisit,

                                                 @Part("Lat") RequestBody Lat,
                                                 @Part("Long") RequestBody Long,
                                                 @Part("Accuracy") RequestBody Accuracy,
                                                 @Part("Time") RequestBody uplodingTime,

                                                 @Part MultipartBody.Part Document
    );


    @FormUrlEncoded
    @POST("TakeCareRuralStep1")
    Call<TakecareRuralStep1Response> TakecareRuralStep1Api(@Header("Authorization") String token,
                                                           @Field("StudentId") String StudentId,
                                                           @Field("NodalOfficerId") String NodalOfficerId,
                                                           @Field("VillageName") String VillageName,
                                                           @Field("Block") String Block,
                                                           @Field("DistrictId") String DistrictId,
                                                           @Field("VillagePopulation") String VillagePopulation,
                                                           @Field("VillageMainProblem") String VillageMainProblem,
                                                           @Field("Above60YearsPersons") String Above60YearsPersons,

                                                           @Field("OldAge_OrphanName") String OldAge_OrphanName,
                                                           @Field("OldAGe_OrphanAddress") String OldAGe_OrphanAddress,
                                                           @Field("OldAge_OrphanInchargeName") String OldAge_OrphanInchargeName,
                                                           @Field("AdoptedPerson") String AdoptedPerson,
                                                           @Field("Mobile") String Mobile,
                                                           @Field("Age") String Age,
                                                           @Field("Gender") String Gender,
                                                           @Field("HealthStatus") String HealthStatus);


    @Multipart
    @POST("RuralTakecareStep2")
    Call<RuraltakecarStep2Response> RuralTakecareStep2Api(@Header("Authorization") String token,
                                                          @Part("StudentId") RequestBody StudentId,
                                                          @Part("NodalOfficerId") RequestBody NodalOfficerId,

                                                          @Part("ProblemFacedByPerson") RequestBody ProblemFacedByPerson,
                                                          @Part("SolutionProvidedByStudent") RequestBody SolutionProvidedByStudent,
                                                          @Part("RquirmentOfPerson") RequestBody RquirmentOfPerson,
                                                          @Part("DateOfVisit") RequestBody DateOfVisit,
                                                          @Part("ContactByPhone") RequestBody ContactByPhone,
                                                          @Part("OtherActivity") RequestBody OtherObservation,
                                                          @Part("AwarenessProgrammesOrg") RequestBody AwarenessProgrammesOrg,
                                                          @Part("TypeOfVisit") RequestBody TypeOfVisit,

                                                          @Part("Lat") RequestBody Lat,
                                                          @Part("Long") RequestBody Long,
                                                          @Part("Accuracy") RequestBody Accuracy,
                                                          @Part("Time") RequestBody uplodingTime,

                                                          @Part MultipartBody.Part Document
    );


    @FormUrlEncoded
    @POST("TakecareStep1")
    Call<OldagetakecarStep1Response> TakecareStep1Api(@Header("Authorization") String token,
                                                      @Field("StudentId") String StudentId,
                                                      @Field("NodalOfficerId") String NodalOfficerId,
                                                      @Field("OldAge_OrphanName") String OldAge_OrphanName,
                                                      @Field("OldAGe_OrphanAddress") String OldAGe_OrphanAddress,
                                                      @Field("OldAge_OrphanInchargeName") String OldAge_OrphanInchargeName,
                                                      @Field("AdoptedPerson") String AdoptedPerson,
                                                      @Field("Mobile") String Mobile,
                                                      @Field("Age") String Age,
                                                      @Field("Gender") String Gender,
                                                      @Field("HealthStatus") String HealthStatus);


    @Multipart
    @POST("TakecareStep2")
    Call<OldagetakecarStep2Response> TakecareStep2Api(@Header("Authorization") String token,
                                                      @Part("StudentId") RequestBody StudentId,
                                                      @Part("NodalOfficerId") RequestBody NodalOfficerId,
                                                      @Part("ProblemFacedByPerson") RequestBody ProblemFacedByPerson,
                                                      @Part("SolutionProvidedByStudent") RequestBody SolutionProvidedByStudent,
                                                      @Part("RquirmentOfPerson") RequestBody RquirmentOfPerson,
                                                      @Part("DateOfVisit") RequestBody DateOfVisit,
                                                      @Part("ContactByPhone") RequestBody ContactByPhone,
                                                      @Part("OtherObservation") RequestBody OtherObservation,
                                                      @Part("TypeOfVisit") RequestBody TypeOfVisit,
                                                      @Part("Lat") RequestBody Lat,
                                                      @Part("Long") RequestBody Long,
                                                      @Part("Accuracy") RequestBody Accuracy,
                                                      @Part("Time") RequestBody uplodingTime,
                                                      @Part MultipartBody.Part Document);



    /*

    @POST("commonapi/UpdatePassword")
    Call<ForgotPasswordResponse> ForgotPasswordApi(@Field("username") String Registration_Id,
                                                   @Field("DOB") String Description);
*/

/*
    @GET("commonapi/GetAssignmentByUserID/{UserID}")
    Call<AssignmentListResponse> getAllAssignmentListAPi(@Header("Authorization") String token, @Path("UserID") String UserID);
*/

    @GET("GetNodalOfficerForScheme/{StudentID}/{collegeID}/{schemeID}")
    Call<NodalofficerOfCollegeResponse> GetNodalOfficerApi(@Header("Authorization") String token, @Path("StudentID") String StudentID, @Path("collegeID") String collegeID, @Path("schemeID") String schemeID);


    @GET("GetCourse/{collegeID}")
    Call<CourseByCollegeIDResponse> CourseDataAPi(@Path("collegeID") String s);

    @GET("GetSection/{CollegeID}/{CourseID}")
    Call<SectionResponse> SectionDataAPi(@Path("CollegeID") String CollegeID, @Path("CourseID") String s);

    //"http://112.196.99.107:81/api/commonapi/DivyangjanGetSubjectCombinationByCollegeIDANDCourseIDAndSectionId/2/3/4"
    @GET("GetSubjectCombination/{CollegeID}/{CourseID}/{SectionId}")
    Call<SubjectCombinationResponse> SubjectCombinationAPi(@Path("CollegeID") String CollegeID, @Path("CourseID") String CourseID, @Path("SectionId") String SectionId);


    @GET("GetSliders/{collegeid}")
    Call<HomePageResponse> getHomePageDataAPi(@Header("Authorization") String token, @Path("collegeid") String s);

    @GET("OldAge_Gallery")
    Call<NssGalleryResponse> nssGalleryDataAPi(@Header("Authorization") String token);
//  @GET("GetSliders")
//    Call<HomePageResponse> getHomePageDataAPi(@Header("Authorization") String token);


    //@GET("DivyangjanGetDistrict")
    @GET("Getdistrict")
    Call<GetDistrictResponse> DivyangjanGetDistrictDataAPi();

    //  OldAgeTakingCare==user_address,OldAgeAdoptionOfLibrary==userdisability_ID,OldAgePeopleInRuralArea=DifferentlyAskedCategory_Type

    @Multipart
    @POST("Registration")
    Call<RegistrationRespone> SignupUsermultipart(
            @Part("districtId") RequestBody District_Id,
            @Part("CollegeIds") RequestBody college,
            @Part("CourseId") RequestBody Course,
            @Part("SectionId") RequestBody Section,
            @Part("SubjectCombinationId") RequestBody Combination,
            @Part("RollNo") RequestBody RollNo,
            @Part("FirstName") RequestBody StudentName,
            @Part("LastName") RequestBody FatherName,
            @Part("DOB") RequestBody DOB,
            @Part("Gender") RequestBody Gender,
            @Part("Mobile") RequestBody MobileNo,
            @Part("ParentMobileNo") RequestBody ParentMobileNo,
            @Part("PercentageOfDisability") RequestBody PercentageOfDisability,
            @Part("DifferentlyAbledCategory") RequestBody DifferentlyAbledCategory,
            @Part("OldAgeAdoptionOfLibrary") RequestBody Diabiltyid,
            @Part("OldAgePeopleInRuralArea") RequestBody DifferentlyAskedCategory,
            @Part("CasteCategory") RequestBody CasteCategory,
            @Part("OldAgeTakingCare") RequestBody Address,
            @Part("Password") RequestBody confirmpassword,
            @Part("StateId") RequestBody StateId,
            @Part("Email") RequestBody EmailID,
            @Part("FCMToken") RequestBody FcmToken_Id,
            @Part("AccountType") RequestBody userType,
            @Part("Semester") RequestBody Sem,
            @Part("OtherCollege") RequestBody OtherCollege,
            @Part("OtherCourse") RequestBody OtherCourse,
            @Part("OtherSection") RequestBody OtherSection,
            @Part("OtherSubjectCombination") RequestBody OtherSubjectCombination,
            @Part MultipartBody.Part Document,
            @Part MultipartBody.Part Certificate);


    //http://112.196.99.107:81/DigitalLibrary/api/commonapi/UserLogin/7018401817/1234
    // http://112.196.99.107:81/api/commonapi/DivyangjanLogin
    @POST("Login")
    Call<LoginResponse> LoginUser(@Body LoginRequest request);


    // http://112.196.99.107:81/api/commonapi/ForgetPassword
    @POST("ForgetPassword")
    Call<ForgotPasswordResponse> ForgetPasswordUser(@Body ForgotPasswordRequest request);


    //http://112.196.99.107:81/api/commonapi/VerifyOTP
    @POST("VerifyOTP")
    Call<VerifyOtpResponse> verifyOTP(@Body VerifyOtpRequest request);


    @GET("GetSchemesFor_CollegeStudent/{StudentID}/{CollegeID}")
    Call<ChangeSchemeForStudentResponse> changeschemeforstudentDataAPi(@Header("Authorization") String token, @Path("StudentID") String StudentID, @Path("CollegeID") String CollegeID);


}
