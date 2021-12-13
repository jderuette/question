package fr.gamedev.question.greeting;

/**
 * @author djer1
 */
public class Greeting {
    /**
     * The id.
     */
    private final long id;
    /**
     * The content.
     */
    private final String content;

    public Greeting(final long newId, final String newContent) {
        this.id = newId;
        this.content = newContent;
    }

    /**
     * Get Id.
     *
     * @return (long)
     */
    public long getId() {
        return id;
    }

    /**
     * Get Content.
     *
     * @return (String)
     */
    public String getContent() {
        return content;
    }


}
