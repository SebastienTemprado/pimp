package fr.stemprado.apps.pimp.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import fr.stemprado.apps.pimp.constants.api.AuthenticationApi;
import fr.stemprado.apps.pimp.constants.api.HomeApi;

public class AuthenticationE2E extends AbstractE2ETest {
	
	@Test
	public void hasToSignIn() {
		// try to access a page without being authenticated
		driver.get(baseUrl + HomeApi.PIMP);
		// forward to Login page
	    assertThat(driver.getTitle()).isEqualTo("Pimp - Login");
	    
	    // try to login with a wrong password
	    WebElement username = driverWaiting().until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	    username.sendKeys("user");
	    WebElement password = driver.findElement(By.id("password"));
	    // wrong password 
	    password.sendKeys("pass");
	    WebElement submit = driver.findElement(By.id("signIn"));
	    submit.click();
	    // wrong password : stay on the login page
	    assertThat(driver.getTitle()).isEqualTo("Pimp - Login");
	    
	    // try to login = OK
	    username = driverWaiting().until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	    username.sendKeys("user");
	    password = driver.findElement(By.id("password"));
	    password.sendKeys("password");
	    submit = driver.findElement(By.id("signIn"));
	    submit.click();
	    driverWaiting().until(ExpectedConditions.titleIs("Pimp"));
	    // access granted
	    assertThat(driver.getTitle()).isEqualTo("Pimp");
	}
	
	@Test
	public void signup() throws E2EConfigurationException {
		// restart the driver to logout
		restartDriver();
		driver.get(baseUrl + AuthenticationApi.LOGIN);
		// Sign up
	    WebElement signupButton = driverWaiting().until(ExpectedConditions.presenceOfElementLocated(By.id("signup")));
	    signupButton.click();
	    assertThat(driver.getTitle()).isEqualTo("Pimp - Sign up");
	}

}
