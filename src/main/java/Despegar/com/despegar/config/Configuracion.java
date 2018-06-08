package Despegar.com.despegar.config;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;



public class Configuracion {
	
	protected static WebDriver driver;
	private static final String CHROME_KEY = "webdriver.chrome.driver";
    private static final String FIREFOX_KEY = "webdriver.gecko.driver";
    private static final String IE_KEY = "webdriver.ie.driver";
    private static final String EDGE_KEY = "webdriver.edge.driver";
    private ChromeOptions chromeOptions;
    protected WebDriverWait wait;
    

	@BeforeClass
	public void setUp() throws Exception{
		browser(browsers.CHROME);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	    wait = new WebDriverWait(driver,10);
		driver.get("https://www.despegar.com.ar/");
		driver.navigate().refresh();
	}
	
	
	private String osName() throws Exception {
		String os = System.getProperty("os.name").toLowerCase();
		if(os.contains("mac")) {
			return "mac";
		}
		if(os.contains("nux") || os.contains("nix")) {
			return "linux";
		}
		throw new Exception("No se puede ejecutar esta prueba en este sistema operativo.... "
				+ "PD:  compre una  MAC");
	}

	private void browser(browsers browser) throws Exception {
		switch (browser) {
		case CHROME:
			System.setProperty(CHROME_KEY, 
					System.getProperty("user.dir") +
					"/drivers/" + osName() + "/chromedriver");
			driver = new ChromeDriver();
			break;
			
		case FIREFOX:
			System.setProperty(FIREFOX_KEY, 
					System.getProperty("user.dir") + 
					"/drivers/" + osName() + "/geckodriver");
			driver= new FirefoxDriver();
			break;
			
			default: 
				throw new Exception("El Browser seleccionando no esta disponible para esta prueba");
			
		}
	
	}
	
	
	private void cleanUp(){
		driver.manage().deleteAllCookies();
	}
	
	@AfterClass
	public void tearDown(){
		cleanUp();
		driver.close();
		driver.quit();
	}
	
	
	public enum browsers{
		  CHROME,
		  FIREFOX
	}
}
