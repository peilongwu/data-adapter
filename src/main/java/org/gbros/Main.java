package org.gbros;

import org.gbros.builder.Resources;
import org.gbros.builder.xml.XmlConfigBuilder;
import org.gbros.config.Config;
import org.gbros.utils.PathKit;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.InputStream;
import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 */
public class Main {

  public static final String BASE_URI = "http://localhost:8090/myapp/";

  /**
   * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
   * application.
   * 
   * @return Grizzly HTTP server.
   */
  public static HttpServer startServer() {
    // create a resource config that scans for JAX-RS resources and providers
    final ResourceConfig rc = new ResourceConfig().packages("org.gbros.web");
    System.out.println(PathKit.getWebRootPath() + "\\src\\main\\webapp");
    StaticHttpHandler statichandler = new StaticHttpHandler(PathKit.getWebRootPath() +
        "\\src\\main\\webapp");
    HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    server.getServerConfiguration().addHttpHandler(statichandler);
    return server;
  }

  /**
   * Main method.
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    final HttpServer server = startServer();
    try {
      InputStream inStream = Resources.getResourceAsStream("config.xml");
      XmlConfigBuilder configBuilder = new XmlConfigBuilder(inStream);
      Config.configuration = configBuilder.parse();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(String.format("Jersey app started with WADL available at " 
            + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
    System.out.println(server.getHttpHandler());
    
    System.in.read();
    server.shutdown();
  }
}
