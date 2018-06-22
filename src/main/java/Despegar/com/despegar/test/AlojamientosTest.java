package Despegar.com.despegar.test;

import static org.testng.Assert.assertEquals;


import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import Despegar.com.despegar.config.Configuracion;
import Despegar.com.despegar.pages.Alojamientos;
import Despegar.com.despegar.pages.FinalizarPage;
import Despegar.com.despegar.pages.HomePage;
import Despegar.com.despegar.pages.PagosPage;
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
		PagosPage pagos = verDetalle.reservarAhoraClick();
		FinalizarPage finaliza = pagos.comprarClick();
		Assert.assertEquals("Último paso: ¡Asegure su habitación ahora!", finaliza.headerUltimoPaso);
		
		//WebElement ordenarPor = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("sorting"))));
		//alojamientosResult.ordenarPorDropdown();
		//alojamientosResult.verMapaSideBar();
	}

}
