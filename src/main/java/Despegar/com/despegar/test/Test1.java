package Despegar.com.despegar.test;

import Despegar.com.despegar.config.Configuracion;
import Despegar.com.despegar.pages.HomePage;
import Despegar.com.despegar.pages.Vuelos;
import java.util.Map;
import org.testng.annotations.Test;


public class Test1 extends Configuracion {

  @Test(dataProvider = "probando", dataProviderClass = DataProviderPrueba.class, groups = "PROBANDO_GRUPO")
  public void prueba(Map<String, String> map) throws Exception {
    String origen = map.get("origen");
    String destino = map.get("destino");
    HomePage home = new HomePage(driver);
    Vuelos vuelos = home.clickOnVuelos();
    vuelos.busqueda("Roma", "Madrid", "16/08/2018", "19/08/2018");
    Thread.sleep(10000);
  }

}
