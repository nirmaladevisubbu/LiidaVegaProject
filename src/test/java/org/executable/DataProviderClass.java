package org.executable;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.utilities.BaseClass;

public class DataProviderClass extends BaseClass {

	@DataProvider(name = "Liida data")
	public Object[][] data() throws IOException {

		return new Object[][] {
				{ excelData(1, 10),excelData(1,11),excelData(1, 0), excelData(1, 1), excelData(1, 2), excelData(1, 3), excelData(1, 4), excelData(1, 5),
						excelData(1, 6), excelData(1, 7), excelData(1, 8), excelData(1, 9) },
				{ excelData(2, 0), excelData(2, 1), excelData(2, 2), excelData(2, 3), excelData(2, 4), excelData(2, 5),
						excelData(2, 6), excelData(2, 7), excelData(2, 8), excelData(2, 9) },
				{ excelData(3, 0), excelData(3, 1), excelData(3, 2), excelData(3, 3), excelData(3, 4), excelData(3, 5),
						excelData(3, 6), excelData(3, 7), excelData(3, 8), excelData(3, 9) }

		};
	}
}
