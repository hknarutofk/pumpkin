package xingkong.tool.core;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Mybatis 类型映射工具
 * 
 * @author xingkong
 *
 */
public class MybatisTypeTool {
    private static Map<Integer, String> typeMap = null;
    static {
        Map<Integer, String> initTypeMap = new HashMap<Integer, String>();
        initTypeMap.put(Types.ARRAY, "ARRAY");
        initTypeMap.put(Types.BIT, "BIT");
        initTypeMap.put(Types.TINYINT, "TINYINT");
        initTypeMap.put(Types.SMALLINT, "SMALLINT");
        initTypeMap.put(Types.INTEGER, "INTEGER");
        initTypeMap.put(Types.BIGINT, "BIGINT");

        initTypeMap.put(Types.FLOAT, "FLOAT");
        initTypeMap.put(Types.REAL, "REAL");
        initTypeMap.put(Types.DOUBLE, "DOUBLE");
        initTypeMap.put(Types.NUMERIC, "NUMERIC");
        initTypeMap.put(Types.DECIMAL, "DECIMAL");

        initTypeMap.put(Types.CHAR, "CHAR");
        initTypeMap.put(Types.VARCHAR, "VARCHAR");
        initTypeMap.put(Types.LONGVARCHAR, "LONGVARCHAR");
        initTypeMap.put(Types.DATE, "DATE");

        initTypeMap.put(Types.TIME, "TIME");
        initTypeMap.put(Types.TIMESTAMP, "TIMESTAMP");
        initTypeMap.put(Types.BINARY, "BINARY");

        initTypeMap.put(Types.VARBINARY, "VARBINARY");
        initTypeMap.put(Types.LONGVARBINARY, "LONGVARBINARY");
        initTypeMap.put(Types.NULL, "NULL");

        initTypeMap.put(Types.OTHER, "OTHER");

        initTypeMap.put(Types.BLOB, "BLOB");
        initTypeMap.put(Types.CLOB, "CLOB");
        initTypeMap.put(Types.BOOLEAN, "BOOLEAN");
        // Oracle
        initTypeMap.put(-10, "CURSOR");
        initTypeMap.put(Integer.MIN_VALUE + 1000, "UNDEFINED");
        // JDK6
        initTypeMap.put(Types.NVARCHAR, "NVARCHAR");
        // JDK6
        initTypeMap.put(Types.NCHAR, "NCHAR");
        // JDK6
        initTypeMap.put(Types.NCLOB, "NCLOB");
        initTypeMap.put(Types.STRUCT, "STRUCT");
        initTypeMap.put(Types.JAVA_OBJECT, "JAVA_OBJECT");
        initTypeMap.put(Types.DISTINCT, "DISTINCT");
        initTypeMap.put(Types.REF, "REF");
        initTypeMap.put(Types.DATALINK, "DATALINK");
        initTypeMap.put(Types.ROWID, "ROWID");
        initTypeMap.put(Types.LONGNVARCHAR, "LONGNVARCHAR");
        initTypeMap.put(Types.SQLXML, "SQLXML");
        // JDK6
        initTypeMap.put(Types.LONGNVARCHAR, "LONGNVARCHAR");
        initTypeMap.put(-155, "DATETIMEOFFSET");

        typeMap = initTypeMap;

    }

    public static String forCode(int code) {
        return typeMap.get(code);
    }

}
