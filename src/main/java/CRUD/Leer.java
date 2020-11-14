package CRUD;

import utils.Consultas;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Leer {
    Consultas consultas = new Consultas();

    public ArrayList ConsultarProductos(){
        ArrayList<String> arrayList = null;
        String Query="call TodosProductos();";
        ResultSet resultSet=consultas.Conexion(Query);
        if (resultSet!=null)
        {
            try{
                while (resultSet.next()){
                    arrayList.add(
                            resultSet.getString("IdProducto")+"#"+
                                    resultSet.getString("Nombre")+"#"+
                                    resultSet.getString("Cantidad")+"#"+
                                    resultSet.getString("PrecioCompra")+"#"+
                                    resultSet.getString("PrecioVenta")
                    );
                }
                consultas.cerrarConexion();
            }catch (Exception e){
                e.printStackTrace();
                consultas.cerrarConexion();
            }
        }
        return arrayList;
    }

    public ArrayList ConsultarVentas(){
        ArrayList<String> arrayList = null;
        String Query="call ConsVentas();";
        ResultSet resultSet=consultas.Conexion(Query);
        if (resultSet!=null)
        {
            try{
                while (resultSet.next()){
                    arrayList.add(
                            resultSet.getString("IdFactura")+"#"+
                                    resultSet.getString("Fecha")+"#"+
                                    resultSet.getString("IdUsuario")+"#"+
                                    resultSet.getString("Total")
                    );
                }
                consultas.cerrarConexion();
            }catch (Exception e){
                e.printStackTrace();
                consultas.cerrarConexion();
            }
        }
        return arrayList;
    }

    public ArrayList ConsultarCompras(){
        ArrayList<String> arrayList = null;
        String Query="call ConsCompras();";
        ResultSet resultSet=consultas.Conexion(Query);
        if (resultSet!=null)
        {
            try{
                while (resultSet.next()){
                    arrayList.add(
                            resultSet.getString("IdCompra")+"#"+
                                    resultSet.getString("IdUsuario")+"#"+
                                    resultSet.getString("Fecha")
                    );
                }
                consultas.cerrarConexion();
            }catch (Exception e){
                e.printStackTrace();
                consultas.cerrarConexion();
            }
        }
        return arrayList;
    }


    public int ConsultarNumeros(String query){
        int resultado=0;
        ResultSet resultSet=consultas.Conexion(query);
        try{
            if (resultSet!=null&&resultSet.next()){
                resultado=resultSet.getInt("resultado");
                consultas.cerrarConexion();
                return resultado;
            }else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            consultas.cerrarConexion();
            return 0;
        }
    }

    public int TotalVentas(){
        return ConsultarNumeros("call TotalVentas();");
    }
}