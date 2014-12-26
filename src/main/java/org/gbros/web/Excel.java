package org.gbros.web;

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
	    	String fileName = "D:/work_test/管道台账.xls";
	    	return excelReader.getObjectList(fileName).toJSONString();
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{sheet}")
    public String get(@PathParam("sheet") final String sheetName,@QueryParam("name")final String excelName) {
    	System.out.println("Excel name is" + excelName);
    	System.out.println("Sheet name is" + sheetName);
        return "in sheet";
    }
}
