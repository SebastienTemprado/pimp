package fr.stemprado.apps.pimp.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticationE2E extends AbstractE2ETest {
	
	@Test
	public void hasToSignIn() {
		// try to access a page without being authenticated
		driver.get(baseUrl + "/pimp");
		// forward to Login page
	    assertThat(driver.getTitle()).isEqualTo("Pimp - Login");
	    WebElement username = (new WebDriverWait(driver, 10))
	    		  .until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	    username.sendKeys("user");
	    WebElement password = driver.findElement(By.id("password"));
	    // wrong password 
	    password.sendKeys("pass");
	    WebElement submit = driver.findElement(By.id("signIn"));
	    submit.click();
	    // wrong password : stay on the login page
	    assertThat(driver.getTitle()).isEqualTo("Pimp - Login");
	    username = (new WebDriverWait(driver, 10))
	    		  .until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	    username.sendKeys("user");
	    password = driver.findElement(By.id("password"));
	    password.sendKeys("password");
	    submit = driver.findElement(By.id("signIn"));
	    submit.click();
	    new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Pimp"));
	    // access granted
	    assertThat(driver.getTitle()).isEqualTo("Pimp");
	}

}
