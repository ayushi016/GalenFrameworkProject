package HTMLReports;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;

public class GenerateReport {
	
//	 private LayoutReport layoutReport;
	 List<GalenTestInfo> tests;
	 GalenTestInfo test;
	 
	public GenerateReport(){
		tests = new LinkedList<GalenTestInfo>();
   	 
	}
	
	public void generateLayoutReport(LayoutReport layoutReport, String testTitle) {

        //Create a GalenTestInfo object
		test = GalenTestInfo.fromString(testTitle);
		
		//Get layoutReport and assign to test object
        test.getReport().layout(layoutReport, "check "+testTitle);
 
        //Add test object to the tests list
        tests.add(test);
       
 	}
	
	public void buildHtmlReport() throws IOException {
		//Create a htmlReportBuilder object
        HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();
 
        //Create a report under /target folder based on tests list
        htmlReportBuilder.build(tests, "reports");
         
	}

}
