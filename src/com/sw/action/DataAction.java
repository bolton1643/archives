package com.sw.action;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sw.service.DataManager;
import com.sw.util.MetaData;
import com.sw.util.MetaDataRow;

public class DataAction extends ActionSupport{
  private static final long serialVersionUID = 1L;
  private static Logger logger = Logger.getLogger(DataAction.class);
	
	 private String page;//当前处于第几页
	 private String pagesize;//每页的数据数

	 private String radd;
	 private String rdelete;
	 private String rmodify;
	 private String rdownload;
	 private String rprint;
	
	 private String total;
	 private String url;
	 private String param;
	 private String tid;// 表的名称
   private String id;// 编辑时数据行的id'

	 private List<MetaDataRow> cList;// 数据表的字段
 	 private List<Object[]> dList;// 数据--列表

	 private DataManager dataManager;

	 private MetaData metaData;// 表结构

	 private List<String> qColumn;

	 private List<String> qCond;

	 private List<String> qValue;

	 private String jsonList;
	
	 private String rid;
	
	 public String getRid() {
		  return rid;
	 }//end

	 public void setRid(String rid) {
		 this.rid = rid;
	 }//end

	 private static Connection getConnection() throws ClassNotFoundException,SQLException{
	   String driver="com.mysql.jdbc.Driver";
   	String url="jdbc:mysql://localhost/rf2";
   	String user="root";
   	String password="123456";
	
	   Class.forName(driver);
   	Connection conn=DriverManager.getConnection(url,user,password);
   	return conn;
  }//end of
	
	 public String getJsonList() {
		 return jsonList;
	 }//end of

	 public void setJsonList(String jsonList) {
		  this.jsonList = jsonList;
	 }//end of 

	 public MetaData getMetaData() {
		 return metaData;
	 }//end of

	 public void setMetaData(MetaData metaData) {
		 this.metaData = metaData;
	 }//end

	 public String test()throws Exception{
		  HttpServletResponse response = ServletActionContext.getResponse();
		  response.setHeader("Cache-Control", "no-cache");
		  response.setContentType("text/html;charset=utf-8");
  		    // 获取流
		  PrintWriter out = response.getWriter();
    		// 将流打到客户端
	  	out.print(jsonList);
		// 清空缓存
  		out.flush();
	  	// 关闭流
		 out.close();	
		
		 return SUCCESS;
	 }//end of func
	 
	 public String toJsonData(int totalRecord){
		  StringBuilder sb = new StringBuilder();
		  StringBuilder sbRow = new StringBuilder();
		  boolean f = true;
		
		  sb.append("{\"Rows\":[");
		  if(dList !=null && dList.size()>0){
			int dataRows = dList.size();
			for(int i=0;i< dataRows;i++){
				Object o2[] = (Object[]) dList.get(i);
				
				sbRow.setLength(0);
				boolean f2 = true;
				for (int j = 0; j < cList.size(); j++) {
					if(f2){
						f2 = false;
					}else{
						sbRow.append(",");
					}
					MetaDataRow mdr = cList.get(j);
					sbRow.append("\"").append(mdr.getDName()).append("\"");
					sbRow.append(":");					
					sbRow.append("\"").append((o2[j] != null && !o2[j].equals("null")) ? o2[j] : "" ).append("\"");
				}
				
				if(f){
					f = false;
				}else{
					sb.append(",");
				}
				sb.append("{"+sbRow.toString()+"}");
			}
			
			sb.append("],\"Total\":\"").append(""+totalRecord).append("\"}");
		}//end of
		//String s = "{\"Rows\":[{\"CustomerID\":\"ALFKI\",\"CompanyName\":\"Alfreds Futterkiste\",\"ContactName\":\"Maria Anders\",\"ContactTitle\":\"Sales Representative\",\"Address\":\"Obere Str. 57\",\"City\":\"Berlin\",\"Region\":null,\"PostalCode\":\"12209\",\"Country\":\"Germany\",\"Phone\":\"030-0074321\",\"Fax\":\"030-0076545\"}],\"Total\":\"2\"}";
		  return sb.toString();
	 }//end of
	 
