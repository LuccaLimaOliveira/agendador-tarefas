package com.lucca.agendador_tarefas.business.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.lucca.agendador_tarefas.business.dto.TarefasDTO;
import com.lucca.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "Spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface TarefaUpdateConverter {

    void updateTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity);
}
