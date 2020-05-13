package com.demosite.SampleUILayoutTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import com.galenframework.testng.GalenTestNgTestBase;

import HTMLReports.GenerateReport;

public class WelcomePage {
	
    private static final String baseURL="http://testapp.galenframework.com/";
    private WebDriver driver;
    private LayoutReport layoutReport;
    GenerateReport genReport = new GenerateReport();
  
    @BeforeClass
    public void init() {
    	String driverPath = "src/main/resources/driver/chromedriver";

        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        
        driver.get(baseURL);        

        driver.manage().window().setSize(new Dimension(1200, 800));
    }
    
    @Test(priority = 1)
    public void checkHomePage() throws IOException {
    	
    	//Executing Layout check and obtaining the Layout report
       layoutReport = Galen.checkLayout(driver, "src/test/resources/specs/homepage.gspec", Arrays.asList("desktop"));
        genReport.generateLayoutReport(layoutReport, "Homepage Layout");
        
    }
    
    @Test(priority = 2)
    public void checkLoginPage() throws IOException, InterruptedException {
    	
         driver.findElement(By.cssSelector(".button-login")).click();
         Thread.sleep(1000);
    	 layoutReport = Galen.checkLayout(driver, "src/test/resources/specs/loginpage.gspec", Arrays.asList("desktop"));
    	 genReport.generateLayoutReport(layoutReport, "Loginpage Layout");
    	
    }
    
    @AfterMethod
    public void reportUpdate() throws IOException {
    	genReport.buildHtmlReport();
    	
    }
    
    @AfterClass
    public void close() {
        driver.quit();
    }
    
//    @AfterMethod
//    public void reportUpdate() {
//        try {
//        	 List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
//        	 
//             //Create a GalenTestInfo object
//             GalenTestInfo test = GalenTestInfo.fromString("homepage layout");
//      
//             //Get layoutReport and assign to test object
//             test.getReport().layout(layoutReport, "check homepage layout");
//      
//             //Add test object to the tests list
//             tests.add(test);
//      
//             //Create a htmlReportBuilder object
//             HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();
//      
//             //Create a report under /target folder based on tests list
//             htmlReportBuilder.build(tests, "reports");
//      
//             //If layoutReport has errors Assert Fail
//             if (layoutReport.errors() > 0)
//             {
//                 Assert.fail("Layout test failed");
//             }
//         } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
 
  


}