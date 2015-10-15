package main.java.util;

public enum SqlInjectionRegex {

	META_CHARACTERS ("/(\\\\%27)|(\\\\')|(\\\\-\\\\-)|(\\\\%23)|(#)/ix "),
	META_CHARACTERS_MODIFIED ("/((\\\\%3D)|(=))[^\\\\n]*((\\\\%27)|(\\\\')|(\\\\-\\\\-)|(\\\\%3B)|(;))/i "),
	TYPICAL_SQL_INJECTION ("/\\\\w*((\\\\%27)|(\\\\'))((\\\\%6F)|o|(\\\\%4F))((\\\\%72)|r|(\\\\%52))/ix "),
	UNION_KEYWORD("	/((\\\\%27)|(\\\\'))union/ix"),
	KEYWORDS ("/SHOW|CREATE|USE|DESCRIBE|DROP|INSERT|DELETE|UPDATE|SELECT|JOIN/ix");
	
	private final String regex;
	
	SqlInjectionRegex(String regex){
		this.regex = regex;
	}
	String regex() { return regex; }
}
