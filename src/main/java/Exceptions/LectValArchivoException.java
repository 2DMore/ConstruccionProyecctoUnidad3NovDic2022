package Exceptions;

public class LectValArchivoException extends RuntimeException{

    public LectValArchivoException(){
        super();
    }
    public LectValArchivoException(Exception e){
        super(e);
    }
}
