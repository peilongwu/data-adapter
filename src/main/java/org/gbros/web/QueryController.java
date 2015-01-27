package org.gbros.web;

import com.alibaba.fastjson.JSON;
import org.gbros.io.Param;
import org.gbros.io.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("query")
public class QueryController {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("statement/{statementName}")
  public String getByStatementName(@PathParam("statementName")final String statementName, 
      @QueryParam("param") final String param) {
    Query query = new Query("rdb","test");
    List<Param> params = new ArrayList<Param>();
    params.add(new Param("param","046098d326ad4904b0fb91bdb0127909"));
    List<Map<String,Object>> list2 = query.getByStatementName("user_query", params);
    System.out.println(list2.size());
    return JSON.toJSONString(list2);
  }
}
