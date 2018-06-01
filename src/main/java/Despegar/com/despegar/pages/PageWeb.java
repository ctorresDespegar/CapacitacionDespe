package Despegar.com.despegar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CalendarUtil;

public class PageWeb {
	
	@FindBy(xpath="//*[@class='sbox-button-default sbox-max-width-container']"
			+ "//*[@class='sbox-3-btn -secondary -md sbox-search']")
	protected WebElement buscarBtn;
	
	  @FindBy(xpath = "//*[@class='input-container sbox-checkin-date-container']")
	  protected WebElement entryDate;
	  
	  @FindBy(className = "_dpmg2--controls-next")
	  protected WebElement nextMonth;
	
	protected WebDriver driver;
	
	
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
	        driver.findElement(By.xpath("//div[" + (calendar.getMonth() + 1) + "]/div[4]/span["
	    + calendar.getDay() + "]")).click();
	  }
	
	private void setDateDeparture(String dateEntry, String dateDeparture) throws Exception {
	    CalendarUtil calendar = new CalendarUtil(dateEntry);
	    int monthEntry = calendar.getMonth();
	    int dayEntry = calendar.getDay();
	    calendar = new CalendarUtil(dateDeparture);
	    int monthDeparture = calendar.getMonth();
	    int dayDeparture = calendar.getDay();

	    if (monthDeparture - monthEntry == 0 && dayDeparture > dayEntry) {
	      driver.findElement(By.xpath("//div[" + (calendar.getMonth() + 1) + "]/div[4]/span[" + calendar.getDay() + "]")).click();
	      return;
	    }

	    if (monthDeparture - monthEntry == 1 && dayDeparture < dayEntry) {
	      for (int i = 0; i < (monthDeparture - monthEntry); i++) {
	        if (calendar.isIncrement()) {
	          nextMonth.click();
	        }
	      }
	      driver.findElement(By.xpath(
	          "//div[" + (calendar.getMonth() + 1) + "]/div[4]/span[" + calendar.getDay() + "]")).click();
	    } else {
	      throw new Exception("No se pueden realizar una reserva por mas de 1 mes....");
	    }
	  }
	
	protected void fechas(String dateEntry,String dateDeparture) throws Exception {
		setDateEntry(dateEntry);
		setDateDeparture(dateEntry, dateDeparture);
	}
}
