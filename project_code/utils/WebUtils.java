package utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
public class WebUtils {
	public static String getSourceCode(String toSearch) {
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
		catch(Exception e) {
			return "Page not found.";
		}
	}
}