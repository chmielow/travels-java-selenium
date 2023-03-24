package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultPage;
import pl.seleniumdemo.utils.FileExcelReader;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() throws IOException {
        ExtentTest test = extentReports.createTest("search hotel test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS, "setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("19/05/2023", "22/06/2023");
        test.log(Status.PASS, "setting dates done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(1, 2);
        test.log(Status.PASS, "setting travellers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "performing search done", SeleniumHelper.getScreenshot(driver));
        test.log(Status.PASS, "Screenshot", SeleniumHelper.getScreenshot(driver));

        ResultPage resultPage = new ResultPage(driver);
        List<String> hotelNames = resultPage.getHotelName();

        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
        test.log(Status.PASS, "assertions passed", SeleniumHelper.getScreenshot(driver));

    }

    @Test
    public void searchHotelWithoutNameTest() throws IOException {
        ExtentTest test = extentReports.createTest("search hotel test without city");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        //hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("19/05/2023", "22/06/2023");
        test.log(Status.PASS, "setting date done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(1, 2);
        test.log(Status.PASS, "setting travellers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "performing search done", SeleniumHelper.getScreenshot(driver));


        ResultPage resultPage = new ResultPage(driver);
        String temp = resultPage.getHeadingText();
        Assert.assertTrue(resultPage.resultHeading.isDisplayed());
        Assert.assertEquals(temp, "No Results Found");
        test.log(Status.PASS, "assertion done", SeleniumHelper.getScreenshot(driver));


    }

    @Test(dataProvider = "data")
    public void searchHotelTestWithDataProvider(String city, String hotel) throws IOException {
        ExtentTest test = extentReports.createTest("search hotel test with data provider for " + city);
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
        test.log(Status.PASS, "setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("19/05/2023", "22/06/2023");
        test.log(Status.PASS, "setting date done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(1, 2);
        test.log(Status.PASS, "setting travellers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "performing search done", SeleniumHelper.getScreenshot(driver));
        ResultPage resultPage = new ResultPage(driver);
        List<String> hotelNames = resultPage.getHotelName();

        Assert.assertEquals(hotel, hotelNames.get(0));
        test.log(Status.PASS, "assertion done", SeleniumHelper.getScreenshot(driver));


    }

    @DataProvider
    public Object[][] data() throws IOException {
        return FileExcelReader.readExcel("testData.xlsx");
    }
}
