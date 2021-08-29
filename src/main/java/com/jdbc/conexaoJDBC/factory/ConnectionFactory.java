package com.jdbc.conexaoJDBC.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	//Nome do usuário do banco de dados
	private static final String USERNAME = "root";
	//Senha do banco de dados
	private static final String PASSWORD = "";
	//Caminho do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/conexaoJDBC?createDatabaseIfNotExist=true&serverTimezone=UTC&useSSl=false";
	
	/*
	 * Conexão com o banco de dados
	 */
	
	public static Connection createConnectionToMySQL() throws Exception {
		Connection connection = null;
		//Cria conexão com banco de dados
		try {
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		}catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}
	
}
