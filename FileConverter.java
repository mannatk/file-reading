package dna;

import java.io.*;
import java.util.*;


public class FileConverter 
{
	private File fastq;
	private File fasta;
	
	//ctor
	public FileConverter(File fastq, File fasta) {
		this.fastq = fastq;
		this.fasta = fasta;
	}
	
	//
	// Writes a fasta file consisting of conversion of all records from the fastq with
	// sufficient quality and unique defline.
	//
	// Use a HashSet<String> to check for unique deflines. When you read a fastq record,
	// check if its defline is in the set. If it's in the set, don't do anything with the
	// record. If the defline isn't in the set, add it to the set, build a fasta record,
	// and write the fasta record using the fasta writer.
	//
	
	public void convert() throws IOException
	{
		// Build chain of readers.
		FileReader fr = new FileReader(fastq);
		BufferedReader br = new BufferedReader(fr);
		FastqReader fqr = new FastqReader(br);

		// Build chain of writers.
		FileWriter fw = new FileWriter(fasta);
		PrintWriter pw = new PrintWriter(fw);
		FastaWriter faw = new FastaWriter(pw);
		
		// Read, translate, write.
		HashSet<String> records = new HashSet<String>();
		FastqRecord tem = null; 
		
		try {
			tem = fqr.readRecord();
		}
		catch (RecordFormatException x) {
			
		}
			
		while(tem != null) {
			if(tem.qualityIsHigh() && records.add(tem.getDefline())) {
				faw.writeRecord(new FastaRecord(tem));
			}
			try {
				tem = fqr.readRecord();
			}
			
			catch (RecordFormatException x) {
				
			}
		}
		
		// Close fr, br, fw, and pw in reverse order of creation.
		
		br.close();
		fr.close();
		
		pw.close();
		fw.close();
	}
	
		
	
	public static void main(String[] args)
	{
		System.out.println("Starting");
		try
		{
			File fastq = new File("data/HW4.fastq");
			if (!fastq.exists())
			{
				System.out.println("Can't find input file " + fastq.getAbsolutePath());
				System.exit(1);
			}
			File fasta = new File("data/HW4.fasta");
			FileConverter converter = new FileConverter(fastq, fasta);
			converter.convert();
		}
		catch (IOException x)
		{
			System.out.println(x.getMessage());
		}
		System.out.println("Done");
	}
}
