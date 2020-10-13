/*package com.smartticket.GoogleSheet;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Managerole {
	WebDriver driver;
	 ExtentReports extent;
	 ExtentTest logger;
	 ExtentHtmlReporter htmlReporter;
	 String htmlReportPath = "D:\\Selenium_Automation\\Smart_ticket\\SmartTicket_GoogleSheet\\test-output\\testReport.html"; 
	
	
	@BeforeTest
	 public void setup(){
       htmlReporter = new ExtentHtmlReporter(htmlReportPath);
       extent = new ExtentReports();
       extent.attachReporter(htmlReporter);
       System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Automation\\Smart_ticket\\smartticketupgrade\\chromedriver.exe");
       driver = new ChromeDriver();
       driver.manage().window().maximize();
	}
	
	@Test(priority=0)
	public void login() throws GeneralSecurityException, IOException, InterruptedException {
		logger = extent.createTest("SmartTicket login", "Test of login page in smart ticket site");
		List<List<Object>> values =TestGoogleSheet.GetData();
		  if (values == null || values.isEmpty()) {
				System.out.println("No data found.");
			} else {
				for (List row : values) {
					
					driver.get("http://smartticket-qa.smartdata.live/landing-page");
							Thread.sleep(3000);
							 driver.findElement(By.xpath("//*[@id=\"top\"]/nav/div/div/ul/li[3]/a")).click();
						WebElement emailid = driver.findElement(By.name("email"));
						emailid.sendKeys(row.get(0).toString());
						Thread.sleep(1000);
						driver.findElement(By.name("password")).sendKeys(row.get(1).toString());;
						Thread.sleep(1000);
						driver.findElement(By.xpath("//*[@id=\"top\"]/nav/div/div/ul/li[3]/div/form/div/div[2]/button")).click();
						Thread.sleep(5000);
						if (driver.findElements(By.partialLinkText("Test1")).size() > 0
								&& driver.findElement(By.partialLinkText("Test1")).isDisplayed() == true) {
							WebElement chooseworkspace = driver.findElement(By.partialLinkText("Test1"));
							JavascriptExecutor jse1 = (JavascriptExecutor) driver;
							jse1.executeScript("arguments[0].click();", chooseworkspace);
						Thread.sleep(5000);
						}
						WebElement user_profile = driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div/app-header/div/nav/ul[2]/li[2]/a"));
						JavascriptExecutor jse = (JavascriptExecutor) driver;
						jse.executeScript("arguments[0].click();", user_profile);
						Thread.sleep(3000);
						if(driver.findElements(By.linkText("SU Dashboard")).size() > 0
								&& driver.findElement(By.linkText("SU Dashboard")).isDisplayed() == true) {
						WebElement su_dashboard =  driver.findElement(By.linkText("SU Dashboard"));
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].click();", su_dashboard);
						Thread.sleep(7000);
						List<List<Object>> values1 =TestGoogleSheet.Get_Data();
						  if (values1 == null || values.isEmpty()) {
								System.out.println("No data found.");
							} else {
								for (List row1 : values1) {
						
						WebElement add_tenant = driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-0\"]/div/app-tenants/div/div/div[2]/button"));
						JavascriptExecutor js1 = (JavascriptExecutor) driver;
						js1.executeScript("arguments[0].click();", add_tenant);
						Thread.sleep(3000);
						WebElement tenant_name = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-add-tenant/div/form/div[1]/div[1]/form-field/input"));
						tenant_name.sendKeys(row1.get(0).toString());
						Thread.sleep(3000);
						WebElement tier = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-add-tenant/div/form/div[1]/div[2]/form-field/select"));
						Select dropdown = new Select(tier);
						dropdown.selectByVisibleText(row1.get(1).toString());
						WebElement add = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-add-tenant/div/form/div[2]/button[1]"));
						JavascriptExecutor js2 = (JavascriptExecutor) driver;
						js2.executeScript("arguments[0].click();", add);
						Thread.sleep(5000);
								}
							}
						List<List<Object>> values2 =TestGoogleSheet.Get_Su();
						  if (values2 == null || values.isEmpty()) {
								System.out.println("No data found.");
							} else {
								for (List row2 : values2) {
						WebElement manage_superuser = driver.findElement(By.xpath("//*[@id=\"mat-tab-label-0-1\"]"));
						manage_superuser.click();
						Thread.sleep(5000);
						WebElement add_superuser = driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-1\"]/div/app-super-users/div/div/div[2]/button"));
						add_superuser.click();
						Thread.sleep(8000);
						WebElement email = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-add-super-user/div/form/div[1]/div/div/form-field/input"));
						email.sendKeys(row2.get(0).toString());
						Thread.sleep(3000);
						WebElement add_superuser1 = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-add-super-user/div/form/div[2]/button[1]"));
						add_superuser1.click();
						Thread.sleep(4000);
						WebElement filter_item = driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-1\"]/div/app-super-users/div/div/div[1]/div/input"));
						filter_item.sendKeys(row2.get(0).toString());
						Thread.sleep(5000);
						WebElement revoke = driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-1\"]/div/app-super-users/div/ag-grid-angular/div/div[1]/div[2]/div[3]/div[2]/div/div/div/div[5]/button"));
						JavascriptExecutor js3 = (JavascriptExecutor) driver;
						js3.executeScript("arguments[0].click();", revoke);
						Thread.sleep(4000);
						WebElement yes = driver.findElement(By.xpath("//*[@id=\"mat-dialog-1\"]/app-confirm-dialog/div/div[3]/button[1]"));
						yes.click();
						Thread.sleep(5000);
						WebElement smart_ticket = driver.findElement(By.xpath("//*[@id=\"dropdownMenuButton\"]"));
						smart_ticket.click();
						Thread.sleep(3000);
						WebElement smart_data_test1 = driver.findElement(By.partialLinkText("Test1"));
						smart_data_test1.click();
						Thread.sleep(5000);
								}
							}
						WebElement setting = driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div/app-header/div/nav/ul[2]/li[3]/a"));
						JavascriptExecutor js4 = (JavascriptExecutor) driver;
						js4.executeScript("arguments[0].click();", setting);
						Thread.sleep(5000);
						WebElement import_ticket = driver.findElement(By.linkText("Import Tickets"));
						JavascriptExecutor js5 = (JavascriptExecutor) driver;
						js5.executeScript("arguments[0].click();", import_ticket);
						Thread.sleep(3000);
						List<List<Object>> values3 =TestGoogleSheet.Get_ImportTicket();
						  if (values3 == null || values.isEmpty()) {
								System.out.println("No data found.");
							} else {
								for (List row3 : values3) {
						
						WebElement select_form = driver.findElement(By.name("formId"));
						Select dropdown1 = new Select(select_form);
						dropdown1.selectByVisibleText(row3.get(0).toString());
						Thread.sleep(3000);
						WebElement choosefile = driver.findElement(By.id("file-input"));
						choosefile.sendKeys(row3.get(1).toString());
						Thread.sleep(3000);
						WebElement default_user = driver.findElement(By.name("userId"));
						default_user.sendKeys(row3.get(2).toString());
						Thread.sleep(3000);
						WebElement start_import = driver.findElement(By.xpath("//*[@id=\"import-tickets\"]/section/div/div/div/div[2]/form/button"));
						JavascriptExecutor js6 = (JavascriptExecutor) driver;
						js6.executeScript("arguments[0].click();", start_import);
						WebElement dashboard = driver.findElement(By.linkText("Dashboard"));
						dashboard.click();
						Thread.sleep(5000);
						WebElement john = driver.findElement(By.linkText("My Johns"));
						JavascriptExecutor js10 = (JavascriptExecutor) driver;
						js10.executeScript("arguments[0].click();", john);
						Thread.sleep(5000);
								}
							}
						List<List<Object>> values4 =TestGoogleSheet.Get_ImportUser();
						  if (values4 == null || values.isEmpty()) {
								System.out.println("No data found.");
							} else {
								for (List row4 : values4) {
						WebElement setting1 = driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div/app-header/div/nav/ul[2]/li[3]/a"));
						JavascriptExecutor js7 = (JavascriptExecutor) driver;
						js7.executeScript("arguments[0].click();", setting1);
						Thread.sleep(3000);
						WebElement import_user = driver.findElement(By.linkText("Import Users"));
						JavascriptExecutor js8 = (JavascriptExecutor) driver;
						js8.executeScript("arguments[0].click();", import_user);
						Thread.sleep(3000);
						WebElement choose_file = driver.findElement(By.name("fileupload"));
						choose_file.sendKeys(row4.get(0).toString());
						Thread.sleep(3000);
						WebElement strt_import = driver.findElement(By.xpath("//*[@id=\"import-users\"]/section/div/div/div/div[2]/form/div[2]/button"));
						JavascriptExecutor js9 = (JavascriptExecutor) driver;
						js9.executeScript("arguments[0].click();", strt_import);
						Thread.sleep(3000);
						WebElement manage_user = driver.findElement(By.linkText("Manage Users"));
						manage_user.click();
						Thread.sleep(5000);
						WebElement filteritem = driver.findElement(By.xpath("//*[@id=\"mat-tab-content-3-0\"]/div/app-current-users/div/div/div[1]/div[1]/input"));
						//*[@id=\"mat-tab-content-1-0\"]/div/app-current-users/div/div/div[1]/div[1]/input
						filteritem.sendKeys(row4.get(1).toString());
						Thread.sleep(5000);
						WebElement remove_user1 = driver.findElement(By.xpath("//*[@id=\"mat-tab-content-3-0\"]/div/app-current-users/div/ag-grid-angular/div/div[1]/div[2]/div[3]/div[2]/div/div/div/div[6]"));
						//*[@id="mat-tab-content-1-0"]/div/app-current-users/div/ag-grid-angular/div/div[1]/div[2]/div[3]/div[2]/div/div/div/div[6]
						remove_user1.click();
						Thread.sleep(5000);
						WebElement yes_button = driver.findElement(By.xpath("//*[@id=\"mat-dialog-2\"]/app-confirm-dialog/div/div[3]/button[1]"));
						//*[@id="mat-dialog-0"]/app-confirm-dialog/div/div[3]/button[1]
						JavascriptExecutor js11 = (JavascriptExecutor) driver;
						js11.executeScript("arguments[0].click();", yes_button);
						driver.findElement(By.linkText("Dashboard")).click();
						Thread.sleep(16000);
								}
							}
						}
						if(driver.findElements(By.xpath("/html/body/app-root/app-dashboard/div/div/app-header/div/nav/ul[2]/li[3]/a")).size() > 0
							&&	driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div/app-header/div/nav/ul[2]/li[3]/a")).isDisplayed() == true) {
							WebElement admin_setting = driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div/app-header/div/nav/ul[2]/li[3]/a"));
							JavascriptExecutor jse1 = (JavascriptExecutor) driver;
							jse1.executeScript("arguments[0].click();", admin_setting);
							Thread.sleep(3000);
							driver.findElement(By.linkText("Api Connectivity")).click();
							Thread.sleep(24000);
							 List<List<Object>> values12 =TestGoogleSheet.Get_APIConnectivity();
							  if (values12 == null || values.isEmpty()) {
									System.out.println("No data found.");
								} else {
									for (List row12 : values12) {
										WebElement enable_api_access = driver.findElement(By.xpath(".//*[@class='mat-slide-toggle-bar mat-slide-toggle-bar-no-side-margin']"));
										JavascriptExecutor js2 = (JavascriptExecutor)driver;
										js2.executeScript("arguments[0].click();", enable_api_access);
									    WebElement default_returnurl = driver.findElement(By.name("successReturnURL"));
									    default_returnurl.clear();
									    Thread.sleep(4000);
									    default_returnurl.sendKeys(row12.get(0).toString());
									    Thread.sleep(4000);
									    WebElement default_returnurl_error = driver.findElement(By.name("errorReturnURL"));
									    default_returnurl_error.clear();
									    Thread.sleep(4000);
									    default_returnurl_error.sendKeys(row12.get(1).toString());
									    Thread.sleep(4000);
									    WebElement enable_apiaccess = driver.findElement(By.name("specificDomainEnableTextArea"));
									    enable_apiaccess.clear();
									    Thread.sleep(4000);
									    enable_apiaccess.sendKeys(row12.get(2).toString());
									    WebElement owner = driver.findElement(By.name("role"));
									    Select dropdown = new Select(owner);
									    dropdown.selectByVisibleText(row12.get(3).toString());
									    Thread.sleep(4000);
									    WebElement testform = driver.findElement(By.xpath("/html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-admin-api/div/section/form/div/div/div/div[2]/div[2]/div[5]/table/tbody/tr[1]/td[2]/mat-slide-toggle/label/div"));
									    JavascriptExecutor js3 = (JavascriptExecutor)driver;
										js3.executeScript("arguments[0].click();", testform);
										Thread.sleep(4000);
										WebElement update = driver.findElement(By.xpath("/html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-admin-api/div/section/form/div/div/button"));
										JavascriptExecutor js4 = (JavascriptExecutor)driver;
										js4.executeScript("arguments[0].click();", update);
										Thread.sleep(10000);
										driver.findElement(By.linkText("Documentation")).click();
										Thread.sleep(5000);
										WebElement demo = driver.findElement(By.xpath(".//*[@class='mat-ripple mat-tab-label mat-focus-indicator ng-star-inserted']"));
										JavascriptExecutor js7 = (JavascriptExecutor)driver;
										js7.executeScript("arguments[0].click();", demo);
										Thread.sleep(5000);
										WebElement title_name = driver.findElement(By.name("subject"));
										title_name.sendKeys(row12.get(4).toString());
										Thread.sleep(4000);
										WebElement description_detail = driver.findElement(By.name("description"));
										description_detail.sendKeys(row12.get(5).toString());
										Thread.sleep(6000);
										WebElement create = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-documentation/div/div[2]/div/mat-tab-group/div/mat-tab-body[2]/div/div/div/form/input[5]"));
										JavascriptExecutor js5 = (JavascriptExecutor)driver;
										js5.executeScript("arguments[0].click();", create);
										Thread.sleep(7000);
										WebElement testform_dashboard = driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div/app-side-bar/aside/div/nav/ul/li[2]"));
										JavascriptExecutor js11 = (JavascriptExecutor)driver;
										js11.executeScript("arguments[0].click();", testform_dashboard);
										Thread.sleep(9000);
										driver.findElement(By.xpath(".//*[@class='fa fa-gear']")).click();
										Thread.sleep(4000);
										driver.findElement(By.linkText("Api Connectivity")).click();
										WebElement testform1 = driver.findElement(By.xpath("/html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-admin-api/div/section/form/div/div/div/div[2]/div[2]/div[5]/table/tbody/tr[1]/td[2]/mat-slide-toggle/label/div"));
									    JavascriptExecutor js6 = (JavascriptExecutor)driver;
										js6.executeScript("arguments[0].click();", testform1);
										Thread.sleep(4000);
										WebElement update1 = driver.findElement(By.xpath("/html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-admin-api/div/section/form/div/div/button"));
										JavascriptExecutor js9 = (JavascriptExecutor)driver;
										js9.executeScript("arguments[0].click();", update1);
										Thread.sleep(10000);
										WebElement enable_api_access1 = driver.findElement(By.xpath(".//*[@class='mat-slide-toggle-bar mat-slide-toggle-bar-no-side-margin']"));
										JavascriptExecutor js10 = (JavascriptExecutor)driver;
										js10.executeScript("arguments[0].click();", enable_api_access1);
										Thread.sleep(4000);
										WebElement update2 = driver.findElement(By.xpath("/html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-admin-api/div/section/form/div/div/button"));
										JavascriptExecutor js8 = (JavascriptExecutor)driver;
										js8.executeScript("arguments[0].click();", update2);
										Thread.sleep(5000);
										}
								}
							driver.findElement(By.linkText("Manage Roles")).click();
							List<List<Object>> values7 =TestGoogleSheet.Get_ManageRoles();
							  if (values7 == null || values.isEmpty()) {
									System.out.println("No data found.");
								} else {
									for (List row7 : values7) {
										Thread.sleep(3000);
										driver.findElement(By.xpath("//*[@id=\"manageRoles\"]/section/div/div/div/div[2]/div/div[2]/button")).click();
										Thread.sleep(3000);
										WebElement role_name = driver.findElement(By.name("name"));
										role_name.sendKeys(row7.get(0).toString());
										Thread.sleep(3000);
										WebElement create=driver.findElement(By.xpath("//*[@id=\"create-new-role\"]/section/div/div/div/div[2]/form/div[4]/button"));
										JavascriptExecutor js1 = (JavascriptExecutor) driver;
										js1.executeScript("arguments[0].click();", create);
										Thread.sleep(5000);
										WebElement setdefault = driver.findElement(By.xpath("//*[@id=\"manageRoles\"]/section/div/div/div/div[2]/ag-grid-angular/div/div[1]/div[2]/div[3]/div[2]/div/div/div[4]/div[2]/span"));
										JavascriptExecutor js2 = (JavascriptExecutor)driver;
										js2.executeScript("arguments[0].click();", setdefault);
										Thread.sleep(5000);
										driver.navigate().refresh();
										Thread.sleep(5000);
										WebElement filter = driver.findElement(By.xpath("//*[@id=\"manageRoles\"]/section/div/div/div/div[2]/div/div[1]/div/input"));
										filter.sendKeys(row7.get(0).toString());
										Thread.sleep(15000);
										driver.findElement(By.xpath(".//*[@class='ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-value']/a")).isDisplayed();
										WebElement name = driver.findElement(By.xpath(".//*[@class='ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-value']/a"));
										JavascriptExecutor js3 = (JavascriptExecutor)driver;
										js3.executeScript("arguments[0].click();", name);
										Thread.sleep(5000);
										WebElement role_name1 = driver.findElement(By.name("name"));
										role_name1.clear();
										Thread.sleep(5000);
										role_name1.sendKeys(row7.get(1).toString());
										Thread.sleep(5000);
										WebElement save = driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right m10']"));
										JavascriptExecutor js4 = (JavascriptExecutor)driver;
										js4.executeScript("arguments[0].click();", save);
										Thread.sleep(5000);
										driver.findElement(By.linkText("Manage Roles")).click();
										Thread.sleep(4000);
										WebElement filter1 = driver.findElement(By.xpath("//*[@id=\"manageRoles\"]/section/div/div/div/div[2]/div/div[1]/div/input"));
										filter1.sendKeys(row7.get(1).toString());
										Thread.sleep(5000);
										WebElement remove_role = driver.findElement(By.xpath("//*[@id=\"manageRoles\"]/section/div/div/div/div[2]/ag-grid-angular/div/div[1]/div[2]/div[3]/div[2]/div/div/div/div[4]/button"));
										System.out.println("remove role");
										JavascriptExecutor js5 = (JavascriptExecutor)driver;
										js5.executeScript("arguments[0].click();", remove_role);
										Thread.sleep(5000);
										WebElement yes1 = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-confirm-dialog/div/div[3]/button[1]"));
										JavascriptExecutor js6 = (JavascriptExecutor)driver;
										js6.executeScript("arguments[0].click();", yes1);
										Thread.sleep(5000);
									}
								}
							  
						}
				}
			}
	}
}*/

