package org.among.domain;

import org.among.domain.template.Template;

public class Survey {
	private String id;
	
	private String authorId;
	
	private Type type;
	
	private Template template;
	
	public enum Type {
		OBJECTIVE, SUBJECTIVE;
		
		public static Type from(String value) {
			return Type.valueOf(value.toUpperCase());
		}
	}
}
