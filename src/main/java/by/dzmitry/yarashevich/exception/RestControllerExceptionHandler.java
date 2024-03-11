package by.dzmitry.yarashevich.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "by/dzmitry/yarashevich/controllers")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
}
