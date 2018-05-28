import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.util.Scanner;

public class Main {
    private static String ticketUrl;
    private static double maximum;
    private static String make;
    private static String model;
    private static String make_model;
    private static String zipCode;
    private static String milesFrom;

    public Main(String url, String carMake, String carModel, String zip, String miles, double maximumPrice) {
        ticketUrl = url;
        maximum = maximumPrice;
        make = carMake;
        model = carModel;
        make_model = make + " " + model;
        zipCode = zip;
        milesFrom = miles;
    }

    private void loadPage() {
        //Selenium API navigation testing
        WebDriver driver = new ChromeDriver();
        driver.get(ticketUrl);
        driver.manage().window().maximize();
        //checks if current url matches the entered url
        if(driver.getCurrentUrl().startsWith("https")) { //checks to see if page loaded
            System.out.println("Craigslist Page Loaded successfully");
            //open the cars+trucks page
            driver.findElement(By.xpath("//*[@id=\"sss0\"]/li[16]/a")).click();
            if(driver.getCurrentUrl().endsWith("/d/cars-trucks/search/cta")) { //checks to see if page loaded
                System.out.println("Cars+Trucks page loaded successfully.");
                //TODO: cars+trucks page
                //set maximum price and set refined search options
                driver.findElement(By.xpath("//*[@id=\"searchform\"]/div[2]/div/div[4]/input[2]")).sendKeys(Double.toString(maximum)); //price box
                driver.findElement(By.xpath("//*[@id=\"searchform\"]/div[2]/div/div[5]/input")).sendKeys(make_model);//make and model

                driver.findElement(By.xpath("//*[@id=\"searchform\"]/div[2]/div/div[3]/input[2]")).sendKeys(zipCode);//zipcode
                driver.findElement(By.xpath("//*[@id=\"searchform\"]/div[2]/div/div[3]/input[1]")).sendKeys(milesFrom);//miles from

                //submit search
                driver.findElement(By.xpath("//*[@id=\"searchform\"]/div[2]/div/div[2]/ul[1]/li[2]/label")).click(); //checkbox for images only
                driver.findElement(By.xpath("//*[@id=\"searchform\"]/div[2]/div/div[9]/button")).click(); //form submission

            } else {
                System.out.println("Cars+Trucks page failed to load");
                throw new RuntimeException("Page failed to load.");
            }
        } else {
            System.out.println("Craigslist page failed to load");
            throw new RuntimeException("Page failed to load.");
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        //TODO: URL Generator
        Scanner reader = new Scanner(System.in);
        System.out.print("Make: ");
        String make = reader.next();
        System.out.print("Model: ");
        String model = reader.next();
        System.out.print("Zip Code: ");
        String zipCode = reader.next();
        System.out.print("Miles From: ");
        String milesFrom = reader.next();
        System.out.print("Maximum Price: ");
        int maximumPrice = reader.nextInt();

        //Main craigslistBot = new Main("https://westernmass.craigslist.org/","Nissan", "240sx", "01040", "100", 5000);
        //craigslistBot.loadPage();

        Main craigslistBot2 = new Main("https://westernmass.craigslist.org/", make, model, zipCode, milesFrom, maximumPrice);
        craigslistBot2.loadPage();
    }
}
