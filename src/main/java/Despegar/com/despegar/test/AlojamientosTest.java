package Despegar.com.despegar.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import Despegar.com.despegar.config.Configuracion;
import Despegar.com.despegar.pages.Alojamientos;
import Despegar.com.despegar.pages.HomePage;
import Despegar.com.despegar.pages.ResultadosAlojamientos;

public class AlojamientosTest extends Configuracion {

	@Test
	public void testAlojamiento() throws Exception {
		HomePage home = new HomePage(driver);
		Alojamientos alojamientos = home.clickOnAlojamientos();
		alojamientos.busquedaAlojamiento("Roma", "16/08/2018", "19/08/2018");
		ResultadosAlojamientos alojamientosResult = new ResultadosAlojamientos(driver);
		alojamientosResult.ordenarPorDropdown();
		alojamientosResult.selectComboBox("Precio: menor a mayor");
		
		//WebElement ordenarPor = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("sorting"))));
		//alojamientosResult.ordenarPorDropdown();
		//alojamientosResult.verMapaSideBar();
	}

}
