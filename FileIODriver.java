
public class FileIODriver {

	/**
	 * A simple driver to open existing files and create output files based on the methods being called.
	 * Make sure all files are in your Project directory, that way you won't need a file path to open all files.
	 * Also, remember to hit F5 to refresh your Project folder so your created files show up.
	 * 
	 * While the files I provided for stretch, sortNumbers, and wordCount should be sufficient to test your methods,
	 * mergeFileContents may need a little more testing on your part.  So you may have to create some extra text files to test this method further.
	 */
	
	public static void main(String[] args) {

		FileIO.stretch("Stretch.txt", "StretchOutput.txt");
		FileIO.sortNumbers("Numbers.txt", "NumbersOutput.txt");
		FileIO.wordCount("WordCount.txt","WordCountOutput.txt");
		FileIO.mergeFileContents("merge1.txt", "merge2.txt", "mergeOutput.txt");
	}
}
