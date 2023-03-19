package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        HotelSearchPage hotelSearchPage = new HotelSearchPage();
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("19/05/2023","22/06/2023");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]")).stream()
                .map(el->el.getAttribute("textContent"))
                .toList();

        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));
    }
    @Test
    public void searchHotelWithoutNameTest() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        HotelSearchPage hotelSearchPage = new HotelSearchPage();
        //hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("19/05/2023","22/06/2023");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();

        WebElement temp = driver.findElement(By.xpath("//h2[@class='text-center' and text()='No Results Found']"));

        Assert.assertTrue(temp.isDisplayed());
        Assert.assertEquals(temp.getText(),"No Results Found");

    }
}
