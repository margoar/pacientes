package web;

import datos.PacienteDaoJDBC;
import dominio.Paciente;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

@WebServlet("/ServletDescargaExcel")
public class ServletDescargaExcel extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Aquí colocas tu objeto tipo Date
        Date fechaActual = new Date();
        String fechActual = new SimpleDateFormat("dd-MM-yyyy").format(fechaActual);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=listado_pacientes_"+fechActual+".xlsx");

        HSSFWorkbook libro = new HSSFWorkbook();
        HSSFSheet hoja = libro.createSheet();
        // Se crea una fila dentro de la hoja
        HSSFRow fila = hoja.createRow(0);

        CellStyle style = libro.createCellStyle();
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // Se crea una celda dentro de la fila
        HSSFCell cell1 = fila.createCell((short) 0);

        HSSFCell cell2 = fila.createCell((short) 1);
        HSSFCell cell3 = fila.createCell((short) 2);
        HSSFCell cell4 = fila.createCell((short) 3);
        HSSFCell cell5 = fila.createCell((short) 4);

        //stilo
        // Se crea el contenido de la celda y se mete en ella.
        HSSFRichTextString nombre = new HSSFRichTextString("NOMBRE_PACIENTE");
        cell1.setCellValue(nombre);
        cell1.setCellStyle(style);

        HSSFRichTextString rut = new HSSFRichTextString("RUT");
        cell2.setCellValue(rut);
        cell2.setCellStyle(style);

        HSSFRichTextString edad = new HSSFRichTextString("EDAD");
        cell3.setCellValue(edad);
        cell3.setCellStyle(style);

        HSSFRichTextString estado = new HSSFRichTextString("ESTADO_COVID");
        cell4.setCellValue(estado);
        cell4.setCellStyle(style);

        HSSFRichTextString fecha_contagio = new HSSFRichTextString("FECHA_CONTAGIO");
        cell5.setCellValue(fecha_contagio);
        cell5.setCellStyle(style);

        List<Paciente> pacientes = new PacienteDaoJDBC().listar();

        int i = 1;
        for (Paciente pac : pacientes) {
            HSSFRow filaData = hoja.createRow(i);
            HSSFCell cellData1 = filaData.createCell((short) 0);
            HSSFCell cellData2 = filaData.createCell((short) 1);
            HSSFCell cellData3 = filaData.createCell((short) 2);
            HSSFCell cellData4 = filaData.createCell((short) 3);
            HSSFCell cellData5 = filaData.createCell((short) 4);
            HSSFRichTextString nombreData = new HSSFRichTextString(pac.getNombre().toUpperCase() + " " + pac.getApellido().toUpperCase());
            cellData1.setCellValue(nombreData);

            HSSFRichTextString rutData = new HSSFRichTextString(pac.getRut());
            cellData2.setCellValue(rutData);

            HSSFRichTextString edadData = new HSSFRichTextString(String.valueOf(pac.getEdad()));
            cellData3.setCellValue(edadData);

            HSSFRichTextString estadoData = new HSSFRichTextString(pac.isEstadoCovid() ? "CONTAGIADO" : "SANO");
            cellData4.setCellValue(estadoData);

            if (pac.getFechaContagio() != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                HSSFRichTextString fechaContagioData = new HSSFRichTextString(formatter.format(pac.getFechaContagio()));
                cellData5.setCellValue(fechaContagioData);
            }

            i++;
        }

        libro.write(response.getOutputStream());
        libro.close();

    }
}
