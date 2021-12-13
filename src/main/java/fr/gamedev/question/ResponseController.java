package fr.gamedev.question;

import fr.gamedev.question.data.Answer;
import fr.gamedev.question.data.Question;
import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;
import fr.gamedev.question.repository.AnswerRepository;
import fr.gamedev.question.repository.QuestionRepository;
import fr.gamedev.question.repository.UserAnswerRepository;
import fr.gamedev.question.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

/**
 * @author RayaneKettana
 */
@RestController
public class ResponseController {
  /**
   * Answer Repository.
   */
  @Autowired
  private AnswerRepository answerRepo;

  /**
   * User Answer repository.
   */
  @Autowired
  private UserAnswerRepository userAnswerRepo;

  /**
   * Question repository.
   */
  @Autowired
  private QuestionRepository questionRepo;

  /**
   * User repository.
   */
  @Autowired
  private UserRepository userRepo;

  /**
   * User answer and process validation.
   *
   * @param questionId the id of the question.
   * @param answer     the Answer.
   * @param userId     the userId.
   * @return Indication about correctness of the answer provided.
   */
  @SuppressWarnings({"checkstyle:NeedBraces", "checkstyle:AvoidInlineConditionals"})
  @GetMapping("/response")
  public String answer(
    @RequestParam final long questionId,
    @RequestParam final Boolean answer,
    @RequestParam final long userId
  ) {

    String response = "Erreur pour trouver l'utilisateur";

    Optional<Question> question = questionRepo.findById(questionId);

    if (!question.isPresent()) return response;

    Optional<Answer> expectedAnswer = answerRepo.findByQuestion(question.get());
    response = "Erreur pour trouver la question";

    if (!expectedAnswer.isPresent()) return response;

    UserAnswer userAnswer = new UserAnswer();

    Optional<User> user = userRepo.findById(userId);

    if (!user.isPresent()) return response;

    userAnswer.setUser(user.get());
    userAnswer.setAnswer(expectedAnswer.get());

    userAnswer.setPoints(expectedAnswer.get().getCorrectAnswer() == answer
      ? 1
      : 0
    );

    userAnswerRepo.save(userAnswer);

    return responseFromPoints((int) userAnswer.getPoints());
  }

  /**
   * Get response from point.
   *
   * @param point point
   * @return (String)
   */
  @SuppressWarnings("checkstyle:FinalParameters")
  protected String responseFromPoints(Integer point) {
    return new HashMap<Integer, String>() {{
      put(0, "Réponse incorrecte");
      put(1, "Réponse correcte");
    }}.get(point);
  }
}
