package com.lvg.school104.entities;

import java.time.LocalDate;

/**
 * Created by Victor Levchenko LVG Corp. on 24.11.2020.
 */
public class ActEntity {
    private String studentName;
    private LocalDate birthDate;
    private String studentAddress;
    private String headCommission;
    private String firstCommissionMember;
    private String secondCommissionMember;
    private String thirdCommissionMember;
    private String actForRequest;
    private String forCause;
    private String childLivesWith;
    private String socialStatusOfFamily;
    private String flatRoomCount;
    private String flatArea;
    private String cleanConditions;
    private String flatCleanState;
    private String presentFamilyDuringInspection;
    private String familyProfit;
    private String childHas;
    private String childHasSuchThings;
    private String familyNeed;
    private String commissionConclusion;
    private LocalDate actDate;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getHeadCommission() {
        return headCommission;
    }

    public void setHeadCommission(String headCommission) {
        this.headCommission = headCommission;
    }

    public String getFirstCommissionMember() {
        return firstCommissionMember;
    }

    public void setFirstCommissionMember(String firstCommissionMember) {
        this.firstCommissionMember = firstCommissionMember;
    }

    public String getSecondCommissionMember() {
        return secondCommissionMember;
    }

    public void setSecondCommissionMember(String secondCommissionMember) {
        this.secondCommissionMember = secondCommissionMember;
    }

    public String getThirdCommissionMember() {
        return thirdCommissionMember;
    }

    public void setThirdCommissionMember(String thirdCommissionMember) {
        this.thirdCommissionMember = thirdCommissionMember;
    }

    public String getActForRequest() {
        return actForRequest;
    }

    public void setActForRequest(String actForRequest) {
        this.actForRequest = actForRequest;
    }

    public String getForCause() {
        return forCause;
    }

    public void setForCause(String forCause) {
        this.forCause = forCause;
    }

    public String getChildLivesWith() {
        return childLivesWith;
    }

    public void setChildLivesWith(String childLivesWith) {
        this.childLivesWith = childLivesWith;
    }

    public String getSocialStatusOfFamily() {
        return socialStatusOfFamily;
    }

    public void setSocialStatusOfFamily(String socialStatusOfFamily) {
        this.socialStatusOfFamily = socialStatusOfFamily;
    }

    public String getFlatRoomCount() {
        return flatRoomCount;
    }

    public void setFlatRoomCount(String flatRoomCount) {
        this.flatRoomCount = flatRoomCount;
    }

    public String getFlatArea() {
        return flatArea;
    }

    public void setFlatArea(String flatArea) {
        this.flatArea = flatArea;
    }

    public String getCleanConditions() {
        return cleanConditions;
    }

    public void setCleanConditions(String cleanConditions) {
        this.cleanConditions = cleanConditions;
    }

    public String getFlatCleanState() {
        return flatCleanState;
    }

    public void setFlatCleanState(String flatCleanState) {
        this.flatCleanState = flatCleanState;
    }

    public String getPresentFamilyDuringInspection() {
        return presentFamilyDuringInspection;
    }

    public void setPresentFamilyDuringInspection(String presentFamilyDuringInspection) {
        this.presentFamilyDuringInspection = presentFamilyDuringInspection;
    }

    public String getFamilyProfit() {
        return familyProfit;
    }

    public void setFamilyProfit(String familyProfit) {
        this.familyProfit = familyProfit;
    }

    public String getChildHas() {
        return childHas;
    }

    public void setChildHas(String childHas) {
        this.childHas = childHas;
    }

    public String getChildHasSuchThings() {
        return childHasSuchThings;
    }

    public void setChildHasSuchThings(String childHasSuchThings) {
        this.childHasSuchThings = childHasSuchThings;
    }

    public String getFamilyNeed() {
        return familyNeed;
    }

    public void setFamilyNeed(String familyNeed) {
        this.familyNeed = familyNeed;
    }

    public String getCommissionConclusion() {
        return commissionConclusion;
    }

    public void setCommissionConclusion(String commissionConclusion) {
        this.commissionConclusion = commissionConclusion;
    }

    public LocalDate getActDate() {
        return actDate;
    }

    public void setActDate(LocalDate actDate) {
        this.actDate = actDate;
    }

    @Override
    public String toString() {
        return "studentName " + studentName + "\t\t\t"+
                "birthDate " + birthDate + "\t\t\t"+
                "studentAddress " + studentAddress + "\t\t\t"+
                "headCommission " +  headCommission + "\t\t\t"+
                "firstCommissionMember " +  firstCommissionMember + "\t\t\t"+
                "secondCommissionMember "  + secondCommissionMember + "\t\t\t"+
                "thirdCommissionMember " + thirdCommissionMember + "\t\t\t"+
                "actForRequest " + actForRequest + "\t\t\t"+
                "forCause " + forCause + "\t\t\t"+
                "childLivesWith " + childLivesWith + "\t\t\t"+
                 "socialStatusOfFamily " + socialStatusOfFamily + "\t\t\t"+
                 "flatRoomCount " + flatRoomCount + "\t\t\t"+
                 "flatArea " + flatArea + "\t\t\t"+
                 "cleanConditions " + cleanConditions + "\t\t\t"+
                 "flatCleanState " + flatCleanState + "\t\t\t"+
                 "presentFamilyDuringInspection " + presentFamilyDuringInspection + "\t\t\t"+
                 "familyProfit " + familyProfit + "\t\t\t"+
                 "childHas " + childHas + "\t\t\t"+
                 "childHasSuchThings " + childHasSuchThings + "\t\t\t"+
                 "familyNeed " + familyNeed + "\t\t\t"+
                 "commissionConclusion " + commissionConclusion + "\t\t\t"+
                 "actDate " + actDate + "\t\t\t";
    }

}
