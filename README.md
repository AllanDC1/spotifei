# 🎵 Spotifei

Spotifei é uma plataforma de informações sobre músicas digitais, desenvolvida como projeto no 3º semestre do curso de CC da FEI. Inspirado na experiência do Spotify, o sistema permite aos usuários interagirem com um acervo de músicas por meio de buscas, playlists e reações às músicas — **sem reprodução de áudio**.

## 📌 Funcionalidades

- Cadastro e login de usuários
- Busca de músicas por nome, artista ou gênero
- Visualização de informações das músicas
- Visualizar histórico das últimas buscas
- Curtir e descurtir músicas
- Listar músicas curtidas e descurtidas
- Criar, editar e excluir playlists
- Adicionar/remover músicas de playlists

## 🧭 Navegação

Após o login ou cadastro, o usuário acessa a tela principal com uma **barra lateral de navegação**, que oferece os seguintes atalhos:

- **Músicas:** Busca e visualização de músicas
- **Playlists:** Gerenciamento de playlists
- **Curtidas:** Lista de músicas curtidas
- **Descurtidas:** Lista de músicas descurtidas
- **Sair:** Retorna ao login/cadastro

## 🛠️ Tecnologias Utilizadas

- **Java**
- **Swing** — Interface gráfica
- **PostgreSQL + JDBC** — Persistência de dados
- **Padrão MVC** — Separação de responsabilidades
- **Maven** — Gerenciamento de dependências e build

## 🚀 Como Executar o Projeto

### Requisitos
- Java 17 ou superior
- PgAdmin 4 (PostgreSQL) rodando com o banco configurado
- IDE NetBeans *(Apenas se quiser compilar ou visualizar previews das interfaces)*

### 1. Clone o repositório:
```bash
git clone https://github.com/AllanDC1/spotifei.git
```

### 2. Clone o banco de dados:
  1. Abra o PgAdmin 4 e conecte-se ao servidor desejado
  2. Com botão direito no servidor, selecione Create -> Database
  3. Dê o nome `spotifei` para o novo banco e clique em Save
  4. Com botão direito no banco criado, selecione Query Tool
  5. Clique em Open File (ícone de pasta) e escolha o arquivo  `spotifei-bd.sql` (presente no repositório)
  6. Clique no botão Execute/Run (ícone de raio), e o banco será criado <br> <br>
  *Caso necessário ajustar configs de owner e url, edite o arquivo ``bd.properties`` em `spotifei/src/main/resources`

### 3. (Opcional) Compile o projeto:
  1. Abra o projeto pela IDE NetBeans
  2. Selecione `Clean and build` para compilar e gerar o execútavel

### 4. Execute o projeto:  
  - Rode diretamente pela IDE, ou use o executável ``spotifei-1.0 (.jar)``, gerado em `spotifei/target` <br>
  - Ou ainda via comando no diretório `spotifei/target`:
  
    java -jar spotifei-1.0.jar
    
