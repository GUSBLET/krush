package krush.ua.system;

import krush.ua.mapper.Mapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemCardDTO implements Mapper<SystemCardDTO, System> {

    private String name;

    private String country;

    private String softwareType;

    private String developer;

    @Override
    public SystemCardDTO toDto(System entity) {
        return SystemCardDTO.builder()
                .name(entity.getName())
                .country(entity.getCountry().getName())
                .developer(entity.getDeveloper())
                .softwareType(entity.getType().getName())
                .build();
    }

    @Override
    public System toEntity(SystemCardDTO dto) {
        return null;
    }
}
