package krush.ua.system.dto;

import krush.ua.classifier.computer.systems.ClassifierComputerSystem;
import krush.ua.environmental.monitoring.classifier.EnvironmentalMonitoringClassifier;
import krush.ua.system.System;
import krush.ua.system.expert.function.ExpertFunction;
import krush.ua.system.literature.Literature;
import krush.ua.technical.mapper.Mapper;
import lombok.*;

import java.util.Set;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatingSystemSupplementsDTO implements Mapper<UpdatingSystemSupplementsDTO, System> {

    private Integer id;
    private String name;
    private Set<ExpertFunction> expertFunctions;
    private Set<EnvironmentalMonitoringClassifier> environmentalMonitoringClassifiers;
    private Set<Literature> literatures;
    private Set<ClassifierComputerSystem> classifierComputerSystems;


    @Override
    public UpdatingSystemSupplementsDTO toDto(System entity) {
        return UpdatingSystemSupplementsDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .expertFunctions(entity.getExpertFunctions())
                .classifierComputerSystems(entity.getClassifierComputerSystems())
                .environmentalMonitoringClassifiers(entity.getEnvironmentalMonitoringClassifiers())
                .literatures(entity.getLiteratures())
                .build();
    }

    @Override
    public System toEntity(UpdatingSystemSupplementsDTO dto) {
        return System.builder()
                .id(dto.getId())
                .build();
    }
}
