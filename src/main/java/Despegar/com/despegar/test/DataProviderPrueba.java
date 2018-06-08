package Despegar.com.despegar.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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

	private String jsonName;

	@DataProvider(name = "alojamiento", parallel = false)
	public Iterator<Object[]> dataAlojamiento() throws Exception {
		jsonName = "data.json";
		return probandoMetodo();

	}

	@DataProvider(name = "probando", parallel = false)
	public Iterator<Object[]> probando() throws Exception {
		jsonName = "testdata.json";
		return probandoMetodo();

	}

	private Iterator<Object[]> probandoMetodo() throws Exception {

		// URL resource = this.getClass().getResource("/");
		// String path = Paths.get(resource.toURI()).toFile().getAbsolutePath();
		String path = System.getProperty("user.dir") + "/src/main/resources";
		String absolutePath = path + "/" + jsonName;

		String dataFile = null;

		try {
			dataFile = new String(Files.readAllBytes(Paths.get(absolutePath)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new Exception("Error al leer dataprovider");
		}

		List<Map<String, String>> resultMap;
		ObjectMapper mapperObj = new ObjectMapper();

		try {
			resultMap = mapperObj.readValue(dataFile, new TypeReference<List<HashMap<String, String>>>() {
			});
			System.out.println("Output Map: " + resultMap);
		} catch (IOException e) {
			throw new Exception("Imposible convertir json a map");
		}

		Collection<Object[]> dp = new ArrayList<Object[]>();
		for (Map<String, String> map : resultMap) {
			dp.add(new Object[] { map });
		}

		return dp.iterator();
	}
}
