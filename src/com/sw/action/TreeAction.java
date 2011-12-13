package com.sw.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.sw.service.TreeManager;
import com.sw.util.ChineseUtil;
import com.sw.util.MetaDataRow;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sw.jsonfactory.treenode.ConvertToJson;
import com.sw.pojo.TreeNode;
import com.sw.pojo.TreeNodeRight;
import com.sw.pojo.User;

public class TreeAction extends ActionSupport {
    private static Logger logger = Logger.getLogger(TreeAction.class);
    private static final long serialVersionUID = 1L;

    public static final String LOGIN_SUCCESS = "success";

    public static final String LOGIN_FAIL = "failure";

    public static final String RESBONSE_ERROR = "error";

    public static final String RESBONSE_SUCCESS = "success";

    public static final String FORWARD_ERROR = "error";

    public static final String FORWARD_SUCCESS = "success";

    public static final String RESULT_COLLECT = "resultList";

    Log log = LogFactory.getLog(this.getClass());

    private String message;

    private String root;

    private TreeManager treeManager;

    private Integer id;

    private String text;// 模板名称

    private Integer parentId;// 父节点编号

    private String expanded;// 能否展开 1可以展开，0没有子菜单

    private String openurl;// 链接

    private String isfolder;// 是否目录 1目录，0非目录

    private List<MetaDataRow> mList;// 用户返回模板的字段

    private List<String> dName;// 新增，编辑时传参

    private List<String> dType;// 新增，编辑时传参

    private List<Integer> dLength;// 新增，编辑时传参

    private List<String> dAllowNull;// 新增，编辑时传参

    private List<String> dNotes;// 新增，编辑时传参

    private String admin;//

