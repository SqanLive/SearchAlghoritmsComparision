import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		File find = new File("C:\\Users\\Sqan\\Desktop\\find.txt");
		
		LinearSearch search = new LinearSearch(find);
		BubbleJumpSearch searchbubble = new BubbleJumpSearch(find);
		QuickSortBinarySearch quick = new QuickSortBinarySearch(find);
	}
}
