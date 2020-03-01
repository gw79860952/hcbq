package com.gw.dev.hcbq.controller;

import com.gw.dev.hcbq.entity.DictionaryValue;
import com.gw.dev.hcbq.entity.ProKfzt;
import com.gw.dev.hcbq.entity.Project;
import com.gw.dev.hcbq.service.*;
import com.gw.dev.hcbq.util.DateUtil;
import com.gw.dev.hcbq.util.FastJsonUtils;
import com.gw.dev.hcbq.util.UUIDUtil;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.ss.usermodel.CellType.*;

@Controller
@RequestMapping("hc/import/")
public class ImportController {
    @Resource
    DicService dicService;

    @Resource
    ProjectService projectService;

    @Resource
    ProjectRightService projectRightService;

    @Resource
    ProjectRightDetailService projectRightDetailService;

    @Resource
    ProKfztService proKfztService;

    @RequestMapping("export")
    @ResponseBody
    public String export(Model model, HttpServletRequest request, HttpServletResponse response){
        try {
            //清空response
            response.reset();
            //设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + "fileName");
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            //将excel写入到输出流中
            //workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hc/showKfzt";
    }

    @RequestMapping("import")
    @ResponseBody
    public String importE(Model model, HttpServletRequest request,@RequestParam(value="file") MultipartFile file ){
        try {
            Map<String,String> promap = this.getProMap();
            Workbook wb = getWorkBook(file);
            Sheet sheet = wb.getSheetAt(0);
            for( int i = 1;i<sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                //判断是否需要项目去重
                boolean cf = false;
                String proId = null;
                String pname = getCellValue(row.getCell(0));
                for(Object s : promap.keySet()){
                    if(pname.equals(s)){
                        cf = true;
                        proId = promap.get(s);
                        break;
                    }
                }
                //开始读取一行导入
                if(cf){
                    //有重复先删整个重复项目
                    this.projectService.deleteAll(proId);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public Map<String,String> getProMap(){
        List<Project> l = this.projectService.findAll();
        Map<String,String> res = new HashMap<String, String>();
        for (Project p : l){
            res.put(p.getName(),p.getId());
        }
        return  res;
    }

    public static Workbook getWorkBook(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Workbook workbook = null;
        try {
            InputStream is = file.getInputStream();
            if (fileName.endsWith("xls")) {
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
        return workbook;
    }

    public static String stringDateProcess(Cell cell) {
        String result = new String();
        if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {// 日期格式、时间格式
            SimpleDateFormat sdf = null;
            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                sdf = new SimpleDateFormat("HH:mm");
            } else {// 日期
                sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            }
            Date date = cell.getDateCellValue();
            result = sdf.format(date);
        } else if (cell.getCellStyle().getDataFormat() == 58) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            double value = cell.getNumericCellValue();
            Date date = org.apache.poi.ss.usermodel.DateUtil
                    .getJavaDate(value);
            result = sdf.format(date);
        } else {
            double value = cell.getNumericCellValue();
            CellStyle style = cell.getCellStyle();
            DecimalFormat format = new DecimalFormat();
            String temp = style.getDataFormatString();
            // 单元格设置成常规
            if (temp.equals("General")) {
                format.applyPattern("#");
            }
            result = format.format(value);
        }

        return result;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = null;
        if (cell == null) {
            return cellValue;
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case NUMERIC: //数字
                cellValue = stringDateProcess(cell);
                break;
            case STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK: //空值
                cellValue = null;
                break;
        }
        return cellValue;
    }
}
