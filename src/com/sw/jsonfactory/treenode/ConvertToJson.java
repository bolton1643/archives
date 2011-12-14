package com.sw.jsonfactory.treenode;

import java.util.List;

import com.sw.pojo.TreeNodeRight;

public class ConvertToJson {

    public ConvertToJson() {
        super();
    }

    /**
     * 将list转换成json数据
     * 
     * @param list
     * @return
     */
    public static String ConverListToJsonAdmin(List<TreeNodeRight> treeNodes) {
        // JSONArray jsonArray = new JSONArray();
        // JSONObject jsonObject = null;
        // for (TreeNode treeNode : list) {
        // jsonObject = new JSONObject(treeNode);
        // jsonArray.put(jsonObject);
        // }
        // String json = jsonArray.toString();
        // return json;

        StringBuilder jsonString = new StringBuilder();
        jsonString.append("[");

        if (treeNodes.isEmpty())
            return "";
        int i = 0;
        for (TreeNodeRight treeNode : treeNodes) {
            if (i > 0) {
                jsonString.append(",");
            }
            jsonString.append(toJSONString(treeNode));
            i++;
        }

        jsonString.append("]");
        return jsonString.toString();
    }

    public static String ConverListToJson(List<TreeNodeRight> treeNodes) {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("[");

        if (treeNodes.isEmpty())
            return "";
        int i = 0;
        for (TreeNodeRight treeNode : treeNodes) {
            if("0".equals(treeNode.getIsfolder())){
                if("0".equals(treeNode.getRadd()) && "0".equals(treeNode.getRmodify()) && "0".equals(treeNode.getRdelete())
                        && "0".equals(treeNode.getRdownload()) && "0".equals(treeNode.getRprint()))
                    continue;
            }//end
            if (i > 0) {
                jsonString.append(",");
            }//endof i>0
            jsonString.append(toJSONString2(treeNode));
            i++;
        }//endoffor

        jsonString.append("]");
        return jsonString.toString();
    }

    public static String ConverListToJsonRight(List<TreeNodeRight> treeNodes) {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("[");

        if (treeNodes.isEmpty())
            return "";
        int i = 0;
        for (TreeNodeRight treeNode : treeNodes) {
            if (i > 0) {
                jsonString.append(",");
            }
            jsonString.append(toJSONStringRight(treeNode));
            i++;
        }

        jsonString.append("]");
        return jsonString.toString();
    }

    public static String toJSONString(TreeNodeRight tn) {
        StringBuilder opSb = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        sb.append(" {");
        sb.append("  \"text\":\"" + tn.getText() + "\"");

        // String url2 = "<a class='l-link'
        // href='javascript:f_addTab(listpage,列表页面,trGet.action?id=3)'>列表页面</a>";
        // sb.append(" \"text\":\"" + url2 + "\"");

        String url = "trEdit.action?id=" + tn.getId() + "&isfolder="
                + tn.getIsfolder();
        if (tn.getText().equalsIgnoreCase("系统管理")
                || tn.getText().equalsIgnoreCase("用户管理")
                || tn.getText().equalsIgnoreCase("权限管理"))
            url = tn.getOpenurl();

        sb.append(",  \"url\":\"" + url + "\"");
        sb.append(",  \"id\":\"" + tn.getId() + "\"");
        sb.append(",  \"pid\":\"" + tn.getParentId() + "\"");
        if (tn.getIsfolder() != null && tn.getIsfolder().equalsIgnoreCase("1")) {
            // sb.append(", \"children\":[{ \"text\": \"\" }]");
            sb.append(", \"isLeaf\":\"false\"");
        } else {
            sb.append(", \"isLeaf\":\"true\"");
        }
        sb.append(",  \"radd\":\"" + tn.getRadd() + "\"");
        sb.append(",  \"rdelete\":\"" + tn.getRdelete() + "\"");
        sb.append(",  \"rmodify\":\"" + tn.getRmodify() + "\"");
        sb.append(",  \"rdownload\":\"" + tn.getRdownload() + "\"");
        sb.append(",  \"rprint\":\"" + tn.getRprint() + "\"");

        sb.append(" }");
        // System.out.println(sb.toString());
        return sb.toString();

    }

