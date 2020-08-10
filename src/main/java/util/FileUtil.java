package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.testng.asserts.SoftAssert;

public class FileUtil 
{

	public static String separator = System.getProperty("file.separator");
//	 public static String fileName =
//	 "UIAutomationMobile"+separator+"Constant.cfg";
	public static String fileNameForMac = separator + "src" + separator + "test" + separator + "java" + separator +
			"com" + separator + "snapdeal" + separator + "qa" + separator + "integration" + separator +
			"config" + separator + "Path.cfg";
	
	public static String fileNameForWindows = "\\src\\test\\java\\com\\snapdeal\\qa\\integration\\config\\path.cfg";
	public static String fileName="";
	public static InputStream input = null;

	public synchronized static String getConstantValue(String property) {

		if(property.equals("WindowsChromeDriver"))
			fileName=fileNameForWindows;
		else
		fileName=fileNameForMac;
		
		Properties prop = new Properties();

		String value = null;
		String path = null;
		// System.out.println("file input constant.cfg ::
		// "+System.getProperty("user.dir")+separator+fileName);
		try {

			path = System.getProperty("user.dir") + fileName;
			File f = new File(path);
			if (f.exists()) {

				input = new FileInputStream(path);
			} else {

				input = new FileInputStream(System.getProperty("user.dir") + fileName);
			}

			prop.load(input);
			value = prop.getProperty(property);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return value;
	}

	/*
	 * public static void loadEnvironment(String environment) throws IOException {
	 * 
	 * String path =
	 * System.getProperty("user.dir")+separator+FileUtil.getConstantValue
	 * ("ProjectName"); System.out.println("path is: "+path); path = path +
	 * separator + FileUtil.getConstantValue("ConfigPath") + separator;
	 * System.out.println("path is: "+path); FileWriter file = new FileWriter(path +
	 * FileUtil.getConstantValue("TestEnvironment"), false); BufferedWriter
	 * define_environment = new BufferedWriter(file);
	 * define_environment.write("environment=" + environment);
	 * define_environment.close(); }
	 */

	public static void loadEnvironment(String environment) throws IOException {

		String path = null;
		path = System.getProperty("user.dir") + separator + "UIAutomationWap" + separator
				+ FileUtil.getConstantValue("ConfigPath");
		FileWriter file = null;
		File f = new File(path);
		if (f.exists()) {
			file = new FileWriter(path + separator + FileUtil.getConstantValue("TestEnvironment"), false);
		} else {
			file = new FileWriter(System.getProperty("user.dir") + separator

					+ getConstantValue("ConfigPath") + separator + getConstantValue("TestEnvironment"), false);
		}
		BufferedWriter define_environment = new BufferedWriter(file);
		define_environment.write("environment=" + environment);
		System.out.println("Setting Test environment As per maven Goal " + environment);
		define_environment.close();
	}

	public static String createFile(String fileName) {

		String userHome = System.getProperty("user.dir");

		try {

			// System.out.println(userHome + separator + fileName);
			File file = new File(userHome + separator + fileName);

			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public FileReader openOrCreateFileToRead(String filePath) throws IOException {
		File file = new File(filePath);
		FileReader fileReader = new FileReader(file);
		return fileReader;
	}

	public FileWriter openOrCreateFileToWrite(String filePath, boolean appendModeOrNot) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file.getName(), appendModeOrNot);
		return fileWriter;
	}

	public static void searchAndRemoveString(String fileName, ArrayList<String> stringOut, int listSize)
			throws IOException {

		int count = 0;
		File targetFile = new File(fileName);
		StringBuffer fileContents = new StringBuffer(FileUtils.readFileToString(targetFile));
		String[] fileContentLines = fileContents.toString().split(System.lineSeparator());

		emptyFile(targetFile);

		fileContents = new StringBuffer();

		for (int fileContentLinesIndex = 0; fileContentLinesIndex < fileContentLines.length; fileContentLinesIndex++) {

			if (fileContentLines[fileContentLinesIndex].contains(stringOut.get(count))) {
				System.out.println(stringOut.get(count));
				if (count < listSize) {
					count++;
				}
				continue;
			}

			fileContents.append(fileContentLines[fileContentLinesIndex] + System.lineSeparator());
		}

		FileUtils.writeStringToFile(targetFile, fileContents.toString().trim());

	}

	private static void emptyFile(File targetFile) throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile, "rw");

