package moddingApp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckForSimilarBytes {

	public static void main(String[] args) {

		File metaC = new File("filesToLookAt/Metal_(3322).unk");
		File saiba = new File("filesToLookAt/Saibam(3273).unk");
		File celljr = new File("filesToLookAt/Cell_J(3303).unk");

		File devil = new File("filesToLookAt/Akuman(3345).unk");
		File appule = new File("filesToLookAt/Appule(3292).unk");
		File babidi = new File("filesToLookAt/Babidi(3304).unk");
		File bardock = new File("filesToLookAt/Bardoc(3107).unk");
		File chichi = new File("filesToLookAt/Chichi(3351).unk");
		File dabura = new File("filesToLookAt/Dabura(3305).unk");

		try {
			byte[] metaCContent = Files.readAllBytes(metaC.toPath());
			byte[] saibaContent = Files.readAllBytes(saiba.toPath());
			byte[] celljrContent = Files.readAllBytes(celljr.toPath());

			List<Integer> possibleIndices = new ArrayList<Integer>();

			for (int i = 0; i < metaCContent.length && i < saibaContent.length && i < celljrContent.length; i++) {
				if (metaCContent[i] == saibaContent[i] && saibaContent[i] == celljrContent[i]) {
					possibleIndices.add(i);
				}
			}

			System.out.println("Number of possible matching bytes: " + possibleIndices.size());
			List<Integer> toRemove = new ArrayList<Integer>();
			Map<Integer, Byte> differentBytes = new HashMap<Integer, Byte>();

			File[] files = { devil, appule, babidi, bardock, chichi, dabura };
			for (int k = 0; k < files.length; k++) {
				byte[] movesetContent = Files.readAllBytes(files[k].toPath());

				for (int i = 0; i < possibleIndices.size(); i++) {
					if (possibleIndices.get(i) >= movesetContent.length) {
						toRemove.add(possibleIndices.get(i));
						continue;
					}
					if (movesetContent[possibleIndices.get(i)] == metaCContent[possibleIndices.get(i)]) {
						toRemove.add(possibleIndices.get(i));
					}
					if (differentBytes.containsKey(possibleIndices.get(i))) {
						if (differentBytes.get(possibleIndices.get(i)) != movesetContent[possibleIndices.get(i)]) {
							toRemove.add(possibleIndices.get(i));
						}
					} else {
						differentBytes.put(possibleIndices.get(i), movesetContent[possibleIndices.get(i)]);
					}
				}
				for (int i = 0; i < toRemove.size(); i++) {
					possibleIndices.remove((Integer) toRemove.get(i));
				}
				if (possibleIndices.size() <= 0) {
					break;
				}
			}
			System.out.println("Actually matching bytes: ");
			for (int i = 0; i < possibleIndices.size(); i++){
				System.out.println("Byte " + possibleIndices.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
