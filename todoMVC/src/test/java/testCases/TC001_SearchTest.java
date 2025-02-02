package testCases;


import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.TodoSearchBox;

public class TC001_SearchTest  {
	
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
	public void searchTest()  {
		TodoSearchBox search = new TodoSearchBox(driver);
		search.setSearch("Complete Selenium");
		
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
		
		Assert.assertEquals(search.getSearch(), "1 item left!");
		
		search.setSearch("Complete Java");
		
		action.sendKeys(Keys.ENTER).perform();
		Assert.assertEquals(search.getSearch(), "2 items left!");
		
		
	}
	
	@Test
	public void radioBtnTest() {
		TodoSearchBox search = new TodoSearchBox(driver);
		String[] searchData = {"Test UAT scenarios", "Check production tickets priority wise", "Attend daily status meeting"};
		for (String data : searchData) {
			search.setSearch(data);
			
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).perform();
			
		}
		
		search.getRadioBtnList().get(1).click();
		Assert.assertEquals(search.getSearch(), "3 items left!");
		
		search.clickDeleteBtn().get(1).click();
		Assert.assertEquals(search.getSearch(), "2 items left!");
		
		search.clickAll();
		Assert.assertEquals(search.getSearch(), "2 items left!");
		
		search.clickActive();
		Assert.assertEquals(search.getSearch(), "2 items left!");
		
		search.clickCompleted();
		Assert.assertEquals(search.getSearch(), "2 items left!");
		
		search.clickclearCompleted();
		Assert.assertEquals(search.getSearch(), "2 items left!");
		
		
	}
	
}
