package fileReader.text;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.IOException;

import fileReader.FileReader;

public abstract class TextFileReader implements FileReader {
	protected BufferedReader reader;
	protected ArrayList<String> lines;
	
	public TextFileReader() {
		this.reader = null;
		this.lines = new ArrayList<String>();
	}
	
	/**
	 * Open file at filePath if it's a text file, then read it and close it
	 * 
	 * @param String filePath
	 */
	public void openFile(String filePath) {
		try {
			if (filePath.endsWith(".txt")) {
				this.reader = Files.newBufferedReader(Paths.get(filePath));
				this.readFile();
			} else {
				System.out.println("Error : This is not a text file");
				return;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (this.reader != null) {
				this.closeFile();
			}
		}
	}
	
	/**
	 * Close file
	 */
	public void closeFile() {
		try {
			this.reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
