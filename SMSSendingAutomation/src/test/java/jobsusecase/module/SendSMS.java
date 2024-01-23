package jobsusecase.module;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SendSMS {

	public static void main(String args[]) throws InterruptedException, IOException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\cg_sftwrs\\chrome_drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.get("https://hh11groupsindia.com/app/login.php?a=f&info=DENIED");
		Thread.sleep(50);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@name='kt_login_user']")).sendKeys("saikrishna2022631");
		driver.findElement(By.xpath("//*[@name='kt_login_password']")).sendKeys("wfh@2022631");
		Thread.sleep(50);
		driver.findElement(By.xpath("//*[@name='kt_login1']")).click();
		Thread.sleep(100);
		driver.findElement(By.xpath("//*[@name='KT_Insert1']")).click();
		Thread.sleep(50);
//		driver.findElement(By.xpath("(//a[@class='tm-nav-link'])[1]")).click();
		driver.findElement(By.linkText("Send Message")).click();
		Thread.sleep(500);
		
		FileInputStream file=new FileInputStream("C:\\Users\\KNAGASRE\\eclipse-workspaces\\eclipse-workspace\\module\\excel_file\\numbers.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(file);
        XSSFSheet sheet=workbook.getSheetAt(0);
        int rowCount=sheet.getLastRowNum();
        int colcount= sheet.getRow(1).getLastCellNum();
        System.out.println("row count :"+rowCount+"colcount :"+colcount);

        Thread.sleep(1000);
        for (int i = 0; i <= rowCount; i++) {
          	XSSFRow celldata=sheet.getRow(i);
          	XSSFCell cell = celldata.getCell(0);
          	if (cell.getCellType() == CellType.NUMERIC) {
          	    double phoneNumberDouble = cell.getNumericCellValue();
          	    long phoneNumberLong = (long) phoneNumberDouble; // Remove decimal part
          	    String phoneNumberString = Long.toString(phoneNumberLong);
          	    driver.findElement(By.xpath("//*[@id='senderno']")).sendKeys(phoneNumberString);
          	    Thread.sleep(2000);
          	    driver.findElement(By.xpath("//*[@name='message']")).sendKeys("We provide full time, part time & work from home jobs, who are interested contact 7287097127 or email on support@hh11groupsindia.com www.hh11groupsindia.com");
          	    Thread.sleep(2000);
          	    driver.findElement(By.xpath("//*[@id='KT_Insert1']")).click();
          	    Thread.sleep(2000);
          	    driver.findElement(By.xpath("//*[@id='senderno']")).clear();
          	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
          	    driver.findElement(By.xpath("//*[@id='senderno']")).sendKeys(phoneNumberString);
          	} else {
          	    String phoneNumberString = cell.getStringCellValue();
          	    // Perform any necessary formatting on the string if needed
          	    driver.findElement(By.xpath("//*[@id='senderno']")).sendKeys(phoneNumberString);
          	}
        }
        
        Thread.sleep(1000);
        driver.findElement(By.linkText("Log Out")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name='button']")).click();

	}
}

