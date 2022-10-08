package com.krish.bgmiadminapp.match;

public class MatchData {
    private String uploaddate,uploadtime,date,time,referencenum,charge,maxParticipants,pricepool,matchTime,matchCategories,matchImage,room_Id,room_Pass;
    public MatchData() {
    }

    public MatchData(String uploaddate, String uploadtime, String date, String time, String referencenum, String charge, String maxParticipants, String pricepool, String matchTime, String matchCategories, String matchImage, String room_Id, String room_Pass) {
        this.uploaddate = uploaddate;
        this.uploadtime = uploadtime;
        this.date = date;
        this.time = time;
        this.referencenum = referencenum;
        this.charge = charge;
        this.maxParticipants = maxParticipants;
        this.pricepool = pricepool;
        this.matchTime = matchTime;
        this.matchCategories = matchCategories;
        this.matchImage = matchImage;
        this.room_Id = room_Id;
        this.room_Pass = room_Pass;
    }

    public String getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(String uploaddate) {
        this.uploaddate = uploaddate;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReferencenum() {
        return referencenum;
    }

    public void setReferencenum(String referencenum) {
        this.referencenum = referencenum;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(String maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getPricepool() {
        return pricepool;
    }

    public void setPricepool(String pricepool) {
        this.pricepool = pricepool;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public String getMatchCategories() {
        return matchCategories;
    }

    public void setMatchCategories(String matchCategories) {
        this.matchCategories = matchCategories;
    }

    public String getMatchImage() {
        return matchImage;
    }

    public void setMatchImage(String matchImage) {
        this.matchImage = matchImage;
    }

    public String getRoom_Id() {
        return room_Id;
    }

    public void setRoom_Id(String room_Id) {
        this.room_Id = room_Id;
    }

    public String getRoom_Pass() {
        return room_Pass;
    }

    public void setRoom_Pass(String room_Pass) {
        this.room_Pass = room_Pass;
    }
}
