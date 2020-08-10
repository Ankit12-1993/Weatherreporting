package util;
//package com.snapdeal.qa.integration.utils;
//
//
//
//	import java.io.File;
//	import java.io.FileInputStream;
//	import java.io.FileNotFoundException;
//	import java.io.FileOutputStream;
//	import java.io.IOException;
//	import java.io.InputStream;
//	import java.sql.Timestamp;
//	import java.util.ArrayList;
//	import java.util.Date;
//	import java.util.List;
//
//	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//	import org.apache.poi.hssf.util.HSSFColor;
//	import org.apache.poi.ss.usermodel.Cell;
//	import org.apache.poi.ss.usermodel.CellStyle;
//	import org.apache.poi.ss.usermodel.Font;
//	import org.apache.poi.ss.usermodel.Row;
//	import org.apache.poi.ss.usermodel.Sheet;
//	import org.apache.poi.ss.usermodel.Workbook;
//	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//	public class ExcelUtil {
//
//		private static final String PATH = System.getProperty("user.dir");
//		private File file;
//		private String ext;
//		private Workbook workbook;
//		private Sheet sheet;
//		private Cell cell;
//		private Row row;
//		private int index;
//		private String fileName;
//		private int sheetIndex = -1;
//		private CellStyle cs;
//
//		/**
//		 * This constructor will give the object of ExcelUtil with loaded
//		 * configuration. You have to set the fileName and sheetIndex before
//		 * 
//		 * @throws NullPointerException
//		 * @throws IOException
//		 * @throws FileNotFoundException
//		 * @throws IllegalArgumentException
//		 */
//		public ExcelUtil() throws NullPointerException, IOException,
//				FileNotFoundException, IllegalArgumentException {
//			super();
//			if (sheetIndex == -1 || fileName == null) {
//				throw new NullPointerException();
//			}
//			index = sheetIndex;
//			file = new File(PATH + "//" + fileName);
//			int i = (PATH + "//" + fileName).lastIndexOf(".");
//			ext = (PATH + "//" + fileName).substring(i + 1);
//			InputStream is = new FileInputStream(file);
//			if (ext.equals("xlsx")) {
//				workbook = new XSSFWorkbook(is);
//			} else if (ext.equals("xls")) {
//				workbook = new HSSFWorkbook(is);
//			} else {
//				extracted();
//			}
//
//			sheet = workbook.getSheetAt(index);
//		}
//
//		/**
//		 * This constructor will take the file name and set the sheet index at 0.
//		 * 
//		 * @param fileName
//		 * @throws IOException
//		 * @throws FileNotFoundException
//		 * @throws IllegalArgumentException
//		 * @throws NullPointerException
//		 */
//		public ExcelUtil(String fileName) throws IOException,
//				FileNotFoundException, IllegalArgumentException,
//				NullPointerException {
//			super();
//			this.sheetIndex = 0;
//			if (sheetIndex == -1 || fileName == null) {
//				throw new NullPointerException();
//			}
//			index = sheetIndex;
//			file = new File(PATH + "//" + fileName);
//			int i = (PATH + "//" + fileName).lastIndexOf(".");
//			ext = (PATH + "//" + fileName).substring(i + 1);
//			InputStream is = new FileInputStream(file);
//			if (ext.equals("xlsx")) {
//				workbook = new XSSFWorkbook(is);
//			} else if (ext.equals("xls")) {
//				workbook = new HSSFWorkbook(is);
//			} else {
//				extracted();
//			}
//
//			sheet = workbook.getSheetAt(0);
//		}
//
//		/**
//		 * This constructor takes the name of the excel file the index of the sheet
//		 * at which we have to work.
//		 * 
//		 * @param fileName
//		 *            : Name of the excel file
//		 * @param sheetIndex
//		 *            : index of the workbook at which the sheet we are supposed to
//		 *            work.
//		 * @throws FileNotFoundException
//		 * @throws IOException
//		 * @throws IllegalArgumentException
//		 */
//		public ExcelUtil(String fileName, int sheetIndex)
//				throws FileNotFoundException, IOException,
//				IllegalArgumentException, NullPointerException {
//			super();
//			index = sheetIndex;
//			file = new File(PATH + "//" + fileName);
//			int i = (PATH + "//" + fileName).lastIndexOf(".");
//			ext = (PATH + "//" + fileName).substring(i + 1);
//			InputStream is = new FileInputStream(file);
//			if (ext.equals("xlsx")) {
//				workbook = new XSSFWorkbook(is);
//			} else if (ext.equals("xls")) {
//				workbook = new HSSFWorkbook(is);
//			} else {
//				extracted();
//			}
//			sheet = workbook.getSheetAt(index);
//
//		}
//
//		public String getValueForColumn(int row, String columnValue) {
//			boolean isnotnull = true;
//			String val = "";
//			String colValue="";
//			int col=0;
//			while (!colValue.equalsIgnoreCase(columnValue)) {
//				cell = sheet.getRow(0).getCell(col++);
//				try
//				{
//				colValue = cell.getStringCellValue();
//				}
//				catch(NullPointerException e)
//				{
//					isnotnull = false;
//					break;
//				}
//			}
//			--col;
//			try {
//				cell = sheet.getRow(row).getCell(col);
//			} catch (NullPointerException e) {
//				isnotnull = false;
//			}
//			if (isnotnull) {
//
//				if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//					Double doubleValue = cell.getNumericCellValue();
//					if ((doubleValue == Math.floor(doubleValue)) && !Double.isInfinite(doubleValue)) 
//					{
//					    int intValue = doubleValue.intValue();
//					    val = intValue+"";
//					}
//					else
//					{
//						val = cell.getNumericCellValue() + "";
//					}
//				} else if (cell != null
//						&& cell.getCellType() == Cell.CELL_TYPE_STRING) {
//					val = cell.getStringCellValue();
//				} 
//				 else if (cell != null
//							&& cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
//						if(cell.getBooleanCellValue())
//						{
//							val="TRUE";
//						}
//						else
//						{
//							val="FALSE";
//						}
//					} 
//				else if (cell == null) {
//					val = null;
//				}
//			}
//			else
//			{
//				return null;
//			}
//			return val;
//		}
//		
//		private void extracted() {
//			throw new IllegalArgumentException(
//					"Unable to process this file. Please check that you have provided a valid file name.");
//		}
//
//		public String getFileName() {
//			return fileName;
//		}
//
//		public void setFileName(String fileName) throws IOException,
//				IllegalArgumentException, NullPointerException {
//			this.fileName = fileName;
//			file = new File(PATH + "//" + this.fileName);
//			int i = (PATH + "//" + fileName).lastIndexOf(".");
//			ext = (PATH + "//" + this.fileName).substring(i + 1);
//			InputStream is = new FileInputStream(file);
//			if (ext.equals("xls")) {
//				workbook = new HSSFWorkbook(is);
//			} else {
//				extracted();
//			}
//		}
//
//		public int getSheetIndex() {
//			return sheetIndex;
//		}
//		
//		public Sheet getSheet()
//		{
//			return this.sheet;
//		}
//		
//		public Workbook getWorkBook()
//		{
//			return this.workbook;
//		}
//
//		public void setSheetindex(int sheetIndex) {
//			this.sheetIndex = sheetIndex;
//			sheet = workbook.getSheetAt(index);
//		}
//
//		
//		
//		public void createNewSheet(String keyword) {
//			this.sheet = workbook.createSheet(keyword);
//		}
//
//		public void createNewRow(int rownum) {
//			this.row = sheet.createRow(rownum);
//		}
//
//		public void createNewCell(int colnum) {
//			this.cell = row.createCell(colnum);
//		}
//		
//		public void setValue(int row, int col, String value)
//		{
//			this.row = sheet.getRow(row);
//			this.cell= sheet.getRow(row).getCell(col);
//	        if(this.cell==null)
//	        {
//	        	createNewCell(col);
//	        	this.cell.setCellValue(value); 
//	        }
//	        else
//	        {
//			switch (cell.getCellType()) 
//			{
//			case Cell.CELL_TYPE_BLANK:
//				cell.setCellValue(value);
//				break;
//				
//			case Cell.CELL_TYPE_STRING:
//				cell.setCellValue(value);
//			    break;
//
//			case Cell.CELL_TYPE_NUMERIC:
//				cell.setCellValue(Double.parseDouble(value));
//			    break;
//
//			case Cell.CELL_TYPE_BOOLEAN:
//				cell.setCellValue(Boolean.valueOf(value));
//			    break;
//
//			}
//	        }
//		}
//		
//		
//		public String getValue_New(int row, int col) throws Exception 
//		{
//			Sheet localSheet = this.sheet;
//			Row localRow = localSheet.getRow(row);
//			String contents = null;
//			if(localRow!=null)
//			{
//				Cell localCell = localRow.getCell(col);
//				if(localCell!=null)
//				{
//					if(localCell.getCellType()==Cell.CELL_TYPE_NUMERIC)
//					{
//						contents = ((Double)localCell.getNumericCellValue()).toString();
//					}
//					else if(localCell.getCellType()==Cell.CELL_TYPE_STRING)
//					{
//						contents = localCell.getStringCellValue();
//					}
//					else if(localCell.getCellType()==Cell.CELL_TYPE_FORMULA)
//					{
//						contents = localCell.getCellFormula();
//					}
//					else if(localCell.getCellType()==Cell.CELL_TYPE_BLANK)
//					{
//						contents = "";
//					}
//					else if(localCell.getCellType()==Cell.CELL_TYPE_BOOLEAN)
//					{
//						contents = ((Boolean)localCell.getBooleanCellValue()).toString();
//					}
//					else if(localCell.getCellType()==Cell.CELL_TYPE_ERROR)
//					{
//						contents = ((Byte)localCell.getErrorCellValue()).toString();
//					}
//					else
//						throw new Exception("Invalid Cell Type detected");
//					
//				}
//				else
//					contents = "";
//			}
//			else
//				contents="";
//			
//			return contents;
//			
//		}
//		
//		public String getValue(int row, int col) throws Exception 
//		{
//			/*boolean isnotnull = true;
//			String val = "";
//			try {
//				cell = sheet.getRow(row).getCell(col);
//			} catch (NullPointerException e) {
//				isnotnull = false;
//			}
//			if (isnotnull) {
//
//				if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//					Double doubleValue = cell.getNumericCellValue();
//					if ((doubleValue == Math.floor(doubleValue)) && !Double.isInfinite(doubleValue)) 
//					{
//					    int intValue = doubleValue.intValue();
//					    val = intValue+"";
//					}
//					else
//					{
//						val = cell.getNumericCellValue() + "";
//					}
//				} else if (cell != null
//						&& cell.getCellType() == Cell.CELL_TYPE_STRING) {
//					val = cell.getStringCellValue();
//				} else if (cell == null) {
//					val = null;
//				}
//			}
//			return val;*/
//			
//			Sheet localSheet = this.sheet;
//			Row localRow = localSheet.getRow(row);
//			String contents = null;
//			if(localRow!=null)
//			{
//				Cell localCell = localRow.getCell(col);
//				if(localCell!=null)
//				{
//					if(localCell.getCellType()==Cell.CELL_TYPE_NUMERIC)
//					{
//						contents = ((Double)localCell.getNumericCellValue()).toString();
//					}
//					else if(localCell.getCellType()==Cell.CELL_TYPE_STRING)
//					{
//						contents = localCell.getStringCellValue();
//					}
//					else if(localCell.getCellType()==Cell.CELL_TYPE_FORMULA)
//					{
//						contents = localCell.getCellFormula();
//					}
//					else if(localCell.getCellType()==Cell.CELL_TYPE_BLANK)
//					{
//						contents = "";
//					}
//					else if(localCell.getCellType()==Cell.CELL_TYPE_BOOLEAN)
//					{
//						contents = ((Boolean)localCell.getBooleanCellValue()).toString();
//					}
//					else if(localCell.getCellType()==Cell.CELL_TYPE_ERROR)
//					{
//						contents = ((Byte)localCell.getErrorCellValue()).toString();
//					}
//					else
//						throw new Exception("Invalid Cell Type detected");
//					
//				}
//				else
//					contents = "";
//			}
//			else
//				contents="";
//			
//			return contents;
//		}
//
//		public String getValue(int row) throws Exception {
//			return getValue(row, 0);
//		}
//
//		public int getColumnCount(int row) throws Exception {
//			int count = 0;
//			String val = "";
//			while (true) {
//				val = getValue(row, count);
//				if (val == null || val.equals("")) {
//					break;
//				} else {
//					count++;
//				}
//			}
//			return count;
//		}
//
//		public int getRowCount_old(int col) throws Exception {
//			int count = 0;
//			String val = "";
//			while (true) {
//
//				val = getValue(count, col);
//
//				if (val == null || val.equals("")) {
//					break;
//				} else {
//					count++;
//				}
//			}
//			return count;
//		}
//		
//		public int getRowCount(int col) {
//			return this.sheet.getLastRowNum()+1;
//		}
//
//		public int getColoumnCount() {
//			return this.sheet.getRow(0).getPhysicalNumberOfCells();
//		}
//
//		
//		public void setCellStyle() {
//			this.cs = workbook.createCellStyle();
//			Font f = workbook.createFont();
//			f.setBoldweight(Font.BOLDWEIGHT_BOLD);
//			cs.setFont(f);
//			cs.setFillBackgroundColor(HSSFColor.LIGHT_GREEN.index);
//
//			cell.setCellStyle(cs);
//
//		}
//		
//		
//		public List<String> getRowValueForTest(String testname) throws Exception {
//			List<String> vars = new ArrayList<String>();
//			int col = 0;
//			int count = 0;
//			String val = "";
//			while (true) {
//
//				val = getValue(count, col);
//				if (val == null || val.equals("")) {
//					break;
//				} else if (val.equalsIgnoreCase(testname)) {
//					col++;
//					while (true) {
//						val = getValue(count, col);
//						if (val == null || val.equals("")) {
//							break;
//						} else {
//							vars.add(val);
//							col++;
//						}
//					}
//					break;
//				}
//				count++;
//			}
//			return vars;
//		}
//
//		public int getColumnNoOf(int rowNo, String columnName) throws Exception
//		{
//			int colNo = 0;
//			while(true)
//			{
//				String val = getValue(rowNo, colNo);
//				if(val.equals("")||val==null)
//					break;
//				else
//				{
//					if(val.equalsIgnoreCase(columnName))
//						return colNo;
//					else
//						colNo++;
//				}
//			}
//			throw new Exception("Given column name is not present in given row");
//		}
//		
//		public void activateSheet(String sheetName) throws Exception
//		{
//			Sheet sheet1 = this.workbook.getSheet(sheetName);
//			if(sheet1!=null)
//				this.sheet = sheet1;
//			else
//				throw new Exception("Sheet Name not found");
//		}
//		
//		public void activateSheet(int sheetNumber) throws Exception
//		{
//			Sheet sheet1 = this.workbook.getSheetAt(sheetNumber);
//			if(sheet1!=null)
//				this.sheet = sheet1;
//			else
//				throw new Exception("Workbook doesn't contain that many sheets");
//		}
//		
//		public static void main(String[] args) throws Exception {
//			try {
//				ExcelUtil util = new ExcelUtil(
//						"//src//test//resources//ProductsUnderNonBookCategory//BulkUploadContentSheetIndexerNonBooks.xlsx",
//						0);
//				int row = 0;
//				int col=0;
//				String sku = "";
//				Cell cell = null;
//				Sheet sheet = util.getSheet();
//				while (!sku.equals("SKU")) 
//				{
//					cell = sheet.getRow(row).getCell(++col);
//					sku = cell.getStringCellValue();
//				}
//				int rows= util.getRowCount(col);
//				for(int i=1; i<rows;i++)
//				{
//					String oldsupc= util.getValue(i, col);
//					oldsupc= oldsupc.substring(0,oldsupc.indexOf(":")+1);
//					Date date = new java.util.Date();
//					Timestamp timestamp = new Timestamp(date.getTime());
//					String time = timestamp.toString();
//					String newsupc= oldsupc+time;
//					util.setValue(i, col, newsupc);
//				}
//				FileOutputStream outFile =new FileOutputStream(PATH+
//						"//src//test//resources//ProductsUnderNonBookCategory//BulkUploadContentSheetIndexerNonBooks.xlsx");
//		        util.getWorkBook().write(outFile);
//		            outFile.close();
//				
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		public List<String> getAllValues(int rownum) throws Exception 
//		{
//			row = sheet.getRow(rownum);
//			List<String> fields = new ArrayList<String>();
//			int start = row.getFirstCellNum();
//			int end = row.getLastCellNum();
//			for(int i=start;i<=end;i++)
//			{
//				String fieldName = getValue(rownum, i).trim();
//				if(!fieldName.equalsIgnoreCase(""))
//					fields.add(fieldName);
//			}
//			
//			return fields;
//		}
//		
//		
//
//	}
//