    protected void print(HttpServletResponse response, String info)
            throws IOException {
        try {
            response.getWriter().print(info);
            response.getWriter().flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public String treeList() throws Exception {
        Map<String,Object> session = ActionContext.getContext().getSession();
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/html;charset=utf-8");
            // 获取流
            PrintWriter out = response.getWriter();
            // 调用业务
            List<TreeNodeRight> list = new ArrayList<TreeNodeRight>();
            // 获取请求参数
            
            User u = (User)session.get("user");
            int userid = u.getUserId();
            if(root == null) 
                root  = "1";
            if (root == null) {

            } else if (root.equalsIgnoreCase("source")) {
                list = treeManager.findChildren("1",userid);
            } else {
                list = treeManager.findChildren(root,userid);
            }

            // 将list转为json
            String json = "";
            if(admin !=null && admin.equalsIgnoreCase("1")){
                if (list != null && list.size() > 0){
                    json = ConvertToJson.ConverListToJsonAdmin(list);
                }//endof if
            }else{
                if (list != null && list.size() > 0)
                    json = ConvertToJson.ConverListToJson(list);
            }//end

            // 将流打到客户端
            out.print(json);
            // 清空缓存
            out.flush();
            // 关闭流
            out.close();

        } catch (IOException e) {
            log.debug("加载树菜单出错" + e.getStackTrace());
        }
        print(response, RESBONSE_SUCCESS);
        return SUCCESS;
    }

    public String treeGet() throws Exception {
        String tb = "t" + id;

        mList = treeManager.getMetaData(tb);
        return SUCCESS;
    }

    public String treeEdit() throws Exception {
        TreeNode treeNode = treeManager.getTreeNodeById(id);
        this.setText(treeNode.getText());
        this.setId(treeNode.getId());
        this.setParentId(treeNode.getParentId());
        this.setExpanded(treeNode.getExpanded());
        if (isfolder != null && isfolder.equalsIgnoreCase("1")) {
            return "editmenu";
        } else {
            String tb = "t" + id;
            mList = treeManager.getMetaData(tb);
            return "edittemplate";
        }

    }

    public String treeDoEditMenu() throws Exception {
        TreeNode treeNode = new TreeNode();
        treeNode.setId(id);
        treeNode.setParentId(parentId);
        treeNode.setText(text);
        treeNode.setExpanded(expanded);
        treeNode.setIsfolder(isfolder);
        treeManager.updateTreeNode(treeNode);
        return SUCCESS;
    }

    /**
      * 修改模板
     * @return
     * @throws Exception
     */
    public String treeDoEditTemplate() throws Exception {
        TreeNode treeNode = new TreeNode();
        treeNode.setId(id);
        treeNode.setParentId(parentId);
        treeNode.setText(text);
        treeNode.setExpanded(expanded);
        treeNode.setIsfolder(isfolder);
        treeNode.setOpenurl(openurl);
        treeManager.updateTreeNode(treeNode);

        mList = treeManager.getMetaData("t" + id);
        Set<String> newColums = new HashSet<String>();
        boolean m = false;// 字段是否变化
        boolean mCol = false;
        boolean first = true;
        boolean existColumn = false;// 是否已有字段
        StringBuilder sb = new StringBuilder();
        StringBuilder sbColumn = new StringBuilder();

        boolean fileToOther = false;
        boolean otherToFile = false;
        sb.append("alter table t").append(id);
        for (int i = 0; i < dNotes.size(); i++) {
            sbColumn.setLength(0);
            existColumn = false;
            mCol = false;
            for (int j = 0; j < mList.size(); j++) {
                MetaDataRow mdr = mList.get(j);
                if (dNotes.get(i).equalsIgnoreCase(mdr.getDNotes())) {
                    if (dType.get(i).equalsIgnoreCase("文件")) {
                        if (mdr.getDName().endsWith("_FILE")) {// 文件类型，没有变化
                            newColums.add(ChineseUtil.getPinYinHeadChar(dNotes.get(i)) + "_FILE");
                            newColums.add(ChineseUtil.getPinYinHeadChar(dNotes.get(i)) + "_PHOTO");
                            existColumn = true;
                            otherToFile = false;
                            fileToOther = false;
                        } else {// 现在是文件类型，原来不是
                            otherToFile = true;
                            fileToOther = false;
                        }//end of else
                    } else {
                        if (mdr.getDName().endsWith("_FILE")) {// 原来是文件，现在不是
                            otherToFile = false;
                            fileToOther = true;
                        } else {  //不涉及文件操作
                            otherToFile = false;
                            fileToOther = false;
                            newColums.add(ChineseUtil.getPinYinHeadChar(dNotes.get(i)));
                            existColumn = true;
                            if (dType.get(i).equalsIgnoreCase(mdr.getDType())) {
                                if (dType.get(i).equalsIgnoreCase("字符串") && (mdr.getDLength().compareTo(dLength.get(i))!=0)) {
                                    mCol = true;
                                }//endofif
                            } else {
                                mCol = true;
                            }//endof

                            if (mCol) {
                                m = true;
                                if (first) {
                                    first = false;
                                    sbColumn.append(" modify column ");
                                } else {
                                    sbColumn.append(" ,modify column ");
                                }//end

                                sbColumn.append(ChineseUtil.getPinYinHeadChar(dNotes.get(i))).append(" ");
                                if (dType.get(i).equalsIgnoreCase("整数"))
                                    sbColumn.append(" int(11)");
                                else if (dType.get(i).equalsIgnoreCase("字符串"))
                                    sbColumn.append("varchar(").append(dLength.get(i)).append(")");
                                else if (dType.get(i).equalsIgnoreCase("日期"))
                                    sbColumn.append("datetime");
                                else if (dType.get(i).equalsIgnoreCase("小数"))
                                    sbColumn.append("double");
                                else if (dType.get(i).equalsIgnoreCase("文件")){
                                    sbColumn.append("varchar(200)");
                                }//end

                                if (dAllowNull.get(i).equalsIgnoreCase("不允许为空"))
                                    sbColumn.append(" not null");

                                sbColumn.append(" comment '").append(dNotes.get(i)).append("'");
                            }//endof(mCol)
                        }//endof//不涉及文件操作
                    }//endof
                }//endof dNotes.get(i).equalsIgnoreCase(mdr.getDNotes())
            }//endof for (int j = 0; j < mList.size(); j++)
            if (!existColumn) {
                m = true;
                if (first) {
                    first = false;
                    sbColumn.append(" add ");
                } else {
                    sbColumn.append(" ,add ");
                }//end
                
                if(otherToFile || dType.get(i).equalsIgnoreCase("文件")){
                    sbColumn.append(ChineseUtil.getPinYinHeadChar(dNotes.get(i))+"_PHOTO varchar(200) comment '"+dNotes.get(i)+"图片',");
                    sbColumn.append(ChineseUtil.getPinYinHeadChar(" add "+dNotes.get(i))+"_FILE ").append(" ");
                }//end
                else{
                    sbColumn.append(ChineseUtil.getPinYinHeadChar(dNotes.get(i))).append(" ");
                }//end

                if (dType.get(i).equalsIgnoreCase("整数"))
                    sbColumn.append(" int(11)");
                else if (dType.get(i).equalsIgnoreCase("字符串"))
                    sbColumn.append("varchar(").append(dLength.get(i)).append(")");
                else if (dType.get(i).equalsIgnoreCase("日期"))
                    sbColumn.append("datetime");
                else if (dType.get(i).equalsIgnoreCase("小数"))
                    sbColumn.append("double");
                else if (dType.get(i).equalsIgnoreCase("文件"))
                    sbColumn.append("varchar(200)");

                if (dAllowNull.get(i).equalsIgnoreCase("不允许为空"))
                    sbColumn.append(" not null");
                sbColumn.append(" comment '").append(dNotes.get(i)).append("'");
            }//endofif(!existColumn)
            if (!existColumn || mCol)
                sb.append(sbColumn.toString());
        }//endof for (int i = 0; i < dNotes.size(); i++)

        for (int j = 0; j < mList.size(); j++) {
            String dName = mList.get(j).getDName();
            if (newColums.contains(dName) || dName.equalsIgnoreCase("id")) {
            } else {
                m = true;
                if (first) {
                    first = false;
                    sb.append(" drop column ").append(dName);
                } else {
                    sb.append(" ,drop column ").append(dName);
                }//end
            }//end
        }//end

        if (m) {
            System.out.println(sb.toString());
            treeManager.operateTable(sb.toString());
        }//end
        return SUCCESS;
    }

    public String treeAddMenu() throws Exception {
        TreeNode treeNode = new TreeNode();
        treeNode.setParentId(parentId);
        treeNode.setText(text);
        treeNode.setExpanded(expanded);
        treeNode.setIsfolder(isfolder);
        treeManager.addTreeNode(treeNode);
        treeManager.updateExpanded(parentId);
        return SUCCESS;
    }

    /**
     * 增加模板
     * @return
     * @throws Exception
     */
    public String treeAdd() throws Exception {
        TreeNode treeNode = new TreeNode();
        treeNode.setParentId(parentId);
        treeNode.setText(text);
        treeNode.setExpanded(expanded);
        treeNode.setOpenurl(openurl);
        treeNode.setIsfolder(isfolder);

        int myId = treeManager.addTreeNode(treeNode);
        treeManager.updateExpanded(parentId);

        StringBuilder sb = new StringBuilder();
        sb.append("create table t").append(myId);
        sb.append(" ( ID int(11) NOT NULL auto_increment comment '编号',");
        for (int i = 0; i < dName.size(); i++) {
            if (dType.get(i).equalsIgnoreCase("文件")) {
                sb.append(ChineseUtil.getPinYinHeadChar(dName.get(i))+"_PHOTO varchar(200) comment '"+dName.get(i)+"图片',");
                sb.append(ChineseUtil.getPinYinHeadChar(dName.get(i))).append("_FILE ");
            } else {
                sb.append(ChineseUtil.getPinYinHeadChar(dName.get(i))).append(" ");
            }//end

            if (dType.get(i).equalsIgnoreCase("整数"))
                sb.append(" int(11)");
            else if (dType.get(i).equalsIgnoreCase("字符串"))
                sb.append("varchar(").append(dLength.get(i)).append(")");
            else if (dType.get(i).equalsIgnoreCase("日期"))
                sb.append("datetime");
            else if (dType.get(i).equalsIgnoreCase("小数"))
                sb.append("double");
            else if (dType.get(i).equalsIgnoreCase("文件"))
                sb.append("varchar(200)");

            if (dAllowNull.get(i).equalsIgnoreCase("不允许为空"))
                sb.append(" not null");

            sb.append(" comment '").append(dName.get(i)).append("'");
            sb.append(",");
        }//endoffor
        sb.append("PRIMARY KEY  (`id`))");
        treeManager.operateTable(sb.toString());
        return SUCCESS;
    }//endof func

    public String treeDel() throws Exception {
        TreeNode treeNode = new TreeNode();
        treeNode.setId(id);
        treeNode = treeManager.deleteTreeNode(treeNode);
        treeManager.updateExpanded(treeNode.getParentId());

        if (!treeNode.getIsfolder().equalsIgnoreCase("1")) {
            String sql = "drop table t" + id + ";";
            treeManager.operateTable(sql);
        }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JSON(serialize = false)
    public TreeManager getTreeManager() {
        return treeManager;
    }

    public void setTreeManager(TreeManager treeManager) {
        this.treeManager = treeManager;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getRoot() {
        return root;
    }

    public List<MetaDataRow> getMList() {
        return mList;
    }

    public void setMList(List<MetaDataRow> list) {
        mList = list;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getExpanded() {
        return expanded;
    }

    public void setExpanded(String expanded) {
        this.expanded = expanded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getDName() {
        return dName;
    }

    public void setDName(List<String> name) {
        dName = name;
    }

    public List<String> getDType() {
        return dType;
    }

    public void setDType(List<String> type) {
        dType = type;
    }

    public List<Integer> getDLength() {
        return dLength;
    }

    public void setDLength(List<Integer> length) {
        dLength = length;
    }

    public List<String> getDAllowNull() {
        return dAllowNull;
    }

    public void setDAllowNull(List<String> allowNull) {
        dAllowNull = allowNull;
    }

    public String getOpenurl() {
        return openurl;
    }

    public void setOpenurl(String openurl) {
        this.openurl = openurl;
    }

    public String getIsfolder() {
        return isfolder;
    }

    public void setIsfolder(String isfolder) {
        this.isfolder = isfolder;
    }

    public List<String> getDNotes() {
        return dNotes;
    }

    public void setDNotes(List<String> notes) {
        dNotes = notes;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

}
