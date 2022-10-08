package com.krish.bgmiadminapp.match;

public class Choose_Squad {
    private String uid,name,p1Id,p2Id,p3Id,p4Id,p1N,p2N,p3N,p4N,ref_no;

    public Choose_Squad() {
    }

    public Choose_Squad(String uid, String name, String p1Id, String p2Id, String p3Id, String p4Id, String p1N, String p2N, String p3N, String p4N, String ref_no) {
        this.uid = uid;
        this.name = name;
        this.p1Id = p1Id;
        this.p2Id = p2Id;
        this.p3Id = p3Id;
        this.p4Id = p4Id;
        this.p1N = p1N;
        this.p2N = p2N;
        this.p3N = p3N;
        this.p4N = p4N;
        this.ref_no = ref_no;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getP1Id() {
        return p1Id;
    }

    public void setP1Id(String p1Id) {
        this.p1Id = p1Id;
    }

    public String getP2Id() {
        return p2Id;
    }

    public void setP2Id(String p2Id) {
        this.p2Id = p2Id;
    }

    public String getP3Id() {
        return p3Id;
    }

    public void setP3Id(String p3Id) {
        this.p3Id = p3Id;
    }

    public String getP4Id() {
        return p4Id;
    }

    public void setP4Id(String p4Id) {
        this.p4Id = p4Id;
    }

    public String getP1N() {
        return p1N;
    }

    public void setP1N(String p1N) {
        this.p1N = p1N;
    }

    public String getP2N() {
        return p2N;
    }

    public void setP2N(String p2N) {
        this.p2N = p2N;
    }

    public String getP3N() {
        return p3N;
    }

    public void setP3N(String p3N) {
        this.p3N = p3N;
    }

    public String getP4N() {
        return p4N;
    }

    public void setP4N(String p4N) {
        this.p4N = p4N;
    }

    public String getRef_no() {
        return ref_no;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }
}
