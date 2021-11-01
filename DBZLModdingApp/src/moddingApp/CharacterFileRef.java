package moddingApp;

public class CharacterFileRef {

	public static String[] allFighterNames = { "EarlyGoku", "MidGoku", "MidGokuSSJ1", "EndGoku", "EndGokuSSJ1",
			"EndGokuSSJ2", "EndGokuSSJ3", "GTGoku", "GTGokuSSJ1", "GTGokuSSJ3", "GokuSSJ4", "KidGoku",
			"KidGokuGreatApe", "KidGohan", "TeenGohan", "TeenGohanSSJ1", "TeenGohanSSJ2", "AdultGohan",
			"AdultGohanSSJ1", "AdultGohanSSJ2", "AdultGohanGreatSaiyaman", "UltimateGohan", "EarlyPiccolo",
			"LatePiccolo", "Nail", "Krillin", "Yamcha", "Tien", "Chiaotzu", "ScouterVegeta", "ScouterVegetaGreatApe",
			"MidVegeta", "MidVegetaSSJ1", "MidVegetaSuper", "EndVegeta", "EndVegetaSSJ1", "EndVegetaSSJ2",
			"MajinVegeta", "VegetaSSJ4", "SwordTrunks", "SwordTrunksSSJ1", "AdultTrunks", "AdultTrunksSSJ1",
			"AdultTrunksSuper", "KidTrunks", "KidTrunksSSJ1", "Goten", "GotenSSJ1", "Gotenks", "GotenksSSJ1",
			"GotenksSSJ3", "Vegito", "VegitoSSJ1", "GogetaSSJ1", "GogetaSSJ4", "Hercule", "Videl",
			"VidelGreatSaiyawoman", "SupremeKai", "SupremeKaiKibito", "Yajirobe", "Pikkon", "Tapion", "Pan", "Uub",
			"UubMajuub", "MasterRoshi", "MasterRoshiMaxPower", "GrandpaGohan", "Nam", "Android8", "KingVegeta",
			"KingVegetaGreatApe", "Bardock", "BardockGreatApe", "Fasha", "FashaGreatApe", "Raditz", "RaditzGreatApe",
			"Saibaman", "Nappa", "NappaGreatApe", "Zarbon", "ZarbonTransformed", "Dodoria", "Cui", "Ginyu", "Recoome",
			"Burter", "Jeice", "Guldo", "Frieza", "FriezaForm2", "FriezaForm3", "FriezaFinalForm", "FriezaMaxPower",
			"MechaFrieza", "KingCold", "Appule", "FriezaSoldier", "Android16", "Android17", "Android18", "Android19",
			"DrGero", "Cell", "CellForm2", "CellPerfect", "CellPerfect2", "CellJunior", "Babidi", "Dabura", "MajinBuu",
			"EvilBuu", "SuperBuu", "SuperBuuGotenks", "SuperBuuGohan", "KidBuu", "GarlicJunior",
			"GarlicJuniorTransformed", "DrWheelo", "Turles", "TurlesGreatApe", "Slug", "SlugGiant", "Salza", "Cooler",
			"CoolerFinalForm", "MetaCooler", "Android13", "Android13Transformed", "Broly", "BrolySSJ1",
			"BrolySSJLegendary", "Zangya", "Bojack", "BojackTransformed", "Janemba", "JanembaSuper", "Hirudigarn",
			"BabyVegeta", "BabyVegetaSuper1", "BabyVegetaSuper2", "BabyVegetaGreatApe", "SuperAndroid17",
			"NuovaShenron", "SynShenron", "SynShenronOmega", "TaoPaiPai", "CyborgTaoPaiPai", "GeneralBlue", "Devilman",
			"PilafRobot", "PilafRobotFusion", "Tambourine", "DemonKingPiccolo", "Arale", "ChiChi", "Spopovich",
			"FutureGohan", "FutureGohanSSJ1" };

