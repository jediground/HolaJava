package com.foobar.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class ConversionStream {

	public ConversionStream() {
		
	}
	
	@Test
	public void test1() throws IOException {
		File inFile = new File("Objc.md");
		FileInputStream fis = new FileInputStream(inFile);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		File outFile = new File("objc_cp.md");
		FileOutputStream fos = new FileOutputStream(outFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		
		String str;
		for (; null != (str = br.readLine());) {
			bw.write(str);
			bw.newLine();
			bw.flush();
		}
		
		bw.close();
		br.close();
	}
	
	@Test
	public void test2() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please input text: ");
		String readString;
		for (; !(readString= br.readLine()).equalsIgnoreCase("bye");) {
			System.out.println(readString.toUpperCase() + "\n");
		}
		br.close();
		System.out.println("Bye-bye!");
	}
}
