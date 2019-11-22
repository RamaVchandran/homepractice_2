package org.map.hashmap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POM_Sreach extends BaseClass {

		public POM_Sreach(){
			PageFactory.initElements(driver, this);
		}

		@FindBy(xpath = "//input[@id='username']")
		private WebElement user;

		@FindBy(xpath = "//input[@id='password']")
		private WebElement password;

		@FindBy(xpath = "//input[@id='login']")
		private WebElement log;

		@FindBy(xpath = "//select[@id='location']")
		private WebElement location;
		
		@FindBy(xpath = "//select[@id='hotels']")
		private WebElement hot;
		
		@FindBy(xpath = "//select[@id='room_type']")
		private WebElement room;
		
		@FindBy(xpath = "//select[@id='room_nos']")
		private WebElement romno;
		
		@FindBy(xpath = "//input[@id='datepick_in']")
		private WebElement in;
		
		@FindBy(xpath = "//input[@id='datepick_out']")
		private WebElement out;
		
		@FindBy(xpath = "//Select[@id='adult_room']")
		private WebElement adult;
		
		@FindBy(xpath = "//Select[@id='child_room']")
		private WebElement child;

		@FindBy(xpath = "//input[@id='Submit']")
		private WebElement sub;

		public WebElement getUser() {
			return user;
		}

		public WebElement getPassword() {
			return password;
		}

		public WebElement getLog() {
			return log;
		}

		public WebElement getLocation() {
			return location;
		}

		public WebElement getHot() {
			return hot;
		}

		public WebElement getRoom() {
			return room;
		}

		public WebElement getRomno() {
			return romno;
		}

		public WebElement getIn() {
			return in;
		}

		public WebElement getOut() {
			return out;
		}

		public WebElement getAdult() {
			return adult;
		}

		public WebElement getChild() {
			return child;
		}

		public WebElement getSub() {
			return sub;
		}

		
		
	}
	

