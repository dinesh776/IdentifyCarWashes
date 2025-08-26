package utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	private  static  final Logger logger= LogManager.getLogger(ExcelUtility.class);
	private static final List<HashMap<String, String>> testData =new ArrayList<>();
	private static final String filepath=ConfigReader.getTestDataFile();
	private static final String sheetName=ConfigReader.getTestDataSheetName();



	public static HashMap<String,String> getData(int index){
		logger.info("Fetching test data at index: {}", index);
		return testData.get(index);
	}

	static {
		getTestData();
	}
	private static void getTestData() {
		logger.info("Reading test data from Excel file: {}, sheet: {}", filepath, sheetName);
		try {
			FileInputStream fs = new FileInputStream(filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Row HeaderRow = sheet.getRow(0);
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)
			{
				Row currentRow = sheet.getRow(i);
				HashMap<String, String> currentHash = new HashMap<>();
				for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++)
				{
					Cell currentCell = currentRow.getCell(j);
					switch (currentCell.getCellType())
					{
						case STRING:
							currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
							break;
					}
				}
				testData.add(currentHash);
				logger.debug("Row {} added: {}", i, currentHash);
			}
			logger.info("Successfully loaded {} rows of test data.", testData.size());
			fs.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
