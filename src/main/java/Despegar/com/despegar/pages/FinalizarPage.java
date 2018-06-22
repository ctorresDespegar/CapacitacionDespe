package Despegar.com.despegar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinalizarPage extends PageWeb {

	public FinalizarPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(className = "checkout-header")
	public WebElement headerUltimoPaso;

	public void tituloHeader() {
		headerUltimoPaso.getText();
	}

}
