package fr.stemprado.apps.pimp.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class AuthenticationE2E extends AbstractE2ETest {
	
	@Test
	public void hasToSignIn() {
		 
	     driver.get(baseUrl + "/pimp");
//	     // Enter something to search for
//	     element.sendKeys("Cheese!");
	//
//	     // Now submit the form. WebDriver will find the form for us from the element
//	     element.submit();

	     assertThat(driver.getTitle()).isEqualTo("Pimp - Login");
//	     WebElement element = driver.findElement(By.name("q"));
	}

}
