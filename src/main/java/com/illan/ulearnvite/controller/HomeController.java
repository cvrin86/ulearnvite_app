package com.illan.ulearnvite.controller;

import com.illan.ulearnvite.model.entity.Course; // ou Formation selon ton modèle // ou FormationService
import com.illan.ulearnvite.model.service.CourseService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView(); // creer l'objet(classe) qui contient les données envoyés a la vue
        mav.setViewName("base"); // la page base sera utilisée comme vue principale
        mav.addObject("showMenu", true);


// Ajouter les cours populaires au modèle
List<Course> popularCourses = courseService.getPopularCourses();

 // Afficher les cours dans les logs
 popularCourses.forEach(course -> {
    System.out.println(course.getTitle()); // Vérifie que chaque objet a bien un 'title'
});


List<Course> topThreeCourses = popularCourses.stream().skip(Math.max(0, popularCourses.size()-3)).collect(Collectors.toList());
mav.addObject("popularCourses", topThreeCourses);  // Ajouter les cours populaires au modèle


        mav.addObject("content", "home/home"); // ajoute une variable "content " au modele qui contient la


        return mav;
    }
}

//    @Autowired
//    private CourseService courseService; // Service pour accéder aux cours
//
//    @GetMapping("/")
//    public ModelAndView home() {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("base"); // Vue principale Thymeleaf (avec fragments)
//
//        // Récupération de la liste des cours disponibles
//        List<Course> courses = courseService.getAll(); 
//        mav.addObject("courses", courses); // Envoie à la vue
//
//        mav.addObject("content", "course/list"); 
//        // Fragment affiché dynamiquement dans base.html (à créer : course/list.html)
//
//        return mav;
//    }

