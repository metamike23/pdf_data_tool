package belto_java_codebase.pdf.base64;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class App {
	
	// Get the project directory
	String output_folder_path = System.getProperty("user.dir") + File.separator + "target" + File.separator + "output" + File.separator;
	

	public static void main(String[] args) throws IOException {
		
		
		String fileName = "fulldemo2.pdf";
		
	

		System.out.println("\nFields and Values as a JSON object\n");
		
		PDFToText pdf2txt = new PDFToText(fileName);
		pdf2txt.parseText();
		String txt = pdf2txt.getUnstructuredText();
		pdf2txt.extractFormData();
		
		Map<String, String> key_value_pairs = pdf2txt.getKeyValuePairs();
		
		FormData fd = new FormData(key_value_pairs, txt);
		fd.exportToJSON();
				
				
				
				
			
			
	
		
		
		

	}

}

// This piece of text represents the logo for fafsa on the first page
// The Federal Student Aid logo and FAFSA are registered trademarks of Federal Student Aid, U.S. Department of Education.