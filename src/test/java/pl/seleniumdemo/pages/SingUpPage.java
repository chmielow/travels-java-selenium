package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SingUpPage {

    public SingUpPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    @FindBy(name = "firstname")
    public WebElement firstName;
    @FindBy(name = "lastname")
    public WebElement lastName;
    @FindBy(name = "phone")
    public WebElement phone;
    @FindBy(name = "email")
    public WebElement email;
    @FindBy(name = "password")
    public WebElement password;
    @FindBy(name = "confirmpassword")
    public WebElement confirmPassword;
    @FindBy(xpath = "//button[@type='submit' and text()=' Sign Up']")
    public WebElement singUpButton;

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
        email =email+randomNumber+"@tester.pl";
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

}
