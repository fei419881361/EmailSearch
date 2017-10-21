package com.query.utils;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {
    private static List<EmailRow> emailRows = null;

    public static List<EmailRow> readExcel(String path) throws IOException, InvalidFormatException {
        System.out.println("path====="+path);
        emailRows = new ArrayList<EmailRow>();
        InputStream inputStream = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = getCellType(cell);
                if (cellValue.trim().equals("") || !ValidateEmail.EmailFormat(cellValue)) {
                    EmailRow emailRow = new EmailRow(cellValue);
                    emailRow.setEmail(cellValue);
                    emailRow.setStatus("-1");
                    emailRows.add(emailRow);
                } else {
                    EmailRow emailRow = new EmailRow(cellValue);
                    emailRow.setEmail(cellValue);
                    emailRows.add(emailRow);
                }
            }
        }
        File file = new File(path);
        if (file.exists())
            file.delete();
        return emailRows;
    }
//    @Test
//    public void test(){
//        List<EmailRow> test2 = new ArrayList<EmailRow>();
//        try {
//            test2 = readExcel("d:\\files\\test.xlsx");
//            System.out.println(test2.toArray().toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }
//
//    }

    private static String getCellType(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();
            case BLANK:
                return "";
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return String.valueOf(cell.getCellFormula());
            case ERROR:
                return "非法值";
            default:
                return "未知类型";
        }
    }
}