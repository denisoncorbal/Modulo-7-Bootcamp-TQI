package one.digitalinnovation.gof.service.validation;

public interface ValidationHandler {

  void setNext(ValidationHandler nextHandler);
  void handle(Object request) throws Exception;

}
