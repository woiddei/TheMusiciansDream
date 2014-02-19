package utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
public class WebUtils {
	/**
	 * Retrieves a web page's source from it's URL, assuming address is legal and
	 * internet connection is valid.
	 * @param toSearch a URL for searching.
	 * @return the source code of the page, if found.
	 * @throws Exception if adress is invalid or if internet connection is unavailable. 
	 */
	public static String getSourceCode(String toSearch) throws Exception {
		BufferedReader in = null;
		try{
			URL url=new URL(toSearch);
			URLConnection urlc=url.openConnection();
			in = new BufferedReader(new InputStreamReader(urlc.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuilder a = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
				a.append(inputLine);
			in.close();
			return a.toString();
		}
		finally {
			if(in!=null)
				in.close();
		}
	}
}