# DnD Micro Services

## Sobre

Este projeto é uma simulação de um ambiente de Dungeons & Dragons (D&D), implementada através de uma arquitetura de microserviços utilizando Quarkus. O projeto é dividido em diferentes serviços, cada um responsável por uma funcionalidade específica dentro do universo do jogo.

### Principais Componentes:

- Monster Service: Este serviço gerencia as entidades "monstros" do jogo. É possível criar, atualizar, recuperar e deletar monstros, além de interagir com eles. Cada monstro tem atributos como vida, força, entre outros, que são persistentes e gerenciados pelo serviço.
- Hero Service: Similar ao Monster Service, este serviço lida com as entidades "heróis". Os heróis podem interagir com os monstros, participando de combates e aventuras.
- Adventure Controller: É o ponto central das interações entre heróis e monstros. Permite inspecionar monstros e simular combates entre heróis e monstros, com os resultados dessas lutas afetando o estado persistente das entidades envolvidas.

### Interação entre os Serviços:

Os serviços comunicam-se entre si para realizar as operações. Por exemplo, durante uma luta, o Adventure Controller consulta o Monster Service e o Hero Service para obter informações sobre os participantes e, em seguida, processa o resultado da luta, atualizando o estado dos envolvidos.

### Persistência:

Os dados de heróis e monstros são persistentemente armazenados, permitindo que o estado das entidades seja mantido entre as sessões de jogo. Isso inclui, por exemplo, a vida de um monstro após uma luta.

## Configuração (Setup)

### Clone o repositório

`git clone https://github.com/carlosarraes/dndmcr.git`

### Construção dos Serviços

Navegue até a pasta de cada serviço e construa-os individualmente. Por exemplo, para o serviço de monstros:

```shell
cd monster
./mvnw package -Pnative -DskipTests
# Se estiver com limitações de memória RAM, utilize a construção via Docker:
./mvnw package -Pnative -Dquarkus.native.container-build=true -DskipTests
```

Repita este processo para o hero-service:

```shell
cd ../hero
./mvnw package -Pnative -DskipTests
# Com Docker, se necessário:
./mvnw package -Pnative -Dquarkus.native.container-build=true -DskipTests
```

### Construção e Execução com Docker Compose

`docker-compose up -d --build`

### Registro dos Serviços no Consul

```shell
curl --request PUT --data @hero-service.json http://localhost:8500/v1/agent/service/register
curl --request PUT --data @monster-service.json http://localhost:8500/v1/agent/service/register
```

### Acesso aos Endpoints

Com o projeto rodando, você pode acessar os endpoints utilizando ferramentas como Postman, Insomnia ou HTTPie.

## Monster Service

URL: http://localhost:8081

### Endpoints

1. Obter Todos os Monstros

   - Método: GET
   - Caminho: /monster
   - Descrição: Recupera uma lista de todas as entidades de monstros.

2. Obter Monstro por ID

   - Método: GET
   - Caminho: /monster/{id}
   - Descrição: Recupera uma entidade específica de monstro pelo seu ID.

3. Criar um Novo Monstro

   - Método: POST
   - Caminho: /monster
   - Descrição: Cria uma nova entidade de monstro.

4. Atualizar um Monstro

   - Método: PUT
   - Caminho: /monster/{id}
   - Descrição: Atualiza uma entidade de monstro existente.

5. Deletar um Monstro

   - Método: DELETE
   - Caminho: /monster/{id}
   - Descrição: Deleta uma entidade de monstro pelo seu ID.

## Hero Service

URL: http://localhost:8080

### Endpoints

1. Obter Todos os Heróis

   - Método: GET
   - Caminho: /hero
   - Descrição: Recupera uma lista de todas as entidades de heróis.

2. Obter Herói por ID

   - Método: GET
   - Caminho: /hero/{id}
   - Descrição: Recupera uma entidade específica de herói pelo seu ID.

3. Criar um Novo Herói

   - Método: POST
   - Caminho: /hero
   - Descrição: Cria uma nova entidade de herói.

4. Atualizar um Herói

   - Método: PUT
   - Caminho: /hero/{id}
   - Descrição: Atualiza uma entidade de herói existente.

5. Deletar um Herói

   - Método: DELETE
   - Caminho: /hero/{id}
   - Descrição: Deleta uma entidade de herói pelo seu ID.

6. Inspecionar Monstro

   - Método: GET
   - Caminho: /adventure/inspect/{id}
   - Descrição: Permite inspecionar um monstro pelo seu ID.
   - Parâmetros do Caminho:
     - id: O ID do monstro a ser inspecionado.
   - Resposta: Um objeto Monster se encontrado, caso contrário, um erro 404.

7. Luta entre Herói e Monstro

   - Método: GET
   - Caminho: /adventure/fight/{hero_id}/{monster_id}
   - Descrição: Inicia uma luta entre um herói e um monstro.
   - Parâmetros do Caminho:
     - hero_id: O ID do herói participante da luta.
     - monster_id: O ID do monstro participante da luta.
   - Resposta: Um mapa Map<String, String> representando o resultado da luta se bem-sucedida; caso contrário, um erro 404.

## Consul

Consul service discovery pode ser acessado atraves do endereco: `http://localhost:8500/ui/`

PS.: Lembre de adicionar os servicos que tem no passo 5 da área Setup.
