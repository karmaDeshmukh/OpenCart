package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider1
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path = ".\\testData\\Opencart_LoginData.xlsx"; //taking Excel file from testData
		
		ExcelUtility objxlUtil =new ExcelUtility(path);//creating an object for XLutility
		
		
		int totalrows=objxlUtil.getRowCount("Sheet1");
		int totalcols=objxlUtil.getCellCount("Sheet1",1);
		System.out.println("Total rows: " + totalrows); // Add this line
	    System.out.println("Total columns: " + totalcols);
		
		String Logindata[][] =new String[totalrows][totalcols];//created for two dimension array which can store 
		
		for(int i=1;i<=totalrows;i++) { //read the data from XL storing in two dimensional array
			for(int j=0;j<totalcols;j++) { //i is row and j is COL
				 System.out.println("i: " + i + ", j: " + j);
				Logindata[i-1][j]=objxlUtil.getCellData("Sheet1", i, j); //1.0
			}
		}
		return Logindata; //returning two dimension array
	}
}
