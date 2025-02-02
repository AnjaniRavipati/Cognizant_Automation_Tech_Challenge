package testCases;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.TodoSearchBox;

public class TC002_WindowHandler {
	
	public WebDriver driver;
	
	@BeforeClass
	public void setup() {
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "/Users/vb/Documents/chromedriver");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://todomvc.com/examples/react/dist/");
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void windowHandler() throws Exception {
		// Write code to handle multiple windows	
		
		String parent = driver.getWindowHandle();
		
		TodoSearchBox search = new TodoSearchBox(driver);
		 search.clickLink();
		
		Set<String> allWindows = driver.getWindowHandles();
		
		int count = allWindows.size();
		
		System.out.println("Total windows"+count);
		
		for(String child:allWindows) 
		{
			if(!parent.equalsIgnoreCase(child))
			{
				driver.switchTo().window(child);
				System.out.println("Child window title is" + driver.getTitle());
				Thread.sleep(3000);
				driver.close();
			}
		}
		
		driver.switchTo().window(parent);
		System.out.println("Parent window title is "+ driver.getTitle());
	}

}
