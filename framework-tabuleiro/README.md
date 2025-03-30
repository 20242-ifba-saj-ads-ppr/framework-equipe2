## Builder

### Intenção

Separar a construção de um objeto complexo da sua representação de modo que o
mesmo processo de construção possa criar diferentes representações.
(GOF)

### Motivação
Para construir objetos complexos – como um tabuleiro de jogo (TabletopProduct) composto por uma área definida e uma coleção de componentes (tiles) – precisamos de uma estratégia que permita a configuração gradual e flexível do objeto sem expor um construtor repleto de parâmetros. Imagine que, sem um padrão de projeto, o código cliente precise instanciar o tabuleiro diretamente, chamando um construtor complexo, como no exemplo abaixo:

```plantuml
@startuml
class TabletopProduct {
  - x: int
  - y: int
  - tiles: List<TabletopComponent>
  + TabletopProduct(x: int, y: int, tiles: List<TabletopComponent>)
  + getX(): int
  + getY(): int
  + getTiles(): List<TabletopComponent>
}

TabletopProduct --> "List<TabletopComponent>"
@enduml
````
Nesse cenário, se o objeto se tornar mais complexo – com parâmetros opcionais ou uma ordem específica de configuração – o código do cliente fica difícil de manter e fortemente acoplado ao construtor do produto. Toda mudança na estrutura do objeto implicaria em modificações em todos os pontos onde ele é criado.

Para resolver esses problemas, aplicamos o padrão Builder. Esse padrão separa o processo de construção de um objeto complexo da sua representação final, permitindo que o objeto seja montado passo a passo. Assim, o código cliente interage com um Builder que expõe métodos para configurar as partes do objeto e, por fim, retorna o produto completo. Essa abordagem reduz o acoplamento e torna a criação do objeto mais legível e flexível.

No nosso exemplo, o Builder permite definir a área do tabuleiro e os componentes (tiles) de forma encadeada, e um Diretor controla a ordem da construção para garantir que o objeto final (TabletopProduct) seja consistente.

```plantuml

@startuml
title Builder Pattern for TabletopProduct

interface TabletopBuilder {
  + setArea(x: int, y: int): TabletopBuilder
  + setTiles(tiles: List<TabletopComponent>): TabletopBuilder
  + getResult(): TabletopProduct
}

class TabletopConcreteBuilder {
  - x: int
  - y: int
  - tiles: List<TabletopComponent>
  + setArea(x: int, y: int): TabletopBuilder
  + setTiles(tiles: List<TabletopComponent>): TabletopBuilder
  + getResult(): TabletopProduct
}

class TabletopDirector {
  + construct(x: int, y: int, tiles: List<TabletopComponent>): TabletopProduct
}

class TabletopProduct {
  - x: int
  - y: int
  - tiles: List<TabletopComponent>
  + TabletopProduct(x: int, y: int, tiles: List<TabletopComponent>)
  + getX(): int
  + getY(): int
  + getTiles(): List<TabletopComponent>
}

