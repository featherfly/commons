
package cn.featherfly.common.db.data.format;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.data.DataFormat;
import cn.featherfly.common.db.data.ExportException;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.lang.Dates;

/**
 * <p>
 * JsonDataFormat
 * </p>
 * .
 *
 * @author zhongj
 */
public class JsonDataFormat implements DataFormat {

    /** The generator. */
    private JsonGenerator generator;
    //	private JsonParser parser;

    /**
     * Instantiates a new json data format.
     *
     * @param writer the writer
     */
    public JsonDataFormat(Writer writer) {
        generator = createJsonGenerator(writer);
    }
    //	/**
    //	 */
    //	public JsonDataFormat(Reader reader) {
    //		parser = createJsonParser(reader);
    //	}
    //	/**
    //	 */
    //	public JsonDataFormat(Writer writer, Reader reader) {
    //		generator = createJsonGenerator(writer);
    //		parser = createJsonParser(reader);
    //	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeDataStart(DatabaseMetadata databaseMetadata) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("name", databaseMetadata.getName());
        generator.writeArrayFieldStart("tables");
    }

    /**
     * {@inheritDoc}
     *
     * @throws IOException IOException
     */
    @Override
    public void writeDataEnd(DatabaseMetadata databaseMetadata) throws IOException {
        generator.writeEndArray();
        generator.writeEndObject();
        generator.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeTableStart(Table tableMetadata) throws Exception {
        generator.writeStartObject();
        generator.writeStringField("name", tableMetadata.getName());
        StringBuilder pkMapping = new StringBuilder();
        for (Column column : tableMetadata.getColumns()) {
            if (column.isPrimaryKey()) {
                pkMapping.append(column.getName()).append(",");
            }
        }
        //        for (ColumnMetadata pkColumn : tableMetadata.getPrimaryColumns()) {
        //            pkMapping.append(pkColumn.getName()).append(",");
        //        }
        if (pkMapping.length() > 0) {
            pkMapping.deleteCharAt(pkMapping.length() - 1);
        }
        generator.writeStringField("pkMapping", pkMapping.toString());
        generator.writeArrayFieldStart("rows");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeTableEnd(Table tableMetadata) throws Exception {
        generator.writeEndArray();
        generator.writeEndObject();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeRow(Table tableMetadata, ResultSet res) throws Exception {
        ResultSetMetaData rsmd = res.getMetaData();
        int columnTotal = rsmd.getColumnCount();
        generator.writeStartObject();
        for (int i = 1; i <= columnTotal; i++) {
            String columnName = rsmd.getColumnName(i);
            int type = rsmd.getColumnType(i);
            Object value = res.getObject(columnName);
            generator.writeObjectFieldStart(columnName);
            generator.writeStringField("type", type + "");
            if (value != null) {
                generator.writeStringField("null", "0");
                switch (type) {
                    case Types.TIMESTAMP:
                        generator.writeStringField("value", Dates.formatTime(res.getTimestamp(columnName)));
                        break;
                    default:
                        generator.writeStringField("value", value.toString());
                        break;
                }
            } else {
                generator.writeStringField("null", "1");
            }
            generator.writeEndObject();
        }
        generator.writeEndObject();
    }

    // ********************************************************************
    // private method
    // ********************************************************************

    /**
     * Creates the json generator.
     *
     * @param writer the writer
     * @return the json generator
     */
    private JsonGenerator createJsonGenerator(Writer writer) {
        try {
            return new JsonFactory().createGenerator(writer);
        } catch (IOException e) {
            throw new ExportException(e);
        }
    }
    //	private JsonParser createJsonParser(Reader writer) {
    //		try {
    //			return new JsonFactory().createParser(writer);
    //		} catch (IOException e) {
    //			throw new ImportException(e);
    //		}
    //	}
}
