import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class BubbleJumpSearch {

	private int personpresent = 0;
	public static long startsorting = 0;
	public static long endsorting = 0;
	public long startsearching = 0;
	public long endsearching = 0;
	public static boolean shouldBreak = false;
	
	public String[] SortDirectory() {
		startsorting = System.currentTimeMillis();
		String directory = "";
		try {
			directory = readFileAsString("C:\\Users\\Sqan\\Desktop\\alfabetyczne.txt");
			directory = directory.replaceAll("\\d", "");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String directoryArray[] = directory.split("\\r?\\n");
		int count = 0;
		for(String line : directoryArray) {
			directoryArray[count++] = line;
		}
		
		for (int i = 0; i < directoryArray.length - 1; i++) {
	        for (int j = 0; j < directoryArray.length - i - 1; j++) {
	            if (directoryArray[j].compareTo(directoryArray[j+1]) > 0 ) {
	                String temp = directoryArray[j];
	                directoryArray[j] = directoryArray[j + 1];
	                directoryArray[j + 1] = temp;
	            }
	        }
	        if(System.currentTimeMillis() - startsorting > (20  * (LinearSearch.endLinearSearch - LinearSearch.startLinearSearch))) {
	        	shouldBreak = true;
	        	endsorting = System.currentTimeMillis();
	        	return directoryArray;
	        }
	    }
		
		endsorting = System.currentTimeMillis();
			

		return directoryArray;
		
	}
	
	public void JumpSearch(String[] directoryArray, String name) {
		int currentRight = 0; 
	    int prevRight = 0;
	    
	    if(directoryArray.length == 0) {
	    	return;
	    }
	    
	    if (directoryArray[currentRight].compareTo(name) == 0) {
	        personpresent++;
	        return;
	    }
	 
	    int jumpLength = (int) Math.sqrt(directoryArray.length);
	 
	    while (currentRight < directoryArray.length - 1) {

	        currentRight = Math.min(directoryArray.length - 1, currentRight + jumpLength);
	 
	        if (directoryArray[currentRight].compareTo(name) >= 0) {
	            break; 
	        }
	 
	        prevRight = currentRight; 
	    }
	 
	    if ((currentRight == directoryArray.length - 1) && name.compareTo(directoryArray[currentRight]) > 0) {
	        return;
	    }
	 
	    backwardSearch(directoryArray, name, prevRight, currentRight);
		
	}
	
	public void backwardSearch(String[] directoryArray, String name, int leftExcl, int rightIncl) {

	    for (int i = rightIncl; i > leftExcl; i--) {
	        if (directoryArray[i].compareTo(name) == 0) {
	            personpresent++;
	            return;
	        }
	    }
	}
	
	public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
	
	public void message(int person, int personpresent) {
		System.out.println("Found " + personpresent + " / " + person + " entries. Time taken: " + ((endsearching - startsorting)-(endsearching - startsorting)%60000)/60000 + " min. "+ ((endsearching - startsorting)-(endsearching - startsorting)%1000)/1000 + " sec. " + (endsearching - startsorting)%1000 + " ms." );
		System.out.println("Sorting time: " + ((endsorting - startsorting)-(endsorting - startsorting)%60000)/60000 + " min. "+ ((endsorting - startsorting)-(endsorting - startsorting)%1000)/1000 + " sec. " + (endsorting - startsorting)%1000 + " ms." );
		System.out.println("Searching time: " + ((endsearching - startsearching)-(endsearching - startsearching)%60000)/60000 + " min. "+ ((endsearching - startsearching)-(endsearching - startsearching)%1000)/1000 + " sec. " + (endsearching - startsearching)%1000 + " ms." );

	}
	
	public BubbleJumpSearch(File find) {
		System.out.println("Start searching (bubble sort + jump search)...");
		try {
			Scanner scanner = new Scanner(find);
			int person = 0;
			String[] directoryArray = SortDirectory();
			
			if(shouldBreak == true) {
				LinearSearch search = new LinearSearch(find);
				return;
			}

			startsearching = System.currentTimeMillis();
			while(scanner.hasNextLine()) {
				person++;
				String name = " " + scanner.nextLine();
				JumpSearch(directoryArray, name);
			}
			endsearching = System.currentTimeMillis();
			message(person, personpresent);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
