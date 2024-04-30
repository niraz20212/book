package org.example.bookmanagementsystem.utils;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.bookmanagementsystem.entity.BookTransaction;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import java.util.List;
import java.util.Map;

public class ExcelGenerator extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1. define your own excel file name
        response.addHeader("Content-Disposition", "attachment;filename=booktransaction.xls");

        //2. read data given by Controller
        @SuppressWarnings("unchecked")
        List<BookTransaction> list = (List<BookTransaction>) model.get("list");

        //3. create one sheet
        Sheet sheet = workbook.createSheet("BOOKTRANSACTION");

        //4. create row#0 as header
        setHead(sheet);

        //5. create row#1 onwards from List<T>
        setBody(sheet, list);
    }


    private void setHead(Sheet sheet) {
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("MEMBER NAME");
        row.createCell(2).setCellValue("BOOK");
        row.createCell(3).setCellValue("RENTED DATE");
    }

    private void setBody(Sheet sheet, List<BookTransaction> bookTransactionList) {
        int rowNum = 1;
        for (BookTransaction bookTransaction : bookTransactionList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(bookTransaction.getId());
            row.createCell(1).setCellValue(bookTransaction.getMember().getName());
            row.createCell(2).setCellValue(bookTransaction.getBook().getName());
            row.createCell(3).setCellValue(bookTransaction.getToDate());

        }
    }

}


