package tests;

import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import util.ExcelUtil;

public class HomeTest extends BaseTest {

	HomePage homePage;

	@Test(dataProvider = "getDataFromExcel")
	public void searchValues(String searchString) {
		homePage = new HomePage(driver);
		homePage.search(searchString);
		takeScreenShot("After Searching " + searchString);
	}

	@DataProvider
	public Object[][] getDataFromExcel() {
		Sheet sheet = ExcelUtil.getSheet("Sheet1");
		int rowCount = sheet.getPhysicalNumberOfRows() - 1;
		Object[][] data = new Object[rowCount][];

		for (int i = 0; i < rowCount; i++) {
			String searchVal = sheet.getRow(i + 1).getCell(0).getStringCellValue();
			data[i] = new Object[] { searchVal };
		}

		return data;
	}

}
