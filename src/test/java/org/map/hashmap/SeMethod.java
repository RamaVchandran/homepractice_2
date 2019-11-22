package org.map.hashmap;


	
	import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

	public  class SeMethod extends BaseClass {
		
		public  RemoteWebDriver driver;

		public void startApp(String browser, String url) {
			if(browser.equalsIgnoreCase("chrome")) {
				try {
					System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
					driver = new ChromeDriver();
					 reportstep("create lead is created","pass");
				} catch (Exception e) {
					 reportstep("create lead is created","fail");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			} else if(browser.equalsIgnoreCase("firefox")) {			
				try {
					System.setProperty("webdriver.gecko.driver", "./driver/geckoriver.exe");
					driver = new FirefoxDriver();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		}

		public WebElement locateElement(String locator, String locValue) {
			try {
				switch (locator) {
				case "id":return driver.findElementById(locValue);			
				case "class": return driver.findElementByClassName(locValue);		
				case "xpath": return driver.findElementByXPath(locValue);
				case "linktext": return driver.findElementByLinkText(locValue);
				case "name": return driver.findElementByName(locValue);
				case "tagname": return driver.findElementByTagName(locValue);
				
				}
			} catch (NoSuchElementException e) {
				System.err.println("Element not found");
				throw new RuntimeException();
				
			}
			System.out.println("This Element Loacated Successfully using "+locator);
			return null;
		}

		public WebElement locateElement(String locValue) {
	       
			return driver.findElementById(locValue);
		}
		public void type(WebElement element, String name) {
			element.sendKeys(name);
			System.out.println("This Element typed Successfully using "+element);
			takeSnap();
		}

		public void click(WebElement ele) {
			ele.click();
			System.out.println("This Element Clicked Successfully using "+ele);
			takeSnap();
		}
		

		public void clickWithNoSnap(WebElement ele) {
			// TODO Auto-generated method stub
			ele.click();
			System.out.println("This Element Clicked Successfully using "+ele);

		}

		public String getText(WebElement ele) {
			// TODO Auto-generated method stub
			String text = ele.getText();
			return text;
		}

		public void selectDropDownUsingText(WebElement ele, String value) {
			// TODO Auto-generated method stub
			Select sel = new Select(ele);
			sel.selectByVisibleText(value);

		}

		public void selectDropDownUsingIndex(WebElement ele, int index) {
			// TODO Auto-generated method stub
			Select sel = new Select(ele);
			sel.selectByIndex(index);
		}

		public boolean verifyTitle(String expectedTitle) {
			// TODO Auto-generated method stub
			String title = driver.getTitle();
			if (title.equals(expectedTitle)) {
				System.out.println("title matches");
				return true;
			} else {
				System.out.println("title not matches");
			}
			return false;
		}

		public void verifyExactText(WebElement ele, String expectedText) {
			// TODO Auto-generated method stub
			String text = ele.getText();
			if (text.equals(expectedText)) {
				System.out.println("text matched");
			} else {
				System.out.println("text not matched");
			}

		}

		public void verifyPartialText(WebElement ele, String expectedText) {
			// TODO Auto-generated method stub

		}

		public void verifyExactAttribute(WebElement ele, String attribute, String value) {
			// TODO Auto-generated method stub

		}

		public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
			// TODO Auto-generated method stub

		}

		public void verifySelected(WebElement ele) {
			// TODO Auto-generated method stub

		}

		public void verifyDisplayed(WebElement ele) {
			// TODO Auto-generated method stub

		}

		public void switchToWindow(int index) {
			// TODO Auto-generated method stub
			Set<String> allWindow = driver.getWindowHandles();
			List<String> all = new ArrayList<String>();
			all.addAll(allWindow);
			driver.switchTo().window(all.get(index));

		}

		public void switchToFrame(WebElement ele) {
			// TODO Auto-generated method stub

		}

		public void acceptAlert() {
			// TODO Auto-generated method stub
	       driver.switchTo().alert().accept();
		}

		public void dismissAlert() {
			// TODO Auto-generated method stub

		}

		public String getAlertText() {
			// TODO Auto-generated method stub
			return null;
		}
		int i =1;
		public void takeSnap() {
			File src = driver.getScreenshotAs(OutputType.FILE);
			File desc = new File("./snaps/img"+i+".png");
			try {
				FileUtils.copyFile(src, desc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;

		}

		public void closeBrowser() {
			// TODO Auto-generated method stub
			driver.close();

		}

		public void closeAllBrowsers() {
			// TODO Auto-generated method stub
			driver.quit();

		}

	}

