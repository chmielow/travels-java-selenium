package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultPage;
import pl.seleniumdemo.utils.FileExcelReader;


import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("19/05/2023", "22/06/2023");
        hotelSearchPage.setTravellers(1, 2);
        hotelSearchPage.performSearch();
        ResultPage resultPage = new ResultPage(driver);
        List<String> hotelNames = resultPage.getHotelName();

        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
    }

    @Test
    public void searchHotelWithoutNameTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        //hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("19/05/2023", "22/06/2023");
        hotelSearchPage.setTravellers(1, 2);
        hotelSearchPage.performSearch();

        ResultPage resultPage = new ResultPage(driver);
        String temp = resultPage.getHeadingText();
        Assert.assertTrue(resultPage.resultHeading.isDisplayed());
        Assert.assertEquals(temp, "No Results Found");

    }
    @Test(dataProvider = "data")
    public void searchHotelTestWithDataProvider(String city, String hotel) {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
        hotelSearchPage.setDates("19/05/2023", "22/06/2023");
        hotelSearchPage.setTravellers(1, 2);
        hotelSearchPage.performSearch();
        ResultPage resultPage = new ResultPage(driver);
        List<String> hotelNames = resultPage.getHotelName();

        Assert.assertEquals(hotel, hotelNames.get(0));

    }
    @DataProvider
    public Object[][] data() throws IOException {
        return FileExcelReader.readExcel("testData.xlsx");
    }
}
