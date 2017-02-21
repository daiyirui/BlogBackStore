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
	//����cell���������ĸ�λ�ֽڽض�
	static short XLS_ENCODING=HSSFCell.ENCODING_UTF_16;
	//���ø�������ʽ
	static String NUMBER_FORMAT="#,##0.00";
	//ָ�����ڸ�ʽ
	static String DATE_FORMAT="m/d/yy";
	FileInputStream fis=null;
	File file=null;	
	//����excel�ļ�����
	HSSFWorkbook wb=null;
	//����excel��sheet����
	HSSFSheet sheet=null;
	//�����ж���
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
	//���췽��
	public ImportExcel(){
		
	}
	public ImportExcel(File file){
		this.file=file;
	}
	//��ȡexcel ��ȡhssfworkbook����
	public void open() throws IOException{
		fis=new FileInputStream(file);
		wb=new HSSFWorkbook(new POIFSFileSystem(fis));
		fis.close();
	}
	//����sheet����Ŀ
	public int getSheetCount(){
		int sheetCount=-1;
		//��ȡһ��excel���е�sheet����
		sheetCount=wb.getNumberOfSheets();
		return sheetCount;
	}
	//sheetNum�µļ�¼����
	public int getRowCount(){
		if(wb==null){
			System.out.println("excel �ļ�������!");
		}
		HSSFSheet sheet=wb.getSheetAt(this.sheetNum);
		int rowCount=-1;
		rowCount=sheet.getLastRowNum();		
		return rowCount;
	}
	//��ȡָ��sheetNum��rowCount
	public int getRowCount(int sheetNum){
		HSSFSheet sheet1=wb.getSheetAt(sheetNum);
		int rowCount=-1;
		rowCount=sheet1.getLastRowNum();		
		return rowCount;
	}
	//�õ�ָ���е�����
	public String[] readExcelLine(int lineNum){
		return readExcelLine(this.sheetNum,lineNum);
	}
	//ָ�������������������
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
	//��ȡָ��������
	private String readStringExcelCell(int cellNum) {
		return readStringExcelCell(this.rowRum,cellNum);
	}
	//ָ���к��б�ŵ�����
	private String readStringExcelCell(int rowNum, int cellNum) {
		// TODO Auto-generated method stub
		return readStringExcelCell(this.sheetNum,rowNum,cellNum);
	}	
	//ָ���������С����µ�����
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
	//ת��Ϊlist����
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
						//�жϵ�Ԫ��Ϊ��
						if(rows[j].trim().length()!=0&&!rows[j].trim().equals(null)&&!rows[j].trim().equals("")){
							switch (j) {
							case 0:
                                System.out.println("#Ȩ�� "+rows[j]);
                                admin.setPname(rows[j].trim().toString());
								break;
							case 1:
                                System.out.println("#���� "+rows[j]);
                                admin.setAname(rows[j].trim().toString());
								break;
							case 2:
                                System.out.println("#���� "+rows[j]);
                                admin.setApwd(rows[j].trim().toString());
								break;
							case 3:
                                System.out.println("#��ʵ���� "+rows[j]);
                                admin.setArealname(rows[j].trim().toString());
								break;
							case 4:
                                System.out.println("#�Ա� "+rows[j]);
                                admin.setAsex(rows[j].trim().toString());
								break;
							case 5:
                                System.out.println("#��ע "+rows[j]);
                                admin.setAremarks(rows[j].trim().toString());
								break;
							}
						}
						System.out.println(rows[j]+"\t");
					}
					System.out.println();
					lisAdmins.add(admin);
				}else{
					System.out.println("��һ�б���");
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
