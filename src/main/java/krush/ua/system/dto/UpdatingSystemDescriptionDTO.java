package krush.ua.system.dto;

import krush.ua.country.Country;
import krush.ua.system.System;
import krush.ua.system.software.type.enums.SoftwareType;
import krush.ua.technical.mapper.Mapper;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatingSystemDescriptionDTO implements Mapper<UpdatingSystemDescriptionDTO, System> {

    private Integer id;

    private String name;

    private String developer;

    private boolean availabilityOfDecisionMakingClassifier;

    private Integer releaseDate;

    private String description;


    private SoftwareType type;

    private Country country;


    @Override
    public UpdatingSystemDescriptionDTO toDto(System entity) {
        if(entity.getType() == null)
            entity.setType(krush.ua.system.software.type.SoftwareType.builder().id(null).build());
        return UpdatingSystemDescriptionDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .developer(entity.getDeveloper())
                .availabilityOfDecisionMakingClassifier(entity.isAvailabilityOfDecisionMakingClassifier())
                .releaseDate(entity.getReleaseDate())
                .type(mapSoftwareTypeToDOTById(entity.getType().getId()))
                .country(entity.getCountry())
                .build();
    }

    @Override
    public System toEntity(UpdatingSystemDescriptionDTO dto) {
        return System.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .developer(dto.getDeveloper())
                .releaseDate(dto.getReleaseDate())
                .availabilityOfDecisionMakingClassifier(dto.isAvailabilityOfDecisionMakingClassifier())
                .type(krush.ua.system.software.type.SoftwareType.builder().id(dto.mapSoftwareTypeToEntityById(dto.getType())).build())
                .country(dto.getCountry())
                .build();
    }

    private SoftwareType mapSoftwareTypeToDOTById(Integer id){
        if(id == null)
            return null;
        return switch (id) {
            case 1 -> SoftwareType.WEB;
            case 2 -> SoftwareType.WEB_SHELL;
            case 3 -> SoftwareType.DESKTOP_SOFTWARE;
            case 4 -> SoftwareType.DESKTOP_SOFTWARE_AND_WEB_SHELL;
            default -> null;
        };
    }


    private Integer mapSoftwareTypeToEntityById(SoftwareType type){
        if(type == null)
            return 0;
        return switch (type) {
            case WEB -> 1;
            case WEB_SHELL -> 2;
            case DESKTOP_SOFTWARE -> 3;
            case DESKTOP_SOFTWARE_AND_WEB_SHELL -> 4;
            default -> null;
        };
    }
}
