package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

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

    @FindBy(xpath = "//li[@id='li_myaccount']")
    private List<WebElement> myAccountLink;
    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> singUpLink;

    private WebDriver driver;

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setCity(String cityName) {
        System.out.println("Setting city " + cityName);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        driver.findElement(By.xpath(xpath)).click();
        System.out.println("Setting city done");

    }

    public void setDates(String checkin, String checkout) {
        System.out.println("Setting dates checkin " + checkin + "checkout " + checkout);
        checkInInput.sendKeys(checkin);
        checkOutInput.sendKeys(checkout);
        System.out.println("Setting dates done");

    }

    public void setTravellers(int adultsToAdd, int childToAdd) {
        System.out.println("Setting travellers: adults + " + adultsToAdd + " child + " + childToAdd);
        travellersInput.click();
        addTravellers(adultPlusBtn, adultsToAdd);
        addTravellers(childPlusBtn, childToAdd);
        System.out.println("Setting travellers done");
    }

    private void addTravellers(WebElement travelerBtn, int numberOfTravelers) {
        for (int i = 0; i < numberOfTravelers; i++) {
            travelerBtn.click();
        }
    }

    public void performSearch() {
        System.out.println("Performing search");
        searchButton.click();
        System.out.println("Performing search done");
    }

    public void openSingUpForm() {
        myAccountLink
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        singUpLink.get(1).click();
    }

}
