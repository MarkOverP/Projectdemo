package com.example.projectdemo.controller;

import com.example.projectdemo.FileUploadUtil;
import com.example.projectdemo.model.Activity;
import com.example.projectdemo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping("index")
    public String Activity1(Model model, @Param("keyword") String keyword) {
        List<Activity> listActivity = activityService.getAllActivities(keyword);
        model.addAttribute("listActivity", listActivity);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @GetMapping("/showNewActivityForm")
    public String Activity2(Model model) {
        // create model attribute to bind form date
        Activity activity = new Activity();
        model.addAttribute("activity", activity);
        return "Add_Activity";
    }

    @PostMapping("/saveProduct")
    public RedirectView saveActivity(@ModelAttribute("activity") Activity activity,
                                     @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        activity.setPhotos(filename);
        Activity saveActivity = activityService.saveActivity(activity);
        String uploadDir = "activity-photos/" + saveActivity.getId();
        FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
        return new RedirectView("/", true);
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String UpdateImage(@PathVariable(value = "id") long id, Model model) {

        Activity activity = activityService.getActivityById(id);

        model.addAttribute("activity", activity);
        return "Edit_Activity";
    }

    public String deleteActivity(@PathVariable(value = "id") long id) {
        this.activityService.deleteActivityById(id);
        return "redirect:/";
    }


}
