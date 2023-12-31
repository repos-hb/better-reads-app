package com.hb.betterreadsapp.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Controller
public class SearchController {
	
	private static final String IMAGE_ROOT = "http://covers.openlibrary.org/b/id/";

	private final WebClient webClient;
	
	public SearchController(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.exchangeStrategies(ExchangeStrategies.builder()
		        .codecs(configurer -> configurer
		                  .defaultCodecs()
		                  .maxInMemorySize(16 * 1024 * 1024))
		                .build()).baseUrl("http://openlibrary.org/search.json").build();
	}
	
	@GetMapping("/search")
	public String getSearchResults(@RequestParam String query, Model model) {
		Mono<SearchResult> resultsMono = this.webClient.get()
									.uri("?q={query}", "query")
									.retrieve()
									.bodyToMono(SearchResult.class);
		
		SearchResult results = resultsMono.block();
		List<SearchResultBook> books = results.getDocs().stream()
				.limit(10)
				.map(bookresult -> {
					bookresult.setKey(bookresult.getKey().replace("/works/", ""));
					String coverId = bookresult.getCover_i();
					if(StringUtils.hasText(coverId)) {
						coverId = IMAGE_ROOT + coverId + "-M.jpg";
					} else {
						coverId = "/images/no-image.png";
					}
					return bookresult;
				})
				.collect(Collectors.toList());
		
		model.addAttribute("searchResults", books);
		return "search";
	}

}
