package krush.ua.system;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SystemService {
    private final SystemRepository systemRepository;

    public Page<System> getTablePage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return systemRepository.findAllWithCountryAndType(pageable);
    }

    @Cacheable(value = "systems", key = "#id", unless = "#result == null")
    @Scheduled(fixedDelay = 300000)
    public System getSystemById(Integer id){
        return systemRepository.findByIdWithAssociations(id).orElse(null);
    }
}
