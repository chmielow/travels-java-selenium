import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelSearch {

    @Test
    public void searchHotel() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
        driver.findElement(By.name("checkin")).click();
        driver.findElements(By.xpath("//td[@class = 'day ' and text()='19']"))
                .stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .ifPresent(el -> el.click());

        driver.findElement(By.name("checkout")).sendKeys("23/08/23");
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]")).stream()
                .map(el->el.getAttribute("textContent"))
                .collect(Collectors.toList());
        System.out.println(hotelNames.size());
        hotelNames.forEach(el-> System.out.println(el));


    }
}
