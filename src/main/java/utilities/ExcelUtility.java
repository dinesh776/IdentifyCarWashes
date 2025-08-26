package utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Utility class to read test data from an Excel file.
 * Loads data into a list of HashMaps where each map represents a row with column headers as keys.
 */

public class ExcelUtility {


	// List to store all test data rows as HashMaps
	private static final List<HashMap<String, String>> testData =new ArrayList<>();

	// File path and sheet name are read from the config file
	private static final String filepath=ConfigReader.getTestDataFile();
	private static final String sheetName=ConfigReader.getTestDataSheetName();




	/**
	 * Returns the test data at the specified index.
	 * @param index Index of the row in the test data list
	 * @return HashMap representing the row data
	 */

	public static HashMap<String,String> getData(int index){
		return testData.get(index);
	}


	// Static block to load test data when the class is first loaded
	static {
		getTestData();
	}

	/**
	 * Reads the Excel file and loads the data into the testData list.
	 * Each row is stored as a HashMap with column headers as keys.
	 */

	private static void getTestData() {

		try {
			//Open the Excel file
			FileInputStream fs = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			//Read the header row(first row)
			Row HeaderRow = sheet.getRow(0);

			// Iterate through each row starting from the second row
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)
			{
				Row currentRow = sheet.getRow(i);
				HashMap<String, String> currentHash = new HashMap<>();

				// Iterate through each cell in the current row
				for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++)
				{
					Cell currentCell = currentRow.getCell(j);

					// Only handle string cells for now
					switch (currentCell.getCellType())
					{
						case STRING:
							currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
							break;
					}
				}

				// Add the row data to the testData list
				testData.add(currentHash);
			}
			fs.close();    // Close the file stream
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
