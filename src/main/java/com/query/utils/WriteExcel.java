package com.query.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

/**
 * Created by exphuhong on 17-10-16.
 * Start
 */
public class WriteExcel {

    public static void emailStatusWriteToExcel(List<EmailRow> emailRowList, String path) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = null;
        File file = new File(path);
        if (!file.exists()) {
            sheet = workbook.createSheet("sheet1");
        }else {
            int size = workbook.getNumberOfSheets();
            System.out.println(size);
            sheet = workbook.createSheet("sheet"+size);
            sheet = workbook.getSheetAt(size);

        }
        for (int i = 0; i < emailRowList.size() ; i++) {
            EmailRow e = emailRowList.get(i);
            Row row = sheet.createRow(i);
            Cell emailCell = row.createCell(0);
            Cell numCell = row.createCell(1);
            Cell statusCell = row.createCell(2);
            emailCell.setCellValue(e.getEmail());
            numCell.setCellValue(e.getResultCount());
            System.out.println(emailRowList.get(i).getStatus());
            if (emailRowList.get(i).getStatus().equals("异常")) {
                XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.GOLD.getIndex());
                style.setFillPattern(FillPatternType.forInt(1));
                emailCell.setCellStyle(style);
                numCell.setCellStyle(style);
                statusCell.setCellStyle(style);
                statusCell.setCellValue(String.valueOf(EmailStatus.异常));
            } else {
                statusCell.setCellValue(String.valueOf(EmailStatus.正常));
            }
        }
        OutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
