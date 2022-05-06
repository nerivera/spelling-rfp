package org.hcpss.inst.nriver5714.aood.spellingrfp;

import java.io.FileNotFoundException;

public class Runner {

	@SuppressWarnings("serial")
	public static class TooManyArgumentsException extends IllegalArgumentException {
		public TooManyArgumentsException(int numArgsPassed) {
			super("too many arguments; " + numArgsPassed + " arguments passed, no more than 1 argument expected");
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 0)
			Configuration.load(Spelling::runGUI);
		else if (args.length == 1)
			Configuration.load(args[0], Spelling::runGUI);
		else
			throw new TooManyArgumentsException(args.length);
	}

}
