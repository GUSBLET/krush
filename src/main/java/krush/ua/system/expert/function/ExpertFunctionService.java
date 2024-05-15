package krush.ua.system.expert.function;

import krush.ua.system.literature.Literature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpertFunctionService {
    private final ExpertFunctionRepository expertFunctionRepository;

    public ExpertFunction findByName(String name) {
        return expertFunctionRepository.findByName(name).orElse(null);
    }

    public ExpertFunction save(ExpertFunction expertFunction) {
        return expertFunctionRepository.save(expertFunction);
    }
}
