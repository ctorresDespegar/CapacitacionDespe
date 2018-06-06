package Despegar.com.despegar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultadosAlojamientos extends PageWeb{
	
	@FindBy (id = "mapResult")
	private WebElement mapa;

	public ResultadosAlojamientos(WebDriver driver) {
		super(driver);
	}

	public boolean verMapaSideBar() {
		mapa.isDisplayed();
		return true;
	}


}
