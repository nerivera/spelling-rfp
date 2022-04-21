package org.hcpss.inst.nriver5714.aood.spellingrfp;

import java.io.FileNotFoundException;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Configuration.load(Spelling::runGUI);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
