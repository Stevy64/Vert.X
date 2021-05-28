package VertXDiscovery;

import java.io.IOException;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;

public class HttpVerticle extends AbstractVerticle {

	String baseURL = "community.chocolatey.org";
	String apiURL = "/api/v2/package/";
	String pkgName = "";
	public int code = 0;
	
	@Override
	public void start (Future<Void> startFuture) throws Exception {
		System.out.println("HttpVerticle : You can access the baseURL HERE : " + baseURL);
				
		// On écoute l'Event Bus pour récupérer la liste des applications
		EventBus eb = vertx.eventBus();
		
		MessageConsumer<String> consumer = eb.consumer("connexion3", message -> {
			pkgName = message.body();
			System.out.println("HttpVerticle : I have received : " + pkgName);
			try {
				message.reply("HttpVerticle REPLIED : " + eduticeAppSearch(pkgName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
        });
		
		System.out.println("HttpVerticle : Finished !");
		
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println("HttpVerticle stopped");
	}
	
	@SuppressWarnings("deprecation")
	public int eduticeAppSearch(String appName) throws IOException {
		
		// Creation du verticle HTTP Client avec le nom de domaine chocolatey en option
		HttpClientOptions options = new HttpClientOptions().setDefaultHost(baseURL);
		HttpClient httpClient = vertx.createHttpClient(options);
				
		// Requete pour vérifier l'existance du paquet
		httpClient.get(apiURL + pkgName, response -> {
			code = response.statusCode();
			if (code == 200) {
				System.out.println("HttpVerticle : package (" + pkgName + ") exists");
			  }
			else {
				System.out.println("HttpVerticle : package (" + pkgName + ") doesn't exist");
			}
		}).setFollowRedirects(true).end();
		
		return code;
		
	}
	
}