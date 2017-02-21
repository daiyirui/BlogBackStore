package com.back.filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.back.po.Admins;
@SuppressWarnings("deprecation")
public class ExceptExcel {
   //����cell���������ĸ�λ�ֽڽض�
	static short XLS_ENCODING=HSSFCell.ENCODING_UTF_16;
	//���ø�������ʽ
	static String NUMBER_FORMAT="#,##0.00";
	//ָ�����ڸ�ʽ
	static String DATE_FORMAT="m/d/yy";
	OutputStream out=null;
	//����excel�ļ�����
	HSSFWorkbook workbook=null;
	//����excel��sheet����
	HSSFSheet sheet=null;
	//�����ж���
	HSSFRow row=null;
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
	public OutputStream getOut() {
		return out;
	}
	public void setOut(OutputStream out) {
		this.out = out;
	}
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
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
	//���췽��
	public ExceptExcel(){
		
	}
	//��ʼ��excel
	public ExceptExcel(OutputStream out){
		this.out=out;
		this.workbook=new HSSFWorkbook();
		this.sheet=workbook.createSheet();
	}
	//����excel
	public void export() throws FileNotFoundException ,IOException{
		workbook.write(out);
		out.flush();
		out.close();
	}
	//���һ��
	public void createRow(int index){
		this.row=this.sheet.createRow(index);
	}
	//��ȡ��Ԫ���ֵvalue
	
	public String getCell(int index){
		HSSFCell cell=this.row.getCell((short)index);
		String strExcelCell="";
		if(cell!=null){
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_FORMULA:
				strExcelCell="FORMULA";
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:{
			    strExcelCell=String.valueOf(cell.getNumericCellValue());	
			}				
				break;
			case HSSFCell.CELL_TYPE_STRING:
				strExcelCell=cell.getStringCellValue();
				break;	
			case HSSFCell.CELL_TYPE_BLANK:
				strExcelCell="";
				break;			
			default:
				strExcelCell="";
				break;
			}			
		}
		return strExcelCell;
	}
	//���õ�Ԫ�� index���value;��Ԫ�����ֵ
	public void setCell(int index,int value){
		HSSFCell cell=this.row.createCell((short)index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}
	public void setCell(int index,double value){
		HSSFCell cell=this.row.createCell((short)index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		//������cell��ʽ
		HSSFCellStyle cellStyle=workbook.createCellStyle();
		HSSFDataFormat format=workbook.createDataFormat();
		//����cell��ʽΪ���Ƶĸ�������ʽ
		cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT));
		//���ø�cell����������ʾ��ʽ
		cell.setCellStyle(cellStyle);
	}
	public void setCell(int index,String value){
		HSSFCell cell=this.row.createCell((short)index);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setEncoding(XLS_ENCODING);
		cell.setCellValue(value);
	}
	public void setCell(int index,Calendar value){
		HSSFCell cell=this.row.createCell((short)index);
		cell.setEncoding(XLS_ENCODING);
		cell.setCellValue(value.getTime());
		HSSFCellStyle cellStyle=workbook.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DATE_FORMAT));
		cell.setCellStyle(cellStyle);
	}
	//����admins����
	public boolean ExceptAdmin(List<Admins> lstAdmins){
		System.out.println("��ʼ����excel������");
		//Tomcat������Ŀ�����ϴ�excel��·��
		File f=new File("F:\\Java����\\apache-tomcat-7.0.5\\webapps\\back\\upload\\excel\\exceptadmin.xls");
		//�����excel�ļ�������ɾ��
		if(!f.exists()){
			f.delete();			
		}
		ExceptExcel e=null;
		try {
			e=new ExceptExcel(new FileOutputStream(f));
			//�����һ�б���
			e.createRow(0);
			e.setCell(0, "ID");
			e.setCell(1, "Ȩ��");
			e.setCell(2, "����");
			e.setCell(3, "����");
			e.setCell(4, "ע��ʱ��");
			e.setCell(5, "��ʵ����");
			e.setCell(6, "�Ա�");
			e.setCell(7, "��ע");
			//��װ����
			Admins admin=null;
			for (int i = 1; i <= lstAdmins.size(); i++) {
				admin=new Admins();
				admin=lstAdmins.get(i-1);
				e.createRow(i);
				e.setCell(0, admin.getAid());
				e.setCell(1, admin.getPermission().getPname());
				e.setCell(2, admin.getAname());
				e.setCell(3, admin.getApwd());
				e.setCell(4, admin.getAdate());
				e.setCell(5, admin.getArealname());
				e.setCell(6, admin.getAsex());
				e.setCell(7, admin.getAremarks());
			}
			e.export();
			System.out.println("Excel �����ɹ���");
			return true;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			 return false;	
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return false;	
		}
	   
	}
}
