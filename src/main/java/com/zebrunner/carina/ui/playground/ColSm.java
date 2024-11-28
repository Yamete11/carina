package com.zebrunner.carina.ui.playground;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ColSm {

    @FindBy(tagName = "h3.a")
    private ExtendedWebElement heading;

    @FindBy(tagName = "a")
    private ExtendedWebElement link;

    @FindBy(tagName = "p")
    private ExtendedWebElement description;

    public ColSm(WebDriver driver) {
        this.driver = driver;
    }

    private final WebDriver driver;


    public String getHeadingText() {
        return heading.getText();
    }

    public String getLinkHref() {
        return link.getAttribute("href");
    }


    public String getDescriptionText() {
        return description.getText();
    }

    public void clickLink() {
        link.click();
    }

    @Override
    public String toString() {
        return "ColSm{" +
                "heading=" + heading +
                ", link=" + link +
                ", description=" + description +
                ", driver=" + driver +
                '}';
    }
}