TabletopConcreteBuilder ..|> TabletopBuilder
TabletopDirector --> TabletopBuilder
TabletopConcreteBuilder --> TabletopProduct
@enduml
```

## Estrutura do padrão (GOF - papeis)
  - UML
  - 
### Padrão aplicado no cenário

No nosso cenário, estamos construindo um jogo de tabuleiro inspirado no jogo Selva. Durante o desenvolvimento, percebemos que criar objetos complexos – como um tabuleiro que consiste em uma área definida e uma coleção de componentes (tiles) – diretamente através de um construtor cheio de parâmetros torna o código difícil de manter e expandir. Imagine que, sem um padrão de projeto, o código cliente precise instanciar o tabuleiro diretamente chamando um construtor complexo:
  
## Participantes 

- **Builder (TabletopBuilder):**
  - Define a interface para construir as partes do objeto complexo. 
  - Declara métodos para configurar a área do tabuleiro e os componentes (tiles), retornando o próprio builder para permitir o encadeamento de chamadas.
  - Declara o método getResult() que retorna o produto final.

- **ConcreteBuilder (TabletopConcreteBuilder):**
  - Implementa a interface TabletopBuilder.
  - Armazena os valores configurados (como a área e os tiles) e, ao final, constrói e retorna uma instância de TabletopProduct.
  
- **Director (TabletopDirector):**
  - Controla o processo de construção, definindo a ordem e os passos necessários para montar o objeto.
  - Utiliza o builder para construir o objeto complexo, garantindo que ele seja criado de forma consistente.


- **Product (TabletopProduct):**
  - É o objeto complexo que estamos construindo (o tabuleiro de jogo).
  - Contém atributos como a área (x e y) e a coleção de componentes (tiles).
  - Oferece métodos para acessar suas propriedades.


 
## Código 
@import "./src/builder/TabletopBuilder.java"
@import "./src/builder/TabletopConcreteBuilder.java"
@import "./src/builder/TabletopDirector.java"
@import "./src/builder/TabletopProduct.java"

## Composite

### Intenção

Compor objetos em estruturas de árvore para representarem hierarquias partes-todo.
Composite permite aos clientes tratarem de maneira uniforme objetos individuais e
composições de objetos.
(GOF)

### Motivação
Ao desenvolver o jogos de tabuleiro, por exemplo o  jogo Selva, perceberemos que o tabuleiro é composto por diversos componentes que podem ser manipulados de forma hierárquica. Cada peça ou grupo de peças pode ser tratado de maneira uniforme, como um único objeto ou como uma composição de múltiplos objetos. O problema é que o código se torna complexo ao lidar com coleções heterogêneas de objetos, pois o cliente precisa distinguir entre objetos simples e compostos para realizar operações.Fazendo com que o código cliente precise iterar manualmente sobre os tiles do tabuleiro e gerenciar cada um individualmente.


```plantuml
@startuml

package "Jogo Selva" {
  class Tabuleiro {
    - tiles: List<Tile>
    + adicionarTile(tile: Tile): void
    + removerTile(tile: Tile): void
    + obterTiles(): List<Tile>
  }

  class Tile {
    + posicaoX: int
    + posicaoY: int
    + tipo: String
    + Tile(posicaoX: int, posicaoY: int, tipo: String)
    + desenhar(): void
  }

  class Cliente {
    + main()
  }

  Cliente --> Tabuleiro
  Tabuleiro --> "1..*" Tile
}

@enduml
```
O padrão Composite permite que o cliente trate objetos individuais e composições de objetos de forma uniforme. Assim, um tabuleiro (composto por várias peças) e uma única peça podem ser manipulados de maneira consistente.

```plantuml
@startuml

interface TabletopComponent {
  + operation(): String
}

class TabletopLeaf {
  - flyweight: TabletopFlyweightProduct
  - x: int
  - y: int
  + operation(): String
}

class TabletopComposite {
  - name: String
  - children: List<TabletopComponent>
  + add(flyweight: TabletopFlyweightProduct, x: int, y: int): void
  + operation(): String
}

TabletopLeaf ..|> TabletopComponent
TabletopComposite ..|> TabletopComponent
TabletopComposite --> "*" TabletopComponent

@enduml
```

## Estrutura do padrão (GOF - papeis)
  - UML
  - 
### Padrão aplicado no cenário

No nosso cenário, estamos construindo um tabuleiro que pode ser composto por peças individuais ou agrupamentos de peças. Ao aplicar o padrão Composite, conseguimos manipular esses elementos de forma hierárquica e uniforme, facilitando operações complexas como movimentação, remoção ou adição de peças no tabuleiro.
  
### Participantes 


- **Component (TabletopComponent):**
  - Declara a interface comum para objetos individuais e composições. Neste caso, define o método operation() que será implementado tanto pelas folhas quanto pelos compostos.

- **Leaf (TabletopLeaf):**
  - Representa os objetos individuais que não possuem filhos.
  - Implementa a interface TabletopComponent e define o comportamento específico dos objetos folha.
  
- **Composite (TabletopComposite):**
  - Representa objetos que possuem filhos (outros componentes).
  - Implementa métodos para adicionar, remover e acessar os filhos.
  - Implementa a interface TabletopComponent de maneira a delegar operações para os filhos.



 
### Código 
@import "./src/composite/TabletopBuilder.java"
@import "./src/composite/TabletopConcreteBuilder.java"
@import "./src/composite/TabletopDirector.java"
@import "./src/composite/TabletopProduct.java"









## Flyweight

### Intenção

Usar compartilhamento para suportar eficientemente grandes quantidades de objetos de granularidade fina.
(GOF)

### Motivação
No desenvolvimento de jogos de tabuleiro, como o jogo Selva, o tabuleiro é composto por inúmeros tiles que podem apresentar características idênticas. Por exemplo, várias casas do tabuleiro podem ter o mesmo tipo (água, armadilha, toca, etc.).

Sem o Flyweight, cada tile seria instanciado individualmente, consumindo mais memória e tornando o gerenciamento desses objetos mais complexo.


```plantuml
@startuml
title Cenário sem Flyweight - Instanciação Individual de Tiles

