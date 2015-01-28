package org.gbros.web;

import com.alibaba.fastjson.JSON;

import org.gbros.io.Param;
import org.gbros.io.Query;
import org.gbros.utils.StringKit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;


@Path("query")
public class QueryController {

  /**
   * get data by statement's name.
   * @param statementName
   * @param param
   * @return
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("q/{name}")
  public String getCollection(@PathParam("name")final String name, 
      @Context UriInfo ui) {
    System.out.println("current query is : " + name);
    Query query = new Query(name);
    List<Param> params = null;
    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
    if (queryParams != null) {
      params = new ArrayList<Param>();
      for (Entry<String,List<String>> entry : queryParams.entrySet()) {
        if (StringKit.isNotBlank(entry.getKey())) {
          String key = entry.getKey();
          String value = null;
          for (String val : entry.getValue()) {
            value = val;
          }
          System.out.println("param key is : " + key);
          System.out.println("param value is : " + value);
          params.add(new Param(key, value));
        }
      }
    }
    List<Map<String,Object>> list2 = query.getCollection(params);
    System.out.println(list2.size());
    return JSON.toJSONString(list2);
  }
  
  /**
   * get data by statement's name.
   * @param statementName
   * @param param
   * @return
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("c/{name}")
  public String getCollection(@PathParam("name")final String name, 
      @QueryParam("param") final String param) {
    System.out.println(name);
    Query query = new Query(name);
    List<Param> params = null;
    if (StringKit.isNotBlank(param)) {
      params = new ArrayList<Param>();
      params.add(new Param("param", param));
    }
    List<Map<String,Object>> list2 = query.getCollection(params);
    System.out.println(list2.size());
    return JSON.toJSONString(list2);
  }
}
