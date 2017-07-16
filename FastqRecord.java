package dna;

//
// FastqRecord contains the defline, sequence, and quality string
// from a record in a fastq file.
//

public class FastqRecord implements DNARecord
{
	
	private String defline;
	private String sequence;
	private String quality;
	
	//
	// Add a precondition check: throw FastqException if the 1st char of the defline is 
	// not '@'. You will have to change the ctor declaration to say that it throws
	// the exception. The exception should contain a useful informative message.
	//
	
	//ctor
	public FastqRecord(String defline, String sequence, String quality) throws RecordFormatException
	{
		if(defline.charAt(0) != '@') {
			throw new RecordFormatException("Bad 1st char in defline in fastq record: Saw" + defline.charAt(0) + " expected @");
		}
		this.defline = defline;
		this.sequence = sequence;
		this.quality = quality;
	}
	
	
	// 
	// Provide the 2 methods that satisfy the interface.
	//

	//returns the variable defline
	public String getDefline() {
		return defline;
	}

	//returns the variable sequence
	public String getSequence() {
		return sequence;
	}
	
	//
	// Provide an equals() method that checks for deep equality of all 3 instance variables. 
	// When checking string variables, be sure to do it like this:  
	//              this.defline.equals(that.defline) 
	// and not like this:  
	//              this.defline == that.defline
	//
	
	
	
	public boolean equals(Object x) {
		FastqRecord that = (FastqRecord) x;
		return this.defline.equals(that.defline) && this.sequence.equals(that.sequence) && this.quality.equals(that.quality);
	}
	
	//
	// Provide a hashCode() method that returns the sum of the hashcodes of 
	// defline, sequence, and quality.
	//
	public int hashCode() {
		return this.defline.hashCode() + this.sequence.hashCode() + this.quality.hashCode();
	}
	
	//
	// Complete this. Return true if quality contains at least 3 '!' chars.
	//
	public boolean qualityIsHigh()
	{
		int counter = 0;
		for(int i = 0; i < quality.length(); i++) {
			if(quality.charAt(i) == '!') {
				counter++;
			}
		}
		return counter >= 3;
	}
	

	//
	// Complete this.
	//
	//returns the variables in string form
	public String toString()
	{
		return this.defline + "\n" + this.sequence + "\n + \n" + this.quality + "\n";
	}
}
