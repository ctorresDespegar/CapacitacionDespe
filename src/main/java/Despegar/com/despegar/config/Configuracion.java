package Despegar.com.despegar.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Configuracion {

	protected static WebDriver driver;
	private static final String CHROME_KEY = "webdriver.chrome.driver";
	private static final String FIREFOX_KEY = "webdriver.gecko.driver";
	private static final String IE_KEY = "webdriver.ie.driver";
	private static final String EDGE_KEY = "webdriver.edge.driver";
	private ChromeOptions chromeOptions;
	protected WebDriverWait wait;

	@BeforeMethod
	public void setUp() throws Exception {
		browser(browsers.CHROME);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (osName().equals("mac")) {
			driver.manage().window().fullscreen();
		} else {
			driver.manage().window().maximize();
		}
		wait = new WebDriverWait(driver, 10);
		driver.get("https://www.despegar.com.ar/");
		driver.navigate().refresh();
	}

	private String osName() throws Exception {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("mac")) {
			return "mac";
		}
		if (os.contains("nux") || os.contains("nix")) {
			return "linux";
		}
		throw new Exception("No se puede ejecutar esta prueba en este sistema operativo.... " + "PD:  compre una  MAC");
	}

	private void browser(browsers browser) throws Exception {
		switch (browser) {
		case CHROME:
			System.setProperty(CHROME_KEY, System.getProperty("user.dir") + "/drivers/" + osName() + "/chromedriver");
			driver = new ChromeDriver();
			break;

		case FIREFOX:
			System.setProperty(FIREFOX_KEY, System.getProperty("user.dir") + "/drivers/" + osName() + "/geckodriver");
			driver = new FirefoxDriver();
			break;

		default:
			throw new Exception("El Browser seleccionando no esta disponible para esta prueba");

		}

	}

	private void cleanUp() {
		driver.manage().deleteAllCookies();
	}

	private void tearDown() {
		cleanUp();
		driver.close();
		driver.quit();
	}

	public enum browsers {
		CHROME, FIREFOX
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_dd_MM_HH_mm_ss_SS");
			String attributeValue = result.getMethod().getMethodName() + dateFormat.format(new Date());
			result.setAttribute("IdTestCase", attributeValue);
			try {
				ExtentReportListener.captureScreenshot(driver, attributeValue);
			} catch (Exception ex) {
				System.out.println(
						"Hubo un error al sacar screenshot. Probablemente el browser este cerrado. Error : " + ex);
			}
		}
		tearDown();
	}

}
