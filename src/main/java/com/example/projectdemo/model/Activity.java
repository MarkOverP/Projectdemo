package com.example.projectdemo.model;

import jakarta.persistence.*;


@Entity
@Table(name = "activitylist")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "activityname")
    private String activityname;

    @Column(name = "description")
    private String description;

    @Column(nullable = true,length=64)
    private String photos;

    @Transient
    public String getPhotosImagePath(){
        if(photos == null) return null;

        return "/activity-photos/" + id + "/" + photos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
