import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class TestDemoTest {

  private TestDemo testDemo;

  @BeforeEach
  void setUp() {
    testDemo = new TestDemo();
  }

  static Stream<Arguments> argumentsForAddPositive() {
    // @formatter:off
    return Stream.of(
        arguments(17, 13, 30, false),
        arguments(10, 0, 20, true),
        arguments(-20, 10, 100, true),
        arguments(100, 100, 200, false),
        arguments(-100, -100, -200, true),
        arguments(50, 50, 95 , true)
        );
    // @formatter:on
  }


  @ParameterizedTest
  @MethodSource("TestDemoTest#argumentsForAddPositive")
  void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected,
      boolean expectException) {
    // Given: 2 positive numbers
    // When: those numbers are summed
    // Then: we assert that the sum of those numbers is equal to the expected number, and if it
    // isn't, we assert that the exception thrown is of type "IllegalArgumentException".
    if (!expectException) {
      assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
    } else {
      assertThatThrownBy(() -> testDemo.addPositive(a, b))
          .isInstanceOf(IllegalArgumentException.class);
    }

  }

  @Test
  void assertThatNumberSquaredIsCorrect() {
    // Given: a random number picked. In this case, the number picked is 5
    TestDemo mockDemo = spy(testDemo);
    doReturn(5).when(mockDemo).getRandomInt();

    // When: The random number gets multiplied by itself (squared)
    int fiveSquared = mockDemo.randomNumberSquared();

    // Then: We verify that the multiplication is correct
    assertThat(fiveSquared).isEqualByComparingTo(25);


  }



}
