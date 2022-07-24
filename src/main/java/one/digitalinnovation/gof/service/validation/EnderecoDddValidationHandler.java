package one.digitalinnovation.gof.service.validation;

import java.util.Arrays;
import java.util.stream.Collectors;
import one.digitalinnovation.gof.model.Endereco;

public class EnderecoDddValidationHandler implements ValidationHandler{

  private enum validUfDdd {SP1(11), SP2(12), SP3(13), SP4(14), SP5(15), SP6(16), SP7(17), SP8(18), SP9(19),
    RJ1(21), RJ2(22), RJ3(24),
    ES1(27), ES2(28),
    MG1(31), MG2(32), MG3(33), MG4(34), MG5(35), MG6(37), MG7(38),
    PR1(41), PR2(42), PR3(43), PR4(44), PR5(45), PR6(46),
    SC1(47), SC2(48), SC3(49),
    RS1(51), RS2(53), RS3(54), RS4(55),
    GO1(62), GO2(64),
    MT1(65), MT2(66),
    BA1(71), BA2(73), BA3(74), BA4(75), BA5(77),
    PE1(81), PE2(87),
    CE1(85), CE2(88),
    PI1(86), PI2(89),
    PA1(91), PA2(93), PA3(94),
    AM1(92), AM2(97),
    MA1(98), MA2(99),
    DF1(61), TO1(63), MS1(67), AC1(68), RO1(69), SE1(79), AL1(82), PB1(83), RN1(84), RR1(95), AP1(96);

    private final int val;

    validUfDdd(int i) {
      this.val = i;
    }

    public int getVal(){
      return this.val;
    }
  }


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
        throw new Exception("O DDD do endereço não pode ser nulo");

      if(!Arrays.stream(validUfDdd.values()).map(validUfDdd::getVal).collect(Collectors.toList()).contains(Integer.parseInt(endereco.getDdd()))){
        throw new Exception("O DDD informado é inválido");
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
