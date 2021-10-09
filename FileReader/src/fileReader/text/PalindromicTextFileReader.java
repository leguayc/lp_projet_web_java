package fileReader.text;

import java.io.IOException;

public class PalindromicTextFileReader extends TextFileReader {

	public PalindromicTextFileReader() {
		super();
	}

	/**
	 * Read file in palindromic way
	 */
	@Override
	public void readFile() {
		String line = null;
		StringBuilder builder = new StringBuilder();
        
        try {
			while((line = this.reader.readLine())!= null) {
				this.lines.add(line);
				System.out.println(line);
				builder.append(line);
				builder.append("\n");
			}
			
			builder.reverse();
			System.out.println(builder);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
