Feature: Validação cadastro lanche

  Scenario: Cadastrar lanche
    Given o seguinte payload para cadastrar um produto da categoria lanche:
      """
      {
        "nome": "X-bacon",
        "categoria": "LANCHE",
        "preco": 10,
        "descricao": "Pão, carne, queijo e bacon",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """
    When envio uma requisição POST para "/api/v1/produto" com os dados do lanche
    Then o código de resposta deve ser 201 para criação do lanche
    And o corpo da resposta da criação do lanche deve ser:
      """
      {
        "nome": "X-bacon",
        "categoria": "LANCHE",
        "preco": 10,
        "descricao": "Pão, carne, queijo e bacon",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """