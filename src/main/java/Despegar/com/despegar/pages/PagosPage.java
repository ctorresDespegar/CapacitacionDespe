package Despegar.com.despegar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PagosPage extends PageWeb {

	public PagosPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(className = "xs-buy-button")
	private WebElement comprarButton;

	public FinalizarPage comprarClick() {
		comprarButton.click();
		return new FinalizarPage(driver);
	}

}
