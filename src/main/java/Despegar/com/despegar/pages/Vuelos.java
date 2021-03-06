package Despegar.com.despegar.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Vuelos extends PageWeb {

	@FindBy(xpath = "//*[@class='radio-circle sbox-radio-circle']")
	private List<WebElement> opcionsFlights;

	@FindBy(xpath = "//*[@class='input-tag sbox-main-focus sbox-bind-reference-flight-roundtrip-origin-input "
			+ "sbox-primary sbox-places-first places-inline']")
	private WebElement origen;

	@FindBy(xpath = "//*[@class='input-tag sbox-main-focus sbox-bind-reference-flight-roundtrip-destination-input"
			+ " sbox-secondary sbox-places-second places-inline']")
	private WebElement destino;
	
	@FindBy(xpath = "//*[@class='sbox-button-default']//*[@class='sbox-3-btn -secondary -md sbox-search']//em[@class='btn-text']")
	protected WebElement buscarBtn;

	public Vuelos(WebDriver driver) {
		super(driver);
	}

	public ResultadosVuelos busqueda(String origin, String destination, String fechaSalida, String fechaRetorno)
			throws Exception {
		origen.clear();
		origen.sendKeys(origin);
		selectOption(getListCitiesOrAirports(), origin).click();
		destino.clear();
		destino.sendKeys(destination);
		selectOption(getListCitiesOrAirports(), destination).click();
		fechas(fechaSalida, fechaRetorno);
		wait.until(ExpectedConditions.elementToBeClickable(buscarBtn)).click();
		return new ResultadosVuelos(driver);
	}

}
