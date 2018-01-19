/**
 * 
 */
package com.cognizant.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cognizant.automation.bo.Record;

/**
 * @author 238209
 *
 */
public class ExcelFileReader {

	public static Logger logger = Logger.getLogger(ExcelFileReader.class);
	
	public static void main(String[] args) {
		readFileWithHeader("D:/238209/Technical/tmp/test_fc.xlsx");
	}
	
	public static List<Record> readFileWithHeader(String fileName) {
		List<Record> recordList = new ArrayList<Record>();
		Record record = null;
		Row headerRow = null;
		boolean isHeader = true;

		try {
			//Read the file
			FileInputStream file = new FileInputStream(new File(fileName));
						
			//Get the workbook instance for XLS file 
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Get iterator to all the rows in current sheet
			for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
				Row row = iterator.next();
				
				if (isHeader) {
					isHeader = false;
					headerRow = row;
					continue;
				}
				
				record = new Record();
				//Get iterator to all cells of current row
				for (Iterator<Cell> cellIter = row.iterator(); cellIter.hasNext();) {
					Cell cell = cellIter.next();

					int colIndex = cell.getColumnIndex();
					Cell headerCell = headerRow.getCell(colIndex);
					
					record.addAttribute(getCellData(headerCell), 
										getCellData(cell));
				}
				
				recordList.add(record);
				System.out.println(record.getAttributes());
			}
			
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return recordList;
	}
	
	public static String getCellData(Cell cell) {
        try {
            if(cell.getCellTypeEnum() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if(cell.getCellTypeEnum() == CellType.NUMERIC 
            		|| cell.getCellTypeEnum() == CellType.FORMULA) {
                String cellValue  = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                
                return cellValue;
            } else if(cell.getCellTypeEnum() == CellType.BLANK) {
                return "";
            } else {
                return String.valueOf(cell.getBooleanCellValue());
            }
        } catch(Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
