package com.atividade;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import com.atividade.model.Usuario;
import com.atividade.model.repository.UsuarioRepository;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import tools.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AtividadeApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@LocalServerPort
	private int port;

	@Test
	void deveSalvarCincoUsuariosERetornarTodoOBancoEmJson() throws Exception {
		assertThat(usuarioRepository.count()).isEqualTo(5);
		// Um sexto registro comprova que a rota consulta o banco inteiro.
		Usuario adicional = usuarioRepository.save(new Usuario(null, "Teste", "Adicional", "00000000000"));
		try (HttpClient client = HttpClient.newHttpClient()) {
			var request = HttpRequest.newBuilder(URI.create("http://localhost:" + port + "/usuarios")).GET().build();
			var response = client.send(request, HttpResponse.BodyHandlers.ofString());
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.headers().firstValue("content-type").orElse("")).contains("application/json");
			Usuario[] usuarios = new ObjectMapper().readValue(response.body(), Usuario[].class);
			assertThat(usuarios).hasSize(6);
			assertThat(usuarios).extracting(Usuario::getCpf).containsExactlyInAnyOrder(
				"11111111111", "22222222222", "33333333333", "44444444444", "55555555555", "00000000000");
			assertThat(usuarios).allSatisfy(usuario -> {
				assertThat(usuario.getId()).isNotNull();
				assertThat(usuario.getNome()).isNotBlank();
				assertThat(usuario.getSobrenome()).isNotBlank();
			});
		} finally {
			usuarioRepository.deleteById(adicional.getId());
		}
	}

}
