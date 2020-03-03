package com.gw.dev.hcbq.controller;

import com.gw.dev.hcbq.entity.*;
import com.gw.dev.hcbq.service.*;
import com.gw.dev.hcbq.util.DateUtil;
import com.gw.dev.hcbq.util.UUIDUtil;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Resource
    SearchService searchService;

    @RequestMapping("export")
    @ResponseBody
    public String export(Model model, HttpServletRequest request, HttpServletResponse response){
        SearchDto dto =  new SearchDto();
        dto.setFormType("bq");
        dto.setAsh("1");
        dto.setBsh("1");
        dto.setCsh("1");
        dto.setPage(0);
        dto.setLimit(60000);
        List<Search> list =  this.searchService.findPageByCondition(dto).getContent();
        String[] headnames = new String[]{"项目名称","曾用名","关联项目","权利类型","权利类别","权利名称","一级权利"
        ,"其他","二级权利","其他","权利限制描述","地域","地域国家","地域细分","权利开始日期","权利结束日期","永久","开始时间待定"
                ,"结束时间待定","期限描述","著作权所属公司","授权方","转出权利接收方","备注","合同名称","合同编号"};
        try {
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet();
            //设置表头样式
            CellStyle headstyle = setHeaderStyle(wb);
            CellStyle style = setCellStyle(wb,2);
            //标题行
            Row headrow = sheet.createRow(0);

            for(int i = 0 ; i < headnames.length ; i++){
                Cell hc = headrow.createCell(i);
                hc.setCellValue(headnames[i]);
                hc.setCellStyle(headstyle);
            }

            for(int i = 0 ; i < list.size() ; i++){
                Search sdto = list.get(i);
                Row row = sheet.createRow(i+1);

                Cell h1 = row.createCell(0);
                h1.setCellValue(sdto.getProName());

                Cell h2 = row.createCell(1);
                h2.setCellValue(sdto.getOldName());

                Cell h3 = row.createCell(2);
                h3.setCellValue(sdto.getGlProName());

                Cell h4 = row.createCell(3);
                if("xy".equals(sdto.getHaveType())){
                    h4.setCellValue("现有权利");
                }
                if("ys".equals(sdto.getHaveType())){
                    h4.setCellValue("原始权利");
                }
                if("zsq".equals(sdto.getHaveType())){
                    h4.setCellValue("转授权");
                }
                if("qlywzr".equals(sdto.getHaveType())){
                    h4.setCellValue("权利义务转让");
                }

                Cell h5 = row.createCell(4);
                h5.setCellValue(sdto.getProjectType());

                Cell h6 = row.createCell(5);
                h6.setCellValue(sdto.getQlname());

                String yjname = sdto.getYjName();
                if(yjname == null){
                    Cell h8 = row.createCell(7);
                }else{
                    if(yjname.contains("[其他]:")){
                        Cell h7 = row.createCell(6);
                        h7.setCellValue(sdto.getYjName().split("[其他]:")[0]);
                        if(sdto.getYjName().split("[其他]:").length > 1){
                            Cell h8 = row.createCell(7);
                            h8.setCellValue(sdto.getYjName().split("[其他]:")[1]);
                        }else{
                            Cell h8 = row.createCell(7);
                        }

                    }else{
                        Cell h7 = row.createCell(6);
                        h7.setCellValue(sdto.getYjName());
                        Cell h8 = row.createCell(7);
                    }
                }

                String ejname = sdto.getEjName();
                if(ejname == null){
                    Cell h10 = row.createCell(9);
                }else{
                    if(ejname.contains("[其他]:")){
                        Cell h9 = row.createCell(8);
                        h9.setCellValue(sdto.getEjName().split("[其他]:")[0]);
                        if(sdto.getEjName().split("[其他]:").length > 1){
                            Cell h10 = row.createCell(9);
                            h10.setCellValue(sdto.getEjName().split("[其他]:")[1]);
                        }else{
                            Cell h10 = row.createCell(9);
                        }

                    }else{
                        Cell h9 = row.createCell(8);
                        h9.setCellValue(sdto.getEjName());
                        Cell h10 = row.createCell(9);
                    }
                }


                Cell h11 = row.createCell(10);
                h11.setCellValue(sdto.getXzms());

                Cell h12 = row.createCell(11);
                h12.setCellValue(sdto.getDy());

                Cell h13 = row.createCell(12);
                h13.setCellValue(sdto.getDygj());

                Cell h14 = row.createCell(13);
                h14.setCellValue(sdto.getDyxf());

                Cell h15 = row.createCell(14);
                h15.setCellValue(sdto.getStartDate().replaceAll("-","/"));

                Cell h16 = row.createCell(15);
                h16.setCellValue(sdto.getEndDate().replaceAll("-","/"));

                if(sdto.getEndDate() == null){
                    Cell h17 = row.createCell(16);
                    h17.setCellValue("是");
                }else{
                    Cell h17 = row.createCell(16);
                }

                if("1".equals(sdto.getKsdd())){
                    Cell h18 = row.createCell(17);
                    h18.setCellValue("是");
                }else{
                    Cell h18 = row.createCell(17);
                }

                if("1".equals(sdto.getJsdd())){
                    Cell h19 = row.createCell(18);
                    h19.setCellValue("是");
                }else{
                    Cell h19 = row.createCell(18);
                }

                Cell h20 = row.createCell(19);
                h20.setCellValue(sdto.getQlms());

                Cell h21 = row.createCell(20);
                h21.setCellValue(sdto.getSsgs());

                Cell h22 = row.createCell(21);
                h22.setCellValue(sdto.getSqf());

                Cell h23 = row.createCell(22);
                h23.setCellValue(sdto.getBsqf());

                Cell h24 = row.createCell(23);
                h24.setCellValue(sdto.getBz());

                Cell h25 = row.createCell(24);
                h25.setCellValue(sdto.getHtmc());

                Cell h26 = row.createCell(25);
                h26.setCellValue(sdto.getHtbh());

            }

            //清空response
            response.reset();
            //设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + "export.xlsx");
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            //将excel写入到输出流中
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping("import")
    @ResponseBody
    public String importE(Model model, HttpServletRequest request,@RequestParam(value="file") MultipartFile file ){
        try {
            Map<String,String> promap = this.getProMap();

            //这里用于缓存从文件新建的记录的id,一级
            Map<String,String> filesavedmap_1 = this.getProMap();

            //这里用于缓存从文件新建的记录的id,二级
            Map<String,String> filesavedmap_2 = this.getProMap();

            Workbook wb = getWorkBook(file);
            Sheet sheet = wb.getSheetAt(0);
            for( int i = 1;i<sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);

                //判断是否需要项目去重
                boolean cf_db = false;
                boolean cf_file_1 = false;
                String proId = null;
                String pname = getCellValue(row.getCell(0));

                //判断db重复
                for(Object s : promap.keySet()){
                    if(pname.equals(s)){
                        cf_db = true;
                        proId = promap.get(s);
                        break;
                    }
                }

                //判断文件重复
                if(i == 0){
                    cf_file_1 = false;
                }else{
                    Row row_p = sheet.getRow(i-1);
                    if(getCellValue(row_p.getCell(0)).equals(pname)){
                        cf_file_1 = true;
                    }
                }

                //db有重复，文件无重复时（或为第一条），删除db里的
                if(cf_db && !cf_file_1){
                    this.projectService.deleteAll(proId);
                    //map中移除
                    promap.remove(pname);
                }
                //开始读取一行导入

                //一级
                //文件无重复，或为第一条时，新建一级
                if(!cf_file_1){
                    Project pro = new Project();
                    pro.setId(UUIDUtil.getUUID());
                    pro.setName(getCellValue(row.getCell(0)));
                    pro.setOldName(getCellValue(row.getCell(1)));
                    pro.setFormType("bq");
                    pro.setShStatus("1");
                    this.projectService.save(pro);
                    promap.put(pro.getName(),pro.getId());
                }

                //第二级
                //判断二级重复，这里不存在db判断了
                boolean cf_file_2 = false;
                if(i == 0){
                    cf_file_2 = false;
                }else{
                    Row row_p = sheet.getRow(i-1);
                    String tests = getCellValue(row.getCell(3)) + "-" + getCellValue(row.getCell(4)) + "-"
                            + getCellValue(row.getCell(5)) + "-" + getCellValue(row.getCell(6));
                    String tests_p = getCellValue(row_p.getCell(3)) + "-" + getCellValue(row_p.getCell(4)) + "-"
                            + getCellValue(row_p.getCell(5)) + "-" + getCellValue(row_p.getCell(6));
                    if(tests.equals(tests_p)){
                        cf_file_2 = true;
                    }
                }

                //文件无重复，或为第一条时，新建二级
                if(!cf_file_2){
                    ProjectRight pr = new ProjectRight();
                    pr.setId(UUIDUtil.getUUID());
                    //从缓存map里找
                    pr.setProjectId(promap.get(getCellValue(row.getCell(0))));
                    String haveType = getCellValue(row.getCell(3));
                    if("原始权利".equals(haveType)){
                        pr.setHaveType("ys");
                    }
                    if("现有权利".equals(haveType)){
                        pr.setHaveType("xy");
                    }
                    if("转授权".equals(haveType)){
                        pr.setHaveType("zsq");
                    }
                    if("权利义务转让".equals(haveType)){
                        pr.setHaveType("qlywzr");
                    }
                    pr.setProjectType(getCellValue(row.getCell(4)));
                    pr.setName(getCellValue(row.getCell(5)));
                    if(getCellValue(row.getCell(6)) != null && getCellValue(row.getCell(7)) != null){
                        pr.setYjName(getCellValue(row.getCell(6))+ "," + getCellValue(row.getCell(7)));
                    }else{
                        if(getCellValue(row.getCell(6)) == null){
                            pr.setYjName(getCellValue(row.getCell(7)));
                        }else{
                            pr.setYjName(getCellValue(row.getCell(6)));
                        }
                    }
                    if(getCellValue(row.getCell(8)) != null && getCellValue(row.getCell(9)) != null){
                        pr.setEjName(getCellValue(row.getCell(8))+ "," + getCellValue(row.getCell(9)));
                    }else{
                        if(getCellValue(row.getCell(8)) == null){
                            pr.setEjName(getCellValue(row.getCell(9)));
                        }else{
                            pr.setEjName(getCellValue(row.getCell(8)));
                        }
                    }
                    pr.setXzms(getCellValue(row.getCell(10)));
                    pr.setShStatus("1");
                    this.projectRightService.save(pr);
                    //同样进行缓存
                    filesavedmap_2.put(getCellValue(row.getCell(3)) + "-" + getCellValue(row.getCell(4)) + "-"
                            + getCellValue(row.getCell(5)) + "-" + getCellValue(row.getCell(6)),pr.getId());
                }

                //第三级不需要判断了
                ProjectRightDetail prd = new ProjectRightDetail();
                prd.setId(UUIDUtil.getUUID());
                prd.setProjectId(promap.get(getCellValue(row.getCell(0))));
                prd.setProjectRightId(filesavedmap_2.get(getCellValue(row.getCell(3)) + "-" + getCellValue(row.getCell(4)) + "-"
                        + getCellValue(row.getCell(5)) + "-" + getCellValue(row.getCell(6))));
                prd.setDy(getCellValue(row.getCell(11)));
                prd.setDygj(getCellValue(row.getCell(12)));
                prd.setDyxf(getCellValue(row.getCell(13)));

                Date ks = null;
                String kss = getCellValue(row.getCell(14));
                if(kss != null && !"".equals(kss)){
                    int count = getCellValue(row.getCell(14)).split("/").length;

                    if(count == 1){
                        ks = DateUtil.parseDate(kss,"yyyy");
                    }
                    if(count == 2){
                        ks = DateUtil.parseDate(kss,"yyyy-MM");
                    }
                    if(count == 3){
                        ks = DateUtil.parseDate(kss,"yyyy-MM-dd");
                    }
                    prd.setStartDate(kss);
                    prd.setStartDateS(ks);
                }

                Date js = null;
                String jss = getCellValue(row.getCell(15));
                if(jss != null && !"".equals(jss)){
                    int count1 = getCellValue(row.getCell(15)).split("/").length;

                    if(count1 == 1){
                        js = DateUtil.parseDate(jss,"yyyy");
                    }
                    if(count1 == 2){
                        js = DateUtil.parseDate(jss,"yyyy-MM");
                    }
                    if(count1 == 3){
                        js = DateUtil.parseDate(jss,"yyyy-MM-dd");
                    }
                    prd.setEndDate(jss);
                    prd.setEndDateS(js);
                }

                String yj = getCellValue(row.getCell(16));
                if("是".equals(yj)){
                    prd.setEndDateS(null);
                    prd.setEndDate(null);
                }

                String ksdd = getCellValue(row.getCell(17));
                if("是".equals(ksdd)){
                    prd.setKsdd("1");
                }else{
                    prd.setKsdd("0");
                }

                String jsdd = getCellValue(row.getCell(18));
                if("是".equals(ksdd)){
                    prd.setJsdd("1");
                }else{
                    prd.setJsdd("0");
                }

                prd.setQlms(getCellValue(row.getCell(19)));
                prd.setSsgs(getCellValue(row.getCell(20)));
                prd.setSqf(getCellValue(row.getCell(21)));
                prd.setBsqf(getCellValue(row.getCell(22)));
                prd.setBz(getCellValue(row.getCell(23)));
                prd.setHtmc(getCellValue(row.getCell(24)));
                prd.setHtbh(getCellValue(row.getCell(25)));
                prd.setShStatus("1");
                this.projectRightDetailService.save(prd);

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
        if("null".equals(cellValue)){
            cellValue = null;
        }
        return cellValue;
    }

    /**
     * 设置表头样式
     *
     * @param workbook
     * @return
     */
    private CellStyle setHeaderStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        //水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        //设置字体
        Font cellFont = workbook.createFont();
        cellFont.setBold(true);
        cellStyle.setFont(cellFont);
        return cellStyle;
    }

    /**
     * 设置单元格样式
     */
    private CellStyle setCellStyle(Workbook workbook, int i) {
        CellStyle cellStyle = workbook.createCellStyle();
        //奇数列 左对齐
        i = 2;
        if ((i & 1) != 1) {
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
        } else {
            //水平居中
            cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        }
        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        //设置字体
        Font cellFont = workbook.createFont();
        cellFont.setFontName("宋体");
        cellStyle.setFont(cellFont);
        return cellStyle;

    }


}
