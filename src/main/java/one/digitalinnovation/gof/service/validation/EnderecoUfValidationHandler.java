package one.digitalinnovation.gof.service.validation;

import java.util.Arrays;
import java.util.stream.Collectors;
import one.digitalinnovation.gof.model.Endereco;

public class EnderecoUfValidationHandler implements ValidationHandler{

  private enum validUf {RO, AC, AM, RR, PA, AP, TO, MA, PI, CE, RN, PB, PE, AL, SE, BA, MG, ES, RJ, SP, PR, SC, RS, MS, MT, GO, DF}

  private ValidationHandler nextHandler;

  @Override
  public void setNext(ValidationHandler nextHandler) {
    this.nextHandler = nextHandler;
  }

  @Override
  public void handle(Object request) throws Exception {
    if(canHandle(request)){
      Endereco endereco = (Endereco) request;
      if(endereco.getUf() == null)
        throw new Exception("A UF do endereço não pode ser nula");

      if(!Arrays.stream(validUf.values()).map(Enum::name).collect(Collectors.toList()).contains(endereco.getUf().toUpperCase())){
        throw new Exception("A UF informada é inválida");
      }

    }
    else{
      if(nextHandler != null)
        nextHandler.handle(request);
    }
  }

  private boolean canHandle(Object request){
    return request.getClass().equals(Endereco.class);
  }
}
