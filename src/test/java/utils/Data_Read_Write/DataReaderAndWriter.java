package utils.Data_Read_Write;

import utils.Interface.DataReadAndWrite;
import utils.Interface.GetValue;
import utils.Interface.WriteValue;

public abstract class DataReaderAndWriter implements DataReadAndWrite, GetValue, WriteValue {

    public String readvalue(String type, Object... args) {
        return switch (type.toLowerCase()) {
            case "yaml" -> Yamlreader.getValue((String) args[0], (String) args[1], (String) args[2], (String) args[3]);
            case "json" ->  jsondatareader.getValue((String) args[0], (String) args[1], (String) args[2], (String) args[3]);
            case "properties" -> propertiesreader.getValue(null, (String) args[1], null, (String) args[3]);
            case "excel_sql" ->
                    excelsqlqueryutils.getValue((String) args[0], (String) args[1], (String) args[2], (String) args[3]);
            case "excel_matrix" ->
                    excelmatrixreader.getValue((String) args[0], (String) args[1], (String) args[2], (String) args[3]);
            default -> throw new IllegalArgumentException("Unsupported type: " + type);
        };
    }

    public void writevalue(String type, Object... args) {
        switch (type.toLowerCase()) {
            case "yaml" ->
                    yamldatawriter.writeValue((String) args[0], (String) args[1], (String) args[2], (String) args[3], (String) args[4]);
            case "json" ->
                    jsondatawriter.writeValue((String) args[0], (String) args[1], (String) args[2], (String) args[3], (String) args[4]);
            case "properties" ->
                    propertieswriter.writeValue(null, (String) args[1], null, (String) args[3], (String) args[4]);
            case "excel_sql" ->
                    excelsqlwriter.writeValue((String) args[0], (String) args[1], (String) args[2], (String) args[3], (String) args[4]);
            case "excel_matrix" ->
                    excelmatrixwriter.writeValue((String) args[0], (String) args[1], (String) args[2], (String) args[3], (String) args[4]);
            default -> throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}