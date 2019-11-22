package org.map.hashmap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POM_book extends BaseClass{
	
		
		public POM_book() {
			PageFactory.initElements(driver, this);

		}

		
		

		@FindBy(xpath = "//input[@id='first_name']")
		public WebElement firstname;
		

		@FindBy(xpath = "//input[@id='last_name']")
		public WebElement lastname;
		

		@FindBy(xpath = "//textarea[@id='address']")
		public WebElement address1;
		
		

		@FindBy(xpath = "//input[@id='cc_num']")
		public WebElement cardno;

		@FindBy(xpath = "//select[@id='cc_type']")
		public WebElement cardtype;

		@FindBy(xpath = "//select[@id='cc_exp_month']")
		public WebElement epmonth;

		@FindBy(xpath = "//select[@id='cc_exp_year']")
		public WebElement epyear;

		@FindBy(xpath = "//input[@id='cc_cvv']")
		public WebElement cvvno;

		@FindBy(xpath = "//input[@id='book_now']")
		public WebElement booknow;

		public WebElement getFirstname() {
			return firstname;
		}

		public void setFirstname(WebElement firstname) {
			this.firstname = firstname;
		}

		public WebElement getLastname() {
			return lastname;
		}

		public void setLastname(WebElement lastname) {
			this.lastname = lastname;
		}

		public WebElement getAddress1() {
			return address1;
		}

		public void setAddress1(WebElement address1) {
			this.address1 = address1;
		}

		public WebElement getCardno() {
			return cardno;
		}

		public void setCardno(WebElement cardno) {
			this.cardno = cardno;
		}

		public WebElement getCardtype() {
			return cardtype;
		}

		public void setCardtype(WebElement cardtype) {
			this.cardtype = cardtype;
		}

		public WebElement getEpmonth() {
			return epmonth;
		}

		public void setEpmonth(WebElement epmonth) {
			this.epmonth = epmonth;
		}

		public WebElement getEpyear() {
			return epyear;
		}

		public void setEpyear(WebElement epyear) {
			this.epyear = epyear;
		}

		public WebElement getCvvno() {
			return cvvno;
		}

		public void setCvvno(WebElement cvvno) {
			this.cvvno = cvvno;
		}

		public WebElement getBooknow() {
			return booknow;
		}

		public void setBooknow(WebElement booknow) {
			this.booknow = booknow;
		}
		
		
		
		
	}


