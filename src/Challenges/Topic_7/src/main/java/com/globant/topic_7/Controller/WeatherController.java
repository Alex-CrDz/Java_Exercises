package com.globant.topic_7.Controller;

import com.globant.topic_7.Dto.WeatherRecordDto;
import com.globant.topic_7.Service.Interfaces.LocationService;
import com.globant.topic_7.Service.Interfaces.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private LocationService locationService;

    @GetMapping("/")
    public String index(Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("header", weatherService.getLastWeatherRecord());
        } catch (Exception e) {
            model.addAttribute("header", null);
        }
        try {
            model.addAttribute("history", weatherService.getHistory());
        } catch (Exception e) {
            model.addAttribute("history", new ArrayList<WeatherRecordDto>());
        }
        try {
            locationService.getAll();
        } catch (Exception e) {
            return "index";
        }
        model.addAttribute("addRecord", true);
        return "index";
    }

    @GetMapping("/records/new")
    public String newRecord(Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("header", weatherService.getLastWeatherRecord());
        } catch (Exception e) {
            model.addAttribute("header", null);
        }
        try {
            model.addAttribute("locations", locationService.getAll());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:/";
        }
        model.addAttribute("record", new WeatherRecordDto());
        return "form-record";
    }

    @PostMapping("/records/new")
    public String saveRecord(@ModelAttribute("record") WeatherRecordDto recordDto, Model model, RedirectAttributes redirectAttributes) {
        try {
            weatherService.newRecord(recordDto);
        } catch (Exception e) {
            try {
                model.addAttribute("header", weatherService.getLastWeatherRecord());
            } catch (Exception ex) {
                model.addAttribute("header", null);
            }
            model.addAttribute("record", recordDto);
            model.addAttribute("errMsg", e.getMessage());
            return "form-record";
        }
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/";
    }

    @GetMapping("/records/edit/{id}")
    public String editRecord(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("header", weatherService.getLastWeatherRecord());
        } catch (Exception e) {
            model.addAttribute("header", null);
        }
        try {
            model.addAttribute("locations", locationService.getAll());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:/";
        }
        try {
            model.addAttribute("record", weatherService.getRecord(id));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:/";
        }
        model.addAttribute("edit", true);
        return "form-record";
    }

    @PostMapping("/records/edit")
    public String updateRecord(@ModelAttribute("record") WeatherRecordDto recordDto, Model model, RedirectAttributes redirectAttributes) {
        try {
            weatherService.editRecord(recordDto);
        } catch (Exception e) {
            try {
                model.addAttribute("header", weatherService.getLastWeatherRecord());
            } catch (Exception ex) {
                model.addAttribute("header", null);
            }
            model.addAttribute("record", recordDto);
            model.addAttribute("errMsg", e.getMessage());
            return "form-record";
        }
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/";
    }

    @GetMapping("/records/delete/{id}")
    public String deleteRecord(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            weatherService.deleteRecord(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/";
    }
}
