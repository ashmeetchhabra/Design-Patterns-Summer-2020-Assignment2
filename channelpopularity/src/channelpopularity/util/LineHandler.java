package channelpopularity.util;

import channelpopularity.operation.Operation;

public class LineHandler {
	public Operation lineProcessor(String line) {

		String s[] = line.split("::");
		System.out.println(s[0] + "     " + s[1]);
		if (!(Constants.ADD_VIDEO.equals(s[0]) || (Constants.REMOVE_VIDEO.equals(s[0])))) {
			String m[]=s[0].split("__");
			System.out.println(m[0] + "     " + m[1]);
			//line starts with add_videos.. arrar of enum
			
		}
		return null;
		
	}

}
