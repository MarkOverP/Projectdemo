package com.example.projectdemo.service;

import com.example.projectdemo.model.Activity;

import java.util.List;

public interface ActivityService {

    Activity saveActivity(Activity activity);
    Activity getActivityById(long id);

    void deleteActivityById(long id);
    List<Activity> getAllActivities(String keyword);

    //code written below for pagination
    //Page<Activity> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
