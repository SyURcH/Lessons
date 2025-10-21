package com.mts.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class PaymentModalPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Сумма')]/following-sibling::*")
    private WebElement amountText;

    @FindBy(xpath = "//*[contains(text(), 'Номер')]/following-sibling::*")
    private WebElement phoneNumberText;

    @FindBy(xpath = "//button[contains(text(), 'Оплатить')]")
    private WebElement payButton;

    @FindBy(xpath = "//input[@placeholder='Номер карты']")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//input[@placeholder='Срок действия']")
    private WebElement expiryDateInput;

    @FindBy(xpath = "//input[@placeholder='CVV']")
    private WebElement cvvInput;

    @FindBy(xpath = "//div[contains(@class, 'card-icons')]//img")
    private List<WebElement> cardIcons;

    public PaymentModalPage(WebDriver driver) {
        super(driver);
    }

    public String getAmount() {
        wait.until(ExpectedConditions.visibilityOf(amountText));
        return amountText.getText();
    }

    public String getPhoneNumber() {
        return phoneNumberText.getText();
    }

    public String getPayButtonText() {
        return payButton.getText();
    }

    public String getCardNumberPlaceholder() {
        return cardNumberInput.getAttribute("placeholder");
    }

    public String getExpiryDatePlaceholder() {
        return expiryDateInput.getAttribute("placeholder");
    }

    public String getCvvPlaceholder() {
        return cvvInput.getAttribute("placeholder");
    }

    public int getCardIconsCount() {
        return cardIcons.size();
    }

    public boolean isCardIconsDisplayed() {
        return !cardIcons.isEmpty() && cardIcons.get(0).isDisplayed();
    }
}