import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class KonaSL_assignment_question_B_automation {
    public static void main(String[] args) throws InterruptedException {

        //Initialize Chrome Driver
        WebDriver driver = new ChromeDriver();
        //Navigate to OrangeHRM
        driver.get("https://opensource-demo.orangehrmlive.com/");

        //Get current url and verify it with expected url.\
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.startsWith("https://opensource-demo.orangehrmlive.com/")) {
            System.out.println("URL has been verified.");
        } else {
            System.out.println("URL is different from expected");
        }

        //Get actual tile and compare with expected title
        String getTitle = driver.getTitle();
        if (Objects.equals(getTitle, "OrangeHRM")) {
            System.out.println("Title has been verified.");
        } else {
            System.out.println("Title is different from expected.");
        }

        //Setting up explicit and implicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Initialize variable with values to verify expected and actual results later-on
        String firstName = "Khalid", lastName = "Redwan", workTelephone = "01713269987", workEmail = "khalidredwan403@gmail.com";

        //Enroll an employee
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.className("orangehrm-login-button")).click();
        driver.findElement(By.cssSelector("a[href='/web/index.php/pim/viewPimModule']")).click();
        driver.findElement(By.className("bi-plus")).click();
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        driver.findElement(By.className("orangehrm-left-space")).click();
        driver.findElement(By.cssSelector("a[href^='/web/index.php/pim/contactDetails/empNumber/']")).click();
        driver.findElement(By.xpath("//form/div[2]/div/div[3]/div/div[2]/input")).sendKeys(workTelephone);
        driver.findElement(By.xpath("//form/div[3]/div/div/div/div[2]/input")).sendKeys(workEmail);
        driver.findElement(By.cssSelector("a[href^='/web/index.php/pim/viewJobDetails/empNumber/']")).click();

        //Verify success message is shown.
        String success = driver.findElement(By.xpath("//html/body/div/div[2]/div/div[1]/div[2]/p[2]")).getText();
        if (Objects.equals(success, "Successfully Saved")) {
            System.out.println("New Employee saved successfully.");
        } else {
            System.out.println("New Employee adding message is missing.");
        }

        //Create action object to interact with drop-downs.
        Actions action = new Actions(driver);

        //Select job title
        WebElement jobTitle = driver.findElement(By.xpath("//form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i"));
        action.click(jobTitle).perform();
        WebElement jobTitleOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Chief Technical Officer')]")));
        action.click(jobTitleOption).perform();

        //Select job location
        WebElement location = driver.findElement(By.xpath("//form/div[1]/div/div[6]/div/div[2]/div/div/div[2]/i"));
        action.click(location).perform();
        WebElement locationOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Texas R&D')]")));
        action.click(locationOption).perform();

        //Click on save button and verify success message.
        driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/button")).click();
        success = driver.findElement(By.xpath("//html/body/div/div[2]/div/div[1]/div[2]/p[2]")).getText();
        if (Objects.equals(success, "Successfully Updated")) {
            System.out.println("Employee's information updated successfully.");
        } else {
            System.out.println("Missing success message on Employee's information update.");
        }

        //Navigate to directory
        driver.findElement(By.xpath("//html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[9]/a")).click();

        //Select job title
        WebElement jobTitleDirectory = driver.findElement(By.xpath("//form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i"));
        action.click(jobTitleDirectory).perform();
        WebElement jobTitleDirectoryOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Chief Technical Officer')]")));
        action.click(jobTitleDirectoryOption).perform();

        //Select location
        WebElement locationDirectory = driver.findElement(By.xpath("//form/div[1]/div/div[3]/div/div[2]/div/div/div[2]/i"));
        action.click(locationDirectory).perform();
        WebElement locationOptionDirectory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Texas R&D')]")));
        action.click(locationOption).perform();

        //Click on Search button
        driver.findElement(By.xpath("//form/div[2]/button[2]")).click();

        //Click on the employee with is filtered by Job Title & Location
        driver.findElement(By.xpath("//html/body/div/div[1]/div[2]/div[2]/div/div[2]/div/div[2]/div/div[1]/div")).click();

        //Compare the employee details with actual input
        String getName = driver.findElement(By.xpath("//div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/p[1]")).getText();
        String getJobTitle = driver.findElement(By.xpath("//html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/p[2]")).getText();
        String getAddress = driver.findElement(By.xpath("//html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/div[3]/div/p[2]")).getText();
        String getWorkTelephone = driver.findElement(By.xpath("//html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/div[5]/div[1]/p[2]")).getText();
        String getWorkEmail = driver.findElement(By.xpath("//html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/div[6]/div[1]/p[2]")).getText();
        String enteredName = firstName + " " + lastName;
        if (Objects.equals(getName, enteredName)) {
            System.out.println("Employee name has been verified.");
        } else {
            System.out.println("Employee name is different from expected.");
        }
        if (Objects.equals(getJobTitle, "Chief Technical Officer")) {
            System.out.println("Employee job title has been verified.");
        } else {
            System.out.println("Employee job title is different from expected.");
        }
        if (Objects.equals(getAddress, "Texas R&D")) {
            System.out.println("Employee job location has been verified.");
        } else {
            System.out.println("Employee job location is different from expected.");
        }
        if (Objects.equals(getWorkTelephone, workTelephone)) {
            System.out.println("Employee work telephone number has been verified.");
        } else {
            System.out.println("Employee work telephone number is different from expected.");
        }
        if (Objects.equals(getWorkEmail, workEmail)) {
            System.out.println("Employee work email address has been verified.");
        } else {
            System.out.println("Employee work email address is different from expected.");
        }

        //Logout
        WebElement profileIcon = driver.findElement(By.xpath("//html/body/div/div[1]/div[1]/header/div[1]/div[2]/ul/li/span"));
        action.click(profileIcon).perform();
        WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Logout')]")));
        action.click(logout).perform();

        //Get current url and verify it with expected url.\
        currentUrl = driver.getCurrentUrl();
        if (currentUrl.startsWith("https://opensource-demo.orangehrmlive.com/")) {
            System.out.println("URL has been verified.");
        } else {
            System.out.println("URL is different from expected");
        }

        //Close the browser
        driver.quit();
    }
}