	 /**
	  *  获取数据列表
	  * @return
	  * @throws Exception
	  */
	 public String list() throws Exception {
		  try {
			  String tb = tid;
  			cList = metaData.getMetaData(tb);
  			boolean f = true;
	  		StringBuffer whereCond = new StringBuffer();
		  	if (qValue != null && qValue.size() > 0) {
			  	String [] qValueArray = qValue.get(0).split(",");
				  String [] qCondArray = qCond.get(0).split(",");
				  String [] qColumnArray = qColumn.get(0).split(",");
				
				  for (int t = 0; t < qValueArray.length; t++) {
					  if (qValueArray[t] != null	&& qValueArray[t].trim().length() > 0) {
						  String qC = qCondArray[t].trim();
						  if (qC.equalsIgnoreCase("等于"))
							  qC = "=";
						  else if (qC.equalsIgnoreCase("大于等于"))
							  qC = ">=";
						  else if (qC.equalsIgnoreCase("小于等于"))
							  qC = "<=";
						  if (f) {
							  f = false;
							  if (qC.equalsIgnoreCase("包含"))
								   whereCond.append("(").append(qColumnArray[t].trim()).append(" like '%").append(qValueArray[t].trim()).append("%')");
							  else
								   whereCond.append("(").append(qColumnArray[t].trim()).append(qC).append(" '").append(qValueArray[t].trim()).append("')");
						   } else {
							    if (qC.equalsIgnoreCase("包含"))
							      whereCond.append(" and (").append(	qColumnArray[t].trim()).append(" like '%").append(qValueArray[t].trim()).append("%')");
							    else
								    whereCond.append(" and (").append(qColumnArray[t].trim()).append(" '").append(qC).append(qValueArray[t].trim()).append("')");
						   }//end of else
					  }//end of if (qValueArray[t] != null   && qValueArray[t].trim().length() > 0)
				}//end of for
			}//end of if (qValue != null && qValue.size() > 0)

			String cp = this.getPage();
			if (cp == null || cp.trim().length() <= 0)
				cp = "1";
			this.setPage(cp);

			int ps = 30;
			if(pagesize != null && pagesize.length()>0){
				ps = Integer.parseInt(pagesize);
			}
			if (dataManager.list(tb,whereCond.toString(), page, ps) != null)
				this.setDList(dataManager	.list(tb,whereCond.toString(), page, ps));
			int totalRecord = dataManager.getTotalCount(tb,whereCond.toString());

			String s = toJsonData(totalRecord);
			if(s!=null && s.length()>0){
				this.setJsonList(s);
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();
			String data = request.getParameter("data");
			if(data !=null && data.equalsIgnoreCase("1"))
				test();
			
			if (totalRecord % ps != 0)
				totalRecord = totalRecord / ps + 1;
			else
				totalRecord = totalRecord / ps;
			this.setTotal(Integer.toString(totalRecord));
			this.setUrl("dList.action");
			this.setParam("tid=" + tid);
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return SUCCESS;
		}
	}

	public String dAdd() throws Exception {
		String tb = tid;
		cList = metaData.getMetaData(tb);
		return SUCCESS;
	}

	public String dDoAdd() throws Exception {
		String tb = tid;
		cList = metaData.getMetaData(tb);
		StringBuffer sbCol = new StringBuffer();
		StringBuffer sbVal = new StringBuffer();
		StringBuffer sql = new StringBuffer();

		HttpServletRequest request = ServletActionContext.getRequest();
		boolean first = true;
		if (cList != null && cList.size() > 0) {
			for (MetaDataRow mdr : cList) {
				if (!mdr.getDName().equalsIgnoreCase("id")) {
					if (first) {
						first = false;

						sbCol.append(mdr.getDName());

						if (mdr.getDType().equalsIgnoreCase("字符串")
								|| mdr.getDType().equalsIgnoreCase("日期") || mdr.getDType().equalsIgnoreCase("文件")) {
							String v = request.getParameter(mdr.getDName());
							if (v == null)
								v = "";
							sbVal.append("'").append(v).append("'");
						} else {
							String v = request.getParameter(mdr.getDName());
							if (v == null || v == "")
								v = null;
							sbVal.append(v);
						}

					} else {
						sbCol.append(",").append(mdr.getDName());
						if (mdr.getDType().equalsIgnoreCase("字符串")
								|| mdr.getDType().equalsIgnoreCase("日期") || mdr.getDType().equalsIgnoreCase("文件")) {
							String v = request.getParameter(mdr.getDName());
							if (v == null)
								v = "";
							sbVal.append(",'").append(v).append("'");
						} else {
							String v = request.getParameter(mdr.getDName());
							if (v == null || v == "")
								v = "0";
							sbVal.append(",").append(v);
						}
					}
				}
			}
			sql.append("insert into ").append(tb).append("(").append(sbCol)
					.append(")").append("values(").append(sbVal).append(")");
			System.out.println(sql.toString());
			dataManager.add(sql.toString());
		}
		return "dsuccess";
	}

	public String dDel() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tb = request.getParameter("tid");
		
		String []ids = id.split(",");
		for(String s: ids)
			dataManager.deleteById(tb, s);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=utf-8");
		// 获取流
		PrintWriter out = response.getWriter();
		// 调用业务

		// 将list转为json
		String json = "success";
		// 将流打到客户端
		out.print(json);
		// 清空缓存
		out.flush();
		// 关闭流
		out.close();
		return SUCCESS;
	}

