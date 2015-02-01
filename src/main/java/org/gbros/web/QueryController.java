package org.gbros.web;

import com.alibaba.fastjson.JSON;

import org.gbros.io.Query;
import org.gbros.io.QueryFactory;
import org.gbros.io.QueryFactoryBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;


@Path("query")
public class QueryController {
  
  private QueryFactoryBuilder builder = new QueryFactoryBuilder();
  private QueryFactory queryFactory = builder.build();

  /**
   * get data by statement's name.
   * @param statementName
   * @param param
   * @return
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{name}")
  public String getCollection(@PathParam("name")final String name, 
      @Context UriInfo ui) {
    try {
      System.out.println("current query is : " + name);
      Query query = queryFactory.openQuery(name);
      MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
      Map<String,List<String>> paramMap = new HashMap<String,List<String>>();
      if (queryParams != null) {
        for (Entry<String,List<String>> entry : queryParams.entrySet()) {
          paramMap.put(entry.getKey(), entry.getValue());
        }
      }
      List<Map<String,Object>> list = query.getCollection(paramMap);
      System.out.println(list.size());
      return JSON.toJSONString(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "{\"error\":\"查询错误\"}";
  }
  
}
