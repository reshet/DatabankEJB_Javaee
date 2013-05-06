/*
/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mresearch.databank.jobs;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author reshet
 */
public class IndexVarsBulkJob implements Serializable{
    private ArrayList<Long> ids;
  
    
    public IndexVarsBulkJob( ArrayList<Long> var_ids)
    {
        this.ids = var_ids;
    }
    public IndexVarsBulkJob(){}

    /**
     * @return the ids
     */
    public ArrayList<Long> getIds() {
        return ids;
    }

    /**
     * @param ids the ids to set
     */
    public void setIds(ArrayList<Long> ids) {
        this.ids = ids;
    }

   
}
