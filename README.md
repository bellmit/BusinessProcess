#个人空间

## 服务错误状态码对应表
>|错误状态码      | 服务名              |
>|-----          |-----                |
>|10XXX          |EHR                  | 
>|11XXX          |BLOOD                |
>|12XXX          |LOGISTICS            |
>|13XXX          |LAB                  |
>|14XXX          |QUALITYCONTROL       |
>|15XXX          |AI                   |
>|16XXX          |SCREENING            |
>|17XXX          |LOGIN                |
>|18XXX          |CONFIG               |
>|19XXX          |CONSUMABLE           |
>|20XXX          |FM                   |
>|21XXX          |LOG                  |
>|22XXX          |MSG                  |
>|23XXX          |PARAMETER            |
>|24XXX          |SECURITY             |
>|25XXX          |STATS                |
>|90XXX          |DOMAIN               |


## 版本说明

>|版本号      | 发布日期              |项目负责人  |项目组成员                       |
>|-----      |-----                  |-----       |-----                           |
>|v1.0.0     |2018-11-8              |胡彬        |胡彬                              |



### v1.1.0 版本说明

#### 功能点
* 1.  【平台登录】登录页面重新设计制作

#### 文档
* 1. 产品原型：
* 2. 产品PRD：
* 3. UI文档：
* 4. API文档：
* 5. 流程图：



    @RequestMapping(value = "/acmeapi/getString", method = RequestMethod.GET)
    public void getString(String filePath,String [] keys) throws IOException {
        logger.info(filePath);
        logger.info(keys[0]);
        List<String[]> excelString = ParseExcel.parseFileToArray(filePath);
        List<String[]> resultString = new ArrayList<>();
        for(int j = 0; j < excelString.size(); j++){
            String [] excelArry = excelString.get(j);
            String departmenttype = excelArry[3];
            String departmentid = excelArry[0];
            logger.info("-----------");
            //医院
            if("hos".equals(departmenttype)){
                Criteria<EhrNewborn> criteria = new Criteria<>();
                EhrNewborn ehrNewborn = new EhrNewborn();
                ehrNewborn.setHosid(departmentid);
                criteria.setCondition(ehrNewborn);
                List<Object> objects = dao.queryByList(criteria);
                if(objects != null && objects.size() > 0){
                    excelArry[14] = "1";
                }
                resultString.add(excelArry);
                //采血点
            } else if("collect".equals(departmenttype)){
                int i = dao.selectIfCollect(departmentid);
                if(i != 0){
                    excelArry[14] = "1";
                }
                resultString.add(excelArry);
                //实验室
            } else if("lab".equals(departmenttype)){
                int i = dao.selectIfCollect(departmentid);
                if(i != 0){
                    excelArry[14] = "1";
                }
                resultString.add(excelArry);
                //其他
            }else if("center".equals(departmenttype)){
                Criteria<EhrNewborn> criteria = new Criteria<>();
                EhrNewborn ehrNewborn = new EhrNewborn();
                ehrNewborn.setCenterid(departmentid);
                criteria.setCondition(ehrNewborn);
                List<Object> objects = dao.queryByList(criteria);
                if(objects != null && objects.size() > 0){
                    excelArry[14] = "1";
                }
                resultString.add(excelArry);
            }else{
                int i = dao.selectIfCollect(departmentid);
                if(i != 0){
                    excelArry[14] = "1";
                }
                resultString.add(excelArry);
            }
        }


        List<String> titles = new ArrayList<>();
        titles.add("departmentid");
        titles.add("departmentname");
        titles.add("code");
        titles.add("departmenttype");
        titles.add("departmentpid");
        titles.add("医院名称");
        titles.add("医院所在区名称");
        titles.add("医院所在区code");
        titles.add("所属筛查中心id");
        titles.add("所属筛查中心名称");
        titles.add("saas-机构id");
        titles.add("saas-机构名称");
        titles.add("saas-机构所在地编码");
        titles.add("saas-备注");
        titles.add("flag");


        List<Map<String, Object>> values = new ArrayList<>();
        resultString.forEach(strings -> {
            Map<String,Object> mapvalue = new HashMap<>();
            mapvalue.put("departmentid",strings[0]);
            mapvalue.put("departmentname",strings[1]);
            mapvalue.put("code",strings[2]);
            mapvalue.put("departmenttype",strings[3]);
            mapvalue.put("departmentpid",strings[4]);
            mapvalue.put("医院名称",strings[5]);
            mapvalue.put("医院所在区名称",strings[6]);
            mapvalue.put("医院所在区code",strings[7]);
            mapvalue.put("所属筛查中心id",strings[8]);
            mapvalue.put("所属筛查中心名称",strings[9]);
            mapvalue.put("saas-机构id",strings[10]);
            mapvalue.put("saas-机构名称",strings[11]);
            mapvalue.put("saas-机构所在地编码",strings[12]);
            mapvalue.put("saas-备注",strings[13]);
            mapvalue.put("flag",strings[14]);
            values.add(mapvalue);
        });

        DiagnoseController.generateWorkbook("C:\\Users\\Mrbeard\\Desktop","20190215","xls",titles,values);

    }


    /**
     * 将数据写入指定path下的Excel文件中
     *      这里会有一个限制条件:列名的顺序必须和数据的存储顺序一致,否则会造成混乱;这是第一版,以后再改进这个
     * @param path 文件存储路径
     * @param name sheet名
     * @param style Excel类型
     * @param titles 标题串
     * @param values 内容集
     * @return T\F
     */
    public static boolean generateWorkbook(String path, String name, String style, List<String> titles, List<Map<String, Object>> values) {
        Workbook workbook;
        if ("XLS".equals(style.toUpperCase())) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        // 生成一个表格
        Sheet sheet = workbook.createSheet(name);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成样式
        Map<String, CellStyle> styles = createStyles(workbook);
        /*
         * 创建标题行
         */
        Row row = sheet.createRow(0);
        for (int i = 0; i < titles.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(styles.get("header"));
            cell.setCellValue(titles.get(i));
        }
        /*
         * 写入正文
         */
        Iterator<Map<String, Object>> iterator = values.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Map<String, Object> value = iterator.next();
            String content = "";
            for (Map.Entry<String, Object> map : value.entrySet()) {
                Object object = map.getValue();
                content = object.toString();
            }
            for (int i = 0; i < value.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(styles.get("cell"));
                cell.setCellValue(content);
            }
        }
        /*
         * 写入到文件中
         */
        boolean isCorrect = false;
        File file = new File(path);
        // 如果文件存在,则删除已有的文件,重新创建一份新的
        if (file.exists()) {
            file.deleteOnExit();
            file = new File(path);
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            isCorrect = true;
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return isCorrect;
    }

    /**
     * Create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = Maps.newHashMap();
        DataFormat dataFormat = wb.createDataFormat();

        // 标题样式
        CellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER); // 水平对齐
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直对齐
        titleStyle.setLocked(true);
        titleStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        titleStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        titleFont.setFontName("微软雅黑");
        titleStyle.setFont(titleFont);
        styles.put("title", titleStyle);

        // 文件头样式
        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setWrapText(true);
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        titleFont.setFontName("微软雅黑");
        headerStyle.setFont(headerFont);
        styles.put("header", headerStyle);

        // 正文样式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell", cellStyle);

        return styles;
    }

}

