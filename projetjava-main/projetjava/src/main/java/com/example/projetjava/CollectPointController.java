package com.example.projetjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/collect-points")
public class CollectPointController {

    @Autowired
    private CollectPointRepository collectPointRepository;

    // Display the list of collection points
    @GetMapping
    public String listCollectPoints(Model model) {
        List<CollectPoint> collectPoints = collectPointRepository.findAll();
        model.addAttribute("collectPoints", collectPoints);
        return "collect-points";
    }

    // Show the add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("collectPoint", new CollectPoint());
        return "add-collect-point";
    }

    // Save a new point from the form
    @PostMapping("/add")
    public String addCollectPoint(@ModelAttribute CollectPoint collectPoint) {
        collectPointRepository.save(collectPoint);
        return "redirect:/admin/collect-points";
    }

    // Save a new point from the map click (AJAX)
    @PostMapping("/add-from-map")
    @ResponseBody
    public ResponseEntity<?> addCollectPointFromMap(@RequestBody CollectPoint collectPoint) {
        collectPointRepository.save(collectPoint);
        return ResponseEntity.ok().build();
    }

    // Delete a point
    @GetMapping("/delete/{id}")
    public String deleteCollectPoint(@PathVariable Long id) {
        collectPointRepository.deleteById(id);
        return "redirect:/admin/collect-points";
    }
}
