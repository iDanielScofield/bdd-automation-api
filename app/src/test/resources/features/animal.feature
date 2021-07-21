#language: pt
Funcionalidade: Gerenciamento de animais da loja

  Cenário: Lista somente animais disponíveis para a venda
    Dado que possua animais available
    Quando pesquisa por todos os animais available
    Então retorna a lista de animais available

  Cenário: Lista somente animais pendentes
    Dado que possua animais pending
    Quando pesquisa por todos os animais pending
    Então retorna a lista com 2 animais

  Cenário: Não lista animais
    Dado que não possua animais sold
    Quando pesquisa por todos os animais sold
    Então retorna a lista com 0 animal
