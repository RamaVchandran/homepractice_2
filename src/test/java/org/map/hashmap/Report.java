package org.map.hashmap;
	
import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

	public class Report {
		public ExtentReports extent;
		public ExtentTest suittest;
		public ExtentTest test;
		
		 
		
	//beforesuite
		public void beginResult() {
			ExtentHtmlReporter  htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtentReports.html");	
			
		     ExtentReports extent = new ExtentReports();
			 extent.attachReporter(htmlreporter);
			 htmlreporter.setAppendExisting(true);
			 htmlreporter.config().setTheme(Theme.STANDARD);
			
		}
		//beforeclass
		public void StartTestcases() {
			ExtentTest suittest = extent.createTest("createlead","create test case");
		}
		//beforemethod
		public void test() {
			ExtentTest test = suittest.createNode("leads");
			test.assignAuthor("sarath");
			test.assignCategory("smoke");
		}
		//aftersuite
		public void flush() {
			extent.flush();
		}
		
		public void reportstep(String desc,String status) {
		
			if(status.equalsIgnoreCase("pass")) {
				try {
					test.pass(desc,
							MediaEntityBuilder.createScreenCaptureFromPath("./../snaps/img1.png").build());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}else if(status.equalsIgnoreCase("fail")) {
				try {
					test.pass(desc,
							MediaEntityBuilder.createScreenCaptureFromPath("./../snaps/img1.png").build());
				} catch (IOException e) {
				
					e.printStackTrace();
				}
			}
			
		}
		
		
		
		
		
		
		

	}

