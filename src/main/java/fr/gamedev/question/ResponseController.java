package fr.gamedev.question;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.gamedev.question.data.Answer;
import fr.gamedev.question.data.UserAnswer;
import fr.gamedev.question.repository.UserAnswerRepository;

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

    /** Answer provided by users (and points earned) repository. */
    @Autowired
    private UserAnswerRepository userAnswerRepo;

    /** Collect a user answer for a specific question.
     * @param userAnswerId : the id of the question answered.
     * @param answer the user Answer.
     * @return Indication about correctness of the answer provided.*/
    @PostMapping(value = "/response", produces = "application/hal+json")
    public UserAnswer answer(@RequestParam final long userAnswerId, @RequestParam final Boolean answer) {

        UserAnswer response = null;

        Optional<UserAnswer> askedQuestion = userAnswerRepo.findById(userAnswerId);

        Assert.isTrue(askedQuestion.isPresent(), "Réponse ignorée : la question ne vous à pas été posée !");
        Assert.isTrue(askedQuestion.get().getPoints() == null,
                "Réponse ignorée : vous avez déja répondu à cette question.");

        Answer expectedAnswer = askedQuestion.get().getAnswer();

        if (expectedAnswer.getCorrectAnswer() == answer) {
            //Si un utilisateur a deja répondu correctement à la question,
            // il ne gagne que 50% des points gagné précédement.
            //S'il répond Faux, il gagne 0 points et cela n'a pas d'impacts sur les calculs futurs.

            Optional<UserAnswer> lastUserAnswer = userAnswerRepo
                    .findTopByAnswerQuestionAndUserAndPointsNotNullAndPointsIsGreaterThanOrderByPoints(
                            askedQuestion.get().getAnswer().getQuestion(), askedQuestion.get().getUser(), 0);

            Integer nbPoints = POINT_FOR_CORRECT_ANSWER;
            if (lastUserAnswer.isPresent()) {
                Integer lastEarnedPoints = lastUserAnswer.get().getPoints();
                nbPoints = lastEarnedPoints / 2;
            }

            //Ajouter des points
            askedQuestion.get().setPoints(nbPoints);
            //response = "Bravo ! vous avez trouvé ! ";
        } else {
            //Ne pas ajouter de points
            askedQuestion.get().setPoints(POINT_FOR_BAD_ANSWER);
            //response = "Oops ! Ca n'est pas correcte";
        }

        response = userAnswerRepo.save(askedQuestion.get());

        return response;
    }

}
