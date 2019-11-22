package org.map.hashmap;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IE_Test {
	static WebDriver driver;
	public static void main(String[] args) {
		File file = new File("D:\\Automate tools\\Training\\Framework_Testng\\Driver\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		driver = new InternetExplorerDriver();
		//System.setProperty("Webdriver.ie.driver","D:\\Automate tools\\Training\\Framework_Testng\\Driver\\IEDriverServer.exe");
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability("nativeEvents", false);
		ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
		ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
		ieCapabilities.setCapability("disable-popup-blocking", true);
		ieCapabilities.setCapability("enablePersistentHover", true);
		ieCapabilities.setCapability("ignoreZoomSetting", true);
		ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false); ;
		//driver = new InternetExplorerDriver();
		driver.get("http://adactin.com/HotelApp/index.php");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Ramachandran15");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Rama@1591");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
	}

}
