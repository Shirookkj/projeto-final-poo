# Sistema de Gerenciamento

Este projeto é um sistema modular que combina funcionalidades para gerenciar um restaurante, uma clínica médica e eventos. O código utiliza princípios de design de software para criar um sistema extensível, reutilizável e fácil de manter.

## Funcionalidades Principais

1. Sistema de Restaurante:
   - Gerenciamento de mesas e pedidos.
   - Controle de itens como pratos, bebidas e sobremesas.
   - Relatórios gerais sobre vendas e utilização de mesas.

2. Sistema de Clínica Médica:
   - Cadastro de pacientes e médicos.
   - Agendamento, modificação e cancelamento de consultas.
   - Relatórios detalhados por médico, especialidade ou data.

3. Sistema de Eventos:
   - Cadastro de eventos (seminários, workshops e conferências).
   - Gerenciamento de participantes.
   - Relatórios de eventos e limites de participantes.

---

## Estrutura do Projeto

### Pacote de Restaurante
- PedidoService: Gerencia as operações relacionadas a pedidos, como criação, cancelamento e geração de relatórios.
- Mesa: Representa as mesas do restaurante e controla seu estado (disponível ou ocupada). Também gerencia os pedidos associados.
- Pedido: Armazena os itens de um pedido e calcula o total. Permite associar um cliente para aplicação de descontos. Caso tenha uma pessoa cadastrada em um clínica médica / evento, ela recebe desconto no restaurante de 10%.
- ItemDoPedido: Classe abstrata para representar itens genéricos. É estendida por **Prato, **Bebida e Sobremesa para especializar tipos de itens.
- SistemaRestaurante: Controla a interação geral com o restaurante, integrando mesas e pedidos.

### Pacote de Clínica Médica
- ClinicaMedica: Coordena a operação da clínica médica, incluindo cadastro de pacientes, médicos e marcação de consultas.
- Paciente: Representa os dados de um paciente, como nome, CPF e idade.
- Medico: Gerencia os dados de um médico, incluindo especialidade e disponibilidade.
- Consulta: Detalha uma consulta médica entre um médico e um paciente, armazenando informações como data e status (cancelada ou ativa).

### Pacote de Eventos
- SistemaEventos: Controla o gerenciamento de eventos, incluindo cadastro de eventos e participantes.
- Evento: Classe base para diferentes tipos de eventos (Seminário, **Workshop, **Conferência). Define propriedades gerais, como local e data.
- Seminario, **Workshop e Conferencia: Estendem a funcionalidade de **Evento, adicionando restrições específicas, como limites de participantes.
- Participante: Representa uma pessoa cadastrada em um evento.
- Local: Define as características do local do evento, como capacidade e endereço.

---

## Exemplos de Perguntas e Decisões Baseadas no Código

### Restaurante
1. Quantos pedidos foram realizados na mesa X?
   - Função que responde: PedidoService.gerarRelatorio().
   - Tomada de decisão: Ajuda a identificar mesas mais utilizadas, otimizando a alocação e oferta de serviços.

2. Qual foi o total de vendas do restaurante?
   - Função que responde: PedidoService.gerarRelatorio().
   - Tomada de decisão: Avaliar o desempenho financeiro do restaurante.

### Clínica Médica
3. Quantas consultas o paciente com CPF X realizou?
   - Função que responde: listarConsultasPacientes().
   - Tomada de decisão: Monitorar a frequência de consultas para identificar pacientes que podem necessitar de acompanhamento mais intenso.

4. Quais médicos realizaram consultas em uma data específica?
   - Função que responde: gerarRelatorioPorData().
   - Tomada de decisão: Analisar a eficiência do quadro médico e planejar escalas futuras.

5. Qual especialidade é mais procurada?
   - Função que responde: gerarRelatorioPorEspecialidade().
   - Tomada de decisão: Realocar médicos ou contratar mais profissionais para atender à demanda.

### Eventos
6. Quantos participantes estão inscritos no evento X?
   - Função que responde: Evento.gerarRelatorio().
   - Tomada de decisão: Verificar se o evento atingiu a capacidade mínima ou máxima para confirmação ou reestruturação.

7. Existe um participante com o e-mail Y no evento X?
   - Função que responde: isParticipanteCadastrado().
   - Tomada de decisão: Garantir a organização da lista de participantes, evitando duplicidades.

