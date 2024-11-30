Feature: Validação busca por ID do produto

  Scenario: Buscar produto pelo ID
    Given o seguinte produto foi cadastrado para busca por id:
      """
      {
        "nome": "X-salada",
        "categoria": "LANCHE",
        "preco": 10,
        "descricao": "Pão, carne, queijo e alface",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """
    When envio uma requisição GET para buscar o produto cadastrado pelo id
    Then o código de resposta deve ser 200 para a busca do produto pelo id
    And o corpo da resposta deve conter os dados do produto cadastrado e buscado pelo id
