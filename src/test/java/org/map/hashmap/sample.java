package org.map.hashmap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class sample {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		System.setProperty("webdriver.gecko.driver", "D:\\Automate tools\\Training\\Framework_Testng\\Driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		Thread.sleep(50000);
		driver.get("http://www.stackoverflow.com");
		
	}
	
}
