package com.example.ManutencaoTi.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ManutencaoTi.model.Equipamento;
import com.example.ManutencaoTi.repository.EquipamentoRepository;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository repository;

    public Equipamento save(Equipamento equipamento) {
        // Adicionar 1 dia à data de chegada
        equipamento.setDataChegada(adicionarUmDia(equipamento.getDataChegada()));

        // Adicionar 1 dia à data de saída, se não for nula
        if (equipamento.getDataSaida() != null) {
            equipamento.setDataSaida(adicionarUmDia(equipamento.getDataSaida()));
        }

        return repository.save(equipamento);
    }

    public List<Equipamento> findAll() {
        return repository.findAll();
    }

    public List<Equipamento> search(Date startDate, Date endDate, String patrimonio) {
        // Adicionar 1 dia às datas de início e fim da pesquisa
        Date adjustedStartDate = adicionarUmDia(startDate);
        Date adjustedEndDate = adicionarUmDia(endDate);

        if (patrimonio == null || patrimonio.isEmpty()) {
            return repository.findByDataChegadaBetween(adjustedStartDate, adjustedEndDate);
        } else {
            return repository.findByDataChegadaBetweenAndPatrimonio(adjustedStartDate, adjustedEndDate, patrimonio);
        }
    }

    private Date adicionarUmDia(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
}
