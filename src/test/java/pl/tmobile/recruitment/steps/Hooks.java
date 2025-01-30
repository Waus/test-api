package pl.tmobile.recruitment.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void beforeTest() {
        logger.info("🔄 Rozpoczęto test.");
    }

    @After
    public void afterTest() {
        logger.info("✅ Test zakończony.");
    }
}
