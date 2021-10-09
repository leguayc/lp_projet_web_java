package fileReader.text;

import java.io.IOException;

public class ReverseTextFileReader extends TextFileReader {

	public ReverseTextFileReader() {
		super();
	}

	/**
	 * Read file bottom to top
	 */
	@Override
	public void readFile() {
		String line = null;
		this.lines.clear();
		
        try {
			while((line = this.reader.readLine())!= null) {
				this.lines.add(line);
			}
			
			for(int i = this.lines.size()-1; i >= 0; i--) {
				System.out.println(lines.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
