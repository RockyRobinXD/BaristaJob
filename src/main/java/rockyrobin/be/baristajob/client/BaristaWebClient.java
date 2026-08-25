package rockyrobin.be.baristajob.client;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rockyrobin.be.baristajob.dto.response.BaristaRestDTO;
import rockyrobin.be.baristajob.dto.response.OpleidingRestDTO;

public class BaristaWebClient {

    private static final String BASE_URL = "http://localhost:8080/api";

    private final WebClient webClient = WebClient.builder()
            .baseUrl(BASE_URL)
            .build();

    public BaristaWebClient() {
        System.out.println("\n------- GET BARISTAS IN GENT -------");
        getBaristasByStad("Gent")
                .doOnNext(System.out::println)
                .blockLast();

        System.out.println("\n------- GET BESCHIKBARE SHIFTS BARISTA 1 -------");
        getBeschikbareShifts(1L)
                .doOnNext(count -> System.out.println("Beschikbare shifts: " + count))
                .block();

        System.out.println("\n------- GET OPLEIDINGEN VESTIGING 1 -------");
        getOpleidingenByVestiging(1L)
                .doOnNext(System.out::println)
                .blockLast();
    }

    private Flux<BaristaRestDTO> getBaristasByStad(String stad) {
        return webClient.get()
                .uri(uri -> uri.path("/baristas").queryParam("stad", stad).build())
                .retrieve()
                .bodyToFlux(BaristaRestDTO.class);
    }

    private Mono<Long> getBeschikbareShifts(Long baristaId) {
        return webClient.get()
                .uri("/baristas/{id}/shifts/beschikbaar", baristaId)
                .retrieve()
                .bodyToMono(Long.class);
    }

    private Flux<OpleidingRestDTO> getOpleidingenByVestiging(Long vestigingId) {
        return webClient.get()
                .uri("/vestigingen/{id}/opleidingen", vestigingId)
                .retrieve()
                .bodyToFlux(OpleidingRestDTO.class);
    }

    public static void main(String[] args) {
        new BaristaWebClient();
    }
}