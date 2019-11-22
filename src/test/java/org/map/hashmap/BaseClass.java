package org.map.hashmap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass extends Report{

	public static String getData(int rowNum, String ColumnName) throws IOException {
		List<LinkedHashMap<String, String>> maplist = new ArrayList<LinkedHashMap<String, String>>();
		File excol = new File("D:\\Automate tools\\Training\\Framework_Testng\\TestData\\Adactin.xlsx");
		String SheetName = ("data");
		FileInputStream f = new FileInputStream(excol);
		Workbook w = new XSSFWorkbook(f);
		Sheet s = w.getSheet(SheetName);
		Row headerRow = s.getRow(0);
		for (int i = 1; i < s.getPhysicalNumberOfRows(); i++) {
			Row currentrow = s.getRow(i);
			LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();

			for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
				Cell current = currentrow.getCell(j);
				int type = current.getCellType();
				if (type == 1) {
					data.put(headerRow.getCell(j).getStringCellValue(), current.getStringCellValue());
				} else if (type == 0) {
					if (DateUtil.isCellDateFormatted(current)) {
						data.put(headerRow.getCell(j).getStringCellValue(),
								new SimpleDateFormat("dd-MMM-YY").format(current.getDateCellValue()));
					} else {
						data.put(headerRow.getCell(j).getStringCellValue(),
								String.valueOf((long) current.getNumericCellValue()));

					}
				}

			}

			maplist.add(data);

		}

		return maplist.get(rowNum).get(ColumnName);

	}

	// config driver
	public static WebDriver driver;

	public static WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", "D:\\Automate tools\\Training\\Framework_Testng\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}

	// loadurl
	public static void loadurl(String url) {
		driver.get(url);

	}

	// sendkey
	public void type(WebElement element, String name) {
		element.sendKeys(name);
	}

	// buttonclick
	public static void butnClick(WebElement element) {
		element.click();

	}

	// findelement
	public static WebElement chooseElement(int x, String path) {

		WebElement webElement = null;

		switch (x) {
		case 1:
			webElement = driver.findElement(By.id(path));
			break;
		case 2:
			webElement = driver.findElement(By.className(path));
			break;
		case 3:
			webElement = driver.findElement(By.linkText(path));
			break;
		case 4:
			webElement = driver.findElement(By.xpath(path));
			break;
		case 5:
			webElement = driver.findElement(By.cssSelector(path));
			break;
		case 6:
			webElement = driver.findElement(By.tagName(path));
			break;
		}
		return webElement;

	}
	public static void write(int rownum, int cellnum, String name) throws IOException {
		File excol = new File("D:\\Automate tools\\Training\\Framework_Testng\\TestData\\Adactin.xlsx");
		FileInputStream stream = new FileInputStream(excol);
		Workbook w = new XSSFWorkbook(stream);
		Sheet s = w.getSheet("data");
		Row trow = s.getRow(rownum);
		Cell c = trow.createCell(cellnum);
		c.setCellValue(name);
		FileOutputStream o = new FileOutputStream(excol);
		w.write(o);

	}
	
	//--------------------------------------------------------------------------------------------------------------------------------//
	
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

	public WebElement locateElement(String locator, By locValue) {
		try {
			switch (locator) {
			case "id":return driver.findElement(locValue);			
			case "class": return driver.findElement(locValue);		
			case "xpath": return driver.findElement(locValue);
			case "linktext": return driver.findElement(locValue);
			case "name": return driver.findElement(locValue);
			case "tagname": return driver.findElement(locValue);
			
			}
		} catch (NoSuchElementException e) {
			System.err.println("Element not found");
			throw new RuntimeException();
			
		}
		System.out.println("This Element Loacated Successfully using "+locator);
		return null;
	}

	public WebElement locateElement(By locValue) {
       
		return driver.findElement(locValue);
	}

	public void type1(WebElement ele, String data) {	   	
		ele.sendKeys(data);
		System.out.println("This Element typed Successfully using "+ele);
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
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
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

	static ExtentTest test;
	static ExtentReports report;
	public void setup(String browser) throws Exception {
		// Check if parameter passed from TestNG is 'firefox'
		
		POM_Sreach see = new POM_Sreach();
		if (browser.equalsIgnoreCase("firefox")) {
			// create firefox instance
			System.setProperty("webdriver.gecko.driver",
					"D:\\Automate tools\\Training\\Framework_Testng\\Driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		// Check if parameter passed as 'chrome'
		else if (browser.equalsIgnoreCase("chrome")) {
			// set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver",
					"D:\\Automate tools\\Training\\Framework_Testng\\Driver\\chromedriver.exe");
			// create chrome instance
			driver = new ChromeDriver();
		}
		// Check if parameter passed as 'Edge'
		else if (browser.equalsIgnoreCase("ie")) {
			// set path to Edge.exe
			System.setProperty("webdriver.ie.driver",
					"D:\\Automate tools\\Training\\Framework_Testng\\Driver\\IEDriverServer.exe");
			// create Edge instance
			driver = new InternetExplorerDriver();
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}

		driver.manage().window().maximize();
		Thread.sleep(6000);
		driver.get("http://adactin.com/HotelApp/index.php");
		report = new ExtentReports("D:\\Automate tools\\Report\\ExtentReportResults.html");
		test = report.startTest("Selenium");
	}

	public static void clickByAction(WebElement element){
		Actions ac = new Actions(driver);
		Action click = ac.click(element).build();
		click.perform();
	}
	
}