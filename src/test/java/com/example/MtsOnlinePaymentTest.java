package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;

public class MtsOnlinePaymentTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get("https://mts.by");

        handleCookieNotification();
    }

    private void handleCookieNotification() {
        try {
            WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Принять') or contains(text(), 'Согласен') or contains(text(), 'Принять все')]")));
            cookieAccept.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Куки-уведомление не найдено или уже закрыто: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void testBlockTitle() {
        WebElement blockTitle = null;

        String[] possibleSelectors = {
                "//*[contains(text(), 'Онлайн пополнение')]",
                "//*[contains(text(), 'Пополнение') and contains(text(), 'без комиссии')]",
                "//h2[contains(text(), 'Онлайн')]",
                "//div[contains(@class, 'payment')]//*[contains(text(), 'Онлайн')]",
                "//section[.//*[contains(text(), 'Онлайн пополнение')]]"
        };

        for (String selector : possibleSelectors) {
            try {
                blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selector)));
                break;
            } catch (TimeoutException e) {
                continue;
            }
        }

        if (blockTitle == null) {
            System.out.println("Текущий URL: " + driver.getCurrentUrl());
            System.out.println("Заголовок страницы: " + driver.getTitle());

            List<WebElement> allHeaders = driver.findElements(By.xpath("//h1 | //h2 | //h3 | //h4"));
            System.out.println("Найденные заголовки на странице:");
            for (WebElement header : allHeaders) {
                System.out.println("- " + header.getText());
            }
            fail("Блок 'Онлайн пополнение без комиссии' не найден на странице");
        }

        assertTrue(blockTitle.isDisplayed(), "Название блока не отображается");
        assertTrue(blockTitle.getText().contains("Онлайн") || blockTitle.getText().contains("пополнение"),
                "Название блока не содержит ожидаемый текст");
    }

    @Test(priority = 2)
    public void testPaymentSystemLogos() {
        List<WebElement> logos = driver.findElements(By.xpath(
                "//img[contains(@alt, 'Visa') or contains(@alt, 'MasterCard') or " +
                        "contains(@alt, 'Белкарт') or contains(@src, 'visa') or contains(@src, 'mastercard') or " +
                        "contains(@class, 'payment') or contains(@class, 'card')]"
        ));

        if (logos.isEmpty()) {
            logos = driver.findElements(By.cssSelector(".payment-section img, .payment-block img, [class*='payment'] img"));
        }

        assertFalse(logos.isEmpty(), "Логотипы платёжных систем не найдены");
        System.out.println("Найдено логотипов: " + logos.size());

        boolean atLeastOneVisible = false;
        for (WebElement logo : logos) {
            if (logo.isDisplayed()) {
                atLeastOneVisible = true;
                break;
            }
        }
        assertTrue(atLeastOneVisible, "Ни один логотип не отображается на странице");
    }

    @Test(priority = 3)
    public void testDetailsLink() {
        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Подробнее') or contains(text(), 'подробнее') or contains(@href, 'service')]")));

        String originalUrl = driver.getCurrentUrl();
        detailsLink.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(originalUrl)));

        String newUrl = driver.getCurrentUrl();
        assertNotEquals(newUrl, originalUrl, "Переход по ссылке не произошёл");
        assertTrue(newUrl.contains("mts.by"), "Открыта страница не на домене mts.by");

        driver.navigate().back();
        wait.until(ExpectedConditions.urlToBe(originalUrl));
    }

    @Test(priority = 4)
    public void testContinueButton() {
        try {
            WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[contains(@placeholder, 'номер') or contains(@name, 'phone') or contains(@id, 'phone') or @type='tel']")
            ));

            phoneInput.clear();
            phoneInput.sendKeys("297777777");
            System.out.println("✓ Номер телефона введен");

            WebElement amountInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[contains(@placeholder, 'Сумма') or contains(@name, 'amount') or contains(@id, 'amount') or @type='number']")
            ));

            amountInput.clear();
            amountInput.sendKeys("1");
            System.out.println("✓ Сумма введена");

            WebElement continueButton = null;
            String[] buttonSelectors = {
                    "//button[contains(text(), 'Продолжить')]",
                    "//button[contains(., 'Продолжить')]",
                    "//*[contains(text(), 'Продолжить') and (self::button or self::a or self::input)]",
                    "//input[@type='submit' and contains(@value, 'Продолжить')]",
                    "//button[@type='submit']"
            };

            for (String selector : buttonSelectors) {
                try {
                    continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
                    break;
                } catch (TimeoutException e) {
                    continue;
                }
            }

            if (continueButton == null) {
                fail("Кнопка 'Продолжить' не найдена или не кликабельна");
            }

            System.out.println("✓ Кнопка 'Продолжить' найдена, текст: " + continueButton.getText());

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", continueButton);
            Thread.sleep(500); // Небольшая пауза после прокрутки

            try {
                js.executeScript("arguments[0].click();", continueButton);
                System.out.println("✓ Клик выполнен через JavaScript");
            } catch (Exception jsException) {
                System.out.println("JavaScript клик не сработал, пробуем обычный клик...");
                continueButton.click();
                System.out.println("✓ Клик выполнен обычным способом");
            }

            int windowsBefore = driver.getWindowHandles().size();

            boolean paymentFormOpened = false;

            Thread.sleep(3000);

            try {
                if (driver.getWindowHandles().size() > windowsBefore) {
                    paymentFormOpened = true;
                    System.out.println("✓ Открылось новое окно с формой оплаты");
                }
            } catch (Exception e) {
            }

            if (!paymentFormOpened) {
                try {
                    WebElement modal = driver.findElement(By.xpath(
                            "//div[contains(@class, 'modal') or contains(@class, 'popup') or contains(@class, 'overlay') or contains(@style, 'display: block')]"
                    ));
                    if (modal.isDisplayed()) {
                        paymentFormOpened = true;
                        System.out.println("✓ Открылось модальное окно с формой оплаты");
                    }
                } catch (NoSuchElementException e) {
                }
            }

            if (!paymentFormOpened) {
                try {
                    WebElement paymentIframe = driver.findElement(By.xpath(
                            "//iframe[contains(@src, 'payment') or contains(@src, 'pay') or contains(@src, 'bepaid')]"
                    ));
                    if (paymentIframe.isDisplayed()) {
                        paymentFormOpened = true;
                        System.out.println("✓ Открылся iframe с формой оплаты");
                    }
                } catch (NoSuchElementException e) {
                }
            }

            if (!paymentFormOpened) {
                try {
                    WebElement paymentText = driver.findElement(By.xpath(
                            "//*[contains(text(), 'Оплата: Услуги связи') or contains(text(), 'Оплатить 1.00') or contains(text(), 'bePaid')]"
                    ));
                    if (paymentText.isDisplayed()) {
                        paymentFormOpened = true;
                        System.out.println("✓ Форма оплаты отобразилась на странице");
                    }
                } catch (NoSuchElementException e) {
                }
            }

            assertTrue(paymentFormOpened, "Форма оплаты не отобразилась после нажатия кнопки 'Продолжить'");
            System.out.println("✓ Тест пройден: форма оплаты успешно открылась");

        } catch (Exception e) {
            fail("Ошибка в тесте: " + e.getMessage());
        }
    }

    private WebElement findPaymentBlock() {
        String[] blockSelectors = {
                "//*[contains(text(), 'Онлайн пополнение')]/ancestor::div[contains(@class, 'container') or contains(@class, 'block') or contains(@class, 'section')]",
                "//div[contains(@class, 'payment')]",
                "//section[contains(@class, 'payment')]",
                "//form[.//*[contains(text(), 'телефон') or contains(text(), 'номер')]]"
        };

        for (String selector : blockSelectors) {
            try {
                return driver.findElement(By.xpath(selector));
            } catch (NoSuchElementException e) {
                continue;
            }
        }
        return null;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}