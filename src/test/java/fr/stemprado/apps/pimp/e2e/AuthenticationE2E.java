package fr.stemprado.apps.pimp.e2e;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes={AuthenticationE2E.class})
public class AuthenticationE2E extends AbstractE2ETest {
	
	@Test
	public void hasToSignIn() throws E2EConfigurationException {
		 WebDriver driver = getWebDriver();
	     driver.get("http://www.google.com");
	//
//	     // Find the text input element by its name
//	     WebElement element = driver.findElement(By.name("q"));
	//
//	     // Enter something to search for
//	     element.sendKeys("Cheese!");
	//
//	     // Now submit the form. WebDriver will find the form for us from the element
//	     element.submit();

	     // Should see: "cheese! - Google Search"
	     System.out.println("Page title is: " + driver.getTitle());
	     
	     //Close the browser
	     driver.quit();
	}
	
}
