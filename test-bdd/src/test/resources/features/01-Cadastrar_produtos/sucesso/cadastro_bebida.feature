Feature: Validação cadastro bebida

  Scenario: Cadastrar sobremesa
    Given o seguinte payload para cadastrar um produto da categoria bebida:
      """
      {
        "nome": "Refrigerante cola",
        "categoria": "BEBIDA",
        "preco": 10,
        "descricao": "Refrigerante de cola",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """
    When envio uma requisição POST para "/api/v1/produto" com os dados da bebida
    Then o código de resposta deve ser 201 para criação da bebida
    And o corpo da resposta da criação da bebida deve ser:
      """
      {
        "nome": "Refrigerante cola",
        "categoria": "BEBIDA",
        "preco": 10,
        "descricao": "Refrigerante de cola",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """