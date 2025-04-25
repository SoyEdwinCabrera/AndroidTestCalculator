package org.grupo1;


import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {

    private AndroidDriver driver;
    private WebDriverWait wait;
    private ArrayList<WebElement> numPad;
  
    @BeforeEach // Para asegurar que siempre se inicie en la pantalla principal
    public void setUp() {
        createConexion();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        abrirApp();

    }
	
  	
    public void initializeNumPad() {
        numPad = new ArrayList<WebElement>(10);
        for (int i = 0; i < 10; i++) {
            WebElement numero = wait
                    .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("" + i)));
            numPad.add(numero);
        }
    }
  
    public void createConexion() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setPlatformName("Android");
        try {
            driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);

        } catch (Exception e) {
            System.out.println("An error with the connection was found");
        }
    }

    public void abrirApp() {
        driver.activateApp("com.google.android.calculator");

        WebElement el1 = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("clear")));
        assertTrue(el1.isDisplayed());
    }

    private void limpiarCalculadora() {
        driver.findElement(AppiumBy.accessibilityId("clear")).click();
    }

    @Test
    @Order(1)
    public void realizarSuma() {
        System.out.println(
            """            
            **********************************
            Inicio test: realizarSuma
            **********************************
            """
        );
        WebElement firstNumber = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("8")));
        WebElement secondNumber = wait
                .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("3")));
        WebElement plus = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("plus")));
        WebElement equal = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("equals")));

        firstNumber.click();
        plus.click();
        secondNumber.click();
        equal.click();

        WebElement result = wait.until(ExpectedConditions
                .presenceOfElementLocated(AppiumBy.id("com.google.android.calculator:id/result_final")));

        assertTrue(result.getText().equals("11"));
    }
  
  	/// Sumar con digitos multiples
//       @ParameterizedTest
//     @CsvSource({
//             "4,8,12",
//             "8,6,14",
//     })
//     @Order(1)
//     public void realizarSuma(String numeroUno, String numeroDos, String resultado) {

//         WebElement firstNumber = numPad.get(Integer.parseInt(numeroUno));
//         WebElement secondNumber = numPad.get(Integer.parseInt(numeroDos));
//         WebElement plus = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("plus")));
//         WebElement equal = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("equals")));

//         firstNumber.click();
//         plus.click();
//         secondNumber.click();
//         equal.click();

//         WebElement result = wait.until(ExpectedConditions
//                 .presenceOfElementLocated(AppiumBy.id("com.google.android.calculator:id/result_final")));

//         assertTrue(result.getText().equals(resultado));
//     }

    @Test
    @Order(2)
    public void realizarMultiplicacion() {
        System.out.println(
            """            
            ************************************
            Inicio test: realizarMultiplicacion
            ************************************
            """
        );
        WebElement firstNumber = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("8")));
        WebElement secondNumber = wait
                .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("3")));
        WebElement multiply = wait
                .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("multiply")));
        WebElement equal = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("equals")));

        firstNumber.click();
        multiply.click();
        secondNumber.click();
        equal.click();

        WebElement result = wait.until(ExpectedConditions
                .presenceOfElementLocated(AppiumBy.id("com.google.android.calculator:id/result_final")));

        assertTrue(result.getText().equals("24"));
    }

    @Test
    @Order(3)
    public void realizarDivision() {
        System.out.println(
            """            
            **********************************
            Inicio test: realizarDivision
            **********************************
            """
        );
        WebElement firstNumber = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("6")));
        WebElement secondNumber = wait
                .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("2")));
        WebElement divide = wait
                .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("divide")));
        WebElement equal = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("equals")));

        firstNumber.click();
        divide.click();
        secondNumber.click();
        equal.click();

        WebElement result = wait.until(ExpectedConditions
                .presenceOfElementLocated(AppiumBy.id("com.google.android.calculator:id/result_final")));

        assertTrue(result.getText().equals("3"));
    }

  
  
    @Test
    @Order(4)
    public void operarConDecimales() {
        System.out.println(
            """            
            **********************************
            Inicio test: operarConDecimales
            **********************************
            """
        );
        WebElement firstNumber = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("7")));
        WebElement point = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("point")));

        WebElement secondNumber = wait
                .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("4")));

        WebElement suma = wait
                .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("plus")));
        WebElement equal = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("equals")));

        firstNumber.click();
        point.click();
        secondNumber.click();
        suma.click();
        secondNumber.click();
        point.click();
        firstNumber.click();
        equal.click();

        WebElement result = wait.until(ExpectedConditions
                .presenceOfElementLocated(AppiumBy.id("com.google.android.calculator:id/result_final")));

        assertTrue(result.getText().equals("12.1"));
    }

    @Test
    @Order(5)
    public void realizarDivisionPorCero() {
        System.out.println(
            """            
            *************************************
            Inicio test: realizarDivisionPorCero
            *************************************
            """
        );
        WebElement firstNumber = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("6")));
        WebElement secondNumber = wait
                .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("0")));
        WebElement divide = wait
                .until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("divide")));
        WebElement equal = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("equals")));

        firstNumber.click();
        divide.click();
        secondNumber.click();
        equal.click();

        WebElement postResult = wait.until(ExpectedConditions
                .presenceOfElementLocated(AppiumBy.id("com.google.android.calculator:id/result_preview")));

        assertTrue(postResult.getText().equals("Can't divide by 0"));
    }

    @Test
    @Order(6)
    public void realizarSumaConNumerosAleatorios() {
        System.out.println(
            """            
            **********************************************
            Inicio test: realizarSumaConNumerosAleatorios
            **********************************************
            """
        );
        limpiarCalculadora();
    
        Random random = new Random();
        int numeroUno = random.nextInt(9999) + 1;
        int numeroDos = random.nextInt(9999) + 1;
    
        int resultadoEsperado = numeroUno + numeroDos;
    
        System.out.println("Número 1: " + numeroUno);
        System.out.println("Número 2: " + numeroDos);
        System.out.println("Resultado esperado: " + resultadoEsperado);
    
        ingresarNumero(numeroUno);
        WebElement plus = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("plus")));
        plus.click();
        ingresarNumero(numeroDos);
        WebElement equal = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("equals")));
        equal.click();
    
        WebElement result = wait.until(ExpectedConditions
                .presenceOfElementLocated(AppiumBy.id("com.google.android.calculator:id/result_final")));
    
        System.out.println("Resultado obtenido: " + result.getText());
    
        assertTrue(result.getText().equals(String.valueOf(resultadoEsperado)),
                "El resultado esperado era " + resultadoEsperado + " pero se obtuvo " + result.getText());
    }
    
    private void ingresarNumero(int numero) {
        String numeroComoTexto = String.valueOf(numero);
        for (char digito : numeroComoTexto.toCharArray()) {
            WebElement boton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(String.valueOf(digito))));
            boton.click();
        }
    }
  
}