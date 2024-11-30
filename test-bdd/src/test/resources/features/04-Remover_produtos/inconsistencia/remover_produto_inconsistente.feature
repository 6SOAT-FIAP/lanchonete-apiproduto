Feature: Validação deleção de produto inexistente

  Scenario: Tentar deletar produto inexistente
    Given que um produto com o ID 999 não existe no sistema
    When envio uma requisição DELETE para o produto "808080808080"
    Then o código de resposta deve ser 404 para produto não encontrado
