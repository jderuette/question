package fr.gamedev.question.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author djer1
 *
 */
@Entity
public class Question {
	
	 @GeneratedValue(generator = "seq_gen_question")
	 @GenericGenerator(
			 name = "seq_gen_question",
			 strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			 parameters = {
				 	@Parameter(name = "sequence_name", value = "seq_question"),
				 	@Parameter(name = "initial_value", value = "0"),
				 	@Parameter(name = "increment_size", value = "1")
			 }
	)
	@Id
	private long id;
	private String content;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
