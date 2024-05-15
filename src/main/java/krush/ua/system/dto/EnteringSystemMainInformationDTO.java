package krush.ua.system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import krush.ua.country.Country;
import krush.ua.system.software.type.SoftwareType;
import krush.ua.technical.mapper.Mapper;
import lombok.*;
import krush.ua.system.System;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnteringSystemMainInformationDTO implements Mapper<EnteringSystemMainInformationDTO, System> {

    @NotBlank
    private String name;
    @NotBlank
    private String developer;
    private String description;
    @NotBlank
    private String country;
    @NotNull
    private krush.ua.system.software.type.enums.SoftwareType type;
    @NotNull
    private boolean availabilityOfDecisionMakingClassifier;
    @NotNull
    private Integer releaseDate;


    @Override
    public EnteringSystemMainInformationDTO toDto(System entity) {
        return null;
    }

    @Override
    public System toEntity(EnteringSystemMainInformationDTO dto) {
        return System.builder()
                .releaseDate(dto.getReleaseDate())
                .name(dto.getName())
                .type(SoftwareType.builder().name(dto.getType().name()).build())
                .developer(dto.getDeveloper())
                .availabilityOfDecisionMakingClassifier(dto.isAvailabilityOfDecisionMakingClassifier())
                .country(Country.builder().name(dto.getCountry()).build())
                .description(dto.getDescription())
                .build();
    }
}
