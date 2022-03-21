package com.globant.topic_7.Controller;

import com.globant.topic_7.Persistence.Model.Location;
import com.globant.topic_7.Service.Interfaces.LocationService;
import com.globant.topic_7.Service.Interfaces.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String index(Model model) {
        try {
            model.addAttribute("header", weatherService.getLastWeatherRecord());
        } catch (Exception e) {
            model.addAttribute("header", null);
        }
        try {
            model.addAttribute("locations", locationService.getAll());
        } catch (Exception e) {
            model.addAttribute("locations", new ArrayList<Location>());
        }
        return "locations";
    }

    @GetMapping("/new")
    public String newLocation(Model model) {
        try {
            model.addAttribute("header", weatherService.getLastWeatherRecord());
        } catch (Exception e) {
            model.addAttribute("header", null);
        }
        model.addAttribute("location", new Location());
        return "form-location";
    }

    @PostMapping("/new")
    public String saveLocation(@ModelAttribute("location") Location location, Model model, RedirectAttributes redirectAttributes) {
        try {
            locationService.newLocation(location);
        } catch (Exception e) {
            try {
                model.addAttribute("header", weatherService.getLastWeatherRecord());
            } catch (Exception ex) {
                model.addAttribute("header", null);
            }
            model.addAttribute("location", location);
            model.addAttribute("errMsg", e.getMessage());
            return "form-location";
        }
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/locations/";
    }

    @GetMapping("/edit/{id}")
    public String editLocation(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("header", weatherService.getLastWeatherRecord());
        } catch (Exception e) {
            model.addAttribute("header", null);
        }
        try {
            model.addAttribute("location", locationService.getLocation(id));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:/locations/";
        }
        model.addAttribute("edit", true);
        return "form-location";
    }

    @PostMapping("/edit")
    public String updateLocation(@ModelAttribute("location") Location location, Model model, RedirectAttributes redirectAttributes) {
        try {
            locationService.editLocation(location);
        } catch (Exception e) {
            try {
                model.addAttribute("header", weatherService.getLastWeatherRecord());
            } catch (Exception ex) {
                model.addAttribute("header", null);
            }
            model.addAttribute("location", location);
            model.addAttribute("errMsg", e.getMessage());
            return "form-location";
        }
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/locations/";
    }

    @GetMapping("/delete/{id}")
    public String deleteLocation(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes){
        try {
            locationService.deleteLocation(id);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:/locations/";
        }
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/locations/";
    }
}
