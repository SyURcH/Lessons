package com.mts.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentLogosTest extends BaseTest {

    @Test
    @DisplayName("2. Проверка наличия логотипов платёжных систем")
    void testPaymentSystemLogos() {
        try {
            List<WebElement> paymentLogos = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath("//img[contains(@src, 'payment') or contains(@alt, 'payment') or " +
                                    "contains(@class, 'payment') or contains(@class, 'logo')]")
                    ));

            if (paymentLogos.isEmpty()) {
                paymentLogos = driver.findElements(
                        By.xpath("//img[@src] | //div[contains(@class, 'payment')]//img")
                );
            }

            System.out.println("Найдено элементов с изображениями: " + paymentLogos.size());

            assertTrue(paymentLogos.size() >= 2,
                    "Найдено недостаточно логотипов. Найдено: " + paymentLogos.size());

            for (int i = 0; i < Math.min(paymentLogos.size(), 5); i++) {
                WebElement logo = paymentLogos.get(i);
                String src = logo.getAttribute("src");
                String alt = logo.getAttribute("alt");
                System.out.println("Логотип " + (i+1) + ": src=" + src + ", alt=" + alt);
            }

            System.out.println("✓ Найдено логотипов: " + paymentLogos.size());

        } catch (Exception e) {
            System.out.println("Ошибка при поиске логотипов: " + e.getMessage());
            fail("Не удалось найти логотипы платежных систем: " + e.getMessage());
        }
    }
}