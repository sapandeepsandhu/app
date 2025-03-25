package com.example.fontextractor;

import java.io.File;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

public class InlineStringProcessor {
    public static String processInlineStrings(String filePath) {
        StringBuilder result = new StringBuilder("\n=== Processing Inline Strings ===\n");

        try (XSSFWorkbook workbook = new XSSFWorkbook(new File(filePath))) {
            for (Sheet sheet : workbook) {
                result.append("Sheet: ").append(sheet.getSheetName()).append("\n");
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        result.append("Cell: ").append(cell.getAddress().formatAsString())
                              .append(" - Value: ").append(cell.toString()).append("\n");
                    }
                }
            }
        } catch (Exception e) {
            result.append("Error processing inline strings: ").append(e.getMessage());
        }

        return result.toString();
    }
}
