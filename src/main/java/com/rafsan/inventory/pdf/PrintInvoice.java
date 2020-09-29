package com.rafsan.inventory.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.rafsan.inventory.entity.Item;

import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Date;

import com.rafsan.inventory.model.EmployeeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class PrintInvoice {

    private final ObservableList<Item> items;
    private final String barcode;

    public PrintInvoice(ObservableList<Item> items, String barcode) {
        this.items = FXCollections.observableArrayList(items);
        this.barcode = barcode;
    }

    public void generateReport() {

        try {
            Document document = new Document();
            FileOutputStream fs = new FileOutputStream("Report.pdf");
            PdfWriter writer = PdfWriter.getInstance(document, fs);
            document.open();

            Paragraph paragraph = new Paragraph("Product ID");
            document.add(paragraph);
            addEmptyLine(paragraph, 5);

            PdfContentByte cb = writer.getDirectContent();
            BarcodeEAN codeEAN = new BarcodeEAN();
            codeEAN.setCodeType(codeEAN.EAN13);
            codeEAN.setCode(barcode);
            document.add(codeEAN.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.DARK_GRAY));
            addEmptyLine(paragraph, 5);

            PdfPTable table = createTable();
            document.add(table);

            document.close();
        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private PdfPTable createTable() {

        PdfPTable table = new PdfPTable(4);
        PdfPCell c1 = new PdfPCell(new Phrase("Item"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantity"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (Item i : items) {
            table.addCell(i.getItemName());
            table.addCell(String.valueOf(i.getUnitPrice()));
            table.addCell(String.valueOf(i.getQuantity()));
            table.addCell(String.valueOf(i.getTotal()));
        }

        return table;
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
//public String getArray() {
//    String array = "";
//    for (Item l : items) {
//        double tva=l.getTotal()*0.2;
//        double tt=l.getTotal();
//        array += "					<tr>\r\n"
//                + "					  <td style=\"padding: 0.75rem;vertical-align: top; border: 1px solid #dee2e6;\">"
//                + l.getItemName() + "</td>\r\n"
//                + "					  <td style=\"padding: 0.75rem;vertical-align: top; border: 1px solid #dee2e6;\">"
//                + new DecimalFormat("##.##").format(l.getUnitPrice()) + "</td>\r\n"
//                + "					  <td style=\"padding: 0.75rem;vertical-align: top; border: 1px solid #dee2e6;\">"
//                + l.getQuantity() + "</td>\r\n"
//                + "					  <td style=\"padding: 0.75rem;vertical-align: top; border: 1px solid #dee2e6;\">20%</td>\r\n"
//                + "					  <td style=\"padding: 0.75rem;vertical-align: top; border: 1px solid #dee2e6;\">"
//                + new DecimalFormat("##.##").format(tva) + "</td>\r\n"
//                + "					  <td style=\"padding: 0.75rem;vertical-align: top; border: 1px solid #dee2e6;\">"
//                + new DecimalFormat("##.##").format(tt)  + "</td>\r\n" + "					</tr>\r\n";
//    }
//    return array;
//}


//    public void generateBellIntoPdf() {
//        String tr = this.getArray();
//        try {
//            Document document = new Document(PageSize.LETTER);
//            PdfWriter pdfWriter = PdfWriter.getInstance(document,
//                    new FileOutputStream("Facture.pdf"));
//            document.open();
//            document.addAuthor("Yassir BOURAS");
//            document.addCreationDate();
//            double tttt=0;
//            for (Item l:items){
//               tttt=l.getTotal()+tttt;
//            }
//
//            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
//            String ht = new DecimalFormat("##.##").format(0.8*tttt);
//            String tva = new DecimalFormat("##.##").format(tttt*0.2);
//            String total = new DecimalFormat("##.##").format(tttt);
//
//            String str = "<html>\r\n" + "	<head>\r\n" + "		<style>\r\n"
//                    + "			#Global #gauche {\r\n" + "				float:left;\r\n"
//                    + "				width:60%;\r\n" + "			}\r\n"
//                    + "			#Global #droite {\r\n" + "				margin-left:60%;   \r\n"
//                    + "			}\r\n" + "			#Global #middle {\r\n"
//                    + "				float:left;\r\n" + "				margin-left:50%;\r\n"
//                    + "			}\r\n" + "		</style>\r\n" + "	</head>\r\n"
//                    + "	<body style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';\"> \r\n"
//                    + "	<div  id=\"contentToConvert\" > \r\n"
//                    + "		<div class=\"contentToConvert\" id=\"contentToConvert\" >\r\n"
//                    + "			<h1  style=\"text-align: center;margin-top: 0;margin-bottom: 0.5rem;color:red;\">Facture "
//                     + "</h1>\r\n" + "			<b>Societe X SARL.</b>\r\n"
//                    + "			<p style=\"margin-top: 0;margin-bottom: 1rem;\">Hay Salam N 26</p>\r\n"
//                    + "			<p style=\"margin-top: 0;margin-bottom: 1rem;\">Maroc 80000</p>\r\n"
//                    + "			<p style=\"margin-top: 0;margin-bottom: 1rem;\">0637834832</p>\r\n"
//                    + "			<p style=\"margin-top: 0;margin-bottom: 1rem;\">www.yourstore.com</p>\r\n"
//                    + "			<table style=\"    width: 100%;\">\r\n"
//                    + "				<tr>\r\n"
//                    + "					<td style=\"width:80%;\"></td>\r\n"
//                    + "					<td><p style=\"margin-top: 0;margin-bottom: 1rem;color:blue;font-weight: bold;\">" + new EmployeeModel().getEmployee(0).getUserName().toUpperCase() +  "</p></td>\r\n"
//                    + "				</tr>\r\n" + "				<tr>\r\n"
//                    + "					<td style=\"width:80%;\">\r\n"
//                    + "						<p style=\"margin-top: 0;margin-bottom: 1rem;\">Reference : "
//                    + "1020515" + "</p>\r\n"
//                    + "						<p style=\"margin-top: 0;margin-bottom: 1rem;c\">Date      : "
//                    + new Date().getDate() + "</p>\r\n"
//                    + "						<p style=\"margin-top: 0;margin-bottom: 1rem;\"></p>\r\n" + "					</td>\r\n"
//                    + "					<td><p style=\"margin-top: 0;margin-bottom: 1rem;\">"
//                    + "Berlin" + "</p>\r\n"
//                    + "					<p style=\"margin-top: 0;margin-bottom: 1rem;\">Germany 80000</p></td>\r\n"
//                    + "				</tr>\r\n" + "			</table>\r\n"
//                    + "			\r\n" + "			<div style=\"padding-top:20px;\">\r\n"
//                    + "				<p style=\"margin-top: 0;margin-bottom: 15px;\"> Products List  :</p>\r\n"
//                    + "				<table class=\"table\" style=\"width: 100%;margin-bottom: 1rem;\">\r\n"
//                    + "					<tr style=\"border-color:red;\">\r\n"
//                    + "					  <td style=\"padding: 0.75rem;vertical-align: top; border: 1px solid #dee2e6;\"><b>Description</b></td>\r\n"
//                    + "					  <td style=\"padding: 0.75rem;vertical-align: top; border: 1px solid #dee2e6;\"><b>Prix Unitaire HT</b></td>\r\n"
//                    + "					  <td style=\"padding: 0.75rem;vertical-align: top; border: 1px solid #dee2e6;\"><b>Anzahl</b></td>\r\n"
//                    + "					  <td style=\"padding: 0.75rem;vertical-align: top; border: 1px solid #dee2e6;\"><b>Steuer %</b></td>\r\n"
//                    + "					  <td style=\"padding: 0.75rem;vertical-align: top; border: 1px solid #dee2e6;\"><b>Steuer</b></td>\r\n"
//                    + "					  <td style=\"padding: 0.75rem;vertical-align: top; border: 1px solid #dee2e6;\"><b>Nettobetrag</b></td>\r\n"
//                    + "					</tr>\r\n" + tr + "				  </table>\r\n"
//                    + "			</div>\r\n"
//                    + "			<table style=\"    width: 100%;margin-top:40px;\">\r\n"
//                    + "				<tr>\r\n"
//                    + "					<td style=\"width:60%;\"></td>\r\n"
//                    + "					<td style=\"width:20%;padding: 0.75rem;vertical-align: top;\">Nettobetrag </td>\r\n"
//                    + "					<td style=\"padding: 0.75rem;vertical-align: top;\">" + ht
//                    + " MAD</td>\r\n" + "				</tr>\r\n"
//                    + "				<tr>\r\n"
//                    + "					<td style=\"width:60%;\"></td>\r\n"
//                    + "					<td style=\"width:20%;padding: 0.75rem;vertical-align: top;\">Steuer (20%)</td>\r\n"
//                    + "					<td style=\"padding: 0.75rem;vertical-align: top;\">" + tva
//                    + " MAD</td>\r\n" + "				</tr>\r\n"
//                    + "				<tr style=\"border:solid 2px;\">\r\n"
//                    + "					<td style=\"width:60%;\"></td>\r\n"
//                    + "					<td style=\"width:20%;padding: 0.75rem;vertical-align: top;\">Bruttosumme </td>\r\n"
//                    + "					<td style=\"padding: 0.75rem;vertical-align: top;\">"
//                    + total + " MAD</td>\r\n" + "				</tr>\r\n"
//                    + "			<br>\r\n" +"</br>\r\n" + "<hr>\r\n" + "</hr>\r\n"
//                    +"<table style=\"    width: 100%;margin-top:10px;\">\r\n"
//                    + "				<tr style=\"padding: 0.75rem;vertical-align: top; \">\r\n"
//                    + "					<td style=\"width:60%;\"></td>\r\n"
//                    + "					<td style=\"width:20%;padding: 0.75rem;vertical-align: top;\">Signature :</td>\r\n"
//                    + "					<td style=\"padding: 0.75rem;vertical-align: top; \">  </td>\r\n"
//                    + "				</tr>\r\n"
//                    + "			</table>\r\n" + "			</div>\r\n" + "		</div>\r\n"
//                    + "	<div style=\"text-align:center;\"><p style=\"margin-top: 130px;margin-bottom: 1rem;position: absolute;\n" +
//                    "    bottom: 0;\">RC 12345 -Siége social :Hay Salam N 26 N° d'identifiant fiscal: 12346578 - Tel: 0637834832 -Email : yourstore@gmail.com site web www.yourstore.com</p></div>"
//                    + "</body>\r\n" + "</html>";
//            worker.parseXHtml(pdfWriter, document, new StringReader(str));
//            document.close();
////            this.openPdfFile();
//            Desktop desktop = Desktop.getDesktop();
//            desktop.getDesktop().open(new File("Facture.pdf"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void openPdfFile() throws IOException {
//        File file = new File("Facture.pdf");
////        if (file.toString().endsWith("re.pdf"))
////            try {
////                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
////            } catch (IOException e) {
////                // TODO Auto-generated catch block
////                e.printStackTrace();
////            }
////        else {
//            Desktop desktop = Desktop.getDesktop();
//            desktop.open(file);
////        }
//    }
}
