package com.bwzy.company.tostring;
import java.io.File;
import java.io.IOException;

public class TestOCR {


	public String toString(String path) {
		String valCode = "";
		//String path = "E:\\5656.jpg";   
        try {   
            valCode = new OCR().recognizeText(new File(path), "jpg");   
        } catch (IOException e) {
            e.printStackTrace();   
        } catch (Exception e) {
			e.printStackTrace();
		} 
        return valCode;
	}
	

}
