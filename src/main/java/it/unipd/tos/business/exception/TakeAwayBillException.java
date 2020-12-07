////////////////////////////////////////////////////////////////////
// [Damiano] [Mason] [1201160]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Throwable {
    private String message;

    public TakeAwayBillException(String exc){
        this.message = exc;
    }

    public String getMessage(){
        return message;
    }
}