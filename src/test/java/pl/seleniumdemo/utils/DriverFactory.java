package pl.seleniumdemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class DriverFactory {
    public static WebDriver getDriver() throws IOException {
        String name = PropertiesLoader.loadProperty("browser.name");
        if (name.equals("firefox")) {
            return new FirefoxDriver();

        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            return new ChromeDriver(options);
        }
    }

}
