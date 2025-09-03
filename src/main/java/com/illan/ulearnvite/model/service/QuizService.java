package com.illan.ulearnvite.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.illan.ulearnvite.model.entity.Answer;
import com.illan.ulearnvite.model.entity.Lesson;
import com.illan.ulearnvite.model.entity.Question;
import com.illan.ulearnvite.model.entity.Quiz;
import com.illan.ulearnvite.model.repository.AnswerRepository;
import com.illan.ulearnvite.model.repository.QuestionRepository;
import com.illan.ulearnvite.model.repository.QuizRepository;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

//Methode pour charger un quiz complet (avec ses questions et lecons) à partir d'une lecon
public Quiz getQuizByLesson(Lesson lesson)
{
Quiz quiz = quizRepository.findByLesson(lesson);
if(quiz!=null){
    quiz.setQuestions(questionRepository.findByQuiz(quiz));

    for(Question question: quiz.getQuestions()){
        question.setAnswers(answerRepository.findByQuestion(question));
    }

return quiz;
}

// Si aucun quiz n'est trouvé, retourne null (ou tu peux lever une exception selon ta logique)
return null;  
}

    // Méthode pour corriger un quiz : calcule le score de l'utilisateur
    // userAnswers : map questionId -> answerId choisi par l'utilisateur
    public int checkQuiz(Quiz quiz, Map<Long, Long> userAnswers) {
        int score = 0; // Compteur de bonnes réponses
        // Parcourt toutes les questions du quiz
        for (Question question : quiz.getQuestions()) {
            // Récupère la réponse choisie par l'utilisateur pour cette question
            Long userAnswerId = userAnswers.get(question.getId());
            if (userAnswerId != null) {
                // Récupère l'objet Answer correspondant à l'id choisi
                Answer answer = answerRepository.findById(userAnswerId).orElse(null);
                // Si la réponse existe et est correcte, incrémente le score
                if (answer != null && answer.isCorrect()) {
                    score++;
                }
            }
        }
        // Retourne le score total de l'utilisateur pour ce quiz
        return score;
    }
}




