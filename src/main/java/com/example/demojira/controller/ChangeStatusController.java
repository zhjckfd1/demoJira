package com.example.demojira.controller;

import com.example.demojira.dto.ChangeStatusGetDto;
import com.example.demojira.service.ChangeStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Tag(name = "Смена статуса задачи", description = "Показывает разрешенные варианты смены статуса задачи")
public class ChangeStatusController {

    private final ChangeStatusService changeStatusService;

    @Autowired
    public ChangeStatusController(ChangeStatusService changeStatusService) {
        this.changeStatusService = changeStatusService;
    }

    @Operation(
            summary = "Получение смен статуса задачи",
            description = "Позволяет получить все возможные смены статуса задачи"
    )
    @RequestMapping(value = "/changesStatus", method = RequestMethod.GET)
    public ResponseEntity<List<ChangeStatusGetDto>> readAll() {
        final List<ChangeStatusGetDto> changes = changeStatusService.getAll();
        return new ResponseEntity<>(changes, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение смен статуса задачи по id начального статуса",
            description = "Позволяет получить все возможные смены статуса задачи с заданным id начального статуса"
    )
    @RequestMapping(value = "/changesStatus/beginStatus/{beginStatusId}", method = RequestMethod.GET)
    public ResponseEntity<List<ChangeStatusGetDto>> readAllByBaseStatus(
            @PathVariable(name = "beginStatusId")
            @Parameter(description = "id начального статуса")
            @Min(1)
                    Integer beginStatusId) {
        final List<ChangeStatusGetDto> changes = changeStatusService.getAllByBeginTaskStatusId(beginStatusId);
        return new ResponseEntity<>(changes, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение смен статуса задачи по id конечного статуса",
            description = "Позволяет получить все возможные смены статуса задачи с заданным id конечного статуса"
    )
    @RequestMapping(value = "/changesStatus/endStatus/{endStatusId}", method = RequestMethod.GET)
    public ResponseEntity<List<ChangeStatusGetDto>> readAllByEndStatus(
            @PathVariable(name = "endStatusId")
            @Parameter(description = "id конечного статуса")
            @Min(1)
                    Integer endStatusId) {
        final List<ChangeStatusGetDto> changes = changeStatusService.getAllByEndTaskStatusId(endStatusId);
        return new ResponseEntity<>(changes, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение смену статуса задачи по ее id",
            description = "Позволяет получить смену статуса задачи по ее id"
    )
    @RequestMapping(value = "/changesStatus/{id}", method = RequestMethod.GET)
    public ResponseEntity<ChangeStatusGetDto> read(
            @PathVariable(name = "id") @Parameter(description = "id смены статуса") @Min(1) Integer id) {
        final ChangeStatusGetDto change = changeStatusService.getById(id);
        return new ResponseEntity<>(change, HttpStatus.OK);
    }
}
