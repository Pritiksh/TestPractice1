import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinks {

	public static void main(String[] args) throws IOException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/");
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		List<String> urlList = new ArrayList<String>();
		
		for(WebElement e:links) {
			String url=e.getAttribute("href");
			urlList.add(url);
			//checkBrokenUrl(url);
		}
		long stTime = System.currentTimeMillis();
		urlList.parallelStream().forEach(e ->checkBrokenUrl(e));
		long endTime = System.currentTimeMillis();
		System.out.println("Total time taken: " +(endTime-stTime));
		driver.quit();
		

	}
	public static void checkBrokenUrl(String linkUrl) {
		try{URL url =new URL(linkUrl);
		HttpURLConnection httpurlConnect =(HttpURLConnection) url.openConnection();
		httpurlConnect.setConnectTimeout(5000);
		httpurlConnect.connect();
		if(httpurlConnect.getResponseCode()>=400) {
			System.out.println(linkUrl + "--->"+httpurlConnect.getResponseMessage() + "is broken link");
		}
		else {
			System.out.println(linkUrl + "--->"+httpurlConnect.getResponseMessage());
		}
	}catch(Exception e) {
		}
	}

}
