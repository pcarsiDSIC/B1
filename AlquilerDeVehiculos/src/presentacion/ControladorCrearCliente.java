package presentacion;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logica.*;

public class ControladorCrearCliente extends ControladorCasoDeUso { 
	@FXML 
	private TextField dni; 
	@FXML 
	private TextField nombreApellidos; 
	@FXML 
	private TextField direccion; 
	@FXML 
	private TextField anyoTC; 
	@FXML 
	private TextField mesTC; 
	@FXML 
	private TextField codigoPostal; 
	@FXML 
	private TextField poblacion; 
	@FXML 
	private DatePicker fechaCarnet; 
	@FXML 
	private TextField cvc; 
	@FXML
	private TextField tipoTarjeta; 
	@FXML 
	private TextField digitosTC; 
	@FXML 
	public Button aceptar; 
	@FXML 
	private Button cancelar; 
	public Cliente nuevoCliente; 
	int anyo;
	int mes;
	int dia;
	String hora="00:00:00";
	String completo;
	@Override 
	public void initialize(URL location, ResourceBundle resources) { 
		
		stage = new Stage(StageStyle.DECORATED); 
		stage.setTitle("CREAR CLIENTE"); 
		cancelar.setOnAction(event -> {
			Stage miStage = (Stage) aceptar.getScene().getWindow();
			miStage.close();
			stage.close();
			}); 
		
		aceptar.setOnAction(event -> { 
			Stage miStage = (Stage) aceptar.getScene().getWindow();
        	String error="";
        	if (dni.getText().trim().length() == 0) {
            	error += "Introduce el DNI\n";
            }
        	if (dni.getText().trim().length()>0 && dni.getText().trim().length() != 9) {
            	error += "Introduce correctamente el DNI: 123456789A\n";
            }
        	if (AlquilerVehiculos.getAlquilerVehiculos().buscarCliente(dni.getText().trim().toUpperCase())!=null) {
            	error += "El cliente ya existe\n";
            }
        	if (nombreApellidos.getText().trim().length() == 0) {
                error += "Introduce el nombre y los apellidos\n";
            }
        	if (nombreApellidos.getText().trim().length() > 20) {
                error += "La longitud m�xima para el nombre es de 20 car�cteres\n";
            }
        	if (direccion.getText().trim().length() == 0) {
                error += "Introduce la direcci�n\n";
            }
        	if (direccion.getText().trim().length() > 20) {
                error += "La longitud m�xima para la direcci�n es de 20 car�cteres\n";
            }
        	if (codigoPostal.getText().trim().length() == 0) {
                error += "Introduce el c�digo postal\n";
            }
        	if (codigoPostal.getText().trim().length()>0  && codigoPostal.getText().trim().length() != 5) {
                error += "La longitud del c�digo postal ha de ser de 5 d�gitos\n";
            }
        	if (poblacion.getText().trim().length() == 0) {
                error += "Introduce la poblaci�n\n";
            }
        	if (poblacion.getText().trim().length() > 20) {
                error += "La longitud m�xima para la poblaci�n es de 20 car�cteres\n";
            }
            if (fechaCarnet.getValue() == null) {
            	error += "Introduce la fecha del carnet\n";
            }
        	if (digitosTC.getText().trim().length() == 0) {
                error += "Introduce los d�gitos de la tarjeta de cr�dito\n";
            }
        	if (digitosTC.getText().trim().length()>0 && digitosTC.getText().trim().length() != 16) {
                error += "Introduce los 16 d�gitos de la tarjeta de cr�dito sin espacios\n";
            }
        	if (anyoTC.getText().trim().length() == 0) {
                error += "Introduce el a�o de caducidad de la tarjeta de cr�dito\n";
            }
        	if (anyoTC.getText().trim().length()>0 && anyoTC.getText().trim().length() != 4) {
                error += "Error en el formato del a�o: AAAA\n";
            }
        	if (mesTC.getText().trim().length() == 0) {
                error += "Introduce el mes de caducidad de la tarjeta de cr�dito\n";
            }
        	if (mesTC.getText().trim().length()>0 && mesTC.getText().trim().length() != 2) {
                error += "Error en el formato del mes: MM\n";
            }
        	if (cvc.getText().trim().length() == 0) {
                error += "Introduce el CVC de la tarjeta de cr�dito\n";
            }
        	if (cvc.getText().trim().length()>0 && cvc.getText().trim().length() != 3) {
                error += "Introduce los 3 d�gitos del CVC de la tarjeta de cr�dito\n";
            }
        	if (tipoTarjeta.getText().trim().length() == 0) {
                error += "Introduce el tipo de la tarjeta de cr�dito\n";
            }
        	if (tipoTarjeta.getText().trim().length()>10) {
                error += "La longitud m�xima para el tipo de tarjeta es de 10 car�cteres\n";
            }
        	
        	if(error.length()!=0){
        		Alert alerta = new Alert(AlertType.ERROR);
        		alerta.initStyle(StageStyle.UNIFIED);
				alerta.setContentText(error);
				alerta.setHeaderText("Error");
				alerta.showAndWait();
        	}else{
			nuevoCliente = new Cliente(dni.getText().toUpperCase(), nombreApellidos.getText(), direccion.getText(),poblacion.getText(), codigoPostal.getText(), LocalDateTime.of(fechaCarnet.getValue(), LocalTime.MIDNIGHT), digitosTC.getText(), Integer.parseInt(mesTC.getText()), Integer.parseInt(anyoTC.getText()), Integer.parseInt(cvc.getText()), tipoTarjeta.getText()); 
			if (nuevoCliente != null) { 
			try {
				AlquilerVehiculos.getAlquilerVehiculos().crearCliente(nuevoCliente);
				Alert alerta = new Alert(AlertType.CONFIRMATION);
        		alerta.initStyle(StageStyle.DECORATED);
				alerta.setContentText("Cliente creado correctamente");
				alerta.setHeaderText("");
				alerta.showAndWait();
				stage.close();
				miStage.close();
			} catch (Exception e) {
				e.printStackTrace();
					} 
				} 
			}
		}); 
	}
}