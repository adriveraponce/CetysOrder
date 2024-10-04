package mx.cetys.aaronrivera.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;

import java.util.Map;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		Map.of(
				"server.tomcat.threads.max", "16",
				"jdk.virtualThreadScheduler.maxPoolSize", "16",
				"spring.threads.virtual.enabled", Boolean.toString(true)
		).forEach(System::setProperty);


		SpringApplication.run(ServiceApplication.class, args);
	}

	@Bean
	RestClient restClient(RestClient.Builder builder) {
		return builder.build();
	}

}

//@Controller
//@ResponseBody
//class NewStoryContoller{
//
//	private final ChatClient singularity;
//
//    NewStoryContoller(ChatClient.Builder singularity) {
//        this.singularity = singularity.build();
//    }
//
//	@GetMapping("/newstory")
//	Map<String, String> story(){
//
//		var prompt = """
//				Querida Singularidad,
//
//				Por favor escribe una historia con la que le pueda enseñar virtual threads con Java 21 para
//				mis alumnos de ingenieria de software,
//
//				al estilo del famoso escritor Stephen King.
//
//				Gracias,
//				Aaron.""";
//
//		var response = this.singularity
//				.prompt()
//				.user(prompt)
//				.call()
//				.content();;
//
//		return Map.of("story", response);
//	}
//}

@Controller
@ResponseBody
class HttpbinClientController {

	private final RestClient restClient;

	HttpbinClientController(RestClient restClient) {
		this.restClient = restClient;
	}

	@GetMapping("/{seconds}")
	Map<String, String> call(@PathVariable int seconds) {
		var result = restClient
				.get()
				.uri("http://localhost:9000/delay/" + seconds)
				.retrieve()
				.body(String.class);

		return Map.of("thread", Thread.currentThread() + "", "reply", result);

	}
}

