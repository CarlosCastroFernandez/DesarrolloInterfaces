package com.example.gestionpedidoswithobjectdb;

import clases.Itempedido;
import clases.Pedido;
import clases.Producto;
import clases.Session;
import dao.ItemPedidoDAOImp;
import dao.PedidoDAOImp;
import dao.ProductoDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AñadirEditarView implements Initializable {
    @javafx.fxml.FXML
    private Spinner spinnerCantidad;
    @javafx.fxml.FXML
    private ComboBox<Producto> comboBoxProductos;
    @javafx.fxml.FXML
    private TextField textTotal;
    @javafx.fxml.FXML
    private Button botonAñadir;
    private Integer numeroPrecio;
    private Integer valorSpinner;
    @javafx.fxml.FXML
    private MenuItem menuVolver;
    @javafx.fxml.FXML
    private MenuItem menuFuera;
    private Integer contador;

    /**
     * Inicializa la ventana y carga datos relacionados con el pedido y sus items en la interfaz.
     *
     * @param url           La ubicación relativa del archivo FXML.
     * @param resourceBundle Recursos específicos de la localización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxProductos.setConverter(new StringConverter<Producto>() {
            @Override
            public String toString(Producto producto) {
                if(producto!=null){
                    return producto.getNombre();
                }else{
                    return null;
                }

            }

            @Override
            public Producto fromString(String s) {
                return null;
            }
        });
        if(Session.getItem()==null){
            comboBoxProductos.getItems().addAll(new ProductoDAOImp().getAllProducts());
            comboBoxProductos.getSelectionModel().selectFirst();
            spinnerCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Math.toIntExact((Long) (comboBoxProductos.getSelectionModel().getSelectedItem().getCantidad())),0,1));
            comboBoxProductos.getSelectionModel().selectedItemProperty().addListener((observableValue, producto, t1) -> {
                String[]precio=t1.getPrecio().split(" ");
                numeroPrecio=Integer.parseInt(precio[0]);
                textTotal.setText(""+(Integer)(spinnerCantidad.getValue())*numeroPrecio+"€");

            });
            spinnerCantidad.valueProperty().addListener((observable, o, nuevo) -> {
                if(numeroPrecio!=null){
                    textTotal.setText(""+((Integer)nuevo*numeroPrecio)+"€");
                }else{
                    String[]precio=comboBoxProductos.getSelectionModel().getSelectedItem().getPrecio().split(" ");
                    numeroPrecio=Integer.parseInt(precio[0]);
                    textTotal.setText(""+((Integer)nuevo*numeroPrecio)+"€");
                }

            });
        }else{
            comboBoxProductos.getItems().addAll(new ProductoDAOImp().getAllProducts());
            comboBoxProductos.setValue(Session.getItem().getProducto());
            spinnerCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Math.toIntExact((Long) (comboBoxProductos.getSelectionModel().getSelectedItem().getCantidad())),Session.getItem().getCantidad(),1));
            valorSpinner=(Integer)spinnerCantidad.getValue();
            String[]precioInicial=comboBoxProductos.getSelectionModel().getSelectedItem().getPrecio().split(" ");
            numeroPrecio=Integer.parseInt(precioInicial[0]);
            textTotal.setText(""+((Integer)spinnerCantidad.getValue()*numeroPrecio)+"€");
            comboBoxProductos.getSelectionModel().selectedItemProperty().addListener((observableValue, producto, t1) -> {
                String[]precio=t1.getPrecio().split(" ");
                numeroPrecio=Integer.parseInt(precio[0]);
                textTotal.setText(""+(Integer)(spinnerCantidad.getValue())*numeroPrecio+"€");

            });
            spinnerCantidad.valueProperty().addListener((observable, o, nuevo) -> {
                if(numeroPrecio!=null){
                    textTotal.setText(""+((Integer)nuevo*numeroPrecio)+"€");
                }else{
                    String[]precio=comboBoxProductos.getSelectionModel().getSelectedItem().getPrecio().split(" ");
                    numeroPrecio=Integer.parseInt(precio[0]);
                    textTotal.setText(""+((Integer)nuevo*numeroPrecio)+"€");
                }

            });
        }




    }

    /**
     * Funcion en la que puede implementar añadir editar o borrar dependiendo de la situación
     * y que cambia de ventana al terminar de hacer cualquier funcion
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void añadir(ActionEvent actionEvent) {
        checkUpOrDown();
        Session.setItem(null);
        if(contador!=null){
            HelloApplication.cambioVentana("ventana-usuario.fxml");
        }else{
            HelloApplication.cambioVentana("ventana-pedido.fxml");
        }



    }

    /**
     *Funcion en la que puede implementar añadir editar o borrar dependiendo de la situación
     */

    private void checkUpOrDown() {
        if(Session.getItem()==null&&Session.getPedido()==null){
            contador=1;
            ArrayList listaPedidos=new ArrayList();
            listaPedidos.addAll(Session.getUsuario().getPedidos());
            System.out.println(listaPedidos);
            Pedido pedido=new Pedido();
            SecureRandom ran=new SecureRandom();
            Integer numeroAzar=ran.nextInt(1000);
            while(listaPedidos.contains(""+numeroAzar)){
                 numeroAzar=ran.nextInt(1000);
            }
            pedido.setCodigo("Pedido-"+numeroAzar);
            DateTimeFormatter format=DateTimeFormatter.ofPattern("yyy-MM-dd");
            LocalDate fechaActual=LocalDate.now();
            String fechaConvertida=fechaActual.format(format);
            pedido.setFecha(fechaConvertida);
            pedido.setUsuario(Session.getUsuario());
            String precio=textTotal.getText().substring(0,textTotal.getText().indexOf("€"));
            System.out.println(precio);
            pedido.setTotal(Integer.valueOf(precio));
            pedido=(new PedidoDAOImp().save(pedido));
            Itempedido item=new Itempedido();
            item.setProducto(comboBoxProductos.getSelectionModel().getSelectedItem());
            item.setCantidad((Integer)spinnerCantidad.getValue());
            item.setPedido(pedido);
            item=(new ItemPedidoDAOImp().save(item));
        }else if(Session.getItem()!=null){

            Session.getItem().setProducto(comboBoxProductos.getValue());
            Session.getItem().setCantidad((Integer)spinnerCantidad.getValue());
            Integer numeroTotal=0;
            Integer contador=0;
            for(int i=0;i<Session.getItem().getPedido().getItems().size();i++){
                String[] precio=Session.getItem().getPedido().getItems().get(i).getProducto().getPrecio().split(" ");
               numeroTotal+=(Session.getItem().getPedido().getItems().get(i).getCantidad()*Integer.valueOf(precio[0]));
               contador++;
            }
            if(contador==1){
                Session.getItem().getPedido().setTotal(numeroPrecio*(Integer)spinnerCantidad.getValue());
                contador=0;
            }else if(contador!=1 &&valorSpinner!=spinnerCantidad.getValue()){
                Integer numeroDefinitivo=numeroTotal;
                Session.getItem().getPedido().setTotal(numeroDefinitivo);
                contador=0;
            }else if(contador!=1&&valorSpinner==spinnerCantidad.getValue()){
                Integer numeroDefinitivo=numeroTotal;
                Session.getItem().getPedido().setTotal(numeroDefinitivo);
                contador=0;
            }

            ItemPedidoDAOImp actualizacion=new ItemPedidoDAOImp();
            actualizacion.update(Session.getItem());


        }else if(Session.getPedido()!=null&&Session.getItem()==null){
            Itempedido item=new Itempedido();
            item.setProducto(comboBoxProductos.getSelectionModel().getSelectedItem());
            item.setCantidad((Integer)spinnerCantidad.getValue());
            item.setPedido(Session.getPedido());
            Session.getPedido().getItems().add(item);
            Integer numeroTotal=0;
            Integer contador=0;
            for(int i=0;i<Session.getPedido().getItems().size();i++){
                String[] precio=Session.getPedido().getItems().get(i).getProducto().getPrecio().split(" ");
                numeroTotal+=(Session.getPedido().getItems().get(i).getCantidad()*Integer.valueOf(precio[0]));
                contador++;
            }
            if(contador==1){
                item.getPedido().setTotal(numeroPrecio*(Integer)spinnerCantidad.getValue());
                contador=0;
            }else{
                Integer numeroDefinitivo=numeroTotal;
                item.getPedido().setTotal(numeroDefinitivo);
                contador=0;
            }
            if((Integer)spinnerCantidad.getValue()!=0){
                item=(new ItemPedidoDAOImp().save(item));
            }

        }
    }

    /**
     * Funciion que ermite volver a la ventana usuario
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        Session.setItem(null);
        HelloApplication.cambioVentana("ventana-usuario.fxml");
    }

    /**
     * Funcion en la que te deslogueas
     * @param actionEvent
     */

    @javafx.fxml.FXML
    public void logOut(ActionEvent actionEvent) {
        Session.setUsuario(null);
        Session.setPedido(null);
        Session.setItem(null);
        HelloApplication.cambioVentana("login.fxml");
    }
}
