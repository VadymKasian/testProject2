package com.test.base;

import com.test.util.Constants;
import com.test.util.reporter.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITest;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class BaseTest implements ITest {

    public static WebDriver driver;
    protected String currentAnnotatedMethodName = "";

    @AfterMethod
    public void setNullTestName() {
        currentAnnotatedMethodName = null;
    }

    @Override
    public String getTestName() {
        return currentAnnotatedMethodName;
    }

    @BeforeMethod
    public void setTestName(Method method) {
        currentAnnotatedMethodName = "";
        try {
            String testName = method.getAnnotation(Test.class).testName();
            if (!testName.isEmpty())
                currentAnnotatedMethodName = testName;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void startFirefox() {
        String platform = System.getProperty("os.name");

        String driversFolder = Constants.DEFAULT_LIB_DIR + File.separator;
        String pathToDriver = null;
        if (System.getProperty("path.to.driver") != null && !System.getProperty("path.to.driver").isEmpty()) {
            pathToDriver = System.getProperty("path.to.driver");
        } else {
            pathToDriver = driversFolder + "geckodriver.exe";
        }

        System.setProperty("webdriver.gecko.driver", pathToDriver);
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        driver = new FirefoxDriver(capabilities) {
            @Override
            public WebElement findElement(By by) {
                try {
                    return by.findElement(this);
                } catch (NoSuchElementException nse) {
                    Field f = null;
                    try {
                        f = Throwable.class.getDeclaredField("detailMessage");
                    } catch (NoSuchFieldException e) {
                        throw nse;
                    }
                    f.setAccessible(true);
                    try {
                        String error = "\n" + by.toString() + "\n" + f.get(nse);
                        f.set(nse, error);
                    } catch (IllegalAccessException ia) {
                    }
                    throw nse;
                }
            }
        };
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.get(Constants.MAIN_PAGE_URL);
    }

    protected void setupOperaRemoteDriver(String hubUrl, String platformName) throws IOException {

        String platform = System.getProperty("os.name");

        String pathToDriver = "D:/java/programs/operadriver.exe";

        System.setProperty("webdriver.opera.driver", pathToDriver);
        DesiredCapabilities capabilities = DesiredCapabilities.opera();
        driver = new OperaDriver(capabilities);

        capabilities.setBrowserName("opera");
        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setVersion("81.0.4044.113");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.get(Constants.MAIN_PAGE_URL);
    }

    protected void startPhantomJs() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            String execPath = System.getProperty("lib.dir") + File.separatorChar +
                    "phantomjs.exe";
            System.setProperty("phantomjs.binary.path", execPath);
        } else if (os.contains("mac")) {
            String execPath = System.getProperty("lib.dir") + File.separatorChar +
                    "phantomjsmac";
            System.setProperty("phantomjs.binary.path", execPath);
        } else if (os.contains("lin")) {
            String execPath = System.getProperty("lib.dir") + File.separatorChar +
                    "phantomjs";
            System.setProperty("phantomjs.binary.path", execPath);
        }

        driver = new PhantomJSDriver(DesiredCapabilities.phantomjs());
        driver.manage().window().setSize(new Dimension(1440, 900));
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void startChrome() {
        String platform = System.getProperty("os.name");

        String driversFolder = Constants.DEFAULT_LIB_DIR + File.separator;
        String pathToDriver = null;
        if (System.getProperty("path.to.driver") != null && !System.getProperty("path.to.driver").isEmpty()) {
            pathToDriver = System.getProperty("path.to.driver");
        } else {
            pathToDriver = (platform.contains("Wind")) ? driversFolder + "chromedriver.exe" : "/usr/local/bin/chromedriver";
        }

        System.setProperty("webdriver.chrome.driver", pathToDriver);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities) {
            @Override
            public WebElement findElement(By by) {
                try {
                    return by.findElement(this);
                } catch (NoSuchElementException nse) {
                    Field f = null;
                    try {
                        f = Throwable.class.getDeclaredField("detailMessage");
                    } catch (NoSuchFieldException e) {
                        throw nse;
                    }
                    f.setAccessible(true);
                    try {
                        String error = "\n" + by.toString() + "\n" + f.get(nse);
                        f.set(nse, error);
                    } catch (IllegalAccessException ia) {
                    }
                    throw nse;
                }
            }
        };
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.get(Constants.MAIN_PAGE_URL);
    }


    protected void setupChromeRemoteDriver(String hubUrl, String platformName) throws IOException {

        String platform = System.getProperty("os.name");

        String driversFolder = Constants.DEFAULT_LIB_DIR + File.separator;
        String pathToDriver = null;
        if (System.getProperty("path.to.driver") != null && !System.getProperty("path.to.driver").isEmpty()) {
            pathToDriver = System.getProperty("path.to.driver");
        } else {
            pathToDriver = "D:/java/programs/chromedriver.exe";
        }

        System.setProperty("webdriver.chrome.driver", pathToDriver);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities) {
            @Override
            public WebElement findElement(By by) {
                try {
                    return by.findElement(this);
                } catch (NoSuchElementException nse) {
                    Field f = null;
                    try {
                        f = Throwable.class.getDeclaredField("detailMessage");
                    } catch (NoSuchFieldException e) {
                        throw nse;
                    }
                    f.setAccessible(true);
                    try {
                        String error = "\n" + by.toString() + "\n" + f.get(nse);
                        f.set(nse, error);
                    } catch (IllegalAccessException ia) {
                    }
                    throw nse;
                }
            }
        };

        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setVersion("83.0.4109.39");

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.get(Constants.MAIN_PAGE_URL);

    }

    protected void closeBrowser() {
        if (driver != null)
            driver.quit();
    }


    @BeforeClass(alwaysRun = true)
    @Parameters({ "browser"})
    public void startBrowser(String browser) throws IOException {

        String message = "* Starting test " + this.getClass().toString();
        Reporter.log("\n" + message);
        System.out.println(message);

        String hubUrl = "http://localhost:4444/wd/hub"; //System.getProperty("hub");
        String platform = System.getProperty("os.name"); //or  os.name

        if (browser.equalsIgnoreCase("chrome")) {
            if (hubUrl != null && !hubUrl.isEmpty()) {
                this.setupChromeRemoteDriver(hubUrl, platform);
            } else {
                this.startChrome();
            }

        } else if (browser.equalsIgnoreCase("opera")) {
            if (hubUrl != null && !hubUrl.isEmpty()) {
                this.setupOperaRemoteDriver(hubUrl, platform);
            } else {
                this.startFirefox();
            }
        } else if (browser.equalsIgnoreCase("phantom")){
            startPhantomJs();
        }
        Reporter.log("Current window size is: " + driver.manage().window().getSize());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Reporter.log("Stopping WebDriver");
        closeBrowser();
    }


}
