package com.ower.dsyz.logCenter.client.filter;

import java.util.ArrayList;

public class OrderArrayList extends ArrayList<String>{
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean add(String e) {
    	int i = 0;
        for(i=0;i<this.size();i++){
        	if(this.get(i).length()<e.length()){
        		break;
        	}
        }
        this.add(i, e);
        return true;
    }
}
