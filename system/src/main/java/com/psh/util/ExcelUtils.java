package com.psh.util;

import com.alibaba.excel.EasyExcel;
import com.psh.entity.BillRecord;
import com.psh.entity.vo.RecordImport;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    private static final SimpleDateFormat sim = new SimpleDateFormat("yyyy/HH/dd");

    public static List<BillRecord> excelToShopIdList(InputStream inputStream) throws IOException {




        Workbook workbook = WorkbookFactory.create(inputStream);
        inputStream.close();
        // 在工作簿获取目标工作表
        Sheet sheet = workbook.getSheetAt(0);
        // 获取到最后一行
        int size = sheet.getPhysicalNumberOfRows();
        // 该集合用来储存行对象
        ArrayList<BillRecord> detaileds = new ArrayList<>();
        // 遍历整张表，从第二行开始，第一行的表头不要，循环次数不大于最后一行的值
        for (int i = 1; i < size; i++) {
            // 该对象用来储存行数据
            BillRecord record = new BillRecord();
            // 获取当前行数据
            Row row = sheet.getRow(i);
            for (Cell cell : row) {
                cell.setCellType(CellType.STRING);
                System.out.print(cell.getStringCellValue() + "  ");
            }
            //获取目标单元格的值并存进对象中
            //获取单元格
            record.setTId(row.getCell(0).getStringCellValue());
            try {

                String ss = row.getCell(1).getStringCellValue();
                System.out.println("---" + ss);
                if (StringUtils.isNotBlank(ss)) {
                    record.setRTime(sim.parse(ss));
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            record.setRNumber(row.getCell(2).getStringCellValue());
            record.setDescd(row.getCell(3).getStringCellValue());
            // 把对象放到集合里
            detaileds.add(record);
            System.out.println("获取到的当前行数据：" + record);
        }
        return detaileds;
    }
}
