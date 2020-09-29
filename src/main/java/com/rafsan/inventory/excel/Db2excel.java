package com.rafsan.inventory.excel;
import java.awt.*;
import java.io.*;
import java.sql.*;

import com.rafsan.inventory.dao.CategoryDao;
import com.rafsan.inventory.entity.Category;
import com.rafsan.inventory.model.CategoryModel;
import com.rafsan.inventory.model.ProductModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
public class Db2excel {
    CategoryModel pr=new CategoryModel();

    public static void main(String[] args) {
        new Db2excel().export();
    }

    public void export() {
        String jdbcURL = "jdbc:mysql://localhost:3306/inventory";
        String username = "root";
        String password = "";

        String excelFilePath = "Stocklist.xlsx";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "SELECT * FROM products WHERE quantity>0";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Products");
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);

            writeHeaderLine(sheet);

            writeDataLines(result, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();

            statement.close();
            Desktop.getDesktop().open(new File(excelFilePath));

        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }

    private void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("N° ");
        sheet.autoSizeColumn(0);

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Description");
        sheet.autoSizeColumn(1);



        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Category");
        sheet.autoSizeColumn(2);


        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Quantity");
        sheet.autoSizeColumn(3);

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Buy Price");
        sheet.autoSizeColumn(4);

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Sell Price");
        sheet.autoSizeColumn(5);

    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
                                XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
            float n = result.getInt("id");
            String description = result.getString("description");
            String name = result.getString("name");
//            Category cc=pr.getCategory(result.getInt("categoryId"));
//            String category=cc.getType();
//            int category=result.getInt("categoryId");
            int quantity = result.getInt("quantity");
            double priceBuy =result.getDouble("priceBuy");
            double priceSell =result.getDouble("priceSell");

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(n);

            cell = row.createCell(columnCount++);
            cell.setCellValue(description);

            cell = row.createCell(columnCount++);
            cell.setCellValue(name);


            cell = row.createCell(columnCount++);
            cell.setCellValue(quantity);

            cell = row.createCell(columnCount++);
            cell.setCellValue(priceBuy);

            cell = row.createCell(columnCount);
            cell.setCellValue(priceSell);

        }
    }
}
