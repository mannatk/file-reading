package dna;

import java.io.*;


//
// Reads lines from a BufferedReader and builds a FastqReader.
//


public class FastqReader 
{
	private BufferedReader theBufferedReader;
	
	//ctor
	public FastqReader(BufferedReader theBufferedReader) {
		this.theBufferedReader = theBufferedReader;
	}
	// Returns next record in the file, or null if EOF.
	public FastqRecord readRecord() throws IOException, RecordFormatException
	{
		// Read the defline from the BufferedReader. Return null if you read null, 
		// indicating end of file.
		String one = theBufferedReader.readLine();
		if(one == null) {
			return null;
		}
		
		// Read the next 3 lines from the buffered reader. Construct and return
		// a FastqRecord.
		
		String d = theBufferedReader.readLine();
		theBufferedReader.readLine();
		String q = theBufferedReader.readLine();
		
		FastqRecord r = new FastqRecord(one, d, q);
		return r;
	}
}
