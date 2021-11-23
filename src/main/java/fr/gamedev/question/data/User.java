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
public class User {

	 @GeneratedValue(generator = "seq_gen_user")
	 @GenericGenerator(
			 name = "seq_gen_user",
			 strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			 parameters = {
				 	@Parameter(name = "sequence_name", value = "seq_user"),
				 	@Parameter(name = "initial_value", value = "0"),
				 	@Parameter(name = "increment_size", value = "1")
			 }
	)
	@Id
	private long id;
	private String login;
	private String lastName;

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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
