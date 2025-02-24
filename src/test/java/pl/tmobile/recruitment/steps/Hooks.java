package pl.tmobile.recruitment.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void beforeTest() {
        logger.info("Rozpoczęto test.");
    }

    @After
    public void afterTest() {
        logger.info("Test zakończony.");
    }
}