package fr.stemprado.apps.pimp.e2e;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Each test class implementing an e2e test has to inherit this class. It loads the webdriver only once for all tests in the class.
 * When the tests are done, the driver quits (the browser is closed so for another e2e class, we need to reload it). It's slower than
 * a test suite but it's easier to use. 
 * 
 * To execute e2e tests, the profile "e2e" is required and you need to provide as
 * JVM arguments :
 * <ul>
 * <li>host : the DNS/IP of the running application</li>
 * <li>port : the port of the running application</li>
 * <li>e2eBrowser : the browser to use (Chrome or Firefox)</li>
 * <li>webdriverPath : the path of the driver</li>
 * </ul>
 * example : mvn verify -Pe2e -Dhost=localhost -Dport=9296 -De2eBrowser=Chrome -DwebdriverPath="/path/to/chromedriver"
 * 
 * @author Sébastien Temprado
 *
 */
@RunWith(SpringRunner.class)
public abstract class AbstractE2ETest {
	
	/**
	 * DNS or IP + port of the runnin application 
	 * example : http://localhost:9296/
	 */
	protected static String baseUrl;
	
	protected static WebDriver driver;
	
	@BeforeClass
	public static void initWebDriver() throws E2EConfigurationException {
		final String host = System.getProperty("host");
		final String port = System.getProperty("port");
		final String e2eBrowser = System.getProperty("e2eBrowser");
		final String webdriverPath = System.getProperty("webdriverPath");

		if (e2eBrowser != null && webdriverPath != null && host != null && port != null) {
			baseUrl = "http://" + host + ":" + port + "/";
			switch (e2eBrowser) {
			case "Chrome":
				System.setProperty("webdriver.chrome.driver", webdriverPath);
				driver = new ChromeDriver();
				break;
			case "Firefox":
			default:
				System.setProperty("webdriver.gecko.driver", webdriverPath);
				driver = new FirefoxDriver();
			}
		} else {
			throw new E2EConfigurationException(
					"host, port, e2eBrowser and webdriverPath are required as JVM arguments");
		}
	}

	@AfterClass
	public static void after() {
		if (driver != null) {
			driver.quit();
		}
	}

}
