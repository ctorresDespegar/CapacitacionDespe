package Despegar.com.despegar.test;

import java.util.Map;
import org.testng.annotations.Test;
import Despegar.com.despegar.config.Configuracion;
import Despegar.com.despegar.pages.HomePage;
import Despegar.com.despegar.pages.Vuelos;


public class Test1 extends Configuracion {
	
	@Test(dataProvider = "probando", dataProviderClass = DataProviderPrueba.class)
	public void prueba(Map<String,String> map) throws Exception {
		String origen = map.get("origen");
		String destino = map.get("destino");
		HomePage home = new HomePage(driver);
		Vuelos vuelos = home.clickOnVuelos();
		vuelos.busqueda(origen, destino, "16/08/2018", "16/08/2018");
	}
}
