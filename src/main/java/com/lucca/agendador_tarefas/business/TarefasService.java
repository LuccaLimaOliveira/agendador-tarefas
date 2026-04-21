package com.lucca.agendador_tarefas.business;

import com.lucca.agendador_tarefas.business.dto.TarefasDTO;
import com.lucca.agendador_tarefas.business.mapper.TarefasConverter;
import com.lucca.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.lucca.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.lucca.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.lucca.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa (String token, TarefasDTO tarefasDTO){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefasDTO.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(tarefasDTO);

        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }
}