    public static String toJSONString2(TreeNodeRight tn) {
        StringBuilder sb = new StringBuilder();
        sb.append(" {");
        sb.append("  \"text\":\"" + tn.getText() + "\"");

        String url = "dList.action?tid=t" + tn.getId() + "&radd="
                + tn.getRadd() + "&rdelete=" + tn.getRdelete() + "&rmodify="
                + tn.getRmodify() + "&rdownload=" + tn.getRdownload()
                + "&rprint=" + tn.getRprint();
        sb.append(",  \"url\":\"" + url + "\"");
        sb.append(",  \"id\":\"" + tn.getId() + "\"");
        sb.append(",  \"pid\":\"" + tn.getParentId() + "\"");
        if (tn.getIsfolder() != null && tn.getIsfolder().equalsIgnoreCase("1")) {
            // sb.append(", \"children\":[{ \"text\": \"\" }]");
            sb.append(", \"isLeaf\":\"false\"");
        } else {
            sb.append(", \"isLeaf\":\"true\"");
        }
        sb.append(",  \"radd\":\"" + tn.getRadd() + "\"");
        sb.append(",  \"rdelete\":\"" + tn.getRdelete() + "\"");
        sb.append(",  \"rmodify\":\"" + tn.getRmodify() + "\"");
        sb.append(",  \"rdownload\":\"" + tn.getRdownload() + "\"");
        sb.append(",  \"rprint\":\"" + tn.getRprint() + "\"");

        sb.append(" }");
        // System.out.println(sb.toString());
        return sb.toString();
    }

    public static String toJSONStringRight(TreeNodeRight tn) {
        StringBuilder opSb = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        sb.append(" {");

        if (tn.getIsfolder().equalsIgnoreCase("0")) {
            
            String rAdd = "";
            if (tn.getRadd().equalsIgnoreCase("1")) {
                rAdd = "增加<input type='checkbox' name='radd' checked value='"+tn.getId()+"'/>&nbsp;&nbsp;";
            } else {
                rAdd = "增加<input type='checkbox' name='radd' value='"+tn.getId()+"'/>&nbsp;&nbsp;";
            }

            String rModify = "";
            if (tn.getRmodify().equalsIgnoreCase("1")) {
                rModify = "修改<input type='checkbox' name='rmodify' checked value='"+tn.getId()+"'/>&nbsp;&nbsp;";
            } else {
                rModify = "修改<input type='checkbox' name='rmodify' value='"+tn.getId()+"'/>&nbsp;&nbsp;";
            }

            String rDelete = "";
            if (tn.getRdelete().equalsIgnoreCase("1")) {
                rDelete = "删除<input type='checkbox' name='rdelete' checked value='"+tn.getId()+"'/>&nbsp;&nbsp;";
            } else {
                rDelete = "删除<input type='checkbox' name='rdelete' value='"+tn.getId()+"'/>&nbsp;&nbsp;";
            }

            String rPrint = "";
            if (tn.getRprint().equalsIgnoreCase("1")) {
                rPrint = "打印<input type='checkbox' name='rprint' checked value='"+tn.getId()+"'/>&nbsp;&nbsp;";
            } else {
                rPrint = "打印<input type='checkbox' name='rprint' value='"+tn.getId()+"'/>&nbsp;&nbsp;";
            }

            String rDownload = "";
            if (tn.getRdownload().equalsIgnoreCase("1")) {
                rDownload = "下载<input type='checkbox' name='rdownload' checked value='"+tn.getId()+"'/>&nbsp;&nbsp;";
            } else {
                rDownload = "下载<input type='checkbox' name='rdownload' value='"+tn.getId()+"'/>&nbsp;&nbsp;";
            }

            sb.append("  \"text\":\"" + tn.getText() + "&nbsp;&nbsp;&nbsp;"
                    + rAdd + rModify + rDelete + rPrint + rDownload + "\"");
        } else {
            sb.append("  \"text\":\"" + tn.getText() + "\"");
        }

        String url = "dList.action?tid=t" + tn.getId() + "&radd="
                + tn.getRadd() + "&rdelete=" + tn.getRdelete() + "&rmodify="
                + tn.getRmodify() + "&rdownload=" + tn.getRdownload()
                + "&rpirnt" + tn.getRprint();
        sb.append(",  \"url\":\"" + url + "\"");
        sb.append(",  \"id\":\"" + tn.getId() + "\"");
        sb.append(",  \"pid\":\"" + tn.getParentId() + "\"");
        if (tn.getIsfolder() != null && tn.getIsfolder().equalsIgnoreCase("1")) {
            // sb.append(", \"children\":[{ \"text\": \"\" }]");
            sb.append(", \"isLeaf\":\"false\"");
        } else {
            sb.append(", \"isLeaf\":\"true\"");
        }
        sb.append(",  \"radd\":\"" + tn.getRadd() + "\"");
        sb.append(",  \"rdelete\":\"" + tn.getRdelete() + "\"");
        sb.append(",  \"rmodify\":\"" + tn.getRmodify() + "\"");
        sb.append(",  \"rdownload\":\"" + tn.getRdownload() + "\"");
        sb.append(",  \"rprint\":\"" + tn.getRprint() + "\"");

        sb.append(" }");
        // System.out.println(sb.toString());
        return sb.toString();
    }
}
