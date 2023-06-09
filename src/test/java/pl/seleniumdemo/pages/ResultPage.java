package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultPage {
    @FindBy(xpath = "//h4[contains(@class,'list_title')]")
    private List<WebElement> hotelList;
    @FindBy(xpath = "//h2[@class='text-center' and text()='No Results Found']")
    public WebElement resultHeading;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> getHotelName() {
        return hotelList.stream()
                .map(el -> el.getAttribute("textContent"))
                .toList();
    }

    public String getHeadingText() {
        return resultHeading.getText();
    }

}

