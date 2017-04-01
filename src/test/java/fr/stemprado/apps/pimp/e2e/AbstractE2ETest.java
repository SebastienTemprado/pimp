package fr.stemprado.apps.pimp.e2e;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Each test class implementing an e2e test has to inherit this class.
 * To execute e2e tests, the profile "e2e" is required and you need to provide as JVM arguments :
 * <ul>
 * 	<li>e2eBrowser : the browser to use (Chrome or Firefox)</li>
 * 	<li>webdriverPath : the path of the driver</li>
 * </ul>
 * example : mvn verify -Pe2e -De2eBrowser=Chrome -DwebdriverPath="/path/to/chromedriver"
 * 
 * @author Sébastien Temprado
 *
 */
@RunWith(SpringRunner.class)
public abstract class AbstractE2ETest {
	
	protected WebDriver getWebDriver() throws E2EConfigurationException {
		final String e2eBrowser = System.getProperty("e2eBrowser");
		final String webdriverPath = System.getProperty("webdriverPath");
		
		WebDriver driver = null;
		
		if (e2eBrowser != null && webdriverPath != null) {
			switch(e2eBrowser) {
				case "Chrome":
					System.setProperty("webdriver.chrome.driver", webdriverPath);
					driver = new ChromeDriver();
					break;
				case "Firefox":
				default:
					System.setProperty("webdriver.gecko.driver", webdriverPath);
					driver = new FirefoxDriver();
			}
		}
		else {
			throw new E2EConfigurationException("e2eBrowser and webdriverPath are required as JVM arguments");
		}
		
		return driver;
	}
}
