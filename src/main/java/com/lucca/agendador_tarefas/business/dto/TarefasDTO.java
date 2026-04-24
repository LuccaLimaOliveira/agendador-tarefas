package com.lucca.agendador_tarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucca.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTO {

    @Id
    private String id;
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
