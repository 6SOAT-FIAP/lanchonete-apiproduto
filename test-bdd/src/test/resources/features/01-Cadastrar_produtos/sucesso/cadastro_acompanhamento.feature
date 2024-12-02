Feature: Validação cadastro acompanhamento

  Scenario: Cadastrar acompanhamento
    Given o seguinte payload para cadastrar um produto da categoria acompanhamento:
      """
      {
        "nome": "Batata frita",
        "categoria": "ACOMPANHAMENTO",
        "preco": 10,
        "descricao": "Batata frita com maionese",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """
    When envio uma requisição POST para "/api/v1/produto" com os dados do acompanhamento
    Then o código de resposta deve ser 201 para criação do acompanhamento
    And o corpo da resposta da criação do acompanhamento deve ser:
      """
      {
        "nome": "Batata frita",
        "categoria": "ACOMPANHAMENTO",
        "preco": 10,
        "descricao": "Batata frita com maionese",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """