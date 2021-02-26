import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

public class Main {

    public static void main(String[] args) {

        //Establecemos la propiedad webdriver.chrome.driver
        //con la ubicación del chromedriver que descargamos
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Admin\\Downloads\\chromedriver_win32\\chromedriver.exe");

        //Creamos una nueva instancia de ChromeDriver
        WebDriver driver = new ChromeDriver();

        //Hacemos una llamada a esta página
        //driver.get("https://formy-project.herokuapp.com/form");
        driver.get("https://b-ok.lat/");

        submitForm(driver);
        waitForAlertBanner(driver);
        assertEquals("It", getAlertBannerText(driver));
        driver.quit();
    }
        public static void submitForm(WebDriver driver) {
            try {
                //Cambia a una busqueda avanzada
                driver.findElement(By.id("advSearch-control")).click();
                Thread.sleep(500);

                //Selecciona el Checkbox "Exact matching"
                driver.findElement(By.id("ftcb")).click();

                //Rellena el campo "Fulltext Search"
                driver.findElement(By.id("searchFieldx")).sendKeys("IT, Hodder, King Stephen");

                //Selecciona el campo "Year from"
                driver.findElement(By.xpath("//*[@id=\"advSearch\"]/select[1]")).click();
                Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id=\"advSearch\"]/select[1]/option[13]")).click();

                //Selecciona el campo "Year to"
                driver.findElement(By.xpath("//*[@id=\"advSearch\"]/select[2]")).click();
                Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id=\"advSearch\"]/select[2]/option[13]")).click();

                //Selecciona el campo "Any language"
                driver.findElement(By.xpath("//*[@id=\"advSearch\"]/select[3]")).click();
                Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id=\"advSearch\"]/select[3]/option[19]")).click();

                //Selecciona el campo "Any Extension"
                driver.findElement(By.xpath("//*[@id=\"advSearch\"]/select[4]")).click();
                Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id=\"advSearch\"]/select[4]/option[3]")).click();

                //Selecciona el boton de busqueda
                driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div[1]/div/div[2]/div/button")).submit();
                Thread.sleep(500);

                driver.findElement(By.xpath("//*[@id=\"searchResultBox\"]/div[2]/div/table/tbody/tr/td[2]/table/tbody/tr[1]/td/h3/a")).click();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        public static void waitForAlertBanner(WebDriver driver) {
            WebDriverWait wait= new WebDriverWait(driver, 10);
            WebElement alert= wait.until((ExpectedConditions.visibilityOfElementLocated(By.className("moderatorPanelToggler"))));
        }

        public static String getAlertBannerText(WebDriver driver) {
            return driver.findElement(By.className("moderatorPanelToggler")).getText();
        }
}
