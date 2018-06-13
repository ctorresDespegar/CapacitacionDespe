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
import Despegar.com.despegar.pages.VerDetallePage;

public class AlojamientosTest extends Configuracion {

	@Test(dataProvider = "alojamiento", dataProviderClass = DataProviderPrueba.class)
	public void testAlojamiento (Map<String, String> map) throws Exception {
		HomePage home = new HomePage(driver);
		Alojamientos alojamientos = home.clickOnAlojamientos();
		alojamientos.busquedaAlojamiento(map.get("origen"), map.get("fechaIda"), map.get("fechaVuelta"));
		ResultadosAlojamientos alojamientosResult = new ResultadosAlojamientos(driver);
		alojamientosResult.ordenarPorDropdown();
		alojamientosResult.selectComboBox("Precio: menor a mayor");
		VerDetallePage verDetalle = alojamientosResult.verDetalleClick();
		
		
		//WebElement ordenarPor = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("sorting"))));
		//alojamientosResult.ordenarPorDropdown();
		//alojamientosResult.verMapaSideBar();
	}

}