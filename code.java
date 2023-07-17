package CODE;

import java.sql.*;
import java.util.Scanner


public class libros {

	public static void main(String[] args) {
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/catalogo";
		String USER = "root";
		String PASS = "";

		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String query = "CREATE TABLE IF NOT EXISTS libros ("
					+ "ID INT NOT NULL AUTO_INCREMENT, "
					+ "TITULO VARCHAR(200), "
					+ "AUTOR VARCHAR(200), "
					+ "PRIMARY KEY (id))";
			stmt.executeUpdate(query);
			
			String opcion = "";

			Scanner scan = new Scanner(System.in);

				System.out.println("OPCIONES PARA USUARIOS");
				System.out.println("----------------------");
				System.out.println("C Agregar libro");
				System.out.println("R Listar datos");
				System.out.println("U Editar datos de un libro");
				System.out.println("D Eliminar un libro");
				
				System.out.print("Eliga su opción: ");
				opcion = scan.nextLine();
				opcion = opcion.toUpperCase();

				switch (opcion) {

				case "C":

					// Agregar libro
					
					System.out.print("TÍTULO: ");
					String titulo = scan.nextLine();

					System.out.print("AUTOR: ");
					String autor = scan.nextLine();

					query = "INSERT INTO libros (TITULO, AUTOR) VALUES ('" + titulo + "','" + autor + "')";

					int result = stmt.executeUpdate(query);

					if (result > 0) {
						System.out.print("Inserción exitosa");
					}

					break;

				case "R":

					// Listar datos
					
					query = "SELECT * FROM libros ";
					ResultSet resu = stmt.executeQuery(query);
					
					while(resu.next()) {
						String id = resu.getString(1) ;
						String mititulo = resu.getString(2) ;
						String miautor = resu.getString(3) ;
						System.out.print(id +" ");
						System.out.print(mititulo +" ");
						System.out.print(miautor);
						System.out.println("");
					}

					break;
					
				case "U":
					
					// Editar datos de un libro
					
					System.out.print("ID del libro: ");
					int ID = scan.nextInt();
					scan.nextLine();
					System.out.print("NUEVO TITULO: ");
					titulo = scan.nextLine();
					System.out.print("NUEVO AUTOR: ");
					autor = scan.nextLine();
					query = "UPDATE libros SET TITULO = '" + titulo + "', AUTOR = '" + autor + "' WHERE ID = " + ID;
					int res = stmt.executeUpdate(query);

					if (res > 0) {
						System.out.println("ACTUALIZACIÓN CORRECTA");
					} else {
						System.out.println("ERROR");
					}

					break;

				case "D":

					// Eliminar un libro
					
					System.out.print("ID del libro: ");
					ID = scan.nextInt();
					query = "DELETE FROM libros WHERE ID = " + ID;
					result = stmt.executeUpdate(query);

					if (result > 0) {
						System.out.println("ELIMINADO");
					} else {
						System.out.println("ERROR");
					}

					break;
				}

			stmt.close();
			conn.close();
			scan.close();

		} catch (Exception error) {
			System.out.print("Error: " + error.getMessage());
		}
		

	}
	

}