class Cliente {
  + main()
}

class Tabuleiro {
  - tiles: List<Tile>
  + adicionarTile(tile: Tile): void
  + removerTile(tile: Tile): void
  + obterTiles(): List<Tile>
}

class Tile {
  - posicaoX: int
  - posicaoY: int
  - tipo: String
  + Tile(posicaoX: int, posicaoY: int, tipo: String)
  + desenhar(): void
}

Cliente --> Tabuleiro
Tabuleiro --> "1..*" Tile

note right of Tabuleiro : Cada Tile é instanciado\nindividualmente, consumindo mais memória\n e aumentando a complexidade do gerenciamento.

@enduml

```

Com o Flyweight, podemos compartilhar instâncias de tiles com estados idênticos, fazendo com que o código cliente não precise criar novas instâncias para cada tile repetido. Assim, o sistema se torna mais leve e eficiente.


```plantuml
@startuml
title Flyweight Pattern for Tabletop Tiles

' Abstract Flyweight
abstract class TabletopFlyweightProduct {
  - name: String
  + TabletopFlyweightProduct(name: String)
  + operation(position: String): String
}

' Abstract Flyweight Creator
abstract class TabletopFlyweightCreator {
  - flyweights: Map<String, TabletopFlyweightProduct>
  + getFlyweight(key: String): TabletopFlyweightProduct
  + listFlyweights(): String[]
}

' Concrete Flyweight
class TabletopFlyweightConcreteProduct {
  + TabletopFlyweightConcreteProduct(name: String)
  + operation(position: String): String
}

' Concrete Flyweight Creator (Factory)
class TabletopFlyweightConcreteCreator {
  + TabletopFlyweightConcreteCreator()
  + getFlyweight(key: String): TabletopFlyweightProduct
  + listFlyweights(): String[]
}

TabletopFlyweightConcreteProduct ..|> TabletopFlyweightProduct
TabletopFlyweightConcreteCreator ..|> TabletopFlyweightCreator

@enduml

```

## Estrutura do padrão (GOF - papeis)
  - UML
  - 
### Padrão aplicado no cenário

No cenário do jogo Selva, o tabuleiro possui muitas casas (tiles) que podem ter características repetidas, como a cor ou a função (água, armadilha, toca, etc.).
Utilizando o Flyweight, o sistema cria apenas uma instância para cada tipo de tile e compartilha essa instância entre todas as casas que apresentem o mesmo tipo.
Dessa forma, o cliente não precisa instanciar repetidamente objetos iguais, reduzindo o consumo de memória e simplificando o gerenciamento dos tiles.


  
## Participantes 


- **Flyweight (TabletopFlyweightProduct):**
  - Define a interface para os objetos compartilhados, encapsulando o estado intrínseco (por exemplo, o nome ou tipo do tile).
  - Contém o método operation() que realiza uma operação usando o estado intrínseco, junto com o estado extrínseco (passado como parâmetro, por exemplo, a posição do tile).

- **Concrete Flyweight (TabletopFlyweightConcreteProduct)**
  - Implementa a interface do Flyweight e define o comportamento concreto do tile compartilhado.
  
- **FlyweightFactory (TabletopFlyweightCreator):**
  - Representa objetos que possuem filhos (outros componentes).
  - Implementa métodos para adicionar, remover e acessar os filhos.
  - Implementa a interface TabletopComponent de maneira a delegar operações para os filhos.



 
## Código 
@import "./src/composite/TabletopBuilder.java"
@import "./src/composite/TabletopConcreteBuilder.java"
@import "./src/composite/TabletopDirector.java"
@import "./src/composite/TabletopProduct.java"

# Prototype 

## Intenção

Especificar os tipos de objetos a serem criados usando uma instância-protótipo e criar novos objetos pela cópia desse protótipo.

###  Motivação

Em cenários onde os objetos a serem criados são complexos – como um tabuleiro de jogo com uma série de componentes e configurações – a criação direta por meio de um construtor pode ser ineficiente e resultar em código redundante. 

```plantuml
@startuml
title UML - Antes da Aplicação do Prototype

