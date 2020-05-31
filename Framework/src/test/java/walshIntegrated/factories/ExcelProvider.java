package walshIntegrated.factories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelProvider {
	
	XSSFWorkbook wb;
	
	public ExcelProvider()
	{
		try {
			wb = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir") + "/TestData/logins_testdata.xlsx")));
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}

	}

	
	public int getRowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getPhysicalNumberOfRows();
	}
	
	public int getColumnCount(String sheetName)
	{
		return wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
	}
	
	public int getColumnCount(String sheetName,int row)
	{
		return wb.getSheet(sheetName).getRow(row).getPhysicalNumberOfCells();
	}
	
	
	public  String getCellData(String sheetName,int row,int column)
	{
		XSSFCell cell=wb.getSheet(sheetName).getRow(row).getCell(column);
		
		String data=null;
		
		if(cell.getCellType()==CellType.STRING)
		{
			data=cell.getStringCellValue();
		}
		else if(cell.getCellType()==CellType.BOOLEAN)
		{
			//data=cell.getBooleanCellValue();
		}
		else if(cell.getCellType()==CellType.NUMERIC)
		{
			data=String.valueOf((int)cell.getNumericCellValue());
		}
		else if(cell.getCellType()==CellType.BLANK)
		{
			data="";
		}
		return data;
	}
	
	

}
