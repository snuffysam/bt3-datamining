package moddingApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.Files;

public class ModdedFighter extends Fighter {

	public ModdedFighter(String name) throws IOException {
		super(name);
	}
	
	public void duplicateFighter(String name) throws IOException{
		String originalName = name.split(" ")[0];
		File modFolder = new File(CheckAllFiles.home + File.separator + "Data" + File.separator + "ModifiedCharacters");
		int maxInt = 0;
		for (File file : modFolder.listFiles()) {
			if (file.getName().contains(originalName) && file.getName().contains(" ")) {
				int n = Integer.parseInt(file.getName().split(" ")[1]);
				if (n > maxInt) {
					maxInt = n;
				}
			}
		}
		String newName = originalName + " " + (maxInt + 1);
		new File(CheckAllFiles.home + File.separator + "Data" + File.separator
				+ "ModifiedCharacters" + File.separator + newName + File.separator + "CharacterData").mkdirs();
		if (CheckAllFiles.fighters.containsKey(name)) {
			Fighter fighter = CheckAllFiles.fighters.get(name);
			for (int i = -1; i <= 8; i++) {
				String fileToGet = "Moveset";
				if (i == 0) {
					fileToGet = "SFX";
				} else if (i > 0 && i <= 4) {
					fileToGet = "Costume_" + i;
				} else if (i > 4) {
					fileToGet = "Costume_" + (i - 4) + "_Damaged";
				}
				System.out.println(fileToGet);
				File endFile = new File(CheckAllFiles.home + File.separator + "Data" + File.separator
						+ "ModifiedCharacters" + File.separator + newName + File.separator + "CharacterData"
						+ File.separator + fileToGet + ".unk");
				File startFile = new File(CheckAllFiles.home + File.separator + "Data" + File.separator
						+ "OriginalCharacters" + File.separator + "CharacterData" + File.separator
						+ fighter.getFilename(fileToGet));
				Files.copy(startFile.toPath(), endFile.toPath(), REPLACE_EXISTING);
			}
		} else if (CheckAllFiles.moddedFighters.containsKey(name)){
			for (int i = -1; i <= 8; i++) {
				String fileToGet = "Moveset";
				if (i == 0) {
					fileToGet = "SFX";
				} else if (i > 0 && i <= 4) {
					fileToGet = "Costume_" + i;
				} else if (i > 4) {
					fileToGet = "Costume_" + (i - 4) + "_Damaged";
				}
				System.out.println(fileToGet);
				File endFile = new File(CheckAllFiles.home + File.separator + "Data" + File.separator
						+ "ModifiedCharacters" + File.separator + newName + File.separator + "CharacterData"
						+ File.separator + fileToGet + ".unk");
				File startFile = new File(CheckAllFiles.home + File.separator + "Data" + File.separator
						+ "ModifiedCharacters" + File.separator + name + File.separator + "CharacterData"
						+ File.separator + fileToGet + ".unk");
				Files.copy(startFile.toPath(), endFile.toPath(), REPLACE_EXISTING);
			}
		}
		this.name = newName;
	}

	public byte[] getData(String fileToGet) throws IOException {
		File fFile = new File(CheckAllFiles.home + File.separator + "Data" + File.separator + "ModifiedCharacters"
				+ File.separator + name + File.separator + "CharacterData" + File.separator
				+ CheckAllFiles.fighters.get(name).getFilename(fileToGet));
		byte[] moveData = Files.readAllBytes(fFile.toPath());
		return moveData;
	}
	
	public String getFilename(String fileType) {
		return fileType;
	}

	public void setChargeType(int chargeType) throws IOException {
		// 0 = normal
		// 1 = electric
		// 2 = android
		// 3 = android and electric
	}

	public void setAuraColor(int auraColor) throws IOException {
		// 0 = blue/white
		// 1 = violet
		// 2 = yellow
		// 3 = red
		// 4 = green
		// 5 = light blue
		// 6 = pink
		// 7 = dark blue
		// 8 = ssj1
		// 9 = ssj2
		// 10 = ssj3
		for (int i = 0; i < 8; i++){
			int auraIndex = statsStartIndex[i] - 30;
			String fName = "Costume_" + (i+1);
			if (i > 3){
				fName = "Costume_" + (i-3) + "_Damaged";
			}
			byte[] moveData = getData(fName);
			moveData[auraIndex] = (byte)(auraColor);
		}
	}
	

}
