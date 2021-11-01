package moddingApp;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Fighter {

	protected String[] costumes;
	protected String[] costumesDamaged;
	protected String moveset;
	protected String sfx;
	protected String name;
	protected int[] statsStartIndex;

	public Fighter(String name) {
		this.name = name;
		costumes = new String[4];
		costumesDamaged = new String[4];
	}

	public String getName() {
		return name;
	}

	public byte[] getData(String fileToGet) throws IOException {
		File fFile = new File(
				CheckAllFiles.home + File.separator + "Data" + File.separator + "OriginalCharacters" + File.separator
						+ "CharacterData" + File.separator + CheckAllFiles.fighters.get(name).getFilename(fileToGet));
		byte[] moveData = Files.readAllBytes(fFile.toPath());
		return moveData;
	}

	public void setFileName(String fileType, String filename) {
		if (fileType.equals("Moveset")) {
			moveset = filename;
		} else if (fileType.equals("SFX")) {
			sfx = filename;
		} else if (fileType.contains("Costume")) {
			int n = Integer.parseInt(fileType.split("_")[1]);
			if (fileType.contains("Damaged")) {
				costumesDamaged[n - 1] = filename;
			} else {
				costumes[n - 1] = filename;
			}
		}
	}

	public void setStatsStart() throws IOException {
		statsStartIndex = new int[8];

		byte[] statsSection = { -96, -122, 1 };

		for (int k = 0; k < costumes.length; k++) {
			byte[] moveData = getData("Costume_" + (k + 1));
			for (int i = 0; i < moveData.length; i++) {
				if (i >= 2) {
					if (moveData[i] == statsSection[2] && moveData[i - 1] == statsSection[1]
							&& moveData[i - 2] == statsSection[0]) {
						statsStartIndex[k] = i - 5;
						if (moveData[statsStartIndex[k] + 94] != (byte)(63)){
							continue;
						}
						break;
					}
				}
			}
		}
		for (int k = 0; k < costumesDamaged.length; k++) {
			byte[] moveData = getData("Costume_" + (k + 1) + "_Damaged");
			for (int i = 0; i < moveData.length; i++) {
				if (i >= 2) {
					if (moveData[i] == statsSection[2] && moveData[i - 1] == statsSection[1]
							&& moveData[i - 2] == statsSection[0]) {
						statsStartIndex[k + 4] = i - 5;
						if (moveData[statsStartIndex[k + 4] + 94] != (byte)(63)){
							continue;
						}
						break;
					}
				}
			}
		}
		
	}

	public List<String> checkMissing() {
		List<String> missing = new ArrayList<String>();
		if (moveset == null || moveset.length() == 0) {
			missing.add("Moveset");
		}
		if (sfx == null || sfx.length() == 0) {
			missing.add("SFX");
		}
		for (int i = 0; i < costumes.length; i++) {
			if (costumes[i] == null || costumes[i].length() == 0) {
				missing.add("Costume_" + (i + 1));
			}
		}
		for (int i = 0; i < costumesDamaged.length; i++) {
			if (costumesDamaged[i] == null || costumesDamaged[i].length() == 0) {
				missing.add("Costume_" + (i + 1) + "_Damaged");
			}
		}
		return missing;
	}

	public String getFilename(String fileType) {
		if (fileType.equals("Moveset")) {
			return moveset;
		}
		if (fileType.equals("SFX")) {
			return sfx;
		}
		if (fileType.contains("Costume")) {
			int n = Integer.parseInt(fileType.split("_")[1]);
			if (fileType.contains("Damaged")) {
				return costumesDamaged[n - 1];
			} else {
				return costumes[n - 1];
			}
		}
		return "";
	}

	public String getChargeType() throws IOException {
		int chargeIndex = statsStartIndex[0] - 33;
		byte[] moveData = getData("Costume_1");
		if (moveData[chargeIndex] >= (byte) (4) && moveData[chargeIndex] <= (byte) (9)) {
			return "Electric Effect";
		} else if (moveData[chargeIndex] == (byte) (-128)) {
			return "Android Charge";
		} else if (moveData[chargeIndex] == (byte) (-124)) {
			return "Android Charge & Electric Effect";
		} else {
			return "Normal Charge";
		}

	}

	public String getChargeSound() throws IOException {
		int chargeIndex = statsStartIndex[0] - 32;
		byte[] moveData = getData("Costume_1");
		if (moveData[chargeIndex] == (byte) (0)) {
			return "Normal Charge Sound";
		} else if (moveData[chargeIndex] == (byte) (1)) {
			return "Buu Charge Sound";
		} else if (moveData[chargeIndex] == (byte) (2)) {
			return "Great Ape Charge Sound";
		} else {
			return "";
		}
	}

	public String getSizeEffect() throws IOException {
		int chargeIndex = statsStartIndex[0] - 31;
		byte[] moveData = getData("Costume_1");
		if (moveData[chargeIndex] == (byte) (0)) {
			return "Very Light";
		} else if (moveData[chargeIndex] == (byte) (1)) {
			return "Light";
		} else if (moveData[chargeIndex] == (byte) (2)) {
			return "Normal";
		} else if (moveData[chargeIndex] == (byte) (3)) {
			return "Big";
		} else if (moveData[chargeIndex] == (byte) (4)) {
			return "Giant (blocks grabs & rush when enabled)";
		} else {
			return "";
		}
	}

	public String getAuraColor() throws IOException {
		int chargeIndex = statsStartIndex[0] - 30;
		byte[] moveData = getData("Costume_1");
		if (moveData[chargeIndex] == (byte) (0)) {
			return "Blue/White";
		} else if (moveData[chargeIndex] == (byte) (1)) {
			return "Violet";
		} else if (moveData[chargeIndex] == (byte) (2)) {
			return "Yellow";
		} else if (moveData[chargeIndex] == (byte) (3)) {
			return "Red";
		} else if (moveData[chargeIndex] == (byte) (4)) {
			return "Green";
		} else if (moveData[chargeIndex] == (byte) (5)) {
			return "Light Blue";
		} else if (moveData[chargeIndex] == (byte) (6)) {
			return "Pink";
		} else if (moveData[chargeIndex] == (byte) (7)) {
			return "Dark Blue";
		} else if (moveData[chargeIndex] == (byte) (8)) {
			return "SSJ1";
		} else if (moveData[chargeIndex] == (byte) (9)) {
			return "SSJ2";
		} else if (moveData[chargeIndex] == (byte) (10)) {
			return "SSJ3";
		} else {
			return "";
		}
	}

	public String getStunProperties() throws IOException {
		int chargeIndex = statsStartIndex[0] - 17;
		byte[] moveData = getData("Costume_1");
		if ((moveData[chargeIndex] >= (byte) (4) && moveData[chargeIndex] <= (byte) (7))
				|| (moveData[chargeIndex] >= (byte) (36) && moveData[chargeIndex] <= (byte) (39))) {
			return "Power Body, Normal Stun, Aura";
		} else if (moveData[chargeIndex] == (byte) (18)) {
			return "Normal Stun, No Aura, Absorb B2s";
		} else if ((moveData[chargeIndex] >= (byte) (8) && moveData[chargeIndex] <= (byte) (11))
				|| moveData[chargeIndex] >= (byte) (40) || moveData[chargeIndex] <= (byte) (43)) {
			return "No Stun, Aura";
		} else if ((moveData[chargeIndex] >= (byte) (24) && moveData[chargeIndex] <= (byte) (27))
				|| moveData[chargeIndex] >= (byte) (42) || moveData[chargeIndex] <= (byte) (57)) {
			return "No Stun, No Aura";
		} else if (moveData[chargeIndex] >= (byte) (16) && moveData[chargeIndex] <= (byte) (19)) {
			return "Normal Stun, No Aura";
		} else if ((moveData[chargeIndex] >= (byte) (20) && moveData[chargeIndex] <= (byte) (23))
				|| (moveData[chargeIndex] >= (byte) (52) && moveData[chargeIndex] <= (byte) (56))) {
			return "Power Body, Normal Stun, No Aura";
		} else if (moveData[chargeIndex] == (byte) (26)) {
			return "Normal Stun, No Aura, Absorb B2s";
		} else if (moveData[chargeIndex] == (byte) (64) || moveData[chargeIndex] == (byte) (65)) {
			return "Normal Stun, Aura, Sword Effect";
		} else if (moveData[chargeIndex] == (byte) (72)) {
			return "No Stun, Aura, Sword Effect";
		} else if (moveData[chargeIndex] == (byte) (79) && moveData[chargeIndex] == (byte) (80)) {
			return "Normal Stun, No Aura, Sword Effect";
		} else if (moveData[chargeIndex] == (byte) (88)) {
			return "No Stun, No Aura, Sword Effect";
		} else {
			return "Normal Stun, Aura";
		}
	}
	
	public String getMPMProperties() throws IOException {
		int chargeIndex = statsStartIndex[0] - 9;
		byte[] moveData = getData("Costume_1");
		if (moveData[chargeIndex] == (byte) (0)) {
			return "No extra bonuses in MPM";
		} else if (moveData[chargeIndex] == (byte) (17) || moveData[chargeIndex] == (byte) (-63)) {
			return "Power Body + Ki blast deflect in MPM";
		} else if (moveData[chargeIndex] == (byte) (25)) {
			return "Bonus dodges in MPM";
		} else if (moveData[chargeIndex] == (byte) (65)) {
			return "Power Body in MPM";
		} else if (moveData[chargeIndex] == (byte) (81)) {
			return "Light Body in MPM";
		} else if (moveData[chargeIndex] == (byte) (-111)) {
			return "Ki blast deflect in MPM";
		} else if (moveData[chargeIndex] == (byte) (-119) || moveData[chargeIndex] == (byte) (-103)) {
			return "Ki blast deflect + Bonus dodges in MPM";
		} else if (moveData[chargeIndex] == (byte) (-39)) {
			return "Power Body + Ki blast deflect + Bonus dodges in MPM";
		}
		return "Unknown bonuses in MPM";
	}

	public String getChargeRate() throws IOException {
		int chargeIndex = statsStartIndex[0] + 15;
		byte[] moveData = getData("Costume_1");
		int total = 0;
		for (int i = 0; i < 4; i++) {
			int n = (int) (moveData[chargeIndex + i]);
			if (n < 0) {
				n += 256;
			}
			total += (n * Math.pow(256, i));
		}
		return "" + total;
	}

	public String getPassiveChargeRate() throws IOException {
		int chargeIndex = statsStartIndex[0] + 23;
		byte[] moveData = getData("Costume_1");
		int total = 0;
		for (int i = 0; i < 4; i++) {
			int n = (int) (moveData[chargeIndex + i]);
			if (n < 0) {
				n += 256;
			}
			total += (n * Math.pow(256, i));
		}
		return "" + total;
	}

	public int searchForBytes(byte[] key) throws IOException {
		return searchForBytes(key, 0, 0);
	}

	public int searchForBytes(byte[] key, int index, int offset) throws IOException {
		byte[] moveData = null;
		if (index >= 4) {
			moveData = getData("Costume_" + (index - 3) + "_Damaged");
		} else {
			moveData = getData("Costume_" + (index + 1));
		}
		int found = -1;
		for (int i = statsStartIndex[index] + offset; i < moveData.length - key.length; i++) {
			boolean foundData = true;
			for (int k = 0; k < key.length; k++) {
				if (key[k] != moveData[i + k]) {
					foundData = false;
					break;
				}
			}
			if (foundData) {
				found = i;
				break;
			}
		}
		return found - statsStartIndex[index];
	}

	public String getDefense() throws IOException {
		int chargeIndex = statsStartIndex[0] + 91;
		byte[] moveData = getData("Costume_1");
		byte[] bytes = {moveData[chargeIndex], moveData[chargeIndex+1], moveData[chargeIndex+2], moveData[chargeIndex+3]};
		float f = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
		float damageMultiplier = f;
		int percent = (int)(damageMultiplier*100f)-100;
		if (percent >= 0){
			return "Damage Taken +" + percent + "%";
		}
		return "Damage Taken " + percent + "%";
	}

}
