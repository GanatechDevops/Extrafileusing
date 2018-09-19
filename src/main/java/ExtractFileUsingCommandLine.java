import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
 
public class ExtractFileUsingCommandLine {
	
	public static void main(String args[])
	{
	
		/*
		 * This program expects two command line arguments
		 * 
		 * 1. Path of zip file
		 * 2. Destination path for extracted file
		 */
		 
		 //fetch command line arguments
		 String strZipFile = args[0];
		String strDestinationPath = args[1];
		 
		 if(strZipFile == null || strZipFile.equals(""))
		 {
		 	System.out.println("Invalid source file");
		 	System.exit(0);
		 }
		 
		 if(strDestinationPath == null || strDestinationPath.equals(""))
		 {
		 	System.out.println("Invalid destination path");
			System.exit(0);
		 }
		
		try
		{
			//create FileInputStream from the source zip file
			FileInputStream fin = new FileInputStream(strZipFile);
			
			//create ZipInputStream from FileInputStream object
			ZipInputStream zin = new ZipInputStream(fin);
			
			//get the first entry from the source zip file
			ZipEntry entry = zin.getNextEntry();
			
			//crate OutputStream to extract the entry from zip file
			OutputStream os = new FileOutputStream(strDestinationPath + "/" + entry.getName());
			
			
			byte[] buffer = new byte[1024];
			int length;
			
			//read the entry from zip file and extract it to disk
			while( (length = zin.read(buffer)) > 0)
			{
				os.write(buffer, 0, length);
			}
			
			//close the streams
			os.close();
			
			//close the zip file
			zin.close();
			
			System.out.println("File Extracted from zip file");
		}
		catch(IOException e)
		{
			System.out.println("IOException :" + e);
		}
 
		 
	}
 
}
