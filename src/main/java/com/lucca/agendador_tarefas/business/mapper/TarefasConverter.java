package com.lucca.agendador_tarefas.business.mapper;

import com.lucca.agendador_tarefas.business.dto.TarefasDTO;
import com.lucca.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefaDTO(TarefasEntity tarefasEntity);

    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);
}
