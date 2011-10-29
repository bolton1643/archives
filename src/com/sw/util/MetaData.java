package com.sw.util;

import java.util.List;

public interface MetaData {
	public boolean operateTable(String sql);

	public List<MetaDataRow> getMetaData(String tName);

}
