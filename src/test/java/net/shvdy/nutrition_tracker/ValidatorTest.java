package net.shvdy.nutrition_tracker;

import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;

/**
 * 02.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
public class ValidatorTest {
    private static final Logger log = LogManager.getLogger(ValidatorTest.class);

    @Mock
    HttpServletRequest request;

    @BeforeAll
    static void init() {
        PropertiesContainer.readProperties();
    }

    @Test
    public void testUserSignUpFormValidation() {
        lenient().when(request.getParameter("username")).thenReturn("testing@mail.com");
        lenient().when(request.getParameter("password")).thenReturn("KT893AW22");
        lenient().when(request.getParameter("firstName")).thenReturn("Jason");
        lenient().when(request.getParameter("lastName")).thenReturn("Rich");
        assertEquals(validateUserSignUpForm.apply(request).size(), 0);

        testWrongValuesForField("username", Set.of("testing@mail", ".testing@mail.com",
                "testingmail.com", "testing@mail.m", "t/esting@mail.com"), validateUserSignUpForm);
        testWrongValuesForField("password", Set.of("6-7afh", "KT893AW22KT893AW22KT893AW22",
                "-KT893AW22", "KT893AW*22"), validateUserSignUpForm);
        testWrongValuesForField("firstName", Set.of("jason", "J", "Jasonяя",
                "Иванd"), validateUserSignUpForm);
        testWrongValuesForField("lastName", Set.of("jason", "J", "Jasonяя",
                "Иванd"), validateUserSignUpForm);
    }

    @Test
    public void testFoodFormValidation() {
        lenient().when(request.getParameter("newFoodName")).thenReturn("Chocolate");
        lenient().when(request.getParameter("newFoodProt")).thenReturn("40");
        lenient().when(request.getParameter("newFoodCalories")).thenReturn("910");
        lenient().when(request.getParameter("newFoodFats")).thenReturn("100");
        lenient().when(request.getParameter("newFoodCarbohydrates")).thenReturn("100");
        assertEquals(validateFoodForm.apply(request).size(), 0);

        testWrongValuesForField("newFoodName", Set.of(".Chocolate", "chocolate", "Z"), validateFoodForm);
        testWrongValuesForField("newFoodProt", Set.of("67afh", "0", "-2", "41", "10.3."), validateFoodForm);
        testWrongValuesForField("newFoodFats", Set.of("67afh", "0", "-2", "101", "10.3."), validateFoodForm);
        testWrongValuesForField("newFoodCalories", Set.of("67afh", "0", "-2", "911", "10.3."), validateFoodForm);
        testWrongValuesForField("newFoodCarbohydrates", Set.of("67afh", "0", "-2", "101", "10.3."), validateFoodForm);

    }

    private void testWrongValuesForField(String field, Set<String> wrongValues,
                                         Function<HttpServletRequest, Map<String, String>> validator) {
        wrongValues.forEach(v -> {
            lenient().when(request.getParameter(field)).thenReturn(v);

            try {
                assertNotNull(validator.apply(request).get(field + "Error"));
            } catch (AssertionFailedError e) {
                log.error(field + " " + v);
                throw e;
            }
        });
    }

    private Function<HttpServletRequest, Map<String, String>> validateFoodForm = request -> Validator
            .validateFormAndReturnErrors(request,
                    PropertiesContainer.JSONProperties.FOOD_FORM_VALIDATION_DATA.getFormFieldsValidationData());

    private Function<HttpServletRequest, Map<String, String>> validateUserSignUpForm = request -> Validator
            .validateFormAndReturnErrors(request,
                    PropertiesContainer.JSONProperties.USER_SIGN_UP_FORM_VALIDATION_DATA.getFormFieldsValidationData());
}