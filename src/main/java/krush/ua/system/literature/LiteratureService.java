package krush.ua.system.literature;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiteratureService {
    private final LiteratureRepository literatureRepository;

    public Literature findByName(String name) {
        return literatureRepository.findByName(name).orElse(null);
    }

    public Literature save(Literature literature) {
        return literatureRepository.save(literature);
    }

    public void update(Literature literature) {
        literatureRepository.updateLiterature(literature.getId(), literature.getName(), literature.getAuthor(),
                literature.getDescription(), literature.getUrl(), literature.getDateOfLastAccess());
    }
}
