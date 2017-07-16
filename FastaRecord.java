package dna;


public class FastaRecord implements DNARecord 
{	
	private String defline;
	private String sequence;
	
	//
	// Add a precondition check: throw FastqException if the 1st char of the defline is 
	// not '>'. You will have to change the ctor declaration to say that it throws
	// the exception. The exception should contain a useful informative message.
	//
	//ctor
	public FastaRecord(String defline, String sequence) throws RecordFormatException
	{
		if(defline.charAt(0) != '>') {
			throw new RecordFormatException("Bad 1st char in defline in fasta record: Saw" + defline.charAt(0) + " expected >");
		}
		this.defline = defline;
		this.sequence = sequence;
	}
	
	
	// Initialize defline and sequence from the input record. The defline should be the
	// defline of the fastq record, but with a '>' in the first position rather than a '@'.
	//ctor
	public FastaRecord(FastqRecord fastqRec)
	{
		this.defline = ">" + fastqRec.getDefline().substring(1);
		this.sequence = fastqRec.getSequence();
		
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
	// Provide an equals() method. 
	//
	//checks to see that both FastaRecor are deep equal to each other
	public boolean equals(Object x) {
		FastaRecord that = (FastaRecord) x;
		return this.defline.equals(that.defline) && this.sequence.equals(that.sequence);
	}

	
	//
	// Provide a toString () method. 
	//
	//returns the variables in string form
	public String toString()
	{
		return this.defline + "\n" + this.sequence + "\n";
	}

	//
	// Provide a hashCode() method that returns the sum of the hashcodes of 
	// defline and sequence.
	//
	public int hashCode() {
		return this.defline.hashCode() + this.sequence.hashCode();
	}
}
