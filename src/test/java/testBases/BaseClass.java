package testBases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;  //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass()); //used to load the log4j2 xml file
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			
			//OS
			if(os.equalsIgnoreCase("Windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("Linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No Matching OS");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome":cap.setBrowserName("chrome");
			break;
			case "Edge": cap.setBrowserName("MicrosoftEdge");
			break;
			case "firefox": cap.setBrowserName("firefox");
			break;
			default: System.out.println("No Matching Browser");
			return;
			}	
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{

			switch(br.toLowerCase())
			{
			case "chrome": driver=new ChromeDriver();
			break;
			case "edge": driver=new EdgeDriver();
			break;
			default : System.out.println("Invalid Browser");
			return;
			}
		}
			
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("URL")); //Reading url from properties file
		driver.manage().window().maximize();
	}
	@AfterClass(groups={"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	public String randomString()
	{
		String ranalpha=RandomStringUtils.randomAlphabetic(5);
		return ranalpha;
	}
	public String randomnumericString()
	{
		String rannum=RandomStringUtils.randomNumeric(10);
		return rannum;
	}
	public String randomalphanumericString()
	{
		String ranalpha=RandomStringUtils.randomAlphabetic(5);
		String rannum=RandomStringUtils.randomNumeric(5);
		return (ranalpha+"@"+rannum);
	}
	
	public String CapureScreen(String tname) throws IOException 

	{
		/*
		 * SimpleDateFormat st=new SimpleDateFormat("yyyyMMddhhmmss"); 
		 * Date dt=new Date(); 
		 * String timeStamp=st.format(dt);
		 */
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		
		
		TakesScreenshot takescreenshot=(TakesScreenshot) driver;
		File sourceFile= takescreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
	}
	
}
