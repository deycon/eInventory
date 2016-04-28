package sprint3;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import common.Base;
import common.LoginTestData;
import common.ReadTestData;
import common.Reporter;
import eInventoryPageClasses.HomePage;
import eInventoryPageClasses.PhysicalInventoryPage;
import sprint2.AbstractTest;

public class US13_PhysicalInventoryManuallyEnterInventoryCount extends AbstractTest{
	
	// TC2060::	Verify that a user can enter only numeric values in outer pack, inner pack and loose units.
	@Test()
	public void sprint3_US13_TC2060() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String userId = LoginTestData.level1_SSO_UserId;
		String password = LoginTestData.level1_SSO_Password;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint3_US13_TC2060", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet,"WRINId");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page
		physicalInventoryPage = homePage.selectUserWithSSOLogin(userId, password)
				.navigateToInventoryManagement().goToPhysicalInventoryPage();
		// Click on start New Inventory button
		physicalInventoryPage.CreateWeeklyInventory_BT.click();
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.WeeklyInventoryList_Title));
		physicalInventoryPage.Search_BT.click();
		physicalInventoryPage.Search_BT.sendKeys(wrinId);
		// enter the outer pack value and verify that error message is displaying for invalid outer pack
		physicalInventoryPage.CreateInventory_Case_TB_List.get(0).clear();
		physicalInventoryPage.CreateInventory_Case_TB_List.get(0).sendKeys("@");
		//verify that error message is displayed
		boolean errorMsgDisplayed = Base.isElementDisplayed(physicalInventoryPage.InvalidValues_Msg);
		Thread.sleep(1000);
		//Enter numeric value for outer pack quantity
		physicalInventoryPage.CreateInventory_Case_TB_List.get(0).clear();
		physicalInventoryPage.CreateInventory_Case_TB_List.get(0).sendKeys("12.34");
		//verify that error message should not displayed for numeric value
		boolean errorMsgNotDisplayedForValidValue = !(Base.isElementDisplayed(physicalInventoryPage.InvalidValues_Msg));
		//Enter invalid value for Inner pack quantity
		physicalInventoryPage.CreateInventory_Pack_TB_List.get(0).clear();
		physicalInventoryPage.CreateInventory_Pack_TB_List.get(0).sendKeys("$");
		//verify that error message is displayed
		errorMsgDisplayed = errorMsgDisplayed && Base.isElementDisplayed(physicalInventoryPage.InvalidValues_Msg);
		Thread.sleep(1000);
		//Enter numeric value for inner pack quantity
		physicalInventoryPage.CreateInventory_Pack_TB_List.get(0).clear();
		physicalInventoryPage.CreateInventory_Pack_TB_List.get(0).sendKeys("12.34");
		//verify that error message should not displayed for numeric value
		errorMsgNotDisplayedForValidValue = errorMsgNotDisplayedForValidValue && !(Base.isElementDisplayed(physicalInventoryPage.InvalidValues_Msg));
		//Enter invalid value for Loose unit quantity
		physicalInventoryPage.CreateInventory_Loose_TB_List.get(0).clear();
		physicalInventoryPage.CreateInventory_Loose_TB_List.get(0).sendKeys("#");
		//verify that error message is displayed
		errorMsgDisplayed = errorMsgDisplayed && Base.isElementDisplayed(physicalInventoryPage.InvalidValues_Msg);
		Thread.sleep(1000);
		//Enter numeric value for Loose unit quantity
		physicalInventoryPage.CreateInventory_Loose_TB_List.get(0).clear();
		physicalInventoryPage.CreateInventory_Loose_TB_List.get(0).sendKeys("12.34");
		//verify that error message should not displayed for numeric value
		errorMsgNotDisplayedForValidValue = errorMsgNotDisplayedForValidValue && !(Base.isElementDisplayed(physicalInventoryPage.InvalidValues_Msg));
		// Verify that user can enter only numeric values in outer pack, inner pack and loose units
		if (errorMsgDisplayed && errorMsgNotDisplayedForValidValue) {
			Reporter.reportPassResult(
					browser,"sprint3_US13_TC2060",
					"user can enter only numeric values in outer pack, inner pack and loose units",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US13_TC2060","sprint3_US13_TC2060",
					"user can enter only numeric values in outer pack, inner pack and loose units",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US13_TC2060");
		}
	}
	
	// TC2061::	Verify,"If the raw item is not measured in the specified column, the respective cell will be blank".
	@Test()
	public void sprint3_US13_TC2061() throws RowsExceededException,
			BiffException, WriteException, IOException, InterruptedException {
		/** Variable Section : **/
		PhysicalInventoryPage physicalInventoryPage;
		String storeId = LoginTestData.StoreId;
		String userId = LoginTestData.userId;
		HSSFSheet physicalInventoryPageSheet = ReadTestData.getTestDataSheet("sprint3_US13_TC2061", "Object1");
		String wrinId = ReadTestData.getTestData(physicalInventoryPageSheet,"WRINId");
		String inventoryType = ReadTestData.getTestData(physicalInventoryPageSheet, "ListType");
		/***********************************/
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		// Navigate to physical inventory Page >> Save a physical inventory
		physicalInventoryPage = homePage.selectUser(userId).selectLocation(storeId).navigateToInventoryManagement()
				.goToPhysicalInventoryPage().saveANewInventory(inventoryType);
		// Get the time for the inventory
		String time = physicalInventoryPage.InventoryTime_Label.getText().split("Time: ")[1];
		// Click on Menu Btn
		homePage.Menu_DD_BT.click();
		// Navigate to physical Inventory and click on the saved inventory with status "In-Progress".
		physicalInventoryPage.goToPhysicalInventoryPage().clickOnInProgressInventory(Base.returnTodayDate(), time);
		wait.until(ExpectedConditions.visibilityOf(physicalInventoryPage.SearchItem_TB));
		//Search for a win Idin saved inventory
		physicalInventoryPage.SearchItem_TB.clear();
		physicalInventoryPage.SearchItem_TB.sendKeys(wrinId);
		//enter blank values for outer pack qty, inner pack qty and loose units qty
		physicalInventoryPage.OuterPackQty_TB.clear();
		physicalInventoryPage.OuterPackQty_TB.sendKeys("");
		physicalInventoryPage.InnerPackQty_TB.clear();
		physicalInventoryPage.InnerPackQty_TB.sendKeys("");
		physicalInventoryPage.LooseCountQty_TB.clear();
		physicalInventoryPage.LooseCountQty_TB.sendKeys("");
		//save the inventory
		physicalInventoryPage.Save_BT.click();
		// verify that user is able to save inventory with blank values
		if (Base.isElementDisplayed(physicalInventoryPage.InventorySaved_Confirmation_MSG)) {
			Reporter.reportPassResult(
					browser,"sprint3_US13_TC2061",
					"User should be able to save the inventory with blank cell for outer pack, inner pack and loose units",
					"Pass");
		} else {
			Reporter.reportTestFailure(
					browser,"sprint3_US13_TC2061","sprint3_US13_TC2061",
					"User should be able to save the inventory with blank cell for outer pack, inner pack and loose units",
					"Fail");
			AbstractTest.takeSnapShot("sprint3_US13_TC2061");
		}
	}

}
