package Org.Utilities.DataReader;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelDataReader {
    /*public static Object[][] getDataFromExcel() throws IOException {
        DataFormatter formatter = new DataFormatter();
        FileInputStream fis = new FileInputStream("C://Users//santa//IdeaProjects//Santanu_OrangeHRM//src//test//java//Org//data//RegistrationData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows(); //to get how many rows present in a sheet
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum(); //To get how many column present in a sheet using counting no of cells in a particular row
        Object[][] data = new Object[rowCount-1][colCount];
        for(int i=0;i<rowCount-1;i++) {
            row=sheet.getRow(i+1);
            for(int j=0;j<colCount;j++) {
                XSSFCell cell = row.getCell(j);
                data[i][j] = formatter.formatCellValue(cell);
            }
        }
        return data;
    }*/

    public static synchronized List<HashMap<String,String>> getDataFromExcel(String filePath, String sheetName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        HashMap<String,String> data = new HashMap<>();
        List<HashMap<String,String>> dataMapList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                int rowCount = sheet.getPhysicalNumberOfRows();
                XSSFRow row = sheet.getRow(0);
                int colCount = row.getLastCellNum();
                for(int j=0;j<rowCount-1;j++) {
                    row=sheet.getRow(j+1);
                    /*HashMap<String,String> data = new HashMap<>();*/
                    for(int k=0;k<colCount;k++) {
                        XSSFCell cellValue = row.getCell(k);
                        XSSFCell cellKey = sheet.getRow(0).getCell(k);
                        data.put(formatter.formatCellValue(cellKey),formatter.formatCellValue(cellValue));
                    }
                    dataMapList.add(new HashMap<>(data));
                    data.clear();
                }
            }
        }
        return dataMapList;
    }
}
