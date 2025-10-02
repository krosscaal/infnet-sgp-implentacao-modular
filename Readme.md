## Autor
    Krossby Krossft

# SISTEMA WEB GESTÃO DE PORTARIA - IMPLEMENTAÇÃO MODULAR

## Feature 3

    Projeto da a materia Arquiteturas Avançadas de Software com Microsserviços e SpringFramework[25E3_3]

    Está aplicação foi desenvolvida com uma visão modular a partir da aplicção original 
[Sistema_Gestão_de_Portaria](https://github.com/krosscaal/infnet-sgp)

    Alem de manter tudo o que foi implementado no projeto original, foi acrescentado:
    - Uso de DTOs
    - Uso de FeignClients, para consumo da aplicação:
[Sistema de Agendamento de Visitantes](vhttps://github.com/krosscaal/infnet-agendamentoapi)

    - Uso de Job que executa a cada 10 minutos, para atualizar os dados de agendamentos de visitantes
      a traves do FeignClient.

## Feature 4

    Implementação de segurança da aplicação com Spring Security.

### SecurityConfig - Resumo da Implementação

    A classe `SecurityConfig` foi criada para configurar a segurança da aplicação utilizando Spring Security com as seguintes características:

#### Configurações Principais:
    - **Autenticação**: HTTP Basic Authentication
    - **Criptografia**: BCrypt para senhas  
    - **CSRF**: Desabilitado para permitir requisições de APIs
    - **Method Security**: Habilitado com `@EnableMethodSecurity(prePostEnabled = true)`

#### Usuários Pré-configurados (InMemory):
    - **Admin**: username: `admin`, password: `admin123`, role: `ADMIN`
    - **User**: username: `user`, password: `user123`, role: `USER`

#### Controle de Acesso por Endpoints:

    **Acesso Público:**
    - Console H2: `/h2-console/**` (permitAll)

    **Acesso Restrito a ADMIN (POST, PUT, DELETE):**
    - `/condominio/**` - Gerenciamento de condomínios
    - `/moradia/**` - Gerenciamento de moradias
    - `/usuario/**` - Gerenciamento de usuários
    - `/usuario-condominio/**` - Gerenciamento de usuários do condomínio
    - `/usuario-sistema/**` - Gerenciamento de usuários do sistema

    **Acesso Compartilhado (ADMIN e USER):**
    - Consultas GET para todos os endpoints acima
    - `/visitante/**` - POST, PUT e GET (DELETE apenas ADMIN)
    - `/correspondencia/**` - POST, PUT e GET (DELETE apenas ADMIN)

#### Segurança em Nível de Método:

    Além da configuração global no `SecurityFilterChain`, alguns Controllers utilizam a 
    anotação `@PreAuthorize("hasRole('ADMIN')")` para reforçar a segurança em nível de método:

    - **CondominioController**
    - **MoradiaController**
    - **UsuarioCondominioController**
    - **UsuarioSistemaController**
    - **VisitanteController**

    Esta abordagem de segurança em camadas garante que apenas usuários com perfil ADMIN possam executar operações críticas, mesmo que a configuração do `SecurityFilterChain` seja modificada.

#### Tratamento de Exceções Personalizado:

    **AuthenticationEntryPoint (401 - UNAUTHORIZED)**:
    - Retorna JSON formatado quando o usuário não está autenticado
    - Mensagem: "AUTENTICAÇÃO NECESSÁRIA! Você precisa estar autenticado. Por favor, forneça credenciais válidas."

    **AccessDeniedHandler (403 - FORBIDDEN)**:
    - Retorna JSON formatado quando o usuário não tem permissão
    - Mensagens contextualizadas baseadas no método HTTP e URI
    - Inclui timestamp formatado (dd/MM/yyyy HH:mm:ss)

#### Estrutura das Respostas de Erro:
    {
      "status": 401/403,
      "timestamp": "02/10/2025 14:30:00",
      "mensagem": "Mensagem descritiva do erro"
    }

    Arquivo json postman para testea incluso no projeto
[krossbyapi-infnet.postman_collection.json](krossbyapi-infnet.postman_collection.json)

## Tecnologias utilizadas

- Java (21)
- Spring Boot (3.5.5)
- banco em memoria H2

## Objetivo

    Este Sistema tem como finalidade Gerenciamento de portaria e conta com as
    seguintes funcionalidades:
    - regitro e cadastro de visitas.
    - registro e cadastro de diferentes tipos de aceso ao condominio(visitantes,
      funcionarios, prestadores de serviço entregadores).
    - registro de encomendas que chegam ao condominio para os moradores.
    - gerenciamento das encomendas.
    - conectar-se com outra aplicação externa Sistema Agendamento de visitantes.

## Projetos Relacionados
[Sistema Agendamento de visitantes](https://github.com/krosscaal/infnet-agendamentoapi)