8. Qual local foi mais utilizado para eventos?
   - Função que responde: Requer análise manual combinando **SistemaEventos e propriedades de Local.
   - Tomada de decisão: Identificar locais populares para planejar futuros eventos.



---

## Princípios SOLID Aplicados

### 1. Princípio da Responsabilidade Única (SRP)
Definição: Cada classe deve ter apenas uma responsabilidade, ou seja, deve haver apenas um motivo para que ela seja modificada.

Como foi aplicado:
- A classe PedidoService é responsável exclusivamente pela lógica relacionada aos pedidos do restaurante (criação, cancelamento e relatórios). 
- As classes como Mesa, Pedido e ItemDoPedido possuem responsabilidades separadas, como o gerenciamento do status da mesa, itens de pedidos e seus cálculos.
- Na clínica médica, a classe ClinicaMedica organiza a lógica de pacientes, médicos e consultas separadamente, enquanto Consulta encapsula os detalhes específicos de uma consulta médica.

Benefício: Essa separação facilita modificações no código sem afetar outras partes do sistema, além de tornar o código mais compreensível.

---

### 2. Princípio Aberto/Fechado (OCP)
Definição: As classes devem estar abertas para extensão, mas fechadas para modificação.

Como foi aplicado:
- A classe ItemDoPedido é abstrata e define o comportamento básico dos itens. Classes concretas como Prato, Bebida e Sobremesa estendem essa funcionalidade sem alterar o código base.
- Para os eventos, a classe Evento permite heranças como Seminario, Workshop e Conferencia, adicionando comportamentos específicos para cada tipo de evento.

Exemplo:
A adição de novos tipos de itens no restaurante ou novos tipos de eventos pode ser feita sem modificar as classes já existentes.

Benefício: O sistema se torna flexível para futuras alterações, como inclusão de novos recursos.

---

### 3. Princípio da Substituição de Liskov (LSP)
Definição: Subtipos devem ser substituíveis por seus tipos base, sem que o comportamento do programa seja alterado.

Como foi aplicado:
- Todas as subclasses de ItemDoPedido (Prato, Bebida, Sobremesa) podem ser usadas de forma intercambiável onde o tipo ItemDoPedido é esperado.
- No sistema de eventos, as subclasses de Evento (Seminario, Workshop, Conferencia) seguem o contrato definido na classe base.

Exemplo:
O método adicionarItem de Pedido trata qualquer objeto derivado de ItemDoPedido, garantindo flexibilidade sem impactar o comportamento.

Benefício: A aplicação se torna robusta e segue princípios polimórficos corretamente.

---

### 4. Princípio da Segregação de Interfaces (ISP)
Definição: Interfaces devem ser específicas para os clientes, evitando métodos que eles não utilizam.

Como foi aplicado:
- No sistema de eventos, os métodos de cada tipo de evento são definidos em classes específicas (Seminario, Workshop, etc.), e não em uma única classe genérica.
- A separação de responsabilidades em classes como Mesa, Pedido e Pessoa evita interfaces com métodos desnecessários.

Benefício: As classes não carregam métodos que não utilizam, tornando o código mais limpo e fácil de compreender.

---

### 5. Princípio da Inversão de Dependências (DIP)
Definição: Dependa de abstrações, não de implementações.

Como foi aplicado:
- A classe PedidoService não depende diretamente de classes concretas, mas de abstrações, como ItemDoPedido.
- No sistema de eventos, o uso da classe base Evento permite que o sistema funcione independentemente do tipo específico de evento.

Exemplo:
Se no futuro forem criados novos tipos de itens para o restaurante ou novos tipos de eventos, o sistema continuará funcionando sem mudanças no código existente.

Benefício: Redução de acoplamento, permitindo maior reutilização de código e facilidade de testes.

---


## Como Executar

1. Certifique-se de que o Java está instalado.
2. Compile o projeto usando um IDE.
3. Execute o programa principal (Main).

---

## Conclusão

Este projeto demonstra como os princípios SOLID podem ser aplicados na prática, resultando em um sistema bem estruturado, de fácil manutenção e flexível para futuras mudanças. Esses princípios foram essenciais para garantir a modularidade e clareza do código.

![Diagrama de Atv drawio](https://github.com/user-attachments/assets/16e2ee99-dfa3-47f0-95bb-e19b2706e806)
![projeto final poopng](https://github.com/user-attachments/assets/6e26a3eb-26b4-4c21-bbbb-fc1d155701ef)
