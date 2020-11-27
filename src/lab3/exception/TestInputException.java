package lab3.exception;

public class TestInputException extends Exception {

    TestInputException(String s){
        super(s);
    }

    public static void validateName(String name)throws TestInputException{
        if(name.contains("!") || name.contains(".") || name.contains(",")) {
            throw new TestInputException("nume invalid");
        }
        else
            System.out.println("nume valid");
    }

    public static void validateID(long id) throws TestInputException{
        if(id == 0){
            throw new TestInputException("ID invalid");
        }
        else
            System.out.println("ID valid");
    }
}
