package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	static String file;
	static Workbook workbook;

	public static void load(String file) {
		if (new File(file).exists()) {
			try (FileInputStream fis = new FileInputStream(file)) {
				workbook = WorkbookFactory.create(fis);
				ExcelUtil.file = file;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}else{
			System.out.println("Excel file is not present - "+file);
		}
	}

	public static Sheet getSheet(String name) {
		return workbook.getSheet(name);
	}

	public static void save() {
		try (FileOutputStream fout = new FileOutputStream(file)) {
			workbook.write(fout);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}