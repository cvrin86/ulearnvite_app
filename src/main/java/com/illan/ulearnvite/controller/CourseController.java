package com.illan.ulearnvite.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.illan.ulearnvite.model.entity.Course;
import com.illan.ulearnvite.model.entity.Lesson;
import com.illan.ulearnvite.model.entity.Section;
import com.illan.ulearnvite.model.entity.User;
import com.illan.ulearnvite.model.service.CourseService;


@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/course/search")
    public String getSearch(){
        return "courses/search";
    }

    @GetMapping
    public String getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue ="7") int size,
        
    Model model){

Page<Course> coursesPage = courseService.getAllCourses(page,size);

// System.out.println("Les cours : " + courses);
model.addAttribute("courses",coursesPage);
model.addAttribute("page", page);
model.addAttribute("totalPages", coursesPage.getTotalPages());

model.addAttribute("content","courses/list-courses");

return "base";
    }


@GetMapping("/popular-courses")
public String popularCourses(Model model){
    List<Course> popularCourses = courseService.getPopularCourses();
    
    
    // Affichage dans la console
    // System.out.println("Nombre de cours populaires récupérés : " + popularCourses.size());
    
    for (Course course : popularCourses) {
       System.out.println(course);
    }
    
    model.addAttribute("popularCourses", popularCourses);
    model.addAttribute("content", "courses/popular");
    return "base";
}











@GetMapping("/{id}")
public String getCourseDetails(@PathVariable Long id,
                               @RequestParam(value="lessonId", required = false) Long lessonId,
                               Model model) {

    Course course = courseService.getCourseById(id); // charge les sections et lessons

    


   
      if (course == null) {
        if (course == null) {
            return "redirect:/404"; // Redirige vers le contrôleur ou template /404
        }
            }

    List<Section> sections = course.getSections();

     // 2. Récupérer l'instructeur (ici, le user du cours)
     User instructor = course.getUser();

    // Progression fictive (exemple : 0%)
    Byte percentage = 22;

      // Trouver la leçon sélectionnée
      Lesson selectedLesson = null;
      if (sections != null && !sections.isEmpty()) {
          if (lessonId != null) {
              for (Section section : sections) {
                  if (section.getLessons() != null) {
                      selectedLesson = section.getLessons().stream()
                          .filter(l -> l.getId().equals(lessonId))
                          .findFirst().orElse(null);
                      if (selectedLesson != null) break;
                  }
              }
          }
          // Si aucune leçon sélectionnée, prendre la première leçon du premier section non vide
          if (selectedLesson == null) {
              for (Section section : sections) {
                  if (section.getLessons() != null && !section.getLessons().isEmpty()) {
                      selectedLesson = section.getLessons().get(0);
                      break;
                  }
              }
          }
      }
  

    model.addAttribute("course", course);
    model.addAttribute("sections", sections);
    model.addAttribute("progress", percentage);
    model.addAttribute("selectedLesson", selectedLesson);
    model.addAttribute("instructor", instructor);

    model.addAttribute("content", "pages/course-details");
    return "base";
}


/**
 * 
 * @param id
 * @param model
 * @return
 */
// @GetMapping("/{id}")
// public String getCourseDetails(@PathVariable Long id,
// @RequestParam(value="lessonId",required = false) Long lessonId,
// Model model ){

//     Course course = courseService.getCourseById(id);
  
    
//      // Liste des leçons du cours
//      List<Lesson> lessons = course.getLessons();

//      // Détermine la leçon sélectionnée (par défaut la première)
//      Lesson selectedLesson = null;
//      if (lessonId != null) {
//          selectedLesson = lessons.stream()
//              .filter(l -> l.getId().equals(lessonId))
//              .findFirst()
//              .orElse(lessons.isEmpty() ? null : lessons.get(0));
//      } else if (!lessons.isEmpty()) {
//          selectedLesson = lessons.get(0);
//      }


//      System.out.println(selectedLesson);

//     // On selectionne une video par defaut
//     Lesson defaultVideoLesson=lessons.stream()
//     . filter(lesson->lesson.getVideo() !=null )
//     .findFirst().orElse(null) ;

//     if(defaultVideoLesson==null){
//         // throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Aucune vidéo disponible !!!");
//         return "redirect:/404";
//     }

//     if(course==null || lessons.isEmpty()){
//         return "redirect:/courses";
//     }


// //    System.out.println(defaultVideoLesson.getVideo().getFileUrl);

//     // System.out.println("les details du cours : " + course.getUser().getUsername());
//     model.addAttribute("course", course);
//     model.addAttribute("lessons", lessons);
//     model.addAttribute("selectedLesson", selectedLesson);
//     model.addAttribute("defaultVideoLesson", defaultVideoLesson.getVideo().getFileUrl());
//     // content  est une variable qui est utilisée dans la vue  pour afichier le contenu de la page à afficher 
    
//     model.addAttribute("content", "pages/course-details");
//     return "base";
// }









// @GetMapping("/{id}")
// public String getCourseDetails(@PathVariable Long id,
//                                @RequestParam(value="lessonId", required = false) Long lessonId,
//                                Model model, Principal principal) {

//     Course course = courseService.getCourseById(id); // charge les sections et lessons
//     List<Section> sections = course.getSections();

//     // Récupérer la progression de l'utilisateur connecté
//     User user = userService.findByUsername(principal.getName());
//     Progress progress = progressRepository.findByUserAndCourse(user, course).orElse(null);
//     Byte percentage = (progress != null) ? progress.getPercentage() : 0;

//     // Trouver la leçon sélectionnée
//     Lesson selectedLesson = null;
//     if (lessonId != null) {
//         for (Section section : sections) {
//             selectedLesson = section.getLessons().stream()
//                 .filter(l -> l.getId().equals(lessonId))
//                 .findFirst().orElse(null);
//             if (selectedLesson != null) break;
//         }
//     }
//     if (selectedLesson == null && !sections.isEmpty() && !sections.get(0).getLessons().isEmpty()) {
//         selectedLesson = sections.get(0).getLessons().get(0);
//     }

//     model.addAttribute("course", course);
//     model.addAttribute("sections", sections);
//     model.addAttribute("progress", percentage);
//     model.addAttribute("selectedLesson", selectedLesson);

//     return "pages/course-details";
// }




}
