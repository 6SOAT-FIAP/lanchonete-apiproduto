# Guia para execução

Segue abaixo instruções para execução

## 2.1 Cadastrar produto

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

## 2.2 Consultar produtos pela categoria

É possível consultar os produtos pela categoria:

[GET] */api/v1/produto/{categoria}*

## 2.3 Editar produto

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

## 2.4 Remover produto

É possível remover um produto com o seu id:

[DEL] */api/v1/produto/{id}*