package walshIntegrated.factories;

public class DataProviderFactory {

	
	public static ExcelProvider getExcel()
	{
		ExcelProvider excel=new ExcelProvider();
		
		return excel;
	}
	
	public static ConfigProvider getConfig()
	{
		ConfigProvider config=new ConfigProvider();
		
		return config;
	}
	
	
	
}
