package main.java.util;

public class SqlInjectionValidator {

	public static boolean validateString(String s){
		for(SqlInjectionRegex regex : SqlInjectionRegex.values()){
			if(s.matches(regex.regex())) return false;
		}
		return true;
	}
}
