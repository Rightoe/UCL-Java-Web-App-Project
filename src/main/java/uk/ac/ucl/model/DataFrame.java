package uk.ac.ucl.model;
import java.util.ArrayList;

public class DataFrame 
{
    public ArrayList<Column> columns;

    public DataFrame()
    {
        this.columns = new ArrayList<>();
    }

    public void addColumn(Column column)
    {
        columns.add(column);
    }

    public String[] getColumnNames()
    {
        String[] columnNames = new String[columns.size()];
        for (int i = 0; i < columns.size(); i++)
        {
            columnNames[i] = columns.get(i).getName();
        }
        return columnNames;
    }

    public int getRowCount()
    {
        if (columns.size() == 0)
        {
            return 0;
        }
        return columns.get(0).getSize();
    }

    public String getValue(String columnName, int row)
    {
        int column = getColumnIndex(columnName);
        if (column == -1)
        {
            throw new IllegalArgumentException("Column not found: " + columnName);
        }
        return columns.get(column).getRowValue(row);
    }

    private int getColumnIndex(String columnName)
    {
        for (int i = 0; i < columns.size(); i++)
        {
            if (columns.get(i).getName().equals(columnName))
            {
                return i;
            }
        }
        return -1;
    }

    public void putValue(String columnName, int row, String value)
    {
        int column = getColumnIndex(columnName);
        if (column == -1)
        {
            throw new IllegalArgumentException("Column not found: " + columnName);
        }
        columns.get(column).setRowValue(row, value);
    }

    public void addValue(String columnName, String value)
    {
        int column = getColumnIndex(columnName);
        if (column == -1)
        {
            throw new IllegalArgumentException("Column not found: " + columnName);
        }
        columns.get(column).addRowValue(value);
        for (Column c : columns) 
        {
            if(!c.getName().equals(columnName))
                c.addRowValue("");
        }
    }
}
