package krush.ua.country;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public Country findByName(String name) {
        return countryRepository.findByName(name).orElse(null);
    }

    public Country save(String name){
        return countryRepository.save(Country.builder().name(name).build());
    }
}
