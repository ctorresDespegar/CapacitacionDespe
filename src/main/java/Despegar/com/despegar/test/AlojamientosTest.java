package Despegar.com.despegar.test;

import org.testng.annotations.Test;

import Despegar.com.despegar.config.Configuracion;
import Despegar.com.despegar.pages.Alojamientos;
import Despegar.com.despegar.pages.HomePage;
import Despegar.com.despegar.pages.Vuelos;

public class AlojamientosTest extends Configuracion{
	
	@Test 
	public void testAlojamiento() throws Exception {
		HomePage home = new HomePage(driver);
		Alojamientos alojamientos = home.clickOnAlojamientos();
		alojamientos.busquedaAlojamiento("Roma", "16/08/2018", "19/08/2018");
		
	}

}
