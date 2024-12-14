package com.example.projetjava;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
@Controller
public class admin {

	

	  @Autowired
	    private CollectPointRepository collectPointRepository;

	    @GetMapping("/map")
	    public String showMap(Model model) {
	    	List<CollectPoint> collectPoints = collectPointRepository.findAll();
	        model.addAttribute("collectPoints", collectPoints );
	        return "map";
	    }
}
