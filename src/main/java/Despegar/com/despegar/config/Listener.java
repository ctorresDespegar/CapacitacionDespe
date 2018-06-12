package Despegar.com.despegar.config;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener {

  @Override

  public void onStart(ISuite arg0) {

    Reporter.log("Comenzando la suite ... " + arg0.getName(), true);

  }

  @Override

  public void onFinish(ISuite arg0) {

    Reporter.log("Finalizando la suite ... " + arg0.getName(), true);

  }

  public void onStart(ITestContext arg0) {

    Reporter.log("Ejecutando test ... " + arg0.getName(), true);

  }

  public void onFinish(ITestContext arg0) {

    Reporter.log("Finalizando test ... " + arg0.getName(), true);

  }

  public void onTestSuccess(ITestResult arg0) {

    printTestResults(arg0);

  }

  public void onTestFailure(ITestResult arg0) {

    printTestResults(arg0);

  }

  public void onTestStart(ITestResult arg0) {

    System.out.println("Ejecución del test comenzando");

  }

  public void onTestSkipped(ITestResult arg0) {

    printTestResults(arg0);

  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

  }

  private void printTestResults(ITestResult result) {

    Reporter.log("El metodo del test se encuentra en " + result.getTestClass().getName(), true);

    if (result.getParameters().length != 0) {

      String params = null;

      for (Object parameter : result.getParameters()) {

        params += parameter.toString() + ",";

      }

      Reporter.log("El test tiene los siguientes parametros : " + params, true);

    }

    String status = null;

    switch (result.getStatus()) {

      case ITestResult.SUCCESS:

        status = "Pasó";

        break;

      case ITestResult.FAILURE:

        status = "Falló";

        break;

      case ITestResult.SKIP:

        status = "Skipped";

    }

    Reporter.log("Estado del test: " + status, true);

  }

  public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
  }

  public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
  }

  private String returnMethodName(ITestNGMethod method) {

    return method.getRealClass().getSimpleName() + "." + method.getMethodName();

  }

}