package utils;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	static Workbook workbook;
	static Sheet sheet;
	static final String filePath = "testData/TestData.xlsx";
	//public static final String SHEET = "Ease My Trip";
	//===============================================
	// Load Excel
	//===============================================
	
	
	public static void loadSheet(String sheetName) {
		try {
			FileInputStream file = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
		}catch (Exception e) {
			throw new RuntimeException("Error loading Excel file", e);
		}
				
	}
	     //===============================================
		// Get Cell Data
		//===============================================
		
	public static String getCellData( String sheetName, int rowNum, int colNum) {
		loadSheet(sheetName);
		
		Row row = sheet.getRow(rowNum);
		if(row == null) return "";
		Cell cell = row.getCell(colNum);
		if (cell  == null ) return "";
		
		switch(cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf((int)cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		default:
			return "";
		}
	}
	//===============================================
	// Write Data
	//===============================================
		public static void setCellData(String sheetName, int rowNum, int colNum,String value) {
			try {
				loadSheet(sheetName);
				
				Row row = sheet.getRow(rowNum);
				if(row == null) row = sheet.createRow(rowNum);
				
				Cell cell = row.getCell(colNum);
				if (cell  == null ) cell = row.createCell(colNum);
				
				cell.setCellValue(value);
				
				FileOutputStream fos = new FileOutputStream(filePath);
				workbook.write(fos);
				fos.close();
			}catch(IOException e) {
				throw new RuntimeException("Error writing to Excel file",e);
			}
		}
		//===============================================
		// Get Row Count
		//===============================================
		
		public static int getRowCount(String sheetName) {
			loadSheet(sheetName);
			return sheet.getLastRowNum();
		}
		//===============================================
		// Find Row by TC_ID 
		//===============================================
		
		public static int getRowByTestCase(String sheetName, String testCaseID) {
			loadSheet(sheetName);
			
			for(int i =1; i<=sheet.getLastRowNum();i++) {
				String tc = sheet.getRow(i).getCell(0).getStringCellValue();
				
				if(tc.equalsIgnoreCase(testCaseID)) {
					return i;
				}
			}
			return -1;
		}
}

