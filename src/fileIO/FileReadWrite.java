package fileIO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReadWrite {
	public static final Path ROOT_FOLDER = Paths.get(System.getenv("APPDATA") + "\\Brandon194\\");
	String filePath;
	private String folderName, fileName;

	public FileReadWrite(String folderNameIn, String fileNameIn){
		
		folderName = folderNameIn;
		fileName = fileNameIn;
		
		filePath = ROOT_FOLDER + "\\" + folderName + "\\" + fileName + ".txt";
		
		try{
			Files.createDirectories(Paths.get(ROOT_FOLDER + "\\" + folderName + "\\"));
			Files.createFile(Paths.get(filePath));
		}catch(Exception e){
			if (!Files.exists(Paths.get(filePath)))
                System.out.println("Path does not exist, creation failed.");
		}
	}
	
	public void writer(String[] InputStringArray){
		
		PrintWriter out;
		try {
            Files.deleteIfExists(Paths.get(filePath));

			out = new PrintWriter(new FileWriter(filePath, false));
			
			for (int i=0;i<InputStringArray.length;i++)
				out.println(InputStringArray[i]);
			
			out.close();
			
		} catch (IOException e) {
			System.out.println("Write Failed");
		}
	}
	public String[] reader(){
		
		Path p = (Paths.get(filePath));
		List<String> l = new ArrayList<String>();
		try{
			l = Files.readAllLines(p, StandardCharsets.UTF_8);
		}catch(Exception e){
			System.out.println("Read Fail");
		}

        String[] returnable = new String[l.size()];
        for (int i=0;i<l.size();i++)
            returnable[i] = l.get(i);
		
		return returnable;
	}
    public void delete(){
        try{
            Files.deleteIfExists(Paths.get(filePath));
        }catch(Exception e){
            System.out.println("Failed to Delete");
        }
    }
}
