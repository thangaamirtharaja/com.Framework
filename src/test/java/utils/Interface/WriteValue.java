package utils.Interface;

import utils.Data_Read_Write.*;

public interface WriteValue {
    YamlDataWriter yamldatawriter= new YamlDataWriter();
    JsonDataWriter jsondatawriter= new JsonDataWriter();
    PropertiesWriter propertieswriter= new PropertiesWriter();
    ExcelSQLWriter excelsqlwriter = new ExcelSQLWriter();
    ExcelMatrixWriter excelmatrixwriter= new ExcelMatrixWriter();
    void writeValue(String section, String key, String subKey, String folderDotFile, String value);
}
