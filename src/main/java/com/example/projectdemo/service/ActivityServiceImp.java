package com.example.projectdemo.service;

import com.example.projectdemo.model.Activity;
import com.example.projectdemo.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImp implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities(String keyword) {
        if (keyword != null){
            return activityRepository.search(keyword);
        }else
            return (List<Activity>) activityRepository.findAll();
    }

    @Override
    public Activity saveActivity(Activity activity) {
        return this.activityRepository.save(activity);
    }

    public Activity getActivityById(long id) {
        Optional<Activity> optional = activityRepository.findById(id);
        Activity activity = null;
        if (optional.isPresent()) {
            activity = optional.get();
        }else {
            throw new RuntimeException("Activity not found for id :: " + id);
        }
        return activity;
    }

    public void deleteActivityById(long id) {
        this.activityRepository.deleteById(id);
    }


}
