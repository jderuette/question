package fr.gamedev.question.greeting;

/**
 * @author djer1
 *
 */
public class Greeting {

    /** Greeting id.*/
    private final long id;
    /** Greeting message.*/
    private final String content;

    /** new salutation.
     * @param newId the ID
     * @param newContent the content
     */
    public Greeting(final long newId, final String newContent) {
        this.id = newId;
        this.content = newContent;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

}
