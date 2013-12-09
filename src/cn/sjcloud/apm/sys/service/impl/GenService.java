package cn.sjcloud.apm.sys.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sjcloud.apm.core.base.BaseDaoIF;
import cn.sjcloud.apm.core.base.BaseService;
import cn.sjcloud.apm.core.constant.Constants;
import cn.sjcloud.apm.core.entity.TMenu;
import cn.sjcloud.apm.core.util.StringUtil;
import cn.sjcloud.apm.sys.page.GenPage;
import cn.sjcloud.apm.sys.service.GenServiceIF;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class GenService extends BaseService implements GenServiceIF {

	private static final String EXCEL_FILE_PATH = "D:/WorkStation/gen.xls";

	/** 忽略处理 */
	private static final int IGNORE = 0;
	/** 需要处理 */
	private static final int COGNIZE = 1;
	/** DAO操作类 */
	@Autowired
	private BaseDaoIF<TMenu> dao;

	@Transactional
	public void init(GenPage page) {

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void read(GenPage page) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT column_name, column_comment FROM information_schema.COLUMNS ");
		sql.append(" WHERE table_schema = ? AND table_name = ?");
		List<Object[]> resultList = (List<Object[]>) dao.findBySql(sql.toString(), new Object[] {
				Constants.DATABASE_NAME, page.getTableName() });
		try {
			this.toExcel(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public void gen(GenPage page) {
		try {
			List<String[]> dataList = this.readFromExel();
			this.genWithTemplate(page, dataList);
			// 生成菜单
			TMenu menu = new TMenu();
			menu.setMenuName(page.getGenBizName());
			menu.setLinkUrl(page.getGenBizCode() + "!doInit.action");
			dao.save(menu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void toExcel(List<Object[]> dataList) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("gen");

		for (int i = 0; i < dataList.size(); i++) {
			HSSFRow row = sheet.createRow(i);
			Object[] dataRow = dataList.get(i);
			for (int j = 0; j < dataRow.length; j++) {
				row.createCell(j).setCellValue((String) dataRow[j]);
			}
		}
		FileOutputStream fileOut = new FileOutputStream(EXCEL_FILE_PATH);
		wb.write(fileOut);
		fileOut.close();
	}

	private List<String[]> readFromExel() {
		// 需要忽略处理的列
		List<String> skipItem = Arrays.asList(new String[] {"id", "create_user", "create_time", "update_user", "update_time", "is_abled"});
		List<String[]> dataList = new ArrayList<String[]>();
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(EXCEL_FILE_PATH));
			HSSFSheet sheet = wb.getSheet("gen");
			
			int rowIndx = 0;
			HSSFRow row = null;
			while((row = sheet.getRow(rowIndx++)) != null) {
				String[] cellArray = new String[5];
				String bizCode = row.getCell(0).getStringCellValue();
				// 共通忽略处理的列
				if(skipItem.contains(bizCode)) {
					continue;
				}
				// Excel定义不处理的列
				if(row.getCell(2) != null) {
					double ignoreType = row.getCell(2).getNumericCellValue();
					if(ignoreType == IGNORE) {
						continue;
					}
				}
				
				cellArray[0] = toHump(bizCode);
				cellArray[1] = StringUtil.toggleFirstLetter(toHump(bizCode));
				cellArray[2] =  row.getCell(1).getStringCellValue();
				// 检索项标志
				Integer isSearchItem = IGNORE;
				if(row.getCell(3) != null && row.getCell(3).getNumericCellValue() == COGNIZE) {
					isSearchItem = COGNIZE;
				}
				cellArray[3]  = isSearchItem.toString();
				// 排序项标志
				Integer isSortItem = IGNORE;
				if(row.getCell(4) != null && row.getCell(4).getNumericCellValue() == COGNIZE) {
					isSortItem = COGNIZE;
				}
				cellArray[4]  = isSortItem.toString();
				dataList.add(cellArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	private void genWithTemplate(GenPage page, List<String[]> dataList) throws Exception {
		// 初始化FreeMarker配置
		// 创建一个Configuration实例
		Configuration cfg = new Configuration();
		// 设置FreeMarker的模版文件夹位置
		String srcBaseDir = this.getClass().getResource("").getFile();
		srcBaseDir = "E:/Software/eclipseKepler/workspace/APM";

		String jspBaseDir = srcBaseDir + File.separator + "WebContent" + File.separator + "page";
		srcBaseDir += File.separator + "src";

		jspBaseDir += File.separator + "sys";
		srcBaseDir += File.separator + "cn" + File.separator + "sjcloud" + File.separator + "apm";
		cfg.setDirectoryForTemplateLoading(new File(this.getClass().getClassLoader().getResource("").getFile()));

		String fileName = StringUtil.toggleFirstLetter(page.getGenBizCode());
		String bizCode = page.getGenBizCode();
		// 构造填充数据的Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bizCode", bizCode);
		map.put("fileName", fileName);
		map.put("itemList", dataList);
		map.put("entityName", toHump(page.getTableName()));
		// 1.Action
		// 创建模版对象
		Template t = cfg.getTemplate("action.ftl");
		// 在模版上执行插值操作，并输出到制定的输出流中
		t.process(map, new FileWriter(new File(srcBaseDir + File.separator + "sys" + File.separator + "action"
				+ File.separator + fileName + "Action.java")));
		// 2.ServiceIF
		// 创建模版对象
		Template t2 = cfg.getTemplate("serviceIF.ftl");
		// 在模版上执行插值操作，并输出到制定的输出流中
		t2.process(map, new FileWriter(new File(srcBaseDir + File.separator + "sys" + File.separator + "service"
				+ File.separator + fileName + "ServiceIF.java")));
		// 3.Service
		// 创建模版对象
		Template t3 = cfg.getTemplate("service.ftl");
		// 在模版上执行插值操作，并输出到制定的输出流中
		t3.process(map, new FileWriter(new File(srcBaseDir + File.separator + "sys" + File.separator + "service"
				+ File.separator + "impl" + File.separator + fileName + "Service.java")));
		// 4.Page
		// 创建模版对象
		Template t4 = cfg.getTemplate("page.ftl");
		// 在模版上执行插值操作，并输出到制定的输出流中
		t4.process(map, new FileWriter(new File(srcBaseDir + File.separator + "sys" + File.separator + "page"
				+ File.separator + fileName + "Page.java")));
		// 5.List JSP
		// 创建模版对象
		Template t5 = cfg.getTemplate("listJSP.ftl");
		// 在模版上执行插值操作，并输出到制定的输出流中
		t5.process(map, new FileWriter(new File(jspBaseDir + File.separator + bizCode + ".jsp")));
		// 6.Edit JSP
		// 创建模版对象
		Template t6 = cfg.getTemplate("editJSP.ftl");
		// 在模版上执行插值操作，并输出到制定的输出流中
		t6.process(map, new FileWriter(new File(jspBaseDir + File.separator + bizCode + "Edit.jsp")));
	}
	
	private String toHump(String str) {
		int underLineIndex = 0;
		while((underLineIndex = str.indexOf("_")) != -1) {
			String subStr = str.substring(underLineIndex, underLineIndex + 2);
			str = str.replaceFirst(subStr, str.substring(underLineIndex + 1, underLineIndex + 2).toUpperCase());
		}
		// 首字母变成大写
		String firstLetter = Character.toString(str.charAt(0));
		str = str.replaceFirst(firstLetter, firstLetter.toUpperCase());
		return str;
	
	}
}
