import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PDFBrowserTest {
	
WebDriver driver;
String url = "https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf";

@BeforeTest
public void setup() throws InterruptedException {

driver =new ChromeDriver();
driver.get(url);
//driver.get("https://www.inkit.com/blog/pdf-the-best-digital-document-management");


}
@Test
public void pdfReaderTest() throws InterruptedException, IOException {
	
	URL pdfurl = new URL(url);
	//Opens a connection to this URL and returns an InputStream for reading from that connection.
	InputStream ip=pdfurl.openStream();
	//Creates a BufferedInputStream and saves.
	BufferedInputStream bf =new BufferedInputStream(ip);
	PDDocument pdDocument =PDDocument.load(bf);
	//Page count
	int pagecount = pdDocument.getNumberOfPages();
	System.out.println(pagecount);
	Assert.assertEquals(pagecount, 4);
	
	//Page content/text
	PDFTextStripper pdfStripper = new PDFTextStripper();
	String pdfText = pdfStripper.getText(pdDocument);
	System.out.println(pdfText);
	Assert.assertTrue(pdfText.contains("Sample Data File "));
	Assert.assertTrue(pdfText.contains("• Place ap_bookmark.IFD in the Designs subdirectory for Output Designer. "));
	Assert.assertTrue(pdfText.contains("Sample Date: May 2001 "));
	Assert.assertTrue(pdfText.contains("Created and Tested Using: • Accelio Present Central 5.4 "));
	
	
	//Set the page no and get the text
	pdfStripper.setStartPage(4);
	String pdftext1 = pdfStripper.getText(pdDocument);
	System.out.println(pdftext1);
	Assert.assertTrue(pdftext1.contains("ap_bookmark.dat"));
	}
	

@AfterTest
public void tearDown() {
	driver.quit();
}
}