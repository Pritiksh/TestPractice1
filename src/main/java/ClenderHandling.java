import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClenderHandling {
static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		driver =new ChromeDriver();
		
		driver.get("https://www.globalsqa.com/demo-site/datepicker/");
		driver.manage().window().maximize();
		
		driver.switchTo().frame(3);
		driver.findElement(By.id("datepicker")).click();
		
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ui-datepicker-div")));
		
		String monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
		String month =monthYearValue.split(" ")[0].trim();
		String year = monthYearValue.split(" ")[1].trim();
		
		while(!(month.equals("July")&&year.equals("2023"))) {
			driver.findElement(By.xpath("//a[@title='Next']")).click();
			monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
			System.out.println(monthYearValue);
			month =monthYearValue.split(" ")[0].trim();
			year = monthYearValue.split(" ")[1].trim();
		}
		driver.findElement(By.xpath("//a[@class='ui-state-default' and text()='26']")).click();
		
		driver.quit();

	}
	
	public static void selectDate() {
		
	}

}
