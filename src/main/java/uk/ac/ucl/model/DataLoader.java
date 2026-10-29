package uk.ac.ucl.model;

import org.apache.commons.csv.*;
import java.io.*;
import java.nio.file.*;

public class DataLoader 
{
    public static DataFrame Load(String filePath)
    {
        DataFrame dataFrame = new DataFrame();

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()))
        {
            // Add columns to the DataFrame
            for (String header : csvParser.getHeaderMap().keySet())
            {
                dataFrame.addColumn(new Column(header));
            }

            // Add rows to the DataFrame
            for (CSVRecord csvRecord : csvParser)
            {
                for (String header : csvParser.getHeaderMap().keySet())
                {
                    String value = csvRecord.get(header);
                    dataFrame.columns.stream()
                            .filter(column -> column.getName().equals(header))
                            .findFirst()
                            .ifPresent(column -> column.addRowValue(value));
                }
            }
        }
        catch (IOException e)
        {
            return null;
        }
        return dataFrame;
    }
}