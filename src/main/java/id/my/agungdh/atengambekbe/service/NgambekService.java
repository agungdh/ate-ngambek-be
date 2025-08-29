package id.my.agungdh.atengambekbe.service;

import id.my.agungdh.atengambekbe.DTO.NgambekDTO;
import id.my.agungdh.atengambekbe.entity.Ngambek;
import id.my.agungdh.atengambekbe.repository.NgambekRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class NgambekService {
    private NgambekRepository repository;

//    public NgambekDTO getNgambek(UUID id) {
//        Ngambek ngambek = repository.getNgambeksByUuid(id);
//    }
}
