package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.pages.model.User;

import java.util.List;

public class SingUpPage {

    public SingUpPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    @FindBy(name = "firstname")
    private WebElement firstName;
    @FindBy(name = "lastname")
    private WebElement lastName;
    @FindBy(name = "phone")
    private WebElement phone;
    @FindBy(name = "email")
    private WebElement email;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(name = "confirmpassword")
    private WebElement confirmPassword;
    @FindBy(xpath = "//button[@type='submit' and text()=' Sign Up']")
    private WebElement singUpButton;
    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> alertDanger;


    public void setFirstName (String firstName){
       this.firstName.sendKeys(firstName);
    }
    public void setLastName (String lastName){
        this.lastName.sendKeys(lastName);
    }
    public void setPhone (String phone){
        this.phone.sendKeys(phone);
    }
    public void setEmail (String email){
        int randomNumber = (int) (Math.random()*1000);
        email = randomNumber + email;
        this.email.sendKeys(email);
    }
    public void setPassword (String password){
        this.password.sendKeys(password);
    }
    public void setConfirmPassword (String confirmPassword){
        this.confirmPassword.sendKeys(confirmPassword);
    }
    public void clickSingUpButton (){
        this.singUpButton.click();
    }

    public List<String> getAlertDanger(){
    return alertDanger.stream().map(WebElement::getText).toList();
    }

    public void fillSingUpForm(User user){
        firstName.sendKeys(user.getFirstName());
        lastName.sendKeys(user.getLastName());
        phone.sendKeys(user.getPhone());
        email.sendKeys(user.getEmail());
        password.sendKeys(user.getPassword());
        confirmPassword.sendKeys(user.getPassword());
    }

}
