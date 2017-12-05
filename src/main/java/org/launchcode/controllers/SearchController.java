package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.launchcode.controllers.ListController.columnChoices;
import static org.omg.IOP.TAG_ORB_TYPE.value;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        model.addAttribute("columns", columnChoices);
        ArrayList<HashMap<String, String>> jobs;

        if (searchType.equals("all") || searchTerm.equals("")) {

           jobs = JobData.findByValue(searchTerm);


        } else {

            jobs=JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("columns" + ListController.columnChoices);

        }

        model.addAttribute("jobs", jobs);
        return "search";
    }
}



