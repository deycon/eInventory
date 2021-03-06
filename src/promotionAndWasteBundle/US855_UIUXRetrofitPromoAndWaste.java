package promotionAndWasteBundle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import sprint2.AbstractTest;
import common.Base;
import common.GlobalVariable;
import common.LoginTestData;
import common.Reporter;
import eInventoryPageClasses.CompletedWastePage;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PromotionsAndWastePage;
import eInventoryPageClasses.PurchasesPage;
import eInventoryPageClasses.RawItemPromoPage;
import eInventoryPageClasses.RawItemWastePage;

public class US855_UIUXRetrofitPromoAndWaste extends AbstractTest {

	//TC2718: Verify Promo & Waste page is accessible from the Main Menu.
	@Test()
	public void promotionWaste_US855_TC2718() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level2_SSO_UserId;
		String password = LoginTestData.level2_SSO_Password;
		String storeId = LoginTestData.level2StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//verify that Promotion and Waste page is accessible from the Main Menu
		if (Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWaste_Title)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC2718",
					"User should be able to access Promotion and Waste Page from the Main Menu",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC2718","promotionWaste_US855_TC2718",
					"User should be able to access Promotion and Waste Page from the Main Menu",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC2718");
		}
	}
	
	//TC3219: Verify that Main Menu can be accessible through Promotions & Waste Page.
	@Test()
	public void promotionWaste_US855_TC3219() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		PurchasesPage purchasesPage = PageFactory.initElements(driver, PurchasesPage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		homePage.Menu_DD_BT.click();
		boolean mainMenuDisplayed = Base.isElementDisplayed(homePage.Purchases_BT);
		Thread.sleep(1000);
		homePage.PromotionAndWaste_BT.click();
		Thread.sleep(2000);
		boolean mainMenuHidden = !Base.isElementDisplayed(homePage.Purchases_BT);
		homePage.Menu_DD_BT.click();
		mainMenuDisplayed = mainMenuDisplayed & Base.isElementDisplayed(homePage.Purchases_BT);
		Thread.sleep(2000);
		homePage.Purchases_BT.click();
		//verify that Manual Vendors  Page is accessible from the Main Menu
		if (mainMenuDisplayed & mainMenuHidden & Base.isElementDisplayed(purchasesPage.Purchases_Label)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3219",
					"User should be able to access Main Menu and other pages from Promotion and Waste Page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3219","promotionWaste_US855_TC3219",
					"User should be able to access Main Menu and other pages from Promotion and Waste Page.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3219");
		}
	}
	
	//TC3222: Verify the GUI of the Promotions & Waste Page.
	@Test()
	public void promotionWaste_US855_TC3222() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		//Verify User is able to view  Enter Raw Waste [button], Enter Raw Promo [button] and Enter Completed Waste [button]
		if(Base.isElementDisplayed(promotionsAndWastePage.RawWaste_BT) 
				& Base.isElementDisplayed(promotionsAndWastePage.RawPromo_BT)
				& Base.isElementDisplayed(promotionsAndWastePage.CompletedWaste_BT)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3222",
					"User should be able to Enter Raw Waste [button], Enter Raw Promo [button] and Enter Completed Waste [button] on Promotion and Waste Page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3222_Condition1","promotionWaste_US855_TC3222",
					"User should be able to Enter Raw Waste [button], Enter Raw Promo [button] and Enter Completed Waste [button] on Promotion and Waste Page.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3222_Condition1");
		}
		/*Verify User is able to view Start Date Field with default date as 1st date of Current Month End Date Field 
		 * with default date as Current date is selected and Show Results [button]- against Start and End Date field.*/
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		String startDate = dateFormat.format(cal1.getTime());
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, 0);
		String endDate = dateFormat.format(cal2.getTime());
		System.out.println("endDate "+endDate);
		System.out.println("Start Date "+promotionsAndWastePage.promoWasteHistoryStartDate_TB.getAttribute("value"));
		System.out.println("End Date "+promotionsAndWastePage.promoWasteHistoryEndDate_TB.getAttribute("value"));
		if (promotionsAndWastePage.promoWasteHistoryStartDate_TB.getAttribute("value").equals(startDate)
				& promotionsAndWastePage.promoWasteHistoryEndDate_TB.getAttribute("value").equals(endDate)
				& Base.isElementDisplayed(promotionsAndWastePage.ShowResults_BT)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3222",
					"User is able to view Start Date Field with default date as 1st date of Current Month End Date Field "
					+ " with default date as Current date is selected and Show Results [button]- against Start and End Date field.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3222_Condition2","promotionWaste_US855_TC3222",
					"User is able to view Start Date Field with default date as 1st date of Current Month End Date Field "
					+ " with default date as Current date is selected and Show Results [button]- against Start and End Date field.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3222_Condition2");
		}
		/*Verify User is able to view Promotion and Waste Table with headers :1. Time of Waste, 2. Date Entered , 3.Time Entered ,
		 * 4. Entered By, 5. Type, 6. Amount*/
		if(Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWasteTable_TimeofWaste_Header) 
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWasteTable_DateEntered_Header)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWasteTable_TimeEntered_Header)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWasteTable_EnteredBy_Header)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWasteTable_Type_Header)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWasteTable_Amount_Header)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3222",
					"User is able to view Promotion and Waste Table with headers :1. Time of Waste, 2. Date Entered , 3.Time Entered ,"
					+ " 4. Entered By, 5. Type, 6. Amount",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3222_Condition3","promotionWaste_US855_TC3222",
					"User is able to view Promotion and Waste Table with headers :1. Time of Waste, 2. Date Entered , 3.Time Entered ,"
					+ " 4. Entered By, 5. Type, 6. Amount",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3222_Condition3");
		}
		
		if(promotionsAndWastePage.verifyWasteDateGroupDisplayedForSelectedDateRange(startDate, endDate)
				& promotionsAndWastePage.verifyByDefaultDateGroupIsCollapsed(startDate, endDate)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3222",
					"By default in table grid, different container should be displayed for each day (as per the date selected in Start and End Date) and it should be collapsed.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3222_Condition4","promotionWaste_US855_TC3222",
					"By default in table grid, different container should be displayed for each day (as per the date selected in Start and End Date) and it should be collapsed.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3222_Condition4");
		}
		
		if(Base.isElementDisplayed(promotionsAndWastePage.AtAGlance_Expand_Icon)
				& Base.isElementDisplayed(promotionsAndWastePage.AtAGlance_Header)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3222",
					"(^) icon should be displayed at the bottom of the screen with the header At a Glance",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3222_Condition5","promotionWaste_US855_TC3222",
					"(^) icon should be displayed at the bottom of the screen with the header At a Glance",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3222_Condition5");
			
		}
	}
	
	//TC3223: Verify the GUI of the Promotions & Waste Page.
	@Test()
	public void promotionWaste_US855_TC3223() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level2_SSO_UserId;
		String password = LoginTestData.level2_SSO_Password;
		String storeId = LoginTestData.level2StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		//Verify User is able to view  Enter Raw Waste [button], Enter Raw Promo [button] and Enter Completed Waste [button]
		if(Base.isElementDisplayed(promotionsAndWastePage.RawWaste_BT) 
				& !Base.isElementDisplayed(promotionsAndWastePage.RawPromo_BT)
				& Base.isElementDisplayed(promotionsAndWastePage.CompletedWaste_BT)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3223",
					"User should be able to Enter Raw Waste [button] and Enter Completed Waste [button] on Promotion and Waste Page.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3223_Condition1","promotionWaste_US855_TC3223",
					"User should be able to Enter Raw Waste [button] and Enter Completed Waste [button] on Promotion and Waste Page.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3223_Condition1");
		}
		/*Verify User is able to view Start Date Field with default date as 1st date of Current Month End Date Field 
		 * with default date as Current date is selected and Show Results [button]- against Start and End Date field.*/
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		cal1.set(Calendar.DAY_OF_MONTH, 1);
		String startDate = dateFormat.format(cal1.getTime());
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, 0);
		String endDate = dateFormat.format(cal2.getTime());
		System.out.println("endDate "+endDate);
		System.out.println("Start Date "+promotionsAndWastePage.promoWasteHistoryStartDate_TB.getAttribute("value"));
		System.out.println("End Date "+promotionsAndWastePage.promoWasteHistoryEndDate_TB.getAttribute("value"));
		if (promotionsAndWastePage.promoWasteHistoryStartDate_TB.getAttribute("value").equals(startDate)
				& promotionsAndWastePage.promoWasteHistoryEndDate_TB.getAttribute("value").equals(endDate)
				& Base.isElementDisplayed(promotionsAndWastePage.ShowResults_BT)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3223",
					"User is able to view Start Date Field with default date as 1st date of Current Month End Date Field "
					+ " with default date as Current date is selected and Show Results [button]- against Start and End Date field.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3223_Condition2","promotionWaste_US855_TC3223",
					"User is able to view Start Date Field with default date as 1st date of Current Month End Date Field "
					+ " with default date as Current date is selected and Show Results [button]- against Start and End Date field.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3223_Condition2");
		}
		/*Verify User is able to view Promotion and Waste Table with headers :1. Time of Waste, 2. Date Entered , 3.Time Entered ,
		 * 4. Entered By, 5. Type, 6. Amount*/
		if(Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWasteTable_TimeofWaste_Header) 
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWasteTable_DateEntered_Header)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWasteTable_TimeEntered_Header)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWasteTable_EnteredBy_Header)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWasteTable_Type_Header)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWasteTable_Amount_Header)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3223",
					"User is able to view Promotion and Waste Table with headers :1. Time of Waste, 2. Date Entered , 3.Time Entered ,"
					+ " 4. Entered By, 5. Type, 6. Amount",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3223_Condition3","promotionWaste_US855_TC3223",
					"User is able to view Promotion and Waste Table with headers :1. Time of Waste, 2. Date Entered , 3.Time Entered ,"
					+ " 4. Entered By, 5. Type, 6. Amount",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3223_Condition3");
		}
		
		if(promotionsAndWastePage.verifyWasteDateGroupDisplayedForSelectedDateRange(startDate, endDate)
				& promotionsAndWastePage.verifyByDefaultDateGroupIsCollapsed(startDate, endDate)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3223",
					"By default in table grid, different container should be displayed for each day (as per the date selected in Start and End Date) and it should be collapsed.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3223_Condition4","promotionWaste_US855_TC3223",
					"By default in table grid, different container should be displayed for each day (as per the date selected in Start and End Date) and it should be collapsed.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3223_Condition4");
		}
		
		if(Base.isElementDisplayed(promotionsAndWastePage.AtAGlance_Expand_Icon)
				& Base.isElementDisplayed(promotionsAndWastePage.AtAGlance_Header)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3223",
					"(^) icon should be displayed at the bottom of the screen with the header At a Glance",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3223_Condition5","promotionWaste_US855_TC3223",
					"(^) icon should be displayed at the bottom of the screen with the header At a Glance",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3223_Condition5");
			
		}
	}
	
	//TC3224: Verify Show Result button functionality ( start and end date) on Promotions & Waste page.
	@Test()
	public void promotionWaste_US855_TC3224() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level2_SSO_UserId;
		String password = LoginTestData.level2_SSO_Password;
		String storeId = LoginTestData.level2StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//Verify user is not able to select more than current date for end date
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, 1);
		String tomorrowDate = dateFormat.format(cal2.getTime());
		System.out.println("tomorrowDate "+tomorrowDate);
		promotionsAndWastePage.promoWasteHistoryEndDate_TB.click();
		Thread.sleep(1000);
		if (promotionsAndWastePage.verifyEndDateIsDisabled(tomorrowDate)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3224",
					"System should not allow user to select date more than the current date.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3224_Condition2","promotionWaste_US855_TC3224",
					"System should not allow user to select date more than the current date.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3224_Condition2");
		}
		//get yesterday date and select end date as yesterday date
		promotionsAndWastePage.promoWasteHistoryEndDate_TB.click();
		Thread.sleep(1000);
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.DATE, -1);
		String yesterdayDate = dateFormat.format(cal3.getTime());
		System.out.println("yesterdayDate "+yesterdayDate);
		promotionsAndWastePage.selectEndDate(yesterdayDate);
		//Click on start date button
		promotionsAndWastePage.promoWasteHistoryStartDate_TB.click();
		Thread.sleep(1000);
		Calendar cal4 = Calendar.getInstance();
		cal4.add(Calendar.DATE, 0);
		String todayDate = dateFormat.format(cal4.getTime());
		//Verify user is not able to select today date for start date when end date is already selected as yesterday date
		if(promotionsAndWastePage.verifyStartDateIsDisabled(todayDate) &
				promotionsAndWastePage.promoWasteHistoryEndDate_TB.getAttribute("value").equals(yesterdayDate)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3224",
					"System should not allow user to select date more than the date selected in End Date.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3224_Condition2","promotionWaste_US855_TC3224",
					"System should not allow user to select date more than the date selected in End Date.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3224_Condition2");
		}
		promotionsAndWastePage.promoWasteHistoryStartDate_TB.click();
		Thread.sleep(1000);
		//Select end date as today date and start date as today date
		promotionsAndWastePage.selectEndDate(todayDate);
		promotionsAndWastePage.selectStartDate(todayDate);
		promotionsAndWastePage.ShowResults_BT.click();
		Thread.sleep(1000);
		//Verify user is able to select same date for start date and end date and user is able to view records for selected date
		if (promotionsAndWastePage.promoWasteHistoryStartDate_TB.getAttribute("value").equals(todayDate)
				& promotionsAndWastePage.promoWasteHistoryEndDate_TB.getAttribute("value").equals(todayDate)
				& promotionsAndWastePage.verifyWasteDateGroupDisplayedForSelectedDateRange(todayDate, todayDate)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3224",
					"User should be able to select same date for start date and end date field  which should not be more than current date ",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3224_Condition3","promotionWaste_US855_TC3224",
					"User should be able to select same date for start date and end date field  which should not be more than current date",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3224_Condition3");
		}
		//Click on end date button
		promotionsAndWastePage.promoWasteHistoryEndDate_TB.click();
		Thread.sleep(1000);
		//verify user is not able to select end date as yesterday date when start date is already set to today date
		if (promotionsAndWastePage.verifyEndDateIsDisabled(yesterdayDate)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3224",
					"System should not allow user to select date less than the date selected in Start Date field.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3224_Condition4","promotionWaste_US855_TC3224",
					"System should not allow user to select date less than the date selected in Start Date field.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3224_Condition4");
		}
	}
	
	//TC3225: Verify container displayed in result grid on Promotions & Waste page when Log-in through other than user which has access to Level 2-6.
	@Test()
	public void promotionWaste_US855_TC3225() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String createdate = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		//Select end date as today date and start date as today date
		promotionsAndWastePage.selectStartDate(startDate);
		promotionsAndWastePage.selectEndDate(endDate);
		promotionsAndWastePage.ShowResults_BT.click();
		Thread.sleep(5000);
		promotionsAndWastePage.clickOnDateGroup(createdate);
		if (promotionsAndWastePage.verifySelectedDateIsExpanded(createdate)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3225",
					"User is able to expand the records for a selecte date",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3225_Condition1","promotionWaste_US855_TC3225",
					"User is able to expand the records for a selecte date",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3225_Condition1");
		}
		
		if(promotionsAndWastePage.calculateWasteAmountForSelectedDate(createdate).equals(promotionsAndWastePage.getWasteAmountFromDateGroupHeader(createdate))
				& promotionsAndWastePage.calculatePromoAmountForSelectedDate(createdate).equals(promotionsAndWastePage.getPromoAmountFromDateGroupHeader(createdate))
				& promotionsAndWastePage.calculateCompleteWasteAmountForSelectedDate(createdate).equals(promotionsAndWastePage.getCompleteWasteAmountFromDateGroupHeader(createdate))){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3225",
					"Calculation for waste/completeWaste/Promo in container should match the total amount displayed on Footer for Raw Waste, Completed Waste and Raw Promo respectively.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3225_Condition2","promotionWaste_US855_TC3225",
					"Calculation for waste/completeWaste/Promo in container should match the total amount displayed on Footer for Raw Waste, Completed Waste and Raw Promo respectively.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3225_Condition2");
			
		}
		promotionsAndWastePage.clickOnDateGroup(createdate);
		if (promotionsAndWastePage.verifySelectedDateIsCollapsed(createdate)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3225",
					"User is able to Collapse the records for a selecte date",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3225_Condition3","promotionWaste_US855_TC3225",
					"User is able to Collapse the records for a selecte date",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3225_Condition3");
		}
	}
	
	//TC3230: Verify container displayed in result grid on Promotions & Waste page when Log-in through user which has access to Level 2-6.
	@Test()
	public void promotionWaste_US855_TC3230() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level3_SSO_UserId;
		String password = LoginTestData.level3_SSO_Password;
		String storeId = LoginTestData.level3StoreId;
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String createdate = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		//Select end date as today date and start date as today date
		promotionsAndWastePage.selectStartDate(startDate);
		promotionsAndWastePage.selectEndDate(endDate);
		promotionsAndWastePage.ShowResults_BT.click();
		Thread.sleep(5000);
		promotionsAndWastePage.clickOnDateGroup(createdate);
		if(!Base.isElementDisplayed(promotionsAndWastePage.RawPromo_BT) 
				& promotionsAndWastePage.verifySelectedDateIsExpanded(createdate)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3230",
					"Level 2-6 User is able to expand the records for a selecte date",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3230_Condition1","promotionWaste_US855_TC3230",
					"Level 2-6 User is able to expand the records for a selecte date",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3230_Condition1");
		}
		
		if(promotionsAndWastePage.calculateWasteAmountForSelectedDate(createdate).equals(promotionsAndWastePage.getWasteAmountFromDateGroupHeader(createdate))
				& promotionsAndWastePage.calculatePromoAmountForSelectedDate(createdate).equals(promotionsAndWastePage.getPromoAmountFromDateGroupHeader(createdate))
				& promotionsAndWastePage.calculateCompleteWasteAmountForSelectedDate(createdate).equals(promotionsAndWastePage.getCompleteWasteAmountFromDateGroupHeader(createdate))){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3230",
					"Calculation for waste/completeWaste/Promo in container should match the total amount displayed on Footer for Raw Waste, Completed Waste and Raw Promo respectively.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3230_Condition2","promotionWaste_US855_TC3230",
					"Calculation for waste/completeWaste/Promo in container should match the total amount displayed on Footer for Raw Waste, Completed Waste and Raw Promo respectively.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3230_Condition2");
			
		}
		promotionsAndWastePage.clickOnDateGroup(createdate);
		if (promotionsAndWastePage.verifySelectedDateIsCollapsed(createdate)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3230",
					"Level 2-6 User is able to Collapse the records for a selecte date",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3230_Condition3","promotionWaste_US855_TC3230",
					"Level 2-6 User is able to Collapse the records for a selecte date",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3230_Condition3");
		}
	}
	
	//TC3233: Verify scroll bar should be available if number of records exceeds screen size.
	@Test()
	public void promotionWaste_US855_TC3233() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level2_SSO_UserId;
		String password = LoginTestData.level2_SSO_Password;
		String storeId = LoginTestData.level2StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar instance = Calendar.getInstance();
	    instance.add(Calendar.MONTH, -1);
	    instance.set(Calendar.DAY_OF_MONTH, 1);
	    String startDate = dateFormat.format(instance.getTime());
	    Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, 0);
		String endDate = dateFormat.format(cal2.getTime());
		System.out.println("endDate "+endDate);
		promotionsAndWastePage.selectStartDate(startDate);
		promotionsAndWastePage.selectEndDate(endDate);
		promotionsAndWastePage.ShowResults_BT.click();
		Thread.sleep(2000);
		int noOfrecords = promotionsAndWastePage.WasteRecordsList.size();
		Actions actions = new Actions(driver);
		actions.moveToElement(promotionsAndWastePage.WasteRecordsList.get(noOfrecords-1));
		actions.perform();
		//verify that Promotion and Waste page is accessible from the Main Menu
		if (promotionsAndWastePage.WasteRecordsList.get(noOfrecords-1).isDisplayed()) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3233",
					"Scroll bar should get appear to accommodate all records.",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3233","promotionWaste_US855_TC3233",
					"Scroll bar should get appear to accommodate all records.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3233");
		}
	}
	
	//TC3238: Verify Enter Raw Waste button functionality on Promotions & Waste page.
	@Test()
	public void promotionWaste_US855_TC3238() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level2_SSO_UserId;
		String password = LoginTestData.level2_SSO_Password;
		String storeId = LoginTestData.level2StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver,RawItemWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		//verify that Promotion and Waste page is accessible from the Main Menu
		if (Base.isElementDisplayed(rawItemWastePage.SelectDateAndTime_TB)
				& Base.isElementDisplayed(rawItemWastePage.SelectTime_TB)
				& Base.isElementDisplayed(rawItemWastePage.RawItemWasted_TB)
				& Base.isElementDisplayed(rawItemWastePage.AddSearchItem_Icon)
				& rawItemWastePage.Submit_BT.getAttribute("disabled").equals("true")
				& Base.isElementDisplayed(rawItemWastePage.Cancel_BT)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3238",
					"User should be able to view Raw Waste screen with details: 1. (Header) Raw Waste 2. Date { Field with Calendar Widget}"
					+ " 3. Time { Field with Clock} 4. Item { Field with + sign in it} 5. Submit {button} - disabled 6. Cancel { button}",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3238","promotionWaste_US855_TC3238",
					"User should be able to view Raw Waste screen with details: 1. (Header) Raw Waste 2. Date { Field with Calendar Widget}"
					+ " 3. Time { Field with Clock} 4. Item { Field with + sign in it} 5. Submit {button} - disabled 6. Cancel { button}.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3238");
		}
	}
	
	//TC3239: Verify Enter Raw Promo button functionality on Promotions & Waste page.
	@Test()
	public void promotionWaste_US855_TC3239() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemPromoPage rawItemPromoPage = PageFactory.initElements(driver,RawItemPromoPage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		promotionsAndWastePage.RawPromo_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemPromoPage.RawPromo_Title));
		//verify that Promotion and Waste page is accessible from the Main Menu
		if (Base.isElementDisplayed(rawItemPromoPage.SelectDateAndTime_TB)
				& Base.isElementDisplayed(rawItemPromoPage.SelectTime_TB)
				& Base.isElementDisplayed(rawItemPromoPage.RawItemWasted_TB)
				& Base.isElementDisplayed(rawItemPromoPage.AddSearchItem_Icon)
				& rawItemPromoPage.Submit_BT.getAttribute("disabled").equals("true")
				& Base.isElementDisplayed(rawItemPromoPage.Cancel_BT)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3239",
					"User should be able to view Raw Promo screen with details: 1. (Header) Raw Promo 2. Date { Field with Calendar Widget}"
					+ " 3. Time { Field with Clock} 4. Item { Field with + sign in it} 5. Submit {button} - disabled 6. Cancel { button}",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3239","promotionWaste_US855_TC3239",
					"User should be able to view Raw Promo screen with details: 1. (Header) Raw Promo 2. Date { Field with Calendar Widget}"
					+ " 3. Time { Field with Clock} 4. Item { Field with + sign in it} 5. Submit {button} - disabled 6. Cancel { button}.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3239");
		}
	}
	
	//TC3240: Verify Enter Completed Waste button functionality on Promotions & Waste page.
	@Test()
	public void promotionWaste_US855_TC3240() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver,CompletedWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		promotionsAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		//verify that Promotion and Waste page is accessible from the Main Menu
		if (Base.isElementDisplayed(completedWastePage.SelectDateAndTime_TB)
				& Base.isElementDisplayed(completedWastePage.SelectTime_TB)
				& Base.isElementDisplayed(completedWastePage.CompletedWastePopUp_SearchBox_TB)
				& Base.isElementDisplayed(completedWastePage.AddSearchItem_Icon)
				& completedWastePage.Submit_BT.getAttribute("disabled").equals("true")
				& Base.isElementDisplayed(completedWastePage.Cancel_BT)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3240",
					"User should be able to view Completed Waste screen with details: 1. (Header) Completed Waste 2. Date { Field with Calendar Widget}"
					+ " 3. Time { Field with Clock} 4. Item { Field with + sign in it} 5. Submit {button} - disabled 6. Cancel { button}",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3240","promotionWaste_US855_TC3240",
					"User should be able to view Completed Waste screen with details: 1. (Header) Completed Waste 2. Date { Field with Calendar Widget}"
					+ " 3. Time { Field with Clock} 4. Item { Field with + sign in it} 5. Submit {button} - disabled 6. Cancel { button}.",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3240");
		}
	}
	
	//TC3248: Verify submit button functionality on Enter Raw Waste screen.
	@Test()
	public void promotionWaste_US855_TC3248() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		String wrinId = GlobalVariable.rawItemWatsewrin1;
		String caseQuantity = "1";
		String innerPackQuantity = "2";
		String looseUnitQuantity = "3";
		String createDate = GlobalVariable.createDate;
		String time = GlobalVariable.time;
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver,RawItemWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//CLick on Raw Waste Button
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		rawItemWastePage.removeAllWrinIdFromRawWastePage();
		if(rawItemWastePage.verifyBackTimeIsSelected(time)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3248",
					"User should be able to Select Time on clicking the down arrow button In Raw Waste Screen", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3248_Condition1","promotionWaste_US855_TC3248",
					"User should be able to Select Time on clicking the down arrow button In Raw Waste Screen", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3248_Condition1");
		}
		//String time = rawItemWastePage.getTimeToSet();
		rawItemWastePage.selectDateForRawWaste(createDate);
		//Create a raw waste entry
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId);
		if(Base.isElementDisplayed(rawItemWastePage.RawWasteForm_ItemAdded_Message)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3248",
					"User should be able to view success message on adding a wrin in Raw Waste Form", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3248_Condition2","promotionWaste_US855_TC3248",
					"User should be able to view success message on adding a wrin in Raw Waste Form", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3248_Condition2");
		}
		
		if (Base.isElementDisplayed(rawItemWastePage.RawWaste_Wrin_Header)
				& Base.isElementDisplayed(rawItemWastePage.RawWaste_Description_Header)
				& Base.isElementDisplayed(rawItemWastePage.RawWaste_OuterPack_Header)
				& Base.isElementDisplayed(rawItemWastePage.RawWaste_InnerPack_Header)
				& Base.isElementDisplayed(rawItemWastePage.RawWaste_LooseUnit_Header)
				& Base.isElementDisplayed(rawItemWastePage.RawWaste_TotalUnits_Header)
				& Base.isElementDisplayed(rawItemWastePage.RawWaste_Subtotal_Header)
				& rawItemWastePage.Submit_BT.getAttribute("disabled").equals("true")) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3248",
					"User should be able to view table grid with WRIN- {WRIN ID} Description- {WRIN Name} Outer Pack- { Text box}"
					+ "Inner Pack ,Loose Unit ,Total Units Sub- total Remove details after adding a wrin in Raw Waste Form", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3248_Condition3","promotionWaste_US855_TC3248",
					"User should be able to view table grid with WRIN- {WRIN ID} Description- {WRIN Name} Outer Pack- { Text box}"
					+ "Inner Pack ,Loose Unit ,Total Units Sub- total Remove details after adding a wrin in Raw Waste Form", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3248_Condition3");
		}
		//Get the total waste amount
		rawItemWastePage.addQuantitiesForMultipleWrin(wrinId, innerPackQuantity, caseQuantity, looseUnitQuantity);
		Thread.sleep(2000);
		String wasteAmount = rawItemWastePage.getTotalWasteAmunt();
		System.out.println("wasteAmount "+wasteAmount);
		//Submit the raw waste entry
		rawItemWastePage.Submit_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.SubmitRawWaste_PopUp_YES_BT)).click();
		if (Base.isElementDisplayed(rawItemWastePage.WasteEntrySaved_Confirmation_MSG)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3248",
					"Complete Waste screen should get closed with an message Complete Waste entry saved at the bottom of the screen.", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3248_Condition4","promotionWaste_US855_TC3248",
					"Complete Waste screen should get closed with an message Complete Waste entry saved at the bottom of the screen.", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3248_Condition4");
		}
		Thread.sleep(5000);
		promotionsAndWastePage.selectStartDate(startDate);
		promotionsAndWastePage.selectEndDate(endDate);
		promotionsAndWastePage.ShowResults_BT.click();
		Thread.sleep(5000);
		//Verify that raw waste entry should displayed in Promotion and waste page
		if (!wasteAmount.equals("0.00") & promotionsAndWastePage.isRawWasteEntryPresent(createDate, wasteAmount)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3248",
					"User should be able to enter raw waste", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3248_Condition5","promotionWaste_US855_TC3248",
					"User should be able to enter raw waste", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3248_Condition5");
		}
	}
	
	//TC3250: Verify the validation performed on Enter Raw Waste screen while adding WRIN's.
	@Test()
	public void promotionWaste_US855_TC3250() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		String wrinId1 = GlobalVariable.rawItemWastewrin2;
		String wrinId1Description = GlobalVariable.rawItemWastewrin2Description;
		String wrinId2 = GlobalVariable.rawItemWatsewrin1;
		String caseQuantity = "2";
		String innerPackQuantity = "2";
		String looseUnitQuantity = "3";
		String createDate = GlobalVariable.createDate;
		String time = GlobalVariable.time;
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver,RawItemWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//CLick on Raw Waste Button
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		rawItemWastePage.removeAllWrinIdFromRawWastePage();
		//String time = rawItemWastePage.getTimeToSet();
		rawItemWastePage.selectDateForRawWaste(createDate).selectTimeInRawWasteForm(time);
		//Create a raw waste entry
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId1);
		//wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWasteForm_ItemAdded_Message));
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId1Description);
		if (Base.isElementDisplayed(rawItemWastePage.DuplicateItem_Error_Message)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3250",
					"Duplicate Items not Allowed message should be displayed on screen when user will try to add the same wrin twice", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3250_Condition1","promotionWaste_US855_TC3250",
					"Duplicate Items not Allowed message should be displayed on screen when user will try to add the same wrin twice", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3250_Condition1");
		}
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId2);
		rawItemWastePage.addQuantitiesForMultipleWrin(wrinId1, "", caseQuantity, "");
		rawItemWastePage.addQuantitiesForMultipleWrin(wrinId2, "", caseQuantity, "");
		//Get the total waste amount
		String wasteAmount = rawItemWastePage.getTotalWasteAmunt();
		System.out.println("wasteAmount "+wasteAmount);
		//Submit the raw waste entry
		rawItemWastePage.Submit_BT.click();
		Thread.sleep(5000);
		promotionsAndWastePage.selectStartDate(startDate);
		promotionsAndWastePage.selectEndDate(endDate);
		promotionsAndWastePage.ShowResults_BT.click();
		Thread.sleep(2000);
		//Verify that raw waste entry should displayed in Promotion and waste page
		if (promotionsAndWastePage.isRawWasteEntryPresent(createDate, wasteAmount)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3250",
					"System should allow user to submit entry if quantity is filled of at least one added WRIN.", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3250_Condition2","promotionWaste_US855_TC3250",
					"System should allow user to submit entry if quantity is filled of at least one added WRIN.", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3250_Condition2");
		}
	}
	
	//TC3251: Verify the validation performed on Enter Raw Waste screen while adding WRIN's.
	@Test()
	public void promotionWaste_US855_TC3251() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		String wrinId1 = GlobalVariable.rawItemWatsewrin1;
		String wrinId2 = GlobalVariable.rawItemWastewrin2;
		String caseQuantity = "2";
		String innerPackQuantity = "2";
		String looseUnitQuantity = "3";
		String createDate = GlobalVariable.createDate;
		String time = GlobalVariable.time;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver,RawItemWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//CLick on Raw Waste Button
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		rawItemWastePage.removeAllWrinIdFromRawWastePage();
		//String time = rawItemWastePage.getTimeToSet();
		rawItemWastePage.selectDateForRawWaste(createDate).selectTimeInRawWasteForm(time);
		//Create a raw waste entry
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId1);
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId2);
		rawItemWastePage.addQuantitiesForMultipleWrin(wrinId1, innerPackQuantity, caseQuantity, looseUnitQuantity);
		if (rawItemWastePage.Submit_BT.getAttribute("disabled")== null) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3251",
					"Submit button should be enabled when quantity is provided to at least one of the item quantity fields", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3251_Condition1","promotionWaste_US855_TC3251",
					"Submit button should be enabled when quantity is provided to at least one of the item quantity fields", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3251_Condition1");
		}
		rawItemWastePage.removeWrinIdFromRawWastePage(wrinId1);
		rawItemWastePage.Submit_BT.click();
		Thread.sleep(1000);
		if (Base.isElementDisplayed(rawItemWastePage.RawWaste_Title) & rawItemWastePage.Submit_BT.getAttribute("disabled").equals("true")) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3251",
					"Submit button should be disabled when quantity is not provided to any of the item quantity fields", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3251_Condition1","promotionWaste_US855_TC3251",
					"Submit button should be disabled when quantity is not provided to any of the item quantity fields", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3251_Condition1");
		}
	}
	
	//TC3252: Verify the Cancel button functionality on Enter Raw Waste Page.
	@Test()
	public void promotionWaste_US855_TC3252() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		String wrinId1 = GlobalVariable.rawItemWatsewrin1;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver,RawItemWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//CLick on Raw Waste Button
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		rawItemWastePage.removeAllWrinIdFromRawWastePage();
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId1);
		rawItemWastePage.Cancel_BT.click();
		boolean warningMessageDisplayed = Base.isElementDisplayed(rawItemWastePage.CancelRawWastePopUp_Confirmation_Message);
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWasteEntryIncomplete_PopUp_NO_BT)).click();
		Thread.sleep(2000);
		if (warningMessageDisplayed & !Base.isElementDisplayed(rawItemWastePage.RawWasteEntryIncomplete_PopUp_NO_BT)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3252",
					"On clickinng cancel button System should display a Warning message with No and Yes options and user should return to RawWaste form on clicking No button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3252_Condition1","promotionWaste_US855_TC3252",
					"On clickinng cancel button System should display a Warning message with No and Yes options and user should return to RawWaste form on clicking No button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3252_Condition1");
		}
		rawItemWastePage.Cancel_BT.click();
		warningMessageDisplayed = Base.isElementDisplayed(rawItemWastePage.CancelRawWastePopUp_Confirmation_Message);
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWasteEntryIncomplete_PopUp_YES_BT)).click();
		Thread.sleep(2000);
		if (warningMessageDisplayed & !Base.isElementDisplayed(rawItemWastePage.RawWaste_Title)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWaste_Title)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3252",
					"On clickinng cancel button System should display a Warning message with No and Yes options and RawWaste form should be closed on clicking Yes button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3252_Condition2","promotionWaste_US855_TC3252",
					"On clickinng cancel button System should display a Warning message with No and Yes options and RawWaste form should be closed on clicking Yes button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3252_Condition2");
		}
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		if (!rawItemWastePage.verifyWasteItemIsAdded(wrinId1)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3252",
					"User should be able to open a fresh Raw Waste Form after canceling the previous entry", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3252_Condition3","promotionWaste_US855_TC3252",
					"User should be able to open a fresh Raw Waste Form after canceling the previous entry", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3252_Condition3");
		}
	}
	
	// TC3253: Verify single Raw Waste screen open feature with Close button functionality.
	@Test()
	public void promotionWaste_US855_TC3253() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		String wrinId1 = GlobalVariable.rawItemWatsewrin1;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver,RawItemWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId).navigateToInventoryManagement()
				.goToPromotionsAndWastePage();
		// CLick on Raw Waste Button
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		rawItemWastePage.removeAllWrinIdFromRawWastePage();
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId1);
		rawItemWastePage.RawWasteForm_SliderToggle_BT.click();
		//verify that user User should be able to close add manual vendor form on clicking Cancel button
		if (rawItemWastePage.RawWasteForm_Container.getAttribute("class").contains("modalCollapsedView")	) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3253",
					"User should be able to collapse the Raw Waste screen","Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3253_Condition1","promotionWaste_US855_TC3253",
					"User should be able to collapse the Raw Waste screen","Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3253_Condition1");
		}
		promotionsAndWastePage.RawWaste_BT.click();
		Thread.sleep(2000);
		rawItemWastePage.Cancel_BT.click();
		boolean warningMessageDisplayed = Base.isElementDisplayed(rawItemWastePage.CancelRawWastePopUp_Confirmation_Message);
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWasteEntryIncomplete_PopUp_NO_BT)).click();
		Thread.sleep(2000);
		if (warningMessageDisplayed & !Base.isElementDisplayed(rawItemWastePage.RawWasteEntryIncomplete_PopUp_NO_BT)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3253",
					"On clickinng cancel button System should display a Warning message with No and Yes options and user should return to RawWaste form on clicking No button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3253_Condition2","promotionWaste_US855_TC3253",
					"On clickinng cancel button System should display a Warning message with No and Yes options and user should return to RawWaste form on clicking No button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3253_Condition2");
		}
		rawItemWastePage.Cancel_BT.click();
		warningMessageDisplayed = Base.isElementDisplayed(rawItemWastePage.CancelRawWastePopUp_Confirmation_Message);
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWasteEntryIncomplete_PopUp_YES_BT)).click();
		Thread.sleep(2000);
		if (warningMessageDisplayed & !Base.isElementDisplayed(rawItemWastePage.RawWaste_Title)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWaste_Title)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3253",
					"On clickinng cancel button System should display a Warning message with No and Yes options and RawWaste form should be closed on clicking Yes button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3253_Condition3","promotionWaste_US855_TC3253",
					"On clickinng cancel button System should display a Warning message with No and Yes options and RawWaste form should be closed on clicking Yes button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3253_Condition3");
		}
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		if (!rawItemWastePage.verifyWasteItemIsAdded(wrinId1)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3253",
					"User should be able to open a fresh Raw Waste Form after canceling the previous entry", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3253_Condition4","promotionWaste_US855_TC3253",
					"User should be able to open a fresh Raw Waste Form after canceling the previous entry", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3253_Condition4");
		}
	}
	
	// TC3254: Verify single Raw Waste screen open feature with Cross(X) icon functionality.
	@Test()
	public void promotionWaste_US855_TC3254() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		String wrinId1 = GlobalVariable.rawItemWatsewrin1;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver,RawItemWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId).navigateToInventoryManagement()
				.goToPromotionsAndWastePage();
		// CLick on Raw Waste Button
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		rawItemWastePage.removeAllWrinIdFromRawWastePage();
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId1);
		rawItemWastePage.RawWasteForm_SliderToggle_BT.click();
		//verify that user User should be able to close add manual vendor form on clicking Cancel button
		if (rawItemWastePage.RawWasteForm_Container.getAttribute("class").contains("modalCollapsedView")	) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3254",
					"User should be able to collapse the Raw Waste screen","Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3254_Condition1","promotionWaste_US855_TC3254",
					"User should be able to collapse the Raw Waste screen","Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3254_Condition1");
		}
		promotionsAndWastePage.RawWaste_BT.click();
		Thread.sleep(2000);
		rawItemWastePage.RawWasteForm_Close_BT.click();
		boolean warningMessageDisplayed = Base.isElementDisplayed(rawItemWastePage.CancelRawWastePopUp_Confirmation_Message);
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWasteEntryIncomplete_PopUp_NO_BT)).click();
		Thread.sleep(2000);
		if (warningMessageDisplayed & !Base.isElementDisplayed(rawItemWastePage.RawWasteEntryIncomplete_PopUp_NO_BT)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3254",
					"On clickinng Close button System should display a Warning message with No and Yes options and user should return to RawWaste form on clicking No button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3254_Condition2","promotionWaste_US855_TC3254",
					"On clickinng Close button System should display a Warning message with No and Yes options and user should return to RawWaste form on clicking No button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3254_Condition2");
		}
		rawItemWastePage.RawWasteForm_Close_BT.click();
		warningMessageDisplayed = Base.isElementDisplayed(rawItemWastePage.CancelRawWastePopUp_Confirmation_Message);
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWasteEntryIncomplete_PopUp_YES_BT)).click();
		Thread.sleep(4000);
		if (warningMessageDisplayed & !Base.isElementDisplayed(rawItemWastePage.RawWaste_Title)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWaste_Title)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3254",
					"On clickinng cancel button System should display a Warning message with No and Yes options and RawWaste form should be closed on clicking Yes button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3254_Condition3","promotionWaste_US855_TC3254",
					"On clickinng cancel button System should display a Warning message with No and Yes options and RawWaste form should be closed on clicking Yes button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3254_Condition3");
		}
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		if (!rawItemWastePage.verifyWasteItemIsAdded(wrinId1)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3254",
					"User should be able to open a fresh Raw Waste Form after closing the previous entry", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3254_Condition4","promotionWaste_US855_TC3254",
					"User should be able to open a fresh Raw Waste Form after closing the previous entry", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3254_Condition4");
		}
	}
	
	//TC3256: Verify single Raw Waste screen open feature with Submit button functionality.
	@Test()
	public void promotionWaste_US855_TC3256() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		String wrinId1 = GlobalVariable.rawItemWatsewrin1;
		String caseQuantity = "2";
		String innerPackQuantity = "2";
		String looseUnitQuantity = "3";
		String createDate = GlobalVariable.createDate;
		String time = GlobalVariable.time;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver,RawItemWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId).navigateToInventoryManagement()
				.goToPromotionsAndWastePage();
		// CLick on Raw Waste Button
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		rawItemWastePage.removeAllWrinIdFromRawWastePage();
		//String time = rawItemWastePage.getTimeToSet();
		rawItemWastePage.selectDateForRawWaste(createDate).selectTimeInRawWasteForm(time);
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId1);
		rawItemWastePage.RawWasteForm_SliderToggle_BT.click();
		promotionsAndWastePage.RawWaste_BT.click();
		Thread.sleep(2000);
		if(rawItemWastePage.Submit_BT.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3256",
					"Submit button should be disabled when quantity is not added for raw waste item", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3256_Condition1","promotionWaste_US855_TC3256",
					"Submit button should be disabled when quantity is not added for raw waste item", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3256_Condition1");
		}
		rawItemWastePage.addQuantitiesForMultipleWrin(wrinId1, innerPackQuantity, caseQuantity, looseUnitQuantity);
		rawItemWastePage.Submit_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.SubmitRawWaste_PopUp_YES_BT)).click();
		if (Base.isElementDisplayed(rawItemWastePage.WasteEntrySaved_Confirmation_MSG)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3256",
					"Raw Waste screen should get closed with message Raw Waste entry saved at bottom of the screen on clicking submit button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3256_Condition2","promotionWaste_US855_TC3256",
					"Raw Waste screen should get closed with message Raw Waste entry saved at bottom of the screen on clicking submit button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3256_Condition2");
		}
	}
	
	// TC3258: Verify scroll bar and form collapse and re-open feature on Enter Raw Waste screen.
	@Test()
	public void promotionWaste_US855_TC3258() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		String storeId = LoginTestData.level1StoreId;
		String wrinId1 = GlobalVariable.rawItemWatsewrin1;
		String wrinId2 = GlobalVariable.rawItemWastewrin2;
		String wrinId3 = GlobalVariable.rawItemWastewrin3;
		String wrinId4 = GlobalVariable.rawItemWastewrin4;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RawItemWastePage rawItemWastePage = PageFactory.initElements(driver,RawItemWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId).navigateToInventoryManagement()
				.goToPromotionsAndWastePage();
		// CLick on Raw Waste Button
		promotionsAndWastePage.RawWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(rawItemWastePage.RawWaste_Title));
		rawItemWastePage.removeAllWrinIdFromRawWastePage();
		rawItemWastePage.RawWasteForm_SliderToggle_BT.click();
		//verify that user User should be able to close add manual vendor form on clicking Cancel button
		if (rawItemWastePage.RawWasteForm_Container.getAttribute("class").contains("modalCollapsedView")	) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3258",
					"User should be able to collapse the Raw Waste screen","Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3258_Condition1","promotionWaste_US855_TC3258",
					"User should be able to collapse the Raw Waste screen","Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3258_Condition1");
		}
		rawItemWastePage.RawWasteForm_SliderToggle_BT.click();
		//verify that User should be able to close add manual vendor form on clicking  Close(X) button
		if (rawItemWastePage.RawWasteForm_Container.getAttribute("class").contains("modalExpandedView")	) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3258",
					"User should be able to expand the collapsed screen","Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3258_Condition2","promotionWaste_US855_TC3258",
					"User should be able to expand the collapsed screen","Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3258_Condition2");
		}
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId1);
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId2);
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId3);
		rawItemWastePage.searchAndSelectRawItemWasted(wrinId4);
		int noOfItem = rawItemWastePage.RawWasteForm_WasteItemEntry_List.size();
		Actions actions = new Actions(driver);
		actions.moveToElement(rawItemWastePage.RawWasteForm_WasteItemEntry_List.get(noOfItem-1));
		actions.perform();
		//verify that Promotion and Waste page is accessible from the Main Menu
		if (rawItemWastePage.RawWasteForm_WasteItemEntry_List.get(noOfItem-1).isDisplayed()) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3258",
					"Scroll bar should appear when added MI's exceed the fixed height in Raw Waste Form",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3258_Condition3","promotionWaste_US855_TC3258",
					"Scroll bar should appear when added MI's exceed the fixed height in Raw Waste Form",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3258_Condition3");
		}
	}

	//TC3261: Verify submit button functionality on Enter Complete Waste screen.
	@Test()
	public void promotionWaste_US855_TC3261() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.operator_SSO_UserId;
		String password = LoginTestData.operator_SSO_Password;
		String storeId = LoginTestData.operatorStoreId;
		String menuItemId = GlobalVariable.completedWasteWrin1;
		String quantity = "2";
		String createDate = GlobalVariable.createDate;
		String time = GlobalVariable.time;
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver,CompletedWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//CLick on Raw Waste Button
		promotionsAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		completedWastePage.removeAllWrinIdFromCompletedWastePage();
		//String time = completedWastePage.getTimeToSet();
		completedWastePage.selectDateForRawWaste(createDate).selectTimeInRawWasteForm(time);
		//Create a raw waste entry
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId);
		if(Base.isElementDisplayed(completedWastePage.CompletedWasteForm_ItemAdded_Message)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3261",
					"User should be able to view success message on adding a wrin in Completed Waste Form", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3261_Condition1","promotionWaste_US855_TC3261",
					"User should be able to view success message on adding a wrin in Completed Waste Form", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3261_Condition1");
		}
		if (Base.isElementDisplayed(completedWastePage.CompletedWaste_MenuItem_Header)
				& Base.isElementDisplayed(completedWastePage.CompletedWaste_Description_Header)
				& Base.isElementDisplayed(completedWastePage.CompletedWaste_QuantityWasted_Header)
				& Base.isElementDisplayed(completedWastePage.CompletedWaste_SubTotal_Header)
				& completedWastePage.Submit_BT.getAttribute("disabled").equals("true")) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3261",
					"User should be able to view MenuItem, Description, QuantityWasted, SubTotal header in Completed waste form", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3261_Condition2","promotionWaste_US855_TC3261",
					"User should be able to view MenuItem, Description, QuantityWasted, SubTotal header in Completed waste form", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3261_Condition2");
		}
		completedWastePage.addQuantitiesForMultipleWrin(menuItemId, quantity);
		//Get the total waste amount
		String wasteAmount = completedWastePage.getTotalCompletedWasteAmount();
		System.out.println("wasteAmount "+wasteAmount);
		//Submit the raw waste entry
		completedWastePage.Submit_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.SubmitCompletedWaste_PopUp_YES_BT)).click();
		if (Base.isElementDisplayed(completedWastePage.WasteEntrySaved_Confirmation_MSG)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3261",
					"Complete Waste screen should get closed with an message Complete Waste entry saved at the bottom of the screen.", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3261_Condition3","promotionWaste_US855_TC3261",
					"Complete Waste screen should get closed with an message Complete Waste entry saved at the bottom of the screen.", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3261_Condition3");
		}
		Thread.sleep(3000);
		promotionsAndWastePage.selectStartDate(startDate);
		promotionsAndWastePage.selectEndDate(endDate);
		promotionsAndWastePage.ShowResults_BT.click();
		Thread.sleep(2000);
		//Verify that raw waste entry should displayed in Promotion and waste page
		if (!wasteAmount.equals("0.00") & promotionsAndWastePage.isCompletedWasteEntryPresent(createDate, wasteAmount)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3261",
					"User should be able to view Completed waste entry for selected date", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3261_Condition4","promotionWaste_US855_TC3261",
					"User should be able to view Completed waste entry for selected date", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3261_Condition4");
		}
	}
	
	//TC3262: Verify the validation performed on Enter Complete Waste screen while adding MI.
	@Test()
	public void promotionWaste_US855_TC3262() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.operator_SSO_UserId;
		String password = LoginTestData.operator_SSO_Password;
		String storeId = LoginTestData.operatorStoreId;
		String menuItemId1 = GlobalVariable.completedWasteWrin1;
		String menuItem1Description = GlobalVariable.completedWasteWrin1Description;
		String menuItemId2 = GlobalVariable.completedWasteWrin2;
		String quantity = "2";
		String createDate = GlobalVariable.createDate;
		String time = GlobalVariable.time;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver,CompletedWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//CLick on Raw Waste Button
		promotionsAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		completedWastePage.removeAllWrinIdFromCompletedWastePage();
		//String time = completedWastePage.getTimeToSet();
		completedWastePage.selectDateForRawWaste(createDate).selectTimeInRawWasteForm(time);
		//Create a raw waste entry
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId1);
		completedWastePage.searchMenuItemForCompletedWaste(menuItem1Description);
		if (Base.isElementDisplayed(completedWastePage.DuplicateItem_Error_Message)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3262",
					"Duplicate Items not Allowed message should be displayed on screen when user will try to add the same menu item twice", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3262_Condition1","promotionWaste_US855_TC3262",
					"Duplicate Items not Allowed message should be displayed on screen when user will try to add the same menu Item twice", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3262_Condition1");
		}
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId2);
		completedWastePage.addQuantitiesForMultipleWrin(menuItemId1,quantity);
		boolean submitButtonEnabled = (completedWastePage.Submit_BT.getAttribute("disabled")== null);
		completedWastePage.Submit_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.SubmitCompletedWaste_PopUp_YES_BT)).click();
		if(submitButtonEnabled & Base.isElementDisplayed(completedWastePage.WasteEntrySaved_Confirmation_MSG)){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3262",
					"User is able to submit completed waste entry when quantity is filled of at least one added MI ", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3262_Condition2","promotionWaste_US855_TC3262",
					"User is able to submit completed waste entry when quantity is filled of at least one added MI", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3262_Condition2");
		}
	}
	
	//TC3263: Verify Remove functionality on Enter Complete Waste screen.
	@Test()
	public void promotionWaste_US855_TC3263() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.operator_SSO_UserId;
		String password = LoginTestData.operator_SSO_Password;
		String storeId = LoginTestData.operatorStoreId;
		String menuItemId1 = GlobalVariable.completedWasteWrin1;
		String menuItemId2 = GlobalVariable.completedWasteWrin2;
		String quantity = "2";
		String createDate = GlobalVariable.createDate;
		String time = GlobalVariable.time;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver,CompletedWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//CLick on Raw Waste Button
		promotionsAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		completedWastePage.removeAllWrinIdFromCompletedWastePage();
		//String time = completedWastePage.getTimeToSet();
		completedWastePage.selectDateForRawWaste(createDate).selectTimeInRawWasteForm(time);
		//Create a raw waste entry
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId1);
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId2);
		completedWastePage.addQuantitiesForMultipleWrin(menuItemId1,quantity);
		if (completedWastePage.Submit_BT.getAttribute("disabled") == null) {
			Reporter.reportPassResult(
					browser,"promotionWaste_US855_TC3263",
					"Submit button should be enabled when quantity is provided to at least one of the item quantity fields",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"promotionWaste_US855_TC3263_Condition1","promotionWaste_US855_TC3263",
					"Submit button should be enabled when quantity is provided to at least one of the item quantity fields",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3263_Condition1");
		}
		completedWastePage.removeWrinIdFromCompletedWastePage(menuItemId1);
		Thread.sleep(1000);
		completedWastePage.Submit_BT.click();
		if (Base.isElementDisplayed(completedWastePage.CompletedWaste_Title)
				& !Base.isElementDisplayed(completedWastePage.SubmitCompletedWaste_PopUp_YES_BT)) {
			Reporter.reportPassResult(
					browser,"promotionWaste_US855_TC3263",
					"User should not be able to submit waste entry when quantity is not provided to any of the item quantity fields",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"promotionWaste_US855_TC3263_Condition1","promotionWaste_US855_TC3263",
					"User should not be able to submit waste entry when quantity is not provided to any of the item quantity fields",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3263_Condition1");
		}
	}
	
	//TC3264: Verify the Cancel button functionality on Enter Complete Waste Page.
	@Test()
	public void promotionWaste_US855_TC3264() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.operator_SSO_UserId;
		String password = LoginTestData.operator_SSO_Password;
		String storeId = LoginTestData.operatorStoreId;
		String menuItemId1 = GlobalVariable.completedWasteWrin1;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver,CompletedWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage =  homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId)
				.navigateToInventoryManagement().goToPromotionsAndWastePage();
		//CLick on Raw Waste Button
		promotionsAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		completedWastePage.removeAllWrinIdFromCompletedWastePage();
		//Create a raw waste entry
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId1);
		completedWastePage.Cancel_BT.click();
		boolean warningMessageDisplayed = Base.isElementDisplayed(completedWastePage.CancelCompletedWastePopUp_Warning_Message);
		System.out.println("Msg "+completedWastePage.CancelCompletedWastePopUp_Warning_Message.getText());
		System.out.println("Comp "+completedWastePage.CancelCompletedWastePopUp_Warning_Message.getText().contains("All entered information will be lost. Are you sure you want to cancel?"));	
		warningMessageDisplayed = warningMessageDisplayed 
				& completedWastePage.CancelCompletedWastePopUp_Warning_Message.getText().contains("All entered information will be lost. Are you sure you want to cancel?");
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWasteEntryIncomplete_PopUp_NO_BT)).click();
		Thread.sleep(2000);
		if (warningMessageDisplayed & !Base.isElementDisplayed(completedWastePage.CompletedWasteEntryIncomplete_PopUp_NO_BT)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3264",
					"On clickinng cancel button System should display a Warning message with No and Yes options and user should return to Completed Waste form on clicking No button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3264_Condition1","promotionWaste_US855_TC3264",
					"On clickinng cancel button System should display a Warning message with No and Yes options and user should return to Completed Waste form on clicking No button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3264_Condition1");
		}
		completedWastePage.Cancel_BT.click();
		warningMessageDisplayed = Base.isElementDisplayed(completedWastePage.CancelCompletedWastePopUp_Warning_Message);
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWasteEntryIncomplete_PopUp_YES_BT)).click();
		Thread.sleep(2000);
		if (warningMessageDisplayed & !Base.isElementDisplayed(completedWastePage.CompletedWaste_Title)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWaste_Title)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3264",
					"On clickinng cancel button System should display a Warning message with No and Yes options and Completed Waste form should be closed on clicking Yes button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3264_Condition2","promotionWaste_US855_TC3264",
					"On clickinng cancel button System should display a Warning message with No and Yes options and Completed Waste form should be closed on clicking Yes button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3264_Condition2");
		}
		promotionsAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		if (!completedWastePage.verifyCompletedWasteItemIsAdded(menuItemId1)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3264",
					"User should be able to open a fresh Completed Waste Form after canceling the previous entry", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3264_Condition3","promotionWaste_US855_TC3264",
					"User should be able to open a fresh Completed Waste Form after canceling the previous entry", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3264_Condition3");
		}
	}
	
	//TC3265: Verify single Completed Waste screen open feature with Close button functionality.
	@Test()
	public void promotionWaste_US855_TC3265() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.operator_SSO_UserId;
		String password = LoginTestData.operator_SSO_Password;
		String storeId = LoginTestData.operatorStoreId;
		String menuItemId1 = GlobalVariable.completedWasteWrin1;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver,CompletedWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId).navigateToInventoryManagement()
				.goToPromotionsAndWastePage();
		// CLick on Raw Waste Button
		promotionsAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		completedWastePage.removeAllWrinIdFromCompletedWastePage();
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId1);
		completedWastePage.CompletedWasteForm_SliderToggle_BT.click();
		//verify that user User should be able to close add manual vendor form on clicking Cancel button
		if (completedWastePage.CompletedWasteForm_Container.getAttribute("class").contains("modalCollapsedView")	) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3265",
					"User should be able to collapse the Completed Waste screen","Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3265_Condition1","promotionWaste_US855_TC3265",
					"User should be able to collapse the Completed Waste screen","Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3265_Condition1");
		}
		promotionsAndWastePage.CompletedWaste_BT.click();
		Thread.sleep(2000);
		completedWastePage.Cancel_BT.click();
		boolean warningMessageDisplayed = Base.isElementDisplayed(completedWastePage.CancelCompletedWastePopUp_Warning_Message);
		System.out.println("Message "+completedWastePage.CancelCompletedWastePopUp_Warning_Message.getText());
		System.out.println("Compare " + completedWastePage.CancelCompletedWastePopUp_Warning_Message.getText().contains("All entered information will be lost. Are you sure you want to cancel?"));
		warningMessageDisplayed = warningMessageDisplayed 
				& completedWastePage.CancelCompletedWastePopUp_Warning_Message.getText().contains("All entered information will be lost. Are you sure you want to cancel?");
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWasteEntryIncomplete_PopUp_NO_BT)).click();
		Thread.sleep(2000);
		if (warningMessageDisplayed & !Base.isElementDisplayed(completedWastePage.CompletedWasteEntryIncomplete_PopUp_NO_BT)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3265",
					"On clickinng cancel button System should display a Warning message with No and Yes options and user should return to Completed Waste form on clicking No button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3265_Condition2","promotionWaste_US855_TC3265",
					"On clickinng cancel button System should display a Warning message with No and Yes options and user should return to Completed Waste form on clicking No button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3265_Condition2");
		}
		completedWastePage.Cancel_BT.click();
		warningMessageDisplayed = Base.isElementDisplayed(completedWastePage.CancelCompletedWastePopUp_Warning_Message);
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWasteEntryIncomplete_PopUp_YES_BT)).click();
		Thread.sleep(2000);
		if (warningMessageDisplayed & !Base.isElementDisplayed(completedWastePage.CompletedWaste_Title)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWaste_Title)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3265",
					"On clickinng cancel button System should display a Warning message with No and Yes options and Completed Waste form should be closed on clicking Yes button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3265_Condition3","promotionWaste_US855_TC3265",
					"On clickinng cancel button System should display a Warning message with No and Yes options and Completed Waste form should be closed on clicking Yes button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3265_Condition3");
		}
		promotionsAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		if (!completedWastePage.verifyCompletedWasteItemIsAdded(menuItemId1)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3265",
					"User should be able to open a fresh Completed Waste Form after canceling the previous entry", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3265_Condition4","promotionWaste_US855_TC3265",
					"User should be able to open a fresh Completed Waste Form after canceling the previous entry", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3265_Condition4");
		}
	}
	
	//TC3266: Verify single Raw Waste screen open feature with Cross(X) icon functionality.
	@Test()
	public void promotionWaste_US855_TC3266() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.operator_SSO_UserId;
		String password = LoginTestData.operator_SSO_Password;
		String storeId = LoginTestData.operatorStoreId;
		String menuItemId1 = GlobalVariable.completedWasteWrin1;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver,CompletedWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId).navigateToInventoryManagement()
				.goToPromotionsAndWastePage();
		// CLick on Raw Waste Button
		promotionsAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		completedWastePage.removeAllWrinIdFromCompletedWastePage();
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId1);
		completedWastePage.CompletedWasteForm_SliderToggle_BT.click();
		//verify that user User should be able to close add manual vendor form on clicking Cancel button
		if (completedWastePage.CompletedWasteForm_Container.getAttribute("class").contains("modalCollapsedView")	) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3266",
					"User should be able to collapse the Completed Waste screen","Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3266_Condition1","promotionWaste_US855_TC3266",
					"User should be able to collapse the Completed Waste screen","Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3266_Condition1");
		}
		promotionsAndWastePage.CompletedWaste_BT.click();
		Thread.sleep(2000);
		completedWastePage.CompletedWasteForm_Close_BT.click();
		boolean warningMessageDisplayed = Base.isElementDisplayed(completedWastePage.CancelCompletedWastePopUp_Warning_Message);
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWasteEntryIncomplete_PopUp_NO_BT)).click();
		Thread.sleep(2000);
		if (warningMessageDisplayed & !Base.isElementDisplayed(completedWastePage.CompletedWasteEntryIncomplete_PopUp_NO_BT)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3266",
					"On clickinng Close button System should display a Warning message with No and Yes options and user should return to Completed Waste form on clicking No button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3266_Condition2","promotionWaste_US855_TC3266",
					"On clickinng Close button System should display a Warning message with No and Yes options and user should return to Completed Waste form on clicking No button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3266_Condition2");
		}
		completedWastePage.CompletedWasteForm_Close_BT.click();
		warningMessageDisplayed = Base.isElementDisplayed(completedWastePage.CancelCompletedWastePopUp_Warning_Message);
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWasteEntryIncomplete_PopUp_YES_BT)).click();
		Thread.sleep(4000);
		if (warningMessageDisplayed & !Base.isElementDisplayed(completedWastePage.CompletedWaste_Title)
				& Base.isElementDisplayed(promotionsAndWastePage.PromotionAndWaste_Title)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3266",
					"On clickinng Close button System should display a Warning message with No and Yes options and  Completed Waste form should be closed on clicking Yes button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3266_Condition3","promotionWaste_US855_TC3266",
					"On clickinng Close button System should display a Warning message with No and Yes options and  Completed Waste form should be closed on clicking Yes button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3266_Condition3");
		}
		promotionsAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		if (!completedWastePage.verifyCompletedWasteItemIsAdded(menuItemId1)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3266",
					"User should be able to open a fresh  Completed Waste Form after closing the previous entry", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3266_Condition4","promotionWaste_US855_TC3266",
					"User should be able to open a fresh  Completed Waste Form after closing the previous entry", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3266_Condition4");
		}
	}
	
	//TC3267: Verify single Raw Waste screen open feature with Submit button functionality.
	@Test()
	public void promotionWaste_US855_TC3267() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.operator_SSO_UserId;
		String password = LoginTestData.operator_SSO_Password;
		String storeId = LoginTestData.operatorStoreId;
		String menuItemId1 = GlobalVariable.completedWasteWrin1;
		String quantity = "2";
		String createDate = GlobalVariable.createDate;
		//String time = GlobalVariable.time;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver,CompletedWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId).navigateToInventoryManagement()
				.goToPromotionsAndWastePage();
		// CLick on Raw Waste Button
		promotionsAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		completedWastePage.removeAllWrinIdFromCompletedWastePage();
		//String time = completedWastePage.getTimeToSet();
		completedWastePage.selectDateForRawWaste(createDate);
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId1);
		completedWastePage.CompletedWasteForm_SliderToggle_BT.click();
		Thread.sleep(1000);
		promotionsAndWastePage.CompletedWaste_BT.click();
		Thread.sleep(2000);
		if(completedWastePage.Submit_BT.getAttribute("disabled").equals("true")){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3267",
					"Submit button should be disabled when quantity is not added for Completed waste item", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3267_Condition1","promotionWaste_US855_TC3267",
					"Submit button should be disabled when quantity is not added for Completed waste item", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3267_Condition1");
		}
		completedWastePage.addQuantitiesForMultipleWrin(menuItemId1, quantity);
		completedWastePage.Submit_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.SubmitCompletedWaste_PopUp_YES_BT)).click();
		if (Base.isElementDisplayed(completedWastePage.WasteEntrySaved_Confirmation_MSG)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3267",
					"Completed Waste screen should get closed with message Completed Waste entry saved at bottom of the screen on clicking submit button", "Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3267_Condition2","promotionWaste_US855_TC3267",
					"Completed Waste screen should get closed with message Completed Waste entry saved at bottom of the screen on clicking submit button", "Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3267_Condition2");
		}
	}
	//TC3268: Verify scroll bar and form collapse and re-open feature on Enter Complete Waste screen.
	@Test()
	public void promotionWaste_US855_TC3268() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.operator_SSO_UserId;
		String password = LoginTestData.operator_SSO_Password;
		String storeId = LoginTestData.operatorStoreId;
		String menuItemId1 = GlobalVariable.completedWasteWrin1;
		String menuItemId2 = GlobalVariable.completedWasteWrin2;
		String menuItemId3 = GlobalVariable.completedWasteWrin3;
		String menuItemId4 = GlobalVariable.completedWasteWrin4;
		String menuItemId5 = GlobalVariable.completedWasteWrin5;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CompletedWastePage completedWastePage = PageFactory.initElements(driver,CompletedWastePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId).navigateToInventoryManagement()
				.goToPromotionsAndWastePage();
		// CLick on Raw Waste Button
		promotionsAndWastePage.CompletedWaste_BT.click();
		wait.until(ExpectedConditions.visibilityOf(completedWastePage.CompletedWaste_Title));
		completedWastePage.removeAllWrinIdFromCompletedWastePage();
		completedWastePage.CompletedWasteForm_SliderToggle_BT.click();
		//verify that user User should be able to close add manual vendor form on clicking Cancel button
		if (completedWastePage.CompletedWasteForm_Container.getAttribute("class").contains("modalCollapsedView")	) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3268",
					"User should be able to collapse the Completed Waste screen","Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3268_Condition1","promotionWaste_US855_TC3268",
					"User should be able to collapse the Completed Waste screen","Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3268_Condition1");
		}
		completedWastePage.CompletedWasteForm_SliderToggle_BT.click();
		//verify that User should be able to close add manual vendor form on clicking  Close(X) button
		if (completedWastePage.CompletedWasteForm_Container.getAttribute("class").contains("modalExpandedView")	) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3268",
					"User should be able to expand the collapsed Completed Waste screen","Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3268_Condition2","promotionWaste_US855_TC3268",
					"User should be able to expand the collapsed Completed Waste screen","Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3268_Condition2");
		}
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId1);
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId2);
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId3);
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId4);
		completedWastePage.searchMenuItemForCompletedWaste(menuItemId5);
		int noOfItem = completedWastePage.CompletedWasteForm_WasteItemEntry_List.size();
		Actions actions = new Actions(driver);
		actions.moveToElement(completedWastePage.CompletedWasteForm_WasteItemEntry_List.get(noOfItem-1));
		actions.perform();
		//verify that Promotion and Waste page is accessible from the Main Menu
		if (completedWastePage.CompletedWasteForm_WasteItemEntry_List.get(noOfItem-1).isDisplayed()) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3268",
					"Scroll bar should appear when added MI's exceed the fixed height in Completed Waste Form",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3268_Condition3","promotionWaste_US855_TC3268",
					"Scroll bar should appear when added MI's exceed the fixed height in Completed Waste Form",
					"Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3268_Condition3");
		}
	}
	
	//TC3270:  	Verify the View button functionality for Raw Waste on Promotion & Waste screen.
	@Test()
	public void promotionWaste_US855_TC3270() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.operator_SSO_UserId;
		String password = LoginTestData.operator_SSO_Password;
		String storeId = LoginTestData.operatorStoreId;
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String createdate = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId).navigateToInventoryManagement()
				.goToPromotionsAndWastePage();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		promotionsAndWastePage.selectStartDate(startDate);
		promotionsAndWastePage.selectEndDate(endDate);
		promotionsAndWastePage.ShowResults_BT.click();
		Thread.sleep(5000);
		promotionsAndWastePage.clickOnDateGroup(createdate);
		promotionsAndWastePage.viewWasteEntry(createdate);
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.ViewWasteForm_Title));
		//verify that user User should be able to close add manual vendor form on clicking Cancel button
		if (Base.isElementDisplayed(promotionsAndWastePage.wasteDetail_WRINColumn_Label)
				& Base.isElementDisplayed(promotionsAndWastePage.wasteDetail_DescriptionColumn_Label)
				& Base.isElementDisplayed(promotionsAndWastePage.wasteDetail_OutePackColumn_Label)
				& Base.isElementDisplayed(promotionsAndWastePage.wasteDetail_InnerPackColumn_Label)
				& Base.isElementDisplayed(promotionsAndWastePage.wasteDetail_LooseUnitColumn_Label)
				& Base.isElementDisplayed(promotionsAndWastePage.wasteDetail_TotalUnitsColumn_Label)
				& Base.isElementDisplayed(promotionsAndWastePage.wasteDetail_SubtotalColumn_Label)) {
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3270",
					"User should be able to view raw waste details with Columns WRIN b. Desciption c. Outer Pack d. Inner Pack "
					+ "e. Loose Unit f. Total Unit g. Sub-total","Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3270_Condition1","promotionWaste_US855_TC3270",
					"User should be able to view raw waste details with Columns WRIN b. Desciption c. Outer Pack d. Inner Pack "
					+ "e. Loose Unit f. Total Unit g. Sub-total","Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3270_Condition1");
		}
		if(promotionsAndWastePage.WasteDetailsEntries_List.size()>0
				& Base.isElementDisplayed(promotionsAndWastePage.wasteDetail_Total_Label)
				& !promotionsAndWastePage.wasteDetail_Total_Value.getText().equals("$0.00")){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3270",
					"User should be able to view raw item records and total amount in waste details screen ","Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3270_Condition2","promotionWaste_US855_TC3270",
					"User should be able to view raw item records and total amount in waste details screen","Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3270_Condition2");
		}
	}
	
	//TC3271: Verify the View button functionality for Completed Waste on Promotion & Waste screen.
	@Test()
	public void promotionWaste_US855_TC3271() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PromotionsAndWastePage promotionsAndWastePage;
		String userId = LoginTestData.operator_SSO_UserId;
		String password = LoginTestData.operator_SSO_Password;
		String storeId = LoginTestData.operatorStoreId;
		String startDate = GlobalVariable.startDate;
		String endDate = GlobalVariable.endDate;
		String createdate = GlobalVariable.createDate;
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to Promotion and Waste page
		promotionsAndWastePage = homePage.selectUserWithSSOLogin(userId, password).selectLocation(storeId).navigateToInventoryManagement()
				.goToPromotionsAndWastePage();
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.PromotionAndWaste_Title));
		promotionsAndWastePage.selectStartDate(startDate);
		promotionsAndWastePage.selectEndDate(endDate);
		promotionsAndWastePage.ShowResults_BT.click();
		Thread.sleep(5000);
		promotionsAndWastePage.clickOnDateGroup(createdate);
		promotionsAndWastePage.viewCompletedWasteEntry(createdate);
		wait.until(ExpectedConditions.visibilityOf(promotionsAndWastePage.ViewCompletedWasteForm_Title));
		//verify that user User should be able to close add manual vendor form on clicking Cancel button
		if (Base.isElementDisplayed(promotionsAndWastePage.ConpletedWasteDetail_MenuItem_Label)
				& Base.isElementDisplayed(promotionsAndWastePage.ConpletedWasteDetail_Description_Label)
				& Base.isElementDisplayed(promotionsAndWastePage.ConpletedWasteDetail_QuantityWasted_Label)
				& Base.isElementDisplayed(promotionsAndWastePage.ConpletedWasteDetail_SubTotal_Label)) {
			Reporter.reportPassResult(
					browser,"promotionWaste_US855_TC3271",
					"System should display Completed Waste details details with Columns a. WRIN-b. Desciption c. Quantity Wasted d. Sub-total ",
					"Pass");
		}
		else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3271_Condition1","promotionWaste_US855_TC3271",
					"System should display Completed Waste details details with Columns a. WRIN-b. Desciption c. Quantity Wasted d. Sub-total ","Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3271_Condition1");
		}
		if(promotionsAndWastePage.CompletedWasteDetailsEntries_List.size()>0
				& Base.isElementDisplayed(promotionsAndWastePage.CompletedWasteDetail_Total_Label)
				& !promotionsAndWastePage.CompletedWasteDetail_Total_Value.getText().equals("$0.00")){
			Reporter.reportPassResult(
					browser, "promotionWaste_US855_TC3271",
					"User should be able to view menu item records and total amount in Completed waste details screen ","Pass");
		} else {
			Reporter.reportTestFailure(
					browser, "promotionWaste_US855_TC3271_Condition2","promotionWaste_US855_TC3271",
					"User should be able to view menu item records and total amount in Completed waste details screen","Fail");
			AbstractTest.takeSnapShot("promotionWaste_US855_TC3271_Condition2");
		}
	}
	
}
