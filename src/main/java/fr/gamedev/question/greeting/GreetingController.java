package fr.gamedev.question.greeting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author djer1
 */
@RestController
public class GreetingController {

    /**
     * Template.
     */
    private static final String TPL = "Hello, %s!";
    /**
     * Counter.
     */
    private final AtomicLong counter = new AtomicLong();

    /**
     * Greeting route.
     * @param name The name.
     * @return (Greeting)
     */
    @GetMapping("/greeting")
    public Greeting greeting(final @RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TPL, name));
    }
}
