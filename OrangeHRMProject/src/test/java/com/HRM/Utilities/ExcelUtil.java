package com.HRM.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	String filePath = "src\\test\\java\\resources\\LoginDataSet.xlsx";

	private File file;
	private FileInputStream fis;
	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	private XSSFRow rows;
	private XSSFCell cell;

	public ExcelUtil() {

		file = new File(filePath);

		try {
			fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("LoginData");

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
	public int totalRows() {
		return sheet.getPhysicalNumberOfRows();
	}
	
	public int totalColumns() {
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}
	
	public String getCellData(int rowNum, int colNum) {
		return sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
	}

}
