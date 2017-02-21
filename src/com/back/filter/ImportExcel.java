package com.back.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.back.po.Admins;

public class ImportExcel {
	//设置cell编码解决中文高位字节截断
	static short XLS_ENCODING=HSSFCell.ENCODING_UTF_16;
	//设置浮点数格式
	static String NUMBER_FORMAT="#,##0.00";
	//指定日期格式
	static String DATE_FORMAT="m/d/yy";
	FileInputStream fis=null;
	File file=null;	
	//创建excel文件对象
	HSSFWorkbook wb=null;
	//创建excel内sheet对象
	HSSFSheet sheet=null;
	//创建行对象
	HSSFRow row=null;
	int sheetNum=0;
	int rowRum=0;
	public static short getXLS_ENCODING() {
		return XLS_ENCODING;
	}
	public static void setXLS_ENCODING(short xLS_ENCODING) {
		XLS_ENCODING = xLS_ENCODING;
	}
	public static String getNUMBER_FORMAT() {
		return NUMBER_FORMAT;
	}
	public static void setNUMBER_FORMAT(String nUMBER_FORMAT) {
		NUMBER_FORMAT = nUMBER_FORMAT;
	}
	public static String getDATE_FORMAT() {
		return DATE_FORMAT;
	}
	public static void setDATE_FORMAT(String dATE_FORMAT) {
		DATE_FORMAT = dATE_FORMAT;
	}
	public FileInputStream getFis() {
		return fis;
	}
	public void setFis(FileInputStream fis) {
		this.fis = fis;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public HSSFWorkbook getWb() {
		return wb;
	}
	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}
	public HSSFSheet getSheet() {
		return sheet;
	}
	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}
	public HSSFRow getRow() {
		return row;
	}
	public void setRow(HSSFRow row) {
		this.row = row;
	}
	public int getSheetNum() {
		return sheetNum;
	}
	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}
	public int getRowRum() {
		return rowRum;
	}
	public void setRowRum(int rowRum) {
		this.rowRum = rowRum;
	}
	//构造方法
	public ImportExcel(){
		
	}
	public ImportExcel(File file){
		this.file=file;
	}
	//读取excel 获取hssfworkbook对象
	public void open() throws IOException{
		fis=new FileInputStream(file);
		wb=new HSSFWorkbook(new POIFSFileSystem(fis));
		fis.close();
	}
	//返回sheet表数目
	public int getSheetCount(){
		int sheetCount=-1;
		//获取一个excel表中的sheet数量
		sheetCount=wb.getNumberOfSheets();
		return sheetCount;
	}
	//sheetNum下的记录行数
	public int getRowCount(){
		if(wb==null){
			System.out.println("excel 文件不存在!");
		}
		HSSFSheet sheet=wb.getSheetAt(this.sheetNum);
		int rowCount=-1;
		rowCount=sheet.getLastRowNum();		
		return rowCount;
	}
	//读取指定sheetNum的rowCount
	public int getRowCount(int sheetNum){
		HSSFSheet sheet1=wb.getSheetAt(sheetNum);
		int rowCount=-1;
		rowCount=sheet1.getLastRowNum();		
		return rowCount;
	}
	//得到指定行的内容
	public String[] readExcelLine(int lineNum){
		return readExcelLine(this.sheetNum,lineNum);
	}
	//指定工作表和行数的内容
	public String[] readExcelLine(int sheetNum,int lineNum){
		if(sheetNum<0||lineNum<0){
			return null;
		}
		String[] strExcelLine=null;
		try {
			sheet=wb.getSheetAt(sheetNum);
			row=sheet.getRow(lineNum);
			int cellCount=row.getLastCellNum();
			strExcelLine=new String[cellCount+1];
			for (int i = 0; i <= cellCount; i++) {
				strExcelLine[i]=readStringExcelCell(lineNum,i);			
			}
		} catch (Exception e) {			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return strExcelLine;	
	}
	//读取指定列内容
	private String readStringExcelCell(int cellNum) {
		return readStringExcelCell(this.rowRum,cellNum);
	}
	//指定行和列编号的内容
	private String readStringExcelCell(int rowNum, int cellNum) {
		// TODO Auto-generated method stub
		return readStringExcelCell(this.sheetNum,rowNum,cellNum);
	}	
	//指定工作表、行、列下的内容
	@SuppressWarnings("deprecation")
	private String readStringExcelCell(int sheetNum,int rowNum, int cellNum) {
		if(sheetNum<0||rowNum<0){
			return "";
		}
		String strExcelCell="";
		try{
			sheet=wb.getSheetAt(sheetNum);
			row=sheet.getRow(rowNum);
			if(row.getCell((short)cellNum)!=null){
				switch (row.getCell((short)cellNum).getCellType()) {
				case HSSFCell.CELL_TYPE_FORMULA:
					strExcelCell="FORMULA";
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:{
					strExcelCell=String.valueOf(row.getCell((short)cellNum).getNumericCellValue());
				}					
					break;
				case HSSFCell.CELL_TYPE_STRING:
					strExcelCell=row.getCell((short)cellNum).getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_BLANK:
					strExcelCell="";
					break;
				default:
					strExcelCell="";
					break;
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return strExcelCell;
	}
	//转换为list集合
	public List<Admins> ImportAdmins()throws IOException{
		List<Admins> lisAdmins=new ArrayList<Admins>();
		Admins admin=null;
		try {
			fis=new FileInputStream(file);
			wb=new HSSFWorkbook(new POIFSFileSystem(fis));
			fis.close();
			this.setSheetNum(0);
			int count=this.getRowCount();
			for (int i = 0; i <=count; i++) {
				String[] rows=this.readExcelLine(i);
				if(i>0){
					admin=new Admins();
					for (int j = 0; j < rows.length; j++) {
						//判断单元格不为空
						if(rows[j].trim().length()!=0&&!rows[j].trim().equals(null)&&!rows[j].trim().equals("")){
							switch (j) {
							case 0:
                                System.out.println("#权限 "+rows[j]);
                                admin.setPname(rows[j].trim().toString());
								break;
							case 1:
                                System.out.println("#姓名 "+rows[j]);
                                admin.setAname(rows[j].trim().toString());
								break;
							case 2:
                                System.out.println("#密码 "+rows[j]);
                                admin.setApwd(rows[j].trim().toString());
								break;
							case 3:
                                System.out.println("#真实姓名 "+rows[j]);
                                admin.setArealname(rows[j].trim().toString());
								break;
							case 4:
                                System.out.println("#性别 "+rows[j]);
                                admin.setAsex(rows[j].trim().toString());
								break;
							case 5:
                                System.out.println("#备注 "+rows[j]);
                                admin.setAremarks(rows[j].trim().toString());
								break;
							}
						}
						System.out.println(rows[j]+"\t");
					}
					System.out.println();
					lisAdmins.add(admin);
				}else{
					System.out.println("第一行标题");
				}
			}
			return lisAdmins;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
