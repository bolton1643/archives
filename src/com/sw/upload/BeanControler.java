package com.sw.upload;

/**
 * <p>Title: �������</p>
 *
 * <p>Description: ��Ҫ�����Ƕ�FileUploadStatus���й��?Ϊ�ͻ����ṩ��Ӧ��
 * FileUploadStatus���������һ�������ࡣ</p>
 *
 */
import java.util.Vector;

public class BeanControler {
    private static BeanControler beanControler = new BeanControler();
    private Vector vector = new Vector();
    private BeanControler() {
    }

    public static BeanControler getInstance() {
        return beanControler;
    }

    /**
     * ȡ����ӦFileUploadStatus�����Ĵ洢λ��
     */
    private int indexOf(String strID) {
        int nReturn = -1;
        for (int i = 0; i < vector.size(); i++) {
            FileUploadStatus status = (FileUploadStatus) vector.elementAt(i);
            if (status.getUploadAddr().equals(strID)) {
                nReturn = i;
                break;
            }
        }
        return nReturn;
    }
    /**
     * ȡ����ӦFileUploadStatus�����
     */
    public FileUploadStatus getUploadStatus(String strID) {
        return (FileUploadStatus) vector.elementAt(indexOf(strID));
    }
    /**
     * �洢FileUploadStatus�����
     */
    public void setUploadStatus(FileUploadStatus status) {
        int nIndex = indexOf(status.getUploadAddr());
        if ( -1 == nIndex) {
            vector.add(status);
        } else {
            vector.insertElementAt(status, nIndex);
            vector.removeElementAt(nIndex + 1);
        }
    }
    /**
     * ɾ��FileUploadStatus�����
     */
    public void removeUploadStatus(String strID){
        int nIndex = indexOf(strID);
        if(-1!=nIndex)
            vector.removeElementAt(nIndex);
    }
}
