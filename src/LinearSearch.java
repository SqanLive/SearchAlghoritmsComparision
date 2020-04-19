import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class LinearSearch {
	
	public static long startLinearSearch = 0;
	public static long endLinearSearch = 0;
	
	public void LoadFile(File find) {	
		try {
			Scanner scanner = new Scanner(find);
			String directory = readFileAsString("C:\\Users\\Sqan\\Desktop\\directory.txt");
			directory = directory.replaceAll("\\d", "");
			String directoryArray[] = directory.split("\\r?\\n");
			
			startLinearSearch = System.currentTimeMillis();
			int personpresent = 0;
			int person = 0;
			while(scanner.hasNextLine()) {
				person++;
				String name = " " + scanner.nextLine();
				for(int i = 0; i < directoryArray.length; i++) {
					if (directoryArray[i].compareTo(name) == 0) {
				        personpresent++;
				    }
				}
			}
			endLinearSearch = System.currentTimeMillis();
			message(person, personpresent);
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void message(int person, int personpresent) {
		long minutes = (((endLinearSearch - startLinearSearch)+(BubbleJumpSearch.endsorting-BubbleJumpSearch.startsorting))-((endLinearSearch - startLinearSearch)+(BubbleJumpSearch.endsorting-BubbleJumpSearch.startsorting))%60000)/60000;
		long seconds = (((endLinearSearch - startLinearSearch)+(BubbleJumpSearch.endsorting-BubbleJumpSearch.startsorting))-((endLinearSearch - startLinearSearch)+(BubbleJumpSearch.endsorting-BubbleJumpSearch.startsorting))%1000)/1000;
		long miliseconds = ((endLinearSearch - startLinearSearch)+(BubbleJumpSearch.endsorting-BubbleJumpSearch.startsorting))%1000;
		System.out.println("Found " + personpresent + " / " + person + " entries. Time taken: " + minutes + " min. " + seconds + " sec. " + miliseconds + " ms.");
		if(BubbleJumpSearch.shouldBreak == true) {
			
			System.out.println("Sorting time: " + ((BubbleJumpSearch.endsorting - BubbleJumpSearch.startsorting)-(BubbleJumpSearch.endsorting - BubbleJumpSearch.startsorting)%60000)/60000 + " min. "+ ((BubbleJumpSearch.endsorting - BubbleJumpSearch.startsorting)-(BubbleJumpSearch.endsorting - BubbleJumpSearch.startsorting)%1000)/1000 + " sec. " + (BubbleJumpSearch.endsorting - BubbleJumpSearch.startsorting)%1000 + " ms. - STOPPED, moved to linear search" );
			System.out.println("Searching time: " + ((endLinearSearch - startLinearSearch)-(endLinearSearch - startLinearSearch)%60000)/60000 + " min. "+ ((endLinearSearch - startLinearSearch)-(endLinearSearch - startLinearSearch)%1000)/1000 + " sec. " + (endLinearSearch - startLinearSearch)%1000 + " ms." );

		}
	}
	
	
	public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
	
	public LinearSearch(File find) {
		if(BubbleJumpSearch.shouldBreak == false) {
		System.out.println("Start searching (linear search)...");
		}
		LoadFile(find);
	}
	

}
