import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class QuickSortBinarySearch {
	
		
	public String[] quickSort(String[] directory, int left, int right) {
		if (left < right) {
	        int pivotIndex = partition(directory, left, right); 
	        quickSort(directory, left, pivotIndex - 1);  
	        quickSort(directory, pivotIndex + 1, right); 
	    }
		return directory;
	}
	
	private static int partition(String[] array, int left, int right) {
	    String pivot = array[right]; 
	    int partitionIndex = left;
	 
	  
	    for (int i = left; i < right; i++) {
	        if (array[i].compareTo(pivot) <= 0) { 
	            swap(array, i, partitionIndex);
	            partitionIndex++;
	        }
	    }
	 
	    swap(array, partitionIndex, right); 
	 
	    return partitionIndex;
	}
	 
	private static void swap(String[] array, int i, int j) {
	    String temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	}

	public String[] loadFile(File find) {
		try {
			Scanner scanner = new Scanner(find);
			
			String directory = readFileAsString("C:\\Users\\Sqan\\Desktop\\directory.txt");
			directory = directory.replaceAll("\\d", "");
			String directoryArray[] = directory.split("\\r?\\n");
			return directoryArray;
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
	
	
	public static int binarySearch(String[] array, String name, int left, int right) {
	    if (left > right) {
	        return 0; 
	    }
	        
	    int mid = left + (right - left) / 2; 
	        
	    if (name.compareTo(array[mid]) == 0) {
	        return 1; 
	    } else if (name.compareTo(array[mid]) < 0) {
	        return binarySearch(array, name, left, mid - 1); 
	    } else {
	        return binarySearch(array, name, mid + 1, right);     
	    }
	}
	
	public void message(int person, int personpresent, long startsorting, long startsearching, long endsorting, long endsearching) {
		System.out.println("Found " + personpresent + " / " + person + " entries. Time taken: " + ((endsearching - startsorting)-(endsearching - startsorting)%60000)/60000 + " min. "+ ((endsearching - startsorting)-(endsearching - startsorting)%1000)/1000 + " sec. " + (endsearching - startsorting)%1000 + " ms." );
		System.out.println("Sorting time: " + ((endsorting - startsorting)-(endsorting - startsorting)%60000)/60000 + " min. "+ ((endsorting - startsorting)-(endsorting - startsorting)%1000)/1000 + " sec. " + (endsorting - startsorting)%1000 + " ms." );
		System.out.println("Searching time: " + ((endsearching - startsearching)-(endsearching - startsearching)%60000)/60000 + " min. "+ ((endsearching - startsearching)-(endsearching - startsearching)%1000)/1000 + " sec. " + (endsearching - startsearching)%1000 + " ms." );

	}
	

	public QuickSortBinarySearch(File find) {
		System.out.println("Start searching (quick sort + binary search)...");
		try {
			Scanner scanner = new Scanner(find);
			long startsorting = System.currentTimeMillis();
			String[] directoryArray = quickSort(loadFile(find),0,(loadFile(find).length-1));
			long endsorting = System.currentTimeMillis();
			
			long startsearching = System.currentTimeMillis();
			int personpresent = 0;
			int person = 0;
			while(scanner.hasNextLine()) {
				person++;
				String name = " " + scanner.nextLine();
				personpresent += binarySearch(directoryArray, name, 0, directoryArray.length-1);
				
			}
			long endsearching = System.currentTimeMillis();
			
			message(person, personpresent, startsorting, startsearching, endsorting, endsearching);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
