package uk.ac.ucl.model;

import java.util.ArrayList;
import java.util.List;

public class Model
{
  public DataFrame dataFrame;

  public Model(String filePath)
  {
    this.dataFrame = DataLoader.Load(filePath);
  }

  public DataFrame searchFor(String keyword)
  {
    ArrayList<Integer> results = new ArrayList<>();
    int size = dataFrame.getRowCount();
    for (Column column : dataFrame.columns)
    {
      for (int i = 0; i < size; i++)
      {
        if (column.getRowValue(i).contains(keyword))
        {
          if(!results.contains(i))
            results.add(i);
        }
      }
    }
    
    DataFrame filteredDataFrame = new DataFrame();
    for (Column column : dataFrame.columns)
    {
      String name = column.getName();
      Column columnToAdd = new Column(name);
      for (int index : results)
      {
        columnToAdd.addRowValue(column.getRowValue(index));
      }
      filteredDataFrame.addColumn(columnToAdd);
    }
    return filteredDataFrame;
  }
}
