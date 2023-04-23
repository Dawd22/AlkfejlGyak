package org.example.model;

public class Jedi {
    private Integer id;
    private String name;
    private String rank;
    private String gender;
    private String councilmember;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCouncilmember() {
        return councilmember;
    }

    public void setCouncilmember(String councilmember) {
        this.councilmember = councilmember;
    }
}
