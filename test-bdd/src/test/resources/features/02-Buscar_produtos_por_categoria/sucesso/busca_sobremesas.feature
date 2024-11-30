Feature: Validação consulta por categoria sobremesa

  Scenario: Consultar produtos da categoria sobremesa
    Given o seguinte produto da categoria sobremesa foi cadastrado:
      """
      {
        "nome": "X-creme de avelã",
        "categoria": "SOBREMESA",
        "preco": 10,
        "descricao": "Pão e creme de avelã",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """
    When envio uma requisição GET para "/api/v1/produto/SOBREMESA" para buscar as sobremesas cadastradas
    Then o código de resposta deve ser 200 para consulta da categoria sobremesa
    And o corpo da resposta deve conter um produto da categoria SOBREMESA