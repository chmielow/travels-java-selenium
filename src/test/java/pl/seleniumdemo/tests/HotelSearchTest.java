package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<String> hotelNames = hotelSearchPage
                .setCity("Dubai")
                .setDates("19/05/2023", "22/06/2023")
                .setTravellers(1, 2)
                .performSearch()
                .getHotelName();

        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
    }

    @Test
    public void searchHotelWithoutNameTest() {

        ResultPage resultPage = new HotelSearchPage(driver)
                .setDates("19/05/2023", "22/06/2023")
                .setTravellers(1, 2)
                .performSearch();


        Assert.assertTrue(resultPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultPage.getHeadingText(), "No Results Found");

    }
}
