package fr.gamedev.question.data;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * User class.
 */
@Entity
public class User {

  /**
   * The id.
   */
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
  /**
   * The login.
   */
  private String login;
  /**
   * The last name.
   */
  private String lastName;

    /** User preferences about questions type (tags).*/
    @ManyToMany
    private List<Tag> interests;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

  /**
   * @param newId the id to set
   */
  public void setId(final long newId) {
    this.id = newId;
  }

  /**
   * @return the login
   */
  public String getLogin() {
    return login;
  }

  /**
   * @param newLogin the login to set
   */
  public void setLogin(final String newLogin) {
    this.login = newLogin;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param newLastName the lastName to set
   */
  public void setLastName(final String newLastName) {
    this.lastName = newLastName;
  }

    /**
     * @return the interests
     */
    public List<Tag> getInterests() {
        return interests;
    }

    /**
     * @param newInterests the interests to set
     */
    public void setInterests(final List<Tag> newInterests) {
        this.interests = newInterests;
    }

}
