package com.mresearch.databank.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class VarDTO_Detailed extends VarDTO implements Serializable{
	public static String real_var_type = "REAL_VAR_TYPE";
	public static String alt_var_type = "ALT_VAR_TYPE";
	public static String text_var_type = "TEXT_VAR_TYPE";
	private int number_of_records = 0;
	private ArrayList<Integer> filtered_indecies;
	private ArrayList<Long> gen_vars_ids;
	private ArrayList<String> gen_var_names;
	private ArrayList<String> gen_research_names;
	private ArrayList<Long> gen_research_ids;
	private HashMap<String,String> filling,research_meta_filling;
        private String research_name;
	public HashMap<String, String> getFilling() {
		return filling;
	}

	public HashMap<String, String> getResearch_meta_filling() {
		return research_meta_filling;
	}

	public void setResearch_meta_filling(
			HashMap<String, String> research_meta_filling) {
		this.research_meta_filling = research_meta_filling;
	}

	public void setFilling(HashMap<String, String> filling) {
		this.filling = filling;
	}
	private String var_type;
	
	public VarDTO_Detailed()
	{
		super();
	}
	
	public ArrayList<Integer> getFiltered_indecies() {
		return filtered_indecies;
	}

	public void setFiltered_indecies(ArrayList<Integer> filtered_indecies) {
		this.filtered_indecies = filtered_indecies;
	}

	public ArrayList<Long> getGen_vars_ids() {
		return gen_vars_ids;
	}

	public void setGen_vars_ids(ArrayList<Long> gen_vars_ids) {
		this.gen_vars_ids = gen_vars_ids;
	}

	public ArrayList<String> getGen_var_names() {
		return gen_var_names;
	}

	public void setGen_var_names(ArrayList<String> gen_var_names) {
		this.gen_var_names = gen_var_names;
	}

	public ArrayList<String> getGen_research_names() {
		return gen_research_names;
	}

	public void setGen_research_names(ArrayList<String> gen_research_names) {
		this.gen_research_names = gen_research_names;
	}

	public String getVar_type() {
		return var_type;
	}

	public void setVar_type(String var_type) {
		this.var_type = var_type;
	}

	public int getNumber_of_records() {
		return number_of_records;
	}

	public void setNumber_of_records(int number_of_records) {
		this.number_of_records = number_of_records;
	}

	public ArrayList<Long> getGen_research_ids() {
		return gen_research_ids;
	}

	public void setGen_research_ids(ArrayList<Long> gen_research_ids) {
		this.gen_research_ids = gen_research_ids;
	}

    /**
     * @return the research_name
     */
    public String getResearch_name() {
        return research_name;
    }

    /**
     * @param research_name the research_name to set
     */
    public void setResearch_name(String research_name) {
        this.research_name = research_name;
    }

	
	
}
