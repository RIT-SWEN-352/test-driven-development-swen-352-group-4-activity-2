package edu.rit.swen352.tdd.easy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import edu.rit.swen352.tdd.easy.Temperature;

/**
 * Test suite for the {@link Temperature} component.
 */
class TemperatureTest {

//
//    ----CONSTRUCTOR TESTS----
//

    @ParameterizedTest(name = "Test constructor with \"{0}\" units")
    @EnumSource(Temperature.TemperatureUnit.class)
    @DisplayName("Test adding the different temperature units.")
    public void testTemperatureUnits(Temperature.TemperatureUnit unit) {
        //setup
        Temperature temp1 = new Temperature(0, unit);

        //testing
        assertAll("Initial temperature values",
            () -> { assertEquals(0, temp1.getValue(), "Initial value should be 0"); },
            () -> { assertEquals(unit, temp1.getUnit(), "Initial unit should be " + unit); }
        );
    }

    @ParameterizedTest(name = "Test constuctor with \"{0}\" value")
    @ValueSource(doubles = { -100, 0, 100, 1000})
    @DisplayName("Test adding the different temperature values.")
    public void testTemperatureValues(double value) {
        //setup
        Temperature temp1 = new Temperature(value);

        //testing
        assertAll("Initial temperature values",
            () -> { assertEquals(value, temp1.getValue(), "Initial value should be " + value); },
            () -> { assertEquals(Temperature.TemperatureUnit.CELSIUS, temp1.getUnit(), "Initial unit should be Celsius"); }
        );
    }

    @ParameterizedTest(name = "Test convertTo() method with \"{0}\" starting temperature")
    @CsvSource({
        "-100, -148.0, 173.15",
        "0, 32.0, 273.15",
        "100, 212.0, 373.15",
        "1000, 1832.0, 1273.15"
    })
    @DisplayName("Test convertTo() method with different starting temperatures.")
    public void testConvertTo(double value, double expectedFahrenheit, double expectedKelvin) {
        //setup
        Temperature temp1 = new Temperature(value);

        //testing
        assertAll("Initial temperature values",
            () -> { assertEquals(value, temp1.getValue(), "Initial value should be " + value); },
            () -> { assertEquals(Temperature.TemperatureUnit.CELSIUS, temp1.getUnit(), "Initial unit should be Celsius"); }
        );

        //convert to Fahrenheit
        Temperature temp2 = temp1.convertTo(Temperature.TemperatureUnit.FAHRENHEIT);
        assertAll("Converted temperature values",
            () -> { assertEquals(expectedFahrenheit, temp2.getValue(), "Converted value should be " + expectedFahrenheit); },
            () -> { assertEquals(Temperature.TemperatureUnit.FAHRENHEIT, temp2.getUnit(), "Converted unit should be Fahrenheit"); }
        );

        //convert to Kelvin
        Temperature temp3 = temp1.convertTo(Temperature.TemperatureUnit.KELVIN);
        assertAll("Converted temperature values",
            () -> { assertEquals(expectedKelvin, temp3.getValue(), "Converted value should be " + expectedKelvin); },
            () -> { assertEquals(Temperature.TemperatureUnit.KELVIN, temp3.getUnit(), "Converted unit should be Kelvin"); }
        );
    }

    @Test
    @DisplayName("Test toString() method.")
    public void testToString() {
        //setup
        Temperature temp1 = new Temperature(25);
        Temperature temp2 = new Temperature(77, Temperature.TemperatureUnit.FAHRENHEIT);
        Temperature temp3 = new Temperature(298.15, Temperature.TemperatureUnit.KELVIN);

        //testing
        assertAll("toString() method",
            () -> { assertEquals("25.0°C", temp1.toString(), "toString() should return 25.0°C"); },
            () -> { assertEquals("77.0°F", temp2.toString(), "toString() should return 77.0°F"); },
            () -> { assertEquals("298.15K", temp3.toString(), "toString() should return 298.15K"); }
        );
    }
}

