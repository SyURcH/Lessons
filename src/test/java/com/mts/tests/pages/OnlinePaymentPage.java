package com.mts.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class OnlinePaymentPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Онлайн пополнение без комиссии')]")
    private WebElement blockTitle;

    @FindBy(xpath = "//div[contains(@class, 'pay-systems')]//img")
    private List<WebElement> paymentLogos;

    @FindBy(xpath = "//a[contains(text(), 'Подробнее о сервисе')]")
    private WebElement detailsLink;

    @FindBy(xpath = "//label[contains(., 'Услуги связи')]")
    private WebElement servicesTab;

    @FindBy(xpath = "//label[contains(., 'Домашний интернет')]")
    private WebElement internetTab;

    @FindBy(xpath = "//label[contains(., 'Рассрочка')]")
    private WebElement installmentTab;

    @FindBy(xpath = "//label[contains(., 'Задолженность')]")
    private WebElement debtTab;

    @FindBy(xpath = "//input[@type='tel' or contains(@placeholder, 'номер')]")
    private WebElement phoneInput;

    @FindBy(xpath = "//button[contains(text(), 'Продолжить')]")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@placeholder]")
    private List<WebElement> inputPlaceholders;

    public OnlinePaymentPage(WebDriver driver) {
        super(driver);
    }

    public String getBlockTitle() {
        wait.until(ExpectedConditions.visibilityOf(blockTitle));
        return blockTitle.getText();
    }

    public int getPaymentLogosCount() {
        return paymentLogos.size();
    }

    public void clickDetailsLink() {
        detailsLink.click();
    }

    public void selectServicesTab() {
        servicesTab.click();
    }

    public void selectInternetTab() {
        internetTab.click();
    }

    public void selectInstallmentTab() {
        installmentTab.click();
    }

    public void selectDebtTab() {
        debtTab.click();
    }

    public void enterPhoneNumber(String phone) {
        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public String getPhoneInputPlaceholder() {
        return phoneInput.getAttribute("placeholder");
    }

    public List<String> getAllPlaceholders() {
        return inputPlaceholders.stream()
                .map(element -> element.getAttribute("placeholder"))
                .collect(java.util.stream.Collectors.toList());  // Работает в Java 11
    }

    public void switchToNewWindow() {
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
    }
}
