package Despegar.com.despegar.test;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import Despegar.com.despegar.config.Configuracion;
import Despegar.com.despegar.pages.Alojamientos;
import Despegar.com.despegar.pages.HomePage;
import Despegar.com.despegar.pages.ResultadosAlojamientos;

public class AlojamientosTest extends Configuracion {

	@Test(dataProvider = "alojamiento", dataProviderClass = DataProviderPrueba.class)
	public void testAlojamiento (Map<String, String> map) throws Exception {
		String origen = map.get("origen");
		String fechaIda = map.get("fechaIda");
		String fechaVuelta = map.get("fechaVuelta");
		HomePage home = new HomePage(driver);
		Alojamientos alojamientos = home.clickOnAlojamientos();
		alojamientos.busquedaAlojamiento(origen, fechaIda, fechaVuelta);
		ResultadosAlojamientos alojamientosResult = new ResultadosAlojamientos(driver);
		alojamientosResult.ordenarPorDropdown();
		alojamientosResult.selectComboBox("Precio: menor a mayor");
		
		//WebElement ordenarPor = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("sorting"))));
		//alojamientosResult.ordenarPorDropdown();
		//alojamientosResult.verMapaSideBar();
	}

}
