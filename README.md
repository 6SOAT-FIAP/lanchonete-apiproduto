# Tech Challenge - Pós Tech Software Architecture

## O que é o projeto

Há uma lanchonete de bairro que está se expandindo devido ao seu grande sucesso.

Nesse contexto, um sistema de controle de pedidos é essencial para garantir que a lanchonete possa atender os clientes
de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada.

Para solucionar o problema, o projeto visa oferecer à lanchonete um sistema de autoatendimento de fast food que permite
aos clientes selecionar e fazer pedidos sem precisar interagir com um atendente.

## Objetivos

Este serviço inclui as seguintes funcionalidades:

- Criar, editar e remover produtos
- Buscar produtos por categoria

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

* Você tem uma máquina `<Windows / Linux / Mac>`.
* Docker
* Postman
* Jdk 17 ou superior

## Como iniciar o projeto localmente

### Docker compose para subir os containers da aplicação e do banco de dados

```bash
docker-compose up -d
```

### <img src="https://user-images.githubusercontent.com/25181517/186711335-a3729606-5a78-4496-9a36-06efcc74f800.png" width=30> Swagger

```
http://localhost:8080/swagger-ui/index.html
```

## Collection

Acesse a [**collection**](assets/collection/Lanchonete.postman_collection) do Postman com todas as APIs desenvolvidas.

## Guia instrutivo

Acesse o [**guia**](assets/collection/guia.md) para execução das APIs.