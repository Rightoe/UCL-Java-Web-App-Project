package uk.ac.ucl.model;
import java.util.ArrayList;

public class Column
{
    public String name;

    public ArrayList<String> values;

    public Column(String name)
    {
        this.name = name;
        this.values = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public int getSize()
    {
        return values.size();
    }

    public String getRowValue(int row)
    {
        return values.get(row);
    }

    public void setRowValue(int row, String value)
    {
        values.set(row, value);
    }

    public void addRowValue(String value)
    {
        values.add(value);
    }
}
