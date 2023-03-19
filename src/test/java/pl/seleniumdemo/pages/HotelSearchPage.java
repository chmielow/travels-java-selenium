package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HotelSearchPage {

    @FindBy(xpath ="//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath ="//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
    private WebElement hotelMatch;
    @FindBy(name = "checkin")
    private WebElement checkInInput;
    @FindBy(name = "checkout")
    private WebElement checkOutInput;
    @FindBy(id = "travellersInput")
    private WebElement travellersInput;
    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;
    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;
    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;


    public void setCity(String cityName){
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        hotelMatch.click();
    }
    public void setDates(String checkin, String checkout){
        checkInInput.sendKeys(checkin);
        checkOutInput.sendKeys(checkout);
    }
    public void setTravellers(){
        travellersInput.click();
        adultPlusBtn.click();
        childPlusBtn.click();
    }

    public void performSearch(){
        searchButton.click();
    }

}