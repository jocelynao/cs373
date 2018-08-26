/*package assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class runProg{

	public static void main(String[] args){
		//System.out.println("HELLO");

		String fileName = "";
		String str = "";
		String line = null;

		BufferedReader br = null;
		FileReader fr = null;

		if (args.length > 1 && args.length < 3){
			fileName = args[0];
			// for (int i = 0; i < args.length; i++){
			// 	System.out.println(args[i]);
			// }
			// // System.out.println("E");
			str = args[1];
			
			try {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);

				FA f1 = new FA();
				while ((line = br.readLine()) != null){
					//System.out.println("1");
					String[] lineSplit = line.split("\\s+");
				//	System.out.println(line);

					// for (int i = 0; i < lineSplit.length; i++){
					// 	lineSplit[i] = lineSplit[i].replaceAll("[^\\w]", "");
					// }

					if (lineSplit[0].equals("state")){
						if (lineSplit.length == 3){
							if (lineSplit[2].length() > 6){
								f1.addAccept(lineSplit[1]);
								f1.setStart(lineSplit[1], str);
							}
							else{
								if (lineSplit[2].equals("accept")){
									f1.addAccept(lineSplit[1]);
								}
								else{
									f1.setStart(lineSplit[1], str);
								}
							}
						}
						else{
							f1.addAccept(lineSplit[1]);
							f1.setStart(lineSplit[1], str);
						}
					}
					else{
						// System.out.println("PRIntiN");
						// System.out.println(lineSplit[1]);
						// System.out.println(lineSplit[2]);
						// System.out.println("E");
						f1.setTransitions(lineSplit[1] + lineSplit[2],
						 (lineSplit[3]));
					}
				}
				// f1.print();
				f1.configure();

				f1.printResults();
			}

			catch (IOException ex){
				System.out.println("File does not exist");
			}
		}

		else{
			System.out.println("This program takes in two arguments");
		}

	}

}*/