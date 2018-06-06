package Despegar.com.despegar.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Alojamientos extends PageWeb {

	@FindBy(xpath = "//*[@class='input-tag sbox-main-focus sbox-destination sbox-primary undefined']")
	private WebElement destino;

	public Alojamientos(WebDriver driver) {
		super(driver);
	}

	public ResultadosAlojamientos busquedaAlojamiento(String destination, String fechaSalida, String fechaRetorno)
			throws Exception {
		// destino.clear();
		destino.sendKeys(destination);
		selectOption(getListCitiesOrAirports(), destination).click();
		fechas(fechaSalida, fechaRetorno);
		wait.until(ExpectedConditions.elementToBeClickable(buscarBtn)).click();
		return new ResultadosAlojamientos(driver);

	}
}
