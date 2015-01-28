package org.gbros.web;

import com.alibaba.fastjson.JSON;

import org.gbros.io.Param;
import org.gbros.io.Query;
import org.gbros.utils.StringKit;

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

  /**
   * get data by statement's name.
   * @param statementName
   * @param param
   * @return
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{name}")
  public String getByStatementName(@PathParam("name")final String name, 
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
