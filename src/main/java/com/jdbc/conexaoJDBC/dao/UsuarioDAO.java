package com.jdbc.conexaoJDBC.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jdbc.conexaoJDBC.factory.ConnectionFactory;
import com.jdbc.conexaoJDBC.model.Usuario;

@Service
public class UsuarioDAO {

	public void save(Usuario usuario) {
		String sql = "INSERT INTO USUARIOS(NOME, IDADE, DATA_CADASTRO) VALUES(?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Cria PreparedStatement para executar uma query
			pstm = conn.prepareStatement(sql);
			//Adicionar valores esperados pela query
			pstm.setNString(1, usuario.getNome());
			pstm.setInt(2, usuario.getIdade());
			pstm.setDate(3, new Date(usuario.getDataCadastro().getTime()));
			
			//Executar a query
			pstm.execute();
			System.out.println("usuario salvo com sucesso!");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//fechar conexões
			try {
				if(pstm!=null) {
					pstm.close();					
				}
				if(conn!=null) {
					conn.close();					
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Usuario> getUsuarios(){
		String sql = "SELECT * FROM USUARIOS";
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que irá recuperar dados do banco de dados  ***SELECT***
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rset.getInt("id"));
				usuario.setNome(rset.getString("nome"));
				usuario.setIdade(rset.getInt("idade"));
				usuario.setDataCadastro(rset.getDate("data_cadastro"));
				usuarios.add(usuario);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset != null) {
					rset.close();
				}
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return usuarios;
	}
	
	public void update(Usuario usuario) {
		String sql = "UPDATE USUARIOS SET NOME = ? , IDADE = ? , DATA_CADASTRO = ? WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, usuario.getNome());
			pstm.setInt(2, usuario.getIdade());
			pstm.setDate(3, new Date(usuario.getDataCadastro().getTime()));
			pstm.setInt(4, usuario.getId());
			
			pstm.execute();
			System.out.println("Update executado com sucesso!");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteById(int id) {
		String sql = "DELETE FROM USUARIOS WHERE ID = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			System.out.println("Delete executado com sucesso!");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
