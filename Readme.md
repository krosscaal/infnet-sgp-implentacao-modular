## Autor
    Krossby Krossft

# SISTEMA WEB GESTÃO DE PORTARIA - IMPLEMENTAÇÃO MODULAR

    Projeto da a materia Arquiteturas Avançadas de Software com Microsserviços e SpringFramework[25E3_3]

    Está aplicação foi desenvolvida com uma visão modular a partir da aplicção original 
[Sistema_Gestão_de_Portaria](https://github.com/krosscaal/infnet-sgp)

    Alem de manter tudo o que foi implementado no projeto original, foi acrescentado:
    - Uso de DTOs
    - Uso de FeignClients, para consumo da aplicação:
[Sistema de Agendamento de Visitantes](vhttps://github.com/krosscaal/infnet-agendamentoapi)

    - Uso de Job que executa a cada 10 minutos, para atualizar os dados de agendamentos de visitantes
      a traves do FeignClient.

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


