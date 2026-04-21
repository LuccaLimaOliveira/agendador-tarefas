package com.lucca.agendador_tarefas.business.mapper;

import com.lucca.agendador_tarefas.business.dto.TarefasDTO;
import com.lucca.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefaDTO(TarefasEntity tarefasEntity);
}
