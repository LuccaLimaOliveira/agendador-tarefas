package com.lucca.agendador_tarefas.business;

import com.lucca.agendador_tarefas.business.dto.TarefasDTO;
import com.lucca.agendador_tarefas.business.mapper.TarefaUpdateConverter;
import com.lucca.agendador_tarefas.business.mapper.TarefasConverter;
import com.lucca.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.lucca.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.lucca.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.lucca.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.lucca.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefa (String token, TarefasDTO tarefasDTO){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefasDTO.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(tarefasDTO);

        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscarTarefaPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefaConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscarTarefaPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDTO(listaTarefas);
    }

    public void deletarTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id! Id inesxistente." + id,
                    e.getCause());
        }
    }

    public TarefasDTO alteraStatusTarefa(StatusNotificacaoEnum status, String id){
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada!" + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa!" + e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id){
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada!" + id));
            tarefaUpdateConverter.updateTarefas(dto, entity);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao dar um update na tarefa!" + e.getCause());
        }
    }
}
