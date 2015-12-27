package com.h2y.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import freemarker.cache.TemplateLoader;

public class StringTemplateLoader implements TemplateLoader{

	private String template;   

	public StringTemplateLoader(){   
		
	}   
	
	public StringTemplateLoader(String template){   
		this.template = template;
	}   


	public void closeTemplateSource(Object templateSource) throws IOException {
		((StringReader)templateSource).close();   

	}

	public Object findTemplateSource(String arg0) throws IOException {
		return new StringReader(template);   

	}

	public long getLastModified(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Reader getReader(Object templateSource, String encoding) throws IOException {
		
		return (Reader) templateSource; 
	}


	public String getTemplate() {
		return template;
	}


	public void setTemplate(String template) {
		this.template = template;
	}

}
