# Atividade Spring Boot — Usuarios

Projeto em Java 21, Spring Boot, Spring Data JPA e H2, seguindo a estrutura do [SPRING/hello](https://github.com/marcosdessaUCSal/ProgWeb2026-2/tree/main/SPRING/hello).

- `model/Usuario`: entidade com id gerado, nome, sobrenome e CPF (String para preservar zeros iniciais).
- `model/repository/UsuarioRepository`: acesso ao banco via JpaRepository.
- `config/Config`: Configuration que implementa CommandLineRunner e salva cinco usuarios na inicializacao.
- `controller/UsuarioController`: GET `/usuarios`, que consulta todos os registros e retorna JSON com status 200.

## Executar

Com JDK 21 ou superior instalado e JAVA_HOME configurado, execute no PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Acesse <http://localhost:8080/usuarios>. Exemplo de um elemento da resposta:

```json
{"id":1,"nome":"Ana","sobrenome":"Silva","cpf":"11111111111"}
```

Os CPFs sao dados ficticios para demonstracao. O banco fica em memoria: ao encerrar a aplicacao, os dados sao descartados e os cinco registros sao recriados na proxima inicializacao.

Console H2: <http://localhost:8080/h2>, JDBC URL `jdbc:h2:mem:testdb`, usuario `sa`, senha em branco.

## Testar

```powershell
.\mvnw.cmd test
```
