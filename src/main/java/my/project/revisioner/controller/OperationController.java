package my.project.revisioner.controller;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.project.revisioner.dto.OperationDto;
import my.project.revisioner.service.OperationService;
import my.project.revisioner.validation.ResponseErrorValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService service;
    private final ResponseErrorValidator validator;


    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody OperationDto dto, BindingResult bindingResult) {
        ResponseEntity<Object> errors = validator.mapValidation(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OperationDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/currentMonth/all")
    public ResponseEntity<List<OperationDto>> findAllByCurrentMonth() {
        return new ResponseEntity<>(service.findAllByCurrentMonth(), HttpStatus.OK);
    }

    @GetMapping("/{yearNum}/{monthNum}/all")
    public ResponseEntity<List<OperationDto>> findAllByCurrentMonthAndPositive(
            @PathVariable int yearNum, @PathVariable int monthNum, @RequestParam @Nullable Boolean positive) {
        return Objects.isNull(positive)
                ? new ResponseEntity<>(service.findAllByMonth(yearNum, monthNum), HttpStatus.OK)
                : new ResponseEntity<>(service.findAllByMonthAndPositive(yearNum, monthNum, positive), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.delete(id);
    }

}
