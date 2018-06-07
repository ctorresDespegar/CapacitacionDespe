package Despegar.com.despegar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Alojamientos extends PageWeb {

	@FindBy(xpath = "//*[@class='input-tag sbox-main-focus sbox-destination sbox-primary undefined']")
	private WebElement destino;

	@FindBy(xpath = "//*[@class='sbox-dates-distri-button-container']//*[@class='sbox-3-btn -secondary -md sbox-search']")
	protected WebElement buscarButton;

	public Alojamientos(WebDriver driver) {
		super(driver);
	}

	public ResultadosAlojamientos busquedaAlojamiento(String destination, String fechaSalida, String fechaRetorno)
			throws Exception {
		destino.clear();
		destino.sendKeys(destination);
		selectOption(getListCitiesOrAirports(), destination).click();
		fechas(fechaSalida, fechaRetorno);
		waitElementIsClickable(buscarButton).click();
		return new ResultadosAlojamientos(driver);

	}

}
