package fr.stemprado.apps.pimp.test.matchers;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public class ErrorMatcher extends ModelResultMatchers {

	private ErrorMatcher() {
	}

	public static ErrorMatcher errors() {
		return new ErrorMatcher();
	}

	public ResultMatcher hasError(String attributeName, String expectedMessage) {
		return result -> {
			BindingResult bindingResult = getBindingResult(result.getModelAndView(), attributeName);
			bindingResult.getAllErrors().stream().filter(oe -> attributeName.equals(oe.getObjectName()))
					.forEach(oe -> assertEquals("Expected default message", expectedMessage, oe.getDefaultMessage()));
		};
	}

	private BindingResult getBindingResult(ModelAndView mav, String name) {
		BindingResult result = (BindingResult) mav.getModel().get(BindingResult.MODEL_KEY_PREFIX + name);
		assertTrue("No BindingResult for attribute: " + name, result != null);
		assertTrue("No errors for attribute: " + name, result.getAllErrors().size() > 0);
		return result;
	}
}
