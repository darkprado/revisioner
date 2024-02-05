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

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService service;
    private final ResponseErrorValidator validator;


    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody OperationDto dto, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = validator.mapValidation(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        return new ResponseEntity<>(service.save(dto, principal), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationDto> findById(@PathVariable Long id, Principal principal) {
        return new ResponseEntity<>(service.findById(id, principal), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OperationDto>> findAll(Principal principal) {
        return new ResponseEntity<>(service.findAll(principal), HttpStatus.OK);
    }

    @GetMapping("/currentMonth/all")
    public ResponseEntity<List<OperationDto>> findAllByCurrentMonth(Principal principal) {
        return new ResponseEntity<>(service.findAllByCurrentMonth(principal), HttpStatus.OK);
    }

    @GetMapping("/{yearNum}/{monthNum}/all")
    public ResponseEntity<List<OperationDto>> findAllByCurrentMonthAndPositive(
            @PathVariable int yearNum, @PathVariable int monthNum, @RequestParam @Nullable Boolean positive, Principal principal) {
        return Objects.isNull(positive)
                ? new ResponseEntity<>(service.findAllByMonth(yearNum, monthNum, principal), HttpStatus.OK)
                : new ResponseEntity<>(service.findAllByMonthAndPositive(yearNum, monthNum, positive, principal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id, Principal principal) {
        service.delete(id, principal);
    }

}
