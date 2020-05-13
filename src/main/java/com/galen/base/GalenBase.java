package com.galen.base;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import com.galenframework.testng.GalenTestNgTestBase;

public class GalenBase extends GalenTestNgTestBase {

	    private static final String ENV_URL = "http://testapp.galenframework.com";

	    @Override
	    public WebDriver createDriver(Object[] args) {
	        WebDriver driver = new ChromeDriver();
	        if (args.length > 0) {
	            if (args[0] != null && args[0] instanceof TestDevice) {
	                TestDevice device = (TestDevice)args[0];
	                if (device.getScreenSize() != null) {
	                    driver.manage().window().setSize(device.getScreenSize());
	                }
	            }
	        }
	        return driver;
	    }

	    public void load(String uri) {
	        getDriver().get(ENV_URL + uri);
	    }

    @DataProvider(name = "devices")
    public Object [][] devices () {
	        return new Object[][] {
	                {new TestDevice("mobile", new Dimension(450, 800), Arrays.asList("mobile"))},
	                {new TestDevice("desktop", new Dimension(1024, 800),Arrays.asList("desktop"))}
	        };
	    }

	    public static class TestDevice {
	        private final String name;
	        private final Dimension screenSize;
	        private final List<String> tags;

	        public TestDevice(String name, Dimension screenSize, List<String> tags) {
	            this.name = name;
	            this.screenSize = screenSize;
	            this.tags = tags;
	        }

	        public String getName() {
	            return name;
	        }

	        public Dimension getScreenSize() {
	            return screenSize;
	        }

	        public List<String> getTags() {
	            return tags;
	        }

	    }

}
