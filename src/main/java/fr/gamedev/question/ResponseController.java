package fr.gamedev.question;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.gamedev.question.data.Answer;
import fr.gamedev.question.data.Question;
import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;
import fr.gamedev.question.repository.AnswerRepository;
import fr.gamedev.question.repository.QuestionRepository;
import fr.gamedev.question.repository.UserAnswerRepository;
import fr.gamedev.question.repository.UserRepository;

/**
 * @author djer1
 *
 */
@RestController
public class ResponseController {

    /** NB Point for a correct answer. */
    private static final Integer POINT_FOR_CORRECT_ANSWER = 15;
    /** NB Points for a bad answer. */
    private static final Integer POINT_FOR_BAD_ANSWER = 0;

    /** (correct) Answer Repository. */
    @Autowired
    private AnswerRepository answerRepo;

    /** Answer provided by users (and points earned) repository. */
    @Autowired
    private UserAnswerRepository userAnswerRepo;

    /** Questions repository. */
    @Autowired
    private QuestionRepository questionRepo;

    /** User repository.*/
    @Autowired
    private UserRepository userRepo;

    /** Collect a user answer for a specific question.
     * @param questionId : the id of the question answered.
     * @param answer the user Answer.
     * @param userId the user ID.
     * @return Indication about correctness of the answer provided.*/
    @PostMapping(value = "/response", produces = "application/hal+json")
    public UserAnswer answer(@RequestParam final long questionId, @RequestParam final Boolean answer,
            @RequestParam final long userId) {

        UserAnswer response = null;

        Optional<Question> question = questionRepo.findById(questionId);
        Assert.isTrue(question.isPresent(), "La question n'eiste pas !");

        Optional<Answer> expectedAnswer = answerRepo.findByQuestion(question.get());
        Assert.isTrue(expectedAnswer.isPresent(),
                "Impossible de vérifier votre réponse. Pas de bonne réponse de configurée pour la question !");

        Optional<User> user = userRepo.findById(userId);
        Assert.isTrue(user.isPresent(), "Cet utilisateur n'existe pas !");

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUser(user.get());
        userAnswer.setAnswer(expectedAnswer.get());

        if (expectedAnswer.get().getCorrectAnswer() == answer) {
            //Ajouter des points
            userAnswer.setPoints(POINT_FOR_CORRECT_ANSWER);
            //response = "Bravo ! vous avez trouvé ! ";
        } else {
            //Ne pas ajouter de points
            userAnswer.setPoints(POINT_FOR_BAD_ANSWER);
            //response = "Oops ! Ca n'est pas correcte";
        }

        response = userAnswerRepo.save(userAnswer);

        return response;
    }

}
