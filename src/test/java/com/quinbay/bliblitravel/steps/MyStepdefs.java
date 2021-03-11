package com.quinbay.bliblitravel.steps;

import com.quinbay.bliblitravel.actions.Selenium;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

public class MyStepdefs {
    Selenium s;
    String date,startdate,enddate;
    LinkedHashMap <String,String> mainmap;
    Select salutation;
    @Given("The user is on Blibli website")
    public void theUserIsOnBlibliWebsite() throws InterruptedException {
        s=new Selenium();
    }

    @When("The user clicks on train tab")
    public void theUserClicksOnTrainTab() throws InterruptedException {
        ((ChromeDriver) s.driver).findElementByXPath("//a[@href='1']").click();
        Thread.sleep(5000);
    }


    @And("User fills details of the train for two way travel")
    public void userFillsDetailsOfTheTrainForTwoWayTravel() throws InterruptedException {
        WebElement twoway=((ChromeDriver) s.driver).findElementByXPath("//*[@id=\"shuttle-rail-2\"]");
        ((JavascriptExecutor) s.driver).executeScript("arguments[0].checked = true;", twoway);
        WebElement oneway=((ChromeDriver) s.driver).findElementByXPath("//*[@id=\"shuttle-rail-1\"]");
        ((JavascriptExecutor) s.driver).executeScript("arguments[0].checked = false;", oneway);
        ((ChromeDriver)s.driver).findElementByXPath("//label[@for='shuttle-rail-2']").click();
        Thread.sleep(5000);
        ((ChromeDriver) s.driver).findElementByXPath("//*[@id=\"travel-blibli-app\"]/div/main/div[5]/main/div[2]/div/div/div/div/div/div[2]/div/div/ul/li[3]/div/div/div/div/div[2]/table/tbody/tr[5]/td[6]/button").click();
        ((ChromeDriver) s.driver).findElementByXPath("//*[@id=\"travel-blibli-app\"]/div/main/div[5]/main/div[2]/div/div/div/div/div/div[2]/div/div/ul/li[1]/div/label/i").click();
        Thread.sleep(5000);
        ((ChromeDriver) s.driver).findElementByXPath("//button[@data-pika-month='3'][@data-pika-day='29']").click();
        mainmap=new LinkedHashMap();
        mainmap.put("source",((ChromeDriver) s.driver).findElementByXPath("//input[@placeholder='Pilih kota asal']").getAttribute("value"));
        mainmap.put("destination",((ChromeDriver) s.driver).findElementByXPath("//input[@placeholder='Pilih kota tujuan']").getAttribute("value"));
        startdate=((ChromeDriver) s.driver).findElementByXPath("//*[@id=\"travel-blibli-app\"]/div/main/div[5]/main/div[2]/div/div/div/div/div/div[2]/div/div/ul/li[1]/div/label/button").getText();
        enddate=((ChromeDriver) s.driver).findElementByXPath("//*[@id=\"travel-blibli-app\"]/div/main/div[5]/main/div[2]/div/div/div/div/div/div[2]/div/div/ul/li[3]/div/label/button").getText();
        mainmap.put("startdate",startdate);
        mainmap.put("enddate",enddate);
    }

    @And("User fills details of the train")
    public void userFillsDetailsOfTheTrain() throws InterruptedException {
        ((ChromeDriver) s.driver).findElementByXPath("//i[@class='icon bli-calendar-ic font-18']").click();
        ((ChromeDriver) s.driver).findElementByXPath("//button[@data-pika-month='3'][@data-pika-day='30']").click();
        mainmap=new LinkedHashMap<String,String>();;
        mainmap.put("source",((ChromeDriver) s.driver).findElementByXPath("//input[@placeholder='Pilih kota asal']").getAttribute("value"));
        mainmap.put("destination",((ChromeDriver) s.driver).findElementByXPath("//input[@placeholder='Pilih kota tujuan']").getAttribute("value"));
        date=((ChromeDriver) s.driver).findElementByXPath("//button[@aria-label='Use the arrow keys to pick a date']").getText();
        mainmap.put("date",date);
    }

    @And("User clicks book tickets")
    public void userClicksBookTickets() {
        ((ChromeDriver) s.driver).findElementByXPath("//button[@class='button button--orange button--big button--full form__button']").click();
    }


