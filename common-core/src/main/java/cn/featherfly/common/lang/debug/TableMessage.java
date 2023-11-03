package cn.featherfly.common.lang.debug;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;

/**
 * The Class TableMessage.
 *
 * @author zhongj
 * @since 1.12.0
 */
public class TableMessage {

    private class Column {

        private Column(String title) {
            super();
            this.title = title;
            length = title.length();
        }

        private String title;

        private int length;
    }

    private Set<Column> columns = new LinkedHashSet<>();

    private List<Map<String, Object>> rows = new ArrayList<>();

    private String columnHeaderDelimiter = " || ";

    private String columnDelimiter = "    ";

    private boolean warpAround = true;

    /**
     * Instantiates a new table debug message.
     */
    protected TableMessage() {
    }

    /**
     * Instantiates a new table debug message.
     *
     * @param columns the columns
     */
    public TableMessage(Set<String> columns) {
        addColumn(columns);
    }

    /**
     * Adds the column.
     *
     * @param column the column
     * @return the table message
     */
    public TableMessage addColumn(String column) {
        columns.add(new Column(column));
        return this;
    }

    /**
     * Adds the column.
     *
     * @param columns the columns
     * @return the table message
     */
    public TableMessage addColumn(String... columns) {
        Lang.each(columns, (c, i) -> this.columns.add(new Column(c)));
        return this;
    }

    /**
     * Adds the column.
     *
     * @param columns the columns
     * @return the table message
     */
    public TableMessage addColumn(Collection<String> columns) {
        Lang.each(columns, (c, i) -> this.columns.add(new Column(c)));
        return this;
    }

    /**
     * Adds the row.
     *
     * @param row the row
     * @return the table message
     */
    public TableMessage addRow(Map<String, Object> row) {
        rows.add(row);
        for (Column column : columns) {
            Object value = row.computeIfAbsent(column.title, k -> "");
            int len = value.toString().length();
            if (len > column.length) {
                column.length = len;
            }
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String format = rowFormat(columnDelimiter);
        StringBuilder dm = new StringBuilder();
        String header = String.format(rowFormat(columnHeaderDelimiter),
                columns.stream().map(column -> column.title).toArray());
        String warpLine = "";
        if (warpAround) {
            warpLine = Strings.join("=", header.length() - 1) + "\n"; // -1 是去掉\n
        }
        dm.append(warpLine).append(header);
        for (Map<String, Object> row : rows) {
            dm.append(String.format(format,
                    columns.stream().map(column -> row.computeIfAbsent(column.title, k -> "")).toArray()));
        }
        return dm.append(warpLine).toString();
    }

    private String rowFormat(String delimiter) {
        StringBuilder fb = new StringBuilder();
        if (warpAround) {
            fb.append("| ");
        } else {
            fb.append("  ");
        }
        for (Column column : columns) {
            fb.append("%-").append(column.length).append("s").append(delimiter);
        }
        if (!columns.isEmpty()) {
            fb.delete(fb.length() - delimiter.length(), fb.length());
        }
        if (warpAround) {
            fb.append(" |");
        }
        fb.append("\n");
        return fb.toString();
    }

    /**
     * get columns value.
     *
     * @return columns
     */
    public Collection<String> getColumns() {
        return columns.stream().map(c -> c.title).collect(Collectors.toList());
    }

    /**
     * get rows value.
     *
     * @return rows
     */
    public List<Map<String, Object>> getRows() {
        return new ArrayList<>(rows);
    }

    /**
     * get columnHeaderDelimiter value.
     *
     * @return columnHeaderDelimiter
     */
    public String getColumnHeaderDelimiter() {
        return columnHeaderDelimiter;
    }

    /**
     * set columnHeaderDelimiter value.
     *
     * @param columnHeaderDelimiter columnHeaderDelimiter
     */
    public void setColumnHeaderDelimiter(String columnHeaderDelimiter) {
        this.columnHeaderDelimiter = columnHeaderDelimiter;
    }

    /**
     * get columnDelimiter value.
     *
     * @return columnDelimiter
     */
    public String getColumnDelimiter() {
        return columnDelimiter;
    }

    /**
     * set columnDelimiter value.
     *
     * @param columnDelimiter columnDelimiter
     */
    public void setColumnDelimiter(String columnDelimiter) {
        this.columnDelimiter = columnDelimiter;
    }

    /**
     * get warpAround value
     *
     * @return warpAround
     */
    public boolean isWarpAround() {
        return warpAround;
    }

    /**
     * set warpAround value
     *
     * @param warpAround warpAround
     */
    public void setWarpAround(boolean warpAround) {
        this.warpAround = warpAround;
    }
}