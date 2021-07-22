package hello;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello, Spring!"));
    }

    public ResponseEntity<Resource> translate() {
        Resource file = new ClassPathResource("D:\\Documents\\APCS\\demoparser\\output.txt") ;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData(file.getFilename(), file.getFilename());
        return ResponseEntity
                .ok().cacheControl(CacheControl.noCache())
                .headers(headers).body(file);
    }
}

