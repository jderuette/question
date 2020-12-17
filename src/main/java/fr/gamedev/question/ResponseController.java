package fr.gamedev.question;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author djer1
 *
 */
@RestController
public class ResponseController {

    /** Collect a user answer for a specific question.
     * @param questionId : the id of the question answered.
     * @param answer the user Answer.
     * @param userId the user ID.
     * @return Indication about correctness of the answer provided.*/
    @GetMapping("/response")
    public String answer(@RequestParam final long questionId, @RequestParam final Boolean answer,
            @RequestParam final long userId) {
        String response;

        if (answer == Boolean.TRUE) {
            //Ajouter des points

            response = "Bravo ! vous avez trouvé ! ";
        } else {
            //Ne pas ajouter de points

            response = "Oops ! Ca n'est pas correcte";
        }

        return response;
    }

}
