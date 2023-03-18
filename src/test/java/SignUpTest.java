import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignUpTest {

    @Test
    public void signUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        String lastName = "Tester";
        int randomNumber = (int) (Math.random()*1000);
        String email ="tester"+randomNumber+"@tester.pl";
        driver.findElement(By.name("firstname")).sendKeys("Marcin");
        driver.findElement(By.name("lastname")).sendKeys("Tester");
        driver.findElement(By.name("phone")).sendKeys("123456789");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(),"Hi, Marcin Tester");










    }
}
