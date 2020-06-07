package net.shvdy.nutrition_tracker;

import net.shvdy.nutrition_tracker.controller.command.utils.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

/**
 * 02.06.2020
 *
 * @author Dmitriy Storozhenko
 * @version 1.0
 */


public class ValidatorTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;

    @BeforeAll
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PropertiesContainer.readProperties(getClass().getClassLoader());
    }

    @Test
    public void test() throws Exception {

        when(request.getParameter("newFoodName")).thenReturn("Chocolate");
        when(request.getParameter("newFoodProt")).thenReturn("22");
        when(request.getParameter("newFoodProt")).thenReturn("22");
        when(request.getParameter("newFoodProt")).thenReturn("22");
        when(request.getParameter("newFoodProt")).thenReturn("22");
        when(request.getParameter("rememberMe")).thenReturn("Y");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/HelloWorld.do")).thenReturn(rd);


        Map<String, String> errors = Validator.validateFormAndReturnErrors(request,
                PropertiesContainer.JSONProperties.FOOD_FORM_VALIDATION_DATA.getFormFieldsValidationData());


        assertNull(errors.get("newFoodName"));

    }
}
