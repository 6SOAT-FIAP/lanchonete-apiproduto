Feature: Validação busca por ID do produto inexistente

  Scenario: Consultar produto com ID inexistente
    Given o seguinte ID "9999" de um produto inexistente
    When envio uma requisição GET para "/api/v1/produto/?ids=99999" para buscar um produto inexistente
    Then o código de resposta deve ser 404 para a busca por ID inexistente