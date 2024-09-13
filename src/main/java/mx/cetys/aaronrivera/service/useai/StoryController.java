package mx.cetys.aaronrivera.service.useai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;


@ResponseBody
@Controller
public class StoryController {

    private final ChatClient singularidad;


    public StoryController(ChatClient.Builder singularidad) {
        this.singularidad = singularidad.build();
    }

    @GetMapping("/story")
    Map<String,String> story(){
        var prompt = """
                Querida Singularidad,
                
                Por favor cuentame una historia sobre mis alumnos de la carrera  ingenieria en software  en "CETYS" en Ensenada
                que quieren conseguir trabajo remoto en Accenture cuando pasen mi clase.
                
                Y porfavor haslo en el estilo del gran famoso escritor de historias para niños, Dr. Seuss
       
                Cordialmente,
                El maestro Aaron
                """;

        var response = this.singularidad
                .prompt()
                .user(prompt)
                .call()
                .content();

        return Map.of("story", response);
    }

}

@Configuration
class Example {
    ApplicationRunner applicationRunner() {
        return args -> {

            // Jose Paumard, Oracle
            var threads = new ArrayList<Thread>();
            var names = new ConcurrentSkipListSet<String>();

            for (var i = 0; i < 1000; i++) {
                var first = i == 0;
                var t = Thread.ofPlatform().unstarted(
                        () -> {
                            if (first) names.add(Thread.currentThread().toString());
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (first) names.add(Thread.currentThread().toString());
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (first) names.add(Thread.currentThread().toString());
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (first) names.add(Thread.currentThread().toString());
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
                threads.add(t);
            }
            for (var t : threads) t.start();
            for (var t : threads) t.join();
            System.out.println("Aaron Threads " + names);
        };
    }
}




















