package fr.iclario.utils;

import java.io.InputStream;
import java.util.Scanner;

public class Constants
{
	static Constants obj = new Constants();
	public static final String modpackFile = "modpacks.xml";
	public static final String thirdpartyFile = "thirdparty.xml";

	public static final String modpackBackupFile = "modpacks_backup.xml";
	public static final String thirdpartyBackupFile = "thirdparty_backup.xml";

	public static final String backupPath = "static/";
	public static final String path = "static/";

	public static final String version = obj.getFile("version/maj") + "." + obj.getFile("version/min") + "." + obj.getFile("version/rev");
	public static final String build = obj.getFile("version/build");

	private String getFile (String fileName)
	{
		InputStream stream = ClassLoader.getSystemResourceAsStream(fileName);
		Scanner scanner = new java.util.Scanner(stream);
		java.util.Scanner s = scanner.useDelimiter("\\A");
		String string = s.hasNext() ? s.next() : "";
		scanner.close();
		return string;
	}
}
