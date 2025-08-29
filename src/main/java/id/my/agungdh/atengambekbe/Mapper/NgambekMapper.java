package id.my.agungdh.atengambekbe.Mapper;

import id.my.agungdh.atengambekbe.DTO.NgambekDTO;
import id.my.agungdh.atengambekbe.entity.Ngambek;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NgambekMapper {
    NgambekMapper INSTANCE = Mappers.getMapper(NgambekMapper.class);

    @Mapping(source = "uuid", target = "id")
    NgambekDTO toDTO(Ngambek entity);

    @Mapping(source = "id", target = "uuid")
    @Mapping(target = "id", ignore = true)
    Ngambek toEntity(NgambekDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uuid", ignore = true)
    })
    void updateEntityFromDto(NgambekDTO dto, @MappingTarget Ngambek entity);
}
