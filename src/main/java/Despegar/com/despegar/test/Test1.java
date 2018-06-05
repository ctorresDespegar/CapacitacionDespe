package Despegar.com.despegar.test;

import org.testng.annotations.Test;
import Despegar.com.despegar.config.Configuracion;
import Despegar.com.despegar.pages.HomePage;
import Despegar.com.despegar.pages.Vuelos;


public class Test1 extends Configuracion {
	
	@Test
	public void prueba() throws Exception {
		HomePage home = new HomePage(driver);
		Vuelos vuelos = home.clickOnVuelos();
		vuelos.busqueda("Roma", "Madrid", "16/08/2018", "19/08/2018");
		
	}

}