	public static String[] getFileClassification(String filename) {
		if (filename.equals("ƒ‚ƒfEunk.unk")) {
			return new String[] { "EarlyGoku", "Costume_1" };
		}
		if (filename.equals("ƒAƒjEunk.unk")) {
			return new String[] { "EarlyGoku", "Moveset" };
		}
		if (filename.equals("•KŽEEunk.unk") || filename.equals("•KŽEEunk.un")) {
			return new String[] { "EarlyGoku", "SFX" };
		}
		if (filename.equals("ƒ‚Eunk.unk")) {
			return new String[] { "GTGoku", "Costume_1" };
		}
		if (filename.equals("ƒAEunk.unk")) {
			return new String[] { "GTGoku", "Moveset" };
		}
		if (filename.equals("•KEunk.unk")) {
			return new String[] { "GTGoku", "SFX" };
		}
		if (filename.equals("ƒAƒjƒEunk.unk")) {
			return new String[] { "KidGokuGreatApe", "Moveset" };
		}
		if (filename.equals("ƒOƒŒ[ƒgƒTƒCƒ„.unk")) {
			return new String[] { "AdultGohanGreatSaiyaman", "Costume_1" };
		}
		if (filename.equals("pa.unk")) {
			return new String[] { "Chiaotzu", "Costume_1" };
		}
		if (filename.equals("ƒAƒjƒ[Eunk.unk")) {
			return new String[] { "Chiaotzu", "Moveset" };
		}
		if (filename.equals("Ch.unk")) {
			return new String[] { "Chiaotzu", "SFX" };
		}
		if (filename.equals("Eunk.unk")) {
			return new String[] { "MajinVegeta", "Costume_1" };
		}
		if (filename.equals("ƒ~ƒXƒ^[EƒTƒ^.unk")) {
			return new String[] { "Hercule", "Costume_1" };
		}
		if (filename.equals("Na.unk")) {
			return new String[] { "Nam", "SFX" };
		}
		if (filename.equals("ƒtƒŠ[ƒUŒR•ºŽm.unk")) {
			return new String[] { "FriezaSoldier", "Costume_1" };
		}
		if (filename.equals("l‘¢lŠÔ13†_0.unk")) {
			return new String[] { "Android13", "Costume_1" };
		}
		if (filename.equals("l‘¢lŠÔ13†_1.unk")) {
			return new String[] { "Android13Transformed", "Costume_1" };
		}
		if (filename.equals("ƒ{[ƒWƒƒƒbƒN_0.unk")) {
			return new String[] { "Bojack", "Costume_1" };
		}
		if (filename.equals("ƒ{[ƒWƒƒƒbƒN_1.unk")) {
			return new String[] { "BojackTransformed", "Costume_1" };
		}
		if (filename.equals("ƒxƒr[ƒxƒW[ƒ^.unk")) {
			return new String[] { "BabyVegeta", "Costume_1" };
		}
		if (filename.equals("ƒC[ƒVƒ“ƒƒ“_0.unk")) {
			return new String[] { "SynShenron", "Costume_1" };
		}
		if (filename.equals("ƒC[ƒVƒ“ƒƒ“_1.unk")) {
			return new String[] { "SynShenronOmega", "Costume_1" };
		}
		if (filename.equals("ƒsƒ‰ƒtƒ}ƒVƒ“_0.unk")) {
			return new String[] { "PilafRobot", "Costume_1" };
		}
		if (filename.equals("ƒsƒ‰ƒtƒ}ƒVƒ“_1.unk")) {
			return new String[] { "PilafRobotFusion", "Costume_1" };
		}
		if (filename.equals("ƒsƒbƒRƒ‘å–‚‰¤.unk")) {
			return new String[] { "DemonKingPiccolo", "Costume_1" };
		}
		for (int i = 1423; i <= 3032; i++) {
			if (!filename.contains("" + i)) {
				continue;
			}
			int n = i - 1423;
			int fighterIndex = n / 10;
			int fileIndex = n % 10;
			String fighterName = allFighterNames[fighterIndex];
			String fileType = "SFX";
			if (fileIndex <= 3) {
				fileType = "Costume_" + (fileIndex + 1);
			} else if (fileIndex <= 7) {
				fileType = "Costume_" + (fileIndex - 3) + "_Damaged";
			} else if (fileIndex == 8) {
				fileType = "Moveset";
			}
			return new String[] { fighterName, fileType };
		}
		return null;
	}
	
	public static String[] GetSlotNames(){
		String[] slotNames = new String[allFighterNames.length];
		for (int i = 0; i < slotNames.length; i++){
			slotNames[i] = "" + i + " - " + allFighterNames[i];
		}
		return slotNames;
	}

}
