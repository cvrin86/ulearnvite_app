package com.illan.ulearnvite.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.illan.ulearnvite.model.entity.Lesson;
import com.illan.ulearnvite.model.entity.Question;
import com.illan.ulearnvite.model.entity.Quiz;
import com.illan.ulearnvite.model.repository.LessonRepository;

import com.illan.ulearnvite.model.service.QuizService;

import lombok.Data;

@Controller
@Data
public class QuizController {

    @Autowired 
    private LessonRepository lessonRepository;

    @Autowired
    private QuizService quizService;



    @GetMapping("/courses/{courseId}/lessons/{lessonId}/quiz")
    public String showQuiz(@PathVariable Long courseId,
                           @PathVariable Long lessonId,
                           Model model) {
        // Récupère la leçon
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        // Utilise le service pour charger le quiz complet (questions + réponses)
        Quiz quiz = quizService.getQuizByLesson(lesson);
    
        if (quiz == null) {
            // Gère le cas où il n'y a pas de quiz pour cette leçon
            model.addAttribute("message", "Aucun quiz pour cette leçon.");
            return "courses/quiz-empty";
        }
    
        model.addAttribute("quiz", quiz);
        model.addAttribute("lesson", lesson);

        model.addAttribute("content", "pages/course-details");
        return "base";
       
    }




    @PostMapping("/courses/{courseId}/lessons/{lessonId}/quiz")
public String submitQuiz(@PathVariable Long courseId,
                         @PathVariable Long lessonId,
                         @RequestParam Map<String, String> allParams,
                         Model model) {
    // Récupère la leçon et le quiz
    Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
    Quiz quiz = quizService.getQuizByLesson(lesson);

    Map<Long, Long> userAnswers = new HashMap<>();
     
    for (Question question : quiz.getQuestions()) {
        String paramName = "question_" + question.getId();
       
        if (allParams.containsKey(paramName)) {
            Long answerId = Long.parseLong(allParams.get(paramName));
            userAnswers.put(question.getId(), answerId);
        }
    }

    // Correction du quiz
    int score = quizService.checkQuiz(quiz, userAnswers);

    model.addAttribute("score", score);
    model.addAttribute("total", quiz.getQuestions().size());
    model.addAttribute("quiz", quiz);
    model.addAttribute("lesson", lesson);

     model.addAttribute("content", "pages/course-details");
        return "base";
}


}
