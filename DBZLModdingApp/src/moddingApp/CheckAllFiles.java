package moddingApp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CheckAllFiles {

	public static String home = System.getProperty("user.dir");
	public static Map<String, Fighter> fighters;
	public static Map<String, ModdedFighter> moddedFighters;
	public static Map<String, String> fighterMappings;

	public static void main(String[] args) throws IOException {

		runSetUp();

		//duplicateFighter("MajinVegeta");
		Map<String, String> allDefenseValues = new HashMap<String, String>();
		for (String fighter : fighters.keySet()){
			Fighter f = fighters.get(fighter);
			String defense = "\t" + f.getStunProperties() + "\n\t" + f.getMPMProperties();
			allDefenseValues.put(f.getName(), defense);
		}
		List<String> allDef = new ArrayList<String>();
		allDef.addAll(allDefenseValues.keySet());
		Collections.sort(allDef);
		for (String defenseValue : allDef){
			System.out.println(defenseValue + "\n" + allDefenseValues.get(defenseValue));
			System.out.println("");
		}
		//System.out.println(fighters.get("UubMajuub").getFilename("Costume_1"));
		//System.out.println(fighters.get("Tapion").getFilename("Costume_1"));
		//System.out.println(fighters.get("EndVegetaSSJ1").getFilename("Costume_1"));
	}

	public static void runSetUp() throws IOException {
		fighters = new HashMap<String, Fighter>();
		for (int i = 0; i < CharacterFileRef.allFighterNames.length; i++) {
			fighters.put(CharacterFileRef.allFighterNames[i], new Fighter(CharacterFileRef.allFighterNames[i]));
		}

		File dir = new File(home + File.separator + "Data" + File.separator + "OriginalCharacters" + File.separator
				+ "CharacterData");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				String[] fileTyping = CharacterFileRef.getFileClassification(child.getName());
				if (fileTyping == null) {
					continue;
				}
				fighters.get(fileTyping[0]).setFileName(fileTyping[1], child.getName());
			}
		}

		for (String fName : fighters.keySet()) {
			fighters.get(fName).setStatsStart();
		}

		moddedFighters = new HashMap<String, ModdedFighter>();

		File modFolder = new File(CheckAllFiles.home + File.separator + "Data" + File.separator + "ModifiedCharacters");
		for (File file : modFolder.listFiles()) {
			String fName = file.getName();
			ModdedFighter mf = new ModdedFighter(fName);
			mf.setFileName("Moveset", "Moveset.unk");
			mf.setFileName("SFX", "SFX.unk");
			for (int i = 1; i <= 4; i++) {
				mf.setFileName("Costume_" + i, "Costume_" + i + ".unk");
				mf.setFileName("Costume_" + i + "_Damaged", "Costume_" + i + "_Damaged.unk");
			}
		}

		fighterMappings = new HashMap<String, String>();
		File mappingFile = new File(
				CheckAllFiles.home + File.separator + "Data" + File.separator + "FighterMapping.txt");
		if (mappingFile.exists()) {
			Scanner reader;
			reader = new Scanner(mappingFile);
			while (reader.hasNextLine()) {
				String str = reader.nextLine();
				String[] bothStr = str.split(" ");
				fighterMappings.put(bothStr[0], bothStr[1]);
			}
			reader.close();
		} else {
			String textData = "";
			for (int i = 0; i < CharacterFileRef.allFighterNames.length; i++) {
				fighterMappings.put(CharacterFileRef.allFighterNames[i], CharacterFileRef.allFighterNames[i]);
				if (i > 0){
					textData += "\n";
				}
				textData += CharacterFileRef.allFighterNames[i] + " " + CharacterFileRef.allFighterNames[i];
			}
			PrintWriter out = new PrintWriter(CheckAllFiles.home + File.separator + "Data" + File.separator + "FighterMapping.txt");
			out.print(textData);
			out.close();
			
			out = new PrintWriter(CheckAllFiles.home + File.separator + "Data" + File.separator + "OriginalFighterMapping.txt");
			out.print(textData);
			out.close();
		}
	}

	public static String[] GetAllFighterNames() {
		ArrayList<String> allNames = new ArrayList<String>();
		for (String s : fighters.keySet()) {
			allNames.add(s);
		}
		for (String s : moddedFighters.keySet()) {
			allNames.add(s);
		}
		Collections.sort(allNames);
		String[] allNamesArr = new String[allNames.size()];
		for (int i = 0; i < allNamesArr.length; i++) {
			allNamesArr[i] = allNames.get(i);
		}
		return allNamesArr;
	}

	public static void duplicateFighter(String name) throws IOException {
		ModdedFighter mod = new ModdedFighter(name);
		mod.duplicateFighter(name);
		fighters.put(mod.getName(), mod);
		mod.setStatsStart();
	}

	public static void duplicateFighter(Fighter fighter) throws IOException {
		duplicateFighter(fighter.name);
	}

}
