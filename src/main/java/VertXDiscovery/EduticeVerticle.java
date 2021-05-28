package VertXDiscovery;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class EduticeVerticle extends AbstractVerticle {
	
	String pkgName = "";
	String baseURL = "https://community.chocolatey.org/api/v2/Packages()?" + pkgName;
	
	@Override
	public void start () throws Exception {
		
		int code = eduticeAppSearch("$filter=Id%20eq%20%27firefox%27%20&$top=3");
		
		String status = (code == 200) ? "OK" : "NONE";
		vertx.eventBus().send("connexion.querry", new JsonObject()
				.put("Querry_Code", code)
				.put("Querry_status", status)
		);
		System.out.println("EduticeVerticle : Message Sent !");
		
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println("EduticeVerticle : BasicVerticle stopped");
	}
	
	public int eduticeAppSearch(String appName) throws IOException {
		
		URL pkgURL = new URL(baseURL + appName);
		System.out.println("eduticeAppSearch : requesting URL...  " + pkgURL);
        HttpURLConnection connection = (HttpURLConnection)pkgURL.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int code = connection.getResponseCode();
        System.out.println("Response code of the object is " + code);
        if (code==200)
        {
            System.out.println("OK");
        }
        return code;
		
	}
	
}