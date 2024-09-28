package testBase;
import org.openqa.selenium.remote.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.WriterManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass  {

	public static WebDriver driver;
	public Logger logger;
	public Properties pr;
	
	@BeforeClass(groups={"Sanity","Master","Regrassion"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		//loading config.properties file
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		pr=new Properties();
		pr.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());
		
		if(pr.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			}else if(os.equalsIgnoreCase("mac")){
				capabilities.setPlatform(Platform.MAC);
			}else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}else {
				System.out.println("No matching os");
				return;
			}
			//browser
			switch(br.toLowerCase()) {
			case "chrome" :capabilities.setBrowserName("chrome");break;
			case "edge" :capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox" :capabilities.setBrowserName("firefox");break;
			default: System.out.println("Invalid Browser name.."); return;
			
		}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
	}
		if(pr.getProperty("execution_env").equalsIgnoreCase("local")){
		WebDriverManager.chromedriver().setup();
		switch(br.toLowerCase()) {
		case "chrome" :driver=new ChromeDriver();break;
		case "edge" :driver=new EdgeDriver();break;
		default: System.out.println("Invalid Browser name.."); return; 
		
		}
		
	}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(pr.getProperty("appUrl")); //reading url from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity","Master","Regrassion"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomeString(){
		String generatedString =RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber(){
		String generatedNumber =RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomeAlphaNumber(){
		//String generatedAlphaNumber =RandomStringUtils.randomAlphanumeric(10);
		String generatedString =RandomStringUtils.randomAlphabetic(3);
		String generatedNumber =RandomStringUtils.randomNumeric(3);
		return (generatedString+"@"+generatedNumber);
	}
	
	public String captureScreen(String tname) throws IOException{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot =(TakesScreenshot)driver;
		File sourcefile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetfile=new File(targetFilePath);
		
		sourcefile.renameTo(targetfile);
		return targetFilePath;
	}
}
