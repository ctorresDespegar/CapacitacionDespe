package Despegar.com.despegar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ResultadosAlojamientos extends PageWeb {

	@FindBy(xpath = "//*[@id='sorting']")
	private WebElement ordenarPor;


	@FindBy(xpath = "//[@value='price_ascending'")
	private WebElement precioAscendiente;
	
	@FindBy (className = "hf-pricebox-detail-and-payment.col.-md-12.-eva-3-hide-small.hf-robot-see-detail")
	private WebElement verDetalleButton;

	public ResultadosAlojamientos(WebDriver driver) {
		super(driver);
	}

	public void ordernarDropDownClick() {
		ordenarPor.click();
	}

	public void ordenarPorDropdown() {
		ordenarPor.isDisplayed();
	}

	public void selectComboBox(String texto) {
		Select comboBox = new Select(ordenarPor);
		comboBox.selectByVisibleText(texto);
	}
	
	public VerDetallePage verDetalleClick() {
		verDetalleButton.click();
		return new VerDetallePage (driver);
	}

}