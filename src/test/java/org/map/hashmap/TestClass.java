package org.map.hashmap;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestClass extends BaseClass {
	static ExtentTest test;
	static ExtentReports report;
	static WebDriverWait wait;
	POM_Sreach see = new POM_Sreach();

	@BeforeClass
	@Parameters({ "browser" })
	// public void beforeClass() throws IOException {
	// driver = getDriver();
	// driver.manage().window().maximize();
	// wait = new WebDriverWait(driver, 30);
	// loadurl("http://adactin.com/HotelApp/index.php");
	// driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	// report = new ExtentReports("D:\\Automate
	// tools\\Report\\ExtentReportResults.html");
	// test = report.startTest("Selenium");
	public void setup(String browser) throws Exception {
		// Check if parameter passed from TestNG is 'firefox'

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

			// System.setProperty("webdriver.edge.driver","D:\\Automate
			// tools\\Jars\\MS Edge_version
			// 79\\edgedriver_win64\\msedgedriver.exe");
			// driver = new EdgeDriver();

			// System.setProperty("webdriver.ie.driver","D:\\Automate
			// tools\\Training\\Framework_Testng\\Driver\\IEDriverServer.exe");
			File file = new File("D:\\Automate tools\\Training\\Framework_Testng\\Driver\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			// driver = new InternetExplorerDriver();
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability("nativeEvents", false);
			ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
			ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
			ieCapabilities.setCapability("disable-popup-blocking", true);
			ieCapabilities.setCapability("enablePersistentHover", true);
			ieCapabilities.setCapability("ignoreZoomSetting", true);
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			;
			driver = new InternetExplorerDriver(ieCapabilities);

		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}

		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 30);
		driver.get("http://adactin.com/HotelApp/index.php");
		report = new ExtentReports("D:\\Automate tools\\Report\\ExtentReportResults.html");
		test = report.startTest("Selenium");
	}

	// driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	// report = new ExtentReports("D:\\Automate
	// tools\\Report\\ExtentReportResults.html");
	// test = report.startTest("Selenium");
	// TakesScreenshot ts = (TakesScreenshot) driver;
	// File source = ts.getScreenshotAs(OutputType.FILE);
	// File destination = new File("D:\\Automate
	// tools\\Report\\screenshot\\issuepass.png");
	// FileUtils.copyFile(source, destination);

	@Test(priority = 1)
	public void test1() throws IOException, InterruptedException {
		String title = driver.getTitle();
		System.out.println(title);
		if (title.startsWith("AdactIn.com")) {
			System.out.println("Pass");
			test.log(LogStatus.PASS, "Url passed");
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File("D:\\Automate tools\\Report\\screenshot\\issuepass.png");
			FileUtils.copyFile(source, destination);
			test.log(LogStatus.PASS, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\issuepass.png"));
			System.out.println("1");
		} else {
			System.out.println("Fail");
			test.log(LogStatus.FAIL, "Url Failed");
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File("D:\\Automate tools\\Report\\screenshot\\issuefail.png");
			FileUtils.copyFile(source, destination);
			test.log(LogStatus.FAIL, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\issuefail.png"));
			System.out.println("2");

		}
	}

	@Test(priority = 2, dependsOnMethods = { "test1" })
	public void test2() throws IOException, InterruptedException {
		POM_Sreach see = new POM_Sreach();

		try {
			type(see.getUser(), "Ramachandran15");
			type(see.getPassword(), "Rama@1591");
			WebElement attribute = chooseElement(4, "//input[@id='username']");
			String text = attribute.getAttribute("value");
			System.out.println(text);
			if (text.endsWith("15")) {
				test.log(LogStatus.PASS, "Login passed");
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				File destination = new File("D:\\Automate tools\\Report\\screenshot\\Credentail.png");
				FileUtils.copyFile(source, destination);
				test.log(LogStatus.PASS,
						test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\Credentail.png"));
				System.out.println("3");
			} else {
				System.out.println("Fail");
				test.log(LogStatus.FAIL, "Login Failed");
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				File destination = new File("D:\\Automate tools\\Report\\screenshot\\Credentialfail.png");
				FileUtils.copyFile(source, destination);
				test.log(LogStatus.FAIL,
						test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\Credentialfail.png"));
				System.out.println("4");

			}
			// WebDriverWait w = new WebDriverWait(driver, 10);
			// w.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@type='Submit']"))));

			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			WebDriverWait wait2 = new WebDriverWait(driver, 20);
			wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='Submit']")));
			WebElement button = driver.findElement(By.xpath("//input[@type='Submit']"));
			button.click();
			// clickByAction(button);
			// JavascriptExecutor js = (JavascriptExecutor) driver;
			// js.executeScript("arguments[0].click();", button);
			// WebElement click =
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='Submit']")));
			// click.click();

		} catch (NullPointerException e) {
			System.out.println("LogedIn");

		}
	}

	@Test(priority = 3, dependsOnMethods = { "test2" })
	public void test3() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String title = driver.getCurrentUrl();
		System.out.println(title);
		if (title.startsWith("http://adactin.com/")) {
			System.out.println("Pass");
			test.log(LogStatus.PASS, "Search passed");
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File("D:\\Automate tools\\Report\\screenshot\\Loginpass.png");
			FileUtils.copyFile(source, destination);
			test.log(LogStatus.PASS, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\Loginpass.png"));
			System.out.println("5");
		} else {
			System.out.println("Fail");
			test.log(LogStatus.FAIL, "Search Failed");
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File("D:\\Automate tools\\Report\\screenshot\\Loginfail.png");
			FileUtils.copyFile(source, destination);
			test.log(LogStatus.FAIL, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\Loginfail.png"));
			System.out.println("6");
		}
	}

	@Test(priority = 4)
	public void test4() throws IOException {
		POM_Sreach see = new POM_Sreach();
		WebDriverWait wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='location']")));
		type(see.getLocation(), getData(1, "Location"));
		WebElement attribute = chooseElement(4, "//option[text()='London']");
		String text = attribute.getAttribute("value");
		System.out.println(text);
		if (text.endsWith("don")) {
			test.log(LogStatus.PASS, "Loction passed");
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File("D:\\Automate tools\\Report\\screenshot\\Locpass.png");
			FileUtils.copyFile(source, destination);
			test.log(LogStatus.PASS, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\Locpass.png"));
			System.out.println("7");
		} else {
			System.out.println("Fail");
			test.log(LogStatus.FAIL, "Location Failed");
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File("D:\\Automate tools\\Report\\screenshot\\Locfail.png");
			FileUtils.copyFile(source, destination);
			test.log(LogStatus.FAIL, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\Locfail.png"));
			System.out.println("8");
		}
	}

	@Test(priority = 5)
	public void test5() throws IOException {
		POM_Sreach see = new POM_Sreach();
		type(see.getHot(), getData(1, "Hotel"));
		WebElement attrihotel = chooseElement(4, "//option[text()='Hotel Sunshine']");
		String texthotel = attrihotel.getAttribute("value");
		System.out.println(texthotel);
		if (texthotel.startsWith("Hot")) {
			test.log(LogStatus.PASS, "hotel passed");
			TakesScreenshot ts1 = (TakesScreenshot) driver;
			File source1 = ts1.getScreenshotAs(OutputType.FILE);
			File destination1 = new File("D:\\Automate tools\\Report\\screenshot\\hotpass.png");
			FileUtils.copyFile(source1, destination1);
			test.log(LogStatus.PASS, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\hotpass.png"));
			System.out.println("9");
		} else {
			System.out.println("Fail");
			test.log(LogStatus.FAIL, "hotel Failed");
			TakesScreenshot ts2 = (TakesScreenshot) driver;
			File source2 = ts2.getScreenshotAs(OutputType.FILE);
			File destination2 = new File("D:\\Automate tools\\Report\\screenshot\\hotfail.png");
			FileUtils.copyFile(source2, destination2);
			test.log(LogStatus.FAIL, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\hotfail.png"));
			System.out.println("8");
		}
	}

	@Test(priority = 6)
	public void test6() throws IOException {
		POM_Sreach see = new POM_Sreach();
		type(see.getRoom(), getData(1, "Room Type"));
		WebElement attribute = chooseElement(4, "//option[text()='Double']");
		String text = attribute.getAttribute("value");
		System.out.println(text);
		if (text.startsWith("Doub")) {
			test.log(LogStatus.PASS, "Roomtype Pass");
			TakesScreenshot ts1 = (TakesScreenshot) driver;
			File source1 = ts1.getScreenshotAs(OutputType.FILE);
			File destination1 = new File("D:\\Automate tools\\Report\\screenshot\\Roomtypepass.png");
			FileUtils.copyFile(source1, destination1);
			test.log(LogStatus.PASS, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\Roomtypepass.png"));
			System.out.println("10");
		} else {
			System.out.println("Fail");
			test.log(LogStatus.FAIL, "Roomtype Fail");
			TakesScreenshot ts2 = (TakesScreenshot) driver;
			File source2 = ts2.getScreenshotAs(OutputType.FILE);
			File destination2 = new File("D:\\Automate tools\\Report\\screenshot\\Roomtypefail.png");
			FileUtils.copyFile(source2, destination2);
			test.log(LogStatus.FAIL, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\Roomtypefail.png"));
			System.out.println("11");
		}
	}

	@Test(priority = 7)
	public void test7() throws IOException {
		POM_Sreach see = new POM_Sreach();
		type(see.getRomno(), getData(1, "No of Rooms"));
		WebElement attribute = chooseElement(4, "//option[text()='1 - One']");
		String text = attribute.getAttribute("value");
		System.out.println(text);
		if (text.startsWith("1")) {
			test.log(LogStatus.PASS, "RoomNo Pass");
			TakesScreenshot ts1 = (TakesScreenshot) driver;
			File source1 = ts1.getScreenshotAs(OutputType.FILE);
			File destination1 = new File("D:\\Automate tools\\Report\\screenshot\\RoomNopass.png");
			FileUtils.copyFile(source1, destination1);
			test.log(LogStatus.PASS, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\RoomNopass.png"));
			System.out.println("12");
		} else {
			System.out.println("Fail");
			test.log(LogStatus.FAIL, "RoomNo Fail");
			TakesScreenshot ts2 = (TakesScreenshot) driver;
			File source2 = ts2.getScreenshotAs(OutputType.FILE);
			File destination2 = new File("D:\\Automate tools\\Report\\screenshot\\RoomNofail.png");
			FileUtils.copyFile(source2, destination2);
			test.log(LogStatus.FAIL, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\RoomNofail.png"));
			System.out.println("11");
		}

	}

	@Test(priority = 8)
	public void test8() throws IOException {
		POM_Sreach see = new POM_Sreach();
		type(see.getIn(), getData(1, "Checkin"));
		WebElement attribute = chooseElement(4, "//input[@name='datepick_in']");
		String text = attribute.getAttribute("value");
		System.out.println(text);
		if (text.startsWith("21")) {
			test.log(LogStatus.PASS, "Checkin");
			TakesScreenshot ts1 = (TakesScreenshot) driver;
			File source1 = ts1.getScreenshotAs(OutputType.FILE);
			File destination1 = new File("D:\\Automate tools\\Report\\screenshot\\Checkinpass.png");
			FileUtils.copyFile(source1, destination1);
			test.log(LogStatus.PASS, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\Checkinpass.png"));
			System.out.println("12");
		} else {
			System.out.println("Fail");
			test.log(LogStatus.FAIL, "Checkin");
			TakesScreenshot ts2 = (TakesScreenshot) driver;
			File source2 = ts2.getScreenshotAs(OutputType.FILE);
			File destination2 = new File("D:\\Automate tools\\Report\\screenshot\\Checkinfail.png");
			FileUtils.copyFile(source2, destination2);
			test.log(LogStatus.FAIL, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\Checkinfail.png"));
			System.out.println("13");
		}

	}

	@Test(priority = 9)
	public void test9() throws IOException {
		POM_Sreach see = new POM_Sreach();
		type(see.getOut(), getData(1, "Checkout"));
		WebElement attribute = chooseElement(4, "//*[@id='datepick_out']");
		String text = attribute.getAttribute("value");
		System.out.println(text);
		if (text.startsWith("22")) {
			test.log(LogStatus.PASS, "Checkout");
			TakesScreenshot ts1 = (TakesScreenshot) driver;
			File source1 = ts1.getScreenshotAs(OutputType.FILE);
			File destination1 = new File("D:\\Automate tools\\Report\\screenshot\\Checkoutpass.png");
			FileUtils.copyFile(source1, destination1);
			test.log(LogStatus.PASS, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\Checkoutpass.png"));
			System.out.println("14");
		} else {
			System.out.println("Fail");
			test.log(LogStatus.FAIL, "Checkout");
			TakesScreenshot ts2 = (TakesScreenshot) driver;
			File source2 = ts2.getScreenshotAs(OutputType.FILE);
			File destination2 = new File("D:\\Automate tools\\Report\\screenshot\\Checkoutfail.png");
			FileUtils.copyFile(source2, destination2);
			test.log(LogStatus.FAIL, test.addScreenCapture("D:\\Automate tools\\Report\\screenshot\\Checkoutfail.png"));
			System.out.println("15");
		}

		type(see.getAdult(), getData(1, "Adults per room"));

		type(see.getChild(), getData(1, "Children per room"));

		butnClick(see.getSub());
	}

	@AfterTest
	public static void endTest() {
		test.log(LogStatus.PASS, "Quit");
		report.endTest(test);
		report.flush();

	}

}
