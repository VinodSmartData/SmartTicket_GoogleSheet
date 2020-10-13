package com.project.SmartTicket_GoogleSheet;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.sun.tools.sjavac.Log;


public class LoginPage_Automation 
{
	WebDriver driver;
	String Variable;
	ArrayList tabs;
	 ExtentReports extent;
	 ExtentTest logger;
	 ExtentHtmlReporter htmlReporter;
	 String htmlReportPath = "C:\\Users\\Vinod\\Workspace\\SmartTicket_GoogleSheet\\test-output\\testReport.html"; 
	
	
	@BeforeTest
	 public void setup(){
        htmlReporter = new ExtentHtmlReporter(htmlReportPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vinod\\Softwares\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	@Test(priority=0)
	public void login() throws GeneralSecurityException, IOException, InterruptedException 
	{
		logger = extent.createTest("SmartTicket login", "Test of login page in smart ticket site");
		List<List<Object>> values =TestGoogleSheet.GetData();
		  if (values == null || values.isEmpty()) 
		  {
				System.out.println("No data found.");
		 } 
		  else 
		  {
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
						String ChoseName = row.get(2).toString();
					
						if (driver.findElements(By.xpath(".//*[@class='text-center ng-star-inserted' and  text()='"+ChoseName+"']")).size() > 0 && driver.findElement(By.xpath(".//*[@class='text-center ng-star-inserted' and  text()='"+ChoseName+"']")).isDisplayed() == true) 
						{
							System.out.println(ChoseName);
							WebElement chooseworkspace = driver.findElement(By.xpath(".//*[@class='text-center ng-star-inserted' and  text()='"+ChoseName+"']"));
							JavascriptExecutor jse1 = (JavascriptExecutor) driver;
							jse1.executeScript("arguments[0].click();", chooseworkspace);

						Thread.sleep(5000);
						}
						WebElement user_profile = driver.findElement(By.xpath(".//*[@class='user-image']"));
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
						WebElement smart_ticket = driver.findElement(By.xpath("//*[@id='dropdownMenuButton']"));
						smart_ticket.click();
						Thread.sleep(3000);
						String WorkDropDown = row2.get(1).toString();
					
						System.out.println(WorkDropDown);
						WebElement smart_data_test1 = driver.findElement(By.xpath("//*[@class='dropdown-item name-header' and text()='"+WorkDropDown+"']"));
						smart_data_test1.click();
						Thread.sleep(5000);
								}
							}
						WebElement setting = driver.findElement(By.xpath("//*[@class='fa fa-gear']"));
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
						WebElement dashboard = driver.findElement(By.xpath(".//*[@class='nav-icon fa fa-home']"));
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
						driver.findElement(By.xpath(".//*[@class='nav-icon fa fa-home']")).click();
						Thread.sleep(16000);
								}
							}
						}
						if(driver.findElements(By.xpath(".//*[@class='fa fa-gear']")).size() > 0
								&&	driver.findElement(By.xpath(".//*[@class='fa fa-gear']")).isDisplayed() == true) {
							WebElement admin_setting = driver.findElement(By.xpath(".//*[@class='fa fa-gear']"));
							JavascriptExecutor jse1 = (JavascriptExecutor) driver;
							jse1.executeScript("arguments[0].click();", admin_setting);
							Thread.sleep(3000);
							List<List<Object>> values5 =TestGoogleSheet.Get_ManageUser();
							  if (values5 == null || values.isEmpty()) {
									System.out.println("No data found.");
								} else {
									for (List row5 : values5) {
									driver.findElement(By.linkText("Manage Users")).click();
										Thread.sleep(5000);
										WebElement add_current_user = driver.findElement(By.xpath(".//*[@class='btn btn-primary margin-bottom-sm ng-star-inserted' and @title='Manage Users']"));
										JavascriptExecutor jse2 = (JavascriptExecutor) driver;
										jse2.executeScript("arguments[0].click();", add_current_user);
										Thread.sleep(5000);
										WebElement email = driver.findElement(By.name("email"));
										email.sendKeys(row5.get(0).toString());
										Thread.sleep(3000);
										WebElement role_name = driver.findElement(By.name("role"));
										Select dropdown1 = new Select(role_name);
										dropdown1.selectByVisibleText(row5.get(1).toString());
										Thread.sleep(5000);
										WebElement add = driver.findElement(By.xpath(".//*[@class='btn btn-primary']"));
										JavascriptExecutor jse3 = (JavascriptExecutor) driver;
										jse3.executeScript("arguments[0].click();", add);
										Thread.sleep(5000);
						
										WebElement filter = driver.findElement(By.xpath(".//*[@class='form-control ng-untouched ng-pristine ng-valid']"));
										//*[@id=\"mat-tab-content-3-0\"]/div/app-current-users/div/div/div[1]/div[1]/input
										//*[@id=\"mat-tab-content-1-0\"]/div/app-current-users/div/div/div[1]/div[1]/input
										filter.sendKeys(row5.get(0).toString());
										Thread.sleep(5000);
										WebElement remove = driver.findElement(By.xpath(".//*[@class='btn btn-danger']"));
										//*[@id="mat-tab-content-3-0"]/div/app-current-users/div/ag-grid-angular/div/div[1]/div[2]/div[3]/div[2]/div/div/div/div[6]
										//*[@id="mat-tab-content-1-0"]/div/app-current-users/div/ag-grid-angular/div/div[1]/div[2]/div[3]/div[2]/div/div/div/div[6]/button
										JavascriptExecutor jse4 = (JavascriptExecutor) driver;
										jse4.executeScript("arguments[0].click();", remove);
										Thread.sleep(5000);
										WebElement yes = driver.findElement(By.xpath(".//*[@class='btn btn-primary']"));
										//*[@id=\"mat-dialog-0\"]/app-confirm-dialog/div/div[3]/button[1]
									    JavascriptExecutor jse5 = (JavascriptExecutor)driver;
									    jse5.executeScript("arguments[0].click();", yes);
									    Thread.sleep(5000);
									    driver.findElement(By.xpath(".//*[@class='mat-ripple mat-tab-label mat-focus-indicator ng-star-inserted']")).click();
									    Thread.sleep(5000);
									    WebElement pendinguser = driver.findElement(By.xpath(".//*[@class='btn btn-primary pull-right' and @title='Manage Users']"));
									    JavascriptExecutor jse6 = (JavascriptExecutor)driver;
									    jse6.executeScript("arguments[0].click();", pendinguser);
									    Thread.sleep(5000);
									    WebElement email_id = driver.findElement(By.name("email"));
									    email_id.sendKeys(row5.get(2).toString());
									    Thread.sleep(5000);
									    WebElement role_id = driver.findElement(By.name("role"));
									    Select dropdown2 = new Select(role_id);
									    dropdown2.selectByVisibleText(row5.get(3).toString());
									    Thread.sleep(5000);
									    WebElement add_pendinguser = driver.findElement(By.xpath(".//*[@class='btn btn-primary']"));
									    JavascriptExecutor jse7 = (JavascriptExecutor)driver;
									    jse7.executeScript("arguments[0].click();", add_pendinguser);
									    Thread.sleep(5000);
									    WebElement filters = driver.findElement(By.xpath(".//*[@class='form-control ng-untouched ng-pristine ng-valid']"));
									    filters.sendKeys(row5.get(2).toString());
									    Thread.sleep(5000);
									    WebElement resend = driver.findElement(By.xpath(".//*[@class='btn btn-default']"));
									  //*[@id="mat-tab-content-0-1"]/div/app-pending-users/div/ag-grid-angular/div/div[1]/div[2]/div[3]/div[2]/div/div/div/div[5]/button
									    JavascriptExecutor jse8 = (JavascriptExecutor)driver;
									    jse8.executeScript("arguments[0].click();", resend);
									    Thread.sleep(5000);
									    WebElement remove_pending = driver.findElement(By.xpath(".//*[@class='btn btn-danger']"));
									    JavascriptExecutor jse9 = (JavascriptExecutor)driver;
									    jse9.executeScript("arguments[0].click();", remove_pending);
									    Thread.sleep(5000);
									    WebElement yes1 = driver.findElement(By.xpath(".//*[@class='btn btn-primary']"));
									    //yes1.click();
									    JavascriptExecutor jse10 = (JavascriptExecutor)driver;
									    jse10.executeScript("arguments[0].click();", yes1);
									    Thread.sleep(5000);
									}
							}
							 WebElement manage_userfield = driver.findElement(By.linkText("Manage User Fields"));
							 manage_userfield.click();
							 Thread.sleep(5000);
							 WebElement add_new_field = driver.findElement(By.id("add_new_field"));
							 JavascriptExecutor js = (JavascriptExecutor)driver;
							 js.executeScript("arguments[0].click();", add_new_field);
							 Thread.sleep(3000);
							 List<List<Object>> values6 =TestGoogleSheet.Get_ManageUserField();
							  if (values6 == null || values.isEmpty()) {
									System.out.println("No data found.");
								} else {
									for (List row6 : values6) {
											WebElement label = driver.findElement(By.name("label"));
											label.sendKeys(row6.get(0).toString());
											Thread.sleep(5000);
											WebElement type = driver.findElement(By.name("controlType"));
											Select dropdown2 = new Select(type);
											dropdown2.selectByVisibleText(row6.get(1).toString());
											Thread.sleep(3000);
											WebElement create = driver.findElement(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/app-add-user-control-modal/div/form/div[2]/button[1]"));
											JavascriptExecutor js0 = (JavascriptExecutor)driver;
											js0.executeScript("arguments[0].click();", create);
											Thread.sleep(5000);
											WebElement mobile_number = driver.findElement(By.xpath("//*[@id=\"accordion_0\"]/div/div/div[1]/h4/a"));
											JavascriptExecutor js1 = (JavascriptExecutor)driver;
											js1.executeScript("arguments[0].click();", mobile_number);
											Thread.sleep(5000);
											WebElement label_name = driver.findElement(By.xpath("//*[@id=\"collapseOne_0\"]/div/div[2]/div[1]/div/div/input"));
											label_name.clear();
											Thread.sleep(5000);
											label_name.sendKeys(row6.get(2).toString());
											Thread.sleep(5000);
											WebElement save = driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right ng-star-inserted']"));
											JavascriptExecutor js2 = (JavascriptExecutor)driver;
											js2.executeScript("arguments[0].click();", save);
											Thread.sleep(6000);
											WebElement remove1 = driver.findElement(By.xpath("//*[@class='panel-collapse in collapse show']/div/div[1]/button"));
											JavascriptExecutor js3 = (JavascriptExecutor)driver;
											js3.executeScript("arguments[0].click();", remove1);
											Thread.sleep(5000);
											WebElement yes_button = driver.findElement(By.xpath(".//*[@class='btn btn-primary']"));
											JavascriptExecutor js4 = (JavascriptExecutor)driver;
											js4.executeScript("arguments[0].click();", yes_button);
											Thread.sleep(5000);
									}
								}
							Thread.sleep(3000);
							driver.findElement(By.linkText("Manage Roles")).click();
								Thread.sleep(3000);
								List<List<Object>> values7 =TestGoogleSheet.Get_ManageRoles();
								  if (values7 == null || values.isEmpty()) {
										System.out.println("No data found.");
									} else {
										for (List row7 : values7) {
											driver.findElement(By.xpath("//*[@id=\"manageRoles\"]/section/div/div/div/div[2]/div/div[2]/button")).click();
											//html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-manage-roles/div/section/div/div/div/div[2]/div/div[2]/button
											//*[@id="manageRoles"]/section/div/div/div/div[2]/div/div[2]/button
											Thread.sleep(3000);
											WebElement role_name = driver.findElement(By.name("name"));
											role_name.sendKeys(row7.get(0).toString());
											Thread.sleep(3000);
											WebElement create=driver.findElement(By.xpath("//*[@id=\"create-new-role\"]/section/div/div/div/div[2]/form/div[4]/button"));
											JavascriptExecutor js1 = (JavascriptExecutor) driver;
											js1.executeScript("arguments[0].click();", create);
											
											Thread.sleep(5000);
											WebElement filter = driver.findElement(By.xpath(".//*[@class='search']/input"));
										filter.sendKeys(row7.get(0).toString());
										Thread.sleep(5000);
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
										Thread.sleep(10000);
										WebElement manage_role= driver.findElement(By.linkText("Manage Roles"));
										JavascriptExecutor js0 = (JavascriptExecutor)driver;
										js0.executeScript("arguments[0].click();", manage_role);
										driver.navigate().refresh();
										Thread.sleep(5000);
										WebElement filter1 = driver.findElement(By.xpath(".//*[@class='form-control ng-untouched ng-pristine ng-valid' and @type='text']"));
										System.out.println("find the element");
										filter1.sendKeys(row7.get(1).toString());
										Thread.sleep(5000);
										WebElement remove_role = driver.findElement(By.xpath(".//*[@class='btn btn-danger']"));
										System.out.println("remove role");
										JavascriptExecutor js5 = (JavascriptExecutor)driver;
										js5.executeScript("arguments[0].click();", remove_role);
										Thread.sleep(5000);
										WebElement yes1 = driver.findElement(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/app-confirm-dialog/div/div[3]/button[1]"));
										//html/body/div/div[2]/div/mat-dialog-container/app-confirm-dialog/div/div[3]/button[1]
										//*[@id="mat-dialog-0"]/app-confirm-dialog/div/div[3]/button[1]
										JavascriptExecutor js6 = (JavascriptExecutor)driver;
										js6.executeScript("arguments[0].click();", yes1);
										Thread.sleep(5000);
										driver.findElement(By.xpath("//*[@class='form-control-feedback feedback-link']")).click();
										Thread.sleep(5000);
										WebElement setdefault = driver.findElement(By.xpath("//*[@id=\"manageRoles\"]/section/div/div/div/div[2]/ag-grid-angular/div/div[1]/div[2]/div[3]/div[2]/div/div/div[1]/div[2]/span"));
										JavascriptExecutor js2 = (JavascriptExecutor)driver;
										js2.executeScript("arguments[0].click();", setdefault);
										Thread.sleep(5000);	
										}
									}
								  driver.findElement(By.xpath("//*[@class='nav-link create-new-form-link']")).click();
								  Thread.sleep(3000);
									List<List<Object>> values8 =TestGoogleSheet.Get_CreateForms();
									  if (values8 == null || values.isEmpty()) {
											System.out.println("No data found.");
										} else {
											for (List row8 : values8) {
												WebElement form_name = driver.findElement(By.name("name"));
												form_name.sendKeys(row8.get(0).toString());
												Thread.sleep(3000);
												WebElement create = driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right m5']"));
												JavascriptExecutor js1 = (JavascriptExecutor)driver;
												js1.executeScript("arguments[0].click();", create);
												Thread.sleep(3000);
												WebElement radio_button = driver.findElement(By.xpath(".//*[@class='ng-untouched ng-pristine ng-valid' and @value='updated_at']"));
												JavascriptExecutor js2 = (JavascriptExecutor)driver;
												js2.executeScript("arguments[0].click();", radio_button);
												Thread.sleep(3000);
												WebElement update = driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right m5 ng-star-inserted']"));
												JavascriptExecutor js3 = (JavascriptExecutor)driver;
												js3.executeScript("arguments[0].click();", update);
												Thread.sleep(3000);
												WebElement form_name1 = driver.findElement(By.name("name"));
												form_name1.clear();
												Thread.sleep(3000);
												form_name1.sendKeys(row8.get(2).toString());
												WebElement checkbox = driver.findElement(By.name("cbOverdue"));
												JavascriptExecutor js4 = (JavascriptExecutor)driver;
												js4.executeScript("arguments[0].click();", checkbox);
												Thread.sleep(4000);
												WebElement days = driver.findElement(By.name("overDueDays"));
												days.sendKeys(row8.get(1).toString());
												Thread.sleep(4000);
												WebElement update1 = driver.findElement(By.xpath("//*[@class='btn btn-primary pull-right m5 ng-star-inserted']"));
												JavascriptExecutor js5 = (JavascriptExecutor)driver;
												js5.executeScript("arguments[0].click();", update1);
												Thread.sleep(4000);
												WebElement delete_form = driver.findElement(By.xpath("//*[@class='btn btn-danger pull-right m5']"));
												JavascriptExecutor js6 = (JavascriptExecutor)driver;
												js6.executeScript("arguments[0].click();", delete_form);
												WebElement yes_button = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-confirm-dialog/div/div[3]/button[1]"));
												JavascriptExecutor js7 = (JavascriptExecutor)driver;
												js7.executeScript("arguments[0].click();", yes_button);
												Thread.sleep(5000);
											}
									}
									  driver.findElement(By.linkText("Test Form")).click();
									  Thread.sleep(4000);
									  List<List<Object>> values9 =TestGoogleSheet.Get_ManageWorkflow();
									  if (values9 == null || values.isEmpty()) {
											System.out.println("No data found.");
										} else {
											for (List row9 : values9) {
									  driver.findElement(By.linkText("Manage Workflow")).click();
									  Thread.sleep(4000);
									  driver.navigate().refresh();
									  Thread.sleep(4000);
									  WebElement add_status = driver.findElement(By.name("newStatus"));
									  add_status.sendKeys(row9.get(0).toString());
									  Thread.sleep(3000);
									  WebElement add_button = driver.findElement(By.xpath(".//*[@class='btn btn-primary' and @type='submit']"));
									  JavascriptExecutor js1 = (JavascriptExecutor)driver;
									  js1.executeScript("arguments[0].click();", add_button);
									  Thread.sleep(4000);
									  WebElement edit = driver.findElement(By.xpath("/html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-admin-workflow/div/section/div/div[1]/div/div[2]/ul/li[3]/span/a[2]"));
									  JavascriptExecutor js2 = (JavascriptExecutor)driver;
									  js2.executeScript("arguments[0].click();", edit);
									  Thread.sleep(4000);
									  WebElement edit_status = driver.findElement(By.name("statusName"));
									  edit_status.clear();
									  Thread.sleep(3000);
									  edit_status.sendKeys(row9.get(1).toString());
									  Thread.sleep(4000);
									  WebElement save = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-edit-status-modal/div/form/div[2]/button[1]"));
									  JavascriptExecutor js3 = (JavascriptExecutor)driver;
									  js3.executeScript("arguments[0].click();", save);
									  Thread.sleep(4000);
									  WebElement delete = driver.findElement(By.xpath("/html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-admin-workflow/div/section/div/div[1]/div/div[2]/ul/li[3]/span/a[1]"));
									  JavascriptExecutor js4 = (JavascriptExecutor)driver;
									  js4.executeScript("arguments[0].click();", delete);
									  Thread.sleep(3000);
									  WebElement option = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-delete-status-modal/div/div[2]/select"));
									  Select dropdown = new Select(option);
									  dropdown.selectByVisibleText(row9.get(2).toString());
									  Thread.sleep(3000);
									  WebElement ok = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-delete-status-modal/div/div[3]/button[1]"));
									  JavascriptExecutor js5 = (JavascriptExecutor)driver;
									  js5.executeScript("arguments[0].click();", ok);
									  Thread.sleep(3000);
											}
										}
									  driver.findElement(By.linkText("Manage Fields")).click();
									  List<List<Object>> values10 =TestGoogleSheet.Get_ManageField();
									  if (values10 == null || values.isEmpty()) {
											System.out.println("No data found.");
										} else {
											for (List row10 : values10) {
												 
												 Thread.sleep(4000);
												 WebElement add_newfield = driver.findElement(By.id("add_new_field"));
												 JavascriptExecutor js1 = (JavascriptExecutor)driver;
												 js1.executeScript("arguments[0].click();", add_newfield);
												 Thread.sleep(4000);
												 WebElement label_name = driver.findElement(By.name("label"));
												 label_name.sendKeys(row10.get(0).toString());
												 Thread.sleep(4000);
												 WebElement type = driver.findElement(By.name("controlType"));
												 Select dropdown3 = new Select(type);
												 dropdown3.selectByVisibleText(row10.get(1).toString());
												 Thread.sleep(5000);
												 WebElement create_button = driver.findElement(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/app-add-form-field-control-modal/div/form/div[2]/button[1]"));
												 JavascriptExecutor js2 = (JavascriptExecutor)driver;
												 js2.executeScript("arguments[0].click();", create_button);
												 Thread.sleep(6000);
												 WebElement field_name = driver.findElement(By.xpath("//*[@id=\"accordion_2\"]/div/div/div[1]/h4/a"));
												 JavascriptExecutor js3 = (JavascriptExecutor)driver;
												 js3.executeScript("arguments[0].click();", field_name);
												 Thread.sleep(5000);
												 WebElement label_name1 = driver.findElement(By.xpath("/html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-form-field-metadata-controls/section/div/div/div/div[2]/form/div[2]/app-metadata-controls/div/div[3]/div/div/div[2]/div/div[2]/div[1]/div/div/input"));
												 													   //html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-form-field-metadata-controls/section/div/div/div/div[2]/form/div[2]/app-metadata-controls/div/div[2]/div/div/div[2]/div/div[2]/div[1]/div/div/input
												 label_name1.clear();
												 Thread.sleep(4000);
												 label_name1.sendKeys(row10.get(2).toString());
												 Thread.sleep(4000);
												 WebElement save_button = driver.findElement(By.xpath("/html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-form-field-metadata-controls/section/div/div/div/div[2]/form/div[4]/button"));
												 JavascriptExecutor js4 = (JavascriptExecutor)driver;
												 js4.executeScript("arguments[0].click();", save_button);
												 Thread.sleep(4000);
												 WebElement remove_button = driver.findElement(By.xpath("/html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-form-field-metadata-controls/section/div/div/div/div[2]/form/div[2]/app-metadata-controls/div/div[3]/div/div/div[2]/div/div[1]/button"));
												 JavascriptExecutor js5 = (JavascriptExecutor)driver;
												 js5.executeScript("arguments[0].click();", remove_button);
												 Thread.sleep(4000);
												 WebElement yes_button = driver.findElement(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/app-confirm-dialog/div/div[3]/button[1]"));
												 JavascriptExecutor js6 = (JavascriptExecutor)driver;
												 js6.executeScript("arguments[0].click();", yes_button);
												 Thread.sleep(4000);
											}
										}
									  driver.findElement(By.linkText("Response Templates")).click();
									  List<List<Object>> values11 =TestGoogleSheet.Get_ResponseTemplate();
									  if (values11 == null || values.isEmpty()) {
											System.out.println("No data found.");
										} else {
											for (List row11 : values11) {
												 Thread.sleep(4000);
												 WebElement add_response_template = driver.findElement(By.xpath(".//button[@id='manager_user']"));
												 JavascriptExecutor js1 = (JavascriptExecutor)driver;
												 js1.executeScript("arguments[0].click();", add_response_template);
												 Thread.sleep(4000);
												 WebElement label_name = driver.findElement(By.name("label"));
												 label_name.sendKeys(row11.get(0).toString());
												 Thread.sleep(5000);
												 driver.findElement(By.xpath(".//*[@class='tox-toolbar__primary']/div[5]")).click();
												 Thread.sleep(2000);
												 WebElement content_field = driver.findElement(By.xpath(".//*[@class='tox-control-wrap']/input"));
												 System.out.println("Content is displayed");
												 content_field.sendKeys(row11.get(1).toString());
												 Thread.sleep(4000);
												 driver.findElement(By.xpath(".//*[@class='tox-form']/div[3]/input")).sendKeys(row11.get(3).toString());
												 Thread.sleep(4000);
												 driver.findElement(By.xpath(".//*[@class='tox-button']")).click();
												 Thread.sleep(2000);
												 WebElement add_button = driver.findElement(By.xpath(".//*[@class='btn btn-primary']"));
												 JavascriptExecutor js2 = (JavascriptExecutor)driver;
												 js2.executeScript("arguments[0].click();", add_button);
												 Thread.sleep(4000);
												 WebElement click_name = driver.findElement(By.xpath(".//*[@class='cdk-drop-list ng-star-inserted']/div[1]/div/div[1]/div[1]/h4/a"));
												 JavascriptExecutor js3 = (JavascriptExecutor)driver;
												 js3.executeScript("arguments[0].click();", click_name);
												 Thread.sleep(4000);
												 WebElement edit_button = driver.findElement(By.xpath(".//*[@class='btn btn-primary pull-right m10']"));
												 JavascriptExecutor js4 = (JavascriptExecutor)driver;
												 js4.executeScript("arguments[0].click();", edit_button);
												 Thread.sleep(4000);
												 WebElement label_name1 = driver.findElement(By.name("editlabel"));
												 label_name1.clear();
												 Thread.sleep(4000);
												 label_name1.sendKeys(row11.get(2).toString());
												 Thread.sleep(4000);
												 WebElement save = driver.findElement(By.xpath(".//*[@class='btn btn-primary']"));
												 JavascriptExecutor js5 = (JavascriptExecutor)driver;
												 js5.executeScript("arguments[0].click();", save);
												 Thread.sleep(4000);
												 WebElement delete = driver.findElement(By.xpath(".//*[@class='btn btn-danger pull-right m10']"));
												 JavascriptExecutor js6 = (JavascriptExecutor)driver;
												 js6.executeScript("arguments[0].click();", delete);
												 Thread.sleep(4000);
												 WebElement yes_button = driver.findElement(By.xpath(".//*[@class='btn btn-primary']"));
												 JavascriptExecutor js7 = (JavascriptExecutor)driver;
												 js7.executeScript("arguments[0].click();", yes_button);
												 Thread.sleep(5000);
											}
										}
									  driver.findElement(By.linkText("Custom Report")).click();
									  Thread.sleep(4000);
									  driver.findElement(By.xpath(".//*[@name='templatetext']")).clear();
										Variable = driver.findElement(By.xpath(".//*[@class='card-footer']/ul/li[1]")).getText();
										driver.findElement(By.xpath(".//*[@name='templatetext']")).sendKeys(Variable);
										driver.findElement(By.xpath(".//*[@name='templatetext']")).sendKeys(Keys.ENTER);
										Thread.sleep(3000);
										Variable=driver.findElement(By.xpath(".//*[@class='card-footer']/ul/li[7]")).getText();
										driver.findElement(By.xpath(".//*[@name='templatetext']")).sendKeys(Variable);
										driver.findElement(By.xpath(".//*[@name='templatetext']")).sendKeys(Keys.ENTER);
										Thread.sleep(3000);
										Variable=driver.findElement(By.xpath(".//*[@class='card-footer']/ul/li[12]")).getText();
										driver.findElement(By.xpath(".//*[@name='templatetext']")).sendKeys(Variable);
										
										JavascriptExecutor js1 = (JavascriptExecutor) driver;
										js1.executeScript("window.scrollBy(0,2000)");
										driver.findElement(By.xpath(".//button[@class='btn btn-primary pull-right']")).click();
										Thread.sleep(5000);
									  
										driver.findElement(By.linkText("Api Connectivity")).click();
										Thread.sleep(4000);
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
										WebElement create = driver.findElement(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/app-documentation/div/div[2]/div/mat-tab-group/div/mat-tab-body[2]/div/div/div/form/input[5]"));
										JavascriptExecutor js5 = (JavascriptExecutor)driver;
										js5.executeScript("arguments[0].click();", create);
										/*Thread.sleep(7000);
										WebElement testform_dashboard = driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div/app-side-bar/aside/div/nav/ul/li[2]"));
										JavascriptExecutor js11 = (JavascriptExecutor)driver;
										js11.executeScript("arguments[0].click();", testform_dashboard);*/
										Thread.sleep(5000);
										driver.findElement(By.xpath(".//*[@class='fa fa-gear']")).click();
										Thread.sleep(4000);
										driver.findElement(By.linkText("Api Connectivity")).click();
										WebElement testform1 = driver.findElement(By.xpath("/html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-admin-api/div/section/form/div/div/div/div[2]/div[2]/div[5]/table/tbody/tr[1]/td[2]/mat-slide-toggle/label/div"));
									    JavascriptExecutor js6 = (JavascriptExecutor)driver;
										js6.executeScript("arguments[0].click();", testform1);
										Thread.sleep(4000);
										WebElement enable_api_access1 = driver.findElement(By.xpath(".//*[@class='mat-slide-toggle-bar mat-slide-toggle-bar-no-side-margin']"));
										JavascriptExecutor js10 = (JavascriptExecutor)driver;
										js10.executeScript("arguments[0].click();", enable_api_access1);
										Thread.sleep(4000);
										WebElement update2 = driver.findElement(By.xpath("/html/body/app-root/app-admin-settings-dashboard/div/div/div/section/div/app-admin-api/div/section/form/div/div/button"));
										JavascriptExecutor js8 = (JavascriptExecutor)driver;
										js8.executeScript("arguments[0].click();", update2);
										Thread.sleep(10000);
										WebElement user = driver.findElement(By.xpath(".//*[@class='nav-icon fa fa-home']"));
										JavascriptExecutor js11= (JavascriptExecutor)driver;
										js11.executeScript("arguments[0].click();", user);
										Thread.sleep(5000);
										}
								}
						}
						
						driver.findElement(By.xpath(".//*[@class='nav nav-pills nav-sidebar flex-column nav-legacy text-sm sidebar-menu']/li[3]")).click();
						Thread.sleep(4000);
						 List<List<Object>> values13 =TestGoogleSheet.Get_Ticket();
						  if (values13 == null || values.isEmpty()) {
								System.out.println("No data found.");
							} else {
								for (List row13 : values13) {
									driver.findElement(By.xpath(".//*[@class='btn btn-default ng-star-inserted']")).click();
									Thread.sleep(5000);
									WebElement title = driver.findElement(By.xpath(".//*[@class='card-body']/div[1]/div/form/div[1]/div[1]/div/app-text/div/input"));
									title.sendKeys(row13.get(0).toString());
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='tox-tbtn']")).click();
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='tox-toolbar__primary']/div[5]")).click();
									Thread.sleep(5000);
									WebElement description = driver.findElement(By.xpath(".//*[@class='tox-control-wrap']/input"));
									description.sendKeys(row13.get(1).toString());
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='tox-form']/div[3]/input")).sendKeys(row13.get(7).toString());
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='tox-button']")).click();
									Thread.sleep(5000);
									if(driver.findElements(By.name("assigneduser")).size() > 0 
											&& driver.findElement(By.name("assigneduser")).isDisplayed() == true) {
										WebElement assigned_to = driver.findElement(By.name("assigneduser"));
										Select dropdown = new Select(assigned_to);
										dropdown.selectByVisibleText(row13.get(2).toString());
									}
									Thread.sleep(5000);
									WebElement add_attachment = driver.findElement(By.id("file-input"));
									add_attachment.sendKeys(row13.get(3).toString());
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//button[@class='btn btn-primary']")).click();
									Thread.sleep(5000);
									// Comment Ticket
									driver.findElement(By.xpath(".//*[@class='fa fa-plus']")).click();
									Thread.sleep(3000);
									driver.findElement(By.xpath(".//*[@class='card-body padding15']/div/div[2]/form/div/div[2]/form-field/div/editor/div/div/div[1]/div[2]/div/div[5]")).click();
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='tox-control-wrap']/input")).sendKeys(row13.get(1).toString());
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='tox-form']/div[3]/input")).sendKeys(row13.get(7).toString());
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='tox-button']")).click();
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='btn btn-card-tool ng-star-inserted' and @title='Add']")).click();
									Thread.sleep(5000);
									//Edit Ticket
									/*if(driver.findElements(By.xpath(".//*[@class='btn btn-card-tool ng-star-inserted' and @title='Edit']")).size() > 0 
											&& driver.findElement(By.xpath(".//*[@class='btn btn-card-tool ng-star-inserted' and @title='Edit']")).isDisplayed() == true) {*/
									driver.findElement(By.xpath(".//*[@class='btn btn-card-tool ng-star-inserted' and @title='Edit']")).click();
									Thread.sleep(5000);
									WebElement Status = driver.findElement(By.xpath(".//*[@class='form-control']"));
									Select dropdown = new Select(Status);
									dropdown.selectByVisibleText(row13.get(4).toString());
									Thread.sleep(5000);
									WebElement subject = driver.findElement(By.xpath(".//*[@class='form-control ng-untouched ng-pristine ng-valid' and @type='text']"));
									subject.clear();
									Thread.sleep(3000);
									subject.sendKeys(row13.get(8).toString());
									Thread.sleep(5000);
									WebElement save = driver.findElement(By.xpath(".//*[@class='btn btn-card-tool' and @type='submit']"));
									JavascriptExecutor js = (JavascriptExecutor)driver;
									js.executeScript("arguments[0].click();", save);
									Thread.sleep(5000);
									if(driver.findElements(By.xpath(".//button[@class='btn btn-card-tool ng-star-inserted' and @title='Archive']")).size() > 0 
										&& driver.findElement(By.xpath(".//button[@class='btn btn-card-tool ng-star-inserted' and @title='Archive']")).isDisplayed()==true){
									driver.findElement(By.xpath(".//button[@class='btn btn-card-tool ng-star-inserted' and @title='Archive']")).click();
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='btn btn-primary' and @type= 'submit']")).click();
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//button[@class='btn btn-card-tool ng-star-inserted' and @title='Unarchive']")).click();
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//button[@class='btn btn-card-tool ng-star-inserted' and @title='Archive']")).click();
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='btn btn-primary' and @type= 'submit']")).click();
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//button[@class='btn btn-card-tool ng-star-inserted' and @title='Delete']")).click();
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='btn btn-primary' and @type= 'submit']")).click();
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//button[@class='btn btn-card-tool ng-star-inserted' and @title='Undelete']")).click();
									Thread.sleep(5000);
									//Export into pdf
									driver.findElement(By.xpath(".//*[@class='fa fa-file-pdf-o']")).click();
									Thread.sleep(5000);
									String originalHandle = driver.getWindowHandle();
								    for(String handle : driver.getWindowHandles()) {
								        if (!handle.equals(originalHandle)) {
								            driver.switchTo().window(handle);
								            driver.close();
								        }
								    }

								    driver.switchTo().window(originalHandle);
								    Thread.sleep(3000);
									}
								    // Ticket History
									driver.findElement(By.xpath(".//*[@class='card-header with-border']/div/div[2]/app-ticket-menu/div/a[3]")).click();
									Thread.sleep(2000);
									JavascriptExecutor js1 = (JavascriptExecutor) driver;
									js1.executeScript("window.scrollBy(0,4000)");
									Thread.sleep(5000);
									js1.executeScript("window.scrollBy(0,-4000)");
									//Ticket Notes
									driver.findElement(By.xpath(".//*[@class='card-header with-border']/div/div[2]/app-ticket-menu/div/a[2]")).click();
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='tox-tbtn' and @title='Insert/edit link']")).click();
									Thread.sleep(2000);
									WebElement admin_notes = driver.findElement(By.xpath(".//*[@class='tox-control-wrap']/input"));
									admin_notes.sendKeys(row13.get(1).toString());
									Thread.sleep(3000);
									driver.findElement(By.xpath(".//*[@class='tox-form']/div[3]/input")).sendKeys(row13.get(7).toString());
									Thread.sleep(4000);
									driver.findElement(By.xpath(".//*[@class='tox-button']")).click();
									Thread.sleep(5000);
									WebElement notify = driver.findElement(By.id("notifyusers"));
									notify.click();
									Thread.sleep(3000);
									WebElement notifycheck = driver.findElement(By.xpath("/html/body/app-root/app-dashboard/div/div/div/section/div/app-notes/section/div/div[2]/div/div/form/div[1]/div/div[1]/div/div/ul/li[4]"));
									notifycheck.click();
									Thread.sleep(5000);
									driver.findElement(By.xpath(".//*[@class='btn btn-primary pull-right m5 ng-star-inserted']")).click();
									Thread.sleep(5000);
								}
							}
						// Report Ticket
						  if(driver.findElements(By.xpath(".//*[@class='card-header with-border']/div/div[2]/app-ticket-menu/div/span/a")).size() > 0 
								  && driver.findElement(By.xpath(".//*[@class='card-header with-border']/div/div[2]/app-ticket-menu/div/span/a")).isDisplayed()) {
						  driver.findElement(By.xpath(".//*[@class='card-header with-border']/div/div[2]/app-ticket-menu/div/span/a")).click();
							Thread.sleep(5000);
							driver.findElement(By.xpath(".//*[@class='btn btn-primary pull-right']")).click();
							Thread.sleep(5000);
							driver.findElement(By.xpath(".//*[@class='btn btn-primary']")).click();
							Thread.sleep(4000);
							driver.findElement(By.xpath(".//*[@class='btn btn-default pull-right mr10']")).click();
							Thread.sleep(3000);
							String originalHandle = driver.getWindowHandle();
						    for(String handle : driver.getWindowHandles()) {
						        if (!handle.equals(originalHandle)) {
						            driver.switchTo().window(handle);
						            driver.close();
						        }
						    }

						    driver.switchTo().window(originalHandle);
						    driver.findElement(By.xpath(".//*[@class='btn btn-default pull-right mr10 ng-star-inserted']")).click();
							Thread.sleep(5000);
						  }
						  WebElement user = driver.findElement(By.xpath(".//*[@class='nav-item dropdown user user-menu']"));
						  JavascriptExecutor js = (JavascriptExecutor)driver;
							js.executeScript("arguments[0].click();", user);
						  Thread.sleep(5000);
						  driver.findElement(By.linkText("Sign Out")).click();
						  Thread.sleep(2000);
						  
						  //Forgot Password
						  List<List<Object>> values14 =TestGoogleSheet.Get_Forgotpassword();
						  if (values14 == null || values.isEmpty()) {
								System.out.println("No data found.");
							} else {
								for (List row14 : values14) {
						  driver.get("https://accounts.google.com/login/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin");
							try
							{	Thread.sleep(4000);
								if(driver.findElement(By.xpath(".//input[@id='identifierId']")).isDisplayed())
								{
									System.out.println("Application Lanch Sucessfull");
								}
							}
							catch (Exception e) 
							{
								System.out.println("Unable to Lanch Application");
							}
							Thread.sleep(3000);
							driver.findElement(By.xpath(".//input[@id='identifierId']")).sendKeys(row14.get(0).toString());
							Thread.sleep(2000);
							driver.findElement(By.xpath(".//input[@id='identifierId']")).sendKeys(Keys.ENTER);
							Thread.sleep(2000);		
							driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")).sendKeys(row14.get(1).toString());		
							Thread.sleep(2000);		
							driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")).sendKeys(Keys.ENTER);		
							Thread.sleep(2000);	
							driver.navigate().to("https://mail.google.com/mail/u/0/#inbox");
							Thread.sleep(5000);
							driver.findElement(By.name("q")).sendKeys(row14.get(2).toString());
							Thread.sleep(2000);
							driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
							Thread.sleep(2000);
							try 
							{
								driver.findElement(By.xpath(".//*[@class='aeH']/div[2]/div[2]/div[1]/div/div/div[1]/div/div[1]/span")).click();
								Thread.sleep(2000);
								driver.findElement(By.xpath(".//*[@class='D E G-atb PY']/div[2]/div[1]/div/div/div[2]/div[3]/div")).click();
								Thread.sleep(2000);
								System.out.println("Old Data Removed from Email");
								Thread.sleep(4000);
							} 
							catch (Exception e)
							{
								System.out.println("No Old data to delete");
								Thread.sleep(4000);
							}
							driver.get("http://smartticket-qa.smartdata.live/landing-page");
							driver.navigate().to("http://smartticket-qa.smartdata.live/landing-page");
							try
							{
								if(driver.findElement(By.xpath(".//*[@class='btn dropdown-toggle ng-tns-c43-0']")).isDisplayed())
								{
									System.out.println("Application Lanch Sucessfull");
								}
							}
							catch (Exception e) 
							{
								System.out.println("Unable to Lanch Application");
							}
							driver.findElement(By.xpath(".//*[@class='btn dropdown-toggle ng-tns-c43-0']")).click();
							Thread.sleep(2000);
							driver.findElement(By.xpath(".//*[@class='p-forgotPassword ng-tns-c43-0']")).click();
							Thread.sleep(2000);
							driver.findElement(By.xpath(".//input[@id='email']")).sendKeys(row14.get(3).toString());
							Thread.sleep(2000);
							driver.findElement(By.xpath(".//button[@class='btn btn-primary pull-right ng-tns-c43-0']")).click();
							Thread.sleep(5000);
							try 
							{
								driver.findElement(By.xpath(".//*[@class='notification-content ng-tns-c43-0 ng-star-inserted']")).isDisplayed();
								System.out.println("Error resetting password - User does not exist");
							} 
							catch (Exception e1) 
							{
								System.out.println("Email Sent Sucessfully");
								Thread.sleep(5000);	
								driver.navigate().to("https://mail.google.com/mail/u/0/#inbox");
								Thread.sleep(2000);
								driver.findElement(By.name("q")).sendKeys(row14.get(2).toString());
								Thread.sleep(2000);
								driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
								Thread.sleep(2000);
								try
								{
									driver.findElement(By.xpath(".//*[@class='BltHke nH oy8Mbf']/div[4]/div[2]")).click();
									Thread.sleep(2000);
									driver.findElement(By.xpath(".//*[@class='a3s aXjCH ']/p[1]/a")).click();
									Thread.sleep(5000);	
									System.out.println("Mail Opend Sucessfully");
								} 
								catch (Exception e2) 
								{
									System.out.println("Mail Not Received");
								}
								try 
								{
							       tabs = new ArrayList (driver.getWindowHandles());
							      //  System.out.println(tabs.size());
							        driver.switchTo().window((String) tabs.get(1));
							        System.out.println("Switched Sucessfully");
							        
							        driver.findElement(By.xpath(".//input[@id='password']")).sendKeys(row14.get(4).toString());
							        Thread.sleep(2000);
							        driver.findElement(By.xpath(".//input[@id='confirmPassword']")).sendKeys(row14.get(4).toString());
							        Thread.sleep(2000);
							        driver.findElement(By.xpath(".//button[@class='btn btn-primary pull-right']")).click();
							        Thread.sleep(5000);
							        System.out.println("Password Changed Sucessfully");
							        driver.close();
								    driver.switchTo().window((String) tabs.get(0));
								    System.out.println("Switched to Gmail Again");
								    Thread.sleep(2000);
								    driver.findElement(By.xpath(".//*[@class='gb_D gb_Ra gb_i']")).click();
								    Thread.sleep(2000);
								    driver.findElement(By.xpath(".//*[@class='gb_5a gb_F gb_l gb_6a gb_oa']/div[4]/a")).click();
								    Thread.sleep(5000);
								    System.out.println("Email Logout Sucessfully");
								}
								catch (Exception e3)
								{
									System.out.println("Unable to Switch");
								}
							}
						}
					}
							
						
			}
		}
	}
	@AfterMethod
	 public void getResult(ITestResult result) throws Exception {
	     if (result.getStatus() == ITestResult.FAILURE)
	     {
	         logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
	         //logger.fail(result.getThrowable());
	     }
	     else if (result.getStatus() == ITestResult.SUCCESS)
	     {
	         logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
	     }
	     else if (result.getStatus() == ITestResult.SKIP)
	     {
	         logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.BLUE));
	     }
	     else
	     {
	     	logger.log(Status.ERROR, MarkupHelper.createLabel(result.getName() + "Test Case Error", ExtentColor.PINK));
	     }
	  }

	 @AfterTest
	 public void testend() throws Exception {
	     extent.flush();
	 }

	 @AfterClass
	 public void tearDown() throws Exception {
	     driver.close();
	 }
}