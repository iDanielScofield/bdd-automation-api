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

  Esquema do Cenário: Lista animais pelo seu estado de venda
    Dado que não possua animais sold
    Quando pesquisa por todos os animais <status>
    Então retorna a lista com <quantidade> animais

    Exemplos: Animais com estoque
      | status    | quantidade |
      | available | 7          |
      | pending   | 2          |

    Exemplos: Animais sem estoque
      | status | quantidade |
      | sold   | 0          |

  Cenario: Lista animais disponíveis para a venda
    Dado que possua animais available
    Quando pesquiso por todos os animais available
    Então recebo a lista com 7 animais available
    E 3 animais possuem o nome Lion