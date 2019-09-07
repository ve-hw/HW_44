package core;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Firefox_patch {
	static Properties p = new Properties();
	static Writer report;
	static String ls = System.getProperty("line.separator");
	static WebDriver driver;

	public static boolean isElementPresent(By by) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty())
			return true;
		else
			return false;
	}

	public static String getSize(By by) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			return driver.findElement(by).getRect().getDimension().toString().replace(", ", "x");
		else
			return "null";
	}

	public static String getLocation(By by) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			return driver.findElement(by).getRect().getPoint().toString().replace(", ", "x");
		else
			return "null";
	}

	public static void setValue(By by, String value) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			driver.findElement(by).sendKeys(p.getProperty(value));
	}

	public static String getValue(By by) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			return driver.findElement(by).getText();
		else
			return "null";
	}

	public static void main(String[] args) throws Exception {
		Logger.getLogger("").setLevel(Level.OFF);
		p.load(new FileInputStream("./input.properties"));
		report = new FileWriter("./report_Firefox_patch.csv", false);
		String driverPath = "";

		if (System.getProperty("os.name").toUpperCase().contains("MAC"))
			driverPath = "./resources/webdrivers/mac/geckodriver.sh";
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			driverPath = "./resources/webdrivers/pc/geckodriver.exe";
		else
			throw new IllegalArgumentException("Unknown OS");

		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(p.getProperty("url"));

		// HEADER
		System.out.println("#,Browser,Page,Field,isPresent,Value,Size,Location");
		report.write("#,Browser,Page,Field,isPresent,Value, Size, Location");
		report.write(ls);

		// 01 :: First Name index page
		report.write("01,Firefox,index.php,First Name," + isElementPresent(By.id(p.getProperty("fname_id"))) + ","
				+ p.getProperty("fname_value") + "," + getSize(By.id(p.getProperty("fname_id"))) + ","
				+ getLocation(By.id(p.getProperty("fname_id"))) + "\n");
		
		System.out.print("01,Firefox,index.php,First Name," + isElementPresent(By.id(p.getProperty("fname_id"))) + ","
				+ p.getProperty("fname_value") + "," + getSize(By.id(p.getProperty("fname_id"))) + ","
				+ getLocation(By.id(p.getProperty("fname_id"))) + "\n");
		setValue(By.id(p.getProperty("fname_id")), "fname_value");

		// 02 :: Last Name index page
		report.write("02,Firefox,index.php,Last Name," + isElementPresent(By.id(p.getProperty("lname_id"))) + ","
				+ p.getProperty("lname_value") + "," + getSize(By.id(p.getProperty("lname_id"))) + ","
				+ getLocation(By.id(p.getProperty("lname_id"))) + "\n");
		
		System.out.print("02,Firefox,index.php,Last Name," + isElementPresent(By.id(p.getProperty("lname_id"))) + ","
				+ p.getProperty("lname_value") + "," + getSize(By.id(p.getProperty("lname_id"))) + ","
				+ getLocation(By.id(p.getProperty("lname_id"))) + "\n");
		setValue(By.id(p.getProperty("lname_id")), "lname_value");

		// 03 :: Email index page
		report.write("03,Firefox,index.php,Email," + isElementPresent(By.id(p.getProperty("email_id"))) + ","
				+ p.getProperty("email_value") + "," + getSize(By.id(p.getProperty("email_id"))) + ","
				+ getLocation(By.id(p.getProperty("email_id"))) + "\n");
		
		System.out.print("03,Firefox,index.php,Email," + isElementPresent(By.id(p.getProperty("email_id"))) + ","
				+ p.getProperty("email_value") + "," + getSize(By.id(p.getProperty("email_id"))) + ","
				+ getLocation(By.id(p.getProperty("email_id"))) + "\n");
		setValue(By.id(p.getProperty("email_id")), "email_value");

		// 04 :: Phone index page
		report.write("04,Firefox,index.php,Phone," + isElementPresent(By.id(p.getProperty("phone_id"))) + ","
				+ p.getProperty("phone_value") + "," + getSize(By.id(p.getProperty("phone_id"))) + ","
				+ getLocation(By.id(p.getProperty("phone_id"))) + "\n");
		
		System.out.print("04,Firefox,index.php,Phone," + isElementPresent(By.id(p.getProperty("phone_id"))) + ","
				+ p.getProperty("phone_value") + "," + getSize(By.id(p.getProperty("phone_id"))) + ","
				+ getLocation(By.id(p.getProperty("phone_id"))) + "\n");
		setValue(By.id(p.getProperty("phone_id")), "phone_value");

		// 05 :: Quotes index page
		report.write("05,Firefox,index.php,Quotes," + isElementPresent(By.id(p.getProperty("quotes"))) + ","
				+ getValue(By.id("id_quotes")) + "," + getSize(By.id(p.getProperty("quotes"))) + ","
				+ getLocation(By.id(p.getProperty("quotes"))) + "\n");

		System.out.print("05,Firefox,index.php,Quotes," + isElementPresent(By.id(p.getProperty("quotes"))) + ","
				+ getValue(By.id("id_quotes")) + "," + getSize(By.id(p.getProperty("quotes"))) + ","
				+ getLocation(By.id(p.getProperty("quotes"))) + "\n");

		// 06 :: Page Title index page
		report.write("06,Firefox,index.php,Title," + isElementPresent(By.id(p.getProperty("title"))) + ","
				+ getValue(By.id(p.getProperty("title"))) + "," + getSize(By.id(p.getProperty("title"))) + ","
				+ getLocation(By.id(p.getProperty("title"))) + "\n");

		System.out.print("06,Firefox,index.php,title," + isElementPresent(By.id(p.getProperty("title"))) + ","
				+ getValue(By.id(p.getProperty("title"))) + "," + getSize(By.id(p.getProperty("title"))) + ","
				+ getLocation(By.id(p.getProperty("title"))) + "\n");
		
		// 07 :: Location index page
		report.write("07,Firefox,index.php,Location," + isElementPresent(By.id(p.getProperty("location"))) + ","
				+ getValue(By.id(p.getProperty("location"))) + "," + getSize(By.id(p.getProperty("location"))) + ","
				+ getLocation(By.id(p.getProperty("location"))) + "\n");

		System.out.print("07,Firefox,index.php,Location," + isElementPresent(By.id(p.getProperty("location"))) + ","
				+ getValue(By.id(p.getProperty("location"))) + "," + getSize(By.id(p.getProperty("location"))) + ","
				+ getLocation(By.id(p.getProperty("location"))) + "\n");
		
		// 08 :: Weather Icon index page
		report.write("08,Firefox,index.php,Weather Icon," + isElementPresent(By.id(p.getProperty("w_icon"))) + ","
				+ getValue(By.id(p.getProperty("w_icon"))) + "," + getSize(By.id(p.getProperty("w_icon"))) + ","
				+ getLocation(By.id(p.getProperty("w_icon"))) + "\n");

		System.out.print("08,Firefox,index.php,Weather Icon," + isElementPresent(By.id(p.getProperty("w_icon"))) + ","
				+ getValue(By.id(p.getProperty("w_icon"))) + "," + getSize(By.id(p.getProperty("w_icon"))) + ","
				+ getLocation(By.id(p.getProperty("w_icon"))) + "\n");
		
		// 09 :: Temperature index page
		report.write("09,Firefox,index.php,Temperature," + isElementPresent(By.id(p.getProperty("temp"))) + ","
				+ getValue(By.id(p.getProperty("temp"))) + "," + getSize(By.id(p.getProperty("temp"))) + ","
				+ getLocation(By.id(p.getProperty("temp"))) + "\n");

		System.out.print("09,Firefox,index.php,Temperature," + isElementPresent(By.id(p.getProperty("temp"))) + ","
				+ getValue(By.id(p.getProperty("temp"))) + "," + getSize(By.id(p.getProperty("temp"))) + ","
				+ getLocation(By.id(p.getProperty("temp"))) + "\n");
		
		// 10 :: First Name Label index page
		report.write("10,Firefox,index.php,First Name Label," + isElementPresent(By.id(p.getProperty("f_name_label"))) + ","
				+ getValue(By.id(p.getProperty("f_name_label"))) + "," + getSize(By.id(p.getProperty("f_name_label"))) + ","
				+ getLocation(By.id(p.getProperty("f_name_label"))) + "\n");

		System.out.print("10,Firefox,index.php,First Name Label," + isElementPresent(By.id(p.getProperty("f_name_label"))) + ","
				+ getValue(By.id(p.getProperty("f_name_label"))) + "," + getSize(By.id(p.getProperty("f_name_label"))) + ","
				+ getLocation(By.id(p.getProperty("f_name_label"))) + "\n");

		// 11 :: First Name Error Icon index page
		report.write("11,Firefox,index.php,First Name Error Icon," + isElementPresent(By.id(p.getProperty("fname_error"))) + ","
				+ getValue(By.id(p.getProperty("fname_error"))) + "," + getSize(By.id(p.getProperty("fname_error"))) + ","
				+ getLocation(By.id(p.getProperty("fname_error"))) + "\n");

		System.out.print("11,Firefox,index.php,First Name Error Icon," + isElementPresent(By.id(p.getProperty("fname_error"))) + ","
				+ getValue(By.id(p.getProperty("fname_error"))) + "," + getSize(By.id(p.getProperty("fname_error"))) + ","
				+ getLocation(By.id(p.getProperty("fname_error"))) + "\n");
		
		// 12 :: Last Name Label index page
		report.write("12,Firefox,index.php,Last Name Label," + isElementPresent(By.id(p.getProperty("l_name_label"))) + ","
				+ getValue(By.id(p.getProperty("l_name_label"))) + "," + getSize(By.id(p.getProperty("l_name_label"))) + ","
				+ getLocation(By.id(p.getProperty("l_name_label"))) + "\n");

		System.out.print("12,Firefox,index.php,Last Name Label," + isElementPresent(By.id(p.getProperty("l_name_label"))) + ","
				+ getValue(By.id(p.getProperty("l_name_label"))) + "," + getSize(By.id(p.getProperty("l_name_label"))) + ","
				+ getLocation(By.id(p.getProperty("l_name_label"))) + "\n");
		
		// 13 :: Last Name Error Icon index page
		report.write("13,Firefox,index.php,Last Name Error Icon," + isElementPresent(By.id(p.getProperty("lname_error"))) + ","
				+ getValue(By.id(p.getProperty("lname_error"))) + "," + getSize(By.id(p.getProperty("lname_error"))) + ","
				+ getLocation(By.id(p.getProperty("lname_error"))) + "\n");

		System.out.print("13,Firefox,index.php,Last Name Error Icon," + isElementPresent(By.id(p.getProperty("lname_error"))) + ","
				+ getValue(By.id(p.getProperty("lname_error"))) + "," + getSize(By.id(p.getProperty("lname_error"))) + ","
				+ getLocation(By.id(p.getProperty("lname_error"))) + "\n");
		
		// 14 :: Email Field Label index page
		report.write("14,Firefox,index.php,Email Field Label," + isElementPresent(By.id(p.getProperty("email_label"))) + ","
				+ getValue(By.id(p.getProperty("email_label"))) + "," + getSize(By.id(p.getProperty("email_label"))) + ","
				+ getLocation(By.id(p.getProperty("email_label"))) + "\n");

		System.out.print("14,Firefox,index.php,Last Email Field Label," + isElementPresent(By.id(p.getProperty("email_label"))) + ","
				+ getValue(By.id(p.getProperty("email_label"))) + "," + getSize(By.id(p.getProperty("email_label"))) + ","
				+ getLocation(By.id(p.getProperty("email_label"))) + "\n");
		
		// 15 :: Email Error index page
		report.write("15,Firefox,index.php,Email Error," + isElementPresent(By.id(p.getProperty("email_error"))) + ","
				+ getValue(By.id(p.getProperty("email_error"))) + "," + getSize(By.id(p.getProperty("email_error"))) + ","
				+ getLocation(By.id(p.getProperty("email_error"))) + "\n");

		System.out.print("15,Firefox,index.php,Last Email Error," + isElementPresent(By.id(p.getProperty("email_error"))) + ","
				+ getValue(By.id(p.getProperty("email_error"))) + "," + getSize(By.id(p.getProperty("email_error"))) + ","
				+ getLocation(By.id(p.getProperty("email_error"))) + "\n");
		
		// 16 :: Phone Field Label index page
		report.write("16,Firefox,index.php,Phone Field Label," + isElementPresent(By.id(p.getProperty("phone_label"))) + ","
				+ getValue(By.id(p.getProperty("phone_label"))) + "," + getSize(By.id(p.getProperty("phone_label"))) + ","
				+ getLocation(By.id(p.getProperty("phone_label"))) + "\n");

		System.out.print("16,Firefox,index.php,Phone Filed Label," + isElementPresent(By.id(p.getProperty("phone_label"))) + ","
				+ getValue(By.id(p.getProperty("phone_label"))) + "," + getSize(By.id(p.getProperty("phone_label"))) + ","
				+ getLocation(By.id(p.getProperty("phone_label"))) + "\n");
		
		// 17 :: Phone Error index page
		report.write("17,Firefox,index.php,Phone Error," + isElementPresent(By.id(p.getProperty("phone_error"))) + ","
				+ getValue(By.id(p.getProperty("phone_error"))) + "," + getSize(By.id(p.getProperty("phone_error"))) + ","
				+ getLocation(By.id(p.getProperty("phone_error"))) + "\n");

		System.out.print("17,Firefox,index.php,Phone Error," + isElementPresent(By.id(p.getProperty("phone_error"))) + ","
				+ getValue(By.id(p.getProperty("phone_error"))) + "," + getSize(By.id(p.getProperty("phone_error"))) + ","
				+ getLocation(By.id(p.getProperty("phone_error"))) + "\n");
		
		// 18 :: Gender Field Label index page
		report.write("18,Firefox,index.php,Gender Field Label," + isElementPresent(By.id(p.getProperty("gender_label"))) + ","
				+ getValue(By.id(p.getProperty("gender_label"))) + "," + getSize(By.id(p.getProperty("gender_label"))) + ","
				+ getLocation(By.id(p.getProperty("gender_label"))) + "\n");

		System.out.print("18,Firefox,index.php, Gender Field Label," + isElementPresent(By.id(p.getProperty("gender_label"))) + ","
				+ getValue(By.id(p.getProperty("gender_label"))) + "," + getSize(By.id(p.getProperty("gender_label"))) + ","
				+ getLocation(By.id(p.getProperty("gender_label"))) + "\n");
		
		// 19 :: Gender Male Button index page
		report.write("19,Firefox,index.php,Gender Male Button," + isElementPresent(By.id(p.getProperty("gender_male"))) + ","
				+ getValue(By.id(p.getProperty("gender_male"))) + "," + getSize(By.id(p.getProperty("gender_male"))) + ","
				+ getLocation(By.id(p.getProperty("gender_male"))) + "\n");
		driver.findElement(By.id(p.getProperty("gender_male"))).click();

		System.out.print("19,Firefox,index.php, Gender Male Button," + isElementPresent(By.id(p.getProperty("gender_male"))) + ","
				+ getValue(By.id(p.getProperty("gender_male"))) + "," + getSize(By.id(p.getProperty("gender_male"))) + ","
				+ getLocation(By.id(p.getProperty("gender_male"))) + "\n");
		
		// 20 :: Gender Male Label index page
		report.write("20,Firefox,index.php,Gender Male Label," + isElementPresent(By.id(p.getProperty("gender_male_label"))) + ","
				+ getValue(By.id(p.getProperty("gender_male_label"))) + "," + getSize(By.id(p.getProperty("gender_male_label"))) + ","
				+ getLocation(By.id(p.getProperty("gender_male_label"))) + "\n");

		System.out.print("20,Firefox,index.php, Gender Male Label," + isElementPresent(By.id(p.getProperty("gender_male_label"))) + ","
				+ getValue(By.id(p.getProperty("gender_male_label"))) + "," + getSize(By.id(p.getProperty("gender_male_label"))) + ","
				+ getLocation(By.id(p.getProperty("gender_male_label"))) + "\n");
		
		// 21 :: Gender Female Button index page
		report.write("21,Firefox,index.php,Gender Female Button," + isElementPresent(By.id(p.getProperty("gender_female"))) + ","
				+ getValue(By.id(p.getProperty("gender_female"))) + "," + getSize(By.id(p.getProperty("gender_female"))) + ","
				+ getLocation(By.id(p.getProperty("gender_female"))) + "\n");

		System.out.print("21,Firefox,index.php, Gender Female Button," + isElementPresent(By.id(p.getProperty("gender_female"))) + ","
				+ getValue(By.id(p.getProperty("gender_female"))) + "," + getSize(By.id(p.getProperty("gender_female"))) + ","
				+ getLocation(By.id(p.getProperty("gender_female"))) + "\n");
		
		// 22 :: Gender Female Label index page
		report.write("22,Firefox,index.php,Gender Female Label," + isElementPresent(By.id(p.getProperty("gender_female_label"))) + ","
				+ getValue(By.id(p.getProperty("gender_female_label"))) + "," + getSize(By.id(p.getProperty("gender_female_label"))) + ","
				+ getLocation(By.id(p.getProperty("gender_female_label"))) + "\n");

		System.out.print("22,Firefox,index.php, Gender Female Label," + isElementPresent(By.id(p.getProperty("gender_female_label"))) + ","
				+ getValue(By.id(p.getProperty("gender_female_label"))) + "," + getSize(By.id(p.getProperty("gender_female_label"))) + ","
				+ getLocation(By.id(p.getProperty("gender_female_label"))) + "\n");
		
		// 23 :: State Label index page
		report.write("23,Firefox,index.php,State Label," + isElementPresent(By.id(p.getProperty("state_label"))) + ","
				+ getValue(By.id(p.getProperty("state_label"))) + "," + getSize(By.id(p.getProperty("state_label"))) + ","
				+ getLocation(By.id(p.getProperty("state_label"))) + "\n");

		System.out.print("23,Firefox,index.php, State Label," + isElementPresent(By.id(p.getProperty("state_label"))) + ","
				+ getValue(By.id(p.getProperty("state_label"))) + "," + getSize(By.id(p.getProperty("state_label"))) + ","
				+ getLocation(By.id(p.getProperty("state_label"))) + "\n");
		
		// 24 :: State index page
		report.write("24,Firefox,index.php,State," + isElementPresent(By.id(p.getProperty("state"))) + ","
				+ getValue(By.id(p.getProperty("state"))) + "," + getSize(By.id(p.getProperty("state"))) + ","
				+ getLocation(By.id(p.getProperty("state"))) + "\n");

		System.out.print("24,Firefox,index.php, State," + isElementPresent(By.id(p.getProperty("state"))) + ","
				+ getValue(By.id(p.getProperty("state"))) + "," + getSize(By.id(p.getProperty("state"))) + ","
				+ getLocation(By.id(p.getProperty("state"))) + "\n");

		// 25 :: Terms index page
		report.write("25,Firefox,index.php,Terms," + isElementPresent(By.id(p.getProperty("terms"))) + ","
				+ getValue(By.id(p.getProperty("terms"))) + "," + getSize(By.id(p.getProperty("terms"))) + ","
				+ getLocation(By.id(p.getProperty("terms"))) + "\n");

		System.out.print("25,Firefox,index.php, Terms," + isElementPresent(By.id(p.getProperty("terms"))) + ","
				+ getValue(By.id(p.getProperty("terms"))) + "," + getSize(By.id(p.getProperty("terms"))) + ","
				+ getLocation(By.id(p.getProperty("terms"))) + "\n");
		driver.findElement(By.id(p.getProperty("terms"))).click();
		
		// 26 :: Terms Label index page
		report.write("26,Firefox,index.php,Terms Label," + isElementPresent(By.id(p.getProperty("terms_label"))) + ","
				+ getValue(By.id(p.getProperty("terms_label"))) + "," + getSize(By.id(p.getProperty("terms_label"))) + ","
				+ getLocation(By.id(p.getProperty("terms_label"))) + "\n");

		System.out.print("26,Firefox,index.php, Terms Label," + isElementPresent(By.id(p.getProperty("terms_label"))) + ","
				+ getValue(By.id(p.getProperty("terms_label"))) + "," + getSize(By.id(p.getProperty("terms_label"))) + ","
				+ getLocation(By.id(p.getProperty("terms_label"))) + "\n");
		
		// 27 :: Error Line index page
		report.write("27,Firefox,index.php,Error Line," + isElementPresent(By.id(p.getProperty("error_line"))) + ","
				+ getValue(By.id(p.getProperty("error_line"))) + "," + getSize(By.id(p.getProperty("error_line"))) + ","
				+ getLocation(By.id(p.getProperty("error_line"))) + "\n");

		System.out.print("27,Firefox,index.php, Error Line," + isElementPresent(By.id(p.getProperty("error_line"))) + ","
				+ getValue(By.id(p.getProperty("error_line"))) + "," + getSize(By.id(p.getProperty("error_line"))) + ","
				+ getLocation(By.id(p.getProperty("error_line"))) + "\n");
		
		// 28 :: Facebook index page
		report.write("28,Firefox,index.php,Facebook," + isElementPresent(By.id(p.getProperty("facebook"))) + ","
				+ getValue(By.id(p.getProperty("facebook"))) + "," + getSize(By.id(p.getProperty("facebook"))) + ","
				+ getLocation(By.id(p.getProperty("facebook"))) + "\n");

		System.out.print("28,Firefox,index.php, Facebook," + isElementPresent(By.id(p.getProperty("facebook"))) + ","
				+ getValue(By.id(p.getProperty("facebook"))) + "," + getSize(By.id(p.getProperty("facebook"))) + ","
				+ getLocation(By.id(p.getProperty("facebook"))) + "\n");
		
		// 29 :: Facebook Icon index page
		report.write("29,Firefox,index.php,Facebook Icon," + isElementPresent(By.id(p.getProperty("facebook_icon"))) + ","
				+ getValue(By.id(p.getProperty("facebook_icon"))) + "," + getSize(By.id(p.getProperty("facebook_icon"))) + ","
				+ getLocation(By.id(p.getProperty("facebook_icon"))) + "\n");

		System.out.print("29,Firefox,index.php, Facebook Icon," + isElementPresent(By.id(p.getProperty("facebook_icon"))) + ","
				+ getValue(By.id(p.getProperty("facebook_icon"))) + "," + getSize(By.id(p.getProperty("facebook_icon"))) + ","
				+ getLocation(By.id(p.getProperty("facebook_icon"))) + "\n");
		
		// 30 :: Twitter index page
		report.write("30,Firefox,index.php,Twitter," + isElementPresent(By.id(p.getProperty("twitter"))) + ","
				+ getValue(By.id(p.getProperty("twitter"))) + "," + getSize(By.id(p.getProperty("twitter"))) + ","
				+ getLocation(By.id(p.getProperty("twitter"))) + "\n");

		System.out.print("30,Firefox,index.php, Twitter," + isElementPresent(By.id(p.getProperty("twitter"))) + ","
				+ getValue(By.id(p.getProperty("twitter"))) + "," + getSize(By.id(p.getProperty("twitter"))) + ","
				+ getLocation(By.id(p.getProperty("twitter"))) + "\n");
		
		// 31 :: Twitter Icon index page
		report.write("31,Firefox,index.php,Twitter Icon," + isElementPresent(By.id(p.getProperty("twitter_icon"))) + ","
				+ getValue(By.id(p.getProperty("twitter_icon"))) + "," + getSize(By.id(p.getProperty("twitter_icon"))) + ","
				+ getLocation(By.id(p.getProperty("twitter_icon"))) + "\n");

		System.out.print("31,Firefox,index.php, Twitter Icon," + isElementPresent(By.id(p.getProperty("twitter_icon"))) + ","
				+ getValue(By.id(p.getProperty("twitter_icon"))) + "," + getSize(By.id(p.getProperty("twitter_icon"))) + ","
				+ getLocation(By.id(p.getProperty("twitter_icon"))) + "\n");
		
		// 32 :: Flickr index page
		report.write("32,Firefox,index.php,Flickr," + isElementPresent(By.id(p.getProperty("flickr"))) + ","
				+ getValue(By.id(p.getProperty("flickr"))) + "," + getSize(By.id(p.getProperty("flickr"))) + ","
				+ getLocation(By.id(p.getProperty("flickr"))) + "\n");

		System.out.print("32,Firefox,index.php, Flickr," + isElementPresent(By.id(p.getProperty("flickr"))) + ","
				+ getValue(By.id(p.getProperty("flickr"))) + "," + getSize(By.id(p.getProperty("flickr"))) + ","
				+ getLocation(By.id(p.getProperty("flickr"))) + "\n");
		
		// 33 :: Flickr Icon index page
		report.write("33,Firefox,index.php,Flickr Icon," + isElementPresent(By.id(p.getProperty("flickr_icon"))) + ","
				+ getValue(By.id(p.getProperty("flickr_icon"))) + "," + getSize(By.id(p.getProperty("flickr_icon"))) + ","
				+ getLocation(By.id(p.getProperty("flickr_icon"))) + "\n");

		System.out.print("33,Firefox,index.php, Flickr Icon," + isElementPresent(By.id(p.getProperty("flickr_icon"))) + ","
				+ getValue(By.id(p.getProperty("flickr_icon"))) + "," + getSize(By.id(p.getProperty("flickr_icon"))) + ","
				+ getLocation(By.id(p.getProperty("flickr_icon"))) + "\n");
		
		// 34 :: Youtube index page
		report.write("34,Firefox,index.php,Youtube," + isElementPresent(By.id(p.getProperty("youtube"))) + ","
				+ getValue(By.id(p.getProperty("youtube"))) + "," + getSize(By.id(p.getProperty("youtube"))) + ","
				+ getLocation(By.id(p.getProperty("youtube"))) + "\n");

		System.out.print("34,Firefox,index.php,Youtube," + isElementPresent(By.id(p.getProperty("youtube"))) + ","
				+ getValue(By.id(p.getProperty("youtube"))) + "," + getSize(By.id(p.getProperty("youtube"))) + ","
				+ getLocation(By.id(p.getProperty("youtube"))) + "\n");
		
		// 35 :: Youtube Icon index page
		report.write("35,Firefox,index.php,Youtube Icon," + isElementPresent(By.id(p.getProperty("youtube_icon"))) + ","
				+ getValue(By.id(p.getProperty("youtube_icon"))) + "," + getSize(By.id(p.getProperty("youtube_icon"))) + ","
				+ getLocation(By.id(p.getProperty("youtube_icon"))) + "\n");

		System.out.print("35,Firefox,index.php,Youtube Icon," + isElementPresent(By.id(p.getProperty("youtube_icon"))) + ","
				+ getValue(By.id(p.getProperty("youtube_icon"))) + "," + getSize(By.id(p.getProperty("youtube_icon"))) + ","
				+ getLocation(By.id(p.getProperty("youtube_icon"))) + "\n");
		
		// 36 :: Reset Button index page
		report.write("36,Firefox,index.php,Reset Button," + isElementPresent(By.id(p.getProperty("reset_button"))) + ","
				+ getValue(By.id(p.getProperty("reset_button"))) + "," + getSize(By.id(p.getProperty("reset_button"))) + ","
				+ getLocation(By.id(p.getProperty("reset_button"))) + "\n");

		System.out.print("36,Firefox,index.php,Reset Button," + isElementPresent(By.id(p.getProperty("reset_button"))) + ","
				+ getValue(By.id(p.getProperty("reset_button"))) + "," + getSize(By.id(p.getProperty("reset_button"))) + ","
				+ getLocation(By.id(p.getProperty("reset_button"))) + "\n");
		
		// 37 :: Submit Button index page
		report.write("37,Firefox,index.php,Submit Button," + isElementPresent(By.id(p.getProperty("submit_button"))) + ","
				+ getValue(By.id(p.getProperty("submit_button"))) + "," + getSize(By.id(p.getProperty("submit_button"))) + ","
				+ getLocation(By.id(p.getProperty("submit_button"))) + "\n");

		System.out.print("37,Firefox,index.php,Submit Button," + isElementPresent(By.id(p.getProperty("submit_button"))) + ","
				+ getValue(By.id(p.getProperty("submit_button"))) + "," + getSize(By.id(p.getProperty("submit_button"))) + ","
				+ getLocation(By.id(p.getProperty("submit_button"))) + "\n");
		
		// 38 :: TimeStamp index page
		report.write("38,Firefox,index.php,TimeStamp," + isElementPresent(By.id(p.getProperty("timestamp"))) + ","
				+ getValue(By.id(p.getProperty("timestamp"))) + "," + getSize(By.id(p.getProperty("timestamp"))) + ","
				+ getLocation(By.id(p.getProperty("timestamp"))) + "\n");

		System.out.print("38,Firefox,index.php,TimeStamp," + isElementPresent(By.id(p.getProperty("timestamp"))) + ","
				+ getValue(By.id(p.getProperty("timestamp"))) + "," + getSize(By.id(p.getProperty("timestamp"))) + ","
				+ getLocation(By.id(p.getProperty("timestamp"))) + "\n");
		
		// 39 :: Copyright index page
		report.write("39,Firefox,index.php,Copyright," + isElementPresent(By.id(p.getProperty("copyright"))) + ","
				+ getValue(By.id(p.getProperty("copyright"))) + "," + getSize(By.id(p.getProperty("copyright"))) + ","
				+ getLocation(By.id(p.getProperty("copyright"))) + "\n");

		System.out.print("39,Firefox,index.php,Copyright," + isElementPresent(By.id(p.getProperty("copyright"))) + ","
				+ getValue(By.id(p.getProperty("copyright"))) + "," + getSize(By.id(p.getProperty("copyright"))) + ","
				+ getLocation(By.id(p.getProperty("copyright"))) + "\n");
		
		// 40 :: OS & Browser index page
		report.write("40,Firefox,index.php,OS & Browser," + isElementPresent(By.id(p.getProperty("os_browser"))) + ","
				+ getValue(By.id(p.getProperty("os_browser"))) + "," + getSize(By.id(p.getProperty("os_browser"))) + ","
				+ getLocation(By.id(p.getProperty("os_browser"))) + "\n");

		System.out.print("40,Firefox,index.php,OS & Browser," + isElementPresent(By.id(p.getProperty("os_browser"))) + ","
				+ getValue(By.id(p.getProperty("os_browser"))) + "," + getSize(By.id(p.getProperty("os_browser"))) + ","
				+ getLocation(By.id(p.getProperty("os_browser"))) + "\n");
	
		// SUBMIT
		driver.findElement(By.id(p.getProperty("submit_id"))).submit();

		// Thread.sleep(1000);

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleIs("Confirmation"));

		// 01 :: First Name confirmation page
		report.write("01,Firefox,confirmation.php,First Name," + isElementPresent(By.id(p.getProperty("fname_id"))) + ","
				+ getValue(By.id(p.getProperty("fname_id"))) + "," + getSize(By.id(p.getProperty("fname_id"))) + ","
				+ getLocation(By.id(p.getProperty("fname_id"))) + "\n");
		System.out.print("01,Firefox,confirmation.php,First Name," + isElementPresent(By.id(p.getProperty("fname_id")))
				+ "," + getValue(By.id(p.getProperty("fname_id"))) + "," + getSize(By.id(p.getProperty("fname_id"))) + ","
				+ getLocation(By.id(p.getProperty("fname_id"))) + "\n");

		// 02 :: Last Name confirmation page
		report.write("02,Firefox,confirmation.php,Last Name," + isElementPresent(By.id(p.getProperty("lname_id"))) + ","
				+ getValue(By.id(p.getProperty("lname_id"))) + "," + getSize(By.id(p.getProperty("lname_id"))) + ","
				+ getLocation(By.id(p.getProperty("lname_id"))) + "\n");
		System.out.print("02,Firefox,confirmation.php,Last Name," + isElementPresent(By.id(p.getProperty("lname_id")))
				+ "," + getValue(By.id(p.getProperty("lname_id"))) + "," + getSize(By.id(p.getProperty("lname_id"))) + ","
				+ getLocation(By.id(p.getProperty("lname_id"))) + "\n");

		// 03 :: Email confirmation page
		report.write("03,Firefox,confirmation.php,Email," + isElementPresent(By.id(p.getProperty("email_id"))) + ","
				+ getValue(By.id(p.getProperty("email_id"))) + "," + getSize(By.id(p.getProperty("email_id"))) + ","
				+ getLocation(By.id(p.getProperty("email_id"))) + "\n");
		System.out.print("03,Firefox,confirmation.php,Email," + isElementPresent(By.id(p.getProperty("email_id"))) + ","
				+ getValue(By.id(p.getProperty("email_id"))) + "," + getSize(By.id(p.getProperty("email_id"))) + ","
				+ getLocation(By.id(p.getProperty("email_id"))) + "\n");

		// 04 :: Phone confirmation page
		report.write("04,Firefox,confirmation.php,Phone," + isElementPresent(By.id(p.getProperty("phone_id"))) + ","
				+ getValue(By.id(p.getProperty("phone_id"))) + "," + getSize(By.id(p.getProperty("phone_id"))) + ","
				+ getLocation(By.id(p.getProperty("phone_id"))) + "\n");
		System.out.print("04,Firefox,confirmation.php,Phone," + isElementPresent(By.id(p.getProperty("phone_id"))) + ","
				+ getValue(By.id(p.getProperty("phone_id"))) + "," + getSize(By.id(p.getProperty("phone_id"))) + ","
				+ getLocation(By.id(p.getProperty("phone_id"))) + "\n");
		
		// 05 :: Page title confirmation page
		report.write("05,Firefox,confirmation.php,Title," + isElementPresent(By.id(p.getProperty("title"))) + ","
				+ getValue(By.id(p.getProperty("title"))) + "," + getSize(By.id(p.getProperty("title"))) + ","
				+ getLocation(By.id(p.getProperty("title"))) + "\n");
		System.out.print("05,Firefox,confirmation.php,Title," + isElementPresent(By.id(p.getProperty("title"))) + ","
				+ getValue(By.id(p.getProperty("title"))) + "," + getSize(By.id(p.getProperty("title"))) + ","
				+ getLocation(By.id(p.getProperty("title"))) + "\n");
		
		// 06 :: First Name Label confirmation page
		report.write("06,Firefox,confirmation.php,First Name Label," + isElementPresent(By.id(p.getProperty("f_name_label"))) + ","
				+ getValue(By.id(p.getProperty("f_name_label"))) + "," + getSize(By.id(p.getProperty("f_name_label"))) + ","
				+ getLocation(By.id(p.getProperty("f_name_label"))) + "\n");
		System.out.print("06,Firefox,confirmation.php,First Name Label," + isElementPresent(By.id(p.getProperty("f_name_label"))) + ","
				+ getValue(By.id(p.getProperty("f_name_label"))) + "," + getSize(By.id(p.getProperty("f_name_label"))) + ","
				+ getLocation(By.id(p.getProperty("f_name_label"))) + "\n");
		
		// 07 :: Last Name Label confirmation page
		report.write("07,Firefox,confirmation.php,Last Name Label," + isElementPresent(By.id(p.getProperty("l_name_label"))) + ","
				+ getValue(By.id(p.getProperty("l_name_label"))) + "," + getSize(By.id(p.getProperty("l_name_label"))) + ","
				+ getLocation(By.id(p.getProperty("l_name_label"))) + "\n");
		System.out.print("07,Firefox,confirmation.php,Last Name Label," + isElementPresent(By.id(p.getProperty("l_name_label"))) + ","
				+ getValue(By.id(p.getProperty("l_name_label"))) + "," + getSize(By.id(p.getProperty("l_name_label"))) + ","
				+ getLocation(By.id(p.getProperty("l_name_label"))) + "\n");
		
		// 08 :: Email Label confirmation page
		report.write("08,Firefox,confirmation.php,Email Label," + isElementPresent(By.id(p.getProperty("email_label"))) + ","
				+ getValue(By.id(p.getProperty("email_label"))) + "," + getSize(By.id(p.getProperty("email_label"))) + ","
				+ getLocation(By.id(p.getProperty("email_label"))) + "\n");
		System.out.print("08,Firefox,confirmation.php,Email Label," + isElementPresent(By.id(p.getProperty("email_label"))) + ","
				+ getValue(By.id(p.getProperty("email_label"))) + "," + getSize(By.id(p.getProperty("email_label"))) + ","
				+ getLocation(By.id(p.getProperty("email_label"))) + "\n");
		
		// 09 :: Phone Label confirmation page
		report.write("09,Firefox,confirmation.php,Phone Label," + isElementPresent(By.id(p.getProperty("phone_label"))) + ","
				+ getValue(By.id(p.getProperty("phone_label"))) + "," + getSize(By.id(p.getProperty("phone_label"))) + ","
				+ getLocation(By.id(p.getProperty("phone_label"))) + "\n");
		System.out.print("09,Firefox,confirmation.php,Phone Label," + isElementPresent(By.id(p.getProperty("phone_label"))) + ","
				+ getValue(By.id(p.getProperty("phone_label"))) + "," + getSize(By.id(p.getProperty("phone_label"))) + ","
				+ getLocation(By.id(p.getProperty("phone_label"))) + "\n");
		
		// 10 :: Gender Label confirmation page
		report.write("10,Firefox,confirmation.php,Gender Label," + isElementPresent(By.id(p.getProperty("gender_label"))) + ","
				+ getValue(By.id(p.getProperty("gender_label"))) + "," + getSize(By.id(p.getProperty("gender_label"))) + ","
				+ getLocation(By.id(p.getProperty("gender_label"))) + "\n");
		System.out.print("10,Firefox,confirmation.php,Gender Label," + isElementPresent(By.id(p.getProperty("gender_label"))) + ","
				+ getValue(By.id(p.getProperty("gender_label"))) + "," + getSize(By.id(p.getProperty("gender_label"))) + ","
				+ getLocation(By.id(p.getProperty("gender_label"))) + "\n");
		
		// 11 :: Gender confirmation page
		report.write("11,Firefox,confirmation.php,Gender," + isElementPresent(By.id(p.getProperty("gender_id"))) + ","
				+ getValue(By.id(p.getProperty("gender_id"))) + "," + getSize(By.id(p.getProperty("gender_id"))) + ","
				+ getLocation(By.id(p.getProperty("gender_id"))) + "\n");
		System.out.print("11,Firefox,confirmation.php,Gender," + isElementPresent(By.id(p.getProperty("gender_id"))) + ","
				+ getValue(By.id(p.getProperty("gender_id"))) + "," + getSize(By.id(p.getProperty("gender_id"))) + ","
				+ getLocation(By.id(p.getProperty("gender_id"))) + "\n");
		
		// 12 :: State Label confirmation page
		report.write("12,Firefox,confirmation.php,State Label," + isElementPresent(By.id(p.getProperty("state_label"))) + ","
				+ getValue(By.id(p.getProperty("state_label"))) + "," + getSize(By.id(p.getProperty("state_label"))) + ","
				+ getLocation(By.id(p.getProperty("state_label"))) + "\n");
		System.out.print("12,Firefox,confirmation.php,State Label," + isElementPresent(By.id(p.getProperty("state_label"))) + ","
				+ getValue(By.id(p.getProperty("state_label"))) + "," + getSize(By.id(p.getProperty("state_label"))) + ","
				+ getLocation(By.id(p.getProperty("state_label"))) + "\n");
		
		// 13 :: Terms Label confirmation page
		report.write("13,Firefox,confirmation.php,Terms Label," + isElementPresent(By.id(p.getProperty("terms_label"))) + ","
				+ getValue(By.id(p.getProperty("terms_label"))) + "," + getSize(By.id(p.getProperty("terms_label"))) + ","
				+ getLocation(By.id(p.getProperty("terms_label"))) + "\n");
		System.out.print("13,Firefox,confirmation.php,Terms Label," + isElementPresent(By.id(p.getProperty("terms_label"))) + ","
				+ getValue(By.id(p.getProperty("terms_label"))) + "," + getSize(By.id(p.getProperty("terms_label"))) + ","
				+ getLocation(By.id(p.getProperty("terms_label"))) + "\n");
		
		// 14 :: Terms confirmation page
		report.write("14,Firefox,confirmation.php,Terms," + isElementPresent(By.id(p.getProperty("terms"))) + ","
				+ getValue(By.id(p.getProperty("terms"))) + "," + getSize(By.id(p.getProperty("terms"))) + ","
				+ getLocation(By.id(p.getProperty("terms"))) + "\n");
		System.out.print("14,Firefox,confirmation.php,Terms," + isElementPresent(By.id(p.getProperty("terms"))) + ","
				+ getValue(By.id(p.getProperty("terms"))) + "," + getSize(By.id(p.getProperty("terms"))) + ","
				+ getLocation(By.id(p.getProperty("terms"))) + "\n");
		
		// 15 :: Back Button confirmation page
		report.write("15,Firefox,confirmation.php,Back Button," + isElementPresent(By.id(p.getProperty("back_button"))) + ","
				+ getValue(By.id(p.getProperty("back_button"))) + "," + getSize(By.id(p.getProperty("back_button"))) + ","
				+ getLocation(By.id(p.getProperty("back_button"))) + "\n");
		System.out.print("15,Firefox,confirmation.php,Back Button," + isElementPresent(By.id(p.getProperty("back_button"))) + ","
				+ getValue(By.id(p.getProperty("back_button"))) + "," + getSize(By.id(p.getProperty("back_button"))) + ","
				+ getLocation(By.id(p.getProperty("back_button"))) + "\n");
		
		// 16 :: Copyright confirmation page
		report.write("16,Firefox,confirmation.php,Back Copyright," + isElementPresent(By.id(p.getProperty("copyright"))) + ","
				+ getValue(By.id(p.getProperty("copyright"))) + "," + getSize(By.id(p.getProperty("copyright"))) + ","
				+ getLocation(By.id(p.getProperty("copyright"))) + "\n");
		System.out.print("16,Firefox,confirmation.php,Copyright," + isElementPresent(By.id(p.getProperty("copyright"))) + ","
				+ getValue(By.id(p.getProperty("copyright"))) + "," + getSize(By.id(p.getProperty("copyright"))) + ","
				+ getLocation(By.id(p.getProperty("copyright"))) + "\n");

		report.flush();
		report.close();
		driver.quit();

	}

}