package exceptions;

public class EmptyInputException extends Exception{
    public EmptyInputException(){
        super("Поле пустое, введите значение");
    }
}
