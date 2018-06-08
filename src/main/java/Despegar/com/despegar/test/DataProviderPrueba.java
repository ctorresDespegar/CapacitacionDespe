package Despegar.com.despegar.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;

public class DataProviderPrueba {

  @DataProvider(name = "probando", parallel = true)
  public Iterator<Object[]> probandoMetodo() throws Exception {
    String dataJson = "testdata.json";

//    URL resource = this.getClass().getResource("/");
//    String path = Paths.get(resource.toURI()).toFile().getAbsolutePath();
    String path = System.getProperty("user.dir") +"/src/main/resources";
    String absolutePath = path + "/" + dataJson;

    String dataFile = null;

    try {
      dataFile = new String(Files.readAllBytes(Paths.get(absolutePath)), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new Exception("Error al leer dataprovider");
    }

    List<Map<String, String>> resultMap;
    ObjectMapper mapperObj = new ObjectMapper();

    try {
      resultMap = mapperObj.readValue(dataFile,
          new TypeReference<List<HashMap<String, String>>>() {
          });
      System.out.println("Output Map: " + resultMap);
    } catch (IOException e) {
      throw new Exception("Imposible convertir json a map");
    }

    Collection<Object[]> dp = new ArrayList<Object[]>();
    for(Map<String,String> map:resultMap){
      dp.add(new Object[]{map});
    }

    return dp.iterator();
  }
}