class TabletopProduct {
  - x: int
  - y: int
  - tiles: List<TabletopComponent>
  + TabletopProduct(x: int, y: int, tiles: List<TabletopComponent>)
  + getX(): int
  + getY(): int
  + getTiles(): List<TabletopComponent>
}

class Client {
  + main(args: String[]): void
}

Client --> TabletopProduct : "new instance"
@enduml
```
Sem o uso do Prototype, cada vez que um objeto complexo precisasse ser replicado, seria necessário chamar o construtor com todos os seus parâmetros, o que poderia levar a erros e dificultar a manutenção.

```plantuml
@startuml
title Prototype Pattern para TabletopProduct

interface TabletopPrototype {
  + clonePrototype(): TabletopPrototype
}

class TabletopConcretePrototype {
  - product: TabletopProduct
  + TabletopConcretePrototype(product: TabletopProduct)
  + clonePrototype(): TabletopPrototype
  + getProduct(): TabletopProduct
}

TabletopConcretePrototype ..|> TabletopPrototype
TabletopConcretePrototype --> TabletopProduct
@enduml
```

Utilizando o Prototype, o framework pode gerar novas instâncias de um objeto complexo por meio da clonagem de um protótipo previamente configurado. Essa abordagem simplifica a criação de cópias, diminui o acoplamento com classes concretas e torna o sistema mais flexível para alterações futuras, além de melhorar a performance em operações onde a criação completa do objeto seria muito custosa.

### Estrutura do Padrão (GOF - Papéis)

- **Prototype (TabletopPrototype):**  
  Define a interface para objetos que podem ser clonados, declarando o método `clonePrototype()`. Essa interface é a base para qualquer objeto que deseje suportar clonagem.

- **ConcretePrototype (TabletopConcretePrototype):**  
  Implementa a interface `TabletopPrototype` e armazena internamente um objeto do tipo `TabletopProduct`. Ao invocar o método `clonePrototype()`, é retornada uma nova instância do protótipo (no caso, utilizando uma cópia superficial do produto).

- **Product (TabletopProduct):**  
  Embora não faça parte direta do padrão Prototype, este objeto complexo é o que está sendo clonado. Ele representa o tabuleiro de jogo com sua configuração e componentes.

### Código




# Observer

## 1. Intenção

Definir uma dependência um-para-muitos entre objetos, de maneira que quando um
objeto muda de estado todos os seus dependentes são notificados e atualizados
automaticamente

## 2. Motivação

Em um framework de jogo de tabuleiro, diversas partes do sistema podem precisar reagir a alterações no estado do jogo, como atualizações na pontuação, mudança de turno ou alterações no estado do tabuleiro. Sem o Observer Pattern, cada componente precisaria interrogar constantemente o estado do jogo ou ser explicitamente atualizado por métodos diretos, aumentando o acoplamento e a complexidade do código.
```plantuml
@startuml
title UML - Sem Observer Pattern

class GameController {
  - state: String
  - display: Display
  - scoreBoard: ScoreBoard
  + setState(newState: String): void
}

class Display {
  + update(data: String): void
}

class ScoreBoard {
  + update(data: String): void
}

GameController --> Display : "chama update()"
GameController --> ScoreBoard : "chama update()"
@enduml
```

Com o Observer Pattern, o sujeito central (por exemplo, um controlador de estado do jogo) notifica automaticamente todos os observadores registrados assim que seu estado muda, garantindo que a interface do usuário, o sistema de pontuação e outros módulos recebam as atualizações em tempo real e de forma desacoplada.

```plantuml
@startuml
title Observer Pattern para Jogo de Tabuleiro