		randomAccessFile.setLength(0);
		randomAccessFile.close();
	}

	public static List<String> readDataFromFile(String path) throws IOException {
		File keywordFile = new File(FileUtil.getConstantValue(path));
		BufferedReader br = new BufferedReader(new FileReader(keywordFile));
		String str = null;
		List<String> listofScores = null;
		listofScores = new ArrayList<String>();
		while ((str = br.readLine()) != null) {
			String[] listofScoresArrayArray = str.split(",");
			for (int i = 0; i < listofScoresArrayArray.length; i++)
				listofScores.add(listofScoresArrayArray[i]);
		}
		br.close();
		return listofScores;
	}
	
	
	/**
	 * This method is used to convert a String to ASCII Code
	 * @param input
	 * @return
	 */

	public static String asciiCodeConverter(String input) {
		final CharsetEncoder asciiEncoder = Charset.forName("US-ASCII").newEncoder();
		//final CharsetDecoder decoder = Charset.forName("US-ASCII").newDecoder();
	    final StringBuilder result = new StringBuilder();
	    for (final Character character : input.toCharArray()) {
	        if (asciiEncoder.canEncode(character)) {
	            result.append(character);
	        } else {
	            result.append("\\u");
	            result.append(Integer.toHexString(0x10000 | character).substring(1).toUpperCase());
	        }
	    }
	    return result.toString();
	}
	
	public static void asciiCodeDecoder(String input) {
		String text="";
		int strLength = input.split(" ").length; //initial was asciiCodeConverter(input)
		for(int abc = 0;abc < strLength; abc++) {
		String str = input.split(" ")[abc]; //initial was asciiCodeConverter(input)
		str = str.replace("\\", "");
		String[] arr = str.split("u");
		
		for (int i = 1; i < arr.length; i++) {
			int hexVal = Integer.parseInt(arr[i], 16);
			text += (char) hexVal;
		}
		text = text + " ";
		}
		System.out.println(text);
		//return text;
	}
	
	//Christ Kansal - need to update this method. 
	// Do not use this method, this will give INCORRECT result right now.
	public static void softAssertWithASCII(String expectedResult, String actualResult) {
		SoftAssert softAssert = new SoftAssert();
		/*
		 * try{ Assert.assertEquals(actualResult, expectedResult);
		 * System.out.println(FileUtil.asciiCodeDecoder(actualResult));
		 * }catch(AssertionError e) { e.getMessage();
		 * System.out.println("Expected Result: "+FileUtil.asciiCodeDecoder(
		 * expectedResult));
		 * System.out.println("Actual Result: "+FileUtil.asciiCodeDecoder(actualResult))
		 * ; }
		 */
//		String expected = asciiCodeDecoder(expectedResult);
//		String actual = asciiCodeDecoder(actualResult);
		System.out.println("Assert that Expected and Actual Values are Equal.");
		//asciiCodeDecoder(expectedResult);
		//System.out.println("Actual Value is: "+actual);
		softAssert.assertEquals(actualResult, expectedResult);
	}
	
	/**
	 * This method is used to split the string with the provided delimiter
	 * @param inputData 
	 * @param delimiter --> Delimiter through which a user wants to split the string
	 * @return --> Returning the array of subString with the delimiter
	 */
	
	public static String[] spliltStringToGetKey(String inputData, String delimiter) {
		//String str = new StringTokenizer(inputData,",",false);
		//str.
		String[] arr =inputData.split(delimiter);
		return arr;
	}


}