	public String dEdit() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tb = request.getParameter("tid");
		id = request.getParameter("id");

		cList = metaData.getMetaData(tb);
		Object o = dataManager.getById(tb, id).get(0);

		Object o2[] = (Object[]) o;
		String v = "";
		for (int j = 0; j < cList.size(); j++) {
			MetaDataRow mdr = cList.get(j);
			if (mdr.getDType().equalsIgnoreCase("字符串")) {
				if(o2[j] !=null)
					v = (String) o2[j];
				else v = "";
			} else if (mdr.getDType().equalsIgnoreCase("整数")) {
				if(o2[j] !=null)
					v = ((Integer) o2[j]).toString();
				else v = "";
			} else if (mdr.getDType().equalsIgnoreCase("小数")) {
				if(o2[j] !=null)
					v = ((Double) o2[j]).toString();
				else v = "";
			} else if (mdr.getDType().equalsIgnoreCase("日期")) {
				if(o2[j] !=null){
				Date d = (Date) o2[j];
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				v = sdf.format(d);
				}else v = "";
			}else if(mdr.getDType().equalsIgnoreCase("文件")){
				if(o2[j] !=null)
					v = (String) o2[j];
				else v = "";
			}

			mdr.setDValue(v);
		}

		return SUCCESS;
	}

	public String dDoEdit() throws Exception {
		String tb = tid;
		cList = metaData.getMetaData(tb);
		StringBuffer sql = new StringBuffer();
		StringBuffer sbCol = new StringBuffer();

		HttpServletRequest request = ServletActionContext.getRequest();
		boolean first = true;
		if (cList != null && cList.size() > 0) {
			for (MetaDataRow mdr : cList) {
				if(mdr.getDName().equalsIgnoreCase("ID")){
					continue;
				}
				if (first) {
					first = false;
					sbCol.append(mdr.getDName()).append("=");
				} else {
					sbCol.append(",").append(mdr.getDName()).append("=");
				}

				if (mdr.getDType().equalsIgnoreCase("字符串")
						|| mdr.getDType().equalsIgnoreCase("日期") || mdr.getDType().equalsIgnoreCase("文件")) {
					String v = request.getParameter(mdr.getDName());
					if (v == null || v == "")
						v = "";
					sbCol.append("'").append(v).append("'");
				} else {
					String v = request.getParameter(mdr.getDName());
					if (v == null || v == "")
						v = null;
					sbCol.append(v);
				}

			}
			sql.append("update ").append(tb).append(" set ").append(sbCol)
					.append(" where id=").append(id);
			// System.out.println(sql.toString());
			dataManager.update(sql.toString());
		}
		return "dsuccess";
	}

	public String PDF() throws Exception{
		boolean f = true;
		StringBuffer whereCond = new StringBuffer();
		if (qValue != null && qValue.size() > 0) {
			String [] qValueArray = qValue.get(0).split(",");
			String [] qCondArray = qCond.get(0).split(",");
			String [] qColumnArray = qColumn.get(0).split(",");
			
			for (int t = 0; t < qValueArray.length; t++) {
				if (qValueArray[t] != null
						&& qValueArray[t].trim().length() > 0) {
					String qC = qCondArray[t].trim();
					if (qC.equalsIgnoreCase("等于"))
						qC = "=";
					else if (qC.equalsIgnoreCase("大于等于"))
						qC = ">=";
					else if (qC.equalsIgnoreCase("小于等于"))
						qC = "<=";

					if (f) {
						f = false;
						if (qC.equalsIgnoreCase("包含"))
							whereCond.append("(").append(qColumnArray[t].trim()).append(
									" like '%").append(
									qValueArray[t].trim()).append("%')");
						else
							whereCond.append("(").append(qColumnArray[t].trim()).append(qC).append(" '").append(
									qValueArray[t].trim()).append("')");
					} else {
						if (qC.equalsIgnoreCase("包含"))
							whereCond.append(" and (").append(
									qColumnArray[t].trim()).append(" like '%").append(
											qValueArray[t].trim()).append("%')");
						else
							whereCond.append(" and (").append(
									qColumnArray[t].trim()).append(" '")
									.append(qC).append(
											qValueArray[t].trim()).append("')");
					}

				}
			}
		}
		
		String rName = "";
		if(rid !=null && rid.length()>0){
			rName = "rt"+rid;
		}
		
		String reportSource;
		reportSource = ServletActionContext.getServletContext()
		.getRealPath("jasper/"+rName+".jrxml");
		File parent = new File(reportSource).getParentFile();
		//将.jrxml模板文件编译成为.jasper文件,当然,其文件名可以指定,如果没指定,则与.jrxml文件名一样.只是后缀不同而已
		JasperCompileManager.compileReportToFile(reportSource, new File(
				parent, rName+".jasper")
		.getAbsolutePath());			 
		
		
			File reportFile = new File(ServletActionContext.getRequest().getRealPath("/jasper/"+rName+".jasper"));  
	        Map<String, String> parameters = new HashMap<String, String>();  
	        if(whereCond.toString() !=null && whereCond.toString().length()>0){
	        	parameters.put("whereCond", " where (1=1 and"+whereCond.toString()+")");  
	        }else parameters.put("whereCond", " ");
	        //List<Person> personList = new PersonService().getAllPerson();  
	        JasperPrint jasperPrint = null;  
	        try{  
	        JasperReport jasperReport = (JasperReport) JRLoader  
	                .loadObject(reportFile);  
	        
//	        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,  
//	                new JRBeanCollectionDataSource(dList));  
	        if(rid !=null && rid.equalsIgnoreCase("7")){
	        	jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	        }else{
	        	jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,  getConnection());	
	        }
			
	        
	        }catch (Exception e) {  
	        	logger.error(e.getMessage());
	            throw e;  
	        }  
	        if(null != jasperPrint){  
	        	HttpServletResponse resp = ServletActionContext.getResponse();  
	        	resp.reset();
	            

	            resp.setContentType("application/pdf");
	            resp.setCharacterEncoding("UTF-8");  
	            resp.setHeader("Content-Disposition", "attachment; filename=\""  
	                    + URLEncoder.encode("r11", "UTF-8") + ".pdf\"");  
	                 
	            ServletOutputStream ouputStream = resp.getOutputStream();  
	            
	            // 使用JRPdfExproter导出器导出pdf  
	            JRPdfExporter exporter = new JRPdfExporter();  
	            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);  
	            exporter.exportReport();
	            
	            ouputStream.flush();
	            ouputStream.close();
	        }  
		
		return null;
	}
	
	public String XLS() throws Exception{
		if(rid !=null && rid.length()<=0){
			rid = "";
		}
		
		String reportSource;
		reportSource = ServletActionContext.getServletContext().getRealPath("jasper/xls_"+rid+".jrxml");
		File parent = new File(reportSource).getParentFile();
		//将.jrxml模板文件编译成为.jasper文件,当然,其文件名可以指定,如果没指定,则与.jrxml文件名一样.只是后缀不同而已
		JasperCompileManager.compileReportToFile(reportSource, new File(
				parent, "xls_"+rid+".jasper")
		.getAbsolutePath());			 
		
		
			File reportFile = new File(ServletActionContext.getRequest().getRealPath("/jasper/xls_"+rid+".jasper"));  
	        Map<String, String> parameters = new HashMap<String, String>();  
	        parameters.put("tid", rid);  
	        //List<Person> personList = new PersonService().getAllPerson();  
	        JasperPrint jasperPrint = null;  
	        try{  
	        JasperReport jasperReport = (JasperReport) JRLoader  
	                .loadObject(reportFile);  
	        
	        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,  getConnection());	
			
	        }catch (Exception e) {  
	        	logger.error(e.getMessage());
	            throw e;  
	        }  
	        if(null != jasperPrint){  
	        	HttpServletResponse resp = ServletActionContext.getResponse();  
	        	resp.reset();
	            

	            resp.setContentType("application/vnd.ms-excel");
	            resp.setCharacterEncoding("UTF-8");  
	            resp.setHeader("Content-Disposition", "attachment; filename=\""  
	                    + URLEncoder.encode("导出", "UTF-8") + ".xls\"");  
	                 
	            ServletOutputStream ouputStream = resp.getOutputStream();  
	            
	            // 使用JRPdfExproter导出器导出pdf  
//	            JRPdfExporter exporter = new JRPdfExporter();  
//	            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//	            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);  
//	            exporter.exportReport();
	            
	            JRXlsExporter exporter = new JRXlsExporter();  
	            
	            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
	            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE); // 删除记录最下面的空行
	            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);// 删除多余的ColumnHeader
	            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);// 显示边框
	            exporter.exportReport();	            
	            
	            ouputStream.flush();
	            ouputStream.close();
	        }  
		
		return null;
	}
	public List<MetaDataRow> getCList() {
		return cList;
	}

	public void setCList(List<MetaDataRow> list) {
		cList = list;
	}

	public List<Object[]> getDList() {
		return dList;
	}

	public void setDList(List<Object[]> list) {
		dList = list;
	}


	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public List<String> getQColumn() {
		return qColumn;
	}

	public void setQColumn(List<String> column) {
		qColumn = column;
	}

	public List<String> getQCond() {
		return qCond;
	}

	public void setQCond(List<String> cond) {
		qCond = cond;
	}

	public List<String> getQValue() {
		return qValue;
	}

	public void setQValue(List<String> value) {
		qValue = value;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	public String getRadd() {
		return radd;
	}

	public void setRadd(String radd) {
		this.radd = radd;
	}

	public String getRdelete() {
		return rdelete;
	}

	public void setRdelete(String rdelete) {
		this.rdelete = rdelete;
	}

	public String getRmodify() {
		return rmodify;
	}

	public void setRmodify(String rmodify) {
		this.rmodify = rmodify;
	}

	public String getRdownload() {
		return rdownload;
	}

	public void setRdownload(String rdownload) {
		this.rdownload = rdownload;
	}

	public String getRprint() {
		return rprint;
	}

	public void setRprint(String rprint) {
		this.rprint = rprint;
	}

}
