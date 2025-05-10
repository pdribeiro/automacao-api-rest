package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Caminho da feature
        glue = "steps",                          // Pacote onde estão os steps
        plugin = {"pretty", "summary"}, // Plugins de saída
        tags ="@todos"

)
public class RunCucumberTest {
}
