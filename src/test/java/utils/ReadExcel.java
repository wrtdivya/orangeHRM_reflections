package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

     public static String[][] readData(String filename) throws Exception {

        XSSFWorkbook wb = new XSSFWorkbook("data/"+filename+".xlsx");
        XSSFSheet sheet = wb.getSheetAt(0);

        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        String[][] data = new String[rowCount][colCount];
//C:\TestLeaf\SeleniumWeekEndCR\pom_constructor_excelddt\pageobjectmodel_constructor\data\data.xlsx
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }

        wb.close();
        return data;
    }

}
