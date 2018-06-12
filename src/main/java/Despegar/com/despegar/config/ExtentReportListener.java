package Despegar.com.despegar.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

/**
 * Created by it on 17/07/17.
 */
public class ExtentReportListener implements IReporter {

  private static final String FILE_NAME = "/report.html";
  private static final String OUTPUT_FILES_FOLDER = "/target/outputfiles";
  private static final String OUTPUT_REPORTS_FOLDER = "/outputreports";
  private static final String OUTPUT_FOLDER = OUTPUT_FILES_FOLDER + OUTPUT_REPORTS_FOLDER;
  private static final String SCREENSHOT_PATH_FOLDER = "/target/outputfiles/errorscreenshotfiles/";
  private static final String SCREENSHOT_PATH = "../errorscreenshotfiles/";
  private static Map<String, String> screenshotList = new HashMap<>();
  private ExtentReports extent;
  private static String baseDir;

  public ExtentReportListener() {
    setBaseDir(System.getProperty("user.dir"));
  }

  public static void setBaseDir(String baseDir) {
    ExtentReportListener.baseDir = baseDir;
  }

  /**
   * Metodo que captura pantalla cuando falla un caso de prueba.
   *
   * @param driver WebDriver (appiumDriver tambien)
   * @param uniqueId id unico del screenshot formado por nombre del metodo + fecha con segundos y
   * milisegundos
   * @throws IOException Excepcion si no puede crear el reporte ni la imagen
   * @author maximiliano.grajales
   */
  public static void captureScreenshot(WebDriver driver, String uniqueId) throws IOException {
    createOutputReportFolder();
    TakesScreenshot ts = (TakesScreenshot) driver;
    File source = ts.getScreenshotAs(OutputType.FILE);
    String imageFilename = uniqueId + ".png";
    String dest = String
        .format("%s%s%s", baseDir, SCREENSHOT_PATH_FOLDER,
            imageFilename);
    File destination = new File(dest);
    FileUtils.copyFile(source, destination);
    screenshotList.put(uniqueId, String.format("%s%s", SCREENSHOT_PATH, imageFilename));
  }

  /**
   * Metodo que crea la carpeta para almacenar los reportes.
   *
   * @author maximiliano.grajales
   */
  private static void createOutputReportFolder() {
    File dir = new File(
        String.format("%s%s%s", baseDir, OUTPUT_FILES_FOLDER, OUTPUT_REPORTS_FOLDER));
    if (!dir.exists()) {
      dir.mkdirs();
    }
  }

  /**
   * Metodo que genera el reporte.
   *
   * @param xmlSuites xml suites de pruebas.
   * @param suites lista de las suites
   * @param outputDirectory directorio donde va a escribir el reporte
   * @author maximiliano.grajales
   */
  @Override
  public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
      String outputDirectory) {
    init();

    boolean sendReportMail = false;

    for (ISuite suite : suites) {
      Map<String, ISuiteResult> result = suite.getResults();

      for (ISuiteResult r : result.values()) {
        ITestContext context = r.getTestContext();

        buildTestNodes(context.getPassedTests(), Status.PASS);
        buildTestNodes(context.getFailedTests(), Status.FAIL);
        buildTestNodes(context.getSkippedTests(), Status.SKIP);

        if (context.getFailedTests().size() > 0) {
          sendReportMail = true;
        }
      }
    }

    for (String s : Reporter.getOutput()) {
      extent.setTestRunnerOutput(s);
    }

    extent.flush();
  }

  /**
   * Metodo que inicia la creacion del reporte.
   *
   * @author maximiliano.grajales
   */
  private void init() {
    createOutputReportFolder();
    String browser = StringUtils.upperCase(System.getProperty("browser"));
    String branch = StringUtils.upperCase(System.getProperty("branch"));
    ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(
        baseDir + OUTPUT_FOLDER + FILE_NAME);

    extentHtmlReporter.config().setDocumentTitle("Reporte de Testing");
    extentHtmlReporter.config().setReportName(
        "Información de la ejecución = Versión del proyecto: " + " - Browser: " + browser +
            " - Branch: " + branch);
    extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    extentHtmlReporter.config().setTheme(Theme.STANDARD);
    extentHtmlReporter.config().setEncoding("UTF-8");

    extent = new ExtentReports();
    extent.attachReporter(extentHtmlReporter);
    extent.setReportUsesManualConfiguration(true);
  }

  /**
   * Metodo que escribe un nodo nuevo con un resultado del caso de prueba.
   *
   * @param tests Test
   * @param status Estado del test
   * @author maximiliano.grajales
   */
  private void buildTestNodes(IResultMap tests, Status status) {

    ExtentTest test;

    if (tests.size() > 0) {
      for (ITestResult result : tests.getAllResults()) {
        test = extent.createTest(result.getMethod().getMethodName());

        for (String group : result.getMethod().getGroups()) {
          test.assignCategory(group);
        }

        if (result.getThrowable() != null) {
          test.log(status, result.getThrowable());
        } else {
          test.log(status,
              String.format("%s%s%s", "Test ", StringUtils.lowerCase(status.toString()), "ed"));
        }

        String descAll = result.getMethod().getDescription();
        String description = String.format("%s%s", "Descripción: ", testDescription(descAll));

        test.getModel()
            .setDescription(description);
        test.getModel().setStartTime(getTime(result.getStartMillis()));
        test.getModel().setEndTime(getTime(result.getEndMillis()));

        if (status == Status.FAIL) {
          try {
            Object testIdObj = result.getAttribute("IdTestCase");
            if (testIdObj != null) {
              String testId = testIdObj.toString();
              test.fail("Captura de pantalla :")
                  .addScreenCaptureFromPath(screenshotList.get(testId));

              Object jiraIdObject = result.getAttribute("jiraId");
              if (jiraIdObject != null) {
                String jiraId = jiraIdObject.toString();
                test.getModel().setDescription(description + " ID DE JIRA : " + jiraId);
                test.getModel().setName(test.getModel().getName() + "_" + jiraId);
              }
            }
          } catch (IOException e) {
            System.out.println(e.getMessage());
          }
        }
      }
    }
  }

  /**
   * Metodo que obtiene el tiempo de inicio y de finalizacion de la prueba.
   *
   * @param millis milisegundos
   * @author maximiliano.grajales
   */

  private Date getTime(long millis) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millis);
    return calendar.getTime();
  }

  /**
   * Metodo que escribe en el reporte la descripcion del caso de prueba.
   *
   * @param testDescription Annotation "description" del caso de prueba (TestNG)
   * @author maximiliano.grajales
   */

  private String testDescription(String testDescription) {

    String descriptionEmptyMessage = "El test no tiene descripción";
    String description;

    if (testDescription == null || testDescription.equals("")) {
      description = descriptionEmptyMessage;
    } else {
      description = testDescription;
    }

    return description;
  }
}
