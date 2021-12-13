package fr.gamedev.question.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Category class.
 */
@Entity
public class Category {
    /**
     * The id.
     */
    @GeneratedValue(generator = "seq_gen_category")
    @GenericGenerator(
      name = "seq_gen_category",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "seq_category"),
          @Parameter(name = "initial_value", value = "0"),
          @Parameter(name = "increment_size", value = "1")
      }
    )
    @Id
    private long id;

    /**
     * The category name.
     */
    private String value;

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
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param newValue the value to set
     */
    public void setValue(final String newValue) {
        this.value = newValue;
    }

}
