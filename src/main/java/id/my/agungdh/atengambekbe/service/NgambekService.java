package id.my.agungdh.atengambekbe.service;

import id.my.agungdh.atengambekbe.DTO.NgambekDTO;
import id.my.agungdh.atengambekbe.Mapper.NgambekMapper;
import id.my.agungdh.atengambekbe.entity.Ngambek;
import id.my.agungdh.atengambekbe.repository.NgambekRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NgambekService {
    private final NgambekRepository repository;
    private final NgambekMapper mapper;

    public List<NgambekDTO> findAll() {
        List<Ngambek> ngambeks = repository.findAllByOrderByIdDesc();
        List<NgambekDTO> dtos = new ArrayList<>();
        for (Ngambek ngambek : ngambeks) {
            dtos.add(mapper.toDTO(ngambek));
        }

        return dtos;
    }

    public NgambekDTO getNgambek(UUID id) {
        Ngambek ngambek = repository.findByUuid(id).orElseThrow(() -> new EntityNotFoundException("Ngambek not found"));

        return mapper.toDTO(ngambek);
    }

    public void createNgambek(NgambekDTO dto) {
        Ngambek ngambek = mapper.toEntity(dto);
        repository.save(ngambek);
    }

    public void updateNgambek(UUID id, NgambekDTO dto) {
        Ngambek ngambek = repository.findByUuid(id).orElseThrow(() -> new EntityNotFoundException("Ngambek not found"));

        mapper.updateEntityFromDto(dto, ngambek);

        repository.save(ngambek);
    }

    public void deleteNgambek(UUID id) {
        Ngambek ngambek = repository.findByUuid(id).orElseThrow(() -> new EntityNotFoundException("Ngambek not found"));

        repository.delete(ngambek);
    }
}
