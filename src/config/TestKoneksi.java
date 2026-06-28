/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;
import java.sql.Connection;
/**
 *
 * @author najwa
 */
public class TestKoneksi {
    public static void main(String[]args) {
        Connection conn = (Connection) Koneksi.getConnection();
        
        if(conn !=null) {
            System.out.println("Database berhasil terhubung.");
        } else {
            System.out.println("Database gagal terhubung.");
        }
    }
}
