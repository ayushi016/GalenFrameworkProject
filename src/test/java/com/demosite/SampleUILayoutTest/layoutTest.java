package com.demosite.SampleUILayoutTest;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.galen.base.GalenBase;
import com.galen.base.GalenBase.TestDevice;
import com.galenframework.api.Galen;
import com.galenframework.reports.model.LayoutReport;

import HTMLReports.GenerateReport;

public class layoutTest  extends GalenBase {
	private LayoutReport homelayoutReport,loginlayoutReport,layoutReport;
	GenerateReport genReport = new GenerateReport();
	
	 @Test(dataProvider = "devices")
	    public void welcomePageonDevice(TestDevice device) throws IOException {
	        load("/");
	        homelayoutReport= Galen.checkLayout(getDriver(), "src/test/resources/specs/homepage.gspec", device.getTags());
	        genReport.generateLayoutReport(homelayoutReport, "Homepage Layout on device "+device.getName());
	    }

	    @Test(dataProvider = "devices")
	    public void loginPageonDevice(TestDevice device) throws IOException {
	        load("/");
	        getDriver().findElement(By.xpath("//button[.='Login']")).click();
	        layoutReport= Galen.checkLayout(getDriver(), "src/test/resources/specs/loginpage.gspec", device.getTags());
            genReport.generateLayoutReport(layoutReport, "Loginpage Layout on device "+device.getName());
	    }
	    
	    @Test(dataProvider = "devices")
	    public void MyNotesPageonDevice(TestDevice device) throws IOException {
	        load("/");
	        getDriver().findElement(By.xpath("//button[.='Login']")).click();
	        String userName = "testuser@example.com";
	        String userPass = "test123";

	        getDriver().findElement(By.name("login.username")).sendKeys(userName);
	        getDriver().findElement(By.name("login.password")).sendKeys(userPass);

	        getDriver().findElement(By.cssSelector(".button-login")).click();
	        
	        layoutReport= Galen.checkLayout(getDriver(), "src/test/resources/specs/myNotesPage.gspec", device.getTags());
            genReport.generateLayoutReport(layoutReport, "My Notes page Layout on device "+device.getName());
	    }
	    
	    @AfterMethod
	    public void reportUpdate() throws IOException {
	    	genReport.buildHtmlReport();
	    	
	    }
	    

}