interface TabletopObserver {
  + update(data: String): void
}

abstract class TabletopSubject {
  + attach(observer: TabletopObserver): void
  + detach(observer: TabletopObserver): void
  + notifyObservers(data: String): void
}

class TabletopConcreteSubject {
  - observers: List<TabletopObserver>
  - state: String
  + TabletopConcreteSubject(initialState: String)
  + setState(newState: String): void
  + attach(observer: TabletopObserver): void
  + detach(observer: TabletopObserver): void
  + notifyObservers(data: String): void
}

class TabletopConcreteObserver {
  - name: String
  + TabletopConcreteObserver(name: String)
  + update(data: String): void
}

TabletopConcreteSubject ..|> TabletopSubject
TabletopConcreteObserver ..|> TabletopObserver

TabletopConcreteSubject --> TabletopObserver : "notifica"
@enduml
```

---

## Estrutura do Padrão (GOF - Papéis)

- **Observer (TabletopObserver):**
  - Define a interface para os observadores que desejam receber atualizações.
  - Declara o método `update(String data)`, que será chamado pelo sujeito quando ocorrer uma mudança de estado.

- **Subject (TabletopSubject):**
  - Define a interface ou classe abstrata para o sujeito observado.
  - Declara os métodos `attach(TabletopObserver observer)`, `detach(TabletopObserver observer)` e `notifyObservers(String data)`, responsáveis por gerenciar os observadores e disseminar atualizações.

- **ConcreteSubject (TabletopConcreteSubject):**
  - Implementa a classe abstrata `TabletopSubject` e mantém o estado interno do objeto.
  - Quando seu estado é alterado (por meio do método `setState(String newState)`), ele notifica todos os observadores registrados.

- **ConcreteObserver (TabletopConcreteObserver):**
  - Implementa a interface `TabletopObserver`.
  - Define o comportamento do observador quando uma atualização é recebida, por exemplo, exibindo a mensagem de atualização no console.

# Factory Method Pattern

## 1. Intenção



## Motivação

Em um framework de jogo de tabuleiro, a criação de diferentes produtos (como peças, tabuleiros ou componentes) pode variar conforme o contexto e as regras do jogo. Sem um padrão de criação centralizada, o código cliente precisaria conhecer os detalhes de instanciamento de cada produto, aumentando o acoplamento e a complexidade. Com o Factory Method, o processo de criação é encapsulado em um criador abstrato, permitindo a flexibilidade e a reutilização do código ao instanciar os produtos corretos conforme a necessidade.

---

## Estrutura do Padrão (GOF - Papéis)

- **Creator (TabletopCreator):**
  - Classe abstrata que declara o método `factoryMethod()`, responsável pela criação do produto abstrato.
  - Possui também o método `createProduct()`, que chama o `factoryMethod()` e retorna o produto.

- **ConcreteCreator (TabletopConcreteCreator):**
  - Implementa o método `factoryMethod()`, instanciando um produto concreto de acordo com a lógica do sistema.

- **Product (TabletopAbstractProduct):**
  - Classe abstrata que define a interface dos produtos criados.
  - Declara o método `describe()`, que será implementado pelas classes concretas.

- **ConcreteProduct (TabletopConcreteProduct):**
  - Implementa a interface do produto, fornecendo uma descrição e métodos específicos para o produto concreto.

---

## 4. UML do Factory Method Pattern

```plantuml
@startuml
title Factory Method Pattern para Jogo de Tabuleiro

abstract class TabletopCreator {
    + factoryMethod(): TabletopAbstractProduct
    + createProduct(): TabletopAbstractProduct
}

class TabletopConcreteCreator {
    - name: String
    + TabletopConcreteCreator(name: String)
    + factoryMethod(): TabletopAbstractProduct
}

abstract class TabletopAbstractProduct {
    + describe(): String
}

class TabletopConcreteProduct {
    - name: String
    + TabletopConcreteProduct(name: String)
    + describe(): String
    + getName(): String
}

TabletopCreator <|-- TabletopConcreteCreator
TabletopAbstractProduct <|-- TabletopConcreteProduct
TabletopCreator --> TabletopAbstractProduct : "factoryMethod()"
@enduml




