package id.my.agungdh.atengambekbe.controller;

import id.my.agungdh.atengambekbe.DTO.NgambekDTO;
import id.my.agungdh.atengambekbe.entity.Ngambek;
import id.my.agungdh.atengambekbe.service.NgambekService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ngambek")
@RequiredArgsConstructor
public class NgambekController {
    private final NgambekService ngambekService;

    @GetMapping
    public ResponseEntity<List<NgambekDTO>> getAllNgambeks() {
        return ResponseEntity.ok(ngambekService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NgambekDTO> getNgambekById(@PathVariable UUID id) {
        return ResponseEntity.ok(ngambekService.getNgambek(id));
    }

    @PostMapping
    public ResponseEntity<Void> createNgambek(@RequestBody NgambekDTO dto) {
        ngambekService.createNgambek(dto);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNgambek(@PathVariable UUID id, @RequestBody NgambekDTO dto) {
        ngambekService.updateNgambek(id, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNgambek(@PathVariable UUID id) {
        ngambekService.deleteNgambek(id);

        return ResponseEntity.ok().build();
    }
}
