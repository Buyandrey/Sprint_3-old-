import com.example.scooterRegisterCourier;
import org.junit.Test;

public class testScooterRegisterCourier {
    @Test
    public void test() {
        scooterRegisterCourier s = new scooterRegisterCourier();
        System.out.println( s.registerNewCourierAndReturnLoginPassword());
    }
}
