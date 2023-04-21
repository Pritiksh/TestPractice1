import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ZoomInZoomOut {
static WebDriver driver;
	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://naveenautomationlabs.com/opencart/");
		
//		JavascriptExecutor js=(JavascriptExecutor)driver;
//		//String zoomChrome= "document.body.style.zoom='150%'";
//		String zoomFirefox= "document.body.style.MozTransform='scale(0.5)'";
//		js.executeScript(zoomFirefox);
//		
		zoomChrome("300.0");
		driver.quit();

	}

	public static void zoomChrome(String zoomPercentage) {
		String zoom= "document.body.style.zoom = '"+zoomPercentage+"%'";
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript(zoom);
	}
	public static void zoomFirefox(String zoomPercentage) {
		String zoom= "document.body.style.zoom ='scale("+zoomPercentage+"+)'";
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript(zoom);
	}
}
