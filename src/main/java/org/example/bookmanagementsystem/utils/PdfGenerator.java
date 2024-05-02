package org.example.bookmanagementsystem.utils;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.bookmanagementsystem.entity.BookTransaction;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PdfGenerator extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //download PDF with a given filename
        response.addHeader("Content-Disposition", "attachment;filename=booktransaction.pdf");

        //read data from controller
        @SuppressWarnings("unchecked")
        List<BookTransaction> list = (List<BookTransaction>) model.get("list");

        //create element
        //Font (Family, Size, Style, Color)
        Font titleFont = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, Color.RED);
        Paragraph title = new Paragraph("BOOKTRANSACTION DATA", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingBefore(20.0f);
        title.setSpacingAfter(25.0f);
        //add to document
        document.add(title);

        Font tableHead = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.ORANGE);
        PdfPTable table = new PdfPTable(4);// no.of columns
        table.addCell(new Phrase("ID", tableHead));
        table.addCell(new Phrase("MEMBER NAME", tableHead));
        table.addCell(new Phrase("BOOK", tableHead));
        table.addCell(new Phrase("RENTED DATE", tableHead));


        for (BookTransaction bookTransaction : list) {
            table.addCell(String.valueOf(bookTransaction.getId()));
            table.addCell(bookTransaction.getMember().getName());
            table.addCell(bookTransaction.getBook().getName());
            table.addCell(String.valueOf(bookTransaction.getToDate()));

        }
        //add to document
        document.add(table);

    }


}
