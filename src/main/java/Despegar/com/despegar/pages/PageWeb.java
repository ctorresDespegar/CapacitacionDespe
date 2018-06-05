package Despegar.com.despegar.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CalendarUtil;

/**
 * Clase Principal de Page Object
 *
 */
public class PageWeb {

	@FindBy(xpath = "//*[@class='sbox-button-default sbox-max-width-container']"
			+ "//*[@class='sbox-3-btn -secondary -md sbox-search']")
	protected WebElement buscarBtn;

	@FindBy(xpath = "//*[@class='input-container sbox-checkin-date-container']")
	protected WebElement entryDate;

	@FindBy(className = "_dpmg2--controls-next")
	protected WebElement nextMonth;

	@FindBy(xpath = ".//*[@class='ac-group-items' or @class='geo-autocomplete-list']")
	private WebElement autoCompleteList;

	protected WebDriver driver;

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public PageWeb(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private void setDateEntry(String dateEntry) throws Exception {
		CalendarUtil calendar = new CalendarUtil(dateEntry);
		entryDate.click();
		for (int i = 0; i < calendar.getMonth(); i++) {
			if (calendar.isIncrement()) {
				nextMonth.click();
			}
		}
		driver.findElement(By.xpath("//div[" + (calendar.getMonth() + 1) + "]/div[4]/span[" + calendar.getDay() + "]"))
				.click();
	}

	private void setDateDeparture(String dateEntry, String dateDeparture) throws Exception {
		CalendarUtil calendar = new CalendarUtil(dateEntry);
		int monthEntry = calendar.getMonth();
		int dayEntry = calendar.getDay();
		calendar = new CalendarUtil(dateDeparture);
		int monthDeparture = calendar.getMonth();
		int dayDeparture = calendar.getDay();

		if (monthDeparture - monthEntry == 0 && dayDeparture > dayEntry) {
			driver.findElement(
					By.xpath("//div[" + (calendar.getMonth() + 1) + "]/div[4]/span[" + calendar.getDay() + "]"))
					.click();
			return;
		}

		if (monthDeparture - monthEntry == 1 && dayDeparture < dayEntry) {
			for (int i = 0; i < (monthDeparture - monthEntry); i++) {
				if (calendar.isIncrement()) {
					nextMonth.click();
				}
			}
			driver.findElement(
					By.xpath("//div[" + (calendar.getMonth() + 1) + "]/div[4]/span[" + calendar.getDay() + "]"))
					.click();
		} else {
			throw new Exception("No se pueden realizar una reserva por mas de 1 mes....");
		}
	}

	/**
	 * Metodo que selecciona las Fechas del calendario
	 * 
	 * @param dateEntry
	 * @param dateDeparture
	 * @throws Exception
	 */
	protected void fechas(String dateEntry, String dateDeparture) throws Exception {
		setDateEntry(dateEntry);
		setDateDeparture(dateEntry, dateDeparture);
	}

	/**
	 * Metodo para seleccionar una opcion de la lista desplegable
	 * 
	 * @param list
	 *            Lista de Opciones
	 * @param option
	 *            String opcion que se quiere seleccionar
	 * @return Elemento que contiene la opcion
	 * @throws Exception
	 */
	protected WebElement selectOption(List<WebElement> list, String option) throws Exception {
		if (!list.isEmpty() && !option.isEmpty()) {
			for (WebElement webElement : list) {
				if (webElement.getText().toUpperCase().contains(option.toUpperCase())) {
					return webElement;
				}
			}
		}
		throw new Exception("Error al querer seleccionar un Elemento de Lista");
	}

	/**
	 * Devueleve la Lista desplegable de Origen y destino
	 * 
	 * @return Lista de elementos
	 * @throws Exception
	 */
	protected List<WebElement> getListCitiesOrAirports() throws Exception {
		Thread.sleep(2000);
		return autoCompleteList.findElements(By.tagName("li"));
	}
}
