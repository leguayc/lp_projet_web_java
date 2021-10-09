package fileReader.text;

import java.io.IOException;
import java.util.ArrayList;

public class DiffTextFileReader extends TextFileReader {
	private int countFileFinished; 

	public DiffTextFileReader() {
		super();
	}
	
	/**
	 * Read files and do difference between two
	 */
	@Override
	public void readFile() {
		String line = null;
		StringBuilder builder = new StringBuilder();
		ArrayList<String> queueFile1 = new ArrayList<String>(); // Used to stock the different lines of the file 1
		ArrayList<String> queueFile2 = new ArrayList<String>(); // Used to stock the different lines of the file 2
		boolean isDifferent = false; // Check if it was a different line before or not
        
		// If it's an even file, it's considered as the first file, so we clear the saved lines
		if(countFileFinished%2 == 0) {
			this.lines.clear();
		}
		
        try {
        	int i = 0;
			while((line = this.reader.readLine())!= null) {
				// If this is an even file, it's considered as the first file
				if (countFileFinished%2 == 0) {
					this.lines.add(line);
				}
				else {
					if(i < this.lines.size()) {
						// We check if the lines are different
						if(this.lines.get(i).compareTo(line) != 0) {
							queueFile1.add("- " + this.lines.get(i) + "\n");
							queueFile2.add("+ " + line + "\n");
							
							isDifferent = true;
						}
						else {
							// If there was different lines before, we build all of them before building the current line
							if (isDifferent) {
								for(int j = 0; j < queueFile1.size(); j++) {
									builder.append(queueFile1.get(j));
								}
								
								for(int j = 0; j < queueFile2.size(); j++) {
									builder.append(queueFile2.get(j));
								}
								
								queueFile1.clear();
								queueFile2.clear();
							}
							
							builder.append("~ " + this.lines.get(i) + "\n");
							
							isDifferent = false;
						}
						i++;
					}
					else {
						// If there was more lines in file 2
						
						// If there was different lines before, we build all of them before building the current line
						if (isDifferent) {
							for(int j = 0; j < queueFile1.size(); j++) {
								builder.append(queueFile1.get(j));
							}
							
							for(int j = 0; j < queueFile2.size(); j++) {
								builder.append(queueFile2.get(j));
							}
							
							queueFile1.clear();
							queueFile2.clear();
							isDifferent = false;
						}
						
						builder.append("+ " + line + "\n");
					}
					
				}
			}
			// If there is multiple files
			if(countFileFinished%2 != 0) {
				if (isDifferent) {
					// If there was different lines, we build all of them
					for(int j = 0; j < queueFile1.size(); j++) {
						builder.append(queueFile1.get(j));
					}
					
					for(int j = 0; j < queueFile2.size(); j++) {
						builder.append(queueFile2.get(j));
					}
				}
				
				// If there was more lines in file 1
				while(i < this.lines.size()) {
					builder.append("- " + this.lines.get(i) + "\n");
					i++;
				}
			}
			
			// We display the diff between the two files and count one file read
			System.out.println(builder);
			this.countFileFinished++;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
