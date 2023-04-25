import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MonkeyTesting {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		List<WebElement> footerLinks =driver.findElements(By.cssSelector("div.navFooterVerticalRow li a"));
		int footerCount = footerLinks.size();
		System.out.println("footer links count : "+footerCount);
		
		for(int i=0;i<footerCount;i++) {
			int randomIndex = (int)Math.floor(Math.random()*footerCount);
			System.out.println(randomIndex);
			
			WebElement e = footerLinks.get(randomIndex);
			System.out.println(e.getText());
			e.click();
			driver.navigate().back();
			Thread.sleep(2000);
			footerLinks =driver.findElements(By.cssSelector("div.navFooterVerticalRow li a"));
		}
		
		driver.quit();
	}

}
