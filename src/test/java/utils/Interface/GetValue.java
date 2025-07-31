package utils.Interface;

import utils.Data_Read_Write.*;

public interface GetValue{
    YamlDataReader Yamlreader = new YamlDataReader();
    JsonDataReader jsondatareader = new JsonDataReader();
    PropertiesReader propertiesreader = new PropertiesReader();
    ExcelSQLQueryUtils excelsqlqueryutils = new ExcelSQLQueryUtils();
    ExcelMatrixReader excelmatrixreader = new ExcelMatrixReader();
    String getValue(String section, String key, String subKey, String fileDotPath);

}
