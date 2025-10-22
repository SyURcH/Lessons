package com.mts.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PaymentModalPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Сумма') or contains(text(), 'сумма')]/following-sibling::*")
    private WebElement amountText;

    @FindBy(xpath = "//*[contains(text(), 'Номер') or contains(text(), 'телефон')]/following-sibling::*")
    private WebElement phoneNumberText;

    @FindBy(xpath = "//button[contains(text(), 'Оплатить') or contains(text(), 'оплатить')]")
    private WebElement payButton;

    @FindBy(xpath = "//input[contains(@placeholder, 'карт') or contains(@placeholder, 'card')]")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//input[contains(@placeholder, 'срок') or contains(@placeholder, 'дей') or contains(@placeholder, 'expir')]")
    private WebElement expiryDateInput;

    @FindBy(xpath = "//input[contains(@placeholder, 'CVV') or contains(@placeholder, 'cvv')]")
    private WebElement cvvInput;

    @FindBy(xpath = "//img[contains(@src, 'card') or contains(@alt, 'card') or contains(@class, 'card')]")
    private List<WebElement> cardIcons;

    public PaymentModalPage(WebDriver driver) {
        super(driver);
    }

    public String getAmount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(amountText));
            return amountText.getText();
        } catch (Exception e) {
            return "Сумма не найдена";
        }
    }

    public String getPhoneNumber() {
        try {
            return phoneNumberText.getText();
        } catch (Exception e) {
            return "Номер телефона не найден";
        }
    }

    public String getPayButtonText() {
        try {
            return payButton.getText();
        } catch (Exception e) {
            return "Текст кнопки не найден";
        }
    }

    public String getCardNumberPlaceholder() {
        try {
            return cardNumberInput.getAttribute("placeholder");
        } catch (Exception e) {
            return "Плейсхолдер не найден";
        }
    }

    public String getExpiryDatePlaceholder() {
        try {
            return expiryDateInput.getAttribute("placeholder");
        } catch (Exception e) {
            return "Плейсхолдер не найден";
        }
    }

    public String getCvvPlaceholder() {
        try {
            return cvvInput.getAttribute("placeholder");
        } catch (Exception e) {
            return "Плейсхолдер не найден";
        }
    }

    public int getCardIconsCount() {
        try {
            return cardIcons.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isCardIconsDisplayed() {
        try {
            return !cardIcons.isEmpty() && cardIcons.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAmountDisplayed() {
        try {
            return amountText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPhoneNumberDisplayed() {
        try {
            return phoneNumberText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPayButtonDisplayed() {
        try {
            return payButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForModalToLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOf(amountText));
            Thread.sleep(2000); // Дополнительная задержка для стабильности
        } catch (Exception e) {
            System.out.println("Модальное окно не загрузилось: " + e.getMessage());
        }
    }
}