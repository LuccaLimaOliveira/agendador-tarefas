package com.lucca.agendador_tarefas.controller;

import com.lucca.agendador_tarefas.business.TarefasService;
import com.lucca.agendador_tarefas.business.dto.TarefasDTO;
import com.lucca.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    public final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> salvarTarefa(@RequestBody TarefasDTO tarefasDTO,
                                                   @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){

        return ResponseEntity.ok(tarefasService.buscarTarefaPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorEmail(@RequestHeader("Authorization") String token){
        List<TarefasDTO> tarefas = tarefasService.buscarTarefaPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id){
        tarefasService.deletarTarefaPorId(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStatusTarefa(@RequestParam("status")StatusNotificacaoEnum status,
                                                         @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.alteraStatusTarefa(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.updateTarefas(dto,id));
    }

}
