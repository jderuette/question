package fr.gamedev.question.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author djer13
 *
 */
@Projection(name = "askedQuestion", types = { UserAnswer.class })
public interface UserAnswerAsAskedQuestion {
    @Value("#{target.id}")
    long getId();

    Integer getPoints();

    @Value("#{target.answer.question}")
    Question getQuestion();

}
