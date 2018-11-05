package lifestyle.com.lifestyle.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lifestyle.com.lifestyle.helper.Utils;

public class User {
    private static final String MALE = "male";
    private static final String FEMALE = "female";
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("user_photo")
    @Expose
    private String avatar;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_verified_at")
    @Expose
    private String emailVerifiedAt;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("current_weight")
    @Expose
    private String currentWeight;
    @SerializedName("goal_weight")
    @Expose
    private String goalWeight;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("work_type")
    @Expose
    private String workType;
    @SerializedName("purpose")
    @Expose
    private String purpose;
    @SerializedName("sleeping_type")
    @Expose
    private String sleepingType;
    @SerializedName("sleeping_at")
    @Expose
    private String sleepingAt;
    @SerializedName("wakes_up_at")
    @Expose
    private String wakesUpAt;
    @SerializedName("notification_token")
    @Expose
    private String notificationToken;
    @SerializedName("is_approved")
    @Expose
    private boolean isApproved;
    @SerializedName("reset_password_code")
    @Expose
    private String resetPasswordCode;
    @SerializedName("password")
    @Expose
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Utils.checkString(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Utils.checkString(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVerifiedAt() {
        return Utils.checkString(emailVerifiedAt);
    }

    public void setEmailVerifiedAt(String emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getPhone() {
        return Utils.checkString(phone);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return Utils.checkString(country);
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return Utils.checkString(city);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeight() {
        return Utils.checkString(height);
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGender() {
        return Utils.checkString(gender);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWorkType() {
        return Utils.checkString(workType);
    }

    public int getWorkTypeKey(){
        try{
            return getWorkType().isEmpty() ? 0 : Integer.parseInt(getWorkType());
        }catch (Exception e){
            return 0;
        }
    }
    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getPurpose() {
        return Utils.checkString(purpose);
    }

    public int getPurposeKey(){
        try{
            return getPurpose().isEmpty() ? 0 : Integer.parseInt(getPurpose());
        }catch (Exception e){
            return 0;
        }
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSleepingType() {
        return Utils.checkString(sleepingType);
    }

    public void setSleepingType(String sleepingType) {
        this.sleepingType = sleepingType;
    }

    public String getSleepingAt() {
        return Utils.checkString(sleepingAt);
    }

    public void setSleepingAt(String sleepingAt) {
        this.sleepingAt = sleepingAt;
    }

    public String getWakesUpAt() {
        return Utils.checkString(wakesUpAt);
    }

    public void setWakesUpAt(String wakesUpAt) {
        this.wakesUpAt = wakesUpAt;
    }

    public String getNotificationToken() {
        return Utils.checkString(notificationToken);
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    public String getBirthday() {
        return Utils.checkString(birthday);
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public String getResetPasswordCode() {
        return Utils.checkString(resetPasswordCode);
    }

    public void setResetPasswordCode(String resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }


    public String getCurrentWeight() {
        if(currentWeight == null || currentWeight.isEmpty())
            currentWeight = "0";
        return currentWeight;
    }

    public void setCurrentWeight(String currentWeight) {
        this.currentWeight = currentWeight;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setMale(){
        gender = MALE;
    }
    public void setFemale(){
        gender = FEMALE;
    }
    public boolean isMale(){
       return getGender().equals(MALE);
    }
    public boolean isFemale(){
        return getGender().equals(FEMALE);
    }

    public String getAvatar() {
        return Utils.checkString(avatar);
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGoalWeight() {
        if(goalWeight == null || goalWeight.isEmpty())
            goalWeight = "0";
        return goalWeight;
    }

    public void setGoalWeight(String goalWeight) {
        this.goalWeight = goalWeight;
    }
}
