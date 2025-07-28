package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
	  private static WebDriver driver;

	  public static WebDriver getDriver() {
	    if (driver == null) {
	      String browser = ConfigReader.get("browser");
	      if (browser.equalsIgnoreCase("chrome")) {
	        driver = new ChromeDriver();
	      }
	    }
	    return driver;
	  }

		public static void closedriver() {
		    if (driver != null) {
		      driver.close();
		      driver = null;
		    }
		  }
	  public static void quitDriver() {
	    if (driver != null) {
	      driver.quit();
	      driver = null;
	    }
	  }
	}

