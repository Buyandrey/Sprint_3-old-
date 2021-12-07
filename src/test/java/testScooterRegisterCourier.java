import com.example.scooterRegisterCourier;
import org.junit.Test;

public class testScooterRegisterCourier {
    @Test
    public void test() {
        scooterRegisterCourier s = new scooterRegisterCourier();
        System.out.println( s.registerNewCourierAndReturnLoginPassword().size());
        System.out.println( s.registerNewCourierAndReturnLoginPassword().get(0));
        System.out.println( s.registerNewCourierAndReturnLoginPassword().get(1));

    }
}
