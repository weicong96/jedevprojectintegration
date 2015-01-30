package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Settings {

	//private ArrayList<Map<String,String>> requiredSubmissionFields;
	private String mainDeadLine;
	private String servletPath;
	private static String dir = "C:\\Users\\Wei Cong\\IBM\\rationalsdp\\workspace\\FinalWeb\\WebContent";
	private static Settings instance;
	
	public static Settings getInstance(){
		if(instance == null)
			instance = loadSettings(dir);
		return instance;
	}
	public Settings(){}
	public String getServletPath() {
		return servletPath;
	}

	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}

	public static void setInstance(Settings instance) {
		Settings.instance = instance;
	}
/*
	public ArrayList<Map<String,String>>  getRequiredSubmissionFields() {
		return requiredSubmissionFields;
	}
	public void setRequiredSubmissionFields(ArrayList<Map<String,String>>  requiredSubmissionFields) {
		this.requiredSubmissionFields = requiredSubmissionFields;
	}*/
	public String getMainDeadLine() {
		return mainDeadLine;
	}
	public void setMainDeadLine(String mainDeadLine) {
		this.mainDeadLine = mainDeadLine;
	}
	private static Settings loadSettings(String path){
		Gson gson = new Gson();
		Settings s = new Settings();
	/*	s.setRequiredSubmissionFields(new ArrayList<Map<String,String>>(){{
			add(new HashMap<String,String>(){{
				put("Required","true");
				put("Format","application/zip");
			}});
		}});
		*/
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path+File.separator+"settings.json"));
			Settings settings = gson.fromJson(reader, Settings.class);
			return settings;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static boolean saveSettings(Settings settings,String path){
		try {
			FileWriter w = new FileWriter(path+File.separator+"settings.json");
			w.write(new Gson().toJson(settings));
			w.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static String getDir() {
		return dir;
	}
	public static void setDir(String dir) {
		Settings.dir = dir;
	}

}
