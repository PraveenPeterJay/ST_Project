package org.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Selenium UI Tests for the Utility Web App.
 * REQUIRES: The Spring Boot App must be running on localhost:8081.
 */
public class WebAppTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String BASE_URL = "http://localhost:8081/index.html";

    @BeforeEach
    public void setUp() {
        // Setup Chrome Driver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Fix for some browser versions
        // options.addArguments("--headless"); // Uncomment to run without opening the browser window

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Wait up to 5 seconds for elements
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close browser after each test
        }
    }

    @Test
    public void testArrayMinFunction() {
        // 1. Open Landing Page
        driver.get(BASE_URL);

        // 2. Click "Array Utils" Card
        // Note: We look for the h2 text inside the link or the link class
        WebElement arrayLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".array-card")));
        arrayLink.click();

        // 3. Select "Min Element" from Sidebar
        // The script.js populates the list. We find the list item containing text "Min Element"
        WebElement minFunc = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Min Element']")));
        minFunc.click();

        // 4. Input Data
        // script.js generates input IDs as inp-0, inp-1...
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inp-0")));
        inputField.clear();
        inputField.sendKeys("10 5 8 -3 20");

        // 5. Click Execute
        WebElement runBtn = driver.findElement(By.id("run-btn"));
        runBtn.click();

        // 6. Verify Output
        WebElement outputArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output-area")));

        // Wait for the text to change from "Waiting..." to the result
        wait.until(ExpectedConditions.textToBePresentInElement(outputArea, "Result"));

        String resultText = outputArea.getText();
        System.out.println("DEBUG Output: " + resultText);

        // Assert that the correct min value (-3) is present in the result
        assertTrue(resultText.contains("-3"), "Output should contain the minimum value -3");
        assertTrue(resultText.contains("SUCCESS"), "Status should be SUCCESS");
    }

    @Test
    public void testStringReverseFunction() {
        driver.get(BASE_URL);

        // 1. Go to String Utils
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".string-card"))).click();

        // 2. Click "Reverse String"
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Reverse String']"))).click();

        // 3. Input "Selenium"
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inp-0")));
        inputField.sendKeys("Selenium");

        // 4. Run
        driver.findElement(By.id("run-btn")).click();

        // 5. Check Output for "muineleS"
        WebElement outputArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output-area")));
        wait.until(ExpectedConditions.textToBePresentInElement(outputArea, "muineleS"));

        assertTrue(outputArea.getText().contains("muineleS"));
    }

    @Test
    public void testStackIsBalanced() {
        driver.get(BASE_URL);

        // 1. Go to Stack Utils
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".stack-card"))).click();

        // 2. Click "Is Balanced"
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Is Balanced']"))).click();

        // 3. Input Balanced String
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inp-0")));
        inputField.sendKeys("{[()]}");

        // 4. Run
        driver.findElement(By.id("run-btn")).click();

        // 5. Check Output
        WebElement outputArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output-area")));
        wait.until(ExpectedConditions.textToBePresentInElement(outputArea, "true"));

        assertTrue(outputArea.getText().contains("true"));
    }

    @Test
    public void testGraphShortestPathBFS() {
        driver.get(BASE_URL);

        // 1. Go to Graph Utils
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".graph-card"))).click();

        // 2. Click "Shortest Path (BFS)"
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'Shortest Path (BFS)')]"))).click();

        // 3. Input Graph Data
        // Input 0: Num Nodes
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inp-0"))).sendKeys("4");

        // Input 1: Edges (Text Area) - 0 connects to 1 and 2.
        driver.findElement(By.id("inp-1")).sendKeys("0 1\n0 2\n1 3");

        // Input 2: Start Node
        driver.findElement(By.id("inp-2")).sendKeys("0");

        // 4. Run
        driver.findElement(By.id("run-btn")).click();

        // 5. Check Output: Distance from 0 to 3 should be 2 (0->1->3)
        WebElement outputArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output-area")));
        wait.until(ExpectedConditions.textToBePresentInElement(outputArea, "Result"));

        // Expect array like [0, 1, 1, 2]
        String output = outputArea.getText();
        assertTrue(output.contains("[0, 1, 1, 2]"), "BFS output incorrect. Got: " + output);
    }
}