package net.shvdy.nutrition_tracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * 13.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class PropertiesContainerTest {

    @Test
    void test() {
        PropertiesContainer.readProperties();

        Arrays.stream(PropertiesContainer.DotProperties.values())
                .forEach(p -> assertNotNull(p.getProp()));

        Arrays.stream(PropertiesContainer.FormValidationConfig.values())
                .forEach(p -> assertNotNull(p.get()));
    }
}
