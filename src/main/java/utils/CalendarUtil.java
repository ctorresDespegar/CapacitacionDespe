package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Clase para el manejo de las fechas de los calendarios Web.
 *
 * @author Carlos Torres
 */
public class CalendarUtil {



  Calendar calendar = Calendar.getInstance();

  private String dateString;
  private boolean increment;

  /**
   * Usar cuando se quiere comparar la fecha del sistema contra otra fecha para obtener el rango de
   * tiempo entre ambas.
   *
   * @param dateString Fecha con la que se compara la fecha actual del sitema.
   */
  public CalendarUtil(String dateString) {
    this.dateString = dateString;
  }

  /**
   * Usar para obtener la fecha del sistema.
   */
  public CalendarUtil() {
  }

  private int getCurrentDay() {
    return calendar.get(Calendar.DAY_OF_MONTH);
  }

  private int getCurrentMonth() {
    return calendar.get(Calendar.MONTH) + 1;
  }

  private int getCurrentYear() {
    return calendar.get(Calendar.YEAR);
  }

  private int getFirstIndex() {
    return dateString.indexOf("/");
  }

  private int getLastIndex() {
    return dateString.lastIndexOf("/");
  }

  public String getDateCurrent() {
    DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    return format.format(calendar.getTime());
  }

  /**
   * Metodo que obtiene el dia de la fecha ingresada.
   *
   * @return Dia
   * @throws Exception Si falla al obtener el dia
   * @author Carlos Torres
   */
  public int getDay() throws Exception {
    String day = dateString.substring(0, getFirstIndex());
    int targetDay = Integer.parseInt(day);
    if (increment) {
      return targetDay;
    } else if (targetDay >= getCurrentDay()) {
      return targetDay;
    }

    throw new Exception("El dia que quiere ingresar ya paso. Por favor Verifique!!");
  }

  /**
   * Metodo que obtiene el Mes de la fecha ingresada y realiza la comparacion con el mes en curso y
   * el año en curso para verificar que sea un mes valido.
   *
   * @return Mes valido
   * @throws Exception Si el mes no es valido.
   */
  public int getMonth() throws Exception {
    String month = dateString.substring(getFirstIndex() + 1, getLastIndex());
    int targetMonth = Integer.parseInt(month);
    int targetYear = getYear();
    if (targetYear > getCurrentYear()) {
      setIncrement(true);
      return (12 - getCurrentMonth()) + targetMonth;
    }
    if ((targetMonth - getCurrentMonth()) >= 0) {
      setIncrement(true);
      return targetMonth - getCurrentMonth();
    } else {

      throw new Exception("El mes ingresado ya paso... Por favor Verifique!!");
    }
  }

  /**
   * Metodo que obtiene el año de la fecha ingresada y realiza una verificacion que sea un año
   * valido.
   *
   * @return Año valido.
   * @throws Exception Si el año no es valido.
   */
  public int getYear() throws Exception {
    String year = dateString.substring(getLastIndex() + 1, dateString.length());
    int targetYear = Integer.parseInt(year);
    if (targetYear >= getCurrentYear()) {
      return targetYear;
    }

    throw new Exception("El año ingresado ya paso... Por favor verifique!!");
  }

  public boolean isIncrement() {
    return increment;
  }

  private void setIncrement(boolean increment) {
    this.increment = increment;
  }
}
