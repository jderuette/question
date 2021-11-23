package fr.gamedev.question.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author djer1
 *
 */
@Entity
public class Answer {
	
	 @GeneratedValue(generator = "seq_gen_answer")
	 @GenericGenerator(
			 name = "seq_gen_answer",
			 strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			 parameters = {
				 	@Parameter(name = "sequence_name", value = "seq_answer"),
				 	@Parameter(name = "initial_value", value = "0"),
				 	@Parameter(name = "increment_size", value = "1")
			 }
	)
	@Id
	private long id;
	
	@OneToOne
	private Question question;
	private Boolean correctAnswer;
	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}
	/**
	 * @return the correctAnswer
	 */
	public Boolean getCorrectAnswer() {
		return correctAnswer;
	}
	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(Boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
}
