package org.map.hashmap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POM_order extends BaseClass {
	
	public POM_order() {
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//input[@name='order_no']")
	public WebElement order;

	public WebElement getOrder() {
		return order;
	}

	public void setOrder(WebElement order) {
		this.order = order;
	}
	

}
