package Despegar.com.despegar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageWeb {

	@FindBy(xpath = "//*[contains(@class, 'nevo-header-navigation-menu-item nevo-js-header-menu-item')]"
			+ "//*[@class='nevo-header-navigation-menu-text' and text()='Passagens' or text()='Vuelos']")
	private WebElement vuelos;

	@FindBy(xpath = "//*[@class='nevo-header-navigation-menu-item nevo-js-header-menu-item HOTELS nevo--active']")
	private WebElement alojamiento;

	@FindBy(xpath = "//*[@class='nevo-header-navigation-menu-item nevo-js-header-menu-item PACKAGES']")
	private WebElement paquetes;

	@FindBy(xpath = "//[@class='nevo-header-navigation-menu-item nevo-js-header-menu-item CARS']")
	private WebElement autos;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Este metodo lleva al formulario de busqueda de Alojamientos.
	 * 
	 * @return {@link Alojamientos} Pagina de alojamientos inicializada
	 */
	public Alojamientos clickOnAlojamientos() {
		alojamiento.click();
		return new Alojamientos(driver);
	}

	/**
	 * Este metodo lleva al formulario de Autos
	 * 
	 * @return {@link Autos} Pagina de autos
	 */
	public Autos clickOnAutos() {
		autos.click();
		return new Autos(driver);
	}

	/**
	 * Este metodo lleva al formulario de Vuelos
	 * 
	 * @return {@link Vuelos} Pagina de vuelos
	 */
	public Vuelos clickOnVuelos() {
		vuelos.click();
		return new Vuelos(driver);
	}

	/**
	 * Este metodo lleva al formulario de Paquetes
	 * 
	 * @return {@link Paquetes} Pagina de Paquetes
	 */
	public Paquetes clickOnPaquetes() {
		paquetes.click();
		return new Paquetes(driver);
	}

}
