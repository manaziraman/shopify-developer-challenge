import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainClass {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		// creating new repository

		new File("./repository").mkdirs();

		startingScreen();

		System.out.println("Thanks for using the image repository! Goodbye!");

	}

	public static void startingScreen() {

		System.out.println("Welcome to the Image Repository!");
		System.out.println("Input the number of choice from the following menu:");
		System.out.println("1) Add image");
		System.out.println("2) Delete image");
		System.out.println("3) Download image");
		System.out.println("4) Empty repository");
		System.out.println("5) Quit application");

		int option = scanner.nextInt();

		switch (option) {
		case 1:
			addImage();
		case 2:
			deleteImage();
		case 3:
			downloadImage();
		case 4:
			emptyRepository();
		case 5:
			return;
		}

	}

	public static void addImage() {

		System.out.println("What is the file path of the image you'd like to add?");

		String path = scanner.next();

		Path temp = null;
		String imageName = path.substring(path.lastIndexOf("/") + 1);

		try {
			temp = Files.move(Paths.get(path), Paths.get("./repository/" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (temp != null) {
			System.out.println("Image added succesfully!");
		} else {
			System.out.println("Failed to move the file");
		}

		startingScreen();

	}

	public static void deleteImage() {

		System.out.println("Of the following, which image would you like to delete? Type the filename.");

		File repository = new File("./repository");

		for (File x : repository.listFiles()) {
			System.out.println(x.getName());
		}

		String name = scanner.next();

		File file = new File("repository/" + name);

		if (file.delete()) {
			System.out.println("Deleted the file: " + file.getName());
		} else {
			System.out.println("Failed to delete the file.");
		}

		startingScreen();

	}

	public static void downloadImage() {

		System.out.println("Of the following, which image would you like to download? Type the filename.");

		File repository = new File("./repository");

		for (File x : repository.listFiles()) {
			System.out.println(x.getName());
		}

		String name = scanner.next();

		System.out.println("Which directory would you like to download it to? Provide the absolute filepath.");

		String path = scanner.next();

//		String imageName = path.substring(path.lastIndexOf("/") + 1);

		try {
			Path temp = Files.move(Paths.get("./repository/" + name), Paths.get(path + "/" + name));
			System.out.println(name + " has been downloaded!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		startingScreen();

	}

	public static void emptyRepository() {

		File folder = new File("./repository");

		File[] files = folder.listFiles();

		if (files != null) {
			for (File f : files) {
				f.delete();
			}
		}
		System.out.println("Image Repository has been emptied!");
		startingScreen();

	}

}
