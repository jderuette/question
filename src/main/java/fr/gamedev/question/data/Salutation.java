package fr.gamedev.question.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author djer1
 *
 */
@Entity
public class Salutation {

    /** message id. */
    @GeneratedValue
    @Id
    private Long id;
    /** salutation message. */
    private String message;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param theId the id to set
     */
    public void setId(final Long theId) {
        this.id = theId;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param theMessage the message to set
     */
    public void setMessage(final String theMessage) {
        this.message = theMessage;
    }

}
