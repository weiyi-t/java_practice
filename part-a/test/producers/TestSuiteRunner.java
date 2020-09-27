package producers;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestSuiteRunner {
  public static void main(String[] args) {
    Result result =
        JUnitCore.runClasses(
            producers.Question1Tests.class,
            producers.Question2Tests.class,
            producers.Question3Tests.class,
            producers.Question4Tests.class,
            producers.Question5Tests.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
  }
}
