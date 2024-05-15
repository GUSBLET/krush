package krush.ua.system.software.type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoftwareTypeService {
    private final SoftwareTypeRepository softwareTypeRepository;

    public SoftwareType findByName(String name){
        return softwareTypeRepository.findByName(name).orElse(null);
    }

    public SoftwareType findById(Integer id){
        return softwareTypeRepository.findById(id).orElse(null);
    }
}
