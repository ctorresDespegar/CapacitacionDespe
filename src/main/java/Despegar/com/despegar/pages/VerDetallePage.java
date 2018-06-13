package Despegar.com.despegar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class VerDetallePage extends PageWeb{

	public VerDetallePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (className = "hf-pricebox-detail-and-payment.col.-md-12.-eva-3-hide-small.hf-robot-see-detail")
	private WebDriver precio;
	

}
