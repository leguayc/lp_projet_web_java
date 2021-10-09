package fileReader.text;

import java.io.IOException;

public class ClassicTextFileReader extends TextFileReader {
	public ClassicTextFileReader() {
		super();
	}
	
	/**
	 * Read the file top to bottom
	 */
	@Override
	public void readFile() {
		String line = null;
		this.lines.clear();
		
        try {
			while((line = this.reader.readLine())!= null) {
				this.lines.add(line);
			    System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
