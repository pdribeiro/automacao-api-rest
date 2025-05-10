@todos
Feature: Gerenciar reservas na API
  @criacao
  Scenario: Criar e alterar uma reserva com sucesso
    Given que eu tenha um token válido
    When eu crio uma nova reserva
    And eu altero essa reserva
    Then a reserva deve ser atualizada com sucesso

  @consulta
  Scenario: Obter todas as reservas
    Given que a API de reservas está disponível
    When eu solicito todas as reservas
    Then a resposta deve ter status code 200