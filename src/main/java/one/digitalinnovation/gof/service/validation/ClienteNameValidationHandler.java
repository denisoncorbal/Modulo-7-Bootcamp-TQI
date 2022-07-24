package one.digitalinnovation.gof.service.validation;

import one.digitalinnovation.gof.model.Cliente;

public class ClienteNameValidationHandler implements ValidationHandler{

  private ValidationHandler nextHandler;

  @Override
  public void setNext(ValidationHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  @Override
  public void handle(Object request) throws Exception{
    if(canHandle(request)){
      Cliente cliente = (Cliente) request;
      if(cliente.getNome() == null || cliente.getNome().isEmpty() || cliente.getNome().isBlank())
        throw new Exception("Nome do cliente não pode ser nulo");
    }
    else{
      if(nextHandler != null)
        nextHandler.handle(request);
    }
  }

  private boolean canHandle(Object request){
    return request.getClass().equals(Cliente.class);
  }
}
