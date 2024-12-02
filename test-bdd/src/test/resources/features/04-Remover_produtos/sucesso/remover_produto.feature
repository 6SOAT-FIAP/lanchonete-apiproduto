Feature: Validação deleção de produto

  Scenario: Deletar produto com sucesso
    Given que um produto foi criado no sistema
    When envio uma requisição DELETE com o ID do produto criado
    Then o código de resposta deve ser 200 para deleção do produto
