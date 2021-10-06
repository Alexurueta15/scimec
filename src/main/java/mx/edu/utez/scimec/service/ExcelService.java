package mx.edu.utez.scimec.service;

import com.spire.xls.FileFormat;
import com.spire.xls.Workbook;
import mx.edu.utez.scimec.model.Announcement;
import mx.edu.utez.scimec.model.Appointment;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

@Service
public class ExcelService {

    private final String uri = ResourceUtils.CLASSPATH_URL_PREFIX + "templates/";

    public byte[] generateAppointmentResultFile(List<Appointment> appointments) {
        try {
            // 1.Lea la plantilla de Excel
            File file = ResourceUtils.getFile(uri + "appointment.xltx");
            InputStream in = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(in);
            // 2.Lee todas las hojas en la plantilla
            XSSFSheet sheet = wb.getSheetAt(0);
            // 3. Configura la fórmula para que se lea automáticamente
            sheet.setForceFormulaRecalculation(true);
            // 4. Establezca el valor en la celda correspondiente
            XSSFRow row;
            XSSFCell cell;
            XSSFCellStyle cellStyle = wb.createCellStyle();

            //Crear estilo de las filas
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);

            int rowNumber = 3;
            for (Appointment appointment : appointments) {

                row = sheet.createRow(rowNumber);

                cell = row.createCell(0);
                cell.setCellValue(appointment.getStudent().getFullname());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(1);
                cell.setCellValue(appointment.getDivision());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(2);
                cell.setCellValue(appointment.getStudent().getInstitutionalEmail());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(3);
                cell.setCellValue(appointment.getStudent().getGender());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(3);
                cell.setCellValue(appointment.getStudent().getGender());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(4);
                cell.setCellValue(appointment.getPrescription().getDiagnostic());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(5);
                cell.setCellValue(appointment.getDateTime().toLocalDate());
                cell.setCellStyle(cellStyle);

                rowNumber++;
            }

            // Guardar archivo
            FileOutputStream output = new FileOutputStream(file);

            wb.write(output);
            wb.close();
            output.close();
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] generateAnnouncementAttendanceFile(Announcement announcement) {
        try {
            // 1.Lea la plantilla de Excel
            File file = ResourceUtils.getFile(uri + "announcement.xltx");
            InputStream in = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(in);
            // 2.Lee todas las hojas en la plantilla
            Sheet sheet = wb.getSheetAt(0);
            // 3. Configura la fórmula para que se lea automáticamente
            sheet.setForceFormulaRecalculation(true);
            // 4. Establezca el valor en la celda correspondiente

            Row row = sheet.getRow(0);
            Cell cellPeriod = row.getCell(1, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
            cellPeriod.setCellValue(announcement.getPeriod().getName());

            row = sheet.getRow(1);
            Cell cellAnnouncement = row.getCell(1, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
            cellAnnouncement.setCellValue(announcement.getName());

            // Guardar archivo
            FileOutputStream output = new FileOutputStream(file);

            wb.write(output);
            wb.close();
            output.close();

            Workbook workbook = new Workbook();
            InputStream inputStream = new FileInputStream(file);
            workbook.loadFromStream(inputStream);

            File filePDF = ResourceUtils.getFile(uri + "announcement.pdf");
            FileOutputStream outputPDF = new FileOutputStream(filePDF);
            workbook.saveToStream(outputPDF, FileFormat.PDF);

            return Files.readAllBytes(filePDF.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}