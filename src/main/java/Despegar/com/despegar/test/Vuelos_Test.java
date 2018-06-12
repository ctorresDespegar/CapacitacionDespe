package Despegar.com.despegar.test;

import Despegar.com.despegar.config.Configuracion;
import Despegar.com.despegar.pages.HomePage;
import Despegar.com.despegar.pages.Vuelos;
import java.util.Map;
import org.testng.annotations.Test;

public class Vuelos_Test extends Configuracion {

	@Test(dataProvider = "vuelos", dataProviderClass = DataProviderPrueba.class, groups = "PROBANDO_GRUPO", testName = "Buesqueda de Vuelos", description = "Esta es una prueba que realiza la busqueda de vuelos por parametros desde una Dataprovider")
	public void busquedaVuelosTest(Map<String, String> map) throws Exception {
		HomePage home = new HomePage(driver);
		Vuelos vuelos = home.clickOnVuelos();
		vuelos.busqueda(map.get("origen"), map.get("destino"), map.get("fechaOrigen"), map.get("fechaDestino"));
		Thread.sleep(10000);
	}
}
