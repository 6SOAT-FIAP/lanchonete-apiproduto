Feature: Validação edição de produto inconsistente

  Scenario: Editar um produto existente
    Given o seguinte produto foi cadastrado para ser editado com inconsistencia:
      """
      {
        "nome": "X-bacon fit",
        "categoria": "LANCHE",
        "preco": 10,
        "descricao": "Pão, carne, queijo e bacon",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """
    When envio uma requisição PUT para "/api/v1/produto/abc" com o número do produto inconsistente:
      """
      {
        "nome": "X-bacon max",
        "categoria": "LANCHE",
        "preco": 12,
        "descricao": "Pão, carne, queijo, bacon e molho especial",
        "imagem": "https://static.itdg.com.br/images/640-440/49687a8a7a7110c7f560b9c7e96a9d0e/254679-shutterstock-364110890-1-.jpg"
      }
      """
    Then o código de resposta deve ser 400 para edição do produto inconsistente