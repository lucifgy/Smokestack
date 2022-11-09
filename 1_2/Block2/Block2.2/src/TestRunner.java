import ex.secondTestClass;
import io_null_arithmetic.firstTestClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("First Class test");
        res(firstTestClass.class);
        System.out.println("Second Class test");
        res(secondTestClass.class);
    }
    public static void res(Class<?>... classes)
    {
        Result result = JUnitCore.runClasses(classes);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}