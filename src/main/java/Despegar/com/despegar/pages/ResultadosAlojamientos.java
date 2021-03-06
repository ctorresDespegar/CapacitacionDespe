package Despegar.com.despegar.pages;

import java.util.List;

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

	@FindBy(className = "btn-text")
	private WebElement verDetalleButton;
	
	@FindBy(xpath = "//*[@class='btn-text' and text()='Ver detalle' or text()='Veja mais']")
	  private List<WebElement> seeDetailList;

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

	public VerDetallePage verDetalleClick() throws InterruptedException {
		Thread.sleep(10000);
		//verDetalleButton.click();
		seeDetailList.get(1).click();
		switchNewWindows(true);
		return new VerDetallePage(driver);
	}

}