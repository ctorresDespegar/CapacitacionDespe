package Despegar.com.despegar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VerDetallePage extends PageWeb{

	public VerDetallePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (xpath = ".//*[@id='hf-buy-button']")
	private WebElement reservarAhora;
	
	public PagosPage reservarAhoraClick () {
		reservarAhora.click();
		return new PagosPage(driver);
	}
	

}
