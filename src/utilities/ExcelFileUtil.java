package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelFileUtil {
  XSSFWorkbook wb;
  // constructor for reading excel path
  public  ExcelFileUtil (String  Excelpath) throws Throwable
  {
	FileInputStream fi = new FileInputStream(Excelpath)  ;
	wb = new XSSFWorkbook(fi);
  }
  // method for count no of rows in a sheet
  public int rowCount(String SheetName)
  {
	  return wb.getSheet(SheetName).getLastRowNum();
  }
  // method for reading cell data
  public String getCellData(String SheetName,int row,int column)
  {
	String data ="";
	if(wb.getSheet(SheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) 
	{
		int celldata =(int) wb.getSheet(SheetName).getRow(row).getCell(column).getNumericCellValue();
		data = String.valueOf(celldata);	
	}
	else
	{
		data = wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
  }
  // method for write results
  public void setCellData(String SheetName,int row,int column,String status,String WriteExcel) throws Throwable
  {
	  // get sheet from wb
	  XSSFSheet ws = wb.getSheet(SheetName);
	  // get row from ws
	  XSSFRow  rowNum = ws.getRow(row);
	  // create cell
	  XSSFCell cell = rowNum.createCell(column);
	  // write status
	   cell.setCellValue(status);
	   if(status.equalsIgnoreCase("Pass"))
	   {
		   XSSFCellStyle style = wb.createCellStyle();
		   XSSFFont font = wb.createFont();
		   font.setColor(IndexedColors.GREEN.getIndex());
		   font.setBold(true);
		   font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		   style.setFont(font);
		   rowNum.getCell(column).setCellStyle(style);	   
	   }
	   else if(status.equalsIgnoreCase("Fail"))
	   {
		   XSSFCellStyle style = wb.createCellStyle();
		   XSSFFont font = wb.createFont();
		   font.setColor(IndexedColors.RED.getIndex());
		   font.setBold(true);
		   font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		   style.setFont(font);
		   rowNum.getCell(column).setCellStyle(style);	    
	   }
	   else if(status.equalsIgnoreCase("Blocked"))
	   {
		   
		   XSSFCellStyle style = wb.createCellStyle();
		   XSSFFont font = wb.createFont();
		   font.setColor(IndexedColors.BLUE.getIndex());
		   font.setBold(true);
		   font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		   style.setFont(font);
		   rowNum.getCell(column).setCellStyle(style);	  
	   }
		 FileOutputStream fo = new FileOutputStream(WriteExcel);
		 wb.write(fo);
	   }
  public static void main(String[] args) throws Throwable {
	ExcelFileUtil xl =  new ExcelFileUtil("D:/Sample.xlsx");
	// count no of rows
	int rc = xl.rowCount("Empdata");
	System.out.println(rc);
	for (int i=1;i<=rc;i++)
	{
		String fname = xl.getCellData("Empdata", i, 0);
		String mname = xl.getCellData("Empdata", i, 1);
		String lname = xl.getCellData("Empdata", i, 2);
		String eid = xl.getCellData("Empdata", i, 3);
		System.out.println(fname+" "+mname+" "+lname+" "+eid);
		xl.setCellData("Empdata", i, 4, "pass", "D:/Results.xlsx");
		//xl.setCellData("Empdata", i, 4, "Fail", "D:/Results.xlsx");
		//xl.setCellData("Empdata", i, 4, "Blocked", "D:/Results.xlsx");
	}
	}
}
  






















