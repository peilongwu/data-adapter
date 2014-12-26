package org.gbros.web;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.gbros.io.ExcelReader;

/**
 * Root resource
 */
@Path("excel")
public class Excel {
	
	private ExcelReader excelReader = new ExcelReader();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@QueryParam("name")final String excelName) {
    	try{
	    	System.out.println("excel name is" + excelName);
	    	return excelReader.getList(excelName).toJSONString();
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{sheet}")
    public String get(@PathParam("sheet") final String sheetName, @QueryParam("name")final String excelName, 
    		@QueryParam("col") final Object col,@QueryParam("value") final Object value) {
    	try{
	    	System.out.println("excel name is" + excelName);
	    	System.out.println("sheet name is" + sheetName);
	    	Map<String,Object> filter = new HashMap<String,Object>();
	    	/*if(col != null){
		    	for(int i=0; i<col.length; i++){
		    		if(value != null && value.length > i){
		    			filter.put(col[i], value[i]);
		    		}
		    	}
	    	}
	    	return excelReader.getList(excelName, sheetName, filter).toJSONString();
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    } */
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{sheet}/{col}/{value}")
    public String get(@PathParam("sheet") final String sheetName, @PathParam("col") final String col, @PathParam("value") final String value,
    		@QueryParam("name")final String excelName) {
    	try{
	    	System.out.println("excel name is" + excelName);
	    	System.out.println("sheet name is" + sheetName);
	    	System.out.println("col name is" + col);
	    	System.out.println("value name is" + value);
	    	Map<String,Object> filter = new HashMap<String,Object>();
	    	filter.put(col, value);
	    	return excelReader.getList(excelName, sheetName, filter).toJSONString();
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
}
