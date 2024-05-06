package com.example.projectdemo.repository;

import com.example.projectdemo.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    //code written below for search activity from data
    @Query("SELECT activity FROM Activity activity WHERE CONCAT(activity.id, ' ',activity.activityname, ' ', activity.description) LIKE %?1%")
    public List<Activity> search(String keyword);
    public Activity findByActivityname(String activityname);
}
