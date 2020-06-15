package com.jo.crowd.entity;

public class School {
    private String primary;
    private String middle;
    private String senior;

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getSenior() {
        return senior;
    }

    public void setSenior(String senior) {
        this.senior = senior;
    }


    @Override
    public String toString() {
        return "School{" +
                "primary='" + primary + '\'' +
                ", middle='" + middle + '\'' +
                ", senior='" + senior + '\'' +
                '}';
    }
}
