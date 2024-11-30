# Guia para execução

Segue abaixo instruções para execução.

## - Cadastrar produto

Na sequência, deve-se cadastrar os produtos:

[POST] */api/v1/produto*

```json
{
  "nome": "X-bacon",
  "categoria": "LANCHE",
  "preco": 10,
  "descricao": "Pão, carne, queijo e bacon"
}
```

## - Consultar produtos pela categoria

É possível consultar os produtos pela categoria:

[GET] */api/v1/produto/{categoria}*

## - Consultar produtos por ids

É possível consultar os produtos pelos ids:

[GET] */api/v1/produto/?ids=1&ids=2&ids=3*

## - Editar produto

É possível editar um produto com o seu id:

[PUT] */api/v1/produto/{id}*

```json
{
  "nome": "X-bacon",
  "categoria": "LANCHE",
  "preco": 10,
  "descricao": "Pão, duas carnes, queijo e bacon"
}
```

## - Remover produto

É possível remover um produto com o seu id:

[DEL] */api/v1/produto/{id}*