    @And("User verifies the details of the travel in the train selection page")
    public void userVerifiesTheDetailsOfTheTravelInTheTrainSelectionPage() throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertEquals(mainmap.get("source"),((ChromeDriver)s.driver).findElementByXPath("//div[@class='route__departure-city']").getText());
        System.out.println("The source is asserted in train selection page!");
        Assert.assertEquals(mainmap.get("destination"),((ChromeDriver)s.driver).findElementByXPath("//div[@class='route__arrival-city']").getText());
        System.out.println("The destination is asserted in train selection page!");
        date=((ChromeDriver)s.driver).findElementByXPath("//span[@class='rail-search__date search-page__input-search-value ng-binding']").getText();
        Assert.assertEquals(mainmap.get("date"),date.substring(0,3)+date.substring(date.length()-13));
        System.out.println("Date is asserted in train selection page!");
        mainmap.put("trainname",((ChromeDriver)s.driver).findElementByXPath("//span[@class='catalog-train__name ng-binding']").getText());
        mainmap.put("totalfare","Rp"+((ChromeDriver)s.driver).findElementByXPath("//span[@class='price__number--zoom ng-binding']").getText()+".000");
    }

    @And("User selects the required train")
    public void userSelectsTheRequiredTrain() {
        ((ChromeDriver)s.driver).findElementByXPath("//button[@class='catalog-train__btn ng-binding']").click();
    }
    

    @And("User fills the customer details")
    public void userFillsTheCustomerDetails() throws InterruptedException {
        salutation=new Select(((ChromeDriver) s.driver).findElementByXPath("//select[@ng-model='contactData.title']"));
        salutation.selectByVisibleText("Tuan");
        ((ChromeDriver)s.driver).findElementByXPath("//input[@ng-model='contactData.fullName']").click();
        ((ChromeDriver)s.driver).findElementByXPath("//input[@ng-model='contactData.fullName']").sendKeys("Saad Ahamed");
        ((ChromeDriver)s.driver).findElementByXPath("//input[@ng-model='contactData.phone']").click();
        ((ChromeDriver)s.driver).findElementByXPath("//input[@ng-model='contactData.phone']").sendKeys("1234567890");
        ((ChromeDriver)s.driver).findElementByXPath("//input[@ng-model='contactData.email']").click();
        ((ChromeDriver)s.driver).findElementByXPath("//input[@ng-model='contactData.email']").sendKeys("samyukthanagulan@gmail.com");
        Thread.sleep(1000);
        mainmap.put("name","Tuan Saad Ahamed");
        mainmap.put("number","1234567890");
        mainmap.put("mail","samyukthanagulan@gmail.com");
        ((ChromeDriver)s.driver).findElementByXPath("//a[@class='checkout__btn checkout__btn--no-margin']").click();
    }

    @And("User fills in passenger details")
    public void userFillsInPassengerDetails() throws InterruptedException{
        ((ChromeDriver)s.driver).findElementByXPath("//label[@class='checkout__checkbox-label']").click();
        ((ChromeDriver)s.driver).findElementByXPath("//input[@class='checkout__input-value ng-pristine ng-untouched ng-valid ng-scope ng-empty']").sendKeys("4344343434434");
        ((ChromeDriver)s.driver).findElementByXPath("//a[@class='checkout__btn-plain']/ancestor::div[@class='box__col']").click();
        mainmap.put("passname","Tuan Saad Ahamed");
        mainmap.put("passport","4344343434434");
    }


    @And("User proceeds to payments")
    public void userProceedsToPayments() throws InterruptedException {
        s.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ((ChromeDriver)s.driver).findElementByXPath("//a[@class='checkout__action-btn-right']").click();
    }
    @Then("User verifies the details of the train booking")
    public void userVerifiesTheDetailsOfTheTrainBooking() throws InterruptedException {
        Thread.sleep(5000);
        s.driver.quit();
    }

    @And("User checks details in payments page")
    public void userChecksDetailsInPaymentsPage() throws InterruptedException {
        Thread.sleep(10000);
        ((ChromeDriver)s.driver).findElementByXPath("/html/body/main/div/article[2]/section[3]").click();
        Thread.sleep(10000);
        Assert.assertEquals(mainmap.get("trainname"),((ChromeDriver)s.driver).findElementByXPath("/html/body/main/div/article[2]/section[3]/div/div/div[1]/div[2]/div[1]/span").getText());
        Assert.assertEquals(mainmap.get("source"),((ChromeDriver)s.driver).findElementByXPath("/html/body/main/div/article[2]/section[3]/div/div/div[1]/div[3]/div[1]/div[2]/div[1]").getText());
        Assert.assertEquals(mainmap.get("destination"),((ChromeDriver)s.driver).findElementByXPath("/html/body/main/div/article[2]/section[3]/div/div/div[1]/div[4]/div[1]/div[2]/div[1]").getText());
        Assert.assertEquals(mainmap.get("date"),((ChromeDriver)s.driver).findElementByXPath("/html/body/main/div/article[2]/section[3]/div/div/div[1]/div[1]/div[3]/span").getText());
    }

    @And("User selects the trains")
    public void userSelectsTheTrains() throws InterruptedException {
        ((ChromeDriver)s.driver).findElementByXPath("//button[@class='catalog-train__btn ng-binding']").click();
        Thread.sleep(5000);
        ((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"site-content\"]/div/div[3]/div[5]/table/tbody/tr[1]/td[6]/div/button").click();
        Assert.assertEquals(mainmap.get("source"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"search-navigation\"]/div[1]/div[1]/div[1]/div[1]").getText());
        System.out.println("The source is asserted in category page !");
        Assert.assertEquals(mainmap.get("destination"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"search-navigation\"]/div[1]/div[1]/div[3]/div[1]").getText());
        System.out.println("The destination is asserted in category page !");
        Assert.assertEquals(mainmap.get("startdate"),"Kam, "+((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"itinerary\"]/div[1]/table/tbody/tr[2]/td[2]/span[2]").getText());
        System.out.println("The start date is asserted in category page !");
        Assert.assertEquals(mainmap.get("enddate"),"Jum, "+((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"itinerary\"]/div[1]/table/tbody/tr[4]/td[2]/span[2]").getText());
        System.out.println("The end date is asserted in category page !");
        mainmap.put("totalfare","Rp"+((ChromeDriver)s.driver).findElementByXPath("//span[@class='price__number--big ng-binding']").getText()+".000");
        mainmap.put("starttrainname",((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"itinerary\"]/div[1]/table/tbody/tr[2]/td[1]/span[1]").getText());
        mainmap.put("endtrainname",((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"itinerary\"]/div[1]/table/tbody/tr[4]/td[1]/span[1]").getText());
        ((ChromeDriver)s.driver).findElementByXPath("//button[@class='itinerary__btn-buy']").click();
    }

    @And("User verifies the customer detail page")
    public void userVerifiesTheCustomerDetailPage() throws InterruptedException{
        Thread.sleep(3000);

        Assert.assertEquals(mainmap.get("trainname"),((ChromeDriver)s.driver).findElementByXPath("//span[@ng-bind='cartData.tripInformation.departureTrainName']").getText());
        System.out.println("The train name is asserted in customer page !");
        Assert.assertEquals(mainmap.get("source"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='cartData.tripInformation.departureOriginStationName']").getText());
        System.out.println("The source is asserted in customer page !");
        Assert.assertEquals(mainmap.get("destination"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='cartData.tripInformation.departureDestinationStationName']").getText());
        System.out.println("The destination is asserted in customer page !");
        Assert.assertEquals(mainmap.get("date"),((ChromeDriver)s.driver).findElementByXPath("//span[@class='order-detail__train-date value ng-binding']").getText());
        System.out.println("The date is asserted in customer page !");
        Assert.assertEquals(mainmap.get("totalfare"),((ChromeDriver)s.driver).findElementByXPath("//div[@class='box__col order-detail__price-value--total ng-binding']").getText());
        System.out.println("The total fare is asserted in customer page !");
    }

    @And("User verifies the passenger detail page")
    public void userVerifiesThePassengerDetailPage() {
        Assert.assertEquals(mainmap.get("name"),"Tuan "+((ChromeDriver)s.driver).findElementByXPath("//label[@ng-bind='contactData.fullName']").getText());
        System.out.println("The name is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("number"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='contactData.phone']").getText());
        System.out.println("The mobile number is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("mail"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='contactData.email']").getText());
        System.out.println("The email is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("trainname"),((ChromeDriver)s.driver).findElementByXPath("//span[@ng-bind='cartData.tripInformation.departureTrainName']").getText());
        System.out.println("The train name is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("source"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='cartData.tripInformation.departureOriginStationName']").getText());
        System.out.println("The source is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("destination"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='cartData.tripInformation.departureDestinationStationName']").getText());
        System.out.println("The destination is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("date"),((ChromeDriver)s.driver).findElementByXPath("//span[@class='order-detail__train-date value ng-binding']").getText());
        System.out.println("The date is asserted in the passenger page !");
        Assert.assertEquals(mainmap.get("totalfare"),((ChromeDriver)s.driver).findElementByXPath("//div[@class='box__col order-detail__price-value--total ng-binding']").getText());
        System.out.println("The total fare is asserted in passenger page !");
    }

    @And("User verifies the passenger details")
    public void userVerifiesThePassengerDetails() {
        Assert.assertEquals(mainmap.get("name"),"Tuan "+((ChromeDriver)s.driver).findElementByXPath("//label[@ng-bind='contactData.fullName']").getText());
        System.out.println("The name is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("number"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='contactData.phone']").getText());
        System.out.println("The phone number is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("mail"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='contactData.email']").getText());
        System.out.println("The mail is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("trainname"),((ChromeDriver)s.driver).findElementByXPath("//span[@ng-bind='cartData.tripInformation.departureTrainName']").getText());
        System.out.println("The train name is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("source"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='cartData.tripInformation.departureOriginStationName']").getText());
        System.out.println("The source is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("destination"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='cartData.tripInformation.departureDestinationStationName']").getText());
        System.out.println("The destination is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("date"),((ChromeDriver)s.driver).findElementByXPath("//span[@class='order-detail__train-date value ng-binding']").getText());
        System.out.println("The date is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("totalfare"),((ChromeDriver)s.driver).findElementByXPath("//div[@class='box__col order-detail__price-value--total ng-binding']").getText());
        System.out.println("The total fare is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("passname"),"Tuan "+((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[1]/section[2]/div[2]/div[1]/div[2]/h3/label[2]").getText());
        System.out.println("The passenger name is asserted in passenger name");
        Assert.assertEquals(mainmap.get("passport"),((ChromeDriver)s.driver).findElementByXPath("//label[@class='checkout__value ng-binding']").getText());
        System.out.println("The passport number is asserted in passenger page !");
    }

    @And("User verifes the customer details page of the trains")
    public void userVerifesTheCustomerDetailsPageOfTheTrains() throws InterruptedException{
        Thread.sleep(3000);
        Assert.assertEquals(mainmap.get("starttrainname"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[1]/div[2]/div[1]/span").getText());
        System.out.println("The start train is asserted in customer page");
        Assert.assertEquals(mainmap.get("endtrainname"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[2]/div[2]/div[1]/span").getText());
        System.out.println("The return train is asserted in customer page");
        Assert.assertEquals(mainmap.get("source"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[1]/div[3]/div[1]/div[2]/div[1]").getText());
        System.out.println("The source is asserted in customer page !");
        Assert.assertEquals(mainmap.get("destination"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[1]/div[4]/div[1]/div[2]/div[1]").getText());
        System.out.println("The destination is asserted in customer page !");
        Assert.assertEquals(mainmap.get("startdate"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[1]/div[1]/div[3]/span").getText());
        System.out.println("The start date is asserted in customer page !");
        Assert.assertEquals(mainmap.get("enddate"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[2]/div[1]/div[3]/span").getText());
        System.out.println("The return date is asserted in customer page !");
        Assert.assertEquals(mainmap.get("totalfare"),((ChromeDriver)s.driver).findElementByXPath("//div[@class='box__col order-detail__price-value--total ng-binding']").getText());
        System.out.println("The total fare is asserted in customer page !");
    }

    @And("User checks details in payments page of the trains")
    public void userChecksDetailsInPaymentsPageOfTheTrains() throws InterruptedException{
        Thread.sleep(10000);
        ((ChromeDriver)s.driver).findElementByXPath("/html/body/main/div/article[2]/section[3]").click();
        Thread.sleep(10000);
        Assert.assertEquals(mainmap.get("starttrainname"),((ChromeDriver)s.driver).findElementByXPath("/html/body/main/div/article[2]/section[3]/div/div/div[1]/div[2]/div[1]/span").getText());
        System.out.println("The start train name is asserted in payment page!");
        Assert.assertEquals(mainmap.get("endtrainname"),((ChromeDriver)s.driver).findElementByXPath("/html/body/main/div/article[2]/section[3]/div/div/div[2]/div[2]/div[1]/span").getText());
        System.out.println("The end train name is asserted in payment page !");
        Assert.assertEquals(mainmap.get("source"),((ChromeDriver)s.driver).findElementByXPath("/html/body/main/div/article[2]/section[3]/div/div/div[1]/div[3]/div[1]/div[2]/div[1]").getText());
        System.out.println("The source is asserted in payment page !");
        Assert.assertEquals(mainmap.get("destination"),((ChromeDriver)s.driver).findElementByXPath("/html/body/main/div/article[2]/section[3]/div/div/div[1]/div[4]/div[1]/div[2]/div[1]").getText());
        System.out.println("The destination is asserted in payment page !");
        Assert.assertEquals(mainmap.get("startdate"),((ChromeDriver)s.driver).findElementByXPath("/html/body/main/div/article[2]/section[3]/div/div/div[1]/div[1]/div[3]/span").getText());
        System.out.println("The start date is asserted in  payment page !");
        Assert.assertEquals(mainmap.get("enddate"),((ChromeDriver)s.driver).findElementByXPath("/html/body/main/div/article[2]/section[3]/div/div/div[2]/div[1]/div[3]/span").getText());
        System.out.println("The return date is asserted in payment page !");
    }

    @And("User verifies the details of the customer")
    public void userVerifiesTheDetailsOfTheCustomer() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertEquals(mainmap.get("starttrainname"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[1]/div[2]/div[1]/span").getText());
        System.out.println("The start train is asserted in customer page !");
        Assert.assertEquals(mainmap.get("source"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[1]/div[3]/div[1]/div[2]/div[1]").getText());
        System.out.println("The source is asserted in customer page !");
        Assert.assertEquals(mainmap.get("destination"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[1]/div[4]/div[1]/div[2]/div[1]").getText());
        System.out.println("The destination is asserted in customer page !");
        Assert.assertEquals(mainmap.get("endtrainname"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[2]/div[2]/div[1]/span").getText());
        System.out.println("The return train name is asserted in customer page !");
        Assert.assertEquals(mainmap.get("startdate"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[1]/div[1]/div[3]/span").getText());
        System.out.println("The start date is asserted in customer page !");
        Assert.assertEquals(mainmap.get("enddate"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[2]/div[1]/div[3]/span").getText());
        System.out.println("The return date is asserted in customer page");
        Assert.assertEquals(mainmap.get("totalfare"),((ChromeDriver)s.driver).findElementByXPath("//div[@class='box__col order-detail__price-value--total ng-binding']").getText());
        System.out.println("The total fare is asserted in customer page");
        Assert.assertEquals(mainmap.get("name"),"Tuan "+((ChromeDriver)s.driver).findElementByXPath("//label[@ng-bind='contactData.fullName']").getText());
        System.out.println("The name is asserted in customer page !");
        Assert.assertEquals(mainmap.get("number"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='contactData.phone']").getText());
        System.out.println("The mobile number is asserted in customer page !");
        Assert.assertEquals(mainmap.get("mail"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='contactData.email']").getText());
        System.out.println("The email is asserted in customer page !");
    }

    @And("User verifies the passenger details of the trains")
    public void userVerifiesThePassengerDetailsOfTheTrains() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertEquals(mainmap.get("name"),"Tuan "+((ChromeDriver)s.driver).findElementByXPath("//label[@ng-bind='contactData.fullName']").getText());
        System.out.println("The name is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("number"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='contactData.phone']").getText());
        System.out.println("The mobile number is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("mail"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='contactData.email']").getText());
        System.out.println("The email is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("endtrainname"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[2]/div[2]/div[1]/span").getText());
        System.out.println("The return train is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("starttrainname"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[1]/div[2]/div[1]/span").getText());
        System.out.println("The start train is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("source"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='cartData.tripInformation.departureOriginStationName']").getText());
        System.out.println("The source is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("destination"),((ChromeDriver)s.driver).findElementByXPath("//div[@ng-bind='cartData.tripInformation.departureDestinationStationName']").getText());
        System.out.println("The destination is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("startdate"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[1]/div[1]/div[3]/span").getText());
        System.out.println("The start date is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("enddate"),((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[2]/section/div/div[2]/div[1]/div[3]/span").getText());
        System.out.println("The enddate is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("totalfare"),((ChromeDriver)s.driver).findElementByXPath("//div[@class='box__col order-detail__price-value--total ng-binding']").getText());
        System.out.println("The total fare is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("passname"),"Tuan "+((ChromeDriver)s.driver).findElementByXPath("//*[@id=\"rail-checkout-id\"]/main/div/article[1]/section[2]/div[2]/div[1]/div[2]/h3/label[2]").getText());
        System.out.println("The passenger name is asserted in passenger page !");
        Assert.assertEquals(mainmap.get("passport"),((ChromeDriver)s.driver).findElementByXPath("//label[@class='checkout__value ng-binding']").getText());
        System.out.println("The passport number is asserted in passenger page !");
    }
}
